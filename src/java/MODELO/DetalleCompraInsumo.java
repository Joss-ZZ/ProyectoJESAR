/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

/**
 *
 * @author PCGAMING
 */
public class DetalleCompraInsumo {
    
    private int id_orden_compra;
    private int id_insumo;
    private double cantidad,stock,total_c;
    private String descrip_insumo,categ_insumo,unidad;
    
     Conexion conn =  new Conexion();

    public DetalleCompraInsumo() {
    }
    public DetalleCompraInsumo(Conexion conn) {
        this.conn = conn;
    }
    
    // detalle insumo consulta
    public DetalleCompraInsumo(int id_insumo, String descrip_insumo, String categ_insumo,double cantidad,  String unidad) {
        this.id_insumo = id_insumo;
        this.descrip_insumo = descrip_insumo;
        this.categ_insumo = categ_insumo;
        this.cantidad = cantidad;
        this.unidad = unidad;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public double getTotal_c() {
        return total_c;
    }

    public void setTotal_c(double total_c) {
        this.total_c = total_c;
    }

    public int getId_orden_compra() {
        return id_orden_compra;
    }

    public void setId_orden_compra(int id_orden_compra) {
        this.id_orden_compra = id_orden_compra;
    }

    public int getId_insumo() {
        return id_insumo;
    }

    public void setId_insumo(int id_insumo) {
        this.id_insumo = id_insumo;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescrip_insumo() {
        return descrip_insumo;
    }

    public void setDescrip_insumo(String descrip_insumo) {
        this.descrip_insumo = descrip_insumo;
    }

    public String getCateg_insumo() {
        return categ_insumo;
    }

    public void setCateg_insumo(String categ_insumo) {
        this.categ_insumo = categ_insumo;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }


    

    public LinkedList<DetalleCompraInsumo> ListarCompraInsumo(int id_ordencompra) {
        try {
            String query = "SELECT \n"
                    + "Z_DETALLE_COMPRAINSUMO.ID_ORDEN_COMPRA ,\n"
                    + "Z_DETALLE_COMPRAINSUMO.ID_INSUMO,\n"
                    + "Z_INSUMO.DESCRIPCION ,\n"
                    + "Z_CATEGORIA.DESCRIPCION,\n"
                    + "Z_DETALLE_COMPRAINSUMO.CANTIDAD_INSUMO,\n"
                    + "Z_INSUMO.CANT ,\n"
                    + "Z_UNIDAD.DESCRIPCION \n"
                    + "FROM  Z_DETALLE_COMPRAINSUMO \n"
                    + "INNER JOIN Z_INSUMO  ON  Z_INSUMO.ID = Z_DETALLE_COMPRAINSUMO.ID_INSUMO\n"
                    + "INNER JOIN Z_CATEGORIA ON  Z_CATEGORIA.ID = Z_INSUMO.ID_CAT\n"   
                    + "INNER JOIN Z_UNIDAD ON Z_UNIDAD.ID=Z_INSUMO.ID_UND\n"
                    + "where id_orden_compra = ?"
                    + ";";
            PreparedStatement ps = conn.getConnection().prepareStatement(query);
            ps.setInt(1, id_ordencompra);
            ResultSet resultado = ps.executeQuery();
            LinkedList<DetalleCompraInsumo> lista7;
            lista7 = new LinkedList<DetalleCompraInsumo>();
            while (resultado.next()) {
                DetalleCompraInsumo det = new DetalleCompraInsumo();
                det.setId_orden_compra(resultado.getInt("Z_DETALLE_COMPRAINSUMO.ID_ORDEN_COMPRA"));
                det.setId_insumo(resultado.getInt("Z_DETALLE_COMPRAINSUMO.ID_INSUMO"));
                det.setDescrip_insumo(resultado.getString("Z_INSUMO.DESCRIPCION"));
                det.setCateg_insumo(resultado.getString("Z_CATEGORIA.DESCRIPCION"));
                det.setCantidad(resultado.getDouble("Z_DETALLE_COMPRAINSUMO.CANTIDAD_INSUMO"));
                det.setStock(resultado.getDouble("Z_INSUMO.CANT"));
                det.setUnidad(resultado.getString("Z_UNIDAD.DESCRIPCION"));
                lista7.add(det);
            }
            conn.desconectar();
            return lista7;
        } catch (Exception e) {
            System.out.println("Problema de conexion...DetalleCompraInsumo Listar");
        }
        return null;
    }

 public LinkedList<DetalleCompraInsumo> detalleinsumo(){
        try {
            String query =  "select * from reporte_detalleinsumo; ";
            Statement sentencia = conn.getConnection().createStatement();
            ResultSet resultado = sentencia.executeQuery(query);
            LinkedList<DetalleCompraInsumo> lista6;
            lista6 = new LinkedList<DetalleCompraInsumo>();
            while(resultado.next()){

                DetalleCompraInsumo u = new DetalleCompraInsumo();
                u.setId_insumo(resultado.getInt("id"));
                u.setDescrip_insumo(resultado.getString("insumo"));
                u.setCateg_insumo(resultado.getString("categoria"));
                u.setCantidad(resultado.getDouble("stock")); //stock insumo
                u.setUnidad(resultado.getString("unidad"));
                lista6.add(u);
            }
            conn.desconectar();
            return lista6;
        } catch (Exception e) {
            System.out.println("Problema de conexion en consulta insumo...");
        }
        return null;
    }
    
 
  public void MantenerDetalleCompraInsumo(DetalleCompraInsumo d, String accion) {
        String sql = "{CALL PRC_DET_COMP_INS(?,?,?,?)}";
        try {
            CallableStatement cs = conn.getConnection().prepareCall(sql);
            cs.setInt(1,d.getId_orden_compra());
            cs.setInt(2,d.getId_insumo());
            cs.setDouble(3, d.getCantidad());
            cs.setString(4, accion);
            cs.execute();
            conn.desconectar();
        } catch (SQLException ex) {
            System.out.println("Error en Stock.MantenerDetalleCompraInsumo: " + ex.getMessage());
        }
    }
     
     
       public void actualizardetalle(int ord_comp2, int id_ins ,double cant){
       try {
            String query = "UPDATE Z_DETALLE_COMPRAINSUMO  SET id_orden_compra=?, ";
            query = query + "id_insumo=?, ";
            query = query + "cantidad_insumo=? ";
            query = query + " WHERE id_orden_compra=? and  id_insumo=?; ";
            PreparedStatement sentencia = conn.getConnection().prepareStatement(query);
            sentencia.setInt(1, ord_comp2);
            sentencia.setInt(2, id_ins);
            sentencia.setDouble(3, cant);
            sentencia.executeUpdate();
            sentencia.close();
            conn.desconectar();
        } catch (Exception e) {
            System.out.println("Problemas de actualizacion...en detalle compra insumo");
        }
     }
 
 
 
}

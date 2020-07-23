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
public class OrdenCompraInsumo {
    
    private int id_orden_compinsumo;
    private String fecha_inicio;
    private String hora;
    private String estado;
    Conexion conn =  new Conexion();
    
    public OrdenCompraInsumo() {
    }

    public OrdenCompraInsumo(Conexion conn) {
        this.conn = conn;
    }
    public OrdenCompraInsumo(int id_orden_compinsumo, String fecha_inicio, String hora, String estado) {
        this.id_orden_compinsumo = id_orden_compinsumo;
        this.fecha_inicio = fecha_inicio;
        this.hora = hora;
        this.estado = estado;
    }


    public int getId_orden_compinsumo() {
        return id_orden_compinsumo;
    }

    public void setId_orden_compinsumo(int id_orden_compinsumo) {
        this.id_orden_compinsumo = id_orden_compinsumo;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
    
    
        
      public void MantenerCompraInsumo(OrdenCompraInsumo o, String accion) {
        String sql = "{CALL PRC_ORDEN_COMPIN(?,?,?,?,?)}";
        try {
            CallableStatement cs = conn.getConnection().prepareCall(sql);
            cs.setInt(1, o.getId_orden_compinsumo());
            cs.setString(2, o.getFecha_inicio());
            cs.setString(3, o.getHora());
            cs.setString(4, o.getEstado());
            cs.setString(5, accion);
            cs.execute();
            conn.desconectar();
        } catch (SQLException ex) {
            System.out.println("Error en Stock.MantenerCompraInsumo: " + ex.getMessage());
        }
    }
    /*
    public LinkedList<OrdenCompraInsumo> Listar(int id, String accion) {
        LinkedList<OrdenCompraInsumo> ordencompin = new LinkedList<OrdenCompraInsumo>();
      //  String sql = "{CALL PRC_MANTE_STOCKS(?,'',0,?)}"; // el cero 
       String sql = "{CALL PRC_ORDEN_COMPIN(?,'',?,?)}";
      
        try {
            CallableStatement cs = conn.getConnection().prepareCall(sql);
            cs.setInt(1, id);
            cs.setString(2, accion);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                OrdenCompraInsumo u = new OrdenCompraInsumo();
                u.setId_orden_compinsumo(rs.getInt("id_orden_compra"));
                u.setFecha_inicio(rs.getString("fecha_inicio"));
                u.setEstado(rs.getString("estado"));
                ordencompin.add(u);
            }
            conn.desconectar();
            return ordencompin;
        } catch (SQLException ex) {
            System.out.println("Error en CompraInsumo.Listar: " + ex.getMessage());
        }
        return null;
    }*/

   public LinkedList<OrdenCompraInsumo> ordencomprainsumolista(){
        try {
            String query =  "select * from Z_ORDEN_COMPRAINSUMO; ";
            Statement sentencia = conn.getConnection().createStatement();
            ResultSet resultado = sentencia.executeQuery(query);
            LinkedList<OrdenCompraInsumo> lista6;
            lista6 = new LinkedList<OrdenCompraInsumo>();
            while(resultado.next()){

                OrdenCompraInsumo u = new OrdenCompraInsumo();
                u.setId_orden_compinsumo(resultado.getInt("id_orden_compra"));
                u.setFecha_inicio(resultado.getString("fecha_inicio"));
                u.setHora(resultado.getString("hora"));
                u.setEstado(resultado.getString("estado"));
                lista6.add(u);
            }
            conn.desconectar();
            return lista6;
        } catch (Exception e) {
            System.out.println("Problema de conexion...");
        }
        return null;
    }
    
        public void Actualizarestado(int id2, String estado, int id) {
        try {
            String query = "UPDATE Z_ORDEN_COMPRAINSUMO SET id_orden_compra=?, ";
            query = query + "estado=? ";
            query = query + "WHERE id_orden_compra=?; ";
            PreparedStatement sentencia = conn.getConnection().prepareStatement(query);
            sentencia.setInt(1, id2);
            sentencia.setString(2, estado);
            sentencia.setInt(3, id);
            sentencia.executeUpdate();
            sentencia.close();
            conn.desconectar();
        } catch (Exception e) {
            System.out.println("Problemas de actualizacion..en cambiar estado orden compra insumo ");
        }
    }
}

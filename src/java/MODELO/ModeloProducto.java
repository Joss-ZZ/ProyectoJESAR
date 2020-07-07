/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Rodrigo
 */
public class ModeloProducto {
    private String cod_modeloproducto;
    private int id_categoria;
    private String nombre;
    private double p_venta;
    private double p_cortado;
    private double p_aparado;
    private double p_armado;
    private double p_alistado;
    Conexion conn =  new Conexion();
    
    public ModeloProducto(Conexion conn) {
        this.conn = conn;
    }

    public ModeloProducto() {
        this.cod_modeloproducto = "";
        this.id_categoria = 0;
        this.nombre = "";
        this.p_venta = 0;
        this.p_cortado = 0;
        this.p_aparado = 0;
        this.p_armado = 0;
        this.p_alistado = 0;
    }

    public ModeloProducto(String cod_modeloproducto, int id_categoria, String nombre, double p_venta, double p_cortado, double p_aparado, double p_armado, double p_alistado) {
        this.cod_modeloproducto = cod_modeloproducto;
        this.id_categoria = id_categoria;
        this.nombre = nombre;
        this.p_venta = p_venta;
        this.p_cortado = p_cortado;
        this.p_aparado = p_aparado;
        this.p_armado = p_armado;
        this.p_alistado = p_alistado;
    }

    public String getCod_modeloproducto() {
        return cod_modeloproducto;
    }

    public void setCod_modeloproducto(String cod_modeloproducto) {
        this.cod_modeloproducto = cod_modeloproducto;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getP_venta() {
        return p_venta;
    }

    public void setP_venta(double p_venta) {
        this.p_venta = p_venta;
    }

    public double getP_cortado() {
        return p_cortado;
    }

    public void setP_cortado(double p_cortado) {
        this.p_cortado = p_cortado;
    }

    public double getP_aparado() {
        return p_aparado;
    }

    public void setP_aparado(double p_aparado) {
        this.p_aparado = p_aparado;
    }

    public double getP_armado() {
        return p_armado;
    }

    public void setP_armado(double p_armado) {
        this.p_armado = p_armado;
    }

    public double getP_alistado() {
        return p_alistado;
    }

    public void setP_alistado(double p_alistado) {
        this.p_alistado = p_alistado;
    }

    public Conexion getConn() {
        return conn;
    }

    public void setConn(Conexion conn) {
        this.conn = conn;
    }
    
    public LinkedList<ModeloProducto> ListarModelosProductos(){
        String sql = "SELECT * FROM Z_MODELO_PRODUCTO";
        LinkedList<ModeloProducto> lista = new LinkedList<>();
        try {
            System.out.println("SERIE");
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ModeloProducto ModeloProducto = new ModeloProducto();
                ModeloProducto.setCod_modeloproducto(rs.getString("cod_modeloproducto"));
                ModeloProducto.setNombre(rs.getString("nombre"));
                lista.add(ModeloProducto);               
            }
            conn.desconectar();
            return lista;
        } catch (Exception e) {
            System.out.println("Problema en ModeloProducto.ListarModelosProductos: "+e.getMessage());
        }
        return null;
    }

    
    public JsonArray MantenerModeloProducto(ModeloProducto modeloProducto, String accion) {
        String sql = "{CALL PRC_MANTE_MODPRO(?,?,?,?,?,?,?,?,?)}";
        try {
            CallableStatement cs = conn.getConnection().prepareCall(sql);
            if (accion.equalsIgnoreCase("Eliminar")) {
                cs.setString(1, modeloProducto.getCod_modeloproducto());
                cs.setInt(2, 0);
                cs.setString(3, "");
                cs.setDouble(4, 0);
                cs.setDouble(5, 0);
                cs.setDouble(6, 0);
                cs.setDouble(7, 0);
                cs.setDouble(8, 0);
                cs.setString(9, accion);
                cs.executeUpdate();
                conn.desconectar();
                return null;
            } else if(accion.equalsIgnoreCase("Nuevo") || accion.equalsIgnoreCase("Editar")){
                cs.setString(1, modeloProducto.getCod_modeloproducto());
                cs.setInt(2, modeloProducto.getId_categoria());
                cs.setString(3, modeloProducto.getNombre());
                cs.setDouble(4, modeloProducto.getP_venta());
                cs.setDouble(5, modeloProducto.getP_cortado());
                cs.setDouble(6, modeloProducto.getP_aparado());
                cs.setDouble(7, modeloProducto.getP_armado());
                cs.setDouble(8, modeloProducto.getP_alistado());
                cs.setString(9, accion);
                ResultSet rs = cs.executeQuery();
                JsonArray array = new JsonArray();
                rs.next();
                JsonObject item = new JsonObject();
                item.addProperty("cod_modeloproducto", rs.getString("MP.cod_modeloproducto"));
                item.addProperty("id_categoria", rs.getInt("CAT.id"));
                item.addProperty("nombre", rs.getString("MP.nombre"));
                item.addProperty("p_venta", rs.getDouble("MP.p_venta"));
                item.addProperty("p_cortado", rs.getDouble("MP.p_cortado"));
                item.addProperty("p_aparado", rs.getDouble("MP.p_aparado"));
                item.addProperty("p_armado", rs.getDouble("MP.p_armado"));
                item.addProperty("p_alistado", rs.getDouble("MP.p_alistado"));
                array.add(item);
                conn.desconectar();
                return array;
            }
        } catch (SQLException ex) {
            System.out.println("Error en ModeloProducto.MantenerModeloProducto: " + ex.getMessage());
        }
        return null;
    }
    
    public JsonArray ListarModeloProducto(int id, String accion) {
        String sql = "{CALL PRC_MANTE_MODPRO('',0,'',0,0,0,0,0,?)}";
        try {
            System.out.println("ddd p");
            CallableStatement cs = conn.getConnection().prepareCall(sql);
            cs.setString(1, accion);
            ResultSet rs = cs.executeQuery();
            JsonArray array = new JsonArray();
            while (rs.next()) {
                JsonObject item = new JsonObject();
                item.addProperty("cod_modeloproducto", rs.getString("MP.cod_modeloproducto"));
                item.addProperty("id_categoria", rs.getInt("CAT.id"));
                item.addProperty("des_categoria", rs.getString("CAT.descripcion"));
                item.addProperty("nombre", rs.getString("MP.nombre"));
                item.addProperty("p_venta", rs.getDouble("MP.p_venta"));
                item.addProperty("p_cortado", rs.getDouble("MP.p_cortado"));
                item.addProperty("p_aparado", rs.getDouble("MP.p_aparado"));
                item.addProperty("p_armado", rs.getDouble("MP.p_armado"));
                item.addProperty("p_alistado", rs.getDouble("MP.p_alistado"));
                array.add(item);
            }
            conn.desconectar();
            return array;
        } catch (SQLException e) {
            System.out.println("Error en ModeloProducto.ListarModeloProducto: " + e.getMessage());
        }
        return null;
    }
}

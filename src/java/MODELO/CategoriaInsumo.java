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
 * @author JhoxiZZ
 */
public class CategoriaInsumo {
    private int id;
    private String descripcion;
    Conexion conn;

    public CategoriaInsumo(Conexion conn) {
        this.conn = conn;
    }
        
    public CategoriaInsumo() {
        this.id = 0;
        this.descripcion = "";
    }

    public CategoriaInsumo(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }
    
    public Conexion getConn() {
        return conn;
    }

    public void setConn(Conexion conn) {
        this.conn = conn;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public LinkedList<CategoriaInsumo> ListarCat(){
        String sql = "SELECT *FROM Z_CATEGORIA";
        LinkedList<CategoriaInsumo> lista = new LinkedList<>();
        try {
            System.out.println("CATEGORIA");
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                CategoriaInsumo categoria = new CategoriaInsumo();
                categoria.setId(rs.getInt("id"));
                categoria.setDescripcion(rs.getString("descripcion"));
                lista.add(categoria);               
            }
            conn.desconectar();
            return lista;
        } catch (Exception e) {
            System.out.println("Problema es CategoriaInsumo.ListarCat: "+e.getMessage());
        }
        return null;
    }
    
    public JsonArray MantenerCategoriaInsumo(CategoriaInsumo categoria, String accion) {
        String sql = "{CALL PRC_MANTE_CAT(?, ?, ?)}";
        try {
            CallableStatement cs = conn.getConnection().prepareCall(sql);
            if (accion.equalsIgnoreCase("Eliminar")) {
                cs.setInt(1, categoria.getId());
                cs.setString(2, "");
                cs.setString(3, accion);
                cs.executeUpdate();
                conn.desconectar();
                return null;
            } else if(accion.equalsIgnoreCase("Nuevo") || accion.equalsIgnoreCase("Editar")){
                cs.setInt(1, categoria.getId());
                cs.setString(2, categoria.getDescripcion());
                cs.setString(3, accion);
                ResultSet rs = cs.executeQuery();
                JsonArray array = new JsonArray();
                rs.next();
                JsonObject item = new JsonObject();
                item.addProperty("id", rs.getInt("id"));
                item.addProperty("descripcion", rs.getString("descripcion"));
                array.add(item);
                conn.desconectar();
                return array;
            }
        } catch (SQLException ex) {
            System.out.println("Error en Color.MantenerCategoriaInsumo: " + ex.getMessage());
        }
        return null;
    }
    
    public JsonArray ListarCategoria(int id, String accion) {
        String sql = "{CALL PRC_MANTE_CAT(0,'',?)}";
        try {
            CallableStatement cs = conn.getConnection().prepareCall(sql);
            cs.setString(1, accion);
            ResultSet rs = cs.executeQuery();
            JsonArray array = new JsonArray();
            while (rs.next()) {
                JsonObject item = new JsonObject();
                item.addProperty("id", rs.getInt("id"));
                item.addProperty("descripcion", rs.getString("descripcion"));
                array.add(item);
            }
            conn.desconectar();
            return array;
        } catch (SQLException e) {
            System.out.println("Error en Color.ListarCategoriaInsumo: " + e.getMessage());
        }
        return null;
    }
    
}

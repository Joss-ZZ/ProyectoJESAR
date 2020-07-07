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
public class Serie {
    private int id;
    private String descripcion;
    Conexion conn;
    
    public Serie(Conexion conn) {
        this.conn = conn;
    }
        
    public Serie() {
        this.id = 0;
        this.descripcion = "";
    }

    public Serie(int id, String descripcion) {
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
    
    public LinkedList<Serie> ListarSeries(){
        String sql = "SELECT *FROM Z_SERIE";
        LinkedList<Serie> lista = new LinkedList<>();
        try {
            System.out.println("SERIE");
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Serie serie = new Serie();
                serie.setId(rs.getInt("id"));
                serie.setDescripcion(rs.getString("descripcion"));
                lista.add(serie);               
            }
            conn.desconectar();
            return lista;
        } catch (Exception e) {
            System.out.println("Problema en Serie.ListarSeries: "+e.getMessage());
        }
        return null;
    }
    
    public JsonArray MantenerSerie(Serie serie, String accion) {
        String sql = "{CALL PRC_MANTE_SER(?, ?, ?)}";
        try {
            CallableStatement cs = conn.getConnection().prepareCall(sql);
            if (accion.equalsIgnoreCase("Eliminar")) {
                cs.setInt(1, serie.getId());
                cs.setString(2, "");
                cs.setString(3, accion);
                cs.executeUpdate();
                conn.desconectar();
                return null;
            } else if(accion.equalsIgnoreCase("Nuevo") || accion.equalsIgnoreCase("Editar")){
                cs.setInt(1, serie.getId());
                cs.setString(2, serie.getDescripcion());
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
            System.out.println("Error en Serie.MantenerSerie: " + ex.getMessage());
        }
        return null;
    }
    
    public JsonArray ListarSerie(int id, String accion) {
        String sql = "{CALL PRC_MANTE_SER(0,'',?)}";
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
            System.out.println("Error en Serie.ListarSerie: " + e.getMessage());
        }
        return null;
    }
}

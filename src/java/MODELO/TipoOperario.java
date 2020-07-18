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
public class TipoOperario {
    private int id;
    private String descripcion;
    Conexion conn;
    
    public TipoOperario(Conexion conn) {
        this.conn = conn;
    }
        
    public TipoOperario() {
        this.id = 0;
        this.descripcion = "";
    }

    public TipoOperario(int id, String descripcion) {
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
    
    public LinkedList<TipoOperario> ListarTipoOperarios(){
        String sql = "SELECT *FROM Z_TIPO_OPERARIO";
        LinkedList<TipoOperario> lista = new LinkedList<>();
        try {
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                TipoOperario tipo_ope = new TipoOperario();
                tipo_ope.setId(rs.getInt("id"));
                tipo_ope.setDescripcion(rs.getString("descripcion"));
                lista.add(tipo_ope);               
            }
            conn.desconectar();
            return lista;
        } catch (Exception e) {
            System.out.println("Problema en TipoOperario.ListarTipoOperario: "+e.getMessage());
        }
        return null;
    }  
    
    public JsonArray MantenerTipoOperario(TipoOperario tipoOperario, String accion) {
        String sql = "{CALL PRC_MANTE_TIPO_OPE(?, ?, ?)}";
        try {
            CallableStatement cs = conn.getConnection().prepareCall(sql);
            if (accion.equalsIgnoreCase("Eliminar")) {
                cs.setInt(1, tipoOperario.getId());
                cs.setString(2, "");
                cs.setString(3, accion);
                cs.executeUpdate();
                conn.desconectar();
                return null;
            } else if(accion.equalsIgnoreCase("Nuevo") || accion.equalsIgnoreCase("Editar")){
                cs.setInt(1, tipoOperario.getId());
                cs.setString(2, tipoOperario.getDescripcion());
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
            System.out.println("Error en TipoOperario.MantenerTipoOperario: " + ex.getMessage());
        }
        return null;
    }
    
    public JsonArray ListarTipoOperario(int id, String accion) {
        String sql = "{CALL PRC_MANTE_TIPO_OPE(0,'',?)}";
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
            System.out.println("Error en TipoOperario.ListarTipoOperario: " + e.getMessage());
        }
        return null;
    }
}

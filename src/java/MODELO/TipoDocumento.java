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
public class TipoDocumento {
    private int id;
    private String descripcion;
    private int cantDigitos;
    Conexion conn;
    
    public TipoDocumento(Conexion conn) {
        this.conn = conn;
    }
        
    public TipoDocumento() {
        this.id = 0;
        this.descripcion = "";
        this.cantDigitos = 0;
    }

    public TipoDocumento(int id, String descripcion, int cantDigitos) {
        this.id = id;
        this.descripcion = descripcion;
        this.cantDigitos = cantDigitos;
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

    public int getCantDigitos() {
        return cantDigitos;
    }

    public void setCantDigitos(int cantDigitos) {
        this.cantDigitos = cantDigitos;
    }
    
    public LinkedList<TipoDocumento> ListarTipoDocumentos(){
        String sql = "SELECT *FROM Z_TIPO_DOCUMENTO";
        LinkedList<TipoDocumento> lista = new LinkedList<>();
        try {
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                TipoDocumento tipoDocumento = new TipoDocumento();
                tipoDocumento.setId(rs.getInt("id"));
                tipoDocumento.setDescripcion(rs.getString("descripcion"));
                lista.add(tipoDocumento);               
            }
            conn.desconectar();
            return lista;
        } catch (Exception e) {
            System.out.println("Problema en TipoDocumento.ListarTipoDocumentos: "+e.getMessage());
        }
        return null;
    }
    
    public JsonArray MantenerTipoDocumento(TipoDocumento tipoDocumento, String accion) {
        String sql = "{CALL PRC_MANTE_TIDOC(?, ?, ?, ?)}";
        try {
            CallableStatement cs = conn.getConnection().prepareCall(sql);
            if (accion.equalsIgnoreCase("Eliminar")) {
                cs.setInt(1, tipoDocumento.getId());
                cs.setString(2, "");
                cs.setInt(3, 0);
                cs.setString(4, accion);
                cs.executeUpdate();
                conn.desconectar();
                return null;
            } else if(accion.equalsIgnoreCase("Nuevo") || accion.equalsIgnoreCase("Editar")){
                cs.setInt(1, tipoDocumento.getId());
                cs.setString(2, tipoDocumento.getDescripcion());
                cs.setInt(3, tipoDocumento.getCantDigitos());
                cs.setString(4, accion);
                ResultSet rs = cs.executeQuery();
                JsonArray array = new JsonArray();
                rs.next();
                JsonObject item = new JsonObject();
                item.addProperty("id", rs.getInt("id"));
                item.addProperty("descripcion", rs.getString("descripcion"));
                item.addProperty("cant_digitos", rs.getInt("cant_digitos"));
                array.add(item);
                conn.desconectar();
                return array;
            }
        } catch (SQLException ex) {
            System.out.println("Error en TipoDocumento.MantenerTipoDocumento: " + ex.getMessage());
        }
        return null;
    }
    
    public JsonArray ListarTipoDocumento(int id, String accion) {
        String sql = "{CALL PRC_MANTE_TIDOC(0,'',0,?)}";
        try {
            CallableStatement cs = conn.getConnection().prepareCall(sql);
            cs.setString(1, accion);
            ResultSet rs = cs.executeQuery();
            JsonArray array = new JsonArray();
            while (rs.next()) {
                JsonObject item = new JsonObject();
                item.addProperty("id", rs.getInt("id"));
                item.addProperty("descripcion", rs.getString("descripcion"));
                item.addProperty("cant_digitos", rs.getString("cant_digitos"));
                array.add(item);
            }
            conn.desconectar();
            return array;
        } catch (SQLException e) {
            System.out.println("Error en TipoDocumento.ListarTipoDocumento: " + e.getMessage());
        }
        return null;
    }
}

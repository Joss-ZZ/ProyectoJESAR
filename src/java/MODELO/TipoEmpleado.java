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
public class TipoEmpleado {
    private int id;
    private String descripcion;
    Conexion conn;
    
    public TipoEmpleado(Conexion conn) {
        this.conn = conn;
    }
        
    public TipoEmpleado() {
        this.id = 0;
        this.descripcion = "";
    }

    public TipoEmpleado(int id, String descripcion) {
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
    
    public LinkedList<TipoEmpleado> ListarTipoEmpleados(){
        String sql = "SELECT *FROM Z_TIPO_EMPLEADO";
        LinkedList<TipoEmpleado> lista = new LinkedList<>();
        try {
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                TipoEmpleado tipoEmpleado = new TipoEmpleado();
                tipoEmpleado.setId(rs.getInt("id"));
                tipoEmpleado.setDescripcion(rs.getString("descripcion"));
                lista.add(tipoEmpleado);               
            }
            conn.desconectar();
            return lista;
        } catch (Exception e) {
            System.out.println("Problema en TipoEmpleado.ListarTipoEmpleados: "+e.getMessage());
        }
        return null;
    }
    
    public JsonArray MantenerTipoEmpleado(TipoEmpleado tipoEmpleado, String accion) {
        String sql = "{CALL PRC_MANTE_TIPO_EMP(?, ?, ?)}";
        try {
            CallableStatement cs = conn.getConnection().prepareCall(sql);
            if (accion.equalsIgnoreCase("Eliminar")) {
                cs.setInt(1, tipoEmpleado.getId());
                cs.setString(2, "");
                cs.setString(3, accion);
                cs.executeUpdate();
                conn.desconectar();
                return null;
            } else if(accion.equalsIgnoreCase("Nuevo") || accion.equalsIgnoreCase("Editar")){
                cs.setInt(1, tipoEmpleado.getId());
                cs.setString(2, tipoEmpleado.getDescripcion());
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
            System.out.println("Error en TipoEmpleado.MantenerTipoEmpleado: " + ex.getMessage());
        }
        return null;
    }
    
    public JsonArray ListarTipoEmpleado(int id, String accion) {
        String sql = "{CALL PRC_MANTE_TIPO_EMP(0,'',?)}";
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
            System.out.println("Error en TipoEmpleado.ListarTipoEmpleado: " + e.getMessage());
        }
        return null;
    }
}

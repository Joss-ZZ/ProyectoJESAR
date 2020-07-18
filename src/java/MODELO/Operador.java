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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JhoxiZZ
 */
public class Operador{

    private int id;
    private String nombre;
    private String apellidos;
    private String tipo;
    private String direccion;
    private String telefono;
    private String correo;
    private String fechanac;
    private String dni;
    Conexion conn = new Conexion();

    public Operador() {
    }

    public Operador(int id, String nombre, String apellidos, String tipo, String direccion, String telefono, String correo, String fechanac, String dni) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.tipo = tipo;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.fechanac = fechanac;
        this.dni = dni;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFechanac() {
        return fechanac;
    }

    public void setFechanac(String fechanac) {
        this.fechanac = fechanac;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Conexion getConn() {
        return conn;
    }

    public void setConn(Conexion conn) {
        this.conn = conn;
    }

    public JsonArray MantenerOperador(Operador ope, String accion) {
        String sql = "{CALL PRC_MANTE_OPERARIOS(?,?,?,?,?,?,?,?,?,?)}";
        try {
            CallableStatement cs = conn.getConnection().prepareCall(sql);
            if (accion.equalsIgnoreCase("Eliminar")) {
                cs.setInt(1, ope.getId());
                cs.setString(2, "");
                cs.setString(3, "");
                cs.setString(4, "");
                cs.setString(5, "");
                cs.setString(6, "");
                cs.setString(7, "");
                cs.setString(8, "");
                cs.setInt(9, 0);
                cs.setString(10, accion);
                cs.executeUpdate();
                conn.desconectar();
                return null;
            } else if(accion.equalsIgnoreCase("Nuevo") || accion.equalsIgnoreCase("Editar")){
                cs.setInt(1, ope.getId());
                cs.setString(2, ope.getNombre());
                cs.setString(3, ope.getApellidos());
                cs.setString(4, ope.getDireccion());
                cs.setString(5, ope.getTelefono());
                cs.setString(6, ope.getCorreo());
                cs.setString(7, ope.getFechanac());
                cs.setString(8, ope.getDni());
                cs.setInt(9, Integer.parseInt(ope.getTipo()));
                cs.setString(10, accion);
                ResultSet rs = cs.executeQuery();
                JsonArray array = new JsonArray();
                rs.next();
                JsonObject item = new JsonObject();
                item.addProperty("id", rs.getInt("ope.id"));
                item.addProperty("nombre", rs.getString("ope.nombres"));
                item.addProperty("apellidos", rs.getString("ope.apellidos"));
                item.addProperty("id_tipo", rs.getInt("tipo.id"));
                item.addProperty("tipo", rs.getString("tipo.descripcion"));
                item.addProperty("direccion", rs.getString("ope.direccion"));
                item.addProperty("telefono", rs.getString("ope.telefono"));
                item.addProperty("correo", rs.getString("ope.correo"));
                item.addProperty("fechanac", rs.getString("ope.fecha_nac"));
                item.addProperty("dni", rs.getString("ope.dni"));
                array.add(item);
                conn.desconectar();
                return array;
            }
        } catch (SQLException ex) {
            System.out.println("Error en Operador.MantenerOperador: " + ex.getMessage());
        }
        return null;
    }

    public JsonArray ListarOperador(int id, String accion) {
        String sql = "{CALL PRC_MANTE_OPERARIOS(0,'','','','','','','',0,?)}";
        try {
            CallableStatement cs = conn.getConnection().prepareCall(sql);
            cs.setString(1, accion);
            ResultSet rs = cs.executeQuery();
            JsonArray array = new JsonArray();
            while (rs.next()) {
                JsonObject item = new JsonObject();
                item.addProperty("id", rs.getInt("ope.id"));
                item.addProperty("nombre", rs.getString("ope.nombres"));
                item.addProperty("apellidos", rs.getString("ope.apellidos"));
                item.addProperty("id_tipo", rs.getInt("tipo.id"));
                item.addProperty("tipo", rs.getString("tipo.descripcion"));
                item.addProperty("direccion", rs.getString("ope.direccion"));
                item.addProperty("telefono", rs.getString("ope.telefono"));
                item.addProperty("correo", rs.getString("ope.correo"));
                item.addProperty("fechanac", rs.getString("ope.fecha_nac"));
                item.addProperty("dni", rs.getString("ope.dni"));
                array.add(item);
            }
            conn.desconectar();
            return array;
        } catch (Exception e) {
            System.out.println("Error en Operador.ListarOperador: " + e.getMessage());
        }
        return null;
    }
}

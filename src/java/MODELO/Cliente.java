/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JhoxiZZ
 */
public class Cliente {
    private int id;
    private String nombre, apellidos, tipo_documento, documento, telefono, direccion,correo;
    Conexion conn;

    public Cliente(Conexion conn) {
        this.conn = conn;
    }

    public Cliente() {
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

    public String getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public JsonArray ListarClientes(){
        String sql="SELECT *FROM Z_CLIENTE";
        System.out.println("Todo ok");
        try {
            System.out.println("Todo ok");
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            JsonArray array = new JsonArray();
            while(rs.next()){
                JsonObject item = new JsonObject();
                item.addProperty("id", rs.getInt("id"));
                item.addProperty("nombre", rs.getString("nombre"));
                item.addProperty("apellidos", rs.getString("apellidos"));
                item.addProperty("tipo_documento", rs.getString("tipo_documento"));
                item.addProperty("documento", rs.getString("documento"));
                item.addProperty("telefono", rs.getString("telefono"));
                item.addProperty("direccion", rs.getString("direccion"));
                item.addProperty("correo", rs.getString("correo"));
                item.addProperty("acciones", " <a class='btn btn-success' id='"+rs.getInt("id")+"'>Editar</a><a class='btn btn-danger' id='"+rs.getInt("id")+"' href=''>Eliminar</a>");
                array.add(item);  
            }
            return array;
        } catch (Exception e) {
            System.out.println("Problema en la consulta");
        }
        return null;
    }
}

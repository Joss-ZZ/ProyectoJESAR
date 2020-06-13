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
    private String nombre, apellidos, tipo_documento, documento, telefono, direccion, correo;
    Conexion conn = new Conexion();

    public Cliente(Conexion conn) {
        this.conn = conn;
    }

    public Cliente(String nombre, String apellidos, String tipo_documento, String documento, String telefono, String direccion, String correo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.tipo_documento = tipo_documento;
        this.documento = documento;
        this.telefono = telefono;
        this.direccion = direccion;
        this.correo = correo;
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

    public JsonArray ListarClientes() {
        String sql = "SELECT *FROM Z_CLIENTE";
        try {
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            JsonArray array = new JsonArray();
            while (rs.next()) {
                JsonObject item = new JsonObject();
                item.addProperty("id", rs.getInt("id"));
                item.addProperty("nombre", rs.getString("nombre"));
                item.addProperty("apellidos", rs.getString("apellidos"));
                item.addProperty("tipo_documento", rs.getString("tipo_documento"));
                item.addProperty("documento", rs.getString("documento"));
                item.addProperty("telefono", rs.getString("telefono"));
                item.addProperty("direccion", rs.getString("direccion"));
                item.addProperty("correo", rs.getString("correo"));
                item.addProperty("acciones", " <button type='button' class='btn btn-warning' id='" + rs.getInt("id") + "'>Editar</button><button type='button' class='btn btn-danger eliminarCliente' id='" + rs.getInt("id") + "'>Eliminar</button>");
                array.add(item);
            }
            return array;
        } catch (Exception e) {
            System.out.println("Problema en la consulta");
        }
        return null;
    }

    public int EliminarClientes(int id) {
        String sql = "DELETE FROM Z_CLIENTE WHERE ID=" + id + "";
        try {
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            int result = ps.executeUpdate();
            if (result == 1) {
                return result;
            }
            return result;
        } catch (Exception e) {
            System.out.println("ERROR EN CLIENTE.ELIMINARCLIENTESS: " + e.getMessage());
        }
        return 0;
    }

    public void RegistrarCliente(Cliente cli) {
        String sql = "INSERT INTO Z_CLIENTE(NOMBRE,APELLIDOS,TIPO_DOCUMENTO,DOCUMENTO,TELEFONO,DIRECCION,CORREO) VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ps.setString(1, cli.getNombre());
            ps.setString(2, cli.getApellidos());
            ps.setString(3, cli.getTipo_documento());
            ps.setString(4, cli.getDocumento());
            ps.setString(5, cli.getTelefono());
            ps.setString(6, cli.getDireccion());
            ps.setString(7, cli.getCorreo());
            int result = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("ERROR AL REGISTRAR CLIENTE : " + e.getMessage());
        }
    }

    public int MaxIDCliente() {
        String query = "SELECT MAX(id) FROM Z_CLIENTE";
        int id = 0;
        try {
            PreparedStatement ps = conn.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getInt("MAX(id)"));
                id = rs.getInt("MAX(id)");
            }
            return id;
        } catch (Exception e) {
            System.out.println("ERROR EN RETONAR EL ID DEL CLIENTE: " + e.getMessage());
        }
        return 0;
    }
}

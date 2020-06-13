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
public class Operador{
    private float sueldo;
    private int id;
    private String nombres, apellidos, direccion, telefono, correo, fecha_nac, dni, tipo_ope;
    Conexion conn;
    
    public Operador(Conexion conn) {
        this.conn = conn;
    }

    public Operador() {
    }
   
    public Operador(float sueldo, int id, String nombres, String apellidos, String direccion, String telefono, String correo, String fecha_nac, String dni, String tipo_ope, Conexion conn) {
        this.sueldo = sueldo;
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.fecha_nac = fecha_nac;
        this.dni = dni;
        this.tipo_ope = tipo_ope;
        this.conn = conn;
    }

    public float getSueldo() {
        return sueldo;
    }

    public void setSueldo(float sueldo) {
        this.sueldo = sueldo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
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

    public String getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(String fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTipo_ope() {
        return tipo_ope;
    }

    public void setTipo_ope(String tipo_ope) {
        this.tipo_ope = tipo_ope;
    }

    public JsonArray ListarOperario(){
        String sql="SELECT *FROM Z_OPERARIO";
        //falta
        return null;
    }
      
}

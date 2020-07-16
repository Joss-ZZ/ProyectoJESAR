package MODELO;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Empleado {

    private int id,tipoDocumento,tipoEmpleado,estado;
    private String usuario, contraseña, nombres, apellidos, direccion, telefono, correo, fecha_nac, nDocumento;
    private int privilegio;
    Conexion conn = new Conexion();

    public Empleado(Conexion conn) {
        this.conn = conn;
    }

    public Empleado() {
    }

    public Empleado(int id, String usuario, String contrasena, String nombres,String apellidos,String direccion,String telefono,String correo,String fecha_nac,int tipoDocumento,String nDocumento,int estado_id,int privilegio,int tipoEmpleado) {
        this.id = id;
        this.tipoDocumento = tipoDocumento;
        this.tipoEmpleado = tipoEmpleado;
        this.usuario = usuario;
        this.contraseña = contrasena;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.fecha_nac = fecha_nac;
        this.nDocumento = nDocumento;
        this.estado = estado_id;
        this.privilegio = privilegio;
    }
     
    public Empleado(int id, String usuario, int estado, int privilegio) {
        this.id = id;
        this.usuario = usuario;
        this.estado = estado;
        this.privilegio = privilegio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(int tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public int getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(int tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
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

    public String getnDocumento() {
        return nDocumento;
    }

    public void setnDocumento(String nDocumento) {
        this.nDocumento = nDocumento;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getPrivilegio() {
        return privilegio;
    }

    public void setPrivilegio(int privilegio) {
        this.privilegio = privilegio;
    }

    public Conexion getConn() {
        return conn;
    }

    public void setConn(Conexion conn) {
        this.conn = conn;
    }

    public Empleado AutorizaUsuario(String usr, String pass) {
        try {
            String sql = "{CALL PRC_AUTORIZA_EMPLEADOS(?, ?)}";
            CallableStatement cs = conn.getConnection().prepareCall(sql);
            cs.setString(1, usr);
            cs.setString(2, pass);
            ResultSet rs = cs.executeQuery();
            Empleado emp = null;
            if(rs.next()){
                emp = new Empleado(rs.getInt("ID"), rs.getString("USUARIO"), rs.getInt("ESTADO"), rs.getInt("PRIVILEGIO"));
            }
            return emp;
        } catch (Exception e) {
            System.out.println("Error en Empleado.AutorizaUsuario: " + e.getMessage());
        }
        return null;
    }
    
    public LinkedList<Empleado> ListarEmpleados(){
        String sql = "SELECT *FROM Z_EMPLEADO";
        LinkedList<Empleado> lista = new LinkedList<>();
        try {
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Empleado empleado = new Empleado();
                empleado.setId(rs.getInt("id"));
                empleado.setEstado(rs.getInt("estado_id"));
                lista.add(empleado);               
            }
            conn.desconectar();
            return lista;
        } catch (Exception e) {
            System.out.println("Problema en Empleado.ListarEmpleados: "+e.getMessage());
        }
        return null;
    }
    
    public JsonArray MantenerEmpleado(Empleado empleado, String accion) {
        String sql = "{CALL PRC_DET_EMP(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        try {
            CallableStatement cs = conn.getConnection().prepareCall(sql);
            if (accion.equalsIgnoreCase("Eliminar")) {
                cs.setInt(1, empleado.getId());
                cs.setString(2, "");
                cs.setString(3, "");
                cs.setString(4, "");
                cs.setString(5, "");
                cs.setString(6, "");
                cs.setString(7, "");
                cs.setString(8, "");
                cs.setString(9, "");
                cs.setInt(10, 0);
                cs.setString(11, "");
                cs.setInt(12, 0);
                cs.setInt(13,0);
                cs.setInt(14, 0);
                cs.setString(15, accion);
                cs.executeUpdate();
                conn.desconectar();
                return null;
            } else if(accion.equalsIgnoreCase("Nuevo") || accion.equalsIgnoreCase("Editar")){
                System.out.println(empleado.getId());
                System.out.println(empleado.getUsuario());
                System.out.println(empleado.getContraseña());
                System.out.println(empleado.getNombres());
                System.out.println(empleado.getApellidos());
                System.out.println(empleado.getDireccion());
                System.out.println(empleado.getTelefono());
                System.out.println(empleado.getCorreo());
                System.out.println(empleado.getFecha_nac());
                System.out.println(empleado.getTipoDocumento());
                System.out.println(empleado.getnDocumento());
                System.out.println(empleado.getEstado()+"MAL");
                System.out.println(empleado.getPrivilegio());
                System.out.println(empleado.getTipoEmpleado());
                System.out.println(accion);
                cs.setInt(1, empleado.getId());
                cs.setString(2, empleado.getUsuario());
                cs.setString(3, empleado.getContraseña());
                cs.setString(4, empleado.getNombres());
                cs.setString(5, empleado.getApellidos());
                cs.setString(6, empleado.getDireccion());
                cs.setString(7, empleado.getTelefono());
                cs.setString(8, empleado.getCorreo());
                cs.setString(9, empleado.getFecha_nac());
                cs.setInt(10, empleado.getTipoDocumento());
                cs.setString(11, empleado.getnDocumento());
                cs.setInt(12, empleado.getEstado());
                cs.setInt(13, empleado.getPrivilegio());
                cs.setInt(14, empleado.getTipoEmpleado());
                cs.setString(15, accion);
                ResultSet rs = cs.executeQuery();
                JsonArray array = new JsonArray();
                rs.next();
                JsonObject item = new JsonObject();
                item.addProperty("cod_emp", rs.getInt("E.id"));
                item.addProperty("usuario", rs.getString("E.usuario"));
                item.addProperty("password", rs.getString("E.contrasena"));
                item.addProperty("nombres", rs.getString("E.nombres"));
                item.addProperty("apellidos", rs.getString("E.apellidos"));
                item.addProperty("direccion", rs.getString("E.direccion"));
                item.addProperty("telefono", rs.getString("E.telefono"));
                item.addProperty("correo", rs.getString("E.correo"));
                item.addProperty("fechaNac", rs.getString("E.fecha_nac"));
                item.addProperty("TipoDoc", rs.getString("TD.descripcion"));
                item.addProperty("nDoc", rs.getString("E.nDocumento"));
                item.addProperty("estado", rs.getString("EE.estado"));
                item.addProperty("privilegio", rs.getInt("E.privilegio"));
                item.addProperty("TipoEmp", rs.getString("TP.descripcion"));
                array.add(item);
                conn.desconectar();
                return array;
            }
        } catch (SQLException ex) {
            System.out.println("Error en Empleado.MantenerEmpleado: " + ex.getMessage());
        }
        return null;
    }
    
    public JsonArray ListarEmpleado(int id, String accion) {
        String sql = "{CALL PRC_DET_EMP(0,'','','','','','','','',0,'',0,0,0,?)}";
        try {
            CallableStatement cs = conn.getConnection().prepareCall(sql);
            cs.setString(1, accion);
            ResultSet rs = cs.executeQuery();
            JsonArray array = new JsonArray();
            while (rs.next()) {
                JsonObject item = new JsonObject();
                item.addProperty("cod_emp", rs.getInt("E.id"));
                item.addProperty("usuario", rs.getString("E.usuario"));
                item.addProperty("password", rs.getString("E.contrasena"));
                item.addProperty("nombres", rs.getString("E.nombres"));
                item.addProperty("apellidos", rs.getString("E.apellidos"));
                item.addProperty("direccion", rs.getString("E.direccion"));
                item.addProperty("telefono", rs.getString("E.telefono"));
                item.addProperty("correo", rs.getString("E.correo"));
                item.addProperty("fechaNac", rs.getString("E.fecha_nac"));
                item.addProperty("TipoDoc", rs.getString("TD.descripcion"));
                item.addProperty("nDoc", rs.getString("E.nDocumento"));
                item.addProperty("estado", rs.getString("EE.estado"));
                item.addProperty("privilegio", rs.getInt("E.privilegio"));
                item.addProperty("TipoEmp", rs.getString("TP.descripcion"));
                array.add(item);
            }
            conn.desconectar();
            return array;
        } catch (SQLException e) {
            System.out.println("Error en Empleado.ListarEmpleado: " + e.getMessage());
        }
        return null;
    }
}
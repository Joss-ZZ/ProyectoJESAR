package MODELO;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Empleado {

    private int id;
    private String usuario, contraseña, nombres, apellidos, direccion, telefono, correo, fecha_nac, dni, estado;
    private int privilegio;
    Conexion conn;

    public Empleado(Conexion conn) {
        this.conn = conn;
    }

    public Empleado() {
    }

    public Empleado(int id, String usuario, String contraseña, String nombres, String apellidos, String direccion, String telefono, String correo, String fecha_nac, String dni, String estado, int privilegio) {
        this.id = id;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.fecha_nac = fecha_nac;
        this.dni = dni;
        this.estado = estado;
        this.privilegio = privilegio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getPrivilegio() {
        return privilegio;
    }

    public void setPrivilegio(int privilegio) {
        this.privilegio = privilegio;
    }

    public List<Empleado> AutorizaUsuario(String usr, String pass) {
        List<Empleado> pasajeros = new ArrayList<Empleado>();
        try {
            String sql = "{CALL PRC_AUTORIZA_EMPLEADOS(?, ?)}";
            CallableStatement cs = conn.getConnection().prepareCall(sql);
            cs.setString(1, usr);
            cs.setString(2, pass);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Empleado emp = new Empleado();
                emp.setId(rs.getInt("ID"));
                emp.setUsuario(rs.getString("USUARIO"));
                emp.setEstado(rs.getString("ESTADO"));
                emp.setPrivilegio(rs.getInt("PRIVILEGIO"));
                pasajeros.add(emp);
            }
            if(pasajeros.get(id).getEstado().equalsIgnoreCase("Activo")){
                return pasajeros;
            }
        } catch (Exception e) {
            System.out.println("Error en Empleado.AutorizaUsuario: " + e.getMessage());
        }
        return null;
    }
}

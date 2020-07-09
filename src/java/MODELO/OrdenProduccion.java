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
public class OrdenProduccion {
    private int id_orden_prod;
    private String fecha_inicio;
    private String fecha_acabado;
    private String estado;
    Conexion conn =  new Conexion();
    
    public OrdenProduccion(Conexion conn) {
        this.conn = conn;
    }

    public OrdenProduccion() {
        this.id_orden_prod = 0;
        this.fecha_inicio = "";
        this.fecha_acabado = "";
        this.estado = "";
    }

    public OrdenProduccion(int id_orden_prod, String fecha_inicio, String fecha_acabado, String estado) {
        this.id_orden_prod = id_orden_prod;
        this.fecha_inicio = fecha_inicio;
        this.fecha_acabado = fecha_acabado;
        this.estado = estado;
    }

    public int getId_orden_prod() {
        return id_orden_prod;
    }

    public void setId_orden_prod(int id_orden_prod) {
        this.id_orden_prod = id_orden_prod;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_acabado() {
        return fecha_acabado;
    }

    public void setFecha_acabado(String fecha_acabado) {
        this.fecha_acabado = fecha_acabado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Conexion getConn() {
        return conn;
    }

    public void setConn(Conexion conn) {
        this.conn = conn;
    }
    
    public LinkedList<OrdenProduccion> ListarOrdenProduccion(){
        String sql = "SELECT * FROM Z_ORDEN_PRODUCCION WHERE estado='en curso'";
        LinkedList<OrdenProduccion> lista = new LinkedList<>();
        try {
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                OrdenProduccion ordenProduccion = new OrdenProduccion();
                ordenProduccion.setId_orden_prod(rs.getInt("id_orden_prod"));
                ordenProduccion.setFecha_inicio(rs.getString("fecha_inicio"));
                ordenProduccion.setFecha_acabado(rs.getString("fecha_acabado"));
                ordenProduccion.setEstado(rs.getString("estado"));
                lista.add(ordenProduccion);               
            }
            conn.desconectar();
            return lista;
        } catch (Exception e) {
            System.out.println("Problema en OrdenProduccion.ListarOrdenesProducciones: "+e.getMessage());
        }
        return null;
    }
   
    public JsonArray MantenerOrdenProduccion(OrdenProduccion ordenProduccion, String accion) {
        String sql = "{CALL PRC_ORDEN_PRO(?,?,?,?,?)}";
        try {
            CallableStatement cs = conn.getConnection().prepareCall(sql);
            if (accion.equalsIgnoreCase("Eliminar")) {
                cs.setInt(1, ordenProduccion.getId_orden_prod());
                cs.setString(2, "");
                cs.setString(3, "");
                cs.setString(4, "");
                cs.setString(5, accion);
                cs.executeUpdate();
                conn.desconectar();
                return null;
            } else if(accion.equalsIgnoreCase("Nuevo") || accion.equalsIgnoreCase("Editar")){
                cs.setInt(1, ordenProduccion.getId_orden_prod());
                cs.setString(2, ordenProduccion.getFecha_inicio());
                cs.setString(3, ordenProduccion.getFecha_acabado());
                cs.setString(4, ordenProduccion.getEstado());
                cs.setString(5, accion);
                ResultSet rs = cs.executeQuery();
                JsonArray array = new JsonArray();
                rs.next();
                JsonObject item = new JsonObject();
                item.addProperty("id_orden_prod", rs.getInt("id_orden_prod"));
                item.addProperty("fecha_inicio", rs.getString("fecha_inicio"));
                item.addProperty("fecha_acabado", rs.getString("fecha_acabado"));
                item.addProperty("estado", rs.getString("estado"));
                array.add(item);
                conn.desconectar();
                return array;
            }
        } catch (SQLException ex) {
            System.out.println("Error en OrdenProduccion.MantenerOrdenesProduccion: " + ex.getMessage());
        }
        return null;
    }
    
    public JsonArray ListarOrdenProduccion(int id, String accion) {
        String sql = "{CALL PRC_ORDEN_PRO(0,'','','',?)}";
        try {
            CallableStatement cs = conn.getConnection().prepareCall(sql);
            cs.setString(1, accion);
            ResultSet rs = cs.executeQuery();
            JsonArray array = new JsonArray();
            while (rs.next()) {
                JsonObject item = new JsonObject();
                item.addProperty("id_orden_prod", rs.getInt("id_orden_prod"));
                item.addProperty("fecha_inicio", rs.getString("fecha_inicio"));
                item.addProperty("fecha_acabado", rs.getString("fecha_acabado"));
                if(rs.getString("estado").equals("terminado")){
                    item.addProperty("estado", "<html><div class='text-center'><div class='btn-group'><button class='btn btn-primary btn-sm btnEstadoOrden' value='"+ rs.getString("estado") +"'><i class='material-icons'>"+ rs.getString("estado") +"</i></button></div></div></html>");
                }else{
                    item.addProperty("estado", "<html><div class='text-center'><div class='btn-group'><button class='btn btn-danger btn-sm btnEstadoOrden' value='"+ rs.getString("estado") +"><i class='material-icons'>"+ rs.getString("estado") +"</i></button></div></div></html>");
                }
                array.add(item);
            }
            conn.desconectar();
            return array;
        } catch (SQLException e) {
            System.out.println("Error en OrdenProduccion.ListarOrdenesProduccion: " + e.getMessage());
        }
        return null;
    }
    
}

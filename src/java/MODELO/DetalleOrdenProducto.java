/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Rodrigo
 */
public class DetalleOrdenProducto {
    private int id_orden_prod;
    private String cod_modeloproducto;
    private int id_serie;
    private int id_color;
    private double cantidad;
    Conexion conn =  new Conexion();
    
    public DetalleOrdenProducto(Conexion conn) {
        this.conn = conn;
    }

    public DetalleOrdenProducto() {
        this.id_orden_prod = 0;
        this.cod_modeloproducto = "";
        this.id_serie = 0;
        this.id_color = 0;
        this.cantidad = 0;
    }

    public DetalleOrdenProducto(int id_orden_prod, String cod_modeloproducto, int id_serie, int id_color, double cantidad) {
        this.id_orden_prod = id_orden_prod;
        this.cod_modeloproducto = cod_modeloproducto;
        this.id_serie = id_serie;
        this.id_color = id_color;
        this.cantidad = cantidad;
    }

    public int getId_orden_prod() {
        return id_orden_prod;
    }

    public void setId_orden_prod(int id_orden_prod) {
        this.id_orden_prod = id_orden_prod;
    }

    public String getCod_modeloproducto() {
        return cod_modeloproducto;
    }

    public void setCod_modeloproducto(String cod_modeloproducto) {
        this.cod_modeloproducto = cod_modeloproducto;
    }

    public int getId_serie() {
        return id_serie;
    }

    public void setId_serie(int id_serie) {
        this.id_serie = id_serie;
    }

    public int getId_color() {
        return id_color;
    }

    public void setId_color(int id_color) {
        this.id_color = id_color;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public Conexion getConn() {
        return conn;
    }

    public void setConn(Conexion conn) {
        this.conn = conn;
    }
    
    public JsonArray MantenerDetalleOrdenProducto(DetalleOrdenProducto detalleOrdenProducto, String accion) {
        String sql = "{CALL PRC_DET_ORDEN_PRO(?,?,?,?,?,?)}";
        try {
            CallableStatement cs = conn.getConnection().prepareCall(sql);
            if (accion.equalsIgnoreCase("Eliminar")) {
                cs.setInt(1, detalleOrdenProducto.getId_orden_prod());
                cs.setString(2, detalleOrdenProducto.getCod_modeloproducto());
                cs.setInt(3, detalleOrdenProducto.getId_serie());
                cs.setInt(4, detalleOrdenProducto.getId_color());
                cs.setDouble(5, 0);
                cs.setString(6, accion);
                cs.executeUpdate();
                conn.desconectar();
                return null;
            } else if(accion.equalsIgnoreCase("Nuevo")){
                cs.setInt(1, detalleOrdenProducto.getId_orden_prod());
                cs.setString(2, detalleOrdenProducto.getCod_modeloproducto());
                cs.setInt(3, detalleOrdenProducto.getId_serie());
                cs.setInt(4, detalleOrdenProducto.getId_color());
                cs.setDouble(5, detalleOrdenProducto.getCantidad());
                cs.setString(6, accion);
                ResultSet rs = cs.executeQuery();
                JsonArray array = new JsonArray();
                rs.next();
                JsonObject item = new JsonObject();
                item.addProperty("detalleProduccion_id", rs.getInt("DOP.id_orden_prod"));
                item.addProperty("producto_id", rs.getString("P.cod_modeloproducto"));
                item.addProperty("id_serie", rs.getInt("P.id_serie"));
                item.addProperty("serie", rs.getString("S.descripcion"));
                item.addProperty("id_color", rs.getInt("P.id_color"));
                item.addProperty("color", rs.getString("C.descripcion"));
                item.addProperty("cant_prod", rs.getDouble("DOP.cantidad"));
                array.add(item);
                conn.desconectar();
                return array;
            }else if(accion.equalsIgnoreCase("Editar")){
                cs.setInt(1, detalleOrdenProducto.getId_orden_prod());
                cs.setString(2, detalleOrdenProducto.getCod_modeloproducto());
                cs.setInt(3, detalleOrdenProducto.getId_serie());
                cs.setInt(4, detalleOrdenProducto.getId_color());
                cs.setDouble(5, detalleOrdenProducto.getCantidad());
                cs.setString(6, accion);
                ResultSet rs = cs.executeQuery();
                JsonArray array = new JsonArray();
                rs.next();
                JsonObject item = new JsonObject();
                item.addProperty("id_orden_prod", rs.getInt("DOP.id_orden_prod"));
                item.addProperty("cod_modeloproducto", rs.getString("P.cod_modeloproducto"));
                item.addProperty("id_serie", rs.getInt("P.id_serie"));
                item.addProperty("serie", rs.getString("S.descripcion"));
                item.addProperty("id_color", rs.getInt("P.id_color"));
                item.addProperty("color", rs.getString("C.descripcion"));
                item.addProperty("cant_prod", rs.getDouble("DOP.cantidad"));
                array.add(item);
                conn.desconectar();
                return array;
            }
        } catch (SQLException ex) {
            System.out.println("Error en DetalleOrdenProducto.MantenerDetalleOrdenProducto: " + ex.getMessage());
        }
        return null;
    }
    
    public JsonArray ListarDetalleOrdenProducto(int id, String accion) {
        String sql = "{CALL PRC_DET_ORDEN_PRO(?,'',0,0,0,?)}";
        try {
            CallableStatement cs = conn.getConnection().prepareCall(sql);
            cs.setInt(1, id);
            cs.setString(2, accion);
            ResultSet rs = cs.executeQuery();
            JsonArray array = new JsonArray();
            while (rs.next()) {
                JsonObject item = new JsonObject();
                item.addProperty("cod_modeloproducto", rs.getString("P.cod_modeloproducto"));
                item.addProperty("nombre", rs.getString("MP.nombre"));
                item.addProperty("id_serie", rs.getInt("P.id_serie"));
                item.addProperty("descripcion_serie", rs.getString("S.descripcion"));
                item.addProperty("id_color", rs.getInt("P.id_color"));
                item.addProperty("descripcion_serie", rs.getString("C.descripcion"));
                item.addProperty("cantidad", rs.getDouble("DOP.cantidad"));
                item.addProperty("stock", rs.getDouble("P.stock_producto"));
                array.add(item);
            }
            conn.desconectar();
            return array;
        } catch (SQLException e) {
            System.out.println("Error en DetalleOrdenProducto.ListarDetalleOrdenProducto: " + e.getMessage());
        }
        return null;
    }
            
}

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
public class Producto {
    private String cod_modeloproducto;
    private int id_serie;
    private int id_color;
    private double stock_producto;
    private double stock_cortado;
    private double stock_aparado;
    private double stock_armado;
    Conexion conn =  new Conexion();
    
    public Producto(Conexion conn) {
        this.conn = conn;
    }

    public Producto() {
        this.cod_modeloproducto = "";
        this.id_serie = 0;
        this.id_color = 0;
        this.stock_producto = 0;
        this.stock_cortado = 0;
        this.stock_aparado = 0;
        this.stock_armado = 0;
    }

    public Producto(String cod_modeloproducto, int id_serie, int id_color, double stock_producto, double stock_cortado, double stock_aparado, double stock_armado) {
        this.cod_modeloproducto = cod_modeloproducto;
        this.id_serie = id_serie;
        this.id_color = id_color;
        this.stock_producto = stock_producto;
        this.stock_cortado = stock_cortado;
        this.stock_aparado = stock_aparado;
        this.stock_armado = stock_armado;
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

    public double getStock_producto() {
        return stock_producto;
    }

    public void setStock_producto(double stock_producto) {
        this.stock_producto = stock_producto;
    }

    public double getStock_cortado() {
        return stock_cortado;
    }

    public void setStock_cortado(double stock_cortado) {
        this.stock_cortado = stock_cortado;
    }

    public double getStock_aparado() {
        return stock_aparado;
    }

    public void setStock_aparado(double stock_aparado) {
        this.stock_aparado = stock_aparado;
    }

    public double getStock_armado() {
        return stock_armado;
    }

    public void setStock_armado(double stock_armado) {
        this.stock_armado = stock_armado;
    }

    public Conexion getConn() {
        return conn;
    }

    public void setConn(Conexion conn) {
        this.conn = conn;
    }
    
     public JsonArray MantenerProducto(Producto producto, String accion) {
        String sql = "{CALL PRC_MANTE_PROD(?,?,?,?,?,?,?,?)}";
        try {
            CallableStatement cs = conn.getConnection().prepareCall(sql);
            if (accion.equalsIgnoreCase("Eliminar")) {
                cs.setString(1, producto.getCod_modeloproducto());
                cs.setInt(2, producto.getId_serie());
                cs.setInt(3, producto.getId_color());
                cs.setDouble(4, 0);
                cs.setDouble(5, 0);
                cs.setDouble(6, 0);
                cs.setDouble(7, 0);
                cs.setString(8, accion);
                cs.executeUpdate();
                conn.desconectar();
                return null;
            } else if(accion.equalsIgnoreCase("Nuevo") || accion.equalsIgnoreCase("Editar")){
                cs.setString(1, producto.getCod_modeloproducto());
                cs.setInt(2, producto.getId_serie());
                cs.setInt(3, producto.getId_color());
                cs.setDouble(4, producto.getStock_producto());
                cs.setDouble(5, producto.getStock_cortado());
                cs.setDouble(6, producto.getStock_aparado());
                cs.setDouble(7, producto.getStock_armado());
                cs.setString(8, accion);
                ResultSet rs = cs.executeQuery();
                JsonArray array = new JsonArray();
                rs.next();
                JsonObject item = new JsonObject();
                item.addProperty("cod_modeloproducto", rs.getString("P.cod_modeloproducto"));
                item.addProperty("id_serie", rs.getInt("S.id"));
                item.addProperty("descripcion_serie", rs.getString("S.descripcion"));
                item.addProperty("id_color", rs.getInt("C.id"));
                item.addProperty("descripcion_serie_color", rs.getString("C.descripcion"));
                item.addProperty("stock_producto", rs.getDouble("P.stock_producto"));
                item.addProperty("stock_cortado", rs.getDouble("P.stock_cortado,"));
                item.addProperty("stock_aparado", rs.getDouble("P.stock_aparado"));
                item.addProperty("stock_armado", rs.getDouble("P.stock_armado"));
                array.add(item);
                conn.desconectar();
                return array;
            }
        } catch (SQLException ex) {
            System.out.println("Error en Producto.MantenerProducto: " + ex.getMessage());
        }
        return null;
    }

    public JsonArray ListarProductos(int id, String accion) {
        String sql = "{CALL PRC_MANTE_PROD('',0,0,0,0,0,0,?)}";
        try {
            CallableStatement cs = conn.getConnection().prepareCall(sql);
            cs.setString(1, accion);
            ResultSet rs = cs.executeQuery();
            JsonArray array = new JsonArray();
            while (rs.next()) {
                JsonObject item = new JsonObject();
                item.addProperty("cod_modeloproducto", rs.getString("P.cod_modeloproducto"));
                item.addProperty("id_serie", rs.getInt("S.id"));
                item.addProperty("descripcion_serie", rs.getString("S.descripcion"));
                item.addProperty("id_color", rs.getInt("C.id"));
                item.addProperty("descripcion_color", rs.getString("C.descripcion"));
                item.addProperty("stock_producto", rs.getDouble("P.stock_producto"));
                item.addProperty("stock_cortado", rs.getDouble("P.stock_cortado"));
                item.addProperty("stock_aparado", rs.getDouble("P.stock_aparado"));
                item.addProperty("stock_armado", rs.getDouble("P.stock_armado"));
                array.add(item);
            }
            conn.desconectar();
            return array;
        } catch (Exception e) {
            System.out.println("Error en Producto.ListarProducto: " + e.getMessage());
        }
        return null;
    }
}

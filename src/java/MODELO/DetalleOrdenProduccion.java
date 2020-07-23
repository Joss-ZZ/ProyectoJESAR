/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

/**
 *
 * @author PCGAMING
 */
public class DetalleOrdenProduccion {
    
    private int id_pedido;
    private int id_orden_prod;
    private String cod_modelo_producto,nombre_producto,categoria_modeloproducto;
    private int id_serie;
    private String serie_descripcion;
    private int id_color;
    private String color_descripcion;
    private double cantidad_produccion;

    Conexion conn =  new Conexion();

    public DetalleOrdenProduccion() {
    }
    
    
    public DetalleOrdenProduccion(Conexion conn) {
        this.conn = conn;
    }
    
    public int getId_orden_prod() {
        return id_orden_prod;
    }

    public void setId_orden_prod(int id_orden_prod) {
        this.id_orden_prod = id_orden_prod;
    }

    public String getCod_modelo_producto() {
        return cod_modelo_producto;
    }

    public void setCod_modelo_producto(String cod_modelo_producto) {
        this.cod_modelo_producto = cod_modelo_producto;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public String getCategoria_modeloproducto() {
        return categoria_modeloproducto;
    }

    public void setCategoria_modeloproducto(String categoria_modeloproducto) {
        this.categoria_modeloproducto = categoria_modeloproducto;
    }

    public int getId_serie() {
        return id_serie;
    }

    public void setId_serie(int id_serie) {
        this.id_serie = id_serie;
    }

    public String getSerie_descripcion() {
        return serie_descripcion;
    }

    public void setSerie_descripcion(String serie_descripcion) {
        this.serie_descripcion = serie_descripcion;
    }

    public int getId_color() {
        return id_color;
    }

    public void setId_color(int id_color) {
        this.id_color = id_color;
    }

    public String getColor_descripcion() {
        return color_descripcion;
    }

    public void setColor_descripcion(String color_descripcion) {
        this.color_descripcion = color_descripcion;
    }

    public double getCantidad_produccion() {
        return cantidad_produccion;
    }

    public void setCantidad_produccion(double cantidad_produccion) {
        this.cantidad_produccion = cantidad_produccion;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }
    
     public LinkedList<DetalleOrdenProduccion> ListarDetalleOrdenProduccion() {
        try {
            String query = "SELECT \n"
                    + "Z_ORDEN_PRODUCCION.ID_ORDEN_PROD ,\n"
                    + "Z_PRODUCTO.COD_MODELOPRODUCTO,\n"
                    + "Z_MODELO_PRODUCTO.NOMBRE,\n"
                    + "Z_CATEGORIA_PRODUCTO.DESCRIPCION,\n"
                    + "Z_SERIE.ID,\n"
                    + "Z_SERIE.DESCRIPCION,\n"
                    + "Z_COLOR.ID ,\n"
                    + "Z_COLOR.DESCRIPCION,\n"
                    + "Z_DETALLE_ORDEN_PRODUCCION.CANTIDAD \n"
                    + "FROM Z_DETALLE_ORDEN_PRODUCCION \n"
                    + "INNER JOIN Z_ORDEN_PRODUCCION ON Z_ORDEN_PRODUCCION.ID_ORDEN_PROD= Z_DETALLE_ORDEN_PRODUCCION.ID_ORDEN_PROD \n"
                    + "INNER JOIN Z_SERIE ON Z_SERIE.ID= Z_DETALLE_ORDEN_PRODUCCION.ID_SERIE \n"   
                    + "INNER JOIN Z_COLOR ON Z_COLOR.ID= Z_DETALLE_ORDEN_PRODUCCION.ID_COLOR \n"
                    + "INNER JOIN Z_PRODUCTO ON Z_PRODUCTO.COD_MODELOPRODUCTO = Z_DETALLE_ORDEN_PRODUCCION.COD_MODELOPRODUCTO \n"
                    + "INNER JOIN Z_MODELO_PRODUCTO ON Z_MODELO_PRODUCTO.COD_MODELOPRODUCTO= Z_PRODUCTO.COD_MODELOPRODUCTO \n"
                    + "INNER JOIN Z_CATEGORIA_PRODUCTO ON Z_CATEGORIA_PRODUCTO.ID= Z_MODELO_PRODUCTO.ID_CATEGORIA \n"
                    + ";";
       
            Statement sentencia = conn.getConnection().createStatement();
            ResultSet resultado = sentencia.executeQuery(query);
            LinkedList<DetalleOrdenProduccion> lista6;
            lista6 = new LinkedList<DetalleOrdenProduccion>();
            while (resultado.next()) {
                DetalleOrdenProduccion det = new DetalleOrdenProduccion();
                det.setId_orden_prod(resultado.getInt("Z_ORDEN_PRODUCCION.ID_ORDEN_PROD"));
                det.setCod_modelo_producto(resultado.getString("Z_PRODUCTO.COD_MODELOPRODUCTO"));
                det.setNombre_producto(resultado.getString("Z_MODELO_PRODUCTO.NOMBRE"));
                det.setCategoria_modeloproducto(resultado.getString("Z_CATEGORIA_PRODUCTO.DESCRIPCION"));
                det.setId_serie(resultado.getInt("Z_SERIE.ID"));
                det.setSerie_descripcion(resultado.getString("Z_SERIE.DESCRIPCION"));
                det.setId_color(resultado.getInt("Z_COLOR.ID"));
                det.setColor_descripcion(resultado.getString("Z_COLOR.DESCRIPCION"));
                det.setCantidad_produccion(resultado.getDouble("Z_DETALLE_ORDEN_PRODUCCION.CANTIDAD"));
                
          
                lista6.add(det);
            }
            conn.desconectar();
            return lista6;
        } catch (Exception e) {
            System.out.println("Problema de conexion...DetalleOrden Produccion Listar");
        }
        return null;
    }
    
         public LinkedList<DetalleOrdenProduccion> ListarDetallePedidoconProduccion(int id_pedido) {
        try {
            String query = "SELECT \n"
                    + "Z_PEDIDO_CLIENTE.ID_PEDIDO_CLIENTE,\n"
                    + "Z_ORDEN_PRODUCCION.ID_ORDEN_PROD ,\n"
                    + "Z_PRODUCTO.COD_MODELOPRODUCTO,\n"
                    + "Z_MODELO_PRODUCTO.NOMBRE,\n"
                    + "Z_CATEGORIA_PRODUCTO.DESCRIPCION,\n"
                    + "Z_SERIE.ID,\n"
                    + "Z_SERIE.DESCRIPCION,\n"
                    + "Z_COLOR.ID ,\n"
                    + "Z_COLOR.DESCRIPCION,\n"
                    + "Z_DETALLE_ORDEN_PRODUCCION.CANTIDAD \n"
                    + "FROM Z_DETALLE_ORDEN_PRODUCCION \n"
                    + "INNER JOIN Z_ORDEN_PRODUCCION ON Z_ORDEN_PRODUCCION.ID_ORDEN_PROD= Z_DETALLE_ORDEN_PRODUCCION.ID_ORDEN_PROD \n"
                    + "INNER JOIN Z_SERIE ON Z_SERIE.ID= Z_DETALLE_ORDEN_PRODUCCION.ID_SERIE \n"   
                    + "INNER JOIN Z_COLOR ON Z_COLOR.ID= Z_DETALLE_ORDEN_PRODUCCION.ID_COLOR \n"
                    + "INNER JOIN Z_PRODUCTO ON Z_PRODUCTO.COD_MODELOPRODUCTO = Z_DETALLE_ORDEN_PRODUCCION.COD_MODELOPRODUCTO \n"
                    + "INNER JOIN Z_MODELO_PRODUCTO ON Z_MODELO_PRODUCTO.COD_MODELOPRODUCTO= Z_PRODUCTO.COD_MODELOPRODUCTO \n"
                    + "INNER JOIN Z_CATEGORIA_PRODUCTO ON Z_CATEGORIA_PRODUCTO.ID= Z_MODELO_PRODUCTO.ID_CATEGORIA \n"
                    + "INNER JOIN Z_PEDIDO_CLIENTE ON Z_PEDIDO_CLIENTE.ID_ORDEN_PRODUCCION= Z_ORDEN_PRODUCCION.ID_ORDEN_PROD\n"
                    + "where Z_PEDIDO_CLIENTE.ID_PEDIDO_CLIENTE= ?"
                    + ";";
       

            PreparedStatement ps = conn.getConnection().prepareStatement(query);
            ps.setInt(1, id_pedido);
            ResultSet resultado = ps.executeQuery();
            LinkedList<DetalleOrdenProduccion> lista6;
            lista6 = new LinkedList<DetalleOrdenProduccion>();
            while (resultado.next()) {
                DetalleOrdenProduccion det = new DetalleOrdenProduccion();
                det.setId_pedido(resultado.getInt("Z_PEDIDO_CLIENTE.ID_PEDIDO_CLIENTE"));
                det.setId_orden_prod(resultado.getInt("Z_ORDEN_PRODUCCION.ID_ORDEN_PROD"));
                det.setCod_modelo_producto(resultado.getString("Z_PRODUCTO.COD_MODELOPRODUCTO"));
                det.setNombre_producto(resultado.getString("Z_MODELO_PRODUCTO.NOMBRE"));
                det.setCategoria_modeloproducto(resultado.getString("Z_CATEGORIA_PRODUCTO.DESCRIPCION"));
                det.setId_serie(resultado.getInt("Z_SERIE.ID"));
                det.setSerie_descripcion(resultado.getString("Z_SERIE.DESCRIPCION"));
                det.setId_color(resultado.getInt("Z_COLOR.ID"));
                det.setColor_descripcion(resultado.getString("Z_COLOR.DESCRIPCION"));
                det.setCantidad_produccion(resultado.getDouble("Z_DETALLE_ORDEN_PRODUCCION.CANTIDAD"));
                
          
                lista6.add(det);
            }
            conn.desconectar();
            return lista6;
        } catch (Exception e) {
            System.out.println("Problema de conexion...DetalleOrden Produccion Listar");
        }
        return null;
    }
    
    
}

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
 * @author JhoxiZZ
 */
public class Insumo {

    private int id_insumo;
    private String descripcion;
    private String categoria;
    private String unidad;
    private double cantidad;
    Conexion conn = new Conexion();

    public Insumo() {
    }

    public Insumo(int id_insumo, String descripcion, String categoria, String unidad, double cantidad) {
        this.id_insumo = id_insumo;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.unidad = unidad;
        this.cantidad = cantidad;
    }

    public int getId_insumo() {
        return id_insumo;
    }

    public void setId_insumo(int id_insumo) {
        this.id_insumo = id_insumo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public JsonArray MantenerInsumo(Insumo ins, String accion) {
        String sql = "{CALL PRC_MANTE_INSUMOS(?, ?, ?, ?, ?,?)}";
        LinkedList<Insumo> insumo = new LinkedList<>();
        try {
            CallableStatement cs = conn.getConnection().prepareCall(sql);
            if (accion.equalsIgnoreCase("Eliminar")) {
                cs.setInt(1, ins.getId_insumo());
                cs.setString(2, "");
                cs.setDouble(3, 0);
                cs.setInt(4, 0);
                cs.setInt(5, 0);
                cs.setString(6, accion);
                cs.executeUpdate();
                conn.desconectar();
                return null;
            } else if(accion.equalsIgnoreCase("Nuevo") || accion.equalsIgnoreCase("Editar")){
                cs.setInt(1, ins.getId_insumo());
                cs.setString(2, ins.getDescripcion());
                cs.setDouble(3, ins.getCantidad());
                cs.setInt(4, Integer.parseInt(ins.getCategoria()));
                cs.setInt(5, Integer.parseInt(ins.getUnidad()));
                cs.setString(6, accion);
                ResultSet rs = cs.executeQuery();
                JsonArray array = new JsonArray();
                rs.next();
                JsonObject item = new JsonObject();
                item.addProperty("id", rs.getInt("ins.id"));
                item.addProperty("nombre_insumo", rs.getString("ins.descripcion"));
                item.addProperty("cant", rs.getDouble("ins.cant"));
                item.addProperty("nombre_cat", rs.getString("cat.descripcion"));
                item.addProperty("nombre_und", rs.getString("und.descripcion"));
                array.add(item);
                conn.desconectar();
                return array;
            }
        } catch (SQLException ex) {
            System.out.println("Error en Insumo.MantenerInsumo: " + ex.getMessage());
        }
        return null;
    }

    public JsonArray ListarInsumos(int id, String accion) {
        String sql = "{CALL PRC_MANTE_INSUMOS(0,'',0,0,0,?)}";
        try {
            CallableStatement cs = conn.getConnection().prepareCall(sql);
            cs.setString(1, accion);
            ResultSet rs = cs.executeQuery();
            JsonArray array = new JsonArray();
            while (rs.next()) {
                JsonObject item = new JsonObject();
                item.addProperty("id", rs.getInt("ins.id"));
                item.addProperty("nombre_insumo", rs.getString("ins.descripcion"));
                item.addProperty("cant", rs.getDouble("ins.cant"));
                item.addProperty("nombre_cat", rs.getString("cat.descripcion"));
                item.addProperty("nombre_und", rs.getString("und.descripcion"));
                array.add(item);
            }
            conn.desconectar();
            return array;
        } catch (Exception e) {
            System.out.println("Error en Insumo.ListarInsumos: " + e.getMessage());
        }
        return null;
    }
}

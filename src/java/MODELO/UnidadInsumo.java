/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

/**
 *
 * @author JhoxiZZ
 */
public class UnidadInsumo {
    private int id;
    private String descripcion;
    Conexion conn;

    public UnidadInsumo() {
    }
    
    public UnidadInsumo(Conexion conn) {
        this.conn = conn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public LinkedList<UnidadInsumo> ListarUnidades(){
        String sql = "SELECT *FROM Z_UNIDAD";
        LinkedList<UnidadInsumo> lista = new LinkedList<>();
        try {
            System.out.println("UNIDAD");
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                UnidadInsumo unidad = new UnidadInsumo();
                unidad.setId(rs.getInt("id"));
                unidad.setDescripcion(rs.getString("descripcion"));
                lista.add(unidad);               
            }
            conn.desconectar();
            return lista;
        } catch (Exception e) {
            System.out.println("Problema en UnidadInsumo.ListarUnidades: "+e.getMessage());
        }
        return null;
    }    
}

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
public class CategoriaInsumo {
    private int id;
    private String descripcion;
    Conexion conn;

    public CategoriaInsumo() {
    }
    
    public CategoriaInsumo(Conexion conn) {
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
    
    public LinkedList<CategoriaInsumo> ListarCat(){
        String sql = "SELECT *FROM Z_CATEGORIA";
        LinkedList<CategoriaInsumo> lista = new LinkedList<>();
        try {
            System.out.println("CATEGORIA");
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                CategoriaInsumo categoria = new CategoriaInsumo();
                categoria.setId(rs.getInt("id"));
                categoria.setDescripcion(rs.getString("descripcion"));
                lista.add(categoria);               
            }
            conn.desconectar();
            return lista;
        } catch (Exception e) {
            System.out.println("Problema es CategoriaInsumo.ListarCat: "+e.getMessage());
        }
        return null;
    }
    
}

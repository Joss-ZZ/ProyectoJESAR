/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

/**
 *
 * @author PCGAMING
 */
public class Pedido {
    
  private int id_pedido_cliente;
  private int id_orden_prod;
  private int id_cliente;
  private String cliente_nombre,cliente_apellido,documento;
  private int id_empleado;
  private String nombre_empleado;
  private String fecha_emision;
  private String fecha_entrega;
  private String estado;
  Conexion conn =  new Conexion();
    
    public Pedido(Conexion conn) {
        this.conn = conn;
    }

    public Pedido() {
    }

    public Pedido(int id_pedido_cliente, int id_orden_prod, int id_cliente, int id_empleado, String fecha_emision, String fecha_entrega, String estado) {
        this.id_pedido_cliente = id_pedido_cliente;
        this.id_orden_prod = id_orden_prod;
        this.id_cliente = id_cliente;
        this.id_empleado = id_empleado;
        this.fecha_emision = fecha_emision;
        this.fecha_entrega = fecha_entrega;
        this.estado = estado;
    }

    public int getId_pedido_cliente() {
        return id_pedido_cliente;
    }

    public void setId_pedido_cliente(int id_pedido_cliente) {
        this.id_pedido_cliente = id_pedido_cliente;
    }

    public int getId_orden_prod() {
        return id_orden_prod;
    }

    public void setId_orden_prod(int id_orden_prod) {
        this.id_orden_prod = id_orden_prod;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getCliente_nombre() {
        return cliente_nombre;
    }

    public void setCliente_nombre(String cliente_nombre) {
        this.cliente_nombre = cliente_nombre;
    }

    public String getCliente_apellido() {
        return cliente_apellido;
    }

    public void setCliente_apellido(String cliente_apellido) {
        this.cliente_apellido = cliente_apellido;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getNombre_empleado() {
        return nombre_empleado;
    }

    public void setNombre_empleado(String nombre_empleado) {
        this.nombre_empleado = nombre_empleado;
    }

    public String getFecha_emision() {
        return fecha_emision;
    }

    public void setFecha_emision(String fecha_emision) {
        this.fecha_emision = fecha_emision;
    }

    public String getFecha_entrega() {
        return fecha_entrega;
    }

    public void setFecha_entrega(String fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
  
  
    public LinkedList<Pedido> listapedido(){
        try {
             String query = "SELECT \n"
                    + "Z_PEDIDO_CLIENTE.ID_PEDIDO_CLIENTE ,\n"
                    + "Z_ORDEN_PRODUCCION.ID_ORDEN_PROD,\n"
                    + "Z_CLIENTE.ID ,\n"
                    + "Z_CLIENTE.NOMBRE,\n"
                    + "Z_CLIENTE.APELLIDOS,\n"
                    + "Z_CLIENTE.DOCUMENTO,\n"
                    + "Z_EMPLEADO.ID,\n"
                    + "Z_EMPLEADO.NOMBRES,\n" 
                    + "Z_PEDIDO_CLIENTE.FECHA_EMISION,\n"
                    + "Z_PEDIDO_CLIENTE.FECHA_ENTREGA,\n" 
                    + "Z_PEDIDO_CLIENTE.ESTADO \n"
                    + "FROM  Z_PEDIDO_CLIENTE \n"
                    + "INNER JOIN Z_CLIENTE ON Z_CLIENTE.ID=Z_PEDIDO_CLIENTE.ID_CLIENTE\n"
                    + "INNER JOIN Z_EMPLEADO ON  Z_EMPLEADO.ID = Z_PEDIDO_CLIENTE.ID_EMPLEADO\n"   
                    + "INNER JOIN Z_ORDEN_PRODUCCION ON Z_ORDEN_PRODUCCION.ID_ORDEN_PROD=Z_PEDIDO_CLIENTE.ID_ORDEN_PRODUCCION\n"
                    + ";";
 
            Statement sentencia = conn.getConnection().createStatement();
            ResultSet resultado = sentencia.executeQuery(query);
            LinkedList<Pedido> lista6;
            lista6 = new LinkedList<Pedido>();
            while(resultado.next()){

                Pedido p = new Pedido();
                
                p.setId_pedido_cliente(resultado.getInt("Z_PEDIDO_CLIENTE.ID_PEDIDO_CLIENTE"));
                p.setId_orden_prod(resultado.getInt("Z_ORDEN_PRODUCCION.ID_ORDEN_PROD"));
                p.setId_cliente(resultado.getInt("Z_CLIENTE.ID"));
                p.setCliente_nombre(resultado.getString("Z_CLIENTE.NOMBRE"));
                p.setCliente_apellido(resultado.getString("Z_CLIENTE.APELLIDOS"));
                p.setDocumento(resultado.getString("Z_CLIENTE.DOCUMENTO"));
                p.setId_empleado(resultado.getInt("Z_EMPLEADO.ID"));
                p.setNombre_empleado(resultado.getString("Z_EMPLEADO.NOMBRES"));
                p.setFecha_emision(resultado.getString("Z_PEDIDO_CLIENTE.FECHA_EMISION"));
                p.setFecha_entrega(resultado.getString("Z_PEDIDO_CLIENTE.FECHA_ENTREGA"));
                p.setEstado(resultado.getString("Z_PEDIDO_CLIENTE.ESTADO"));
               
                lista6.add(p);
            }
            conn.desconectar();
            return lista6;
        } catch (Exception e) {
            System.out.println("Problema de conexion... listar pedido");
        }
        return null;
    }
  
    
    
}

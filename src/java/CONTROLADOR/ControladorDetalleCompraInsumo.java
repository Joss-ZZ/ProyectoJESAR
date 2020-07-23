/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADOR;

import MODELO.Conexion;
import MODELO.DetalleCompraInsumo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author PCGAMING
 */
public class ControladorDetalleCompraInsumo extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         Conexion conn=new Conexion();
        String accion = request.getParameter("accion");
        
        if (accion.equals("Eliminar")) {
           int orden = Integer.parseInt(request.getParameter("Ord"));
           int insumo = Integer.parseInt(request.getParameter("Ins"));
           DetalleCompraInsumo d = new DetalleCompraInsumo(conn);
            
           d.setId_orden_compra(Integer.parseInt(request.getParameter("Ord")));
           d.setId_insumo(Integer.parseInt(request.getParameter("Ins")));
           
           d.MantenerDetalleCompraInsumo(d, accion);
           //d.eliminardetalle(orden, insumo);
           response.sendRedirect("admin/OrdenCompraInsumo.jsp");
         //  response.sendRedirect("ControladorOrdenCompraInsumo?accion=ListarOrdenCompraInsumo");
   
        }   
        
        if (accion.equals("Buscar")) {
              int orden = Integer.parseInt(request.getParameter("Ord"));
              int id_ins = Integer.parseInt(request.getParameter("Ins"));
              
              String desc= request.getParameter("NomIns");
              String cat= request.getParameter("Categ");
              String unid= request.getParameter("Unid");
              double cant= Double.parseDouble(request.getParameter("Cant"));
              
              request.setAttribute("orden", orden);
              request.setAttribute("idinsumo",id_ins);
              request.setAttribute("descripcion",desc);
              request.setAttribute("cantidad",cant);
              request.setAttribute("categoria",cat);
              request.setAttribute("unidad",unid);
              
              System.out.println(orden + id_ins  + desc + cant);
             // System.out.println("entrooooooooooooooooooo");
              
              request.getRequestDispatcher("admin/editardetallecomprainsumo.jsp").forward(request, response);;
    
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String accion = request.getParameter("accion");
    

        Conexion conn=new Conexion();
        if (accion.equals("Nuevo")) // agrega detalle
        {
            
            
            DetalleCompraInsumo d = new DetalleCompraInsumo(conn);  
    
            int id_ord=Integer.parseInt(request.getParameter("id_ordc"));
            int id_ins=Integer.parseInt(request.getParameter("id_ins"));
            double cant= Double.parseDouble(request.getParameter("cantidad"));
        
             System.out.println(id_ord +"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
         
            d.setId_orden_compra(Integer.parseInt(request.getParameter("id_ordc")));
            System.out.println("entroooooooooooooooooooooooooo  o "+ id_ord);
            d.setId_insumo(Integer.parseInt(request.getParameter("id_ins")));
            System.out.println("entroooooooooooooooooooooooooo  i "+ id_ins);
            d.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
            System.out.println("entroooooooooooooooooooooooooo  c "+ cant);
            
          //  d.Agregardetalle(id_ord,id_ins,cant);
             
            d.MantenerDetalleCompraInsumo(d, accion);
            response.sendRedirect("admin/OrdenCompraInsumo.jsp");
         //   response.sendRedirect("ControladorOrdenCompraInsumo?accion=ListarOrdenCompraInsumo");
          //    response.sendRedirect("/Admin-JESAR/admin/registrardetallecomprainsumo.jsp");
        }
        
        
        if (accion.equals("Editar")) {
            DetalleCompraInsumo d = new DetalleCompraInsumo(conn);  
      
           // d.setId_orden_compra(Integer.parseInt(request.getParameter("Id_ordencompra")));
            int id_orden=Integer.parseInt(request.getParameter("Id_ordencompra"));
            int id_ins=Integer.parseInt(request.getParameter("Id_insumo"));
            double cant =Double.parseDouble(request.getParameter("Cantidad"));
            
            System.out.println( id_orden +"a---------->");
            System.out.println( id_ins  +"b---------->");
            System.out.println( cant  +"c---------->"); 
            

            d.setId_orden_compra(Integer.parseInt(request.getParameter("Id_ordencompra")));     
            d.setId_insumo(Integer.parseInt(request.getParameter("Id_insumo")));     
            d.setCantidad(Double.parseDouble(request.getParameter("Cantidad")));   

            d.MantenerDetalleCompraInsumo(d, accion);
            response.sendRedirect("admin/OrdenCompraInsumo.jsp");
            
            //response.sendRedirect("ControladorOrdenCompraInsumo?accion=ListarOrdenCompraInsumo");
        }        
        

        
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADOR;

import MODELO.Conexion;
import MODELO.OrdenCompraInsumo;
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
public class ControladorOrdenCompraInsumo extends HttpServlet {

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
        processRequest(request, response);

        String accion = request.getParameter("accion");
        
         if (accion.equals("ListarOrdenCompraInsumo")) {
           // OrdenCompraInsumo ord = new OrdenCompraInsumo();
         //   LinkedList<OrdenCompraInsumo> m = m.Listar(0, accion);
          //  request.setAttribute("Marcas",m);
             request.getRequestDispatcher("admin/OrdenCompraInsumo.jsp").forward(request, response);
        }
         if (accion.equals("Buscar")) {
            int id = Integer.parseInt(request.getParameter("Id"));
            String fec= request.getParameter("Fecha");
            request.setAttribute("id", id);
     
            request.getRequestDispatcher("admin/registrardetallecomprainsumo.jsp").forward(request, response); // direcciona al jsp  registrar detalle comprainsumo
        }
        if (accion.equals("Estado")) {
            Conexion conn=new Conexion();
            OrdenCompraInsumo or = new OrdenCompraInsumo(conn);  
            int  id = Integer.parseInt(request.getParameter("Idorden"));       
            String estado ="finalizado";
            or.Actualizarestado(id,estado, id);
            System.out.println("entrooooooooooooooooooooo");
            response.sendRedirect("admin/OrdenCompraInsumo.jsp");
       
        }
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        String accion = request.getParameter("accion");
   
         if (accion.equals("Nuevo")) {
            Conexion conn=new Conexion();
            OrdenCompraInsumo or = new OrdenCompraInsumo(conn);  
            
            or.setFecha_inicio(request.getParameter("fechaactual"));       
            or.setHora(request.getParameter("horaactual"));
            or.setEstado(request.getParameter("estado"));
            
            or.MantenerCompraInsumo(or, accion);
  
            response.sendRedirect("admin/OrdenCompraInsumo.jsp");
        }
         
            
  
         
    }

    
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

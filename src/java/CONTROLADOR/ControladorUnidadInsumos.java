/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADOR;

import MODELO.Conexion;
import MODELO.UnidadInsumo;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rodrigo
 */
public class ControladorUnidadInsumos extends HttpServlet {

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
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        Conexion con = new Conexion();
        
        if (request.getParameter("action").equalsIgnoreCase("Editar")) {
            String accion = request.getParameter("action");
            int id = Integer.parseInt(request.getParameter("id"));
            String descripcion = request.getParameter("descripcion");
            UnidadInsumo unidadInsumo = new UnidadInsumo(id, descripcion);
            unidadInsumo.setConn(con);
            
            com.google.gson.JsonObject gson = new JsonObject();
            String datos = "";
            JsonArray array = new JsonArray();

            array = unidadInsumo.MantenerUnidadInsumo(unidadInsumo, accion);
            gson.add("datos", array);
            response.getWriter().write(gson.toString());
            System.out.println(gson.toString());
        }

        if (request.getParameter("action").equalsIgnoreCase("Nuevo")) {
            String accion = request.getParameter("action");
            int id = 0;
            String descripcion = request.getParameter("descripcion");

            UnidadInsumo unidadInsumo = new UnidadInsumo(id, descripcion);
            unidadInsumo.setConn(con);
            
            com.google.gson.JsonObject gson = new JsonObject();
            String datos = "";
            JsonArray array = new JsonArray();

            array = unidadInsumo.MantenerUnidadInsumo(unidadInsumo, accion);
            gson.add("datos", array);
            response.getWriter().write(gson.toString());
            System.out.println(gson.toString());
        }

        if (request.getParameter("action").equalsIgnoreCase("Listar")) {
            UnidadInsumo unidadInsumo = new UnidadInsumo();
            unidadInsumo.setConn(con);
            com.google.gson.JsonObject gson = new JsonObject();
            String datos = "";
            JsonArray array = new JsonArray();
            array = unidadInsumo.ListarUnidad(0, request.getParameter("action"));
            gson.add("datos", array);
            System.out.println(gson.toString());
            out.print(gson.toString());
            out.close();
        }

        if (request.getParameter("action").equalsIgnoreCase("Eliminar")) {
            response.setContentType("text/html;charset=UTF-8");
            String accion = request.getParameter("action");
            int id = Integer.parseInt(request.getParameter("unidadInsumos_id"));
            UnidadInsumo unidadInsumo = new UnidadInsumo();
            unidadInsumo.setConn(con);
            unidadInsumo.setId(id);
            unidadInsumo.MantenerUnidadInsumo(unidadInsumo, accion);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}

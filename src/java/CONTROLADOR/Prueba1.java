/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADOR;

import MODELO.Cliente;
import MODELO.Conexion;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JhoxiZZ
 */
public class Prueba1 extends HttpServlet {

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

        if (request.getParameter("action").equalsIgnoreCase("editar")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("nombre");
            String apellidos = request.getParameter("apellidos");
            String tipodoc = request.getParameter("tipodoc");
            String doc = request.getParameter("doc");
            String telefono = request.getParameter("telefono");
            String direccion = request.getParameter("direccion");
            String correo = request.getParameter("correo");
            Cliente cliente = new Cliente(id, nombre, apellidos, tipodoc, doc, telefono, direccion, correo);
            int resultado = cliente.EditarCliente(cliente);
            if (resultado == 1) {
                com.google.gson.JsonObject gson = new JsonObject();
                JsonArray array = new JsonArray();
                JsonObject item = new JsonObject();
                item.addProperty("id", cliente.getId());
                item.addProperty("nombre", cliente.getNombre());
                item.addProperty("apellidos", cliente.getApellidos());
                item.addProperty("tipodoc", cliente.getTipo_documento());
                item.addProperty("doc", cliente.getDocumento());
                item.addProperty("telefono", cliente.getTelefono());
                item.addProperty("direccion", cliente.getDireccion());
                item.addProperty("correo", cliente.getCorreo());
                array.add(item);
                gson.add("datos", array);
                out.print(gson.toString());
            } else {
                System.out.println("NOOOOOOOOOOOOOOOOOOO");
            }

        }

        if (request.getParameter("action").equalsIgnoreCase("nuevo")) {
            String nombre = request.getParameter("nombre");
            String apellidos = request.getParameter("apellidos");
            String tipodoc = request.getParameter("tipodoc");
            String doc = request.getParameter("doc");
            String telefono = request.getParameter("telefono");
            String direccion = request.getParameter("direccion");
            String correo = request.getParameter("correo");

            Cliente cliente = new Cliente(nombre, apellidos, tipodoc, doc, telefono, direccion, correo);
            cliente.RegistrarCliente(cliente);
            cliente.setId(cliente.MaxIDCliente());

            com.google.gson.JsonObject gson = new JsonObject();
            JsonArray array = new JsonArray();
            JsonObject item = new JsonObject();

            item.addProperty("id", cliente.getId());
            item.addProperty("nombre", cliente.getNombre());
            item.addProperty("apellidos", cliente.getApellidos());
            item.addProperty("tipodoc", cliente.getTipo_documento());
            item.addProperty("doc", cliente.getDocumento());
            item.addProperty("telefono", cliente.getTelefono());
            item.addProperty("direccion", cliente.getDireccion());
            item.addProperty("correo", cliente.getCorreo());
            array.add(item);
            gson.add("datos", array);
            response.getWriter().write(gson.toString());
            System.out.println(gson.toString());
        }

        if (request.getParameter("action").equalsIgnoreCase("listar")) {
            Cliente clientes = new Cliente();
            com.google.gson.JsonObject gson = new JsonObject();
            String datos = "";
            JsonArray array = new JsonArray();
            array = clientes.ListarClientes();
            gson.add("datos", array);
            System.out.println(gson.toString());
            out.print(gson.toString());
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

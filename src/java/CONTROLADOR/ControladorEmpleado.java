/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADOR;

import MODELO.Empleado;
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
 * @author JhoxiZZ
 */
public class ControladorEmpleado extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();

        if (request.getParameter("action").equalsIgnoreCase("Editar")) {
            String accion = request.getParameter("action");
            int id = Integer.parseInt(request.getParameter("cod_emp"));
            String usuario = request.getParameter("usuario");
            String contrasena = request.getParameter("password");
            String nombres = request.getParameter("nombres");
            String apellidos = request.getParameter("apellidos");
            String direccion = request.getParameter("direccion");
            String telefono = request.getParameter("telefono");
            String correo = request.getParameter("correo");
            String fecha_nac = request.getParameter("fechaNac");
            int tipoDocumento = Integer.parseInt(request.getParameter("TipoDoc"));
            String nDocumento = request.getParameter("nDoc");
            int estado_id = Integer.parseInt(request.getParameter("estado"));
            int privilegio = Integer.parseInt("privilegio");
            int tipoEmpleado = Integer.parseInt(request.getParameter("TipoEmp"));
            
            Empleado empleado = new Empleado(id, usuario, contrasena, nombres, apellidos,direccion,telefono,correo,fecha_nac,tipoDocumento,nDocumento,estado_id,privilegio,tipoEmpleado);
            
            com.google.gson.JsonObject gson = new JsonObject();
            String datos = "";
            JsonArray array = new JsonArray();

            array = empleado.MantenerEmpleado(empleado, accion);
            gson.add("datos", array);
            response.getWriter().write(gson.toString());
            System.out.println(gson.toString());
        }

        if (request.getParameter("action").equalsIgnoreCase("Nuevo")) {
            String accion = request.getParameter("action");
            int id = Integer.parseInt(request.getParameter("cod_emp"));
            String usuario = request.getParameter("usuario");
            String contrasena = request.getParameter("password");
            String nombres = request.getParameter("nombres");
            String apellidos = request.getParameter("apellidos");
            String direccion = request.getParameter("direccion");
            String telefono = request.getParameter("telefono");
            String correo = request.getParameter("correo");
            String fecha_nac = request.getParameter("fechaNac");
            int tipoDocumento = Integer.parseInt(request.getParameter("TipoDoc"));
            String nDocumento = request.getParameter("nDoc");
            int estado_id = Integer.parseInt(request.getParameter("estado"));
            int privilegio = Integer.parseInt("privilegio");
            int tipoEmpleado = Integer.parseInt(request.getParameter("TipoEmp"));
            
            Empleado empleado = new Empleado(id, usuario, contrasena, nombres, apellidos,direccion,telefono,correo,fecha_nac,tipoDocumento,nDocumento,estado_id,privilegio,tipoEmpleado);
            
            com.google.gson.JsonObject gson = new JsonObject();
            String datos = "";
            JsonArray array = new JsonArray();

            array = empleado.MantenerEmpleado(empleado, accion);
            gson.add("datos", array);
            response.getWriter().write(gson.toString());
            System.out.println(gson.toString());
        }

        if (request.getParameter("action").equalsIgnoreCase("Listar")) {
            Empleado empleado = new Empleado();
            com.google.gson.JsonObject gson = new JsonObject();
            String datos = "";
            JsonArray array = new JsonArray();
            array = empleado.ListarEmpleado(0, request.getParameter("action"));
            gson.add("datos", array);
            System.out.println(gson.toString());
            out.print(gson.toString());
            out.close();
        }

        if (request.getParameter("action").equalsIgnoreCase("Eliminar")) {
            response.setContentType("text/html;charset=UTF-8");
            String accion = request.getParameter("action");
            int id = Integer.parseInt(request.getParameter("cod_emp"));
            Empleado empleado = new Empleado();
            empleado.setId(id);
            empleado.MantenerEmpleado(empleado, accion);
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

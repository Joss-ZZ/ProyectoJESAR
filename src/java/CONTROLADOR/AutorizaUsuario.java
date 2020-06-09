/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADOR;

import MODELO.Conexion;
import MODELO.Empleado;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AutorizaUsuario extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String mensaje = "";
        String user = request.getParameter("user");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        Conexion conn = new Conexion();
        Empleado emp = new Empleado(conn);
        List<Empleado> empleados = new ArrayList<Empleado>();
        empleados = emp.AutorizaUsuario(user, password);
        RequestDispatcher rd;
        
        if(empleados!=null){
            session.setAttribute("usuario", empleados);
            response.sendRedirect("admin/admin-area.jsp");          
        }else{
            mensaje = "Usuario/Contraseña incorrectas";
            request.setAttribute("mensaje", mensaje);
            response.sendRedirect("admin/login.jsp");    
        }
    }
}

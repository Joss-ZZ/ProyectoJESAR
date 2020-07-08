package CONTROLADOR;

import MODELO.OrdenProduccion;

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
public class ControladorOrdenProduccion extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();

        if (request.getParameter("action").equalsIgnoreCase("Editar")) {
            String accion = request.getParameter("action");
            int id_orden_prod = Integer.parseInt(request.getParameter("ordenProduccion_id"));
            String fecha_inicio = request.getParameter("fecha_inicio");
            String fecha_acabado = request.getParameter("fecha_acabado");
            String estado = request.getParameter("estado");
            
            OrdenProduccion ordenProduccion = new OrdenProduccion(id_orden_prod, fecha_inicio, fecha_acabado, estado);
            
            com.google.gson.JsonObject gson = new JsonObject();
            String datos = "";
            JsonArray array = new JsonArray();

            array = ordenProduccion.MantenerOrdenProduccion(ordenProduccion, accion); 
            gson.add("datos", array);
            response.getWriter().write(gson.toString());
            System.out.println(gson.toString());
        }

        if (request.getParameter("action").equalsIgnoreCase("Nuevo")) {
            String accion = request.getParameter("action");
            int id_orden_prod = Integer.parseInt(request.getParameter("ordenProduccion_id"));
            String fecha_inicio = request.getParameter("fecha_inicio");
            String fecha_acabado = request.getParameter("fecha_acabado");
            String estado = request.getParameter("estado");
            
            OrdenProduccion ordenProduccion = new OrdenProduccion(id_orden_prod, fecha_inicio, fecha_acabado, estado);
            
            com.google.gson.JsonObject gson = new JsonObject();
            String datos = "";
            JsonArray array = new JsonArray();

            array = ordenProduccion.MantenerOrdenProduccion(ordenProduccion, accion);
            gson.add("datos", array);
            response.getWriter().write(gson.toString());
            System.out.println(gson.toString());
        }

        if (request.getParameter("action").equalsIgnoreCase("Listar")) {
            OrdenProduccion ordenProduccion = new OrdenProduccion();
            com.google.gson.JsonObject gson = new JsonObject();
            String datos = "";
            JsonArray array = new JsonArray();
            array = ordenProduccion.ListarOrdenProduccion(0, request.getParameter("action"));
            gson.add("datos", array);
            System.out.println(gson.toString());
            out.print(gson.toString());
            out.close();
        }

        if (request.getParameter("action").equalsIgnoreCase("Eliminar")) {
            response.setContentType("text/html;charset=UTF-8");
            String accion = request.getParameter("action");
            int id = Integer.parseInt(request.getParameter("ordenProduccion_id"));
            OrdenProduccion ordenProduccion = new OrdenProduccion();
            ordenProduccion.setId_orden_prod(id);
            ordenProduccion.MantenerOrdenProduccion(ordenProduccion, accion);
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
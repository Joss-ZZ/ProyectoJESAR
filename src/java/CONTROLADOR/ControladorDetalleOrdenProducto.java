package CONTROLADOR;

import MODELO.DetalleOrdenProducto;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControladorDetalleOrdenProducto extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();

        if (request.getParameter("action").equalsIgnoreCase("Editar")) {
            String accion = request.getParameter("action");
            int id_orden_prod = Integer.parseInt(request.getParameter("detalleProduccion_id"));
            String cod_modeloproducto = request.getParameter("cod_modeloproducto");
            int id_serie = Integer.parseInt(request.getParameter("id_serie"));
            int id_color = Integer.parseInt(request.getParameter("id_color"));
            double cantidad = Double.parseDouble(request.getParameter("cantidad"));

            DetalleOrdenProducto detalleOrdenProducto = new DetalleOrdenProducto(id_orden_prod, cod_modeloproducto, id_serie, id_color, cantidad);

            com.google.gson.JsonObject gson = new JsonObject();
            String datos = "";
            JsonArray array = new JsonArray();

            array = detalleOrdenProducto.MantenerDetalleOrdenProducto(detalleOrdenProducto, accion);
            gson.add("datos", array);
            response.getWriter().write(gson.toString());
            System.out.println(gson.toString());
        }

        if (request.getParameter("action").equalsIgnoreCase("Nuevo")) {
            String accion = request.getParameter("action");
            int id_orden_prod = Integer.parseInt(request.getParameter("detalleProduccion_id"));
            String cod_modeloproducto = request.getParameter("cod_modeloproducto");
            int id_serie = Integer.parseInt(request.getParameter("id_serie"));
            int id_color = Integer.parseInt(request.getParameter("id_color"));
            double cantidad = Double.parseDouble(request.getParameter("cantidad"));

            DetalleOrdenProducto detalleOrdenProducto = new DetalleOrdenProducto(id_orden_prod, cod_modeloproducto, id_serie, id_color, cantidad);

            com.google.gson.JsonObject gson = new JsonObject();
            String datos = "";
            JsonArray array = new JsonArray();

            array = detalleOrdenProducto.MantenerDetalleOrdenProducto(detalleOrdenProducto, accion);
            gson.add("datos", array);
            response.getWriter().write(gson.toString());
            System.out.println(gson.toString());
        }

        if (request.getParameter("action").equalsIgnoreCase("Listar")) {
            DetalleOrdenProducto detalleOrdenProducto = new DetalleOrdenProducto();
            com.google.gson.JsonObject gson = new JsonObject();
            String datos = "";
            JsonArray array = new JsonArray();
            array = detalleOrdenProducto.ListarDetalleOrdenProducto(Integer.parseInt(request.getParameter("detalleProduccion_id")), request.getParameter("action"));
            gson.add("datos", array);
            System.out.println(gson.toString());
            out.print(gson.toString());
            out.close();
        }

        if (request.getParameter("action").equalsIgnoreCase("Eliminar")) {
            response.setContentType("text/html;charset=UTF-8");
            String accion = request.getParameter("action");
            int id_orden_prod = Integer.parseInt(request.getParameter("detalleProduccion_id"));
            String cod_modeloproducto = request.getParameter("cod_modeloproducto");
            int id_serie = Integer.parseInt(request.getParameter("id_serie"));
            int id_color = Integer.parseInt(request.getParameter("id_color"));
            DetalleOrdenProducto detalleOrdenProducto = new DetalleOrdenProducto();
            detalleOrdenProducto.setId_orden_prod(id_orden_prod);
            detalleOrdenProducto.setCod_modeloproducto(cod_modeloproducto);
            detalleOrdenProducto.setId_serie(id_serie);
            detalleOrdenProducto.setId_color(id_color);
            detalleOrdenProducto.MantenerDetalleOrdenProducto(detalleOrdenProducto, accion);
        }

        if (request.getParameter("action").equals("AgregarProductoAlDetalle")) {
            String id = request.getParameter("producto_id");
            int id_serie = Integer.parseInt(request.getParameter("id_serie"));
            String serie = request.getParameter("serie");
            int id_color = Integer.parseInt(request.getParameter("id_color"));
            String color = request.getParameter("color");
            int cantprod = Integer.parseInt(request.getParameter("cant_prod"));

            
            
            JsonArray array = new JsonArray();
            JsonObject item = new JsonObject();
            item.addProperty("cod_modeloproducto", id);
            item.addProperty("id_serie", id_serie);
            item.addProperty("descripcion_serie", serie);
            item.addProperty("id_color", id_color);
            item.addProperty("descripcion_serie", color);
            item.addProperty("cantidad", cantprod);
            array.add(item);

            
            com.google.gson.JsonObject gson = new JsonObject();
            gson.add("datos", array);
            System.out.println(gson.toString());
            out.print(gson.toString());
            out.close();
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

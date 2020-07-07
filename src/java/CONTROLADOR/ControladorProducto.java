package CONTROLADOR;

import MODELO.Producto;

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
public class ControladorProducto extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();

        if (request.getParameter("action").equalsIgnoreCase("Editar")) {
            String accion = request.getParameter("action");
            String cod_modeloproducto = request.getParameter("cod_modeloproducto");
            int id_serie = Integer.parseInt(request.getParameter("id_serie"));
            int id_color = Integer.parseInt(request.getParameter("id_color"));
            double stock_producto = Double.parseDouble(request.getParameter("stock_producto"));
            double stock_cortado = Double.parseDouble(request.getParameter("stock_cortado"));
            double stock_aparado = Double.parseDouble(request.getParameter("stock_aparado"));
            double stock_armado = Double.parseDouble(request.getParameter("stock_armado"));
            
            Producto producto = new Producto(cod_modeloproducto, id_serie, id_color, stock_producto, stock_cortado,stock_aparado,stock_armado);
            
            com.google.gson.JsonObject gson = new JsonObject();
            String datos = "";
            JsonArray array = new JsonArray();

            array = producto.MantenerProducto(producto, accion); 
            gson.add("datos", array);
            response.getWriter().write(gson.toString());
            System.out.println(gson.toString());
        }

        if (request.getParameter("action").equalsIgnoreCase("Nuevo")) {
            String accion = request.getParameter("action");
            String cod_modeloproducto = request.getParameter("cod_modeloproducto");
            int id_serie = Integer.parseInt(request.getParameter("id_serie"));
            int id_color = Integer.parseInt(request.getParameter("id_color"));
            double stock_producto = Double.parseDouble(request.getParameter("stock_producto"));
            double stock_cortado = Double.parseDouble(request.getParameter("stock_cortado"));
            double stock_aparado = Double.parseDouble(request.getParameter("stock_aparado"));
            double stock_armado = Double.parseDouble(request.getParameter("stock_armado"));
            
            Producto producto = new Producto(cod_modeloproducto, id_serie, id_color, stock_producto, stock_cortado,stock_aparado,stock_armado);
            
            com.google.gson.JsonObject gson = new JsonObject();
            String datos = "";
            JsonArray array = new JsonArray();

            array = producto.MantenerProducto(producto, accion);
            gson.add("datos", array);
            response.getWriter().write(gson.toString());
            System.out.println(gson.toString());
        }

        if (request.getParameter("action").equalsIgnoreCase("Listar")) {
            Producto producto = new Producto();
            com.google.gson.JsonObject gson = new JsonObject();
            String datos = "";
            JsonArray array = new JsonArray();
            array = producto.ListarProductos(0, request.getParameter("action"));
            gson.add("datos", array);
            System.out.println(gson.toString());
            out.print(gson.toString());
            out.close();
        }

        if (request.getParameter("action").equalsIgnoreCase("Eliminar")) {
            response.setContentType("text/html;charset=UTF-8");
            String accion = request.getParameter("action");
            String id_p = request.getParameter("producto_id");
            int id_s = Integer.parseInt(request.getParameter("serie_id"));
            int id_c = Integer.parseInt(request.getParameter("color_id"));
            Producto producto = new Producto();
            producto.setCod_modeloproducto(id_p);
            producto.setId_color(id_c);
            producto.setId_serie(id_s);
            producto.MantenerProducto(producto, accion);
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


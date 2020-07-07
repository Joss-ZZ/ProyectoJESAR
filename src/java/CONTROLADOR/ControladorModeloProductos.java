package CONTROLADOR;

import MODELO.CategoriaProducto;
import MODELO.ModeloProducto;

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
public class ControladorModeloProductos extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();

        if (request.getParameter("action").equalsIgnoreCase("Editar")) {
            String accion = request.getParameter("action");
            String cod_modeloproducto = request.getParameter("cod_modeloproducto");
            int id_categoria = Integer.parseInt(request.getParameter("id_categoria"));
            String nombre = (request.getParameter("nombre"));
            double p_venta = Double.parseDouble(request.getParameter("p_venta"));
            double p_cortado = Double.parseDouble(request.getParameter("p_cortado"));
            double p_aparado = Double.parseDouble(request.getParameter("p_aparado"));
            double p_armado = Double.parseDouble(request.getParameter("p_armado"));
            double p_alistado = Double.parseDouble(request.getParameter("p_alistado"));
            
            ModeloProducto modeloProducto = new ModeloProducto(cod_modeloproducto, id_categoria, nombre, p_venta, p_cortado,p_aparado,p_armado,p_alistado);
            
            com.google.gson.JsonObject gson = new JsonObject();
            String datos = "";
            JsonArray array = new JsonArray();

            array = modeloProducto.MantenerModeloProducto(modeloProducto, accion); 
            gson.add("datos", array);
            response.getWriter().write(gson.toString());
            System.out.println(gson.toString());
        }

        if (request.getParameter("action").equalsIgnoreCase("Nuevo")) {
            String accion = request.getParameter("action");
            String cod_modeloproducto = request.getParameter("cod_modeloproducto");
            int id_categoria = Integer.parseInt(request.getParameter("id_categoria"));
            String nombre = (request.getParameter("nombre"));
            double p_venta = Double.parseDouble(request.getParameter("p_venta"));
            double p_cortado = Double.parseDouble(request.getParameter("p_cortado"));
            double p_aparado = Double.parseDouble(request.getParameter("p_aparado"));
            double p_armado = Double.parseDouble(request.getParameter("p_armado"));
            double p_alistado = Double.parseDouble(request.getParameter("p_alistado"));
            
            ModeloProducto modeloProducto = new ModeloProducto(cod_modeloproducto, id_categoria, nombre, p_venta, p_cortado,p_aparado,p_armado,p_alistado);
            
            com.google.gson.JsonObject gson = new JsonObject();
            String datos = "";
            JsonArray array = new JsonArray();

            array = modeloProducto.MantenerModeloProducto(modeloProducto, accion);
            gson.add("datos", array);
            response.getWriter().write(gson.toString());
            System.out.println(gson.toString());
        }

        if (request.getParameter("action").equalsIgnoreCase("Listar")) {
            ModeloProducto modeloProducto = new ModeloProducto();
            com.google.gson.JsonObject gson = new JsonObject();
            String datos = "";
            JsonArray array = new JsonArray();
            array = modeloProducto.ListarModeloProducto(0, request.getParameter("action"));
            gson.add("datos", array);
            System.out.println(gson.toString());
            out.print(gson.toString());
            out.close();
        }

        if (request.getParameter("action").equalsIgnoreCase("Eliminar")) {
            response.setContentType("text/html;charset=UTF-8");
            String accion = request.getParameter("action");
            String id = request.getParameter("modeloProducto_id");
            ModeloProducto modeloProducto = new ModeloProducto();
            modeloProducto.setCod_modeloproducto(id);
            modeloProducto.MantenerModeloProducto(modeloProducto, accion);
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

<%-- 
    Document   : ConsultarOrdProduccion
    Created on : 23-jul-2020, 2:01:43
    Author     : PCGAMING
--%>

<%@page import="java.util.LinkedList"%>
<%@page import="MODELO.DetalleOrdenProduccion"%>
<%@ include file="templates/header.jsp"%>
<%@ include file="templates/barra.jsp"%>
<%@ include file="templates/navegacion.jsp"%>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- /.card-header -->


      <% 
                 DetalleOrdenProduccion det=new DetalleOrdenProduccion();
                 LinkedList<DetalleOrdenProduccion> listasdetalle;
                 listasdetalle=det.ListarDetalleOrdenProduccion();
                 // pedido
                 String  recuperaridpedido= request.getParameter("enviar_orden_pedido");
                 // cliente recuperando
                 /*
                 String  recuperaridcliente= request.getParameter("enviarIdcliente");
                 String  recuperarnombrecliente= request.getParameter("enviar_nombrecli");
                 String  recuperarapellidocliente= request.getParameter("enviar_apellidocli");
                 String  recuperardocumentocliente= request.getParameter("enviar_documento");
                 */
              

      %>    
       

    <div class="card-body">
    </div>
    <!-- /.card-body --><section class="content">

        <!-- Default box -->
        <div class="card">
            <div class="card-header text-center bg-dark text-white">
                <h3 class="card-title">Consultar Orden Produccion Detallada</h3>
            </div>
    
           
                <input type="text" name="enviar_orden_pedido" value="<%=recuperaridpedido%>">
           
             
             
                                     
                        <!-- /.card-header -->
                         <div class="card-body">
                             <table id="example1" class="table table-bordered table-striped">
                                 <thead>
                                     <tr>
                                         <th class="text-center" >ID-ORDEN PROD</th>
                                         <th class="text-center" >PRODUCTO</th>
                                         <th class="text-center" >DESCRIPCION</th>
                                         <th class="text-center" >CATEGORIA</th>
                                         <th class="text-center" >SERIE</th>
                                         <th class="text-center" >COLOR</th>
                                         <th class="text-center" >CANTIDAD-PRODUCIR</th>
                                         <th class="text-center" >ACCIONES</th>
                                     </tr>
                                 </thead>

                                  <tbody>
                                    <%for (int i = 0; i < listasdetalle.size(); i++) { %>  
                                     <tr>    
                                         <td class="text-center"><%=listasdetalle.get(i).getId_orden_prod()%></td>
                                         <td class="text-center"><%=listasdetalle.get(i).getCod_modelo_producto()%></td>     
                                         <td class="text-center"><%=listasdetalle.get(i).getNombre_producto()%></td>   
                                         <td class="text-center"><%=listasdetalle.get(i).getCategoria_modeloproducto()%></td>     
                                         <td class="text-center" style="display:none;" ><%=listasdetalle.get(i).getId_serie()%></td>     
                                         <td class="text-center"><%=listasdetalle.get(i).getSerie_descripcion()%></td>   
                                         <td class="text-center" style="display:none;" ><%=listasdetalle.get(i).getId_color()%></td>     
                                         <td class="text-center"><%=listasdetalle.get(i).getColor_descripcion()%></td>     
                                         <td class="text-center"><%=listasdetalle.get(i).getCantidad_produccion()%></td>   
                                         
                                         <td class="text-center">                         
                      
                                             <form action="RegistrarPedido.jsp" method="POST">
                                                 
                                                      <input type="hidden"  name="enviar_orden_pedido" value="<%=recuperaridpedido%>">
                                                      <input type="hidden"  name="enviarOrdproduccion" value="<%=listasdetalle.get(i).getId_orden_prod()%>">
                                                      <input type="hidden"  name="enviarIdproducto" value="<%=listasdetalle.get(i).getCod_modelo_producto()%>">
                                                      <input type="hidden"  name="enviarDescpricionproducto" value="<%=listasdetalle.get(i).getNombre_producto()%>">
                                                      <input type="hidden"  name="enviarCategoria" value="<%=listasdetalle.get(i).getCategoria_modeloproducto()%>">
                                                      <input type="hidden"  name="enviarIdserie" value="<%=listasdetalle.get(i).getId_serie()%>">
                                                      <input type="hidden"  name="enviarSeriedescripcion" value="<%=listasdetalle.get(i).getSerie_descripcion()%>">
                                                      <input type="hidden"  name="enviarIdcolor" value="<%=listasdetalle.get(i).getId_color()%>">
                                                      <input type="hidden"  name="enviarColordescripcion" value="<%=listasdetalle.get(i).getColor_descripcion()%>">
                                                      <input type="hidden"  name="enviarCantidadproducir" value="<%=listasdetalle.get(i).getCantidad_produccion()%>">
            
                                                      <input class="btn btn-warning " type="submit" value="agregar Orden Produccion">   

                                                 
                                             </form>                          
                                         </td> 
                
                                     </tr> 
                                     <%}%>
                                 </tbody>
                             </table>
        
                         </div>
                         <!-- /.card-body -->               
        </div>
        <!-- /.card -->
                  <div>
                         <form action="RegistrarPedido.jsp" method="GET">                                                 
                                       <input type="hidden"  name="enviar_orden_pedido" value="<%=recuperaridpedido%>">         
                                       <input class="btn btn-info " type="submit" value="Salir">                                           
                         </form>      
                  </div>
</div>
        

<%@ include file="templates/footer.jsp"%>
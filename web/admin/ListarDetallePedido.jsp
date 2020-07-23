<%-- 
    Document   : DetallePedido
    Created on : 23-jul-2020, 12:11:05
    Author     : PCGAMING
--%>

<%@page import="java.util.LinkedList"%>
<%@page import="MODELO.Conexion"%>
<%@page import="MODELO.DetalleOrdenProduccion"%>
<%@ include file="templates/header.jsp"%>
<%@ include file="templates/barra.jsp"%>
<%@ include file="templates/navegacion.jsp"%>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- /.card-header -->
      <style>
          @media print {
           .oculto-impresion,
           .oculto-impresion * {
             display: none !important;
           }
           
         }
       </style> 
    <div class="card-body">
    </div>
    <!-- /.card-body --><section class="content">

        <!-- Default box -->
        <div class="card">
           <%
               
            // DE CONSULTAR INSUMO SE RECUPERA
    
             //===============================================================================
             String recuperarorden = request.getParameter("enviarid_pedido");// del jsp orden comprainsumo
             int id_ordenn =Integer.parseInt(recuperarorden);
  
             
           %>
            <div class="card-header text-center bg-blue text-white">
                <h3 class="card-title">Detalle de Pedido Cliente N° <%=recuperarorden%></h3>
            </div>
            <!-- /.card-header -->
            <br>
             
                <div class="card-body">
                             <table id="example1" class="table table-bordered table-striped">
                                 <thead>
                                     <tr>
                                         <th class="text-center" >ID-PEDIDO</th>
                                         <th class="text-center" >ORDEN-PROD</th>
                                         <th class="text-center" >MODELO-PRODUCTO</th>
                                         <th class="text-center" >DESCRIPCION</th>
                                         <th class="text-center" >SERIE</th>
                                         <th class="text-center" >COLOR</th>
                                         <th class="text-center" >CANTIDAD</th>
                                     </tr> 
                                 </thead>
                                 <%
                                    Conexion conn = new Conexion();
                                    DetalleOrdenProduccion det = new DetalleOrdenProduccion(conn);
                                    LinkedList<DetalleOrdenProduccion> lista = det.ListarDetallePedidoconProduccion(id_ordenn);
                                    for (DetalleOrdenProduccion dt: lista){%>

                                 <tbody>
                                    <tr>
                                            <td class="text-center"><%=dt.getId_pedido()%></td>                 
                                            <td class="text-center"><%=dt.getId_orden_prod()%></td>
                                            <td class="text-center"><%=dt.getCod_modelo_producto()%></td>
                                            <td class="text-center"><%=dt.getNombre_producto()%></td>
                                            <td class="text-center"><%=dt.getSerie_descripcion()%></td>
                                            <td class="text-center"><%=dt.getColor_descripcion()%></td>
                                            <td class="text-center"><%=dt.getCantidad_produccion()%></td>
                                            
                                            
                                        </tr>
                                    <%}%>           

                                     
                                 </tbody>
                             </table>
                             <button id="btnImprimir" class="mt-2 btn btn-success">Imprimir</button>
                      
                             
                              <script src="/Admin-JESAR/admin/js/imprimir.js"></script>
                                  <form action="ListarPedidos.jsp" method="POST" class="row" style="padding-top: 6%;">
                                           <input class="btn btn-dark " type="submit" value="Salir" >   
   
                                  </form>    
                         </div>
     
            <!-- /.card-body -->
        </div>
        <!-- /.card -->     

        <%@ include file="templates/footer.jsp"%>

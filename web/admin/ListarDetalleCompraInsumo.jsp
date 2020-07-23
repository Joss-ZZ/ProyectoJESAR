<%-- 
    Document   : ListarDetalleCompraInsumo
    Created on : 20-jul-2020, 18:44:51
    Author     : PCGAMING
--%>

<%@page import="java.util.LinkedList"%>
<%@page import="MODELO.DetalleCompraInsumo"%>
<%@page import="MODELO.Conexion"%>
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
             String recuperarorden = request.getParameter("enviarorden");// del jsp orden comprainsumo
             int id_ordenn =Integer.parseInt(recuperarorden);
  
             
           %>
            <div class="card-header text-center bg-blue text-white">
                <h3 class="card-title">Detalle de Compra de Insumo  N° <%=recuperarorden%></h3>
            </div>
            <!-- /.card-header -->
            <br>
           
                <div class="card-body">
                             <table id="example1" class="table table-bordered table-striped">
                                 <thead>
                                     <tr>
                                         <th class="text-center" >ID</th>
                                         <th class="text-center" >INSUMO</th>
                                         <th class="text-center" >CATEGORIA</th>
                                         <th class="text-center" >CANTIDAD</th>
                                         <th class="text-center" >UNIDAD</th>  
                                     </tr>
                                 </thead>
                                 <%
                                    Conexion conn = new Conexion();
                                    DetalleCompraInsumo det = new DetalleCompraInsumo(conn);
                                    LinkedList<DetalleCompraInsumo> lista = det.ListarCompraInsumo(id_ordenn);
                                    for (DetalleCompraInsumo dt: lista){%>

                                 <tbody>
                                    <tr>
                                            <td class="text-center"><%=dt.getId_orden_compra() %></td>                 
                                            <td class="text-center"><%=dt.getDescrip_insumo()%></td>
                                            <td class="text-center"><%=dt.getCateg_insumo()%></td>
                                            <td class="text-center"><%=dt.getCantidad()%></td>
                                            <td class="text-center"><%=dt.getUnidad()%></td>
                                        </tr>
                                    <%}%>           

                                     
                                 </tbody>
                             </table>
                             <button id="btnImprimir" class="mt-2 btn btn-success">Imprimir</button>
                             <div class="row" style="padding-top: 6%;">
 <!--volver a la pagina detlle insumo-->   <a href="http://localhost:8080/Admin-JESAR/admin/OrdenCompraInsumo.jsp" class="form-control btn btn-dark" style="width: auto; margin-left: auto" >Salir</a>
                              </div>
                              <script src="/Admin-JESAR/admin/js/imprimir.js"></script>
                         </div>
     
            <!-- /.card-body -->
        </div>
        <!-- /.card -->     

        <%@ include file="templates/footer.jsp"%>
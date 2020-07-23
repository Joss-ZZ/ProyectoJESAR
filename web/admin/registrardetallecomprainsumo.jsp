<%-- 
    Document   : registrarpedidocomprainsumo
    Created on : 17-jul-2020, 17:48:05
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

    <div class="card-body">
    </div>
    <!-- /.card-body --><section class="content">

        <!-- Default box -->
        <div class="card">
           <%
               
            // DE CONSULTAR INSUMO SE RECUPERA
            
             String id_ordencompra = request.getParameter("enviarIdOrdenCompra");
             String id_insumo = request.getParameter("enviarIdinsumo");
               
             String recuperar_insumo = request.getParameter("enviarInsumo");
             String recuperar_categoria = request.getParameter("enviarCategoria");
             String recuperar_unidad = request.getParameter("enviarUnidad");
             
             //===============================================================================
             String recuperarorden = request.getParameter("enviarorden");// del jsp orden comprainsumo
             int id_ordenn =Integer.parseInt(recuperarorden);
             
             if(recuperar_insumo==null && recuperar_categoria==null && recuperar_unidad==null){
                 recuperar_insumo=" ";
                 recuperar_categoria=" ";
                 recuperar_unidad=" ";
             }
      
             
           %>
            <div class="card-header">
                <h3 class="card-title">Lista de Pedidos de Insumo</h3>
            </div>
            <!-- /.card-header -->
            <br>
                <div class="container">
              <!--   <form name="form_"action="ControladorOrdenCompraInsumo?accion=Nuevo" method="post"> --> 
                                      
                                    <h5> Registro Detalle Compra Insumo</h5>
                                    <br>
                                    <div class="row">
                                        <div class="form-group col-2" >
                                            <label>Orden Compra Insumo</label><br>
                                            <input type="text" class="form-control"  name="id" value="<%=recuperarorden %>" readonly="readonly"><br>

                                        </div>

                                        <div class="form-group col-3" >
                                            <label>Insumo</label><br>
                                            <input type="text" class="form-control"  name="insumo" value="<%=recuperar_insumo %>" readonly="readonly"><br>                     
                                        </div>

                                        <div class="form-group col-3" >
                                            <label>Categoria</label><br>
                                            <input type="text" class="form-control"  name="insumo" value="<%=recuperar_categoria %> "readonly="readonly"><br>                     
                                        </div>

                                        <div class="form-group col-2" >
                                            <label>Unidad</label><br>
                                            <input type="text" class="form-control"  name="insumo" value="<%=recuperar_unidad %>" readonly="readonly"><br>                     
                                        </div>
                                       <!-- envia al jsp consultar insumo-->     
                                        <div class="col-lg-2">                            
                                              <form action="ConsultarInsumo.jsp" method="POST">
                                                  <input type="hidden" id="enviaridorden" name="enviarorden" value="<%=recuperarorden %>"><br>
                                                    <input class="btn btn-success" type="submit" value="Consultar Insumo">   
                                              </form>  
                                        </div>
                                                 
                                   <form name="" action="/Admin-JESAR/ControladorDetalleCompraInsumo?accion=Nuevo" method="post">
                     
     
                                         <input type="hidden" class="form-control"  name="id_ordc" value="<%=recuperarorden %>">
                                         <input type="hidden" class="form-control"  name="id_ins" value="<%=id_insumo %>">
                    
                                        <div class="form-group col-6" >
                                            <label>Cantidad</label><br>
                                            <input type="text" class="form-control"  name="cantidad"><br>

                                        </div>
                                    
                                    </div>
                                        <div>
                                             <div class="col-lg-12">       
                                               <input class="btn btn-primary" type="submit" value="Registrar Orden de Compra Insumo">
                                             </div>        
                                        </div>
                                            
                                    </form>                
                                        

                              <br>
                  <!--   </form>  --> 
           
            </div>
                                                    
         

            <br>
           
                <div class="card-body">
                             <table id="example1" class="table table-bordered table-striped">
                                 <thead>
                                     <tr>
                                         <th class="text-center" >ID</th>
                                         <th class="text-center" >INSUMO</th>
                                         <th class="text-center" >CATEGORIA</th>
                                         <th class="text-center" >CANT-COMP</th>
                                         <th class="text-center" >STOCK</th>
                                         <th class="text-center" >TOTAL</th>
                                         <th class="text-center" >UNIDAD</th> 
                                         <th class="text-center" >Acciones</th>
                                     </tr>
                                 </thead>
                                 <%
                                    Conexion conn = new Conexion();
                                    DetalleCompraInsumo det = new DetalleCompraInsumo(conn);
                                    LinkedList<DetalleCompraInsumo> lista = det.ListarCompraInsumo(id_ordenn);
                                    for (DetalleCompraInsumo dt: lista){%>

                                 <tbody>
                                    <tr>
                                            <td><%=dt.getId_orden_compra() %></td>       
                                            <td style="display:none;"><%=dt.getId_insumo()%></td>  
                                            <td class="text-center"><%=dt.getDescrip_insumo()%></td>
                                            <td class="text-center"><%=dt.getCateg_insumo()%></td>
                                            <td class="text-center"><%=dt.getCantidad()%></td>
                                            <td class="text-center"><%=dt.getStock()%></td>
                                            <td class="text-center"><%=dt.getCantidad()+ dt.getStock()%></td>
                                            <td class="text-center"><%=dt.getUnidad()%></td>
                                            <td class="text-center">
                                              <a class=" btn btn-warning" href="/Admin-JESAR/ControladorDetalleCompraInsumo?accion=Buscar&Ord=<%=dt.getId_orden_compra()%>&Ins=<%=dt.getId_insumo()%>&NomIns=<%=dt.getDescrip_insumo()%>&Cant=<%=dt.getCantidad()%>&Categ=<%=dt.getCateg_insumo()%>&Unid=<%=dt.getUnidad()%> ">Editar</a>  
                                              <a class=" btn btn-danger" href="/Admin-JESAR/ControladorDetalleCompraInsumo?accion=Eliminar&Ord=<%=dt.getId_orden_compra()%>&Ins=<%=dt.getId_insumo()%>">Eliminar</a>
                                            </td>
                                        </tr>
                                    <%}%>           

                                     
                                 </tbody>
                             </table>
                                    <div class="row" style="padding-top: 6%;">
                <!--volver a la pagina detlle insumo-->   <a href="http://localhost:8080/Admin-JESAR/admin/OrdenCompraInsumo.jsp" class="form-control btn btn-dark" style="width: auto; margin-left: auto" >Salir</a>
                                   </div>
                         </div>
     
            <!-- /.card-body -->
        </div>
        <!-- /.card -->     

        <%@ include file="templates/footer.jsp"%>
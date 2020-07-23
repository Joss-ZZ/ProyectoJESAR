<%-- 
    Document   : ListarPedidos
    Created on : 22-jul-2020, 22:42:38
    Author     : PCGAMING
--%>

<%@page import="java.util.LinkedList"%>
<%@page import="MODELO.Pedido"%>
<%@ include file="templates/header.jsp"%>
<%@ include file="templates/barra.jsp"%>
<%@ include file="templates/navegacion.jsp"%>
<!-- Content Wrapper. Contains page content -->
   <link href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.8.0/sweetalert2.min.css" rel="stylesheet" />
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
   <script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.8.0/sweetalert2.min.js"></script>
<div class="content-wrapper">
    <!-- /.card-header -->

    <div class="card-body">
    </div>
    <!-- /.card-body -->
    <section class="content">

<% 
                 Pedido listapedido =new  Pedido();
                 LinkedList<Pedido> listapedi; 
                 listapedi=listapedido.listapedido();
                

%>

      <div class="card-header text-center bg-cyan text-white">
                <h3 class="card-title ">Lista de Pedidos-Cliente</h3>
            </div>
            <!-- /.card-header -->
            <br>
 
            <div class="container">
    
                    <form action="/Admin-JESAR/admin/RegistrarPedido.jsp" method="POST">
                          <input class="btn btn-primary" id="regpedido" type="submit" value="Registrar Pedido">
                    </form>    
                <br>

            </div>
        
        <!-- Default box -->
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <table id="" class="table table-bordered display nowrap" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <th>ID-PED</th>
                                    <th>ORD-PROD</th>
                                    <th style="display:none;">ID-CLIENTE</th>
                                    <th>CLIENTE</th>
                                    <th>DOCUMENTO</th>
                                    <th>EMPLEADO</th>
                                    <th>FECHA-EMISION</th>
                                    <th>FECHA-ENTREGA</th>
                                    <th>ESTADO</th>
                                    <th class="text-center">Acciones</th>
                                </tr>
                            </thead>
                            <tbody>      
                                  <%for (int i = 0; i < listapedi.size(); i++) { %>  
                                  <tr>
                                      <td><%=listapedi.get(i).getId_pedido_cliente()%></td>    
                                      <td><%=listapedi.get(i).getId_orden_prod()%></td>    
                                      <td style="display:none;"><%=listapedi.get(i).getId_cliente()%></td>    
                                      <td><%=listapedi.get(i).getCliente_nombre()%> , <%=listapedi.get(i).getCliente_apellido()%></td>    
                                      <td><%=listapedi.get(i).getDocumento()%></td> 
                                      <td style="display:none;"><%=listapedi.get(i).getId_empleado()%></td> 
                                      <td><%=listapedi.get(i).getNombre_empleado()%></td>  
                                      <td><%=listapedi.get(i).getFecha_emision()%></td>  
                                      <td><%=listapedi.get(i).getFecha_entrega()%></td>  
                                      <td><%=listapedi.get(i).getEstado()%></td>  
                                      <td>
                                          <%
                                           int cod_pedido = listapedi.get(i).getId_pedido_cliente();
                                          %>
                                          <form name="lista" action="/Admin-JESAR/admin/ListarDetallePedido.jsp" method="POST">
                                              <input type="hidden" id="enviarid_pedido" name="enviarid_pedido" value="<%=cod_pedido%>" >
                                                       <input id="GenerarLista" class="btn btn-warning" type="submit" value="Detalle Orden-Produccion" >   
                                          </form>                   

                                      </td>
                                  </tr>
                                  <%}%>
                            </tbody>
                            
                        </table> 
             

                    </div>
                </div>
            </div>
              

            <!-- /.card-body -->
        </div>
        <!-- /.card -->     

<%@ include file="templates/footer.jsp"%>
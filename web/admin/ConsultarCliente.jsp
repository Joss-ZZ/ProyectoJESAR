<%-- 
    Document   : ConsultarCliente
    Created on : 23-jul-2020, 9:51:54
    Author     : PCGAMING
--%>

<%@page import="java.util.LinkedList"%>
<%@page import="MODELO.Cliente"%>
<%@ include file="templates/header.jsp"%>
<%@ include file="templates/barra.jsp"%>
<%@ include file="templates/navegacion.jsp"%>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- /.card-header -->
  

      <% 
                 Cliente det=new Cliente();
                 LinkedList<Cliente> listascliente;
                 listascliente=det.consultarClientes();
                 
                 String  recuperaridpedido= request.getParameter("enviar_pedido");
                 
                  // produccion
             String id_ordenproduccion = request.getParameter("IDORDENPRODUCCION");
             String producto = request.getParameter("IDPRODUCTO");
             String descrip_producto = request.getParameter("IDDESCPRODUCTO");
             String categ_producto = request.getParameter("IDCATEGORIA");
             String id_serie = request.getParameter("IDSERIE");  
             String serie_descripcion = request.getParameter("SERIEDESC");  
             String id_color = request.getParameter("IDCOLOR");   
             String color_descripcion = request.getParameter("COLORDESC"); 
             String cantidad = request.getParameter("CANTIDAD"); 

      %>    
       

    <div class="card-body">
    </div>
    <!-- /.card-body --><section class="content">

        <!-- Default box -->
        <div class="card">
            <div class="card-header text-center bg-dark text-white">
                <h3 class="card-title">Consultar Clientes</h3>
            </div>
             <input type="text" name="enviar_orden_pedido" value="<%=recuperaridpedido%>">
           
             
                                     
                        <!-- /.card-header -->
                         <div class="card-body">
                             <table id="example1" class="table table-bordered table-striped">
                                 <thead>
                                     <tr>
                                         <th class="text-center" >ID</th>
                                         <th class="text-center" >NOMBRES</th>
                                         <th class="text-center" >APELLIDOS</th>
                                         <th class="text-center" >NRO DOCUMENTO</th>
                                         <th class="text-center" >Acciones</th>
                                     </tr>
                                 </thead>

                                  <tbody>
                                    <%for (int i = 0; i < listascliente.size(); i++) { %>  
                                     <tr>    
                                         <td class="text-center"><%=listascliente.get(i).getId()%></td>
                                         <td class="text-center"><%=listascliente.get(i).getNombre()%></td>     
                                         <td class="text-center"><%=listascliente.get(i).getApellidos()%></td>   
                                         <td class="text-center"><%=listascliente.get(i).getDocumento()%></td>     
                                      

                                         
                                         <td class="text-center">                         
                                            <form action="RegistrarPedido.jsp" method="GET">
                                                      <input type="hidden"  name="enviar_orden_pedido" value="<%=recuperaridpedido%>">
                                                      <input type="hidden"  name="enviarIdcliente" value="<%=listascliente.get(i).getId()%>" >
                                                      <input type="hidden"  name="enviarNombrecliente" value="<%=listascliente.get(i).getNombre()%>" >
                                                      <input type="hidden"  name="enviarApellidocliente" value="<%=listascliente.get(i).getApellidos()%>" >
                                                      <input type="hidden"  name="enviarDocumento" value="<%=listascliente.get(i).getDocumento()%>" >
                                                      <!--produccion-->
                                                      <input type="hidden"  name="enviarOrdproduccion" value="<%=id_ordenproduccion %>">
                                                      <input type="hidden"  name="enviarIdproducto" value="<%=producto %>">
                                                      <input type="hidden"  name="enviarDescpricionproducto" value="<%=descrip_producto %>">
                                                      <input type="hidden"  name="enviarCategoria" value="<%=categ_producto%>">
                                                      <input type="hidden"  name="enviarIdserie" value="<%=id_serie %>">
                                                      <input type="hidden"  name="enviarSeriedescripcion" value="<%=serie_descripcion%>">
                                                      <input type="hidden"  name="enviarIdcolor" value="<%=id_color%>">
                                                      <input type="hidden"  name="enviarColordescripcion" value="<%=color_descripcion %>">
                                                      <input type="hidden"  name="enviarCantidadproducir" value="<%=cantidad%>">
                                                       <input id="" class="btn btn-warning" type="submit" value="Agregar Cliente" >   
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
                                 <form action="registrardetallecomprainsumo.jsp" method="POST">                                                 
                                         
                                       <input class="btn btn-info " type="submit" value="Salir">                                           
                                  </form>  
                  </div>
</div>
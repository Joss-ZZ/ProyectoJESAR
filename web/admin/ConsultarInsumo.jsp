<%-- 
    Document   : ConsultaInsumo
    Created on : 19-jul-2020, 16:26:57
    Author     : PCGAMING
--%>

<%@page import="java.util.LinkedList"%>
<%@page import="MODELO.DetalleCompraInsumo"%>
<%@ include file="templates/header.jsp"%>
<%@ include file="templates/barra.jsp"%>
<%@ include file="templates/navegacion.jsp"%>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- /.card-header -->
 

      <% 
                 DetalleCompraInsumo det=new DetalleCompraInsumo();
                 LinkedList<DetalleCompraInsumo> listasdetalleinsumo;
                 listasdetalleinsumo=det.detalleinsumo();
                 
                 String  recuperaridordcomp= request.getParameter("enviarorden");

      %>    
       

    <div class="card-body">
    </div>
    <!-- /.card-body --><section class="content">

        <!-- Default box -->
        <div class="card">
            <div class="card-header text-center bg-dark text-white">
                <h3 class="card-title">Consultar Insumos</h3>
            </div>
           <input type="hidden" name="Ordcomp" value="<%=recuperaridordcomp %>">
           
             
                                     
                        <!-- /.card-header -->
                         <div class="card-body">
                             <table id="example1" class="table table-bordered table-striped">
                                 <thead>
                                     <tr>
                                         <th class="text-center" >ID</th>
                                         <th class="text-center" >INSUMO</th>
                                         <th class="text-center" >CATEGORIA</th>
                                         <th class="text-center" >STOCK</th>
                                         <th class="text-center" >UNIDAD</th>
                                         <th class="text-center" >Acciones</th>
                                     </tr>
                                 </thead>

                                  <tbody>
                                    <%for (int i = 0; i < listasdetalleinsumo.size(); i++) { %>  
                                     <tr>    
                                         <td class="text-center"><%=listasdetalleinsumo.get(i).getId_insumo()%></td>
                                         <td class="text-center"><%=listasdetalleinsumo.get(i).getDescrip_insumo()%></td>     
                                         <td class="text-center"><%=listasdetalleinsumo.get(i).getCateg_insumo()%></td>   
                                         <td class="text-center"><%=listasdetalleinsumo.get(i).getCantidad()%></td>     
                                         <td class="text-center"><%=listasdetalleinsumo.get(i).getUnidad()%></td>     

                                         
                                         <td class="text-center">                         
                      
                                             <form action="registrardetallecomprainsumo.jsp" method="POST">
                                                 
                                                        <input type="hidden" id="enviarid" name="enviarorden" value="<%=recuperaridordcomp %>">
                                                        <input type="hidden" id="enviarid" name="enviarIdinsumo" value="<%=listasdetalleinsumo.get(i).getId_insumo()%>">
                                                        <input type="hidden" id="enviarid" name="enviarInsumo" value="<%=listasdetalleinsumo.get(i).getDescrip_insumo()%>">
                                                        <input type="hidden" id="enviarid" name="enviarCategoria" value="<%=listasdetalleinsumo.get(i).getCateg_insumo()%>">
                                                        <input type="hidden" id="enviarid" name="enviarUnidad" value="<%=listasdetalleinsumo.get(i).getUnidad()%>">
                                                        
                                                        <input class="btn btn-warning " type="submit" value="agregar Insumo">   

                                                 
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
                                       <input type="hidden" id="enviarid" name="enviarorden" value="<%=recuperaridordcomp %>">         
                                       <input class="btn btn-info " type="submit" value="Salir">                                           
                                  </form>  
                  </div>
</div>
        

<%@ include file="templates/footer.jsp"%>
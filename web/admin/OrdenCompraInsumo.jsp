<%-- 
    Document   : consultarOrdenCompraInsumo
    Created on : 17-jul-2020, 17:53:07
    Author     : PCGAMING
--%>


<%@page import="java.util.LinkedList"%>
<%@page import="MODELO.OrdenCompraInsumo"%>
<%@ include file="templates/header.jsp"%>
<%@ include file="templates/barra.jsp"%>
<%@ include file="templates/navegacion.jsp"%>
<!-- Content Wrapper. Contains page content -->
   <link href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.8.0/sweetalert2.min.css" rel="stylesheet" />
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
   <script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.8.0/sweetalert2.min.js"></script>
<div class="content-wrapper">
    <!-- /.card-header -->
    
<% 
                 OrdenCompraInsumo mar=new  OrdenCompraInsumo();
                 LinkedList< OrdenCompraInsumo> listascompinsumo;
                 listascompinsumo=mar.ordencomprainsumolista();

%>

        <script>
            window.onload=function(){
                var fecha=new Date();
                var mes =fecha.getMonth()+1;
                var dia=fecha.getDate();
                var ano=fecha.getFullYear();
                var hora=fecha.getHours();
                var min=fecha.getMinutes();
                if(dia<10)dia='0'+dia;
                if(mes<10)mes='0'+mes;
                if(hora<10)hora='0'+hora;
                if(min<10)min='0'+min;
                document.getElementById('fechaactual').value=ano+"-"+mes+"-"+dia;
                document.getElementById('horaactual').value=hora+":"+min;
            };
            
        </script>

    <div class="card-body">
    </div>
    <!-- /.card-body --><section class="content">

        <!-- Default box -->
        <div class="card">

            <div class="card-header">
                <h3 class="card-title">Lista de Pedidos de Compra de Insumo</h3>
            </div>
            <!-- /.card-header -->
            <br>
 
            <div class="container">
                <form name="form_"action="/Admin-JESAR/ControladorOrdenCompraInsumo?accion=Nuevo" method="post">
                       
                                 
                                    <div class="form-group col-4" >
                                        <input type="hidden" id="fechaactual" class="form-control" name="fechaactual" > 
                                        <input type="hidden" id="estado" class="form-control" name="estado" value="proceso" >    
                                        <input type="hidden" id="horaactual" class="form-control" name="horaactual">    
                                    </div>

                       <div class="col-lg-12">       
                           <input class="btn btn-primary" id="generar" type="submit" value="Generar">
                       </div>     
                      
                </form>       
            </div>

            <br>
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <table id="" class="table table-bordered display nowrap" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Fecha de Inicio</th>
                                    <th>Hora</th>
                                    <th>Estado</th>
                                    <th class="text-center">Acciones</th>
                                </tr>
                            </thead>
                            <tbody>      
                                  <%for (int i = 0; i < listascompinsumo.size(); i++) { %>  
                                  <tr>
                                      <td><%=listascompinsumo.get(i).getId_orden_compinsumo()%></td>    
                                      <td><%=listascompinsumo.get(i).getFecha_inicio()%></td>    
                                      <td><%=listascompinsumo.get(i).getHora()%></td>    
                                    
                                      <td><a   id="estadoo" class=" btn btn-info input" href="/Admin-JESAR/ControladorOrdenCompraInsumo?accion=Estado&Idorden=<%=listascompinsumo.get(i).getId_orden_compinsumo()%>&Est=<%=listascompinsumo.get(i).getId_orden_compinsumo()%>" onkeyup="success()" ><%=listascompinsumo.get(i).getEstado()%></a></td>  
                                      
                                      <td class="text-center">
                                              <!-- enviar el id orden prodcuccion a registrardetallecomprainsumo .jsp -->
                                         <form action="/Admin-JESAR/admin/ListarDetalleCompraInsumo.jsp" method="POST">
                                                       <input type="hidden" id="enviarorden" name="enviarorden" value="<%=listascompinsumo.get(i).getId_orden_compinsumo()%>">
                                                       <input class="btn btn-danger" type="submit" value="DetalleOrden">   
                                         </form>    
                                         <br>
                                          <!-- enviar el id orden prodcuccion a registrardetallecomprainsumo .jsp -->
                                         <form name="lista" action="/Admin-JESAR/admin/registrardetallecomprainsumo.jsp" method="POST">
                                                       <input type="hidden" id="enviarorden" name="enviarorden" value="<%=listascompinsumo.get(i).getId_orden_compinsumo()%>">
                                                       <input id="GenerarLista" class="btn btn-warning" type="submit" value="GenerarLista" >   
                                          </form>                   

                                        <script>
                                    
                                          function success() {
                                             if(document.getElementById("estadoo").value==="finalizado") { 
                                                document.getElementById('GenerarLista').disabled = true; 
                                            } else { 
                                                document.getElementById('GenerarLista').disabled = false;
                                            }
                                           }

                                    
                                        </script>

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
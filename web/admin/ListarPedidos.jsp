<%-- 
    Document   : ListarPedidos
    Created on : 22-jul-2020, 22:42:38
    Author     : PCGAMING
--%>

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
    <!-- /.card-body --><section class="content">

        <!-- Default box -->
        <div class="card">

            <div class="card-header">
                <h3 class="card-title">Lista de Pedidos </h3>
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
                                  
                                  <tr>
                                  
                                    
                                      <td><a   id="estadoo" class=" btn btn-info input"</a></td>  
                                      
                                      <td class="text-center">
                                              <!-- enviar el id orden prodcuccion a registrardetallecomprainsumo .jsp -->
                                         <form action="/Admin-JESAR/admin/ListarDetalleCompraInsumo.jsp" method="POST">
                                                       <input type="hidden" id="enviarorden" name="enviarorden" >
                                                       <input class="btn btn-danger" type="submit" value="DetalleOrden">   
                                         </form>    
                                         <br>
                                          <!-- enviar el id orden prodcuccion a registrardetallecomprainsumo .jsp -->
                                         <form name="lista" action="/Admin-JESAR/admin/registrardetallecomprainsumo.jsp" method="POST">
                                                       <input type="hidden" id="enviarorden" name="enviarorden" >
                                                       <input id="GenerarLista" class="btn btn-warning" type="submit" value="GenerarLista" >   
                                          </form>                   

                           
                                      </td>
                                       
                                  </tr>
                             
                            </tbody>
                        </table> 
             

                    </div>
                </div>
            </div>
              

            <!-- /.card-body -->
        </div>
        <!-- /.card -->     

<%@ include file="templates/footer.jsp"%>
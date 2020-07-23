<%-- 
    Document   : editardetallecomprainsumo
    Created on : 22-jul-2020, 12:36:48
    Author     : PCGAMING
--%>

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
  
            <div class="card-header">
                <h3 class="card-title">Editar detalle compra insumo</h3>
            </div>
            <!-- /.card-header -->
            <br>
                <div class="container">
              <!--   <form name="form_"action="ControladorOrdenCompraInsumo?accion=Nuevo" method="post"> --> 
                                      
              <form action="ControladorDetalleCompraInsumo?accion=Editar" method="post">
                                    <br>
                                <div class="row">
                                        <div class="form-group col-2" >
                                            <label>Orden Compra Insumo</label><br>
                                            <input type="text" class="form-control"  name="Id_ordencompra"  value="${orden}" readonly="readonly"><br>

                                        </div>

                                        <div class="form-group col-3" >
                                            <label>Insumo</label><br>
                                            <input type="hidden" class="form-control"  name="Id_insumo" value="${idinsumo}"  readonly="readonly">
                                            <input type="text" class="form-control"  name="Insumo" value="${descripcion}"  readonly="readonly"><br>    
                                        </div>

                                        <div class="form-group col-3" >
                                            <label>Categoria</label><br>
                                            <input type="text" class="form-control"  name="Categoria" value="${categoria}" readonly="readonly"><br>                     
                                        </div>

                                        <div class="form-group col-2" >
                                            <label>Cantidad</label><br>
                                            <input type="text" class="form-control"  name="Cantidad" value="${cantidad}"><br>                     
                                        </div>
                                        
                                        <div class="form-group col-2" >
                                            <label>Unidad</label><br>
                                            <input type="text" class="form-control"  name="Unidad" value="${unidad}" readonly="readonly"><br>                     
                                        </div>
                                </div>
                                        
                               <button class="btn btn-info" type="submit">Actualizar</button>         
           
                </form>                
                  <!--   </form>  --> 
 
            <br>

            <!-- /.card-body -->
        </div>
        <!-- /.card -->     

        <%@ include file="templates/footer.jsp"%>
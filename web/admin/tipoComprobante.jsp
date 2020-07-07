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

            <div class="card-header">
                <h3 class="card-title">Mantener Tipo de Comprobantes</h3>
            </div>
            <!-- /.card-header -->

            <div class="container">
                <div class="row">
                    <div class="col-lg-12">            
                        <button id="btnNuevo-TipoComprobantes" type="button" class="btn btn-success" data-toggle="modal">Nuevo</button>    
                    </div>    
                </div>    
            </div>                 
           
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <table id="listTipoComprobantes" class="table table-bordered display nowrap" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Descripción</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>                       
                            </tbody>
                        </table>                       
                    </div>
                </div>
            </div>
            
            
            <!-- MODAL CRUD -->>                    
            <div class="modal fade" id="TipoComprobantesCRUD" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel"></h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <form id="formTipoComprobantes">    
                            <div class="modal-body">
                                <div class="form-group">
                                    <input type="hidden" class="form-control" id="id">
                                </div>
                                <div class="form-group">
                                    <label for="nombre" class="col-form-label">Descripción:</label>
                                    <input type="text" class="form-control" id="descripcion">
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-light" data-dismiss="modal">Cancelar</button>
                                <button type="submit" id="btnGuardar-TipoComprobantes" class="btn btn-dark">Guardar</button>
                            </div>
                        </form>    
                    </div>
                </div>
            </div>  

            <!-- /.card-body -->
        </div>
        <!-- /.card -->     

        <%@ include file="templates/footer.jsp"%>
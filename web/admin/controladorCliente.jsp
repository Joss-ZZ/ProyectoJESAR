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
                <h3 class="card-title">Mantener Clientes</h3>
            </div>
            <!-- /.card-header -->

            <div class="container">
                <div class="row">
                    <div class="col-lg-12">            
                        <button id="btnNuevo" type="button" class="btn btn-success" data-toggle="modal">Nuevo</button>    
                    </div>    
                </div>    
            </div>                


            <div class="card-body">

                <table id="listclientes" class="table table-bordered table-striped">
                    <thead>
                        <tr>
                            <th>Código</th>
                            <th>Nombre</th>
                            <th>Apellido</th>
                            <th>Tipo Documento</th>
                            <th>Documento</th>
                            <th>Teléfono</th>
                            <th>Dirección</th>
                            <th>Correo</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>                
                </table>
            </div>

            <!-- MODAL CRUD -->>                    
            <div class="modal fade" id="clientesCRUD" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel"></h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <form id="formPersonas">    
                            <div class="modal-body">
                                <div class="form-group">
                                    <label for="nombre" class="col-form-label">id:</label>
                                    <input type="number" class="form-control" id="id">
                                </div>
                                <div class="form-group">
                                    <label for="nombre" class="col-form-label">Nombre:</label>
                                    <input type="text" class="form-control" id="nombre">
                                </div>
                                <div class="form-group">
                                    <label for="pais" class="col-form-label">Apellidos:</label>
                                    <input type="text" class="form-control" id="apellidos">
                                </div>                
                                <div class="form-group">
                                    <label for="edad" class="col-form-label">Tipo de documento:</label>
                                    <input type="text" class="form-control" id="tipodoc">
                                </div>        
                                <div class="form-group">
                                    <label for="edad" class="col-form-label">Documento:</label>
                                    <input type="text" class="form-control" id="doc">
                                </div>   
                                <div class="form-group">
                                    <label for="edad" class="col-form-label">Telefono</label>
                                    <input type="text" class="form-control" id="telf">
                                </div>   
                                <div class="form-group">
                                    <label for="edad" class="col-form-label">Direccion:</label>
                                    <input type="text" class="form-control" id="dir">
                                </div>   
                                <div class="form-group">
                                    <label for="edad" class="col-form-label">Correo:</label>
                                    <input type="text" class="form-control" id="correo">
                                </div>   
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-light" data-dismiss="modal">Cancelar</button>
                                <button type="submit" id="btnGuardar" class="btn btn-dark">Guardar</button>
                            </div>
                        </form>    
                    </div>
                </div>
            </div>  



            <!-- /.card-body -->
        </div>
        <!-- /.card -->     

        <%@ include file="templates/footer.jsp"%>

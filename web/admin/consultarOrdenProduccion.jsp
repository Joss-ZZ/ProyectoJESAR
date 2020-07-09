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
                <h3 class="card-title">Lista de Ordenes de Producción</h3>
            </div>
            <!-- /.card-header -->
            <br>
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">            
                        <button id="btnRegistrarOrdenProduccion" type="button" class="btn btn-success">Registrar Orden de Producción</button>    
                    </div>    
                </div>    
            </div>    
            <br>
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <table id="ListaOrdenesProduccion" class="table table-bordered display nowrap" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Fecha de Inicio</th>
                                    <th>Fecha de Acabado</th>
                                    <th>Estado</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>                       
                            </tbody>
                        </table>                       
                    </div>
                </div>
            </div>
              

            <!-- /.card-body -->
        </div>
        <!-- /.card -->     

        <%@ include file="templates/footer.jsp"%>
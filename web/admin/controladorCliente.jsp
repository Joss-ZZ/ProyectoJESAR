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
            <div>
                <a href="#" class="btn btn-primary" id="btn-nuevo-cliente" style="width: 100%">Nuevo Cliente</a>
                <div class="overlay" id="overlay-nuevo-cliente">
                    <div class="popup" id="popup-nuevo-cliente">
                        <a href="#" id="btn-cerrar-popup-nuevo-cliente" class="btn-cerrar-popup"><i class="fas fa-times-circle"></i></a>
                        <h3>Nuevo Cliente Cl002</h3>
                        <form action="">
                            <div class="contenedor-inputs">
                                <input type="text" placeholder="Nombre">
                                <input type="text" placeholder="Apellido">
                                <input type="text" placeholder="Tipo Documento">
                                <input type="text" placeholder="Documento">
                                <input type="text" placeholder="Teléfono">
                                <input type="text" placeholder="Dirección">
                                <input type="email" placeholder="Correo">
                            </div>
                            <a href="#" id="btn btn-success-nuevo-cliente" class="btn btn-success">Realizar Cambios</a>
                        </form>
                    </div>
                </div>
                <script src="js/popup.js"></script>
            </div>
            <div class="card-body">

                <table id="example1" class="table table-bordered table-striped">
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
                            <th>Editar</th>
                            <th>Eliminar</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Cl001</td>
                            <td>Rodrigo</td>
                            <td>Garcia</td>
                            <td>DNI</td>
                            <td>71850039</td>
                            <td>923548737</td>
                            <td>Mz P lote 9</td>
                            <td>rodrigo.sandro_10@hotmail.com</td>
                            <th>
                                <a class="btn btn-success" id="btn-abrir-popup">Editar</a>
                                <div class="overlay" id="overlay">
                                    <div class="popup" id="popup">
                                        <a href="#" id="btn-cerrar-popup" class="btn-cerrar-popup"><i class="fas fa-times-circle"></i></a>
                                        <h3>Editar Cliente Cl001</h3>
                                        <form action="">
                                            <div class="contenedor-inputs">
                                                <input type="text" placeholder="Nombre">
                                                <input type="text" placeholder="Apellido">
                                                <input type="text" placeholder="Tipo Documento">
                                                <input type="text" placeholder="Documento">
                                                <input type="text" placeholder="Teléfono">
                                                <input type="text" placeholder="Dirección">
                                                <input type="email" placeholder="Correo">
                                            </div>
                                            <a href="#" id="btn btn-success" class="btn btn-success">Realizar Cambios</a>
                                        </form>
                                    </div>
                                </div>
                                <script src="js/popup.js"></script>
                            </th>
                            <th><a class="btn btn-danger" href="">Eliminar</a></th>
                        </tr>


                    </tbody>
                </table>
            </div>
            <!-- /.card-body -->
        </div>
        <!-- /.card -->

        <%@ include file="templates/footer.jsp"%>

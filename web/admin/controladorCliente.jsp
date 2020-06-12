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
                                <input type="text" placeholder="Nombre" value=${nombre}>
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

            <!-- /.card-body -->
        </div>
        <!-- /.card -->
        <script src="/Admin-JESAR/admin/js/jquery.min.js"></script>
            <script>
                $(document).ready(function() {
                    var tabla = $('#listclientes').DataTable({
                        ajax:{
                            method: "POST",
                            url: "/Admin-JESAR/Prueba",
                            dataSrc: "datos"
                        },
                        columns: [
                            {"data": "id"},
                            {"data": "nombre"},
                            {"data": "apellidos"},
                            {"data": "tipo_documento"},
                            {"data": "documento"},
                            {"data": "telefono"},
                            {"data": "direccion"},
                            {"data": "correo"},
                            {"data": "acciones"}
                        ]
                    });
                });
            </script>

        <%@ include file="templates/footer.jsp"%>

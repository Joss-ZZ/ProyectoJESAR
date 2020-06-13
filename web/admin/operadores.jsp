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
                <h3 class="card-title">Mantener Operarios</h3>
            </div>
            <!-- /.card-header -->
            <div>
                <a href="#" class="btn btn-primary" id="btn-nuevo-operario" style="width: 100%">Nuevo Operario</a>
                <div class="overlay" id="overlay-nuevo-operario">
                    <div class="popup" id="popup-nuevo-operario">
                        <a href="#" id="btn-cerrar-popup-nuevo-operador" class="btn-cerrar-popup"><i class="fas fa-times-circle"></i></a>
                        <h3>Nuevo Operario Ol002</h3>              
                        <form action="">
                            <div class="contenedor-inputs">
                                <input type="text" placeholder="Nombre" value=${nombre}>
                                <input type="text" placeholder="Apellido">
                                <input type="text" placeholder="Dirección">
                                <input type="text" placeholder="Teléfono">
                                <input type="email" placeholder="Correo">
                                <input type="text" placeholder="Edad">
                                <input type="text" placeholder="DNI">
                                <input type="text" placeholder="Sueldo">
                                <input type="text" placeholder="Tipo de operario">      
                            </div>
                            <a href="#" id="btn btn-success-nuevo-operador" class="btn btn-success">Realizar Cambios</a>
                        </form>
                    </div>
                </div>
                <script src="js/popup.js"></script>
            </div>
            <div class="card-body">

                <table codigo="listoperadores" class="table table-bordered table-striped">
                    <thead>
                        <tr>
                            <th>Código< /th>
                            <th>Nombre</th>
                            <th>Apellido</th>
                            <th>Dirección</th>
                            <th>Teléfono</th>
                            <th>Correo</th>
                            <th>Edad</th>
                            <th>DNI</th>
                            <th>Sueldo</th>
                            <th>Tipo de operario</th>
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
                            {"data": "direccion"},
                            {"data": "teléfono"},
                            {"data": "correo"},
                            {"data": "edad"},
                            {"data": "dni"},
                            {"data": "sueldo"},
                            {"data": "tipo de operario"},
                        ]
                    });
                });
            </script>

        <%@ include file="templates/footer.jsp"%>
        
        
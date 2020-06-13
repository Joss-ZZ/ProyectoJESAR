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
                <h3 class="card-title">Mantener Empleados</h3>
              </div>
            <!-- /.card-header -->
            <div>
                <a href="#" class="btn btn-primary" id="btn-nuevo-empleado" style="width: 100%">Nuevo Empleado</a>
                <div class="overlay" id="overlay-nuevo-empleado">
                    <div class="popup" id="popup-nuevo-empleado">
                        <a href="#" id="btn-cerrar-popup-nuevo-empleado" class="btn-cerrar-popup"><i class="fas fa-times-circle"></i></a>
                        <h3>Nuevo Empleado El002</h3>              
                        <form action="">
                            <div class="contenedor-inputs">
                                <input type="text" placeholder="Usuario" value=${usuario}>
                                <input type="text" placeholder="Contraseña">
                                <input type="text" placeholder="Nombre" >
                                <input type="text" placeholder="Apellido">
                                <input type="text" placeholder="Dirección">
                                <input type="text" placeholder="Teléfono">
                                <input type="email" placeholder="Correo">
                                <input type="text" placeholder="Edad">
                                <input type="text" placeholder="DNI">
                                <input type="text" placeholder="Estado">
                                <input type="text" placeholder="Privilegio">      
                            </div>
                            <a href="#" id="btn btn-success-nuevo-empleado" class="btn btn-success">Realizar Cambios</a>
                        </form>
                    </div>
                </div>
                <script src="js/popup.js"></script>
            </div>
            <div class="card-body">

                <table codigo="listempleado" class="table table-bordered table-striped">
                    <thead>
                        <tr>
                            <th>Código</th>
                            <th>Usuario</th>
                            <th>Contraseña</th>
                            <th>Nombre</th>
                            <th>Apellido</th>
                            <th>Dirección</th>
                            <th>Teléfono</th>
                            <th>Correo</th>
                            <th>Edad</th>
                            <th>DNI</th>
                            <th>Estado</th>
                            <th>Tipo de Empleado</th>
                        </tr>
                    </thead>
                  <tbody>
                  <tr>
                    <td>1</td>
                    <td>RenatoQ</td>
                    <td>123456</td>
                    <td>Renato</td>
                    <td>Quispe</td>
                    <td>asdfghjkl</td>
                    <td>123456789</td>
                    <td>asdfgh@gmail.com</td>
                    <td>19</td>
                    <td>75315946</td>
                    <td>Estado</td>
                    <td>Tipo de Empleado</td>
                  </tr>
                  <tr>
                    <td>2</td>
                    <td>Jhoxi</td>
                    <td>123456</td>
                    <td>Jhosimar</td>
                    <td>Zevallos</td>
                    <td>asdfghjkl</td>
                    <td>123456789</td>
                    <td>asdfgh@gmail.com</td>
                    <td>26</td>
                    <td>75315946</td>
                    <td>Estado</td>
                    <td>Tipo de Empleado</td>
                  </tr>
                  </tbody>
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
                            {"data": "usuario"},
                            {"data": "contraseña"},
                            {"data": "nombre"},
                            {"data": "apellidos"},
                            {"data": "direccion"},
                            {"data": "teléfono"},
                            {"data": "correo"},
                            {"data": "edad"},
                            {"data": "dni"},
                            {"data": "estado"},
                            {"data": "privilegio"},
                        ]
                    });
                });
            </script>
            
<%@ include file="templates/footer.jsp"%>

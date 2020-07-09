<%@page import="java.util.LinkedList"%>
<%@page import="MODELO.TipoEmpleado"%>
<%@page import="MODELO.TipoDocumento"%>
<%@page import="MODELO.Empleado"%>
<%@page import="MODELO.Conexion"%>
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

            <div class="container">
                <div class="row">
                    <div class="col-lg-12">            
                        <button id="btnNuevo-Empleado" type="button" class="btn btn-success" data-toggle="modal">Nuevo</button>    
                    </div>    
                </div>    
            </div>                 
           
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <table id="listEmpleado" class="table table-bordered display nowrap" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Usuario</th>
                                    <th>Contraseña</th>
                                    <th>Nombres</th>
                                    <th>Apellidos</th>
                                    <th>Dirección</th>
                                    <th>Teléfono</th>
                                    <th>Correo</th>
                                    <th>Fecha Nacimiento</th>
                                    <th>Tipo Documento</th>
                                    <th>N° Documento</th>
                                    <th>Estado</th>
                                    <th>Privilegio</th>
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
            <div class="modal fade" id="empleadoCRUD" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel"></h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <form id="formEmpleado">    
                            <div class="modal-body">
                                <div class="form-group">
                                    <input type="hidden" class="form-control" id="id">
                                </div>
                                <div class="form-group">
                                    <label for="nombre" class="col-form-label">Usuario:</label>
                                    <input type="text" class="form-control" id="usuario">
                                </div>
                                <div class="form-group">
                                    <label for="nombre" class="col-form-label">Contraseña:</label>
                                    <input type="text" class="form-control" id="password">
                                </div>
                                <div class="form-group">
                                    <label for="nombre" class="col-form-label">Nombres:</label>
                                    <input type="text" class="form-control" id="nombres">
                                </div>
                                <div class="form-group">
                                    <label for="nombre" class="col-form-label">Apellidos:</label>
                                    <input type="text" class="form-control" id="apellidos">
                                </div>
                                <div class="form-group">
                                    <label for="nombre" class="col-form-label">Dirección:</label>
                                    <input type="text" class="form-control" id="direccion">
                                </div>
                                <div class="form-group">
                                    <label for="nombre" class="col-form-label">Teléfono:</label>
                                    <input type="text" class="form-control" id="telefono">
                                </div>
                                <div class="form-group">
                                    <label for="nombre" class="col-form-label">Correo:</label>
                                    <input type="text" class="form-control" id="correo">
                                </div>
                                <div class="form-group">
                                    <label for="nombre" class="col-form-label">Fecha Nacimiento:</label>
                                    <input type="text" class="form-control" id="fechaNacimiento">
                                </div>
                                <div class="form-group">
                                    <label for="categoria" class="col-form-label">Tipo de Documento:</label>
                                    <select name="categoria" id="tipoDocumento">
                                        <option >Elija una opción</option>
                                        <%
                                            Conexion conn = new Conexion();
                                            TipoDocumento tidoc = new TipoDocumento(conn);
                                            LinkedList<TipoDocumento> tipoDocumentos = new LinkedList<>();
                                            tipoDocumentos = tidoc.ListarTipoDocumentos();
                                            for (TipoDocumento td : tipoDocumentos) {
                                        %>                                        
                                        <option name="categoria" value="<%=td.getId()%>"><%=td.getDescripcion()%></option>                                      
                                        <%}%>
                                    </select>                                    
                                </div>
                                <div class="form-group">
                                    <label for="nombre" class="col-form-label">N° Documento:</label>
                                    <input type="text" class="form-control" id="nDocumento">
                                </div>
                                <div class="form-group">
                                    <label for="categoria" class="col-form-label">Estado:</label>
                                    <select name="categoria" id="estado">
                                        <option >Elija una opción</option>
                                        <%
                                            Conexion conn1 = new Conexion();
                                            Empleado emp = new Empleado(conn1);
                                            LinkedList<Empleado> empleados = new LinkedList<>();
                                            empleados = emp.ListarEmpleados();
                                            for (Empleado e : empleados) {
                                        %>                                        
                                        <option name="categoria" value="<%=e.getId()%>"><%=e.getEstado()%></option>                                      
                                        <%}%>
                                    </select>                                    
                                </div>
                                <div class="form-group">
                                    <label for="nombre" class="col-form-label">Privilegio:</label>
                                    <input type="text" class="form-control" id="privilegio">
                                </div>
                                <div class="form-group">
                                    <label for="categoria" class="col-form-label">Tipo de Empleado:</label>
                                    <select name="categoria" id="tipoEmpleado">
                                        <option >Elija una opción</option>
                                        <%
                                            Conexion conn2 = new Conexion();
                                            TipoEmpleado tiemp = new TipoEmpleado(conn2);
                                            LinkedList<TipoEmpleado> tipoEmpleados = new LinkedList<>();
                                            tipoEmpleados = tiemp.ListarTipoEmpleados();
                                            for (TipoEmpleado te : tipoEmpleados) {
                                        %>                                        
                                        <option name="categoria" value="<%=te.getId()%>"><%=te.getDescripcion()%></option>                                      
                                        <%}%>
                                    </select>                                    
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-light" data-dismiss="modal">Cancelar</button>
                                <button type="submit" id="btnGuardar-color" class="btn btn-dark">Guardar</button>
                            </div>
                        </form>    
                    </div>
                </div>
            </div>  

            <!-- /.card-body -->
        </div>
        <!-- /.card -->     

        <%@ include file="templates/footer.jsp"%>
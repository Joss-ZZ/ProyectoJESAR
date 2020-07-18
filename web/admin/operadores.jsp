<%@page import="MODELO.TipoOperario"%>
<%@page import="MODELO.Conexion"%>
<%@page import="java.util.LinkedList"%>
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

            <div class="container">
                <div class="row">
                    <div class="col-lg-12">            
                        <button id="btnNuevoOperario" type="button" class="btn btn-success" data-toggle="modal">Nuevo Operario</button>    
                    </div>    
                </div>    
            </div>                

            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <table id="ListaOperarios" class="table table-bordered display nowrap" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <th>Código</th>
                                    <th>Nombre</th>
                                    <th>Apellidos</th>
                                    <th>IDTipoOpe</th>                                
                                    <th>Tipo</th>
                                    <th>Direccion</th>
                                    <th>Telefono</th>
                                    <th>Correo</th>
                                    <th>Fecha Naci</th>
                                    <th>DNI</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>                       
                            </tbody>
                        </table>                       
                    </div>
                </div>
            </div>

            <!-- MODAL CREAR -->>                    
            <div class="modal fade" id="operariosCrear" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel"></h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <form id="formOperarios">    
                            <div class="modal-body">
                                <div class="form-group">
                                    <label for="id" class="col-form-label"></label>                                  
                                    <input type="hidden" class="form-control" id="operario_id">
                                </div>                                                          
                                <div class="form-group">
                                    <label for="insumo" class="col-form-label">Nombre:</label>
                                    <input type="text" class="form-control" id="nombre_ope">
                                </div>                
                                <div class="form-group">
                                    <label for="cantidad" class="col-form-label">Apellidos:</label>
                                    <input type="text" class="form-control" id="apellidos_ope">
                                </div>        
                                <div class="form-group">
                                    <label for="unidad" class="col-form-label">Tipo:</label>
                                    <select name="unidad" id="tipo_ope">
                                        <option selected="selected">Elija una opción</option>
                                        <%
                                            Conexion conn1 = new Conexion();
                                            TipoOperario und = new TipoOperario(conn1);
                                            LinkedList<TipoOperario> tipo_ope = new LinkedList<>();
                                            tipo_ope = und.ListarTipoOperarios();
                                            for (TipoOperario tipos : tipo_ope) {
                                        %>                                        
                                        <option name="unidad" value="<%=tipos.getId()%>"><%=tipos.getDescripcion()%></option>                                      
                                        <%}%>
                                    </select> 
                                </div>   
                                <div class="form-group">
                                    <label for="cantidad" class="col-form-label">Direccion:</label>
                                    <input type="text" class="form-control" id="direccion_ope">
                                </div>  
                                <div class="form-group">
                                    <label for="cantidad" class="col-form-label">Telefono:</label>
                                    <input type="text" class="form-control" id="telefono_ope">
                                </div>  
                                <div class="form-group">
                                    <label for="cantidad" class="col-form-label">Correo:</label>
                                    <input type="text" class="form-control" id="correo_ope">
                                </div>
                                <div class="form-group">
                                    <label for="cantidad" class="col-form-label">Fecha Nacimiento:</label>
                                    <input type="text" class="form-control" id="fechanac_ope">
                                </div>  
                                <div class="form-group">
                                    <label for="cantidad" class="col-form-label">DNI:</label>
                                    <input type="text" class="form-control" id="dni_ope">
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

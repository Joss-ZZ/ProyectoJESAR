<%@page import="MODELO.Conexion"%>
<%@page import="MODELO.UnidadInsumo"%>
<%@page import="java.util.LinkedList"%>
<%@page import="MODELO.CategoriaInsumo"%>
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
                <h3 class="card-title">Mantener Insumos</h3>
            </div>
            <!-- /.card-header -->

            <div class="container">
                <div class="row">
                    <div class="col-lg-12">            
                        <button id="btnNuevoInsumo" type="button" class="btn btn-success" data-toggle="modal">Nuevo Insumo</button>    
                    </div>    
                </div>    
            </div>                

            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <table id="ListaInsumos" class="table table-bordered display nowrap" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <th>Código</th>
                                    <th>Categoría</th>
                                    <th>Nombre de Insumo</th>
                                    <th>Cantidad</th>
                                    <th>Unidad</th>
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
            <div class="modal fade" id="insumosCrear" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel"></h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <form id="formInsumos">    
                            <div class="modal-body">
                                <div class="form-group">
                                    <label for="id" class="col-form-label"></label>                                  
                                    <input type="hidden" class="form-control" id="insumo_id">
                                </div>                                 
                                <div class="form-group">
                                    <label for="categoria" class="col-form-label">Categoria:</label>
                                    <select name="categoria" id="categoria">
                                        <option >Elija una opción</option>
                                        <%
                                            Conexion conn = new Conexion();
                                            CategoriaInsumo cat = new CategoriaInsumo(conn);
                                            LinkedList<CategoriaInsumo> categorias = new LinkedList<>();
                                            categorias = cat.ListarCat();
                                            for (CategoriaInsumo cate : categorias) {
                                        %>                                        
                                        <option name="categoria" value="<%=cate.getId()%>"><%=cate.getDescripcion()%></option>                                      
                                        <%}%>
                                    </select>                                    
                                </div>
                                <div class="form-group">
                                    <label for="insumo" class="col-form-label">Nombre de Insumo:</label>
                                    <input type="text" class="form-control" id="nombre">
                                </div>                
                                <div class="form-group">
                                    <label for="cantidad" class="col-form-label">Cantidad:</label>
                                    <input type="text" class="form-control" id="cantidad">
                                </div>        
                                <div class="form-group">
                                    <label for="unidad" class="col-form-label">Unidad:</label>
                                    <select name="unidad" id="unidad">
                                        <option selected="selected">Elija una opción</option>
                                        <%
                                            Conexion conn1 = new Conexion();
                                            UnidadInsumo und = new UnidadInsumo(conn1);
                                            LinkedList<UnidadInsumo> unidades = new LinkedList<>();
                                            unidades = und.ListarUnidades();
                                            for (UnidadInsumo unidad : unidades) {
                                        %>                                        
                                        <option name="unidad" value="<%=unidad.getId()%>"><%=unidad.getDescripcion()%></option>                                      
                                        <%}%>
                                    </select> 
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

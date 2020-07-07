<%@page import="MODELO.Conexion"%>
<%@page import="MODELO.CategoriaProducto"%>
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
                <h3 class="card-title">Mantener Modelo de Productos</h3>
            </div>
            <!-- /.card-header -->

            <div class="container">
                <div class="row">
                    <div class="col-lg-12">            
                        <button id="btnNuevoModeloProducto" type="button" class="btn btn-success" data-toggle="modal">Nuevo</button>    
                    </div>    
                </div>    
            </div>                

            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <table id="ListaModeloProductos" class="table table-bordered" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <th>Código</th>
                                    <th>IDCat</th>
                                    <th>Categoría</th>
                                    <th>Nombre de Modelo</th>
                                    <th>Precio de Venta</th>
                                    <th>Precio de Cortado</th>
                                    <th>Precio de Aparado</th>
                                    <th>Precio de Armado</th>
                                    <th>Precio de Alistado</th>
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
            <div class="modal fade" id="ModeloProductosCRUD" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel"></h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <form id="formModeloProductos">    
                            <div class="modal-body">
                                <div class="form-group">
                                    <label for="id" class="col-form-label">Código de Modelo</label>                                  
                                    <input type="text" class="form-control" id="cod_modeloproducto">
                                </div>                                 
                                <div class="form-group">
                                    <label for="categoria" class="col-form-label">Categoria:</label>
                                    <select name="categoria" id="id_categoria">
                                        <option >Elija una opción</option>
                                        <%
                                            Conexion conn = new Conexion();
                                            CategoriaProducto cat = new CategoriaProducto(conn);
                                            LinkedList<CategoriaProducto> categorias = new LinkedList<>();
                                            categorias = cat.ListarCat();
                                            for (CategoriaProducto cate : categorias) {
                                        %>                                        
                                        <option name="categoria" value="<%=cate.getId()%>"><%=cate.getDescripcion()%></option>                                      
                                        <%}%>
                                    </select>                                    
                                </div>
                                <div class="form-group">
                                    <label for="insumo" class="col-form-label">Nombre de Modelo:</label>
                                    <input type="text" class="form-control" id="nombre">
                                </div>                
                                <div class="form-group">
                                    <label for="cantidad" class="col-form-label">Precio de Venta:</label>
                                    <input type="text" class="form-control" id="p_venta">
                                </div>
                                <div class="form-group">
                                    <label for="cantidad" class="col-form-label">Precio de Cortado:</label>
                                    <input type="text" class="form-control" id="p_cortado">
                                </div>
                                <div class="form-group">
                                    <label for="cantidad" class="col-form-label">Precio de Aparado:</label>
                                    <input type="text" class="form-control" id="p_aparado">
                                </div>
                                <div class="form-group">
                                    <label for="cantidad" class="col-form-label">Precio de Armado:</label>
                                    <input type="text" class="form-control" id="p_armado">
                                </div>
                                <div class="form-group">
                                    <label for="cantidad" class="col-form-label">Precio de Alistado:</label>
                                    <input type="text" class="form-control" id="p_alistado">
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

<%@page import="MODELO.ModeloProducto"%>
<%@page import="MODELO.Color"%>
<%@page import="MODELO.Serie"%>
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
                <h3 class="card-title">Mantener Productos</h3>
            </div>
            <!-- /.card-header -->

            <div class="container">
                <div class="row">
                    <div class="col-lg-12">            
                        <button id="btnNuevoProducto" type="button" class="btn btn-success" data-toggle="modal">Nuevo</button>    
                    </div>    
                </div>    
            </div>                

            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <table id="ListaProductos1" class="table table-bordered" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <th>Código Modelo</th>
                                    <th>Código Serie</th>
                                    <th>Serie</th>
                                    <th>Código Color</th>
                                    <th>Color</th>
                                    <th>Stock del Producto</th>
                                    <th>Stock Cortado</th>
                                    <th>Stock Aparado</th>
                                    <th>Stock Armado</th>
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
            <div class="modal fade" id="ProductosCRUD" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel"></h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <form id="formProductos">    
                            <div class="modal-body">
                                <div class="form-group">
                                    <label for="categoria" class="col-form-label">Modelo del Producto:</label>
                                    <select name="categoria" id="cod_modeloproducto">
                                        <option >Elija una opción</option>
                                        <%
                                            Conexion conn2 = new Conexion();
                                            ModeloProducto mp = new ModeloProducto(conn2);
                                            LinkedList<ModeloProducto> modelosProductos = new LinkedList<>();
                                            modelosProductos = mp.ListarModelosProductos();
                                            for (ModeloProducto mopro : modelosProductos) {
                                        %>                                        
                                        <option name="categoria" value="<%=mopro.getCod_modeloproducto()%>"><%=mopro.getCod_modeloproducto()%></option>                                      
                                        <%}%>
                                    </select>                                    
                                </div>                                 
                                <div class="form-group">
                                    <label for="categoria" class="col-form-label">Serie:</label>
                                    <select name="categoria" id="id_serie">
                                        <option >Elija una opción</option>
                                        <%
                                            Conexion conn = new Conexion();
                                            Serie s = new Serie(conn);
                                            LinkedList<Serie> series = new LinkedList<>();
                                            series = s.ListarSeries();
                                            for (Serie ser : series) {
                                        %>                                        
                                        <option name="categoria" value="<%=ser.getId()%>"><%=ser.getDescripcion()%></option>                                      
                                        <%}%>
                                    </select>                                    
                                </div>
                                <div class="form-group">
                                    <label for="categoria" class="col-form-label">Color:</label>
                                    <select name="categoria" id="id_color">
                                        <option >Elija una opción</option>
                                        <%
                                            Conexion conn1 = new Conexion();
                                            Color c = new Color(conn1);
                                            LinkedList<Color> colores = new LinkedList<>();
                                            colores = c.ListarColores();
                                            for (Color col : colores) {
                                        %>                                        
                                        <option name="categoria" value="<%=col.getId()%>"><%=col.getDescripcion()%></option>                                      
                                        <%}%>
                                    </select>                                    
                                </div>               
                                <div class="form-group">
                                    <label for="cantidad" class="col-form-label">Stock del Producto:</label>
                                    <input type="text" class="form-control" id="stock_producto">
                                </div>
                                <div class="form-group">
                                    <label for="cantidad" class="col-form-label">Stock Cortado:</label>
                                    <input type="text" class="form-control" id="stock_cortado">
                                </div>
                                <div class="form-group">
                                    <label for="cantidad" class="col-form-label">PStock Aparado:</label>
                                    <input type="text" class="form-control" id="stock_aparado">
                                </div>
                                <div class="form-group">
                                    <label for="cantidad" class="col-form-label">Stock Armado:</label>
                                    <input type="text" class="form-control" id="stock_armado">
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-light" data-dismiss="modal">Cancelar</button>
                                <button type="submit" id="btnGuardarProducto" class="btn btn-dark">Guardar</button>
                            </div>
                        </form>    
                    </div>
                </div>
            </div>  

            <!-- /.card-body -->
        </div>
        <!-- /.card -->     

        <%@ include file="templates/footer.jsp"%>

<%@page import="java.util.LinkedList"%>
<%@page import="MODELO.OrdenProduccion"%>
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
                <h3 class="card-title">Registrar Orden de Producto</h3>
            </div>

            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="container">
                            <div class="form-group">
                                <label for="categoria" class="col-form-label">Categoria:</label>
                                <select name="categoria" id="id_orden_produccion">
                                    <option >Elija una opción</option>
                                    <%
                                        Conexion conn = new Conexion();
                                        OrdenProduccion ord = new OrdenProduccion(conn);
                                        LinkedList<OrdenProduccion> OrdenProducciones = new LinkedList<>();
                                        OrdenProducciones = ord.ListarOrdenProduccion();
                                        for (OrdenProduccion or : OrdenProducciones) {
                                    %>                                        
                                    <option name="categoria" value="<%=or.getId_orden_prod()%>"><%=or.getId_orden_prod()%></option>                                      
                                    <%}%>
                                </select>                                    
                            </div>
                            <div class="row">
                                <div class="col-lg-12">            
                                    <button id="btnAgregar" type="button" class="btn btn-primary">Agregar Producto</button>    
                                </div>    
                            </div>    
                        </div>       
                        <br> 
                        <div class="container">
                            <div class="row">
                                <div class="col-lg-12">
                                    <table id="listDetalleProductos" class="table table-bordered display nowrap" cellspacing="0" width="100%">
                                        <thead>
                                            <tr>
                                                <th>Código Orden</th>
                                                <th>Código Producto</th>
                                                <th>id Serie</th>
                                                <th>Serie</th>
                                                <th>id color</th>
                                                <th>Color</th>
                                                <th>Cantidad a Producir</th>
                                                <th>Acciones</th>
                                            </tr>
                                        </thead>
                                        <tbody>                       
                                        </tbody>
                                    </table>                       
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <br>

            <div class="modal fade" id="editarCantidad" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel"></h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <form id="formEditarCantidad">    
                            <div class="modal-body">
                                <div class="modal-body">
                                    <div class="form-group">
                                        <label for="nombre" class="col-form-label">Cantidad:</label>
                                        <input type="text" class="form-control" id="cantidad">
                                    </div>
                                </div>
                                <div class="modal-body">
                                    <div class="form-group">
                                        <input type="hidden" class="form-control" id="id_orden_prod">
                                    </div>
                                </div>
                                <div class="modal-body">
                                    <div class="form-group">
                                        <input type="hidden" class="form-control" id="cod_modeloproducto">
                                    </div>
                                </div>
                                <div class="modal-body">
                                    <div class="form-group">
                                        <input type="hidden" class="form-control" id="id_serie">
                                    </div>
                                </div>
                                <div class="modal-body">
                                    <div class="form-group">
                                        <input type="hidden" class="form-control" id="id_color">
                                    </div>
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
          
            <!-- MODAL CREAR -->>                    
            <div class="modal fade" id="agregarProductosOrden" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel"></h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="container">
                            <br><br><br><br>
                            <div class="row">
                                <div class="col-lg-12">
                                    <table id="ListaProductos2" class="table table-bordered" cellspacing="0" width="100%">
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
                        <br><br><br><br>
                    </div>
                </div>
            </div> 


            <%@ include file="templates/footer.jsp"%>

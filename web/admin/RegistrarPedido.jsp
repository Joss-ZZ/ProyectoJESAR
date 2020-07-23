<%-- 
    Document   : RegistrarPedido
    Created on : 23-jul-2020, 1:32:21
    Author     : PCGAMING
--%>

<%@page import="java.util.LinkedList"%>
<%@page import="MODELO.Conexion"%>
<%@ include file="templates/header.jsp"%>
<%@ include file="templates/barra.jsp"%>
<%@ include file="templates/navegacion.jsp"%>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- /.card-header -->
      <%
               
            // DE CONSULTAR ORDEN PRODUCCION
            
             String id_pedido= request.getParameter("enviar_orden_pedido"); // pedido 
             
             String id_ordenproduccion = request.getParameter("enviarOrdproduccion");
             String producto = request.getParameter("enviarIdproducto");
             String descrip_producto = request.getParameter("enviarDescpricionproducto");
             String categ_producto = request.getParameter("enviarCategoria");
             String id_serie = request.getParameter("enviarIdserie");  
             String serie_descripcion = request.getParameter("enviarSeriedescripcion");  
             String id_color = request.getParameter("enviarIdcolor");   
             String color_descripcion = request.getParameter("enviarColordescripcion"); 
             String cantidad = request.getParameter("enviarCantidadproducir"); 

             //===============================================================================
            // empleado
            // String id_empleado= request.getParameter("enviar_id_empleado");
             
             // cliente
             String id_cliente = request.getParameter("enviarIdcliente");
             String nom_cliente = request.getParameter("enviarNombrecliente");
             String apellido_cliente = request.getParameter("enviarApellidocliente");
             String documento_cliente = request.getParameter("enviarDocumento");
             
             if(id_pedido==null){
                 id_pedido=" ";
   
             }
           if(id_ordenproduccion==null && producto==null && descrip_producto==null && categ_producto==null && id_serie==null && serie_descripcion==null && id_color==null && color_descripcion==null && cantidad==null){
                 
                 id_ordenproduccion=" ";
                 producto=" ";
                 descrip_producto=" ";
                 categ_producto=" ";
                 id_serie=" ";
                 serie_descripcion=" ";
                 id_color=" ";
                 color_descripcion=" ";
                 cantidad=" ";
             }
            if(id_cliente==null && nom_cliente==null && apellido_cliente==null && documento_cliente==null){
                 id_cliente=" ";
                 nom_cliente=" ";
                 apellido_cliente=" ";
                 documento_cliente=" ";
             }

            int id_empleado;
             
         %>
         <%
               Empleado emp =new Empleado();
                 LinkedList<Empleado> listaempleado; 
                 listaempleado=emp.consultarEmpleados();
         %>
    <div class="card-body">
    </div>
    <!-- /.card-body --><section class="content">

        <!-- Default box -->
        <div class="card">
     
            <div class="card-header">
                <h3 class="card-title">Registrar Pedidos Cliente</h3>
            </div>
            <!-- /.card-header -->
            <br>
                <div class="container">
              <!--   <form name="form_"action="ControladorOrdenCompraInsumo?accion=Nuevo" method="post"> --> 
                                      
                                    <h5> Registro Pedido</h5>
                                    <br>
                                    <div class="row">
                                        <div class="form-group col-2">      
                                            <label>Id-Pedido Cliente</label><br>
                                            <form action="ConsultarOrdProduccion.jsp" method="POST">
                                                <input type="text"  class="form-control" id="enviar_orden_pedido" name="enviar_orden_pedido" value="<%=id_pedido %>" ><br>
                                       
                                                
                                                <input class="btn btn-success" type="submit" value="Consultar Ord Produccion">   
                                             </form>  
                                        </div>
                                        
                                        <div class="form-group col-2">      
                                            <label>EMPLEADO</label><br>
                                               
                                                 <select id="ori_esta_id" class="form-control" name="" onchange="ShowSelected();">
                                                        <option value="">Seleccionar</option>
                                                        <% for (int e = 0; e < listaempleado.size(); e++) {
                                                        String nomcom=listaempleado.get(e).getNombres()+" , "+ listaempleado.get(e).getApellidos();
                                                         id_empleado=listaempleado.get(e).getId();
                                                        %>
                                                       
                                                        <option  value="<%=listaempleado.get(e).getId()%>"><%=nomcom%></option>
                                                   
                                                        <%}%>
                                                 </select>                                      
                                        </div>
                                        <div class="form-group col-2">         
                                          <form action="ConsultarCliente.jsp" method="POST">      
                                              
                                         <div class="form-group col-12">      
                                            <label>Nombre Cliente</label><br>
                                           
                                                <input type="hidden"  class="form-control" name="enviar_pedido" value="<%=id_pedido %>" >
                                                <input type="hidden"  class="form-control" name="enviarIdcliente" value="<%=id_cliente  %>" >
                                                <input type="text"  class="form-control" id="enviar_pedido" name="enviar_pedido" value="<%=nom_cliente %>" readonly="readonly" ><br>
                                              <!---aaaa-->
                                                <input type="hidden" class="form-control"  value="<%=id_ordenproduccion %>" name="IDORDENPRODUCCION" readonly="readonly">
                                                <input type="hidden" class="form-control" value="<%=producto%>" name="IDPRODUCTO" readonly="readonly"> 
                                                <input type="hidden" class="form-control" value="<%=descrip_producto%>" name="IDDESCPRODUCTO"  readonly="readonly">     
                                                <input type="hidden" class="form-control" value="<%=categ_producto%>" name="IDCATEGORIA"  readonly="readonly">   
                                                <input type="hidden" class="form-control" value="<%=id_serie %>" name="IDSERIE" readonly="readonly">
                                                <input type="hidden" class="form-control" value="<%=serie_descripcion %>"  name="SERIEDESC"  readonly="readonly">
                                                <input type="hidden" class="form-control" value="<%=id_color %>"  name="IDCOLOR"  readonly="readonly">
                                                <input type="hidden" class="form-control" value="<%=color_descripcion %>"  name="COLORDESC"  readonly="readonly"> 
                                                <input type="hidden" class="form-control"  value="<%=cantidad %>" name="CANTIDAD"  readonly="readonly">  
                                                
                                                <input class="btn btn-danger" type="submit" value="Consultar Cliente">       
                                          </form>
              
                                         </div>
                                        
                                         </div>
                                          <div class="form-group col-2"> 
                                              <label>Apellido Cliente</label><br>
        
                                              <input type="text"  class="form-control"  name="enviar_apellido" value="<%=apellido_cliente %>" readonly="readonly" ><br>
                                             
                                           </div>
                                          <div class="form-group col-2"> 
                                          <label>Documento</label><br>
                                          
                                              <input type="text"  class="form-control" name="enviar_documento" value="<%=documento_cliente %>" readonly="readonly" ><br>
                     
                                           </div>   
                                      </div><!-- 1 row -->
                                      <div class="row"> 
                                        <div class="form-group col-2" >
                                            <label>Orden Produccion</label><br>
                                            <input type="text" class="form-control"  name="id" value="<%=id_ordenproduccion %>"readonly="readonly"><br>

                                        </div>

                                        <div class="form-group col-2" >
                                            <label>Producto</label><br>
                                            <input type="text" class="form-control" value="<%=producto%>" name="producto" readonly="readonly"><br>                     
                                        </div>

                                        <div class="form-group col-2" >
                                            <label>Descripcion</label><br>
                                            <input type="text" class="form-control" value="<%=descrip_producto%>" name="descproducto"  readonly="readonly"><br>                     
                                        </div>

                                        <div class="form-group col-2" >
                                            <label>Categoria</label><br>
                                            <input type="text" class="form-control" value="<%=categ_producto%>" name="categoria"  readonly="readonly"><br>                     
                                        </div>
                                        
                                        <div class="form-group col-2" >
                                            <label>Serie</label><br>
                                            <input type="hidden" class="form-control" value="<%=id_serie %>" name="idserie" readonly="readonly">
                                            <input type="text" class="form-control" value="<%=serie_descripcion %>"  name="seriedesc"  readonly="readonly"><br>   
                                        </div>
                                        
                                        <div class="form-group col-2" >
                                            <label>Color</label><br>
                                            <input type="hidden" class="form-control" value="<%=id_color %>"  name="idcolor"  readonly="readonly">
                                            <input type="text" class="form-control" value="<%=color_descripcion %>"  name="colordesc"  readonly="readonly"><br>   
                                        </div>
                                        
                                        <div class="form-group col-2" >
                                            <label>Cantidad Producir</label><br>
                                            <input type="text" class="form-control"  value="<%=cantidad %>" name="cantidad"  readonly="readonly"><br>                     
                                        </div>
                                        
                          
                                       <!-- envia al jsp consultar orden produccion-->     
                                  
                                       <br>            
                                    
                                    </div>   
                                     <div class="row">
                                        <form name="" action="/Admin-JESAR/ControladorDetalleCompraInsumo?accion=Nuevo" method="post">
                                           <!-- capturando datos para el servlet-->
                                           <input type="hidden"  class="form-control" name="SER_IDPEDIDO" value="<%=id_pedido %>" >
                                           <input type="hidden" class="form-control"  name="SER_ORDPROD" value="<%=id_ordenproduccion %>" >
                                           <input type="hidden" class="form-control"  name="SER_IDCLI" value="<%=id_cliente %>" >
                                     
                                           <input type="hidden" class="form-control"  name="SER_IDEMP" value="${id_empleado}" >
                                           
                                           
                                           
                                        <div>
                                             <div class="col-lg-12">       
                                               <input class="btn btn-primary" type="submit" value="Registrar">
                                             </div>        
                                        </div>
                                            
                                        </form>
                                    </div>
                                     
                                            
                                  
                                     
                                        
                                        

                              <br>
                  <!--   </form>  --> 
           
            </div>
                                                    
         

            <br>
           
                <div class="card-body">
                             <table id="example1" class="table table-bordered table-striped">
                                 <thead>
                                     <tr>
                                         <th class="text-center" >ID-PED-CLIENTE</th>
                                         <th class="text-center" >ORD-PROD</th>
                                         <th class="text-center" >NOMB-CLIENTE</th>
                                         <th class="text-center" >APELLIDO-CLIENTE</th>
                                         <th class="text-center" >DOCUMENTO</th>
                                         <th class="text-center" >EMPLEADO</th>
                                         <th class="text-center" >FECHA EMISION</th>
                                         <th class="text-center" >FECHA ENTREGA</th>
                                         <th class="text-center" >ESTADO</th>
                                         <th class="text-center" >Acciones</th>
                                     </tr>
                                 </thead>
                                
                                 <tbody>
                                    <tr>
                                           
                                        </tr>
                                      

                                     
                                 </tbody>
                             </table>
                                    <div class="row" style="padding-top: 6%;">
                <!--volver a la pagina detlle insumo-->   <a href="http://localhost:8080/Admin-JESAR/admin/OrdenCompraInsumo.jsp" class="form-control btn btn-dark" style="width: auto; margin-left: auto" >Salir</a>
                                   </div>
                         </div>
     
            <!-- /.card-body -->
        </div>
        <!-- /.card -->     

<%@ include file="templates/footer.jsp"%>
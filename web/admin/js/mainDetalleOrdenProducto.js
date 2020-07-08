$(document).ready(function () {
    var detalleProduccion_id, accion;
    accion = "Listar";

    ListaProductos2 = $('#ListaProductos2').DataTable({
        responsive: true,
        "ajax": {
            "url": "/Admin-JESAR/ControladorProducto",
            "method": 'POST', //usamos el metodo POST
            "data": {action: accion}, //enviamos opcion 4 para que haga un SELECT
            "dataSrc": "datos"
        },
        "columns": [
            {"data": "cod_modeloproducto"},
            {"data": "id_serie"},
            {"data": "descripcion_serie"},
            {"data": "id_color"},
            {"data": "descripcion_color"},
            {"data": "stock_producto"},
            {"data": "stock_cortado"},
            {"data": "stock_aparado"},
            {"data": "stock_armado"},
            {"defaultContent": "<div class='text-center'><div class='btn-group'><button class='btn btn-primary btn-sm btnAgregarProducto'><i class='material-icons'>Agregar</i></button></div></div>"}
        ]
    });
    var fila; //captura la fila, para editar o eliminar
//submit para el Alta y Actualización

    $("#btnAgregar").click(function () {
        $(".modal-header").css("background-color", "#17a2b8");
        $(".modal-header").css("color", "white");
        $(".modal-title").text("Agregar Productos");
        $('#agregarProductosOrden').modal('show');
    });


/*function listarProformas(){
  var datos = {
   "dni" : dni,
   "cargo" : cargo
  }  
  ajax: {
      url: "controlador",
      type: "POST",
      datatype: "json"
      success: function(data){
         $("#tablaPersonal").Datatable();
        var fila = '';
        for(var i=0; data.lenght; i++){
          fila += "<tr>"+
                    "<td>"+data['nroProforma']+"</td>"+
                    "<td>"+data['dni']+"</td>"+
                    "<td>"+data['concepto']+"</td>"+
                    "<td>"+data['Dirección']+"</td>"+
                    "<td>"+data['Email']+"</td>"+
                    "<td>"+data['direccion']+"</td>"+
                  "</tr>";
        }
        $("#tablaPersonal").append(fila);
      }
   }
}*/














    $(document).on("click", ".btnAgregarProducto", function () {
        accion = "AgregarProductoAlDetalle"
        fila = $(this);
        producto_id = $(this).closest('tr').find('td:eq(0)').text();
        id_serie = $(this).closest('tr').find('td:eq(1)').text();
        serie = $(this).closest('tr').find('td:eq(2)').text();
        id_color = $(this).closest('tr').find('td:eq(3)').text();
        color = $(this).closest('tr').find('td:eq(4)').text();
        cant_prod = 1;
       
        tablaDetalleOrdenProducto = $('#listDetalleProductos').DataTable({
        responsive: true,
        "ajax": {
            url: "/Admin-JESAR/ControladorDetalleOrdenProducto",
            type: "POST",
            datatype: "json",
            data: {action: accion, producto_id: producto_id, id_serie: id_serie, serie: serie, id_color:id_color,color:color ,cant_prod:cant_prod},
            "dataSrc": "datos"
        },
        "columns": [
            {"data": "cod_modeloproducto"},
            {"data": "id_serie"},
            {"data": "descripcion_serie"},
            {"data": "id_color"},
            {"data": "descripcion_serie"},
            {"data": "cantidad"},
            {"defaultContent": "<div class='text-center'><div class='btn-group'><button class='btn btn-secondary btn-sm btnDetalleOrdenProductoEditar'><i class='material-icons'>Editar</i></button><button class='btn btn-info btn-sm btnDetalleOrdenProductoEliminar'><i class='material-icons'>Eliminar</i></button></div></div>"}
        ]
    });


        $('#agregarProductosOrden').modal('hide');
        /*fila = $(this);
         producto_id = $(this).closest('tr').find('td:eq(0)').text();
         accion = "Eliminar"; //eliminar        
         var respuesta = confirm("¿Está seguro de borrar el registro " + producto_id + "?");
         if (respuesta) {
         $.ajax({
         url: "/Admin-JESAR/ControladorProducto",
         type: "POST",
         datatype: "json",
         data: {action: accion, producto_id: producto_id, serie_id:serie_id, color_id:color_id},
         success: function () {
         alert('Lograste eliminar');
         tablaProductos.row(fila.parents('tr')).remove().draw();
         }
         });
         }*/
    });

});
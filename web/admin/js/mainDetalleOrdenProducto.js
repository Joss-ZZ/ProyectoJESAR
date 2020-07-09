$(document).ready(function () {
    var detalleProduccion_id, accion;

    tablaDetalleOrdenProducto = $('#listDetalleProductos').DataTable({
        "columnDefs": [{
                "targets": -1,
                "data": null,
                "defaultContent": "<div class='text-center'><div class='btn-group'><button class='btn btn-primary btnEditar'>Editar</button><button class='btn btn-danger btnBorrar'>Borrar</button></div></div>"
            }]
    });

    ListaProductos2 = $('#ListaProductos2').DataTable({
        responsive: true,
        "ajax": {
            "url": "/Admin-JESAR/ControladorProducto",
            "method": 'POST', //usamos el metodo POST
            "data": {action: "Listar"}, //enviamos opcion 4 para que haga un SELECT
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

    $(document).on("click", ".btnAgregarProducto", function () {
        accion = "Nuevo";
        fila = $(this);
        detalleProduccion_id = document.getElementById('id_orden_produccion').value;
        producto_id = $(this).closest('tr').find('td:eq(0)').text();
        id_serie = $(this).closest('tr').find('td:eq(1)').text();
        serie = $(this).closest('tr').find('td:eq(2)').text();
        id_color = $(this).closest('tr').find('td:eq(3)').text();
        color = $(this).closest('tr').find('td:eq(4)').text();
        cant_prod = 0;

        $.ajax({
            url: "/Admin-JESAR/ControladorDetalleOrdenProducto",
            type: "POST",
            datatype: "json",
            data: {action: accion, detalleProduccion_id: detalleProduccion_id, producto_id: producto_id, id_serie: id_serie, serie: serie, id_color: id_color, color: color, cant_prod: cant_prod},
            success: function (data) {
                d = data.datos[0].detalleProduccion_id;
                producto_id = data.datos[0].producto_id;
                alert(producto_id);
                id_serie = data.datos[0].id_serie;
                serie = data.datos[0].serie;
                id_color = data.datos[0].id_color;
                color = data.datos[0].color;
                cant_prod = data.datos[0].cant_prod;
                tablaDetalleOrdenProducto.row.add([d, producto_id, id_serie, serie, id_color, color, cant_prod]).draw();

            }
        });
    });

});

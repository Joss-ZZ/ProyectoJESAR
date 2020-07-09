$(document).ready(function () {
    var detalleProduccion_id, accion, accion2;

    tablaDetalleOrdenProducto = $('#listDetalleProductos').DataTable({
        "columnDefs": [{
                "targets": -1,
                "data": null,
                "defaultContent": "<div class='text-center'><div class='btn-group'><button class='btn btn-primary btnEditarCantidad'>Editar Cantidad</button><button class='btn btn-danger btnBorrarLista'>Borrar de Lista</button></div></div>"
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
    var fila2;
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
                id_serie = data.datos[0].id_serie;
                serie = data.datos[0].serie;
                id_color = data.datos[0].id_color;
                color = data.datos[0].color;
                cant_prod = data.datos[0].cant_prod;
                tablaDetalleOrdenProducto.row.add([d, producto_id, id_serie, serie, id_color, color, cant_prod]).draw();
            }
        });


    });

    $(document).on("click", ".btnEditarCantidad", function () {
        accion2 = "Editar";
        fila2 = $(this).closest("tr");
        id_orden_prod = parseInt(fila2.find('td:eq(0)').text()); //capturo el ID
        cod_modeloproducto = fila2.find('td:eq(1)').text();
        id_serie = parseInt(fila2.find('td:eq(2)').text()); //capturo el ID
        id_color = parseInt(fila2.find('td:eq(4)').text());
        cantidad = 0;

        $("#id_orden_prod").val(id_orden_prod);
        $("#cod_modeloproducto").val(cod_modeloproducto);
        $("#id_serie").val(id_serie);
        $("#id_color").val(id_color);
        $("#cantidad").val(cantidad);

        $(".modal-header").css("background-color", "#007bff");
        $(".modal-header").css("color", "white");
        $(".modal-title").text("Editar Cantidad");
        $('#editarCantidad').modal('show');
    });
    // Para seleccionar la FILAAAAA !!
    $('#listDetalleProductos').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        } else {
            tablaDetalleOrdenProducto.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    });

    $('#formEditarCantidad').submit(function (e) {
        e.preventDefault(); //evita el comportambiento normal del submit, es decir, recarga total de la página
        id_orden_prod = $.trim($('#id_orden_prod').val());
        cod_modeloproducto = $.trim($('#cod_modeloproducto').val());
        id_serie = $.trim($('#id_serie').val());
        id_color = $.trim($('#id_color').val());
        cant_prod = $.trim($('#cantidad').val());

        $.ajax({
            url: "/Admin-JESAR/ControladorDetalleOrdenProducto",
            type: "POST",
            datatype: "json",
            data: {action: accion2, id_orden_prod: id_orden_prod, cod_modeloproducto: cod_modeloproducto, id_serie: id_serie, serie: serie, id_color: id_color, color: color, cant_prod: cant_prod},
            success: function (data) {
                d = data.datos[0].id_orden_prod;
                producto_id = data.datos[0].cod_modeloproducto;
                id_serie = data.datos[0].id_serie;
                serie = data.datos[0].serie;
                id_color = data.datos[0].id_color;
                color = data.datos[0].color;
                cant_prod = data.datos[0].cant_prod;
                var a = [d, producto_id, id_serie, serie, id_color, color, cant_prod];
                //tablaDetalleOrdenProducto.row(2).data(a);
                tablaDetalleOrdenProducto.row('.selected').data(a).draw();
            }
        });
        $('#editarCantidad').modal('hide');
    });
    
    $(document).on("click", ".btnBorrarLista", function () {
        accion2 = "Eliminar";
        id_orden_prod = parseInt($(this).closest('tr').find('td:eq(0)').text());
        cod_modeloproducto = $(this).closest('tr').find('td:eq(1)').text();
        id_serie = parseInt($(this).closest('tr').find('td:eq(2)').text());
        id_color = parseInt($(this).closest('tr').find('td:eq(4)').text());
        cant_prod = 0;

        var respuesta = confirm("¿Está seguro de borrar el producto " + cod_modeloproducto + " de la orden  " + id_orden_prod + " ?");
        if (respuesta) {
            $.ajax({
                url: "/Admin-JESAR/ControladorDetalleOrdenProducto",
                type: "POST",
                datatype: "json",
                data: {action: accion2, id_orden_prod: id_orden_prod, cod_modeloproducto: cod_modeloproducto, id_serie: id_serie, id_color: id_color, cant_prod: cant_prod},
                success: function () {
                    alert('Lograste eliminar');
                    tablaDetalleOrdenProducto.row('.selected').remove().draw(false);
                }
            });
        }
    });

});

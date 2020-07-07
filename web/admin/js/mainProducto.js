$(document).ready(function () {
    var producto_id,serie_id,color_id, accion;
    accion = "Listar";

    tablaProductos = $('#ListaProductos1').DataTable({  
        responsive:true,
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
            {"defaultContent": "<div class='text-center'><div class='btn-group'><button class='btn btn-primary btn-sm btnEditarProducto'><i class='material-icons'>Editar</i></button><button class='btn btn-danger btn-sm btnBorrarProducto'><i class='material-icons'>Eliminar</i></button></div></div>"}
        ],    
        "aoColumnDefs": [ { "sClass": "hide_me", "aTargets": [1,3] } ]
    });
    
    var fila; //captura la fila, para editar o eliminar
//submit para el Alta y Actualización
    $('#formProductos').submit(function (e) {
        e.preventDefault(); //evita el comportambiento normal del submit, es decir, recarga total de la página
        cod_modeloproducto = $.trim($('#cod_modeloproducto').val());
        id_serie = $.trim($('#id_serie').val());
        id_color = $.trim($('#id_color').val());
        stock_producto = $.trim($('#stock_producto').val());
        stock_cortado = $.trim($('#stock_cortado').val());
        stock_aparado = $.trim($('#stock_aparado').val());
        stock_armado = $.trim($('#stock_armado').val());
        $.ajax({
            url: "/Admin-JESAR/ControladorProducto",
            type: "POST",
            datatype: "json",
            data: {action: accion, cod_modeloproducto: cod_modeloproducto, id_serie: id_serie, id_color: id_color, stock_producto: stock_producto, stock_cortado: stock_cortado, stock_aparado: stock_aparado, stock_armado: stock_armado},
            success: function (data) {
                tablaProductos.ajax.reload(null, false);
            }
        });
        $('#ProductosCRUD').modal('hide');
    });



//para limpiar los campos antes de dar de Alta una Persona
    $("#btnNuevoProducto").click(function () {
        accion = "Nuevo"; //alta           
        producto_id = null;
        $("#formProductos").trigger("reset");
        $(".modal-header").css("background-color", "#17a2b8");
        $(".modal-header").css("color", "white");
        $(".modal-title").text("Nuevo Producto");
        $('#ProductosCRUD').modal('show');
        
        document.getElementById('cod_modeloproducto').disabled=false;
        document.getElementById('id_serie').disabled=false;
        document.getElementById('id_color').disabled=false;
    });

//Editar        
    $(document).on("click", ".btnEditarProducto", function () {
        accion = "Editar";//editar
        fila = $(this).closest("tr");
      
        producto_id = fila.find('td:eq(0)').text(); //capturo el ID 
        serie_id = parseInt(fila.find('td:eq(1)').text());
        color_id = parseInt(fila.find('td:eq(3)').text());
        stock_producto = parseFloat(fila.find('td:eq(5)').text());
        stock_cortado = parseFloat(fila.find('td:eq(6)').text());
        stock_aparado = parseFloat(fila.find('td:eq(7)').text());
        stock_armado = parseFloat(fila.find('td:eq(8)').text());

        $("#cod_modeloproducto").val(producto_id);
        $("#id_serie").val(serie_id);
        $("#id_color").val(color_id);
        $("#stock_producto").val(stock_producto);
        $("#stock_cortado").val(stock_cortado);
        $("#stock_aparado").val(stock_aparado);
        $("#stock_armado").val(stock_armado);
        $(".modal-header").css("background-color", "#007bff");
        $(".modal-header").css("color", "white");
        $(".modal-title").text("Editar Producto");
        $('#ProductosCRUD').modal('show');
        
        document.getElementById('cod_modeloproducto').disabled=true;
        document.getElementById('id_serie').disabled=true;
        document.getElementById('id_color').disabled=true;
    });

//Borrar
    $(document).on("click", ".btnBorrarProducto", function () {
        fila = $(this);
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
        }
    });
});


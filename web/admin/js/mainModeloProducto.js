$(document).ready(function () {
    var modeloProducto_id, accion;
    accion = "Listar";

    tablaModeloProductos = $('#ListaModeloProductos').DataTable({  
        responsive:true,
        "ajax": {
            "url": "/Admin-JESAR/ControladorModeloProductos",
            "method": 'POST', //usamos el metodo POST
            "data": {action: accion}, //enviamos opcion 4 para que haga un SELECT
            "dataSrc": "datos"
        },
        "columns": [
            {"data": "cod_modeloproducto"},
            {"data": "id_categoria"},
            {"data": "des_categoria"},
            {"data": "nombre"},
            {"data": "p_venta"},
            {"data": "p_cortado"},
            {"data": "p_aparado"},
            {"data": "p_armado"},
            {"data": "p_alistado"},
            {"defaultContent": "<div class='text-center'><div class='btn-group'><button class='btn btn-primary btn-sm btnEditarModeloProducto'><i class='material-icons'>Editar</i></button><button class='btn btn-danger btn-sm btnBorrarModeloProducto'><i class='material-icons'>Eliminar</i></button></div></div>"}
        ],    
        "aoColumnDefs": [ { "sClass": "hide_me", "aTargets": [1] } ]
    });
    
    var fila; //captura la fila, para editar o eliminar
//submit para el Alta y Actualización
    $('#formModeloProductos').submit(function (e) {
        e.preventDefault(); //evita el comportambiento normal del submit, es decir, recarga total de la página
        cod_modeloproducto = $.trim($('#cod_modeloproducto').val());
        id_categoria = $.trim($('#id_categoria').val());
        nombre = $.trim($('#nombre').val());
        p_venta = $.trim($('#p_venta').val());
        p_cortado = $.trim($('#p_cortado').val());
        p_aparado = $.trim($('#p_aparado').val());
        p_armado = $.trim($('#p_armado').val());
        p_alistado = $.trim($('#p_alistado').val());
        $.ajax({
            url: "/Admin-JESAR/ControladorModeloProductos",
            type: "POST",
            datatype: "json",
            data: {action: accion, cod_modeloproducto: cod_modeloproducto, id_categoria: id_categoria, nombre: nombre, p_venta: p_venta, p_cortado: p_cortado, p_aparado: p_aparado, p_armado: p_armado, p_alistado: p_alistado},
            success: function (data) {
                tablaModeloProductos.ajax.reload(null, false);
            }
        });
        $('#ModeloProductosCRUD').modal('hide');
    });



//para limpiar los campos antes de dar de Alta una Persona
    $("#btnNuevoModeloProducto").click(function () {
        accion = "Nuevo"; //alta           
        modeloProducto_id = null;
        $("#formModeloProductos").trigger("reset");
        $(".modal-header").css("background-color", "#17a2b8");
        $(".modal-header").css("color", "white");
        $(".modal-title").text("Nuevo Modelo Producto");
        $('#ModeloProductosCRUD').modal('show');
    });

//Editar        

    $(document).on("click", ".btnEditarModeloProducto", function () {
        accion = "Editar";//editar
        fila = $(this).closest("tr");
      
        modeloProducto_id = fila.find('td:eq(0)').text(); //capturo el ID 
        id_categoria = parseInt(fila.find('td:eq(1)').text());
        nombre = fila.find('td:eq(3)').text();
        p_venta = parseFloat(fila.find('td:eq(4)').text());
        p_cortado = parseFloat(fila.find('td:eq(5)').text());
        p_aparado = parseFloat(fila.find('td:eq(6)').text());
        p_armado = parseFloat(fila.find('td:eq(7)').text());
        p_alistado = parseFloat(fila.find('td:eq(8)').text());

        $("#cod_modeloproducto").val(modeloProducto_id);
        $("#id_categoria").val(id_categoria);
        $("#nombre").val(nombre);
        $("#p_venta").val(p_venta);
        $("#p_cortado").val(p_cortado);
        $("#p_aparado").val(p_aparado);
        $("#p_armado").val(p_armado);
        $("#p_alistado").val(p_alistado)
        $(".modal-header").css("background-color", "#007bff");
        $(".modal-header").css("color", "white");
        $(".modal-title").text("Editar Modelo Producto");
        $('#ModeloProductosCRUD').modal('show');
    });

//Borrar
    $(document).on("click", ".btnBorrarModeloProducto", function () {
        fila = $(this);
        modeloProducto_id = $(this).closest('tr').find('td:eq(0)').text();
        accion = "Eliminar"; //eliminar        
        var respuesta = confirm("¿Está seguro de borrar el registro " + modeloProducto_id + "?");
        if (respuesta) {
            $.ajax({
                url: "/Admin-JESAR/ControladorModeloProductos",
                type: "POST",
                datatype: "json",
                data: {action: accion, modeloProducto_id: modeloProducto_id},
                success: function () {
                    alert('Lograste eliminar');
                    tablaModeloProductos.row(fila.parents('tr')).remove().draw();
                }
            });
        }
    });
});


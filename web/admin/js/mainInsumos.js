$(document).ready(function () {
    var insumo_id, accion;
    accion = "Listar";

    tablaInsumos = $('#ListaInsumos').DataTable({  
        responsive:true,
        "ajax": {
            "url": "/Admin-JESAR/ControladorInsumos",
            "method": 'POST', //usamos el metodo POST
            "data": {action: accion}, //enviamos opcion 4 para que haga un SELECT
            "dataSrc": "datos"
        },
        "columns": [
            {"data": "id"},
            {"data": "nombre_cat"},
            {"data": "nombre_insumo"},
            {"data": "cant"},
            {"data": "nombre_und"},
            {"defaultContent": "<div class='text-center'><div class='btn-group'><button class='btn btn-primary btn-sm btnEditarInsumo'><i class='material-icons'>Editar</i></button><button class='btn btn-danger btn-sm btnBorrarInsumo'><i class='material-icons'>Eliminar</i></button></div></div>"}
        ]    
    });

    var fila; //captura la fila, para editar o eliminar
//submit para el Alta y Actualización
    $('#formInsumos').submit(function (e) {
        e.preventDefault(); //evita el comportambiento normal del submit, es decir, recarga total de la página
        id = $.trim($('#insumo_id').val());
        categoria = $.trim($('#categoria').val());
        nombre = $.trim($('#nombre').val());
        cantidad = $.trim($('#cantidad').val());
        unidad = $.trim($('#unidad').val());
        $.ajax({
            url: "/Admin-JESAR/ControladorInsumos",
            type: "POST",
            datatype: "json",
            data: {action: accion, id: id, categoria: categoria, nombre: nombre, cantidad: cantidad, unidad: unidad},
            success: function (data) {
                tablaInsumos.ajax.reload(null, false);
            }
        });
        $('#insumosCrear').modal('hide');
    });



//para limpiar los campos antes de dar de Alta una Persona
    $("#btnNuevoInsumo").click(function () {
        accion = "Nuevo"; //alta           
        insumo_id = null;
        $("#formInsumos").trigger("reset");
        $(".modal-header").css("background-color", "#17a2b8");
        $(".modal-header").css("color", "white");
        $(".modal-title").text("Nuevo Insumo");
        $('#insumosCrear').modal('show');
    });

//Editar        
    $(document).on("click", ".btnEditarInsumo", function () {
        accion = "Editar";//editar
        fila = $(this).closest("tr");
      
        insumo_id = parseInt(fila.find('td:eq(0)').text()); //capturo el ID 
        id_categoria = fila.find
        categoria = fila.find('td:eq(1)').text();
        nombre = fila.find('td:eq(2)').text();
        cantidad = parseFloat(fila.find('td:eq(3)').text());
        unidad = fila.find('td:eq(4)').text();
        alert(cantidad);

        $("#insumo_id").val(insumo_id);
        $("#categoria").val(categoria);
        $("#nombre").val(nombre);
        $("#cantidad").val(cantidad);
        $("#unidad").val(unidad);
        $(".modal-header").css("background-color", "#007bff");
        $(".modal-header").css("color", "white");
        $(".modal-title").text("Editar Insumo");
        $('#insumosCrear').modal('show');
    });

//Borrar
    $(document).on("click", ".btnBorrarInsumo", function () {
        fila = $(this);
        insumo_id = parseInt($(this).closest('tr').find('td:eq(0)').text());
        accion = "Eliminar"; //eliminar        
        var respuesta = confirm("¿Está seguro de borrar el registro " + insumo_id + "?");
        if (respuesta) {
            $.ajax({
                url: "/Admin-JESAR/ControladorInsumos",
                type: "POST",
                datatype: "json",
                data: {action: accion, insumo_id: insumo_id},
                success: function () {
                    alert('Lograste eliminar');
                    tablaInsumos.row(fila.parents('tr')).remove().draw();
                }
            });
        }
    });

});


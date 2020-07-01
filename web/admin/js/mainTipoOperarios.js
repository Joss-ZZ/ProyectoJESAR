$(document).ready(function () {
    var tipoOperario_id, accion;
    accion = "Listar";

    tablaTipoOperarios = $('#listTipoOperarios').DataTable({
        "ajax": {
            "url": "/Admin-JESAR/ControladorTipoOperarios",
            "method": 'POST', //usamos el metControladorUnidadInsumosodo POST
            "data": {action: accion}, //enviamos opcion 4 para que haga un SELECT
            "dataSrc": "datos"
        },
        "columns": [
            {"data": "id"},
            {"data": "descripcion"},
            {"defaultContent": "<div class='text-center'><div class='btn-group'><button class='btn btn-primary btn-sm btnEditarTipoOperario'><i class='material-icons'>edit</i></button><button class='btn btn-danger btn-sm btnBorrarTipoOperario'><i class='material-icons'>delete</i></button></div></div>"}
        ]
    });

    var fila; //captura la fila, para editar o eliminar
//submit para el Alta y Actualización
    $('#formTipoOperarios').submit(function (e) {
        e.preventDefault(); //evita el comportambiento normal del submit, es decir, recarga total de la página
        id = $.trim($('#id').val());
        descripcion = $.trim($('#descripcion').val());
        
        $.ajax({
            url: "/Admin-JESAR/ControladorTipoOperarios",
            type: "POST",
            datatype: "json",
            data: {action: accion, id: id, descripcion: descripcion},
            success: function (data) {
                tablaTipoOperarios.ajax.reload(null, false);
            }
        });
        $('#TipoOperariosCRUD').modal('hide');
    });

//para limpiar los campos antes de dar de Alta una Persona
    $("#btnNuevo-TipoOperario").click(function () {
        accion = "Nuevo"; //alta           
        tipoOperario_id = null;
        $("#formTipoOperarios").trigger("reset");
        $(".modal-header").css("background-color", "#17a2b8");
        $(".modal-header").css("color", "white");
        $(".modal-title").text("Alta de Color");
        $('#TipoOperariosCRUD').modal('show');
    });

//Editar        
    $(document).on("click", ".btnEditarTipoOperario", function () {
        accion = "Editar";//editar
        fila = $(this).closest("tr");
        tipoOperario_id = parseInt(fila.find('td:eq(0)').text()); //capturo el ID
        
        descripcion = fila.find('td:eq(1)').text();
  
        $("#id").val(tipoOperario_id);
        $("#descripcion").val(descripcion);
        
        $(".modal-header").css("background-color", "#007bff");
        $(".modal-header").css("color", "white");
        $(".modal-title").text("Editar Color");
        $('#TipoOperariosCRUD').modal('show');
    });

//Borrar
    $(document).on("click", ".btnBorrarTipoOperario", function () {
        fila = $(this);
        tipoOperario_id = parseInt($(this).closest('tr').find('td:eq(0)').text());
        accion = "Eliminar"; //eliminar        
        var respuesta = confirm("¿Está seguro de borrar el registro " + tipoOperario_id + "?");
        if (respuesta) {
            $.ajax({
                url: "/Admin-JESAR/ControladorTipoOperarios",
                type: "POST",
                datatype: "json",
                data: {action: accion, tipoOperario_id: tipoOperario_id},
                success: function () {
                    alert('Lograste eliminar');
                    tablaTipoOperarios.row(fila.parents('tr')).remove().draw();
                }
            });
        }
    });

});
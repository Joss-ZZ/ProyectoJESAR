$(document).ready(function () {
    var tipoComprobante_id, accion;
    accion = "Listar";

    tablaTipoComprobantes = $('#listTipoComprobantes').DataTable({
        "ajax": {
            "url": "/Admin-JESAR/ControladorTipoComprobante",
            "method": 'POST', //usamos el metControladorUnidadInsumosodo POST
            "data": {action: accion}, //enviamos opcion 4 para que haga un SELECT
            "dataSrc": "datos"
        },
        "columns": [
            {"data": "id"},
            {"data": "descripcion"},
            {"defaultContent": "<div class='text-center'><div class='btn-group'><button class='btn btn-primary btn-sm btnEditarTipoComprobante'><i class='material-icons'>edit</i></button><button class='btn btn-danger btn-sm btnBorrarTipoComprobante'><i class='material-icons'>delete</i></button></div></div>"}
        ]
    });

    var fila; //captura la fila, para editar o eliminar
//submit para el Alta y Actualización
    $('#formTipoComprobantes').submit(function (e) {
        e.preventDefault(); //evita el comportambiento normal del submit, es decir, recarga total de la página
        id = $.trim($('#id').val());
        descripcion = $.trim($('#descripcion').val());
        
        $.ajax({
            url: "/Admin-JESAR/ControladorTipoComprobante",
            type: "POST",
            datatype: "json",
            data: {action: accion, id: id, descripcion: descripcion},
            success: function (data) {
                tablaTipoComprobantes.ajax.reload(null, false);
            }
        });
        $('#TipoComprobantesCRUD').modal('hide');
    });

//para limpiar los campos antes de dar de Alta una Persona
    $("#btnNuevo-TipoComprobantes").click(function () {
        accion = "Nuevo"; //alta           
        tipoComprobante_id = null;
        $("#formTipoComprobantes").trigger("reset");
        $(".modal-header").css("background-color", "#17a2b8");
        $(".modal-header").css("color", "white");
        $(".modal-title").text("Nuevo Tipo de Comprobante");
        $('#TipoComprobantesCRUD').modal('show');
    });

//Editar        
    $(document).on("click", ".btnEditarTipoComprobante", function () {
        accion = "Editar";//editar
        fila = $(this).closest("tr");
        tipoComprobante_id = parseInt(fila.find('td:eq(0)').text()); //capturo el ID
        
        descripcion = fila.find('td:eq(1)').text();
  
        $("#id").val(tipoComprobante_id);
        $("#descripcion").val(descripcion);
        
        $(".modal-header").css("background-color", "#007bff");
        $(".modal-header").css("color", "white");
        $(".modal-title").text("Editar Tipo de Comprobante");
        $('#TipoComprobantesCRUD').modal('show');
    });

//Borrar
    $(document).on("click", ".btnBorrarTipoComprobante", function () {
        fila = $(this);
        tipoComprobante_id = parseInt($(this).closest('tr').find('td:eq(0)').text());
        accion = "Eliminar"; //eliminar        
        var respuesta = confirm("¿Está seguro de borrar el registro " + tipoComprobante_id + "?");
        if (respuesta) {
            $.ajax({
                url: "/Admin-JESAR/ControladorTipoComprobante",
                type: "POST",
                datatype: "json",
                data: {action: accion, tipoComprobante_id: tipoComprobante_id},
                success: function () {
                    alert('Lograste eliminar');
                    tablaTipoComprobantes.row(fila.parents('tr')).remove().draw();
                }
            });
        }
    });

});
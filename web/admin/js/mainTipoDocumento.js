$(document).ready(function () {
    var tipoDocumento_id, accion;
    accion = "Listar";

    tablaTipoDocumentos = $('#listTipoDocumentos').DataTable({
        "ajax": {
            "url": "/Admin-JESAR/ControladorTipoDocumento",
            "method": 'POST', //usamos el metControladorUnidadInsumosodo POST
            "data": {action: accion}, //enviamos opcion 4 para que haga un SELECT
            "dataSrc": "datos"
        },
        "columns": [
            {"data": "id"},
            {"data": "descripcion"},
            {"data": "cant_digitos"},
            {"defaultContent": "<div class='text-center'><div class='btn-group'><button class='btn btn-primary btn-sm btnEditarTipoDocumento'><i class='material-icons'>edit</i></button><button class='btn btn-danger btn-sm btnBorrarTipoDocumento'><i class='material-icons'>delete</i></button></div></div>"}
        ]
    });

    var fila; //captura la fila, para editar o eliminar
//submit para el Alta y Actualización
    $('#formTipoDocumentos').submit(function (e) {
        e.preventDefault(); //evita el comportambiento normal del submit, es decir, recarga total de la página
        id = $.trim($('#id').val());
        descripcion = $.trim($('#descripcion').val());
        cant_digitos = $.trim($('#cant_digitos').val());
        
        $.ajax({
            url: "/Admin-JESAR/ControladorTipoDocumento",
            type: "POST",
            datatype: "json",
            data: {action: accion, id: id, descripcion: descripcion, cant_digitos:cant_digitos},
            success: function (data) {
                tablaTipoDocumentos.ajax.reload(null, false);
            }
        });
        $('#TipoDocumentosCRUD').modal('hide');
    });

//para limpiar los campos antes de dar de Alta una Persona
    $("#btnNuevo-TipoDocumento").click(function () {
        accion = "Nuevo"; //alta           
        tipoDocumento_id = null;
        $("#formTipoDocumentos").trigger("reset");
        $(".modal-header").css("background-color", "#17a2b8");
        $(".modal-header").css("color", "white");
        $(".modal-title").text("Nuevo Tipo Documento");
        $('#TipoDocumentosCRUD').modal('show');
    });

//Editar        
    $(document).on("click", ".btnEditarTipoDocumento", function () {
        accion = "Editar";//editar
        fila = $(this).closest("tr");
        tipoDocumento_id = parseInt(fila.find('td:eq(0)').text()); //capturo el ID
        
        descripcion = fila.find('td:eq(1)').text();
        cant_digitos = fila.find('td:eq(2)').text();
  
        $("#id").val(tipoDocumento_id);
        $("#descripcion").val(descripcion);
        $("#cant_digitos").val(cant_digitos);
        
        $(".modal-header").css("background-color", "#007bff");
        $(".modal-header").css("color", "white");
        $(".modal-title").text("Editar Tipo Documento");
        $('#TipoDocumentosCRUD').modal('show');
    });

//Borrar
    $(document).on("click", ".btnBorrarTipoDocumento", function () {
        fila = $(this);
        tipoDocumento_id = parseInt($(this).closest('tr').find('td:eq(0)').text());
        accion = "Eliminar"; //eliminar        
        var respuesta = confirm("¿Está seguro de borrar el registro " + tipoDocumento_id + "?");
        if (respuesta) {
            $.ajax({
                url: "/Admin-JESAR/ControladorTipoDocumento",
                type: "POST",
                datatype: "json",
                data: {action: accion, tipoDocumento_id: tipoDocumento_id},
                success: function () {
                    alert('Lograste eliminar');
                    tablaTipoDocumentos.row(fila.parents('tr')).remove().draw();
                }
            });
        }
    });

});
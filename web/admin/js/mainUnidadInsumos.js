$(document).ready(function () {
    var unidadInsumos_id, accion;
    accion = "Listar";

    tablaUnidadInsumos = $('#listUnidadInsumos').DataTable({
        "ajax": {
            "url": "/Admin-JESAR/ControladorUnidadInsumos",
            "method": 'POST', //usamos el metodo POST
            "data": {action: accion}, //enviamos opcion 4 para que haga un SELECT
            "dataSrc": "datos"
        },
        "columns": [
            {"data": "id"},
            {"data": "descripcion"},
            {"defaultContent": "<div class='text-center'><div class='btn-group'><button class='btn btn-primary btn-sm btnEditarUnidad'><i class='material-icons'>edit</i></button><button class='btn btn-danger btn-sm btnBorrarUnidad'><i class='material-icons'>delete</i></button></div></div>"}
        ]
    });

    var fila; //captura la fila, para editar o eliminar
//submit para el Alta y Actualización
    $('#formUnidadInsumos').submit(function (e) {
        e.preventDefault(); //evita el comportambiento normal del submit, es decir, recarga total de la página
        id = $.trim($('#id').val());
        descripcion = $.trim($('#descripcion').val());
        
        $.ajax({
            url: "/Admin-JESAR/ControladorUnidadInsumos",
            type: "POST",
            datatype: "json",
            data: {action: accion, id: id, descripcion: descripcion},
            success: function (data) {
                tablaUnidadInsumos.ajax.reload(null, false);
            }
        });
        $('#unidadInsumosCRUD').modal('hide');
    });

//para limpiar los campos antes de dar de Alta una Persona
    $("#btnNuevo-UnidadInsumos").click(function () {
        accion = "Nuevo"; //alta           
        unidadInsumos_id = null;
        $("#formUnidadInsumos").trigger("reset");
        $(".modal-header").css("background-color", "#17a2b8");
        $(".modal-header").css("color", "white");
        $(".modal-title").text("Alta de Color");
        $('#unidadInsumosCRUD').modal('show');
    });

//Editar        
    $(document).on("click", ".btnEditarUnidad", function () {
        accion = "Editar";//editar
        fila = $(this).closest("tr");
        unidadInsumos_id = parseInt(fila.find('td:eq(0)').text()); //capturo el ID
        
        descripcion = fila.find('td:eq(1)').text();
  
        $("#id").val(unidadInsumos_id);
        $("#descripcion").val(descripcion);
        
        $(".modal-header").css("background-color", "#007bff");
        $(".modal-header").css("color", "white");
        $(".modal-title").text("Editar Color");
        $('#unidadInsumosCRUD').modal('show');
    });

//Borrar
    $(document).on("click", ".btnBorrarUnidad", function () {
        fila = $(this);
        unidadInsumos_id = parseInt($(this).closest('tr').find('td:eq(0)').text());
        accion = "Eliminar"; //eliminar        
        var respuesta = confirm("¿Está seguro de borrar el registro " + unidadInsumos_id + "?");
        if (respuesta) {
            $.ajax({
                url: "/Admin-JESAR/ControladorUnidadInsumos",
                type: "POST",
                datatype: "json",
                data: {action: accion, unidadInsumos_id: unidadInsumos_id},
                success: function () {
                    alert('Lograste eliminar');
                    tablaUnidadInsumos.row(fila.parents('tr')).remove().draw();
                }
            });
        }
    });

});
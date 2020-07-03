$(document).ready(function () {
    var serie_id, accion;
    accion = "Listar";

    tablaSeries = $('#listSeries').DataTable({
        "ajax": {
            "url": "/Admin-JESAR/ControladorSeries",
            "method": 'POST', //usamos el metControladorUnidadInsumosodo POST
            "data": {action: accion}, //enviamos opcion 4 para que haga un SELECT
            "dataSrc": "datos"
        },
        "columns": [
            {"data": "id"},
            {"data": "descripcion"},
            {"defaultContent": "<div class='text-center'><div class='btn-group'><button class='btn btn-primary btn-sm btnEditarSerie'><i class='material-icons'>edit</i></button><button class='btn btn-danger btn-sm btnBorrarSerie'><i class='material-icons'>delete</i></button></div></div>"}
        ]
    });

    var fila; //captura la fila, para editar o eliminar
//submit para el Alta y Actualización
    $('#formSeries').submit(function (e) {
        e.preventDefault(); //evita el comportambiento normal del submit, es decir, recarga total de la página
        id = $.trim($('#id').val());
        descripcion = $.trim($('#descripcion').val());
        
        $.ajax({
            url: "/Admin-JESAR/ControladorSeries",
            type: "POST",
            datatype: "json",
            data: {action: accion, id: id, descripcion: descripcion},
            success: function (data) {
                tablaSeries.ajax.reload(null, false);
            }
        });
        $('#SeriesCRUD').modal('hide');
    });

//para limpiar los campos antes de dar de Alta una Persona
    $("#btnNuevo-Serie").click(function () {
        accion = "Nuevo"; //alta           
        serie_id = null;
        $("#formSeries").trigger("reset");
        $(".modal-header").css("background-color", "#17a2b8");
        $(".modal-header").css("color", "white");
        $(".modal-title").text("Alta de Color");
        $('#SeriesCRUD').modal('show');
    });

//Editar        
    $(document).on("click", ".btnEditarSerie", function () {
        accion = "Editar";//editar
        fila = $(this).closest("tr");
        serie_id = parseInt(fila.find('td:eq(0)').text()); //capturo el ID
        
        descripcion = fila.find('td:eq(1)').text();
  
        $("#id").val(serie_id);
        $("#descripcion").val(descripcion);
        
        $(".modal-header").css("background-color", "#007bff");
        $(".modal-header").css("color", "white");
        $(".modal-title").text("Editar Color");
        $('#SeriesCRUD').modal('show');
    });

//Borrar
    $(document).on("click", ".btnBorrarSerie", function () {
        fila = $(this);
        serie_id = parseInt($(this).closest('tr').find('td:eq(0)').text());
        accion = "Eliminar"; //eliminar        
        var respuesta = confirm("¿Está seguro de borrar el registro " + serie_id + "?");
        if (respuesta) {
            $.ajax({
                url: "/Admin-JESAR/ControladorSeries",
                type: "POST",
                datatype: "json",
                data: {action: accion, serie_id: serie_id},
                success: function () {
                    alert('Lograste eliminar');
                    tablaSeries.row(fila.parents('tr')).remove().draw();
                }
            });
        }
    });

});
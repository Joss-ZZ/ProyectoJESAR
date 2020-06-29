$(document).ready(function () {
    var color_id, accion;
    accion = "Listar";

    tablaColor = $('#listColor').DataTable({
        "ajax": {
            "url": "/Admin-JESAR/ControladorColor",
            "method": 'POST', //usamos el metodo POST
            "data": {action: accion}, //enviamos opcion 4 para que haga un SELECT
            "dataSrc": "datos"
        },
        "columns": [
            {"data": "id"},
            {"data": "descripcion"},
            {"defaultContent": "<div class='text-center'><div class='btn-group'><button class='btn btn-primary btn-sm btnEditarColor'><i class='material-icons'>edit</i></button><button class='btn btn-danger btn-sm btnBorrarColor'><i class='material-icons'>delete</i></button></div></div>"}
        ]
    });

    var fila; //captura la fila, para editar o eliminar
//submit para el Alta y Actualización
    $('#formColor').submit(function (e) {
        e.preventDefault(); //evita el comportambiento normal del submit, es decir, recarga total de la página
        id = $.trim($('#id').val());
        descripcion = $.trim($('#descripcion').val());
        
        $.ajax({
            url: "/Admin-JESAR/ControladorColor",
            type: "POST",
            datatype: "json",
            data: {action: accion, id: id, descripcion: descripcion},
            success: function (data) {
                tablaColor.ajax.reload(null, false);
            }
        });
        $('#colorCRUD').modal('hide');
    });

//para limpiar los campos antes de dar de Alta una Persona
    $("#btnNuevo-color").click(function () {
        accion = "Nuevo"; //alta           
        color_id = null;
        $("#formColor").trigger("reset");
        $(".modal-header").css("background-color", "#17a2b8");
        $(".modal-header").css("color", "white");
        $(".modal-title").text("Alta de Color");
        $('#colorCRUD').modal('show');
    });

//Editar        
    $(document).on("click", ".btnEditarColor", function () {
        accion = "Editar";//editar
        fila = $(this).closest("tr");
        color_id = parseInt(fila.find('td:eq(0)').text()); //capturo el ID
        
        descripcion = fila.find('td:eq(1)').text();
  
        $("#id").val(color_id);
        $("#descripcion").val(descripcion);
        
        $(".modal-header").css("background-color", "#007bff");
        $(".modal-header").css("color", "white");
        $(".modal-title").text("Editar Color");
        $('#colorCRUD').modal('show');
    });

//Borrar
    $(document).on("click", ".btnBorrarColor", function () {
        fila = $(this);
        color_id = parseInt($(this).closest('tr').find('td:eq(0)').text());
        accion = "Eliminar"; //eliminar        
        var respuesta = confirm("¿Está seguro de borrar el registro " + color_id + "?");
        if (respuesta) {
            $.ajax({
                url: "/Admin-JESAR/ControladorColor",
                type: "POST",
                datatype: "json",
                data: {action: accion, color_id: color_id},
                success: function () {
                    alert('Lograste eliminar');
                    tablaColor.row(fila.parents('tr')).remove().draw();
                }
            });
        }
    });

});
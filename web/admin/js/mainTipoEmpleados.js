$(document).ready(function () {
    var tipoEmpleado_id, accion;
    accion = "Listar";

    tablaTipoEmpleados = $('#listTipoEmpleados').DataTable({
        "ajax": {
            "url": "/Admin-JESAR/ControladorTipoEmpleados",
            "method": 'POST', //usamos el metControladorUnidadInsumosodo POST
            "data": {action: accion}, //enviamos opcion 4 para que haga un SELECT
            "dataSrc": "datos"
        },
        "columns": [
            {"data": "id"},
            {"data": "descripcion"},
            {"defaultContent": "<div class='text-center'><div class='btn-group'><button class='btn btn-primary btn-sm btnEditarTipoEmpleado'><i class='material-icons'>edit</i></button><button class='btn btn-danger btn-sm btnBorrarTipoEmpleado'><i class='material-icons'>delete</i></button></div></div>"}
        ]
    });

    var fila; //captura la fila, para editar o eliminar
//submit para el Alta y Actualización
    $('#formTipoEmpleados').submit(function (e) {
        e.preventDefault(); //evita el comportambiento normal del submit, es decir, recarga total de la página
        id = $.trim($('#id').val());
        descripcion = $.trim($('#descripcion').val());
        
        $.ajax({
            url: "/Admin-JESAR/ControladorTipoEmpleados",
            type: "POST",
            datatype: "json",
            data: {action: accion, id: id, descripcion: descripcion},
            success: function (data) {
                tablaTipoEmpleados.ajax.reload(null, false);
            }
        });
        $('#TipoEmpleadosCRUD').modal('hide');
    });

//para limpiar los campos antes de dar de Alta una Persona
    $("#btnNuevo-TipoEmpleado").click(function () {
        accion = "Nuevo"; //alta           
        tipoEmpleado_id = null;
        $("#formTipoEmpleados").trigger("reset");
        $(".modal-header").css("background-color", "#17a2b8");
        $(".modal-header").css("color", "white");
        $(".modal-title").text("Alta de Color");
        $('#TipoEmpleadosCRUD').modal('show');
    });

//Editar        
    $(document).on("click", ".btnEditarTipoEmpleado", function () {
        accion = "Editar";//editar
        fila = $(this).closest("tr");
        tipoEmpleado_id = parseInt(fila.find('td:eq(0)').text()); //capturo el ID
        
        descripcion = fila.find('td:eq(1)').text();
  
        $("#id").val(tipoEmpleado_id);
        $("#descripcion").val(descripcion);
        
        $(".modal-header").css("background-color", "#007bff");
        $(".modal-header").css("color", "white");
        $(".modal-title").text("Editar Color");
        $('#TipoEmpleadosCRUD').modal('show');
    });

//Borrar
    $(document).on("click", ".btnBorrarTipoEmpleado", function () {
        fila = $(this);
        tipoEmpleado_id = parseInt($(this).closest('tr').find('td:eq(0)').text());
        accion = "Eliminar"; //eliminar        
        var respuesta = confirm("¿Está seguro de borrar el registro " + tipoEmpleado_id + "?");
        if (respuesta) {
            $.ajax({
                url: "/Admin-JESAR/ControladorTipoEmpleados",
                type: "POST",
                datatype: "json",
                data: {action: accion, tipoEmpleado_id: tipoEmpleado_id},
                success: function () {
                    alert('Lograste eliminar');
                    tablaTipoEmpleados.row(fila.parents('tr')).remove().draw();
                }
            });
        }
    });

});
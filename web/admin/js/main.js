

$(document).ready(function () {
    var user_id, opcion;
    accion = "listar";

    tablaUsuarios = $('#listclientes').DataTable({
        "ajax": {
            "url": "/Admin-JESAR/Prueba",
            "method": 'POST', //usamos el metodo POST
            "data": {action: accion}, //enviamos opcion 4 para que haga un SELECT
            "dataSrc": "datos"
        },
        "columns": [
            {"data": "id"},
            {"data": "nombre"},
            {"data": "apellidos"},
            {"data": "tipo_documento"},
            {"data": "documento"},
            {"data": "telefono"},
            {"data": "direccion"},
            {"data": "correo"},
            {"defaultContent": "<div class='text-center'><div class='btn-group'><button class='btn btn-primary btn-sm btnEditar'><i class='material-icons'>edit</i></button><button class='btn btn-danger btn-sm btnBorrar'><i class='material-icons'>delete</i></button></div></div>"}
        ]
    });

    var fila; //captura la fila, para editar o eliminar
//submit para el Alta y Actualización
    $('#formUsuarios').submit(function (e) {
        e.preventDefault(); //evita el comportambiento normal del submit, es decir, recarga total de la página
        id = $.trim($('#id').val());
        nombre = $.trim($('#nombre').val());
        apellidos = $.trim($('#apellidos').val());
        tipo_documento = $.trim($('#tipodoc').val());
        documento = $.trim($('#doc').val());
        telefono = $.trim($('#telf').val());
        direccion = $.trim($('#dir').val());
        correo = $.trim($('#correo').val());
        
        $.ajax({
            url: "/Admin-JESAR/Prueba",
            type: "POST",
            datatype: "json",
            data: {action: accion, id: id, nombre: nombre, apellidos: apellidos, tipodoc: tipo_documento, doc: documento, telefono: telefono, direccion: direccion, correo:correo},
            success: function (data) {
                tablaUsuarios.ajax.reload(null, false);
            }
        });
        $('#clientesCRUD').modal('hide');
    });



//para limpiar los campos antes de dar de Alta una Persona
    $("#btnNuevo").click(function () {
        accion = "nuevo"; //alta           
        user_id = null;
        $("#formUsuarios").trigger("reset");
        $(".modal-header").css("background-color", "#17a2b8");
        $(".modal-header").css("color", "white");
        $(".modal-title").text("Alta de Usuario");
        $('#clientesCRUD').modal('show');
    });

//Editar        
    $(document).on("click", ".btnEditar", function () {
        accion = "editar";//editar
        fila = $(this).closest("tr");
        user_id = parseInt(fila.find('td:eq(0)').text()); //capturo el ID
        
        nombre = fila.find('td:eq(1)').text();
        apellidos = fila.find('td:eq(2)').text();
        tipo_documento = fila.find('td:eq(3)').text();
        documento = fila.find('td:eq(4)').text();
        telefono = fila.find('td:eq(5)').text();
        direccion = fila.find('td:eq(6)').text();
        correo = fila.find('td:eq(7)').text();
  
        $("#id").val(user_id);
        $("#nombre").val(nombre);
        $("#apellidos").val(apellidos);
        $("#tipodoc").val(tipo_documento);
        $("#doc").val(documento);
        $("#telf").val(telefono);
        $("#dir").val(direccion);
        $("#correo").val(correo);
        $(".modal-header").css("background-color", "#007bff");
        $(".modal-header").css("color", "white");
        $(".modal-title").text("Editar Usuario");
        $('#clientesCRUD').modal('show');
    });

//Borrar
    $(document).on("click", ".btnBorrar", function () {
        fila = $(this);
        user_id = parseInt($(this).closest('tr').find('td:eq(0)').text());
        accion = "eliminar"; //eliminar        
        var respuesta = confirm("¿Está seguro de borrar el registro " + user_id + "?");
        if (respuesta) {
            $.ajax({
                url: "/Admin-JESAR/Prueba2",
                type: "POST",
                datatype: "json",
                data: {action: accion, user_id: user_id},
                success: function () {
                    tablaUsuarios.row(fila.parents('tr')).remove().draw();
                }
            });
        }
    });

});


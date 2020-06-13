$(document).ready(function () {
    var tabla = $('#listclientes').DataTable({
        ajax: {
            method: "POST",
            url: "/Admin-JESAR/Prueba",
            data: {"action": "listar"},
            dataSrc: "datos"
        },
        columns: [
            {"data": "id"},
            {"data": "nombre"},
            {"data": "apellidos"},
            {"data": "tipo_documento"},
            {"data": "documento"},
            {"data": "telefono"},
            {"data": "direccion"},
            {"data": "correo"},
            {"data": "acciones"}
        ],

        //Para cambiar el lenguaje a español
        "language": {
            "lengthMenu": "Mostrar _MENU_ registros",
            "zeroRecords": "No se encontraron resultados",
            "info": "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
            "infoEmpty": "Mostrando registros del 0 al 0 de un total de 0 registros",
            "infoFiltered": "(filtrado de un total de _MAX_ registros)",
            "sSearch": "Buscar:",
            "oPaginate": {
                "sFirst": "Primero",
                "sLast": "Último",
                "sNext": "Siguiente",
                "sPrevious": "Anterior"
            },
            "sProcessing": "Procesando...",
        }

    });

    $("#btnNuevo").click(function () {
        $("#formPersonas").trigger("reset");
        $(".modal-header").css("background-color", "#28a745");
        $(".modal-header").css("color", "white");
        $(".modal-title").text("Nueva Persona");
        $("#clientesCRUD").modal("show");
        id = null;
        opcion = 1; //alta
    });

    $(document).on('click', '.eliminarCliente', function () {
        var idcliente = $(this).attr('id');
        var row = $(this).parent().parent();
        var r = confirm("Seguro que desea eliminar Cliente?");
        if (r == true) {
            $.ajax({
                url: "/Admin-JESAR/Prueba2",
                method: "POST",
                data: {"action": "eliminar", "idcliente": idcliente},
                success: function (data) {
                    alert(data);
                    row.remove();
                },
                error: function (error) {
                    alert('No se pudo eliminar');
                }
            });
        }
    });


    $("#formPersonas").submit(function (e) {
        e.preventDefault();
        nombre = $.trim($("#nombre").val());
        apellidos = $.trim($("#apellidos").val());
        tipodoc = $.trim($("#tipodoc").val());
        doc = $.trim($("#doc").val());
        telefono = $.trim($("#telf").val());
        direccion = $.trim($("#dir").val());
        correo = $.trim($("#correo").val());
        $.ajax({
            url: "/Admin-JESAR/Prueba",
            method: "POST",
            data: {"action": "nuevo", "nombre": nombre, "apellidos": apellidos, "tipodoc": tipodoc, "doc": doc, "telefono": telefono, "direccion": direccion, "correo": correo},
            success: function (data) {
                alert('NADA PAPU');
            },
            error: function (error) {
                alert('No se pudo eliminar');
            }
        });
        $("#modalCRUD").modal("hide");

    });


});


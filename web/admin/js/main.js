$(document).ready(function () {
      
    var tabla = $('#listclientes').DataTable({
       "columnDefs":[{
        "targets": -1,
        "data":null,
        "defaultContent": "<div class='text-center'><div class='btn-group'><button class='btn btn-primary btnEditar'>Editar</button><button class='btn btn-danger btnBorrar'>Borrar</button></div></div>"  
       }],

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
        id = $.trim($("#id").val());
        nombre = $.trim($("#nombre").val());
        apellidos = $.trim($("#apellidos").val());
        tipodoc = $.trim($("#tipodoc").val());
        doc = $.trim($("#doc").val());
        telefono = $.trim($("#telf").val());
        direccion = $.trim($("#dir").val());
        correo = $.trim($("#correo").val());
        $.ajax({
            url: "/Admin-JESAR/Prueba",
            type: "POST",
            dataType: "json",
            data: {action: "nuevo",id:id, nombre: nombre, apellidos: apellidos, tipodoc: tipodoc, doc: doc, telefono: telefono, direccion: direccion, correo: correo},
            success: function (data) {
                id = data.datos[0].id;
                nombre = data.datos[0].nombre;
                apellidos = data.datos[0].apellidos;
                tipodoc = data.datos[0].tipodoc;
                doc =data.datos[0].doc;
                telefono =data.datos[0].telefono;
                direccion =data.datos[0].direccion;
                correo =data.datos[0].correo;
                tabla.row.add([id,nombre,apellidos,tipodoc,doc,telefono,direccion,correo]).draw();
            },
            error: function (error) {
                alert('No se pudo eliminar');
            }
        });
        $("#clientesCRUD").modal("hide");

    });


});


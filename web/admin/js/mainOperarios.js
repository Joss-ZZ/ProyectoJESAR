$(document).ready(function () {
    var operario_id, accion;
    accion = "Listar";

    tablaOperarios = $('#ListaOperarios').DataTable({  
        //responsive:true,
        "ajax": {
            "url": "/Admin-JESAR/ControladorOperario",
            "method": 'POST', //usamos el metodo POST
            "data": {action: accion}, //enviamos opcion 4 para que haga un SELECT
            "dataSrc": "datos"
        },
        "columns": [
            {"data": "id"},
            {"data": "nombre"},
            {"data": "apellidos"},
            {"data": "id_tipo"},
            {"data": "tipo"},
            {"data": "direccion"},
            {"data": "telefono"},
            {"data": "correo"},
            {"data": "fechanac"},
            {"data": "dni"},
            {"defaultContent": "<div class='text-center'><div class='btn-group'><button class='btn btn-primary btn-sm btnEditarOperario'><i class='material-icons'>Editar</i></button><button class='btn btn-danger btn-sm btnBorrarOperario'><i class='material-icons'>Eliminar</i></button></div></div>"}
        ],    
        "aoColumnDefs": [ { "sClass": "hide_me", "aTargets": [3] } ]
    });
    
    var fila; //captura la fila, para editar o eliminar
//submit para el Alta y Actualización
    $('#formOperarios').submit(function (e) {
        e.preventDefault(); //evita el comportambiento normal del submit, es decir, recarga total de la página
        id = $.trim($('#operario_id').val());
        nombre = $.trim($('#nombre_ope').val());
        apellidos = $.trim($('#apellidos_ope').val());
        tipo = $.trim($('#tipo_ope').val());
        direccion = $.trim($('#direccion_ope').val());
        telefono = $.trim($('#telefono_ope').val());
        correo = $.trim($('#correo_ope').val());
        fechanac = $.trim($('#fechanac_ope').val());
        dni = $.trim($('#dni_ope').val());
        $.ajax({
            url: "/Admin-JESAR/ControladorOperario",
            type: "POST",
            datatype: "json",
            data: {action: accion, id: id, nombre: nombre, apellidos: apellidos, tipo: tipo, direccion: direccion, telefono:telefono,correo:correo,fechanac:fechanac,dni:dni},
            success: function (data) {
                tablaOperarios.ajax.reload(null, false);
            }
        });
        $('#operariosCrear').modal('hide');
    });



//para limpiar los campos antes de dar de Alta una Persona
    $("#btnNuevoOperario").click(function () {
        accion = "Nuevo"; //alta           
        operario_id = null;
        $("#formOperarios").trigger("reset");
        $(".modal-header").css("background-color", "#17a2b8");
        $(".modal-header").css("color", "white");
        $(".modal-title").text("Nuevo Operario");
        $('#operariosCrear').modal('show');
    });

//Editar        

    $(document).on("click", ".btnEditarOperario", function () {
        accion = "Editar";//editar
        fila = $(this).closest("tr");
      
        operario_id = parseInt(fila.find('td:eq(0)').text()); //capturo el ID 
        nombre = fila.find('td:eq(1)').text();
        apellidos = fila.find('td:eq(2)').text();
        tipo_id = parseInt(fila.find('td:eq(3)').text());
        direccion = fila.find('td:eq(5)').text();
        telefono = fila.find('td:eq(6)').text();
        correo = fila.find('td:eq(7)').text();
        fechanac = fila.find('td:eq(8)').text();
        dni = fila.find('td:eq(9)').text();

        $("#operario_id").val(operario_id);
        $("#nombre_ope").val(nombre);
        $("#apellidos_ope").val(apellidos);
        $("#tipo_ope").val(tipo_id);
        $("#direccion_ope").val(direccion);
        $("#telefono_ope").val(telefono);
        $("#correo_ope").val(correo);
        $("#fechanac_ope").val(fechanac);
        $("#dni_ope").val(dni);
        $(".modal-header").css("background-color", "#007bff");
        $(".modal-header").css("color", "white");
        $(".modal-title").text("Editar Operario");
        $('#operariosCrear').modal('show');
    });

//Borrar
    $(document).on("click", ".btnBorrarOperario", function () {
        fila = $(this);
        operario_id = parseInt($(this).closest('tr').find('td:eq(0)').text());
        accion = "Eliminar"; //eliminar        
        var respuesta = confirm("¿Está seguro de borrar el registro " + operario_id + "?");
        if (respuesta) {
            $.ajax({
                url: "/Admin-JESAR/ControladorOperario",
                type: "POST",
                datatype: "json",
                data: {action: accion, insumo_id: operario_id},
                success: function () {
                    alert('Lograste eliminar');
                    tablaOperarios.row(fila.parents('tr')).remove().draw();
                }
            });
        }
    });
});


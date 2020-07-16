$(document).ready(function () {
    var cod_emp, accion;
    accion = "Listar";

    tablaEmpleado = $('#listEmpleado').DataTable({
        //responsive:true,
        "ajax": {
            "url": "/Admin-JESAR/ControladorEmpleado",
            "method": 'POST', //usamos el metodo POST
            "data": {action: accion}, //enviamos opcion 4 para que haga un SELECT
            "dataSrc": "datos"
        },
        "columns": [
            {"data": "cod_emp"},
            {"data": "usuario"},
            {"data": "password"},
            {"data": "nombres"},
            {"data": "apellidos"},
            {"data": "direccion"},
            {"data": "telefono"},
            {"data": "correo"},
            {"data": "fechaNac"},
            {"data": "TipoDoc"},
            {"data": "nDoc"},
            {"data": "estado"},
            {"data": "privilegio"},
            {"data": "TipoEmp"},
            {"defaultContent": "<div class='text-center'><div class='btn-group'><button class='btn btn-primary btn-sm btnEditarEmpleado'><i class='material-icons'>edit</i></button><button class='btn btn-danger btn-sm btnBorrarEmpleado'><i class='material-icons'>delete</i></button></div></div>"}
        ],    
        "aoColumnDefs": [ { "sClass": "hide_me", "aTargets": [2,5,8,12] } ]
    });

    var fila; //captura la fila, para editar o eliminar
//submit para el Alta y Actualización
    $('#formEmpleado').submit(function (e) {
        e.preventDefault(); //evita el comportambiento normal del submit, es decir, recarga total de la página
        cod_emp = $.trim($('#id').val());
        usuario = $.trim($('#usuario').val());
        password = $.trim($('#password').val());
        nombres = $.trim($('#nombres').val());
        apellidos = $.trim($('#apellidos').val());
        direccion = $.trim($('#direccion').val());
        telefono = $.trim($('#telefono').val());
        correo = $.trim($('#correo').val());
        fechaNacimiento = $.trim($('#fechaNacimiento').val());
        tipoDocumento = $.trim($('#tipoDocumento').val());
        nDocumento = $.trim($('#nDocumento').val());
        estado = $.trim($('#estado').val());
        privilegio = $.trim($('#privilegio').val());
        tipoEmpleado = $.trim($('#tipoEmpleado').val());
        alert(estado);
        $.ajax({
            url: "/Admin-JESAR/ControladorEmpleado",
            type: "POST",
            datatype: "json",
            data: {action: accion, cod_emp: cod_emp, usuario: usuario, password: password, nombres: nombres, apellidos: apellidos, direccion: direccion, telefono: telefono, correo: correo, fechaNacimiento: fechaNacimiento, tipoDocumento: tipoDocumento, nDocumento: nDocumento, estado: estado, privilegio: privilegio, tipoEmpleado: tipoEmpleado},
            success: function (data) {
                tablaEmpleado.ajax.reload(null, false);
            }
        });
        $('#empleadoCRUD').modal('hide');
    });

//para limpiar los campos antes de dar de Alta una Persona
    $("#btnNuevo-Empleado").click(function () {
        accion = "Nuevo"; //alta           
        cod_emp = null;
        $("#formColor").trigger("reset");
        $(".modal-header").css("background-color", "#17a2b8");
        $(".modal-header").css("color", "white");
        $(".modal-title").text("Nuevo Empleado");
        $('#empleadoCRUD').modal('show');
    });

//Editar        
    $(document).on("click", ".btnEditarEmpleado", function () {
        accion = "Editar";//editar
        fila = $(this).closest("tr");
        cod_emp = parseInt(fila.find('td:eq(0)').text()); //capturo el ID
        usuario = fila.find('td:eq(1)').text();
        password = fila.find('td:eq(2)').text();
        nombres = fila.find('td:eq(3)').text();
        apellidos = fila.find('td:eq(4)').text();
        direccion = fila.find('td:eq(5)').text();
        telefono = fila.find('td:eq(6)').text();
        correo = fila.find('td:eq(7)').text();
        fechaNacimiento = fila.find('td:eq(8)').text();
        tipoDocumento = fila.find('td:eq(9)').text();
        nDocumento = fila.find('td:eq(10)').text();
        estado = fila.find('td:eq(11)').text();
        privilegio = parseInt(fila.find('td:eq(12)').text());
        tipoEmpleado = fila.find('td:eq(13)').text();
        
  
        $("#id").val(cod_emp);
        $("#usuario").val(usuario);
        $("#password").val(password);
        $("#nombres").val(nombres);
        $("#apellidos").val(apellidos);
        $("#direccion").val(direccion);
        $("#telefono").val(telefono);
        $("#correo").val(correo);
        $("#fechaNacimiento").val(fechaNacimiento);
        $("#tipoDocumento").val(tipoDocumento);
        $("#nDocumento").val(nDocumento);
        $("#estado").val(estado);
        $("#privilegio").val(privilegio);
        $("#tipoEmpleado").val(tipoEmpleado);
        
        $(".modal-header").css("background-color", "#007bff");
        $(".modal-header").css("color", "white");
        $(".modal-title").text("Editar Color");
        $('#empleadoCRUD').modal('show');
    });

//Borrar
    $(document).on("click", ".btnBorrarEmpleado", function () {
        fila = $(this);
        cod_emp = parseInt($(this).closest('tr').find('td:eq(0)').text());
        accion = "Eliminar"; //eliminar        
        var respuesta = confirm("¿Está seguro de borrar el registro " + cod_emp + "?");
        if (respuesta) {
            $.ajax({
                url: "/Admin-JESAR/ControladorEmpleado",
                type: "POST",
                datatype: "json",
                data: {action: accion, cod_emp: cod_emp},
                success: function () {
                    alert('Lograste eliminar');
                    tablaEmpleado.row(fila.parents('tr')).remove().draw();
                }
            });
        }
    });

});
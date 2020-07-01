$(document).ready(function () {
    var categoriaProducto_id, accion;
    accion = "Listar";

    tablaCategoriaProductos = $('#listCategoriaProducto').DataTable({
        "ajax": {
            "url": "/Admin-JESAR/ControladorCategoriaProductos",
            "method": 'POST', //usamos el metodo POST
            "data": {action: accion}, //enviamos opcion 4 para que haga un SELECT
            "dataSrc": "datos"
        },
        "columns": [
            {"data": "id"},
            {"data": "descripcion"},
            {"defaultContent": "<div class='text-center'><div class='btn-group'><button class='btn btn-primary btn-sm btnEditarCategoriaP'><i class='material-icons'>edit</i></button><button class='btn btn-danger btn-sm btnBorrarCategoriaP'><i class='material-icons'>delete</i></button></div></div>"}
        ]
    });

    var fila; //captura la fila, para editar o eliminar
//submit para el Alta y Actualización
    $('#formCategoriaProducto').submit(function (e) {
        e.preventDefault(); //evita el comportambiento normal del submit, es decir, recarga total de la página
        id = $.trim($('#id').val());
        descripcion = $.trim($('#descripcion').val());
        
        $.ajax({
            url: "/Admin-JESAR/ControladorCategoriaProductos",
            type: "POST",
            datatype: "json",
            data: {action: accion, id: id, descripcion: descripcion},
            success: function (data) {
                tablaCategoriaProductos.ajax.reload(null, false);
            }
        });
        $('#categoriaProductoCRUD').modal('hide');
    });

//para limpiar los campos antes de dar de Alta una Persona
    $("#btnNuevo-CategoriaProducto").click(function () {
        accion = "Nuevo"; //alta           
        categoriaProducto_id = null;
        $("#formCategoriaProducto").trigger("reset");
        $(".modal-header").css("background-color", "#17a2b8");
        $(".modal-header").css("color", "white");
        $(".modal-title").text("Alta de Color");
        $('#categoriaProductoCRUD').modal('show');
    });

//Editar        
    $(document).on("click", ".btnEditarCategoriaP", function () {
        accion = "Editar";//editar
        fila = $(this).closest("tr");
        categoriaProducto_id = parseInt(fila.find('td:eq(0)').text()); //capturo el ID
        
        descripcion = fila.find('td:eq(1)').text();
  
        $("#id").val(categoriaProducto_id);
        $("#descripcion").val(descripcion);
        
        $(".modal-header").css("background-color", "#007bff");
        $(".modal-header").css("color", "white");
        $(".modal-title").text("Editar Color");
        $('#categoriaProductoCRUD').modal('show');
    });

//Borrar
    $(document).on("click", ".btnBorrarCategoriaP", function () {
        fila = $(this);
        categoriaProducto_id = parseInt($(this).closest('tr').find('td:eq(0)').text());
        accion = "Eliminar"; //eliminar        
        var respuesta = confirm("¿Está seguro de borrar el registro " + categoriaProducto_id + "?");
        if (respuesta) {
            $.ajax({
                url: "/Admin-JESAR/ControladorCategoriaProductos",
                type: "POST",
                datatype: "json",
                data: {action: accion, categoriaProducto_id: categoriaProducto_id},
                success: function () {
                    alert('Lograste eliminar');
                    tablaCategoriaProductos.row(fila.parents('tr')).remove().draw();
                }
            });
        }
    });

});
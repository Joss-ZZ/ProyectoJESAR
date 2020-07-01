$(document).ready(function () {
    var categoriaInsumos_id, accion;
    accion = "Listar";

    tablaCategoriaInsumos = $('#listCategoriaInsumos').DataTable({
        "ajax": {
            "url": "/Admin-JESAR/ControladorCategoriaInsumos",
            "method": 'POST', //usamos el metodo POST
            "data": {action: accion}, //enviamos opcion 4 para que haga un SELECT
            "dataSrc": "datos"
        },
        "columns": [
            {"data": "id"},
            {"data": "descripcion"},
            {"defaultContent": "<div class='text-center'><div class='btn-group'><button class='btn btn-primary btn-sm btnEditarCategoria'><i class='material-icons'>edit</i></button><button class='btn btn-danger btn-sm btnBorrarCategoria'><i class='material-icons'>delete</i></button></div></div>"}
        ]
    });

    var fila; //captura la fila, para editar o eliminar
//submit para el Alta y Actualización
    $('#formCategoriaInsumos').submit(function (e) {
        e.preventDefault(); //evita el comportambiento normal del submit, es decir, recarga total de la página
        id = $.trim($('#id').val());
        descripcion = $.trim($('#descripcion').val());
        
        $.ajax({
            url: "/Admin-JESAR/ControladorCategoriaInsumos",
            type: "POST",
            datatype: "json",
            data: {action: accion, id: id, descripcion: descripcion},
            success: function (data) {
                tablaCategoriaInsumos.ajax.reload(null, false);
            }
        });
        $('#categoriaInsumosCRUD').modal('hide');
    });

//para limpiar los campos antes de dar de Alta una Persona
    $("#btnNuevo-CategoriaInsumos").click(function () {
        accion = "Nuevo"; //alta           
        categoriaInsumos_id = null;
        $("#formCategoriaInsumos").trigger("reset");
        $(".modal-header").css("background-color", "#17a2b8");
        $(".modal-header").css("color", "white");
        $(".modal-title").text("Alta de Color");
        $('#categoriaInsumosCRUD').modal('show');
    });

//Editar        
    $(document).on("click", ".btnEditarCategoria", function () {
        accion = "Editar";//editar
        fila = $(this).closest("tr");
        categoriaInsumos_id = parseInt(fila.find('td:eq(0)').text()); //capturo el ID
        
        descripcion = fila.find('td:eq(1)').text();
  
        $("#id").val(categoriaInsumos_id);
        $("#descripcion").val(descripcion);
        
        $(".modal-header").css("background-color", "#007bff");
        $(".modal-header").css("color", "white");
        $(".modal-title").text("Editar Color");
        $('#categoriaInsumosCRUD').modal('show');
    });

//Borrar
    $(document).on("click", ".btnBorrarCategoria", function () {
        fila = $(this);
        categoriaInsumos_id = parseInt($(this).closest('tr').find('td:eq(0)').text());
        accion = "Eliminar"; //eliminar        
        var respuesta = confirm("¿Está seguro de borrar el registro " + categoriaInsumos_id + "?");
        if (respuesta) {
            $.ajax({
                url: "/Admin-JESAR/ControladorCategoriaInsumos",
                type: "POST",
                datatype: "json",
                data: {action: accion, categoriaInsumos_id: categoriaInsumos_id},
                success: function () {
                    alert('Lograste eliminar');
                    tablaCategoriaInsumos.row(fila.parents('tr')).remove().draw();
                }
            });
        }
    });

});
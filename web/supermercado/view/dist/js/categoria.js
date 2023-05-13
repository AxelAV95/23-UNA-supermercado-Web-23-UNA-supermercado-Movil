$(function () {
  $('#categorias').DataTable({
    "language": {
      "url": "//cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json"
    },
    "paging": true,
    "lengthChange": false,
    "searching": true,
    "ordering": true,
    "info": true,
    "autoWidth": false,
    "responsive": true,
  });

  $(".tabla-categorias tbody").on("click", "button.btnEditarCategoria", function(){
    var categoriaid = $(this).attr("categoriaid");
    var categorianombre = $(this).attr("categorianombre");
    $("#modalEditarCategoria input[name='categoriaid']").val(categoriaid);
    $("#modalEditarCategoria input[name='categorianombre']").val(categorianombre);
  });

  $(document).on("submit", "#modalEditarCategoria form", function(e){
    var categoriaNombre = $("#modalEditarCategoria input[name='categorianombre']").val();
    if (categoriaNombre == "") {
      e.preventDefault();
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'El nombre de la categoría no puede estar vacío.'
      });
    } else if (!/^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]+$/.test(categoriaNombre)) {
      e.preventDefault();
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'El nombre de la categoría solo puede contener letras y espacios.'
      });
    }
  });
});


$(".tabla-categorias tbody").on("click", "button.btnEliminarCategoria", function(){
  var categoriaid = $(this).attr("categoriaid");
  
  Swal.fire({
    title: '¿Desea eliminar esta categoría?',
    showDenyButton: true,
    showCancelButton: false,
    confirmButtonText: 'Sí',
    denyButtonText: `Cancelar`
  }).then((result) => {
    if (result.isConfirmed) {
      window.location = "../business/categoriaaction.php?eliminar=true&categoriaid=" + categoriaid;
    }
  });
});

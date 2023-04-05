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
  });

  $(".tabla-categorias tbody").on("click", "button.btnEditarCategoria", function(){

    var categoriaid = $(this).attr("categoriaid");
    var categorianombre = $(this).attr("categorianombre");

    
   // alert(categoriaid);
    $("#modalEditarCategoria #categoriaid").val(categoriaid);
    $("#modalEditarCategoria #categorianombre").val(categorianombre);
 
  

});

  
$(".tabla-categorias tbody").on("click", "button.btnEliminarCategoria", function(){

  var categoriaid = $(this).attr("categoriaid");



  //alert(proveedorid);
 Swal.fire({
        title: '¿Desea eliminar esta categoría?',
        showDenyButton: true,
        showCancelButton: false,
        confirmButtonText: 'Sí',
        denyButtonText: `Cancelar`
       }).then((result) => {
         if (result.isConfirmed) {
           window.location = "../business/categoriaaction.php?eliminar=true&categoriaid="+categoriaid;
    
         }
   })


});

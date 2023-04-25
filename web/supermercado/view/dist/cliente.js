var Toast = Swal.mixin({
    toast: true,
    position: 'top-right',
    showConfirmButton: false,
    timer: 3000,
    timerProgressBar: true
  });





$(document).on('click','#insertar',function(e) {

  var descripcion = $("#formulario-insertar div").children().get(2);

 if(descripcion.value  == ""){
   $("#formulario-insertar div").children().get(2).focus()
     Toast.fire({
                 icon: 'warning',
                 title: '<div style=margin-top:0.5rem;>Ingresar descripción.</div>'
           })
     
 }else{
     var data = $("#formulario-insertar").serialize();

     console.log(data)
     $.ajax({
       data: data,
       type: "POST",
       url: "../business/clienteaction.php",
       success: function(dataResult){
           var dataResult = JSON.parse(dataResult);
           if(dataResult.statusCode==200){

                  Toast.fire({
                     icon: 'success',
                     title: '<div style=margin-top:0.5rem;>Insertado con éxito.</div>'
               });

                  $('#modalAgregarCliente #clientenombre').val("");
                  $('#modalAgregarCliente #clienteapellidos').val("");
                  $('#modalAgregarCliente #clientecedula').val("");
                  $('#modalAgregarCliente #clientedireccion').val("");
                  $('#modalAgregarCliente #clientetelefono').val("");
                  $('#modalAgregarCliente #clientecorreo').val("");
                  $('#modalAgregarCliente #clientefechaafiliacion').val("");
                  $('#modalAgregarCliente #clientetipomembresia').val("");



                  $('#modalAgregarCliente').modal('hide');
                  $('#tabla-clientes').DataTable().ajax.reload();
                 
                                              
           }else{
                 Toast.fire({
                     icon: 'error',
                     title: '<div style=margin-top:0.5rem;>Error al efectuar la operación.</div>'
               })
           }
                     
       }
     });
   }
});

$(document).on('click','#actualizar',function(e) {

 var descripcion = $("#formulario-editar div").children().get(3);

if(descripcion.value  == ""){
      $("#formulario-editar div").children().get(3).focus()
     Toast.fire({
                 icon: 'warning',
                 title: '<div style=margin-top:0.5rem;>Ingresar descripción.</div>'
           })
 }else{
     var data = $("#formulario-editar").serialize();
     console.log(data)
     $.ajax({
       data: data,
       type: "POST",
       url: "../business/membresiaaction.php",
       success: function(dataResult){
             var dataResult = JSON.parse(dataResult);
           if(dataResult.statusCode==200){
                        
                 Toast.fire({
                     icon: 'success',
                     title: '<div style=margin-top:0.5rem;>Actualizado con éxito.</div>'
               });
                  
                  $('#modalEditarTipo').modal('hide');
                  $('#tabla-clientes').DataTable().ajax.reload();
                                              
           }else{
                  Toast.fire({
                     icon: 'error',
                     title: '<div style=margin-top:0.5rem;>Error al efectuar la operación.</div>'
               })
       
           }
                     
       }
     });
 }
 

});



  $(".tabla-clientes tbody").on("click", "button.btnEditarCategoria", function(){

    var clienteid = $(this).attr("clienteid");
    var clientenombre = $(this).attr("clientenombre");
    var clienteapellidos = $(this).attr("clienteapellidos");
    var clientecedula = $(this).attr("clientecedula");
    var clientedireccion = $(this).attr("clientedireccion");
    var clientetelefono = $(this).attr("clientetelefono");
    var clientecorreo = $(this).attr("clientecorreo");
    var clientefechaafiliacion = $(this).attr("clientefechaafiliacion");
    var clientetipomembresia = $(this).attr("clientetipomembresia");
   
    $("#modalEditarlCiente #clienteid").val(clienteid);
    $("#modalEditarlCiente #clientenombre").val(clientenombre);
    $("#modalEditarlCiente #clienteapellidos").val(clienteapellidos);
    $("#modalEditarlCiente #clientecedula").val(clientecedula);
    $("#modalEditarlCiente #clientedireccion").val(clientedireccion);
    $("#modalEditarlCiente #clientetelefono").val(clientetelefono);
    $("#modalEditarlCiente #clientecorreo").val(clientecorreo);
    $("#modalEditarlCiente #clientefechaafiliacion").val(clientefechaafiliacion);
    $("#modalEditarlCiente #clientetipomembresia").val(clientetipomembresia);
  

});

$(".tabla-clientes tbody").on("click", "button.btnEliminarCategoria", function(){

  var clienteid = $(this).attr("clienteid");

      Swal.fire({
        title: '¿Desea eliminar la categoría?',
        text: "No se podrá revertir el cambio",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
         cancelButtonText: "Cancelar",
        confirmButtonText: 'Eliminar'
        }).then((result) => {
          if (result.isConfirmed) {
            window.location = "../../business/clientection.php?eliminar=true&clienteid="+clienteid;
      
          }
    })
 

});
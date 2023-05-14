var Toast = Swal.mixin({
       toast: true,
       position: 'top-right',
       showConfirmButton: false,
       timer: 3000,
       timerProgressBar: true
     });



  $('#tabla-tipos').dataTable( {
          'processing': true,
            'serverSide': true,
            'serverMethod': 'get',
            'ajax': {
                'url':'../business/tipoempleadoaction.php?metodo=obtener'
            },          
          "columns": [
             
              { "data": "tipodescripcion" },
              { "data": null,
              render: function ( data, type, row, meta) {
                return "<div class='btn-group'><button class='btn btn-warning btnEditarTipo' tipoid="+data.tipoid+" descripcion="+data.tipodescripcion+" data-toggle='modal' data-target='#modalEditarTipo'><i class='fa fa-pencil-alt'></i></button><button class='btn btn-danger btnEliminarTipo' tipoid="+data.tipoid+" ><i class='fa fa-times'></i></button></div>";
                }
              }
              
          ]
      
        })


  $(document).on('click','#insertar',function(e) {


     var descripcion = $("#formulario-insertar div").children().get(2);
   
    if(descripcion.value  == ""){
      $("#formulario-insertar div").children().get(2).focus()
        Toast.fire({
                    icon: 'warning',
                    title: '<div style=margin-top:0.5rem;>Ingresar descripción.</div>'
              })
        
    }else if(!/^[a-zA-Z\s]*$/.test(descripcion.value)){
      $("#formulario-insertar div").children().get(2).focus()
        Toast.fire({
                    icon: 'warning',
                    title: '<div style=margin-top:0.5rem;>La descripción ingresada contiene caracteres no válidos, ingrese solo texto.</div>'
              })
    }else{
        var data = $("#formulario-insertar").serialize();

        console.log(data)
        $.ajax({
          data: data,
          type: "POST",
          url: "../business/tipoempleadoaction.php",
          success: function(dataResult){
              var dataResult = JSON.parse(dataResult);
              if(dataResult.statusCode==200){

                     Toast.fire({
                        icon: 'success',
                        title: '<div style=margin-top:0.5rem;>Insertado con éxito.</div>'
                  });
                     $('#modalAgregarTipo #tipodescripcion').val("");
                     $('#modalAgregarTipo').modal('hide');
                     $('#tabla-tipos').DataTable().ajax.reload();
                    
                                                 
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
    }else if(!/^[a-zA-Z\s]*$/.test(descripcion.value)){
      $("#formulario-editar div").children().get(3).focus()
        Toast.fire({
                    icon: 'warning',
                    title: '<div style=margin-top:0.5rem;>La descripción ingresada contiene caracteres no válidos, ingrese solo texto.</div>'
              })
    }else{
        var data = $("#formulario-editar").serialize();
        console.log(data)
        $.ajax({
          data: data,
          type: "POST",
          url: "../business/tipoempleadoaction.php",
          success: function(dataResult){
                var dataResult = JSON.parse(dataResult);
              if(dataResult.statusCode==200){
                           
                    Toast.fire({
                        icon: 'success',
                        title: '<div style=margin-top:0.5rem;>Actualizado con éxito.</div>'
                  });
                     
                     $('#modalEditarTipo').modal('hide');
                     $('#tabla-tipos').DataTable().ajax.reload();
                                                 
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



  $(".tabla-tipos tbody").on("click", "button.btnEditarTipo", function(){

    var id = $(this).attr("tipoid");
    var descripcion = $(this).attr("descripcion");
   
    $("#modalEditarTipo #tipoid").val( id );
    $("#modalEditarTipo #tipodescripcion").val(descripcion);

  

});

  
$(".tabla-tipos tbody").on("click", "button.btnEliminarTipo", function(){

  var id = $(this).attr("tipoid");

  Swal.fire({
        title: '¿Desea eliminar este tipo de empleado?',
        showDenyButton: true,
        showCancelButton: false,
        confirmButtonText: 'Sí',
        denyButtonText: `Cancelar`,
      }).then((result) => {
       
        if (result.isConfirmed) {
          let requestUrl ="../business/tipoempleadoaction.php?metodo=eliminar&id="+id;
          console.log(requestUrl)
          $.ajax({
          url: requestUrl ,
          type: "GET",
          
          
          success: function(dataResult){


            var dataResult = JSON.parse(dataResult);
            if(dataResult.statusCode==200){
                         
                  Toast.fire({
                      icon: 'success',
                      title: '<div style=margin-top:0.5rem;>Eliminado con éxito.</div>'
                });
                   
                  
                   $('#tabla-tipos').DataTable().ajax.reload();
                                               
            }else{
                   Toast.fire({
                      icon: 'error',
                      title: '<div style=margin-top:0.5rem;>Error al efectuar la operación.</div>'
                })
        
            }
          }
        });

        } 
      })
});
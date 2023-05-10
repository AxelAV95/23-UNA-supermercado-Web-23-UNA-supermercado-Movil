<?php  

    include '../business/usuariobusiness.php';
    include '../business/tipousuariobusiness.php';

?>

<?php 
  include 'template/sesion.php';
  ?>


<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Usuarios | Dashboard</title>

  <?php include 'template/dependenciascss.php' ?>
  <style>
    .hidetext { -webkit-text-security: disc; /* Default */ }
  </style>
</head>
<body class="hold-transition sidebar-mini layout-fixed">
<div class="wrapper">

  <!-- Preloader -->
 <!--  <div class="preloader flex-column justify-content-center align-items-center">
    <img class="animation__shake" src="dist/img/AdminLTELogo.png" alt="AdminLTELogo" height="60" width="60">
  </div> -->

  <!-- Navbar --> 
  <?php include 'template/header.php' ?>
  <!-- /.navbar -->

  <!-- Main Sidebar Container -->
<aside class="main-sidebar sidebar-dark-primary elevation-4">
    <!-- Brand Logo -->
    <a href="index.html" class="brand-link d-flex justify-content-center ">
     <!--  <img src="dist/img/AdminLTELogo.png" alt="AdminLTE Logo" class="brand-image img-circle elevation-3" style="opacity: .8"> -->
           <?php include 'template/infosuper.php' ?>
    </a>

    <!-- Sidebar -->
     <?php include 'template/sidebaradmin.php' ?>
    <!-- /.sidebar -->
  </aside>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-12">
            <h1 class="m-0 text-center">Usuarios</h1>
          </div><!-- /.col -->
        
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        
        <!-- /.row -->
        <!-- Main row -->
       <div class="card">
              <div class="card-header jus">
                <button class="btn btn-primary btnAgregar" data-toggle="modal" data-target="#modalAgregarUsuario">

                    Agregar usuario

                  </button>
                  <a href="tipousuarioview.php" class="btn btn-primary">Tipos de usuario</a>
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                 <table id="usuarios" class="tabla-usuarios table table-bordered table-hover">
                  <thead>
                  <tr>
                    
                    <th>Nombre</th>
                    <th>Apellidos</th>
                    <th>Tipo</th>
                    <th>Acciones</th>
                    
                  </tr>
                  </thead>
                  <tbody>
                  
                  </tbody>
                  
                </table>
              </div>
              <!-- /.card-body -->
            </div>
        <!-- /.row (main row) -->
      </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  <?php include 'template/footer.php'; ?>


</div>
<!-- ./wrapper -->

<?php include 'template/modales/modalagregarusuario.php' ?>

<?php include 'template/modales/modaleditarusuario.php' ?>
<?php include 'template/modales/modalqr.php' ?>

<?php include 'template/dependenciasjs.php' ?>


<script src="dist/js/usuarios.js">
     
</script>


<script>


  
  $(".tabla-usuarios tbody").on("click", "button.btnEditarUsuario", function(){

    var usuarioid = $(this).attr("usuarioid");

    $("#modalEditarUsuario #usuarioid").val(usuarioid);
    
     $('#modalEditarUsuario #tipos').empty();
     $('#modalEditarUsuario #tipos').append($('<option>').text("Seleccione el tipo de usuario"))

    $.ajax({
      type: "GET",
      url: '../business/usuarioaction.php?metodo=obtenerTipos',
      dataType: 'json',
      success: function(data) {
   
        $.each(data, function(key, value){ 
          $('#modalEditarUsuario #tipos').append($('<option>').text(value.tipodescripcion).attr('value', value.tipoid));
        });

      }
    });

});

  
$(".tabla-usuarios tbody").on("click", "button.btnEliminarUsuario", function(){

  var usuarioid = $(this).attr("usuarioid");
  //aquiii??
 
 Swal.fire({
        title: '¿Desea eliminar este usuario?',
        showDenyButton: true,
        showCancelButton: false,
        confirmButtonText: 'Sí',
        denyButtonText: `Cancelar`,
       }).then((result) => {
         if (result.isConfirmed) {
           let requestUrl ="../business/usuarioaction.php?metodo=eliminar&usuarioid="+usuarioid;
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
                   
                  
                   $('#usuarios').DataTable().ajax.reload();
                                               
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


$('#modalEditarUsuario #mostrarPassword').click(function(){

  $(this).is(':checked') ? $('#modalEditarUsuario #usuariopassword').attr('type', 'text') : $('#modalEditarUsuario #usuariopassword').attr('type', 'password');
});


$(".btnAgregar").on("click",function(){

  $('#empleados').empty();
  $('#empleados').append($('<option>').text("Seleccione un empleado"))
    $('#tipos').empty();
    $('#tipos').append($('<option>').text("Seleccione el tipo de usuario"))

    $.ajax({
      type: "GET",
      url: '../business/usuarioaction.php?metodo=obtenerEmpleados',
      dataType: 'json',
      success: function(data) {
   
        $.each(data, function(key, value){ 
          $('#empleados').append($('<option>').text(value.empleadonombre).attr('value', value.empleadoid));
        });

      }
    });

    $.ajax({
      type: "GET",
      url: '../business/usuarioaction.php?metodo=obtenerTipos',
      dataType: 'json',
      success: function(data) {
   
        $.each(data, function(key, value){ 
          $('#tipos').append($('<option>').text(value.tipodescripcion).attr('value', value.tipoid));
        });

      }
    });

  })

  

   $(document).on('click','#insertar',function(e) {


     var empleadoid= $("#formulario-insertar div").children().get(1);
     var tipoid = $("#formulario-insertar div").children().get(3);
     var pass = $("#formulario-insertar div").children().get(5);
    

    if($(empleadoid).val()  == "Seleccione un empleado"){
      $("#formulario-insertar div").children().get(1).focus()
        Toast.fire({
                    icon: 'warning',
                    title: '<div style=margin-top:0.5rem;>Debe seleccionar un empleado.</div>'
              })
        
    }else if($(tipoid).val()  == "Seleccione el tipo de usuario"){

        $("#formulario-insertar div").children().get(3).focus()
        Toast.fire({
                    icon: 'warning',
                    title: '<div style=margin-top:0.5rem;>Debe seleccionar un tipo.</div>'
              })

    }else if($(pass).val()  == ""){


        $("#formulario-insertar div").children().get(5).focus()
        Toast.fire({
                    icon: 'warning',
                    title: '<div style=margin-top:0.5rem;>Debe establecer una contraseña.</div>'
              })

    } else{
        var data = $("#formulario-insertar").serialize();

        console.log(data)

        
        $.ajax({
          data: data,
          type: "POST",
          url: "../business/usuarioaction.php",
          success: function(dataResult){
             var dataResult = JSON.parse(dataResult);
              console.log(dataResult)
              if(dataResult.statusCode==200){

                     Toast.fire({
                        icon: 'success',
                        title: '<div style=margin-top:0.5rem;>Insertado con éxito.</div>'
                  });
                     $('#modalAgregarUsuario #usuariopassword').val("");
                     $('#modalAgregarUsuario').modal('hide');
                    $('#usuarios').DataTable().ajax.reload();
                    
                                                 
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


     
     var tipoid = $("#modalEditarUsuario #formulario-editar div").children().get(1);
     var pass = $("#modalEditarUsuario #formulario-editar div").children().get(3);
    
     console.log(pass)
   if($(tipoid).val()  == "Seleccione el tipo de usuario"){

        $("#formulario-editar div").children().get(1).focus()
        Toast.fire({
                    icon: 'warning',
                    title: '<div style=margin-top:0.5rem;>Debe seleccionar un tipo.</div>'
              })

    }else if($(pass).val()  == ""){


        $("#formulario-editar div").children().get(3).focus()
        Toast.fire({
                    icon: 'warning',
                    title: '<div style=margin-top:0.5rem;>Debe establecer una contraseña.</div>'
              })

    } else{
        var data = $("#formulario-editar").serialize();

        console.log(data)

        
        
        $.ajax({
          data: data,
          type: "POST",
          url: "../business/usuarioaction.php",
          success: function(dataResult){
               var dataResult = JSON.parse(dataResult);
              if(dataResult.statusCode==200){
                           
                    Toast.fire({
                        icon: 'success',
                        title: '<div style=margin-top:0.5rem;>Actualizado con éxito.</div>'
                  });
                     
                     $('#modalEditarUsuario').modal('hide');
                     $('#usuarios').DataTable().ajax.reload();
                                                 
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

  $('#empleados').change(function() {
    var empleado = $(this).val();

    $.ajax({
      type: "GET",
      url: '../business/usuarioaction.php?metodo=verificarEmpleado&empleadoid='+empleado,
      dataType: 'json',
      success: function(data) {
       
      
        if(data.statusCode==409){
             Toast.fire({
                        icon: 'error',
                        title: '<div style=margin-top:0.5rem;>El empleado elegido ya cuenta con un usuario.</div>'
                  });

             $('#insertar').prop('disabled', true);

        }else{
            $('#insertar').prop('disabled', false);
        }
        

      }
    });

  });

</script>
</body>
</html>


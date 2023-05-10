<?php 

 



 ?>

 <?php 
   include 'template/sesion.php';
  ?>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Configuración - Dashboard</title>

  <?php include 'template/dependenciascss.php' ?>
</head>
<body class="hold-transition sidebar-mini layout-fixed">
<div class="wrapper">

  <!-- Navbar -->
  <?php include 'template/header.php' ?>
  <!-- /.navbar -->

  <!-- Main Sidebar Container -->
<aside class="main-sidebar sidebar-dark-primary elevation-4">
    <!-- Brand Logo -->
    <a href="index.php" class="brand-link d-flex justify-content-center ">
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
            <h1 class="m-0 text-center">Configuración</h1>
          </div><!-- /.col -->
          
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <section class="content">

      <div class="container-fluid">

        

        <!-- Small boxes (Stat box) -->
      <form id="formulario-configuracion" enctype="multipart/form-data">

        <div class="card">
  
          <div class="card-body">
              <div class="row m-3">
            
          
            <div class="col-lg-6">
                
                <input type="hidden" class="form-control" id="supermercadoid" name="supermercadoid" >
                <input type="hidden" class="form-control" name="metodo" value="actualizar" >
                <div class="form-group">
                  <label for="formNombre">Nombre</label>
                  <input type="text" class="form-control" id="supermercadonombre" name="supermercadonombre" placeholder="Nombre del supermercado">
                </div>
                <div class="form-group">
                  <label for="formTelefono">Teléfono</label>
                  <input type="number" class="form-control" id="supermercadotelefono" name="supermercadotelefono" placeholder="Teléfono">
                </div>
                <div class="form-group">
                  <label for="formCorreo">Correo</label>
                  <input type="email" class="form-control" id="supermercadocorreo" name="supermercadocorreo" placeholder="Correo">
                </div>
             
            </div>


             <div class="col-lg-6">
          
                <div class="form-group">
                  <label for="formDireccion">Dirección</label>
                  <input type="text" class="form-control" id="supermercadodireccion" name="supermercadodireccion" placeholder="Dirección">
                </div>
                <div class="form-group">
                  <label for="formLogo">Logo</label>
                  <input type="file" class="form-control" id="supermercadologo" name="supermercadologo" accept="image/png, image/jpeg" >

                  <div class="d-flex justify-content-center mt-2">
                    <img src="" id="logo" width="100" height="100">
                  </div>
                  <input type="hidden" name="imagenActual" id="imagenActual">
                </div>
                
            </div>
            
            <div class="mx-auto">
                <button type="submit" name="actualizar" id="actualizar" class="btn btn-primary">  Actualizar información</button>  
            </div>
            
         
         
         
        </div>
          </div>
        </div>
        
       </form>
        <!-- Main row -->
       
        <!-- /.row (main row) -->
      </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  <?php include 'template/footer.php'; ?>
  <?php include 'template/modales/modalqr.php' ?>


</div>
<!-- ./wrapper -->

<?php include 'template/dependenciasjs.php' ?>


<script type="text/javascript">

   var Toast = Swal.mixin({
       toast: true,
       position: 'top-right',
       showConfirmButton: false,
       timer: 3000,
       timerProgressBar: true
     });




  $( document ).ready(function() {

     $.ajax({
      type: "GET",
      url: '../business/configuracionaction.php?metodo=obtener',
      dataType: 'json',
      success: function(data) {

          $("#supermercadoid").val(data[0].supermercadoid)
          $("#supermercadonombre").val(data[0].supermercadonombre)
          $("#supermercadotelefono").val(data[0].supermercadotelefono)
          $("#supermercadocorreo").val(data[0].supermercadocorreo)
          $("#supermercadodireccion").val(data[0].supermercadodireccion)
          $("#imagenActual").val(data[0].supermercadologo)
          $("#logo").attr("src", data[0].supermercadologo);
          
          
          console.log(data[0] .supermercadologo)

      }
    });
  });

  $("#supermercadologo").on("change",function(){

    var imagen = this.files[0];
    var datosImagen = new FileReader;
    datosImagen.readAsDataURL(imagen);
    console.log(imagen)

    $(datosImagen).on("load", function(event) {

          var rutaImagen = event.target.result;

           $("#logo").attr("src", rutaImagen);
           $("#imagenActual").val(imagen.name);

    });    
  })

  $('#formulario-configuracion').on("submit",function(){
    event.preventDefault();

    if($("#supermercadonombre").val() == ""){
      $("#supermercadonombre").focus();
        Toast.fire({
            icon: 'warning',
            title: '<div style=margin-top:0.5rem;>Debe ingresar un nombre.</div>'
        });
        event.preventDefault();
    }else if(!/^[a-zA-Z\s]*$/.test($("#supermercadonombre").val())){
      $("#supermercadonombre").focus();
        Toast.fire({
            icon: 'warning',
            title: '<div style=margin-top:0.5rem;>El nombre ingresado contiene caracteres no válidos, ingrese solo texto.</div>'
        });
        event.preventDefault();
    }
    else if($("#supermercadotelefono").val() == ""){
      $("#supermercadotelefono").focus();
      Toast.fire({
            icon: 'warning',
            title: '<div style=margin-top:0.5rem;>Debe ingresar un teléfono.</div>'
        });
        event.preventDefault();

    }else if(!(/^\d{8}$/.test($("#supermercadotelefono").val()))){
      $("#supermercadotelefono").focus();
      Toast.fire({
            icon: 'warning',
            title: '<div style=margin-top:0.5rem;>El teléfono ingresado debe tener 8 dígitos.</div>'
        });
        event.preventDefault();

    }else if($("#supermercadocorreo").val() == ""){
      $("#supermercadocorreo").focus();
      Toast.fire({
            icon: 'warning',
            title: '<div style=margin-top:0.5rem;>Debe ingresar un correo.</div>'
        });
        event.preventDefault();

    }else if(!(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test($("#supermercadocorreo").val()))){
      $("#supermercadocorreo").focus();
      Toast.fire({
            icon: 'warning',
            title: '<div style=margin-top:0.5rem;>El correo ingresado no es válido.</div>'
        });
        event.preventDefault();
    }else if($("#supermercadodireccion").val() == ""){
      $("#supermercadodireccion").focus()
      Toast.fire({
            icon: 'warning',
            title: '<div style=margin-top:0.5rem;>Debe ingresar una dirección.</div>'
        });
        event.preventDefault();

    }else if(!/^[a-zA-Z0-9\s,'-]*$/.test($("#supermercadodireccion").val())){
      $("#supermercadodireccion").focus()
      Toast.fire({
            icon: 'warning',
            title: '<div style=margin-top:0.5rem;>Dirección no válida. Por favor ingrese solo letras, números y los caracteres</div>'
        });
        event.preventDefault();
    }else if($("#supermercadologo").get(0).files.length === 0){
      $("#supermercadologo").focus()
        Toast.fire({
            icon: 'warning',
            title: '<div style=margin-top:0.5rem;>Debe seleccionar algún logo.</div>'
        });
        event.preventDefault();
    }else{
      var formData = new FormData(this);
    console.log(formData)

     $.ajax({
        url: '../business/configuracionaction.php',
        type: 'POST',
        data: formData,
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        success: function(dataResult) {
          var dataResult = JSON.parse(dataResult);
          console.log(dataResult)
              if(dataResult.statusCode==200){
                           
                  Toast.fire({
                        icon: 'success',
                        title: '<div style=margin-top:0.5rem;>Actualizado con éxito.</div>'
                  });
                     
                     location.reload();
                                                 
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

  //$('#formulario-configuracion').submit(function(event) {
    
    /*
    $.ajax({
        url: 'upload.php',
        type: 'POST',
        data: formData,
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        success: function(response) {
          console.log(response);
        }
      });
    });
*/

</script>

</body>
</html>

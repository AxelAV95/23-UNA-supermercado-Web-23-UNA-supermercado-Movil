<?php 

  include '../business/usuariobusiness.php';
  $usuarioBusiness = new UsuarioBusiness();

  session_start();
  if(isset($_SESSION['sesionActiva'])){
    header("Location: index.php");
  }

 
 ?>



<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Inicio sesión</title>

  <?php include 'template/dependenciascss.php' ?>

  <style>
    body{
      background: #0f0c29;  /* fallback for old browsers */
      background: -webkit-linear-gradient(to right, #24243e, #302b63, #0f0c29);  /* Chrome 10-25, Safari 5.1-6 */
      background: linear-gradient(to right, #24243e, #302b63, #0f0c29); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */

    }

    #btnIniciarSesion:hover{
      animation: pulse; 
      animation-duration: 2s; 
      animation-iteration-count: infinite;


    }
  </style>
</head>
<body class="hold-transition login-page">
<div class="login-box animate__animated animate__backInLeft" style="width: 50%">

   <div class="card" >
            <div class="row no-gutters">
                <div class="col-sm-5 d-flex justify-content-center align-items-center">
                    <img class="card-img animate__animated animate__flipInY animate__delay-1s" src="img/otros/easymarket.png" alt="Suresh Dasari Card">
                </div>
                <div class="col-sm-7">
                    <div class="card-body mt-3">
                        <p class="login-box-msg">Ingrese sus datos</p>

      <form id="formulario-sesion" >
        <input type="hidden" name="metodo" value="iniciarSesion">
        <div class="input-group mb-3">
          <input type="number" class="form-control" name="usuariocedula" id="usuariocedula" placeholder="Cédula">
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas fa-id-card"></span>

            </div>
          </div>
        </div>
        <div class="input-group mb-3">
          <input type="password" name="usuariopassword" class="form-control" placeholder="Contraseña">
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas fa-lock"></span>
            </div>
          </div>
        </div>
        <div class="col">
            <button type="button" id="btnIniciarSesion" name="iniciarSesion"  class="btn btn-primary btn-block">Iniciar sesión</button>
          </div>
      
      </form>
                    </div>
                </div>
            </div>
        </div>

<!-- <div class="card mb-3" >
  <div class="row">
    <div class="col-md-4 col-sm-12 d-flex justify-content-center align-items-center">
      <center>
      <img src="img/otros/easymarket.png" class="card-img" style="width: 100%">
      </center>
    </div>
    <div class="col-md-8 ">
      <div class="card-body">
        <p class="login-box-msg">Ingrese sus datos</p>

      <form id="formulario-sesion" >
        <input type="hidden" name="metodo" value="iniciarSesion">
        <div class="input-group mb-3">
          <input type="number" class="form-control" name="usuariocedula" placeholder="Cédula">
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas fa-id-card"></span>

            </div>
          </div>
        </div>
        <div class="input-group mb-3">
          <input type="password" name="usuariopassword" class="form-control" placeholder="Contraseña">
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas fa-lock"></span>
            </div>
          </div>
        </div>
        <div class="col">
            <button type="button" id="btnIniciarSesion" name="iniciarSesion"  class="btn btn-primary btn-block">Iniciar sesión</button>
          </div>
      
      </form>
      </div>
    </div>
  </div>
</div> -->

  <!-- /.card -->
</div>
<!-- /.login-box -->

<!-- jQuery -->
<script src="plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="dist/js/adminlte.min.js"></script>
<script src="plugins/sweetalert2/sweetalert2.min.js"></script>
<script src="plugins/toastr/toastr.min.js"></script>

<script type="text/javascript">
  var Toast = Swal.mixin({
       toast: true,
       position: 'top-end',
       showConfirmButton: false,
       timer: 3000
     });
</script>

<?php 
  //ALERTAS
  echo '<script>';
  echo " var Toast = Swal.mixin({
       toast: true,
       position: 'top',
       showConfirmButton: false,
       timer: 5000,
       timerProgressBar: true
     });";
 if($_GET['mensaje'] == 2){ //error
    echo " Toast.fire({
        icon: 'error',
        title: '<div style=margin-top:0.1rem;>Campos vacios.</div>'
      })";
  }else if($_GET['mensaje'] == 3){ //error
    echo " Toast.fire({
        icon: 'error',
        title: '<div style=margin-top:0.1rem;>El usuario ingresado no existe.</div>'
      })";
  }else if($_GET['mensaje'] == 4){ //error
    echo " Toast.fire({
        icon: 'error',
        title: '<div style=margin-top:0.1rem;>No se pudo iniciar sesión, correo o contraseña incorrecta.</div>'
      })";
  }
  echo "</script>";

?>


<script type="text/javascript">


  const animateCSS = (element, animation, prefix = 'animate__') =>
  // We create a Promise and return it
  new Promise((resolve, reject) => {
    const animationName = `${prefix}${animation}`;
    const node = document.querySelector(element);

    node.classList.add(`${prefix}animated`, animationName);

    // When the animation ends, we clean the classes and resolve the Promise
    function handleAnimationEnd(event) {
      event.stopPropagation();
      node.classList.remove(`${prefix}animated`, animationName);
      resolve('Animation ended');
    }

    node.addEventListener('animationend', handleAnimationEnd, {once: true});
  });
  
  $("#btnIniciarSesion").on("click", function(){
    
    var cedula= $("#formulario-sesion div").children().get(0);
    var pass = $("#formulario-sesion div").children().get(4);
    

    if($(cedula).val() == ""){
      $("#formulario-sesion div").children().get(0).focus()
        Toast.fire({
                    icon: 'warning',
                    title: '<div style=margin-top:0.5rem;>Debe ingresar una cédula.</div>'
              })
    }else if($(pass).val() == ""){
        $("#formulario-sesion div").children().get(4).focus()
        Toast.fire({
                    icon: 'warning',
                    title: '<div style=margin-top:0.5rem;>Debe ingresar una contraseña.</div>'
              })
    }else{
      var data = $("#formulario-sesion").serialize();

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
                        title: '<div style=margin-top:0.5rem;>Inicio de sesión exitoso.</div>'
                  });

                    animateCSS('.card', 'flipOutX').then((message) => {

                         
                          
                    });
                
                setTimeout(function() {
                    window.location.href = "index.php";
              }, 500);

                
                    
                    
                                                 
              }
              if(dataResult.statusCode==400){
                    Toast.fire({
                        icon: 'error',
                        title: '<div style=margin-top:0.5rem;>La contraseña ingresada no es válida.</div>'
                  })
              }

              if(dataResult.statusCode==404){
                    Toast.fire({
                        icon: 'error',
                        title: '<div style=margin-top:0.5rem;>No existe una cuenta asociada a estos datos.</div>'
                  })
              }
                        
          }
        });

    }

  })

  $('#usuariocedula').change(function() {
    var cedula = $(this).val();

  //  alert(cedula)

    
    $.ajax({
      type: "GET",
      url: '../business/usuarioaction.php?metodo=verificarUsuario&empleadocedula='+ cedula ,
      dataType: 'json',
      success: function(data) {
       
      
        if(data.statusCode==200){
            
             $('#btnIniciarSesion').prop('disabled', false);
             

        }else{
           Toast.fire({
                        icon: 'error',
                        title: '<div style=margin-top:0.5rem;>No se encuentra registrada ninguna cuenta de usuario a esta cédula.</div>'
                  });
            $('#usuariocedula').focus()
            $('#btnIniciarSesion').prop('disabled', true);
        }
        

      }
    })

  });


</script>
</body>
</html>

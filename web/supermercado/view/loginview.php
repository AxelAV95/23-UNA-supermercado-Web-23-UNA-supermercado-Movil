<?php 

  include '../business/usuariobusiness.php';
  $usuarioBusiness = new UsuarioBusiness();

 
 ?>



<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Inicio sesión</title>

  <!-- Google Font: Source Sans Pro -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="plugins/fontawesome-free/css/all.min.css">
  <!-- icheck bootstrap -->
  <link rel="stylesheet" href="plugins/icheck-bootstrap/icheck-bootstrap.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="dist/css/adminlte.min.css">
   <link
    rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"
  />
  <link rel="stylesheet" href="plugins/sweetalert2-theme-bootstrap-4/bootstrap-4.min.css">
  <link rel="stylesheet" href="plugins/toastr/toastr.min.css">

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
<div class="login-box animate__animated animate__backInDown">
  <!-- /.login-logo -->
  <div class="card card-outline card-primary">
    <div class="card-header text-center">
      <a href="../index2.html" class="h1"><b>Supermercado</b></a>
    </div>
    <div class="card-body">
      <p class="login-box-msg">Ingrese sus datos</p>

      <form action="../business/usuarioaction.php" method="post">
        <div class="input-group mb-3">
          <input type="email" class="form-control" name="usuariocorreo" placeholder="Correo">
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas fa-envelope"></span>
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
            <button type="submit" id="btnIniciarSesion" name="iniciarSesion" class="btn btn-primary btn-block">Iniciar sesión</button>
          </div>
      
      </form>

    
    </div>
    <!-- /.card-body -->
  </div>
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
</body>
</html>

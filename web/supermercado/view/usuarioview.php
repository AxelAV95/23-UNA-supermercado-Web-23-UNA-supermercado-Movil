<?php  

    include '../business/usuariobusiness.php';
    include '../business/tipousuariobusiness.php';

   

    $usuarioBusiness = new UsuarioBusiness();
    $usuarios = $usuarioBusiness->getAllTBusuarios();
    $tipousuarioBusiness = new TipoUsuarioBusiness();
    $tipousuarios = $tipousuarioBusiness->getAllTBTipoUsuarios();
   // echo __DIR__;
   // var_dump($categorias);


?>

<?php 
  // include 'template/sesion.php';
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
         <span class="brand-text font-weight-light">Nombre de Super</span>
      <img src="img/otros/logo.png" class="img-fluid" alt="Responsive image" width="30px" height="30px" style="margin-left: 1rem;">
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
                <button class="btn btn-primary" data-toggle="modal" data-target="#modalAgregarUsuario">

                    Agregar usuario

                  </button>
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                 <table id="usuarios" class="tabla-usuarios table table-bordered table-hover">
                  <thead>
                  <tr>
                    <th>Nombre</th>
                    <th>Teléfono</th>
                    <th>Correo</th>
                    <th>Contraseña</th>
                    <th>Tipo</th>
                    <th>Acciones</th>
                    
                  </tr>
                  </thead>
                  <tbody>
                    <?php 
                        foreach($usuarios as $usuario){
                          echo '<tr>';
                          echo '<td>'.$usuario['usuarionombre'].'</td>';
                         
                          echo '<td>'.$usuario['usuariotelefono'].'</td>';
                         
                          echo '<td>'.$usuario['usuariocorreo'].'</td>';
                          
                          echo '<td class="hidetext">'.$usuario['usuariopassword'].'</td>';
                          echo '<td>';
                          if($usuario['tipoid'] == 1){
                            echo '<span class="badge badge-light">Administrador</span>';
                          }else{
                            echo '<span class="badge badge-light">Empleado</span>';
                          }
                          echo '</td>';
                          echo '<td>';
                          echo "<div class='btn-group'><button class='btn btn-warning btnEditarUsuario' usuarioid='".$usuario["usuarioid"]."' usuarionombre='".$usuario['usuarionombre']."' usuariotelefono='".$usuario['usuariotelefono']."'  usuariocorreo='".$usuario["usuariocorreo"]."' usuariopassword='".$usuario["usuariopassword"]."' tipoid='".$usuario["tipoid"]."' data-toggle='modal' data-target='#modalEditarUsuario'><i class='fa fa-pencil-alt'></i></button><button class='btn btn-danger btnEliminarUsuario' usuarioid='".$usuario["usuarioid"]."' usuariotelefono='".$usuario["usuariotelefono"]."' usuariocorreo='".$usuario["usuariocorreo"]."' usuariopassword='".$usuario["usuariopassword"]."' tipoid='".$usuario["tipoid"]."' ><i class='fa fa-times'></i></button></div>";
                          echo '</td>';
                          echo '</tr>';
                        }




                    ?>
                
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
<?php 
  //ALERTAS
  echo '<script>';
  echo " var Toast = Swal.mixin({
       toast: true,
       position: 'top-right',
       showConfirmButton: false,
       timer: 3000,
       timerProgressBar: true
     });";
  if($_GET['mensaje']==1){ //insertar
    echo "Toast.fire({
         icon: 'success',

        title: '<div style=margin-top:0.5rem;>Insertado con éxito.</div>'
     });";
  }else if($_GET['mensaje']==2){ //actualizar
    echo "Toast.fire({
         icon: 'success',
        title: '<div style=margin-top:0.5rem;>Actualizado con éxito.</div>'
     });";
  }else if($_GET['mensaje'] == 3){ //eliminar
    echo "Toast.fire({
         icon: 'success',
        title: '<div style=margin-top:0.5rem;>Eliminado con éxito.</div>'
     });";
  }else if($_GET['mensaje'] == 4){ //error
    echo " Toast.fire({
        icon: 'error',
        title: '<div style=margin-top:0.5rem;>Error al efectuar la operación.</div>'
      })";
  }
  echo "</script>";

?>

<script>
     // var Toast = Swal.mixin({
     //   toast: true,
     //   position: 'top-end',
     //   showConfirmButton: false,
     //   timer: 3000
     // });
 
</script>

<script>
  $(function () {

    $('#usuarios').DataTable({
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
</script>

<script>
  $(".tabla-usuarios tbody").on("click", "button.btnEditarUsuario", function(){

    var usuarioid = $(this).attr("usuarioid");
    var usuarionombre = $(this).attr("usuarionombre");
    var usuariotelefono = $(this).attr("usuariotelefono");
    var usuariocorreo =  $(this).attr("usuariocorreo");
    var usuariopassword = $(this).attr("usuariopassword");
    var tipoid =  $(this).attr("tipoid");
    alert(usuarioid);
    $("#modalEditarUsuario #usuarioid").val(usuarioid);
    $("#modalEditarUsuario #usuarionombre").val(usuarionombre);
    $("#modalEditarUsuario #usuariotelefono").val(usuariotelefono);
    $("#modalEditarUsuario #usuariocorreo").val(usuariocorreo);
    $("#modalEditarUsuario #usuariopassword").val(usuariopassword);
    $("#modalEditarUsuario #tipoid").val(tipoid);
  

});

  
$(".tabla-usuarios tbody").on("click", "button.btnEliminarUsuario", function(){

  var usuarioid = $(this).attr("usuarioid");
  //aquiii??
  alert(usuarioid);
 Swal.fire({
       title: '¿Desea eliminar el usuario?',
       text: "No se podrá revertir el cambio",
       icon: 'warning',
       showCancelButton: true,
       confirmButtonColor: '#3085d6',
       cancelButtonColor: '#d33',
        cancelButtonText: "Cancelar",
       confirmButtonText: 'Eliminar'
       }).then((result) => {
         if (result.isConfirmed) {
           window.location = "../../business/usuarioaction.php?eliminar=true&usuarioid="+usuarioid;
    
         }
   })


});


$('#modalEditarUsuario #mostrarPassword').click(function(){

  $(this).is(':checked') ? $('#modalEditarUsuario #usuariopassword').attr('type', 'text') : $('#modalEditarUsuario #usuariopassword').attr('type', 'password');
});



</script>
</body>
</html>


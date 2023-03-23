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

  <!-- Google Font: Source Sans Pro -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="plugins/fontawesome-free/css/all.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
  <!-- Tempusdominus Bootstrap 4 -->
  <link rel="stylesheet" href="plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css">
  <!-- iCheck -->
  <link rel="stylesheet" href="plugins/icheck-bootstrap/icheck-bootstrap.min.css">
  <!-- JQVMap -->
  <link rel="stylesheet" href="plugins/jqvmap/jqvmap.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="dist/css/adminlte.min.css">
  <!-- overlayScrollbars -->
  <link rel="stylesheet" href="plugins/overlayScrollbars/css/OverlayScrollbars.min.css">
  <!-- Daterange picker -->
  <link rel="stylesheet" href="plugins/daterangepicker/daterangepicker.css">
  <!-- summernote -->
  <link rel="stylesheet" href="plugins/summernote/summernote-bs4.min.css">

  <!-- DataTables -->
  <link rel="stylesheet" href="plugins/datatables-bs4/css/dataTables.bootstrap4.min.css">
  <link rel="stylesheet" href="plugins/datatables-responsive/css/responsive.bootstrap4.min.css">
  <link rel="stylesheet" href="plugins/datatables-buttons/css/buttons.bootstrap4.min.css">

   <!-- DataTables -->
  <link rel="stylesheet" href="plugins/datatables-bs4/css/dataTables.bootstrap4.min.css">
  <link rel="stylesheet" href="plugins/datatables-responsive/css/responsive.bootstrap4.min.css">
  <link rel="stylesheet" href="plugins/datatables-buttons/css/buttons.bootstrap4.min.css">
  <link rel="stylesheet" href="plugins/sweetalert2-theme-bootstrap-4/bootstrap-4.min.css">
  <link rel="stylesheet" href="plugins/toastr/toastr.min.css">
  <link
    rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"
  />
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
      <span class="brand-text font-weight-light">Panel de administración SO</span>
    </a>

    <!-- Sidebar -->
    <div class="sidebar" style="background: #0f0c29;  /* fallback for old browsers */
background: -webkit-linear-gradient(to right, #24243e, #302b63, #0f0c29);  /* Chrome 10-25, Safari 5.1-6 */
background: linear-gradient(to right, #24243e, #302b63, #0f0c29); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
">
      <!-- Sidebar user panel (optional) -->
      <div class="user-panel mt-3 pb-3 mb-3 d-flex justify-content-center">
      
        <div class="info d-flex justify-content-between">
          <i class="fas fa-user text-light mr-3" style="font-size: 23px;"></i>
          <a href="#" class="d-block"><?php // echo $usuario ?></a>
        </div>
      </div>

      <!-- Sidebar Menu -->
      <nav class="mt-2">
        <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
          <!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->
          <li class="nav-item ">
            <a href="index.php" class="nav-link ">
              <i class="nav-icon fas fa-tachometer-alt"></i>
              <p>
                Dashboard
              </p>
            </a>
          </li>

          <li class="nav-item ">
            <a href="productoview.php" class="nav-link ">
             <i class="nav-icon fab fa-product-hunt"></i>
              <p>
                Productos
              </p>
            </a>
          </li>

           <li class="nav-item ">
            <a href="categoriaview.php" class="nav-link">
              <i class="nav-icon fas fa-list"></i>
              <p>
                Categorías
              </p>
            </a>
          </li>

           <li class="nav-item ">
            <a href="proveedorview.php" class="nav-link">
            <i class="nav-icon fas fa-people-carry"></i>
              <p>
                Proveedores
              </p>
            </a>
          </li>
         
          <li class="nav-item">
            <a href="" class="nav-link active">
               <i class="nav-icon fas fa-users"></i>
              <p>
                Usuarios
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="usuarioview.php" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <i class="fas fa-users-cog nav-icon"></i>
                  <p>Gestionar</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="tipousuarioview.php" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                   <i class="nav-icon fas fa-list"></i>
                  <p>Tipos</p>
                </a>
              </li>
            </ul>
          </li>
         
         <li class="nav-item">
            <a href="empleadoview.php" class="nav-link">
              <i class="nav-icon fas fa-user-edit"></i>
              <p>
                Empleados
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="empleadoview.php" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <i class="fas fa-users-cog nav-icon"></i>
                  <p>Gestionar</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="tipoempleadoview.php" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <i class="nav-icon fas fa-list"></i>
                  <p>Tipos</p>
                </a>
              </li>
            </ul>
          </li>

          <li class="nav-item">
            <a href="clienteview.php" class="nav-link">
              <i class="nav-icon fas fa-address-book"></i>
              <p>
                Clientes
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="clienteview.php" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <i class="fas fa-users-cog nav-icon"></i>
                  <p>Gestionar</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="tipoclienteview.php" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                   <i class="nav-icon fas fa-list"></i>
                  <p>Tipos</p>
                </a>
              </li>
            </ul>
          </li>

           <li class="nav-item">
            <a href="" class="nav-link">
               <i class="nav-icon fas fa-file"></i>
              <p>
                  Reportes
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="reporteproveedorview.php" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                   <i class="nav-icon fas fa-people-carry"></i>
                  <p>Proveedores</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="reporteclienteview.php" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                 <i class="nav-icon fas fa-address-book"></i>
                  <p>Clientes</p>
                </a>
              </li>
            </ul>
          </li>

          <li class="nav-item">
            <a href="" class="nav-link">
              <i class="nav-icon fas fa-cog"></i>
              <p>
                Configuración
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="configuracionview.php" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <i class="nav-icon fas fa-info-circle"></i>
                  <p>Información general</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="descuentosview.php" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                 <i class="nav-icon fas fa-percent"></i>
                  <p>Descuentos</p>
                </a>
              </li>
            </ul>
          </li>
         

         <li class="nav-item ">
            <a href="../business/usuarioaction.php?cerrarSesion=true" class="nav-link">
               <i class="nav-icon fas fa-sign-out-alt"></i>
              <p>
                Cerrar sesión
              </p>
            </a>
          </li>

        </ul>
      </nav>
      <!-- /.sidebar-menu -->
    </div>
    <!-- /.sidebar -->
  </aside>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">Usuarios</h1>
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

<div id="modalAgregarUsuario" class="modal fade" role="dialog">
  
  <div class="modal-dialog">

    <div class="modal-content">

        <div class="modal-header">
          <h4 class="modal-title">Agregar usuario</h4>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>

        <div class="modal-body">

          <div class="box-body">

            <form method="POST" action="../../business/usuarioaction.php"  enctype="multipart/form-data">
           

              <div class="form-group">
                <label >Nombre:</label>
                <input type="text" class="form-control" name="usuarionombre" id="usuarionombre" placeholder="Ingrese un nombre">
               
              </div>
              
              <div class="form-group">
                <label >Telefono:</label>
                <input type="text" class="form-control" name="usuariotelefono" id="usuariotelefono" placeholder="Ingrese un telefono">
               
              </div>
              <div class="form-group">
                <label >Correo:</label>
                <input type="text" class="form-control" name="usuariocorreo" id="usuariocorreo" placeholder="Ingrese un correo">
               
              </div>
              <div class="form-group">
                <label >Password:</label>
                <input type="text" class="form-control" name="usuariopassword" id="usuariopassword" placeholder="Ingrese contraseña">
                      </div>

                <label>Tipo de Usuario: </label>
              <select class="tipoid form-control" name="tipoid" id="tipoid" >

                <option selected>Seleccione el tipo de usuario</option>
              
                  <?php foreach($tipousuarios as $tipousuario){

                    echo ' <option value="'.$tipousuario['tipoid'].'" class="badge badge-pill badge-warning" style="font-size: 15px;">'.$tipousuario['tipodescripcion'].'</option>';
                  }?>

            </select>
            <br><br>

             
              
             <center><button type="submit" name="insertar" class="btn btn-primary">Insertar</button></center> 
            </form>

        </div>

      </div>

    </div>
  </div>

</div>

<div id="modalEditarUsuario" class="modal fade" role="dialog">

    <div class="modal-dialog">

      <div class="modal-content">

        <div class="modal-header">
          <h4 class="modal-title">Editar usuario</h4>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>

        <div class="modal-body">

          <div class="box-body">

            <form method="POST" action="../../business/usuarioaction.php" enctype="multipart/form-data">

              <div class="form-group">
                <input type="hidden" name="usuarioid" id="usuarioid">

              </div>

              <div class="form-group">
                <label >Nombre:</label>
                <input type="text" class="form-control" name="usuarionombre" id="usuarionombre" placeholder="Ingrese un nombre">
               
              </div>
              
              <div class="form-group">
                <label >Telefono:</label>
                <input type="text" class="form-control" name="usuariotelefono" id="usuariotelefono" placeholder="Ingrese un telefono">
               
              </div>
              <div class="form-group">
                <label >Correo:</label>
                <input type="text" class="form-control" name="usuariocorreo" id="usuariocorreo" placeholder="Ingrese un correo">
               
              </div>
              <div class="form-group">
                <label >Password:</label>
                
                
                <input type="password" class="form-control" name="usuariopassword" id="usuariopassword" placeholder="Ingrese contraseña">
                <div class="form-group">
                   <label class="">
                      <input type="checkbox" id="mostrarPassword" >  Mostrar contraseña
                    </label>

                </div>
                      </div>

              <label>Tipo de usuario: </label>
              <select class="tipoid form-control" name="tipoid" id="tipoid">

                <option selected>Seleccione el tipo de usuario</option>
              
                  <?php foreach($tipousuarios as $tipousuario){

                    echo ' <option value="'.$tipousuario['tipoid'].'" class="badge badge-pill badge-warning" style="font-size: 15px;">'.$tipousuario['tipodescripcion'].'</option>';
                  }?>

            </select>
            <br>
              <center><button type="submit" name="actualizar" class="btn btn-primary">Actualizar</button></center>
            </form>

          </div>

        </div>

      </div>
    </div>

  </div>






<!-- jQuery -->
<script src="plugins/jquery/jquery.min.js"></script>
<!-- jQuery UI 1.11.4 -->
<script src="plugins/jquery-ui/jquery-ui.min.js"></script>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<script>
  $.widget.bridge('uibutton', $.ui.button)
</script>
<!-- Bootstrap 4 -->
<script src="plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- ChartJS -->
<script src="plugins/chart.js/Chart.min.js"></script>
<!-- Sparkline -->
<script src="plugins/sparklines/sparkline.js"></script>
<!-- JQVMap -->
<script src="plugins/jqvmap/jquery.vmap.min.js"></script>
<script src="plugins/jqvmap/maps/jquery.vmap.usa.js"></script>
<!-- jQuery Knob Chart -->
<script src="plugins/jquery-knob/jquery.knob.min.js"></script>
<!-- daterangepicker -->
<script src="plugins/moment/moment.min.js"></script>
<script src="plugins/daterangepicker/daterangepicker.js"></script>
<!-- Tempusdominus Bootstrap 4 -->
<script src="plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"></script>
<!-- Summernote -->
<script src="plugins/summernote/summernote-bs4.min.js"></script>
<!-- overlayScrollbars -->
<script src="plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
<!-- AdminLTE App -->
<script src="dist/js/adminlte.js"></script>
<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<script src="dist/js/pages/dashboard.js"></script>
<!-- DataTables  & Plugins -->
<script src="plugins/datatables/jquery.dataTables.min.js"></script>
<script src="plugins/datatables-bs4/js/dataTables.bootstrap4.min.js"></script>
<script src="plugins/datatables-responsive/js/dataTables.responsive.min.js"></script>
<script src="plugins/datatables-responsive/js/responsive.bootstrap4.min.js"></script>
<script src="plugins/datatables-buttons/js/dataTables.buttons.min.js"></script>
<script src="plugins/datatables-buttons/js/buttons.bootstrap4.min.js"></script>
<script src="plugins/jszip/jszip.min.js"></script>
<script src="plugins/pdfmake/pdfmake.min.js"></script>
<script src="plugins/pdfmake/vfs_fonts.js"></script>
<script src="plugins/datatables-buttons/js/buttons.html5.min.js"></script>
<script src="plugins/datatables-buttons/js/buttons.print.min.js"></script>
<script src="plugins/datatables-buttons/js/buttons.colVis.min.js"></script>
<script src="plugins/sweetalert2/sweetalert2.min.js"></script>
<script src="plugins/toastr/toastr.min.js"></script>
<script src="https://unpkg.com/web-audio-daw"></script>
<script src="dist/js/notificacion.js"></script>

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


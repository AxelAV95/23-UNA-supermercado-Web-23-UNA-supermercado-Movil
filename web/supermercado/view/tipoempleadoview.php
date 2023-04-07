<?php 

  include '../business/tipoempleadobusiness.php';
 // $tipoUsuarioBusiness = new TipoUsuarioBusiness();
  //$tipos = $tipoUsuarioBusiness->getAllTBTipoUsuarios();

  //print_r($tipos)


 ?>

 <?php 
   include 'template/sesion.php';
  ?>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Tipos de empleados | Dashboard</title>

 <?php include 'template/dependenciascss.php' ?>
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
            <h1 class="m-0 text-center">Tipo de empleados</h1>
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
              <div class="card-header">
                <button class="btn btn-primary" data-toggle="modal" data-target="#modalAgregarTipo">

                    Agregar tipo

                  </button>
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                <table id="tabla-tipos" class="tabla-tipos table table-bordered table-hover">
                  <thead>
                  <tr>
                    <th>Descripción</th>
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

 <?php include 'template/modales/modalagregartipoempleado.php' ?>

<?php include 'template/modales/modaleditartipoempleado.php' ?>

<?php include 'template/modales/modalqr.php' ?>



</div>
<!-- ./wrapper -->

<?php include 'template/dependenciasjs.php' ?>




<script src="dist/js/tipoempleado.js">
</script>


</body>
</html>

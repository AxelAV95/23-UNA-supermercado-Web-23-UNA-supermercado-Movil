<?php  
 
    include '../business/proveedorbusiness.php';

    $proveedorBusiness = new ProveedorBusiness();
    $proveedores = $proveedorBusiness->getAllTBProveedores();



?>

<?php 

  ?>


<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Proveedores | Dashboard</title>

 <?php include 'template/dependenciascss.php' ?>

  <script type="text/javascript" src="wforms.js"></script>
</head>
<body class="hold-transition sidebar-mini layout-fixed">
<div class="wrapper">


  <!-- Navbar -->
  <?php include 'template/header.php' ?>
  <!-- /.navbar -->

  <!-- Main Sidebar Container -->
<aside class="main-sidebar sidebar-dark-primary elevation-4">
    <!-- Brand Logo -->
    <a href="index.html" class="brand-link d-flex justify-content-center ">

       <span class="brand-text font-weight-light">Nombre de Super</span>
      <img src="img/otros/logo.png" class="img-fluid " alt="Responsive image" width="30px" height="30px" style="margin-left: 1rem;">
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
            <h1 class="m-0 text-center">Proveedores</h1>
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
                <button class="btn btn-primary" data-toggle="modal" data-target="#modalAgregarProveedor">

                    Agregar proveedor

                  </button>
              </div>
              <!-- /.card-header -->
              <div class="card-body">
              <table id="proveedores" class="tabla-proveedores table table-bordered table-hover">
                  <thead>
                  <tr>
                    <th>Nombre</th>
                    <th>Dirección</th>
                    <th>Correo</th>
                    <th>Teléfono</th>
                    <th>Acciones</th>
                    
                  </tr>
                  </thead>
                  <tbody>
                    <?php 
                        foreach($proveedores as $proveedor){
                          echo '<tr>';
                          echo '<td>'.$proveedor['proveedornombre'].'</td>';
                         
                          echo '<td>'.$proveedor['proveedordireccion'].'</td>';
                         
                          echo '<td>'.$proveedor['proveedorcorreo'].'</td>';
                          
                          echo '<td>'.$proveedor['proveedortelefono'].'</td>';
                          echo '<td>';
                          echo "<div class='btn-group'><button class='btn btn-warning btnEditarProveedor' proveedorid='".$proveedor["proveedorid"]."' proveedornombre='".$proveedor['proveedornombre']."' proveedordireccion='".$proveedor['proveedordireccion']."'  proveedorcorreo='".$proveedor["proveedorcorreo"]."'  proveedortelefono='".$proveedor["proveedortelefono"]."' data-toggle='modal' data-target='#modalEditarProveedor'><i class='fa fa-pencil-alt'></i></button><button class='btn btn-danger btnEliminarProveedor' proveedorid='".$proveedor["proveedorid"]."' proveedordireccion='".$proveedor["proveedordireccion"]."' proveedorcorreo='".$proveedor["proveedorcorreo"]."' proveedortelefono='".$proveedor["proveedortelefono"]."' ><i class='fa fa-times'></i></button></div>";
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

<?php include 'template/modales/modalagregarproveedor.php' ?>
<?php include 'template/modales/modaleditarproveedor.php' ?>
<?php include 'template/modales/modalqr.php' ?>




<?php include 'template/dependenciasjs.php' ?>

<script type="text/javascript" src="dist/js/proveedor.js"></script>

<?php 
  //ALERTS
  echo '<script>';
  echo " var Toast = Swal.mixin({
       toast: true,
       position: 'top-right',
       showConfirmButton: false,
       timer: 3000,
       timerProgressBar: true
     });";
  if($_GET['mensaje']==1){ //insertar proveedor
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



  </body>
</html>
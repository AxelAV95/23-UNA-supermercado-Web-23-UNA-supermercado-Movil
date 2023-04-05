<?php  
 
    include '../business/categoriabusiness.php';

    $categoriaBusiness = new CategoriaBusiness();
    $categorias = $categoriaBusiness->getAllTBCategorias();



?>

<?php 
 //  include 'template/sesion.php';
  ?>


<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Categorías | Dashboard</title>

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
    <a href="index.html" class="brand-link d-flex justify-content-center ">

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
            <h1 class="m-0 text-center">Categorías</h1>
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
                <button class="btn btn-primary" data-toggle="modal" data-target="#modalAgregarCategoria">

                    Agregar categoría
          
                  </button>
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                 <table id="categorias" class="tabla-categorias table table-bordered table-hover">
                  <thead>
                  <tr>
                    <th>Descripción</th>
                    <th>Acciones</th>
                    
                  </tr>
                  </thead>
                  <tbody>
                    <?php 
                    
                        foreach($categorias as $categoria){
                          echo '<tr>';
                          echo '<td>'.$categoria['categorianombre'].'</td>';
                          echo '<td>';
                          echo "<div class='btn-group'> <button class='btn btn-warning btnEditarCategoria' categoriaid='".$categoria["categoriaid"]."' categorianombre='".$categoria['categorianombre']."' data-toggle='modal' data-target='#modalEditarCategoria'> <i class='fa fa-pencil-alt'></i></button><button class='btn btn-danger  btnEliminarCategoria' categoriaid='".$categoria["categoriaid"]."' categorianombre='".$categoria['categorianombre']."'><i class='fa fa-times'></i>  </button> </div>";

                          echo '</td>';
                                      echo '</tr>';
                        }
                        ?>
                      </tr>

                    

                
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
 


</div>
  </div>

</div>

 <?php include 'template/footer.php'; ?>

<?php include 'template/modales/modalagregarcategoria.php' ?>
<?php include 'template/modales/modaleditarcategoria.php' ?>
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
  if($_GET['success']=="inserted"){ //insertar
    echo "Toast.fire({
         icon: 'success',

        title: '<div style=margin-top:0.5rem;>Insertado con éxito.</div>'
     });";
  }else if($_GET['success']=="update"){ //actualizar
    echo "Toast.fire({
         icon: 'success',
        title: '<div style=margin-top:0.5rem;>Actualizado con éxito.</div>'
     });";
  }else if($_GET['success'] == "deleted"){ //eliminar
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


<script src="dist/js/categoria.js">
</script>


</body>
</html>

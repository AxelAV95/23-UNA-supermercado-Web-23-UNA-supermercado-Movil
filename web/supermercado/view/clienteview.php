<?php  
 
    include '../business/categoriabusiness.php';

    $categoriaBusiness = new CategoriaBusiness();
    $categorias = $categoriaBusiness->getAllTBCategorias();



?>

<?php 
  // include 'template/sesion.php';
  ?>


<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Clientes | Dashboard</title>

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
  </aside>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-12">
            <h1 class="m-0 text-center">Clientes</h1>
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
                    /*
                        foreach($categorias as $categoria){
                          echo '<tr>';
                          echo '<td>'.$categoria['categoriadescripcion'].'</td>';
                          echo '<td>';
                          echo "<div class='btn-group'><button class='btn btn-warning btnEditarCategoria' categoriaid='".$categoria["categoriaid"]."' descripcion='".$categoria['categoriadescripcion']."'  imagen='".$categoria["categoriaimg"]."' codigo='".$categoria["categoriacodigo"]."' data-toggle='modal' data-target='#modalEditarCategoria'><i class='fa fa-pencil'></i></button><button class='btn btn-danger btnEliminarCategoria' categoriaid='".$categoria["categoriaid"]."' imagen='".$categoria["categoriaimg"]."' codigo='".$categoria["categoriacodigo"]."' ><i class='fa fa-times'></i></button></div>";
                          echo '</td>';
                          echo '</tr>';
                        }


                      */

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
<?php include 'template/modales/modalagregarcliente.php' ?>

<?php include 'template/modales/modaleditarlciente.php' ?>
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
  $(function () {

    $('#categorias').DataTable({
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
  $(".tabla-categorias tbody").on("click", "button.btnEditarCategoria", function(){

    var idcategoria = $(this).attr("categoriaid");
    var descripcion = $(this).attr("descripcion");
    var img = $(this).attr("imagen");
    var codigo =  $(this).attr("codigo");
  
    $("#modalEditarCategoria #categoriaid").val(idcategoria);
    $("#modalEditarCategoria #categoriadescripcion").val(descripcion);
    $("#modalEditarCategoria #categoriacodigo").val(codigo);
    $("#modalEditarCategoria #imagenActual").val(img);
    $("#modalEditarCategoria .previsualizar").attr("src", img);
  

});

  
$(".tabla-categorias tbody").on("click", "button.btnEliminarCategoria", function(){

  var categoriaid = $(this).attr("categoriaid");
  var imagen = $(this).attr("imagen");
  var codigo = $(this).attr("codigo");

      Swal.fire({
        title: '¿Desea eliminar la categoría?',
        text: "No se podrá revertir el cambio",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
         cancelButtonText: "Cancelar",
        confirmButtonText: 'Eliminar'
        }).then((result) => {
          if (result.isConfirmed) {
            window.location = "../../business/categoriaaction.php?eliminar=true&id="+categoriaid+"&imagen="+imagen+"&codigo="+codigo;
      
          }
    })
 

});


$(".nuevaImagen").change(function(){

  var imagen = this.files[0];
  
  /*=============================================
    VALIDAMOS EL FORMATO DE LA IMAGEN SEA JPG O PNG
    =============================================*/

    if(imagen["type"] != "image/jpeg" && imagen["type"] != "image/png"){

      $(".nuevaImagen").val("");

       Toast.fire({
          title: "Error al subir la imagen",
          text: "¡La imagen debe estar en formato JPG o PNG!",
          type: "error",
          confirmButtonText: "¡Cerrar!"
        });

    }else if(imagen["size"] > 2000000){

      $(".nuevaImagen").val("");

       Toast.fire({
          title: "Error al subir la imagen",
          text: "¡La imagen no debe pesar más de 2MB!",
          type: "error",
          confirmButtonText: "¡Cerrar!"
        });

    }else{

      var datosImagen = new FileReader;
      datosImagen.readAsDataURL(imagen);

      $(datosImagen).on("load", function(event){

        var rutaImagen = event.target.result;

        $(".previsualizar").attr("src", rutaImagen);

      })

    }
})
</script>
</body>
</html>

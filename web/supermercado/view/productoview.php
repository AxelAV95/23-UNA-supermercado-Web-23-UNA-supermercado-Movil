<?php 
  
  include '../business/productobusiness.php';
  include '../business/categoriabusiness.php';
  $productoBusiness = new ProductoBusiness();
  $productos = $productoBusiness->getAllTBProductos();
  $categoriabusiness = new Categoriabusiness();
  $categorias = $categoriabusiness->getAllTBCategorias();

  //var_dump($productos);

?>

<?php 
 //  include 'template/sesion.php';
  ?>


<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Productos | Dashboard</title>

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
            <h1 class="m-0 text-center">Producto</h1>
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
                <div class="box-header with-border">

                  <button class="btn btn-primary" id="agregarProducto" data-toggle="modal" data-target="#modalAgregarProducto">

                    Agregar producto

                  </button>

               </div>

              </div>
              <!-- /.card-header -->
              <div class="card-body">
                <table id="tabla-productos" class="tabla-productos table table-bordered table-hover">
                  <thead>
                  <tr>
                    <th>Nombre</th>
                    <th>Precio</th>
                    <th>Estado</th>
                    <th>Categoria</th>
                    <th>Acciones</th>
                    
                  </tr>
                  </thead>
                  </center>
                  <tbody>
                    <?php 
                    /*
                        foreach($productos as $producto){
                          echo '<tr>';
                          echo '<td>'.$producto['productonombre'].'</td>';
                          echo '<td>'.$producto['productoprecio'].'</td>';

                          echo '<td>';
                          if($producto['productoestado'] == 1){
                            echo '<span class="badge badge-success">Disponible</span>';
                          }else if($producto['productoestado'] == 2){
                            echo '<span class="badge badge-warning">No disponible</span>';
                          }
                          echo '</td>';
                         echo '<td >';
                         echo '<span class="badge badge-light">'.$categoriabusiness->getDescripcionCategoria($producto['productocategoriaid'])[0]['categoriadescripcion'].'</span>';
                          

                           echo '</td>';
                          echo '<td>';
                          echo "<div class='btn-group'><button class='btn btn-warning btnEditarProducto' productoid='" . $producto["productoid"] . "' imagen='" . $producto["productoimg"] . "' productonombre='" . $producto['productonombre'] . "' productoprecio='" . $producto['productoprecio'] . "' productocodigo='" . $producto['productocodigo'] . "' productoestado='" . $producto['productoestado'] . "' productocategoriaid='" . $producto['productocategoriaid'] . "'  data-toggle='modal' data-target='#modalEditarProducto'><i class='fa fa-pencil-alt'></i></button><button class='btn btn-danger btnEliminarProducto' productoid='" . $producto["productoid"] . "' productoimg='" . $producto["productoimg"] . "'><i class='fa fa-times'></i></button></div>";
                          echo '</td>';
                          echo '</tr>';
                        }*/




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

 <?php include 'template/modales/modalagregarproducto.php' ?>
 <?php include 'template/modales/modaleditarproducto.php' ?>
 <?php include 'template/modales/modalqr.php' ?>

<!-- ./wrapper -->
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
  if ($_GET['mensaje'] == 1) { //insertar
    echo "Toast.fire({
         icon: 'success',

        title: '<div style=margin-top:0.5rem;>Insertado con éxito.</div>'
     });";
  } else if ($_GET['mensaje'] == 2) { //actualizar
    echo "Toast.fire({
         icon: 'success',
        title: '<div style=margin-top:0.5rem;>Actualizado con éxito.</div>'
     });";
  } else if ($_GET['mensaje'] == 3) { //eliminar
    echo "Toast.fire({
         icon: 'success',
        title: '<div style=margin-top:0.5rem;>Eliminado con éxito.</div>'
     });";
  } else if ($_GET['mensaje'] == 4) { //error
    echo " Toast.fire({
        icon: 'error',
        title: '<div style=margin-top:0.5rem;>Error al efectuar la operación.</div>'
      })";
  }
  echo "</script>";

  ?>

<script>
  $(function () {
   
    $('#tabla-productos').DataTable({
       "language": {
            "url": "//cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json"
        },
      "paging": true,
      "lengthChange": false,
      "searching": true,
      "ordering": true,
      "info": true,
      "autoWidth": false,
      "responsive": true

    });
  });
</script>

<script>
    $(".tabla-productos tbody").on("click", "button.btnEditarProducto", function() {

      var productoid = $(this).attr("productoid");
      var img = $(this).attr("imagen");
      var nombre = $(this).attr("productonombre");
      var precio = $(this).attr("productoprecio");
      var estado = $(this).attr("productoestado");
      var productocategoriaid = $(this).attr("productocategoriaid");
      var codigo = $(this).attr("productocodigo");
    

      $("#modalEditarProducto #productoid").val(productoid);
      $("#modalEditarProducto #productonombre").val(nombre);
      $("#modalEditarProducto #productoprecio").val(precio);
      $("#modalEditarProducto #productoestado").val(estado);
      $("#modalEditarProducto #productocategoriaid").val(productocategoriaid);
     
      $("#modalEditarProducto #productocodigo").val(codigo);
      $("#modalEditarProducto #imagenActual").val(img);
      $("#modalEditarProducto .previsualizar").attr("src", img);




    });

   

    $(".tabla-productos tbody").on("click", "button.btnEliminarProducto", function() {

      var productoid = $(this).attr("productoid");

      Swal.fire({
        title: '¿Desea eliminar el producto?',
        text: "No se podrá revertir el cambio",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        cancelButtonText: "Cancelar",
        confirmButtonText: 'Eliminar'
      }).then((result) => {
        if (result.isConfirmed) {
          window.location = "../../business/productoaction.php?eliminar=true&productoid=" + productoid;

        }
      })


    });


    $(".nuevaImagen").change(function() {

      var imagen = this.files[0];

      /*=============================================
        VALIDAMOS EL FORMATO DE LA IMAGEN SEA JPG O PNG
        =============================================*/

      if (imagen["type"] != "image/jpeg" && imagen["type"] != "image/png") {

        $(".nuevaImagen").val("");

       Toast.fire({
          title: "Error al subir la imagen",
          text: "¡La imagen debe estar en formato JPG o PNG!",
          type: "error",
          confirmButtonText: "¡Cerrar!"
        });

      
      } else if (imagen["size"] > 2000000) {

        $(".nuevaImagen").val("");

        Toast.fire({
          title: "Error al subir la imagen",
          text: "¡La imagen no debe pesar más de 2MB!",
          type: "error",
          confirmButtonText: "¡Cerrar!"
        });

        

      } else {

        var datosImagen = new FileReader;
        datosImagen.readAsDataURL(imagen);

        $(datosImagen).on("load", function(event) {

          var rutaImagen = event.target.result;

          $(".previsualizar").attr("src", rutaImagen);

        })

      }
    })
  </script>
</body>
</html>

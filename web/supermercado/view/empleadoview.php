<?php  
 
    include '../business/categoriabusiness.php';

    $categoriaBusiness = new CategoriaBusiness();
    $categorias = $categoriaBusiness->getAllTBCategorias();

    include '../business/tipoempleadobusiness.php';
    $tipoempleadosbusiness = new TipoEmpleadoBusiness();
    $tipos = $tipoempleadosbusiness->obtener();

?>

<?php 
   include 'template/sesion.php';
  ?>


<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Empleados | Dashboard</title>

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
            <h1 class="m-0 text-center">Empleados</h1>
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
                <button class="btn btn-primary" data-toggle="modal" data-target="#modalAgregarEmpleado">

                    Agregar empleado

                  </button>
                  <a href="tipoempleadoview.php" class="btn btn-primary">Tipos de empleado</a>
              </div>
              <!-- /.card-header -->
              <div class="card-body">
              <table id="empleados2" class="tabla-empleados table table-bordered table-hover">
                  <thead>
                  <tr>
                    
                    <th>Cédula</th>
                    <th>Nombre</th>
                    <th>Apellidos</th>
                    <th>Direccion</th>
                    <th>Tipo</th>
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


</div>
<!-- ./wrapper -->

<?php include 'template/modales/modalagregarempleado.php' ?>

<?php include 'template/modales/modaleditarempleado.php' ?>
<?php include 'template/modales/modalqr.php' ?>
<?php include 'template/dependenciasjs.php' ?>

<script src="dist/js/empleados.js"> </script>


<!-- EDITAR -->
<script>
  $(".tabla-empleados tbody").on("click", "button.btnEditarEmpleado", function(){

var empleadoid = $(this).attr("empleadoid");
var empleadocedula = $(this).attr("empleadocedula");
var empleadonombre = $(this).attr("empleadonombre");
var empleadoapellidos = $(this).attr("empleadoapellidos");
var empleadotelefono = $(this).attr("empleadotelefono");
var empleadodireccion = $(this).attr("empleadodireccion");
var empleadofechaingreso = $(this).attr("empleadofechaingreso");
var empleadofechasalida = $(this).attr("empleadofechasalida");
var empleadoestado = $(this).attr("empleadoestado");
var empleadotipoid = $(this).attr("empleadotipoid");

$("#modalEditarEmpleado #empleadoid").val(empleadoid);
$("#modalEditarEmpleado #empleadocedula").val(empleadocedula);
$("#modalEditarEmpleado #empleadonombre").val(empleadonombre);
$("#modalEditarEmpleado #empleadoapellidos").val(empleadoapellidos);
$("#modalEditarEmpleado #empleadotelefono").val(empleadotelefono);
$("#modalEditarEmpleado #empleadodireccion").val(empleadodireccion);
$("#modalEditarEmpleado #empleadofechaingreso").val(empleadofechaingreso);
$("#modalEditarEmpleado #empleadofechasalida").val(empleadofechasalida);
$("#modalEditarEmpleado #empleadoestado").val(empleadoestado);
$("#modalEditarEmpleado #empleadotipoid").val(empleadotipoid);

$.ajax({
  type: "GET",
  url: '../business/usuarioaction.php?metodo=obtenerTipos',
  dataType: 'json',
  success: function(data) {

    $.each(data, function(key, value){ 
      $('#modalEditarEmpleado #tipos').append($('<option>').text(value.tipodescripcion).attr('value', value.tipoid));
    });

  }
});

});



$(".tabla-empleados tbody").on("click", "button.btnEliminarEmpleado", function(){

var empleadoid = $(this).attr("empleadoid");

Swal.fire({
      title: '¿Desea eliminar este empleado?',
      showDenyButton: true,
      showCancelButton: false,
      confirmButtonText: 'Sí',
      denyButtonText: `Cancelar`,
     }).then((result) => {
       if (result.isConfirmed) {
         let requestUrl ="../business/empleadoaction.php?metodo=eliminar&empleadoid="+empleadoid;
        console.log(requestUrl)
        $.ajax({
        url: requestUrl ,
        type: "GET",
        
        
        success: function(dataResult){
          var dataResult = JSON.parse(dataResult);
          if(dataResult.statusCode==200){
                       
                Toast.fire({
                    icon: 'success',
                    title: '<div style=margin-top:0.5rem;>Eliminado con éxito.</div>'
              });                
                
                 $('#empleados2').DataTable().ajax.reload();                                             
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
});
</script>

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

    $('#empleados').DataTable({
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


</script>
</body>
</html>

<?php   
    include '../business/descuentobusiness.php';
    include '../business/membresiabusiness.php';
    $descuentoBusiness = new DescuentoBusiness();
    $descuentos = $descuentoBusiness->getAllTBDescuentos();
    $membresiabusiness = new MembresiaBusiness();
    $membresias = $membresiabusiness->getAllTBMembresias();


?>

<?php 
   include 'template/sesion.php';
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

  <?php include 'template/infosuper.php' ?>
  
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
            <h1 class="m-0 text-center">Descuentos</h1>
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
                <button class="btn btn-primary" data-toggle="modal" data-target="#modalAgregarDescuento">

                    Agregar Descuento

                  </button>
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                 <table id="categorias" class="tabla-categorias table table-bordered table-hover">
                  <thead>
                  <tr>
   
                    <th>Tarifa</th>
                    <th>Tipo membresia</th>
                    <th>Acciones</th>
                
	
                  </tr>
                  </thead>
                  <tbody>
                    <?php 
                    
                        foreach($descuentos as $descuento){
                          echo '<tr>';
                          echo '<td>'.$descuento['descuentotarifa'].'</td>';
                           echo '<td>';
                           echo '<span class="badge badge-light">'.$membresiabusiness->getNombreMembresia($descuento['descuentomembresiaid'])[0]['membresiadescripcion'].'</span>';
                           echo '</td>';                
                          echo '<td>';
                          echo "<div class='btn-group'><button class='btn btn-warning btnEditarCategoria' descuentoid='" . $descuento["descuentoid"] . "' descuentotarifa='" . $descuento['descuentotarifa'] . "' descuentomembresiaid='" . $descuento['descuentomembresiaid'] . "' data-toggle='modal' data-target='#modalEditarDescuento'><i class='fa fa-pencil-alt'></i></button><button class='btn btn-danger btnEliminarCategoria'descuentoid='".$descuento["descuentoid"]. "' ><i class='fa fa-times'></i></button></div>";  
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
<?php include 'template/modales/modalagregardescuento.php' ?>
<?php include 'template/modales/modaleditardescuento.php' ?>
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

  
$(document).on('click','#insertar',function(e) {

var descripcion = $("#formulario-insertar div").children().get(2);

if(descripcion.value  == ""){
 $("#formulario-insertar div").children().get(2).focus()
   Toast.fire({
               icon: 'warning',
               title: '<div style=margin-top:0.5rem;>Ingresar descripción.</div>'
         })
   
}else{
   var data = $("#formulario-insertar").serialize();

   console.log(data)
   $.ajax({
     data: data,
     type: "POST",
     url: "../business/descuentoaction.php",
     success: function(dataResult){
         var dataResult = JSON.parse(dataResult);
         if(dataResult.statusCode==200){

                Toast.fire({
                   icon: 'success',
                   title: '<div style=margin-top:0.5rem;>Insertado con éxito.</div>'
             });

                $('#modalAgregarDescuento #descuentotarifa').val("");
                $('#modalAgregarDescuento #descuentomembresiaid').val("");            
                $('#modalAgregarDescuento').modal('hide');
                $('#tabla-categorias').DataTable().ajax.reload();
               
                                            
         }else{
               Toast.fire({
                   icon: 'error',
                   title: '<div style=margin-top:0.5rem;>Error al efectuar la operación.</div>'
             })
         }
                   
     }
   });
 }
});
$(document).on('click','#actualizar',function(e) {

var descripcion = $("#formulario-editar div").children().get(3);

if(descripcion.value  == ""){
     $("#formulario-editar div").children().get(3).focus()
    Toast.fire({
                icon: 'warning',
                title: '<div style=margin-top:0.5rem;>Ingresar descripción.</div>'
          })
}else{
    var data = $("#formulario-editar").serialize();
    console.log(data)
    $.ajax({
      data: data,
      type: "POST",
      url: "../business/clienteaction.php",
      success: function(dataResult){
            var dataResult = JSON.parse(dataResult);
          if(dataResult.statusCode==200){
                       
                Toast.fire({
                    icon: 'success',
                    title: '<div style=margin-top:0.5rem;>Actualizado con éxito.</div>'
              });
                 
                 $('#modalEditarDescuento').modal('hide');
                 $('#tabla-clientes').DataTable().ajax.reload();
                                             
          }else{
                 Toast.fire({
                    icon: 'error',
                    title: '<div style=margin-top:0.5rem;>Error al efectuar la operación.</div>'
              })
      
          }
                    
      }
    });
}


});




  $(".tabla-categorias tbody").on("click", "button.btnEditarCategoria", function(){

    var descuentoid = $(this).attr("descuentoid");
    var descuentotarifa = $(this).attr("descuentotarifa");
    var descuentomembresiaid = $(this).attr("descuentomembresiaid");
   
    $("#modalEditarDescuento #descuentoid").val(descuentoid);
    $("#modalEditarDescuento #descuentotarifa").val(descuentotarifa);
    $("#modalEditarDescuento #descuentomembresiaid").val(descuentomembresiaid);
 
});

$(".tabla-categorias tbody").on("click", "button.btnEliminarCategoria", function(){
  var descuentoid = $(this).attr("descuentoid");
Swal.fire({
      title: '¿Desea eliminar este descuento?',
      showDenyButton: true,
      showCancelButton: false,
      confirmButtonText: 'Sí',
      denyButtonText: `Cancelar`,
    }).then((result) => {
     
      if (result.isConfirmed) {
        let requestUrl ="../business/descuentoaction.php?metodo=eliminar&descuentoid="+descuentoid;
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
                 
                
                 $('#categorias').DataTable().ajax.reload();
                                             
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


$(document).on('click','#insertar',function(e) {


var descripcion = $("#formulario-insertar div").children().get(2);

if(descripcion.value  == ""){
 $("#formulario-insertar div").children().get(2).focus()
   Toast.fire({
               icon: 'warning',
               title: '<div style=margin-top:0.5rem;>Ingresar descripción.</div>'
         })
   
}else{
   var data = $("#formulario-insertar").serialize();

   console.log(data)
   $.ajax({
     data: data,
     type: "POST",
     url: "../business/descuentoaction.php",
     success: function(dataResult){
         var dataResult = JSON.parse(dataResult);
         if(dataResult.statusCode==200){

                Toast.fire({
                   icon: 'success',
                   title: '<div style=margin-top:0.5rem;>Insertado con éxito.</div>'
             });

                $('#modalAgregarDescuento #descuentotarifa').val("");
                $('#modalAgregarDescuento #descuentomembresiaid').val("");
             
                $('#modalAgregarDescuento').modal('hide');
                $('#tabla-tipos').DataTable().ajax.reload();
               
                                            
         }else{
               Toast.fire({
                   icon: 'error',
                   title: '<div style=margin-top:0.5rem;>Error al efectuar la operación.</div>'
             })
         }
                   
     }
   });
 }
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

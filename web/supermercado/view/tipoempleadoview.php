<?php 

  include '../business/tipoempleadobusiness.php';
 // $tipoUsuarioBusiness = new TipoUsuarioBusiness();
  //$tipos = $tipoUsuarioBusiness->getAllTBTipoUsuarios();

  //print_r($tipos)


 ?>
<?php 
   //include 'template/sesion.php';
  ?>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Tipos de empleados | Dashboard</title>

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

  <!-- Theme style -->
  <link rel="stylesheet" href="dist/css/adminlte.min.css">
  <!-- overlayScrollbars -->
  <link rel="stylesheet" href="plugins/overlayScrollbars/css/OverlayScrollbars.min.css">
  <!-- Daterange picker -->
  <link rel="stylesheet" href="plugins/daterangepicker/daterangepicker.css">


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
            <a href="categoriaview.php" class="nav-link ">
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
            <a href="" class="nav-link">
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
            <a href="empleadoview.php" class="nav-link active">
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
                <a href="membresiaview.php" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                   <i class="nav-icon fas fa-list"></i>
                  <p>Tipos de membresia</p>
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
            <h1 class="m-0">Tipo de empleados</h1>
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

  <div id="modalAgregarTipo" class="modal fade" role="dialog">
  
  <div class="modal-dialog">

    <div class="modal-content">

        <div class="modal-header">
          <h4 class="modal-title">Agregar tipo</h4>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>

        <div class="modal-body">

          <div class="box-body">

            <form id="formulario-insertar"  >
           

              <div class="form-group">
                <input type="hidden" name="metodo" value="agregar">
                <label >Descripción:</label>
                <input type="text" class="form-control" name="tipodescripcion" id="tipodescripcion" placeholder="Ingrese tipo">
               
              </div>
                         
             <center><button type="submit" name="insertar" id="insertar" class="btn btn-primary">Insertar</button></center> 
            </form>

    </div>

  </div>

</div>
  </div>

</div>

<div id="modalEditarTipo" class="modal fade" role="dialog">
  
  <div class="modal-dialog">

    <div class="modal-content">

        <div class="modal-header">
          <h4 class="modal-title">Editar tipo</h4>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>

        <div class="modal-body">

          <div class="box-body">

            <form id="formulario-editar">
              
              <div class="form-group">
                <input type="hidden" name="tipoid" id="tipoid">
                 <input type="hidden" name="metodo" value="actualizar">
              
              </div>

              <div class="form-group">
                <label >Descripción:</label>
                <input type="text" class="form-control" name="tipodescripcion" id="tipodescripcion" placeholder="Ingrese tipo">
               
              </div>
              

             <center><button type="submit" name="actualizar" id="actualizar" class="btn btn-primary">Actualizar</button></center> 
            </form>

    </div>

  </div>

</div>
  </div>

</div>




</div>
<!-- ./wrapper -->

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

<!-- jQuery Knob Chart -->
<script src="plugins/jquery-knob/jquery.knob.min.js"></script>
<!-- daterangepicker -->
<script src="plugins/moment/moment.min.js"></script>
<script src="plugins/daterangepicker/daterangepicker.js"></script>
<!-- Tempusdominus Bootstrap 4 -->
<script src="plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"></script>
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




<script>

  var Toast = Swal.mixin({
       toast: true,
       position: 'top-right',
       showConfirmButton: false,
       timer: 3000,
       timerProgressBar: true
     });



  $('#tabla-tipos').dataTable( {
          'processing': true,
            'serverSide': true,
            'serverMethod': 'get',
            'ajax': {
                'url':'../business/tipoempleadoaction.php?metodo=obtener'
            },          
          "columns": [
             
              { "data": "tipodescripcion" },
              { "data": null,
              render: function ( data, type, row, meta) {
                return "<div class='btn-group'><button class='btn btn-warning btnEditarTipo' tipoid="+data.tipoid+" descripcion="+data.tipodescripcion+" data-toggle='modal' data-target='#modalEditarTipo'><i class='fa fa-pencil-alt'></i></button><button class='btn btn-danger btnEliminarTipo' tipoid="+data.tipoid+" ><i class='fa fa-times'></i></button></div>";
                }
              }
              
          ]
      
        })


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
          url: "../business/tipoempleadoaction.php",
          success: function(dataResult){
              var dataResult = JSON.parse(dataResult);
              if(dataResult.statusCode==200){

                     Toast.fire({
                        icon: 'success',
                        title: '<div style=margin-top:0.5rem;>Insertado con éxito.</div>'
                  });
                     $('#modalAgregarTipo #tipodescripcion').val("");
                     $('#modalAgregarTipo').modal('hide');
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
          url: "../business/tipoempleadoaction.php",
          success: function(dataResult){
                var dataResult = JSON.parse(dataResult);
              if(dataResult.statusCode==200){
                           
                    Toast.fire({
                        icon: 'success',
                        title: '<div style=margin-top:0.5rem;>Actualizado con éxito.</div>'
                  });
                     
                     $('#modalEditarTipo').modal('hide');
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



  $(".tabla-tipos tbody").on("click", "button.btnEditarTipo", function(){

    var id = $(this).attr("tipoid");
    var descripcion = $(this).attr("descripcion");
   
    $("#modalEditarTipo #tipoid").val( id );
    $("#modalEditarTipo #tipodescripcion").val(descripcion);

  

});

  
$(".tabla-tipos tbody").on("click", "button.btnEliminarTipo", function(){

  var id = $(this).attr("tipoid");

  Swal.fire({
        title: '¿Desea eliminar este tipo de empleado?',
        showDenyButton: true,
        showCancelButton: false,
        confirmButtonText: 'Sí',
        denyButtonText: `Cancelar`,
      }).then((result) => {
       
        if (result.isConfirmed) {
          let requestUrl ="../business/tipoempleadoaction.php?metodo=eliminar&id="+id;
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
      })
});


</script>


</body>
</html>
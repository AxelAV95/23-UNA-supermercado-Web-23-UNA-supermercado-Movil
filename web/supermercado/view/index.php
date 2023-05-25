
 <?php 


   include 'template/sesion.php';
    include '../business/productobusiness.php';
     include '../business/empleadobusiness.php';
     include '../business/proveedorbusiness.php';

     
  
     $productoBusiness = new ProductoBusiness();
     $empleadoBusiness = new EmpleadoBusiness();
     $proveedorBusiness = new ProveedorBusiness();
  $productos = $productoBusiness->obtenerProductosRecientes();
  $totalEmpleados = $empleadoBusiness->obtenerTotalEmpleados();
  $totalProductos = $productoBusiness->obtenerTotalProductos();
  $totalProveedores = $proveedorBusiness->obtenerTotalProveedores();
  $totalProductoCategoria = $productoBusiness->obtenerTotalProductosCategoria();
  $totalProductoProveedor = $proveedorBusiness->obtenerTotalProductosProveedorChart();

  //print_r($productos);
  ?>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title><?php echo $_SESSION['nombresuper'] ?></title>

  <?php include 'template/dependenciascss.php' ?>
  <link rel="stylesheet" type="text/css" href="plugins/chart.js/Chart.min.css">
 
</head>
<body class="hold-transition sidebar-mini layout-fixed">
<div class="wrapper">

  <!-- Navbar -->
  <?php include 'template/header.php' ?>
  <!-- /.navbar -->

  <!-- Main Sidebar Container -->
<aside class="main-sidebar sidebar-dark-primary elevation-4">
    <!-- Brand Logo -->
    <a href="index.php" class="brand-link d-flex justify-content-center ">
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
          <div class="col-sm-6">
            <h1 class="m-0">Dashboard</h1>
          </div><!-- /.col -->
          
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <section class="content">

      <div class="container-fluid">
        <!-- Small boxes (Stat box) -->
        <div class="row d-flex justify-content-center">
          <div class="col-lg-3 col-6">
            <!-- small box -->
            <div class="small-box bg-info">
              <div class="inner">
                <p>Total de empleados</p>
                <?php echo $totalEmpleados ?>
              </div>
              <div class="icon">
               <i class="nav-icon fas fa-user-edit"></i>
              </div>
              <a href="empleadoview.php" class="small-box-footer">Más información<i class="fas fa-arrow-circle-right"></i></a>
            </div>
          </div>
          <div class="col-lg-3 col-6">
            <!-- small box -->
            <div class="small-box bg-secondary">
              <div class="inner">
                 <p>Total de productos</p>
                 <?php echo  $totalProductos ?>
              </div>
              <div class="icon">
                <i class="nav-icon fab fa-product-hunt"></i>
              </div>
              <a href="productoview.php" class="small-box-footer">Más información<i class="fas fa-arrow-circle-right"></i></a>
            </div>
          </div>
          <!-- ./col -->
          <div class="col-lg-3 col-6">
            <!-- small box -->
            <div class="small-box bg-success">
              <div class="inner">
                  
               
            

                <p>Total de proveedores</p>
                <?php echo   $totalProveedores ?>
              </div>
              <div class="icon">
                <i class="nav-icon fas fa-people-carry"></i>
              </div>
              <a href="proveedorview.php" class="small-box-footer">Más información<i class="fas fa-arrow-circle-right"></i></a>
             <!--  <a href="#" class="small-box-footer"> <i class="fas fa-arrow-circle-right"></i></a> -->
            </div>
          </div>

          
         
        

         
         
         
        </div>

        <div class="row">
          <section class="col-lg-6 connectedSortable ui-sortable">
            <div class="card">
            <div class="card-header">
              <h3 class="card-title">Productos agregados recientemente</h3>
              <div class="card-tools">
                <button type="button" class="btn btn-tool" data-card-widget="collapse">
                <i class="fas fa-minus"></i>
                </button>
                <button type="button" class="btn btn-tool" data-card-widget="remove">
                <i class="fas fa-times"></i>
                </button>
              </div>
            </div>

            <div class="card-body p-0">
              <ul class="products-list product-list-in-card pr-2">
                <?php foreach ($productos as $producto): ?>
                  <li class="item">
                      <div class="product-img ml-3">
                        <i class="nav-icon fab fa-product-hunt"></i>
                       </div>
                    <div class="product-info">
                      <a href="javascript:void(0)" class="product-title"><?php echo $producto['productonombre']; ?>
                        <span class="badge badge-warning float-right">₡<?php echo $producto['productoprecio']; ?></span>
                      </a>
                      <span class="product-description">
                          Stock: <?php echo $producto['productostock']; ?>
                       </span>
                     
                    </div>
                  </li>
                <?php endforeach; ?>
              </ul>

          </div>

          <div class="card-footer text-center">
          <a href="productoview.php" class="uppercase">Ver todos los productos</a>
          </div>

        </div>
          </section>
          <section class="col-lg-6 connectedSortable ui-sortable">
            <canvas id="chart-productos-categorias"></canvas>
          </section>
        </div>

        <div class="row">
          <section class="col-lg-6 connectedSortable ui-sortable">
            <canvas id="chart-productos-proveedor"></canvas>
            </section>
        </div>
       
        <!-- Main row -->
       
        <!-- /.row (main row) -->
      </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  
 

</div>
  <?php include 'template/modales/modalqr.php'; ?>
  <?php include 'template/footer.php'; ?>
  <?php include 'template/dependenciasjs.php' ?>

  <script type="text/javascript" src="plugins/chart.js/Chart.min.js"></script>

<script>
var ctxpc = document.getElementById("chart-productos-categorias").getContext('2d');
var ctxpp = document.getElementById("chart-productos-proveedor").getContext('2d');
var dataProductoCategoria = <?php echo $totalProductoCategoria; ?>;
var dataProductoProveedor = <?php echo $totalProductoProveedor; ?>;

var chartProductoCategoria = new Chart(ctxpc, {
      type: 'bar',
    data: dataProductoCategoria ,
    options: {
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero:true
                }
            }]
        }
    }
});

var chartProductoProveedor = new Chart(ctxpp, {
     type: 'doughnut',
    data: dataProductoProveedor ,
    options: {
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero:true
                }
            }]
        }
    }
});
</script>


</body>
</html>

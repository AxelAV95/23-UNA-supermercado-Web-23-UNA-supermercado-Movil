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
          <a href="#" class="d-block">Usuario</a>
        </div>
      </div>

      <!-- Sidebar Menu -->
      <nav class="mt-2">
        <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
          <!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->
          <li class="nav-item ">
            <a href="index.php" class="nav-link active">
              <i class="nav-icon fas fa-tachometer-alt"></i>
              <p>
                Dashboard
              </p>
            </a>
          </li>

          <li class="nav-item ">
            <a href="template/productosview.php" class="nav-link">
              <i class="nav-icon fas fa-hamburger"></i>
              <p>
                Productos
              </p>
            </a>
          </li>

          <li class="nav-item ">
            <a href="./index.html" class="nav-link">
              <i class="nav-icon fas fa-file-invoice"></i>
              <p>
                Órdenes
              </p>
            </a>
          </li>

           <li class="nav-item ">
            <a href="./index.html" class="nav-link">
              <i class="nav-icon fas fa-list"></i>
              <p>
                Categorías
              </p>
            </a>
          </li>
         
          <li class="nav-item">
            <a href="./index.html" class="nav-link">
               <i class="nav-icon fas fa-users"></i>
              <p>
                Usuarios
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="./index.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <i class="fas fa-users-cog nav-icon"></i>
                  <p>Tipos</p>
                </a>
              </li>
            </ul>
          </li>
         
         <li class="nav-item ">
            <a href="./index.html" class="nav-link">
               <i class="nav-icon fas fa-history"></i>
              <p>
                Historial
              </p>
            </a>
          </li>

          

         <li class="nav-item ">
            <a href="./index.html" class="nav-link">
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
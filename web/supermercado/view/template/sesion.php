 <?php 

 	 session_start();
    if(!isset($_SESSION['sesionActiva'])){
      header("location: loginview.php");
    }else{
     
      $empleadoid = $_SESSION["empleadoid"];
      $cedula = $_SESSION["empleadocedula"];
      $nombre = $_SESSION["empleadonombre"];
      $apellidos = $_SESSION["empleadoapellidos"];
      $descripcion = $_SESSION["tipodescripcion"];
      $estadoSesion =$_SESSION["sesionActiva"];
      $logo = $_SESSION["logo"];;
    }
  ?>
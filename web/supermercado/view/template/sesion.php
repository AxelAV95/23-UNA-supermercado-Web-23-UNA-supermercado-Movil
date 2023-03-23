 <?php 

 	 session_start();
    if(!isset($_SESSION['usuario'])){
      header("location: loginview.php");
    }else{
      $usuario = $_SESSION['usuario'];
    }
  ?>
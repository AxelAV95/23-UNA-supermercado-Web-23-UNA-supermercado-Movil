<?php
include 'empleadobusiness.php';

if(isset($_POST['metodo']) && $_POST['metodo'] == "agregar"){
    $empleadocedula = $_POST['empleadocedula'];
    $empleadonombre = $_POST['empleadonombre'];
    $empleadoapellidos = $_POST['empleadoapellidos'];
    $empleadotelefono = $_POST['empleadotelefono'];
    $empleadodireccion = $_POST['empleadodireccion'];
    $empleadofechaingreso = $_POST['empleadofechaingreso'];
    $empleadofechasalida = $_POST['empleadofechasalida'];
    $empleadoestado = $_POST['empleadoestado'];
    $empleadotipoid = $_POST['empleadotipoid'];

   // echo "$empleadocedula";

    $empleado = new Empleado(0,$empleadocedula,$empleadonombre,$empleadoapellidos,
$empleadotelefono,$empleadodireccion,$empleadofechaingreso,$empleadofechasalida,
$empleadoestado,$empleadotipoid);
    
    $empleadoBusiness = new EmpleadoBusiness();
    $resultado = $empleadoBusiness->insertarempleado($empleado);
    

    if($resultado == 1){
            echo json_encode(array("statusCode"=>200));	
            Header("Location: ../view/empleadoview.php");
    }else{
            echo json_encode(array("statusCode"=>400));
            Header("Location: ../view/empleadoview.php");
    }

 //OBTENER LOS EMPLEADOS 
}else if(isset($_GET['metodo']) && $_GET['metodo'] == "obtener" ){
    $empleadoBusiness = new EmpleadoBusiness();
    $empleados = $empleadoBusiness->obtenerempleados();

    $data = array();
      foreach ($empleados  as $row) {
      $data[] = array(
         "empleadoid"=>$row['empleadoid'],
         "empleadocedula"=>$row['empleadocedula'],
         "empleadonombre"=>$row['empleadonombre'],
         "empleadoapellidos"=>$row['empleadoapellidos'],
         "empleadotelefono"=>$row['empleadotelefono'],
         "empleadodireccion"=>$row['empleadodireccion'],
         "empleadofechaingreso"=>$row['empleadofechaingreso'],
         "empleadofechasalida"=>$row['empleadofechasalida'],
         "empleadoestado"=>$row['empleadoestado'],
         "empleadotipoid"=>$row['empleadotipoid']
      );
       }

       $response = array(
           "iTotalRecords" => count($data),
      "aaData" => $data
       );

      echo json_encode($response);


      //ELIMINAR EMPLEADO
    }else if(isset($_GET['metodo']) && $_GET['metodo'] == "eliminar"){
		$id = $_GET['empleadoid'];

		$empleadoBusiness = new EmpleadoBusiness();
		$resultado = $empleadoBusiness->eliminarempleado($id);

		if($resultado == 1){
	    		echo json_encode(array("statusCode"=>200));	
	    }else{
	    		echo json_encode(array("statusCode"=>400));	
	    }

        //ACTUALIZAR EMPLEADO
	}else if(isset($_POST['metodo']) && $_POST['metodo'] == "editar"){
		$empleadoid  = $_POST['empleadoid'];
		$empleadocedula = $_POST['empleadocedula'];
        $empleadonombre = $_POST['empleadonombre'];
        $empleadoapellidos = $_POST['empleadoapellidos'];
        $empleadotelefono = $_POST['empleadotelefono'];
        $empleadodireccion = $_POST['empleadodireccion'];
        $empleadofechaingreso = $_POST['empleadofechaingreso'];
        $empleadofechasalida = $_POST['empleadofechasalida'];
        $empleadoestado = $_POST['empleadoestado'];
        $empleadotipoid = $_POST['empleadotipoid'];

		$empleado = new Empleado($empleadoid, $empleadocedula, $empleadonombre, 
        $empleadoapellidos, $empleadotelefono, $empleadodireccion, 
        $empleadofechaingreso, $empleadofechasalida, $empleadoestado,
        $empleadotipoid);

          //  echo "$empleadoid,$empleadocedula, $empleadonombre";
	
		$empleadoBusiness = new EmpleadoBusiness();
		$resultado = $empleadoBusiness->modificarempleado($empleado);

		if($resultado == 1){
	    		echo json_encode(array("statusCode"=>200));	
                Header("Location: ../view/empleadoview.php");
	    }else{
	    		echo json_encode(array("statusCode"=>400));	
                Header("Location: ../view/empleadoview.php");
	    }
	}






?>
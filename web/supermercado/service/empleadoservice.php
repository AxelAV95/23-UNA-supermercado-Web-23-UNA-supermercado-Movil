<?php 

	include '../business/empleadobusiness.php';

	if($_SERVER['REQUEST_METHOD'] == "GET"){
		$empleadoBusiness = new EmpleadoBusiness();
        $empleados = $empleadoBusiness->obtenerempleados();
  		echo json_encode($empleados);
	}

?>
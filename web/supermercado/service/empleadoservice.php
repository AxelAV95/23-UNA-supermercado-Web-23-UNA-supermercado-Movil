<?php 

	include '../business/empleadobusiness.php';

	//OBTENER EMPLEADOS
	if($_SERVER['REQUEST_METHOD'] == "GET"){
		$empleadoBusiness = new EmpleadoBusiness();
        $empleados = $empleadoBusiness->obtenerempleados();
  		echo json_encode($empleados);
	
		//INSERTAR EMPLEADOS
	}else if($_SERVER['REQUEST_METHOD'] == "POST"){
		//se utiliza en PHP para obtener el contenido de la solicitud HTTP entrante
		$json = file_get_contents('php://input');
		$data = json_decode($json);

		if($data->metodo == "insertar"){
			/*$empleado = new Empleado();
			$empleado->setEmpleadoid($data->id);
			$empleado->setEmpleadocedula($data->cedula);
			$empleado->setEmpleadonombre($data->nombre);
			$empleado->setEmpleadoapellidos($data->apellidos);
			$empleado->setEmpleadotelefono($data->telefono);
            $empleado->setEmpleadodireccion($data->direccion);
			$empleado->setEmpleadofechaingreso($data->fechaIngreso);
			$empleado->setEmpleadofechasalida($data->fechaSalida);
			$empleado->setEmpleadoestado($data->estado);
			$empleado->setEmpleadotipoid($data->tipoEmpleadoId);	*/		

			$empleadoBusiness = new EmpleadoBusiness();
			$resultado = $empleadoBusiness->insertarempleado(new Empleado($data->id,$data->cedula,$data->nombre,$data->apellidos,$data->telefono,$data->direccion,$data->fechaIngreso,$data->fechaSalida,$data->estado,$data->tipoEmpleadoId));

			if($resultado == 1){
		    		echo json_encode(array("statusCode"=>200));	
		    }else{
		    		echo json_encode(array("statusCode"=>400));	
		    }
		}

		//ACTUALIZAR EMPLEADO
	}else if($_SERVER['REQUEST_METHOD'] == "PUT"){
		$json = file_get_contents('php://input');
		$data = json_decode($json);

		$empleadoBusiness = new EmpleadoBusiness();
		$resultado = $empleadoBusiness->modificarempleado(new Empleado($data->id,$data->cedula,$data->nombre,$data->apellidos,$data->telefono,$data->direccion,$data->fechaIngreso,$data->fechaSalida,$data->estado,$data->tipoEmpleadoId));

		if($resultado == 1){
	    		echo json_encode(array("statusCode"=>200));	
	    }else{
	    		echo json_encode(array("statusCode"=>400));	
	    }
	
	//ELIMINAR UN EMPLEADO
	}else if($_SERVER['REQUEST_METHOD'] === "DELETE"){
		
		$id = $_GET['id'];

		$empleadoBusiness = new EmpleadoBusiness();
		
		$resultado = $empleadoBusiness->eliminarempleado($id);

		if($resultado == 1){
	    		echo json_encode(array("statusCode"=>200));	
	    }else{
	    		echo json_encode(array("statusCode"=>400));	
	    }
	}

?>
<?php 
	
	include '../business/tipoempleadobusiness.php';

	if($_SERVER['REQUEST_METHOD'] == "GET"){
		$tipoEmpleadoBusiness = new TipoEmpleadoBusiness();
  		$tipos = $tipoEmpleadoBusiness->obtener();
  		echo json_encode($tipos);
	

	}else if($_SERVER['REQUEST_METHOD'] == "POST"){
		//se utiliza en PHP para obtener el contenido de la solicitud HTTP entrante
		$json = file_get_contents('php://input');
		$data = json_decode($json);

		if($data->metodo == "insertar"){
		//	$tipo = new TipoEmpleado();
		//	$tipo->setTipoEmpleadoDescripcion($data->descripcion);

			$tipoBusiness = new TipoEmpleadoBusiness();

			$resultado = $tipoBusiness->insertarTipo(new TipoEmpleado(0,$data->descripcion));

			if($resultado == 1){
		    		echo json_encode(array("statusCode"=>200));	
		    }else{
		    		echo json_encode(array("statusCode"=>400));	
		    }
		}

		
	}else if($_SERVER['REQUEST_METHOD'] == "PUT"){
		$json = file_get_contents('php://input');
		$data = json_decode($json);

		//$tipo = new TipoEmpleado();
		//$tipo->setId($data->id);
		//$tipo->setDescripcion($data->descripcion);
		$tipoBusiness = new TipoEmpleadoBusiness();

		$resultado = $tipoBusiness->actualizar(new TipoEmpleado($data->tipoid,$data->tipodescripcion));

		if($resultado == 1){
	    		echo json_encode(array("statusCode"=>200));	
	    }else{
	    		echo json_encode(array("statusCode"=>400));	
	    }


	}else if($_SERVER['REQUEST_METHOD'] === "DELETE"){
		
		$id = $_GET['id'];

		$tipoBusiness = new TipoEmpleadoBusiness();
		
		$resultado = $tipoBusiness->eliminarTipo($id);

		if($resultado == 1){
	    		echo json_encode(array("statusCode"=>200));	
	    }else{
	    		echo json_encode(array("statusCode"=>400));	
	    }
	}


 ?>
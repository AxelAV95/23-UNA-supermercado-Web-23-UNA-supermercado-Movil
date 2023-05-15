<?php 
	
	include '../business/descuentobusiness.php';

	if($_SERVER['REQUEST_METHOD'] == "GET"){
		$descuentoBusiness = new DescuentoBusiness();
  		$descuentos = $descuentoBusiness->obtener();
  		echo json_encode($descuentos);
	

	}else if($_SERVER['REQUEST_METHOD'] == "POST"){
		//se utiliza en PHP para obtener el contenido de la solicitud HTTP entrante
		$json = file_get_contents('php://input');
		$data = json_decode($json);

		if($data->metodo == "insertar"){
		//	$descuento = new Descuento();
		//	$descuento->setTarifa($data->tarifa);
			$descuento->setMembresiaId($data->membresiaid);

			$descuentoBusiness = new DescuentoBusiness();

			$resultado = $descuentoBusiness->insertarDescuento(new Descuento(0,$data->tarifa,$data->membresiaid));

			if($resultado == 1){
		    		echo json_encode(array("statusCode"=>200));	
		    }else{
		    		echo json_encode(array("statusCode"=>400));	
		    }
		}

		
	}else if($_SERVER['REQUEST_METHOD'] == "PUT"){
		$json = file_get_contents('php://input');
		$data = json_decode($json);

		//$descuento = new Descuento();
		//$descuento->setId($data->id);
		//$descuento->setTarifa($data->tarifa);
		$descuentoBusiness = new DescuentoBusiness();

		$resultado = $descuentoBusiness->actualizar(new Descuento($data->descuentoid,$data->descuentotarifa,$data->descuentomembresiaid));

		if($resultado == 1){
	    		echo json_encode(array("statusCode"=>200));	
	    }else{
	    		echo json_encode(array("statusCode"=>400));	
	    }


	}else if($_SERVER['REQUEST_METHOD'] === "DELETE"){
		
		$id = $_GET['id'];

		$descuentoBusiness = new DescuentoBusiness();
		
		$resultado = $descuentoBusiness->eliminarDescuento($id);

		if($resultado == 1){
	    		echo json_encode(array("statusCode"=>200));	
	    }else{
	    		echo json_encode(array("statusCode"=>400));	
	    }
	}


 ?>
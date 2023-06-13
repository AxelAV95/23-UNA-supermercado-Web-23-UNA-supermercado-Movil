<?php 
	
	include '../business/descuentobusiness.php';

	if($_SERVER['REQUEST_METHOD'] == "GET"){
		$descuentoBusiness = new DescuentoBusiness();
  		$descuentos = $descuentoBusiness->getAllTBDescuentos();
  		echo json_encode($descuentos);
	

	}else if($_SERVER['REQUEST_METHOD'] == "POST"){
		//se utiliza en PHP para obtener el contenido de la solicitud HTTP entrante
		$json = file_get_contents('php://input');
		$data = json_decode($json);

		if($data->metodo == "insertar"){
			$descuento = new Descuento();
			$descuento->setDescuentotarifa($data->tarifa);
			$descuento->setDescuentomembresiaid($data->membresiaid);
			
			

			$descuentobusiness = new DescuentoBusiness();

			$resultado = $descuentobusiness->insertarDescuento($descuento);

			if($resultado == 1){
		    		echo json_encode(array("statusCode"=>200));	
		    }else{
		    		echo json_encode(array("statusCode"=>400));	
		    }
		}

		
	}else if($_SERVER['REQUEST_METHOD'] == "PUT"){
		$json = file_get_contents('php://input');
		$data = json_decode($json);
		
		$descuento = new Descuento();
		$descuento ->setDescuentoid($data->id);
		$descuento ->setDescuentotarifa($data->tarifa);
		
		
		$descuentoBusiness = new DescuentoBusiness();
		$resultado = $descuentoBusiness->modificarDescuento1($descuento);

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
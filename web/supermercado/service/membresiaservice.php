<?php 

	include '../business/membresiabusiness.php';

	if($_SERVER['REQUEST_METHOD'] == "GET"){

		$membresiaBusiness = new MembresiaBusiness();
		$membresias = $membresiaBusiness->getAllTBMembresias();
  		echo json_encode($membresias);
		


	}else if($_SERVER['REQUEST_METHOD'] == "POST"){
		
			$json = file_get_contents('php://input');
			$data = json_decode($json);
	

			$membresiaBusiness = new MembresiaBusiness();
			if($data->metodo == "insertar"){
			
				$membresia = new Membresia();
				$membresia->setMembresiadescripcion($data->descripcion);
				$resultado = $membresiaBusiness->insertarMembresia($membresia);
	
				if($resultado == 1){
						echo json_encode(array("statusCode"=>200));	
				}else{
						echo json_encode(array("statusCode"=>400));	
				}
			}




	}else if($_SERVER['REQUEST_METHOD'] == "PUT"){
		$json = file_get_contents('php://input');
		$data = json_decode($json);

		$membresiaBusiness = new MembresiaBusiness();
		$membresia = new Membresia();
		$membresia->setMembresiaid($data->id);
		$membresia->setMembresiadescripcion($data->descripcion);
		$resultado = $membresiaBusiness->modificarMembresia($membresia);


	    if($resultado == 1){
	    	echo json_encode(array("statusCode"=>200));	
	    }else{
	    	echo json_encode(array("statusCode"=>400));	
	    }





		
	}else if($_SERVER['REQUEST_METHOD'] === "DELETE"){
		$id = $_GET['id'];
		$membresiaBusiness = new MembresiaBusiness();
		$resultado = $membresiaBusiness->eliminarMembresia($id);

		if($resultado == 1){
	    		echo json_encode(array("statusCode"=>200));	
	    }else{
	    		echo json_encode(array("statusCode"=>400));	
	    }
	}




 ?>
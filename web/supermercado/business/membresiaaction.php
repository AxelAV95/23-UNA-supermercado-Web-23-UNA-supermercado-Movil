<?php 


	include 'membresiabusiness.php';
	

	if(isset($_POST['metodo']) && $_POST['metodo'] == "agregar" && isset($_POST['membresiadescripcion'])){
		$descripcion = $_POST['membresiadescripcion'];
		$membresia = new membresia( );
		$membresia->setMembresiadescripcion($descripcion);
		
		$membresiaBusiness = new membresiaBusiness();
		

		$resultado = $tipoBusiness->insertarMembresia($membresia);

		if($resultado == 1){
	    		echo json_encode(array("statusCode"=>200));	
	    }else{
	    		echo json_encode(array("statusCode"=>400));	
	    }

	}else if(isset($_POST['metodo']) && $_POST['metodo'] == "actualizar" && isset($_POST['membresiaid']) && isset($_POST['membresiadescripcion']) ){
		$id = $_POST['membresiaid'];
		$descripcion = $_POST['membresiadescripcion'];

		$membresia = new Membresia();
		$membresia->setMembresiaid($id);
		$membresia->setMembresiadescripcion($descripcion);
		$membresiaBusiness = new MembresiaBusiness();

		$resultado = $membresiaBusiness->modificarMembresia($membresia);

		if($resultado == 1){
	    		echo json_encode(array("statusCode"=>200));	
	    }else{
	    		echo json_encode(array("statusCode"=>400));	
	    }
	}else if(isset($_GET['metodo']) && $_GET['metodo'] == "eliminar"){
		$id = $_GET['membresiaid'];

		$membresiaBusiness = new MembresiaBusiness();
		

		$resultado = $membresiaBusiness->eliminarMembresia($id);

		if($resultado == 1){
	    		echo json_encode(array("statusCode"=>200));	
	    }else{
	    		echo json_encode(array("statusCode"=>400));	
	    }

	}else if(isset($_GET['metodo']) && $_GET['metodo'] == "obtener" ){
		$membresiaBusiness = new MembresiaBusiness();
  		$membresias = $membresiaBusiness->getAllTBMembresias();

  		$data = array();
  		foreach ($membresias as $row) {
	      $data[] = array(
	         "membresiaid"=>$row['membresiaid'],
	         "membresiadescripcion"=>$row['membresiadescripcion']
	      );
   		}

   		$response = array(
   			"iTotalRecords" => count($data),
	      "aaData" => $data
   		);

  		echo json_encode($response);
  	}

?>
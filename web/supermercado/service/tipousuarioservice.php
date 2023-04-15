<?php 
	
	include '../business/tipousuariobusiness.php';

	if($_SERVER['REQUEST_METHOD'] == "GET"){
		$tipoUsuarioBusiness = new TipoUsuarioBusiness();
  		$tipos = $tipoUsuarioBusiness->getAllTBTipoUsuarios();
  		echo json_encode($tipos);
	}else if($_SERVER['REQUEST_METHOD'] == "POST"){
		$json = file_get_contents('php://input');
		$data = json_decode($json);

		if($data->metodo == "insertar"){
			$tipo = new TipoUsuario();
			$tipo->setDescripcion($data->descripcion);

			$tipoBusiness = new TipoUsuarioBusiness();

			$resultado = $tipoBusiness->insertarTipo($tipo);

			if($resultado == 1){
		    		echo json_encode(array("statusCode"=>200));	
		    }else{
		    		echo json_encode(array("statusCode"=>400));	
		    }
		}
	}else if($_SERVER['REQUEST_METHOD'] == "PUT"){
		$json = file_get_contents('php://input');
		$data = json_decode($json);

		$tipo = new TipoUsuario();
		$tipo->setId($data->id);
		$tipo->setDescripcion($data->descripcion);
		$tipoBusiness = new TipoUsuarioBusiness();

		$resultado = $tipoBusiness->modificarTipo($tipo);

		if($resultado == 1){
	    		echo json_encode(array("statusCode"=>200));	
	    }else{
	    		echo json_encode(array("statusCode"=>400));	
	    }
	}else if($_SERVER['REQUEST_METHOD'] === "DELETE"){
		
		$id = $_GET['id'];

		$tipoBusiness = new TipoUsuarioBusiness();
		
		$resultado = $tipoBusiness->eliminarTipo($id);

		if($resultado == 1){
	    		echo json_encode(array("statusCode"=>200));	
	    }else{
	    		echo json_encode(array("statusCode"=>400));	
	    }
	}


 ?>
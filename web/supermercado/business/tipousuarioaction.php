<?php 


	include 'tipousuariobusiness.php';
	

	if(isset($_POST['metodo']) && $_POST['metodo'] == "agregar" && isset($_POST['tipodescripcion'])){
		$descripcion = $_POST['tipodescripcion'];
		$tipo = new TipoUsuario( );
		$tipo->setDescripcion($descripcion);
		
		$tipoBusiness = new TipoUsuarioBusiness();
		

		$resultado = $tipoBusiness->insertarTipo($tipo);

		if($resultado == 1){
	    		echo json_encode(array("statusCode"=>200));	
	    }else{
	    		echo json_encode(array("statusCode"=>400));	
	    }

	}else if(isset($_POST['metodo']) && $_POST['metodo'] == "actualizar" && isset($_POST['tipoid']) && isset($_POST['tipodescripcion']) ){
		$id = $_POST['tipoid'];
		$descripcion = $_POST['tipodescripcion'];

		$tipo = new TipoUsuario();
		$tipo->setId($id);
		$tipo->setDescripcion($descripcion);
		$tipoBusiness = new TipoUsuarioBusiness();

		$resultado = $tipoBusiness->modificarTipo($tipo);

		if($resultado == 1){
	    		echo json_encode(array("statusCode"=>200));	
	    }else{
	    		echo json_encode(array("statusCode"=>400));	
	    }
	}else if(isset($_GET['metodo']) && $_GET['metodo'] == "eliminar"){
		$id = $_GET['id'];

		$tipoBusiness = new TipoUsuarioBusiness();
		

		$resultado = $tipoBusiness->eliminarTipo($id);

		if($resultado == 1){
	    		echo json_encode(array("statusCode"=>200));	
	    }else{
	    		echo json_encode(array("statusCode"=>400));	
	    }

	}else if(isset($_GET['metodo']) && $_GET['metodo'] == "obtener" ){
		$tipoUsuarioBusiness = new TipoUsuarioBusiness();
  		$tipos = $tipoUsuarioBusiness->getAllTBTipoUsuarios();

  		$data = array();
  		foreach ($tipos as $row) {
	      $data[] = array(
	         "tipoid"=>$row['tipoid'],
	         "tipodescripcion"=>$row['tipodescripcion']
	      );
   		}

   		$response = array(
   			"iTotalRecords" => count($data),
	      "aaData" => $data
   		);

  		echo json_encode($response);
  	}

?>
<?php 


  include '../business/configuracionbusiness.php';

  if($_SERVER['REQUEST_METHOD'] == "GET"){
		$configuracionBusiness = new ConfiguracionBusiness();
		$configuracion = $configuracionBusiness->obtenerInformacion();
		echo json_encode($configuracion);
	}else if($_SERVER['REQUEST_METHOD'] == "PUT"){
		$json = file_get_contents('php://input');
		$data = json_decode($json);

		
		$id = $data->id;
		$nombre = $data->nombre;
		$telefono = $data->telefono;
		$correo = $data->correo;
		$direccion = $data->direccion;
		$rutaLogo = $data->logo;
		$configuracionBusiness = new ConfiguracionBusiness();
		$resultado = $configuracionBusiness->actualizarInformacion($id,$nombre,$telefono,$correo,$direccion,$rutaLogo);	

		if($resultado == 1){

	    	echo json_encode(array("statusCode"=>200));	
	    }else{
	    	echo json_encode(array("statusCode"=>400));	
	    }
	}


 ?>

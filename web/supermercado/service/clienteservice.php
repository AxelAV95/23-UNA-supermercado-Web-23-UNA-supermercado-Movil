<?php 

	include '../business/clientebusiness.php';

	if($_SERVER['REQUEST_METHOD'] == "GET"){

		$clienteBusiness = new ClienteBusiness();
		$clientes = $clienteBusiness->getAllTBClientes();
  		echo json_encode($clientes);
		


	}else if($_SERVER['REQUEST_METHOD'] == "POST"){
		
			$json = file_get_contents('php://input');
			$data = json_decode($json);
	

			$clienteBusiness = new ClienteBusiness();
			if($data->metodo == "insertar"){
			

				$resultado = $clienteBusiness->insertarCliente(new Cliente(0,$data->nombre,$data->apellidos,$data->cedula,$data->direccion,$data->telefono,$data->correo,$data->fechaafiliacion,$data->tipomembresia));
	
				if($resultado == 1){
						echo json_encode(array("statusCode"=>200));	
				}else{
						echo json_encode(array("statusCode"=>400));	
				}
			}




	}else if($_SERVER['REQUEST_METHOD'] == "PUT"){
		$json = file_get_contents('php://input');
		$data = json_decode($json);

		$clienteBusiness = new ClienteBusiness();
		$resultado = $clienteBusiness->modificarCliente(new Cliente($data->id,$data->nombre,$data->apellidos,$data->cedula,$data->direccion,$data->telefono,$data->correo,$data->fechaafiliacion,$data->tipomembresia));


	    if($resultado == 1){
	    	echo json_encode(array("statusCode"=>200));	
	    }else{
	    	echo json_encode(array("statusCode"=>400));	
	    }





		
	}else if($_SERVER['REQUEST_METHOD'] === "DELETE"){
		$id = $_GET['id'];
		$clienteBusiness = new ClienteBusiness();
		$resultado = $clienteBusiness->eliminarCliente($id);

		if($resultado == 1){
	    		echo json_encode(array("statusCode"=>200));	
	    }else{
	    		echo json_encode(array("statusCode"=>400));	
	    }
	}





 ?>
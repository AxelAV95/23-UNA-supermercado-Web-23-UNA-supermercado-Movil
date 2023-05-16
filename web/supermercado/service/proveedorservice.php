<?php 

	include '../business/proveedorbusiness.php';

	if($_SERVER['REQUEST_METHOD'] == "GET"){
		$proveedorbusiness = new ProveedorBusiness();
  		$proveedores = $proveedorbusiness->getAllTBProveedores();
  		echo json_encode($proveedores);
	}else if($_SERVER['REQUEST_METHOD'] == "POST"){
		//se utiliza en PHP para obtener el contenido de la solicitud HTTP entrante
		$json = file_get_contents('php://input');
		$data = json_decode($json);

		if($data->metodo == "insertar"){
			$proveedor = new Proveedor();
			$proveedor ->setNombre($data->nombre);
			$proveedor ->setDireccion($data->direccion);
			$proveedor ->setTelefono($data->telefono);
			$proveedor ->setCorreo($data->correo);
			$proveedor ->setLatitud($data->latitud);
			$proveedor ->setLongitud($data->longitud);

			$proveedorBusiness = new ProveedorBusiness();

			$resultado = $proveedorBusiness->insertarProveedor($proveedor);

			if($resultado == 1){
		    		echo json_encode(array("statusCode"=>200));	
		    }else{
		    		echo json_encode(array("statusCode"=>400));	
		    }
		}
	}else if($_SERVER['REQUEST_METHOD'] == "PUT"){
		$json = file_get_contents('php://input');
		$data = json_decode($json);

		$proveedor = new Proveedor();
		$proveedor ->setId($data->id);
		$proveedor ->setNombre($data->nombre);
		$proveedor ->setDireccion($data->direccion);
		$proveedor ->setTelefono($data->telefono);
		$proveedor ->setCorreo($data->correo);
		$proveedor ->setLatitud($data->latitud);
		$proveedor ->setLongitud($data->longitud);
		$proveedorBusiness = new ProveedorBusiness();

		$resultado = $proveedorBusiness->actualizar($proveedor);

		if($resultado == 1){
	    		echo json_encode(array("statusCode"=>200));	
	    }else{
	    		echo json_encode(array("statusCode"=>400));	
	    }
	}else if($_SERVER['REQUEST_METHOD'] === "DELETE"){
		
		$id = $_GET['id'];

		$proveedorBusiness = new ProveedorBusiness();
		
		$resultado = $proveedorBusiness->eliminarProveedor($id);

		if($resultado == 1){
	    		echo json_encode(array("statusCode"=>200));	
	    }else{
	    		echo json_encode(array("statusCode"=>400));	
	    }
	}


?>
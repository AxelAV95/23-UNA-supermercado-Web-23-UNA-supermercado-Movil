<?php 

	include '../business/usuariobusiness.php';

	if($_SERVER['REQUEST_METHOD'] == "GET"){


		$usuarioBusiness = new UsuarioBusiness();
		
		if($_GET['metodo'] == "obtener"){
			$usuarios = $usuarioBusiness->getAllTBusuarios();
  			echo json_encode($usuarios);
		}

		if($_GET['metodo'] == "verificarEmpleado"){
			
			$resultado = $usuarioBusiness->verificarEmpleadoUsuario($_GET['empleadoid']);
			if($resultado == 1){
				echo json_encode(array("statusCode"=>409));	
			}else{
				echo json_encode(array("statusCode"=>400));	
			}
		}
		
	}else if($_SERVER['REQUEST_METHOD'] == "POST"){

	 	$json = file_get_contents('php://input');
		$data = json_decode($json);

		if( $data->metodo == "iniciarSesionQR"){

			if($data->cedula == ""){
				echo json_encode(array("response" => "400"));
			}else{
				$cedula = $data->cedula;
				$usuarioBusiness = new UsuarioBusiness();
				$datosUsuario = $usuarioBusiness->obtenerUsuarioLogin(
				$cedula);

				if(count($datosUsuario) > 0){
					echo json_encode(array("response" => $datosUsuario));
				}
			}
		}

		if( $data->metodo == "iniciarSesionNormal"){

			if($data->cedula == ""){
				echo json_encode(array("response" => "400"));
			}else{
				$cedula = $data->cedula;
				$usuarioBusiness = new UsuarioBusiness();
				$datosUsuario = $usuarioBusiness->obtenerUsuarioLogin(
				$cedula);

				if(count($datosUsuario) > 0){
					echo json_encode(array("response" => $datosUsuario));
				}
			}
		}


		if($data->metodo == "insertar"){
			$usuarioBusiness = new UsuarioBusiness();
			$empleadoid = $data->empleadoid;
            $password = $data->password;
            $tipoid = $data->tipoid;
			
	    	$usuario = new Usuario();
			$usuario->setEmpleadoId($empleadoid);
			$usuario->setPassword($password);
			$usuario->setTipoid($tipoid);
						
	    	$resultado = $usuarioBusiness->insertarUsuario($usuario);

	    	if($resultado == 1){
	    		echo json_encode(array("statusCode"=>200));	
	    	}else{
	    		echo json_encode(array("statusCode"=>400));	
	    	}
		}
	}else if($_SERVER['REQUEST_METHOD'] == "PUT"){
		$json = file_get_contents('php://input');
		$data = json_decode($json);

		$id = $data->usuarioid; 			
        $password = $data->usuariopassword; 
        $tipoid = $data->tipoid;
			

		$usuarioBusiness = new UsuarioBusiness();
		$usuario = new Usuario(); 
		$usuario->setId($id);			
        $usuario->setPassword($password);
        $usuario->setTipoid($tipoid);
				
	    $resultado = $usuarioBusiness->modificarusuario($usuario);

	    if($resultado == 1){
	    	echo json_encode(array("statusCode"=>200));	
	    }else{
	    	echo json_encode(array("statusCode"=>400));	
	    }
		
	}else if($_SERVER['REQUEST_METHOD'] === "DELETE"){
		$id = $_GET['id'];
		$usuarioBusiness = new UsuarioBusiness();
		$resultado = $usuarioBusiness->eliminarUsuario($id);

		if($resultado == 1){
	    		echo json_encode(array("statusCode"=>200));	
	    }else{
	    		echo json_encode(array("statusCode"=>400));	
	    }
	}




 ?>
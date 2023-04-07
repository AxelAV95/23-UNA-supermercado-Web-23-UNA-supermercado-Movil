<?php 
	session_start();
	include 'usuariobusiness.php';
	include 'tipousuariobusiness.php';
	include 'configuracionbusiness.php';


	if(isset($_POST['metodo']) && $_POST['metodo'] == "agregar" ){

		$usuarioBusiness = new UsuarioBusiness();
			$empleadoid = $_POST['empleadoid'];
            $password = $_POST['usuariopassword'];
            $tipoid = $_POST['tipoid'];
			

			
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
		
		
	}else if(isset($_POST['metodo']) && $_POST['metodo'] == "actualizar" && isset($_POST['tipoid'])  ){
			
			$id = $_POST['usuarioid'];
			
            $password = $_POST['usuariopassword'];
            $tipoid = $_POST['tipoid'];
			

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


	} else if(isset($_GET['metodo']) && $_GET['metodo'] == "eliminar"){
		$id = $_GET['usuarioid'];
		$usuarioBusiness = new UsuarioBusiness();
		$resultado = $usuarioBusiness->eliminarUsuario($id);

		if($resultado == 1){
	    		echo json_encode(array("statusCode"=>200));	
	    }else{
	    		echo json_encode(array("statusCode"=>400));	
	    }


	}else if(isset($_POST['metodo']) && $_POST['metodo'] == "iniciarSesion"){
		if(isset($_POST['usuariocedula'] ) && isset($_POST['usuariopassword']) ){

			$cedula = $_POST['usuariocedula'];
			$password = $_POST['usuariopassword'];

			$usuarioBusiness = new UsuarioBusiness();
			$datosUsuario = $usuarioBusiness->obtenerUsuarioLogin(
				$cedula);

			
			if(count($datosUsuario) > 0){
				if($datosUsuario[0]['empleadocedula'] == $cedula && password_verify($password, $datosUsuario[0]['usuariopassword'])){
					
					$configuracionBusiness = new ConfiguracionBusiness();
					$configuracion = $configuracionBusiness->obtenerInformacion();

					$_SESSION["empleadoid"] = $datosUsuario[0]['empleadoid'];
					$_SESSION["empleadocedula"] = $datosUsuario[0]['empleadocedula'];
					$_SESSION["empleadonombre"] = $datosUsuario[0]['empleadonombre'];
					$_SESSION["empleadoapellidos"] = $datosUsuario[0]['empleadoapellidos'];
					$_SESSION["tipodescripcion"] = $datosUsuario[0]['tipodescripcion'];
					$_SESSION["sesionActiva"] = 1;
					$_SESSION['logo'] = $configuracion[0]['supermercadologo'];
					$_SESSION['nombresuper'] = $configuracion[0]['supermercadonombre'];

					echo json_encode(array("statusCode"=>200));	
				
				}else{
					echo json_encode(array("statusCode"=>400));	
				}
			}else{
				echo json_encode(array("statusCode"=>404));	
			}
			// if(count($datosUsuario)>=1){
			// 	if($datosUsuario[0]['usuariocorreo'] == $correo &&  $datosUsuario[0]['usuariopassword'] == $password){

			// 		$_SESSION["id"] = $datosUsuario[0]['usuarioid'];
			// 		$_SESSION["usuario"] = $datosUsuario[0]['usuarionombre'];

			// 		header("location: ../view/index.php" );
			// 	}else{
			// 		header("location: ../view/loginview.php?mensaje=4" );
			// 	}
			// }else{
			// 	header("location: ../view/loginview.php?mensaje=3" );
			// }

		// }else{
		// 	header("location: ../view/loginview.php?mensaje=2" );
		// }
			}
		
	}else if(isset($_GET['metodo']) && $_GET['metodo'] == "cerrarSesion"){
		session_destroy();
		header("location: ../view/loginview.php");
	}else if(isset($_GET['metodo']) && $_GET['metodo'] == "obtenerTipos" ){
		$tipoUsuarioBusiness = new TipoUsuarioBusiness();
  		$tipos = $tipoUsuarioBusiness->getAllTBTipoUsuarios();
		//objeto business empleado
		//obtener los empleados
		//pasar por json
		
		echo json_encode($tipos);
	}else if(isset($_GET['metodo']) && $_GET['metodo'] == "obtenerEmpleados"){
		$empleados =  array(
		  array('empleadoid' => 1, 'empleadonombre' => 'Option 1'),
		  array('empleadoid' => 2, 'empleadonombre' => 'Option 2'),
		  array('empleadoid' => 3, 'empleadonombre' => 'Option 3')

		);

		echo json_encode($empleados);


	}else if(isset($_GET['metodo']) && $_GET['metodo'] == "verificarEmpleado"){

		$usuarioBusiness = new UsuarioBusiness();
  		$resultado = $usuarioBusiness->verificarEmpleadoUsuario($_GET['empleadoid']);


	    if($resultado == 1){
	    	echo json_encode(array("statusCode"=>409));	
	    }else{
	    	echo json_encode(array("statusCode"=>400));	
	    }

	}else if(isset($_GET['metodo']) && $_GET['metodo'] == "obtener" ){
		$usuarioBusiness = new UsuarioBusiness();
		$usuarios = $usuarioBusiness->getAllTBusuarios();

		$data = array();
  		foreach ($usuarios  as $row) {
	      $data[] = array(
	         "usuarioid"=>$row['usuarioid'],
	         "usuariopassword"=>$row['usuariopassword'],
	         "usuarioempleadoid"=>$row['usuarioempleadoid'],
	         "usuariotipo"=>$row['usuariotipo'],
	         "empleadonombre"=>$row['empleadonombre'],
	         "empleadoapellidos"=>$row['empleadoapellidos'],
	         "tipoid"=>$row['tipoid'],
	         "tipodescripcion"=>$row['tipodescripcion']


	      );
   		}

   		$response = array(
   			"iTotalRecords" => count($data),
	      "aaData" => $data
   		);

  		echo json_encode($response);
	}else if(isset($_GET['metodo']) && $_GET['metodo'] == "verificarUsuario"){

		$usuarioBusiness = new UsuarioBusiness();
  		$resultado = $usuarioBusiness->verificarCuentaUsuario($_GET['empleadocedula']);


	    if($resultado == 1){
	    	echo json_encode(array("statusCode"=>200));	
	    }else{
	    	echo json_encode(array("statusCode"=>400));	
	    }

	}



 ?>
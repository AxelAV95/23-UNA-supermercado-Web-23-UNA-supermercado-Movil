<?php 
	session_start();
	include 'usuariobusiness.php';


	if(isset($_POST['insertar'])){
		if(isset($_POST['usuarionombre'])&& isset($_POST['usuariotelefono']) && isset($_POST['usuariocorreo'])
        && isset($_POST['usuariopassword'])
        && isset($_POST['tipoid'])){
			$usuarioBusiness = new UsuarioBusiness();
			$nombre = $_POST['usuarionombre'];
			$telefono = $_POST['usuariotelefono'];
            $correo = $_POST['usuariocorreo'];
            $password = $_POST['usuariopassword'];
            $tipoid = $_POST['tipoid'];
			

			
	    	$usuario = new Usuario();
			$usuario->setNombre($nombre);	 
			$usuario->setTelefono($telefono);			   		    	
	    	$usuario->setCorreo($correo);
			$usuario->setPassword($password);
			$usuario->setTipoid($tipoid);
			
			
	    	$resultado = $usuarioBusiness->insertarusuario($usuario);

	    	if($resultado == 1){
	    		header("location: ../view/backend/usuarioview.php?mensaje=1" );
	    	}else{
	    		header("location: ../view/backend/usuarioview.php?mensaje=4" );
	    	}
        }else if(isset($_POST['id'])){

			$categoriaid = $_POST['id'];
			$categoriaBusiness = new CategoriaBusiness();
			$resultado = $categoriaBusiness->getAllTBCategorias($categoriaid);
	
			$datos = "";
			foreach ($resultado as $dato) { 
				
				$datos .= "<option>".$dato['categoriaid']."</option>";			
				
			}
			echo $datos;


		 }
			 
		
	}else if(isset($_POST['actualizar'])){
		if(isset($_POST['usuarionombre']) && isset($_POST['usuariotelefono']) && isset($_POST['usuariocorreo'])
        && isset($_POST['usuariopassword']) && isset($_POST['tipoid'])&&isset($_POST['usuarioid'])){
			$id = $_POST['usuarioid'];
			$nombre = $_POST['usuarionombre'];
			$telefono = $_POST['usuariotelefono'];
            $correo = $_POST['usuariocorreo'];
            $password = $_POST['usuariopassword'];
            $tipoid = $_POST['tipoid'];
			

				$usuarioBusiness = new UsuarioBusiness();
				$usuario = new Usuario(); 
				$usuario->setId($id);
				$usuario->setNombre($nombre);
	    		$usuario->setTelefono($telefono);
                $usuario->setCorreo($correo);
                $usuario->setPassword($password);
                $usuario->setTipoid($tipoid);
				
	    		$resultado = $usuarioBusiness->modificarusuario($usuario);

	    		if($resultado == 1){
	    			header("location: ../view/backend/usuarioview.php?mensaje=2" );
	    		}else{
	    			header("location: ../view/backend/usuarioview.php?mensaje=4" );
	    		}

			}
		


	} else if(isset($_GET['eliminar'])){
		$id = $_GET['usuarioid'];
		$usuarioBusiness = new UsuarioBusiness();
		$resultado = $usuarioBusiness->eliminarusuario($id);

		if($resultado == 1){
			header("location: ../view/backend/usuarioview.php?mensaje=3" );
		}else{
			header("location: ../view/backend/usuarioview.php?mensaje=4" );
		}


	}
	else if(isset($_POST['iniciarSesion']) ){
		if(isset($_POST['usuariocorreo'] ) && isset($_POST['usuariopassword']) ){

			$correo = $_POST['usuariocorreo'];
			$password = $_POST['usuariopassword'];

			$usuarioBusiness = new UsuarioBusiness();
			$datosUsuario = $usuarioBusiness->obtenerUsuarioLogin(
				$correo, $password);

			if(count($datosUsuario)>=1){
				if($datosUsuario[0]['usuariocorreo'] == $correo &&  $datosUsuario[0]['usuariopassword'] == $password){

					$_SESSION["id"] = $datosUsuario[0]['usuarioid'];
					$_SESSION["usuario"] = $datosUsuario[0]['usuarionombre'];

					header("location: ../view/index.php" );
				}else{
					header("location: ../view/loginview.php?mensaje=4" );
				}
			}else{
				header("location: ../view/loginview.php?mensaje=3" );
			}

		}else{
			header("location: ../view/loginview.php?mensaje=2" );
		}
		
	}else if(isset($_GET['cerrarSesion'])){
		session_destroy();
		header("location: ../view/loginview.php");
	}



 ?>
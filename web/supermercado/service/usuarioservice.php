<?php 

	include '../business/usuariobusiness.php';

	 if($_SERVER['REQUEST_METHOD'] == "POST"){

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


		/*if( $data->metodo == "iniciarSesionQR"){

			if($data->cedula == ""){
				echo json_encode(array("HTTP_CODE" => "400"));
			}else{
				$cedula = $data->cedula;
				$usuarioBusiness = new UsuarioBusiness();
				$datosUsuario = $usuarioBusiness->obtenerUsuarioLogin(
				$cedula);

				if(count($datosUsuario) > 0){
					echo json_encode(array("HTTP_CODE" => $data->metodo));
				}
			}



		}*/



		//echo json_encode(array("HTTP_CODE" => $data->metodo));
	}




 ?>
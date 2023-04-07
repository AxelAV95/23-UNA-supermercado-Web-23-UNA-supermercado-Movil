<?php 
	session_start();
	include 'configuracionbusiness.php';


	if(isset($_GET['metodo']) && $_GET['metodo'] == "obtener"){

		$configuracionBusiness = new ConfiguracionBusiness();
		$configuracion = $configuracionBusiness->obtenerInformacion();
		echo json_encode($configuracion);



	}else if(isset($_POST['metodo']) && $_POST['metodo'] == "actualizar"){

		$configuracionBusiness = new ConfiguracionBusiness();

		$id = $_POST['supermercadoid'];
		$nombre = $_POST['supermercadonombre'];
		$telefono = $_POST['supermercadotelefono'];
		$correo = $_POST['supermercadocorreo'];
		$direccion = $_POST['supermercadodireccion'];
		$ruta = "";
		$rutaDB = "";
		$destino ="";
		//$destino = $ruta . basename($_FILES['image']['supermercadologo']);

		if(isset($_POST['imagenActual']) && $_POST['imagenActual'] == "img/otros/logo.png"){
			$ruta = "img/otros/logo.png";
			$rutaDB = "img/otros/logo.png";
		}else{
			if(isset($_FILES['supermercadologo']['tmp_name'])){
			
			$aleatorio = mt_rand(100,999);
			list($ancho, $alto) = getimagesize($_FILES["supermercadologo"]["tmp_name"]);

			if($_FILES["supermercadologo"]["type"] == "image/jpeg"){

					 $directorio = "img/otros";
				     $ruta = "../view/".$directorio."/".$aleatorio.".jpg";
				     $rutaDB = $directorio."/".$aleatorio.".jpg";
				     $origen = imagecreatefromjpeg($_FILES["supermercadologo"]["tmp_name"]);      

				     $destino = imagecreatetruecolor(160, 160);

				     imagecopyresized($destino, $origen, 0, 0, 0, 0, 160, 160, $ancho, $alto);

				     imagejpeg($destino, $ruta);

			}else if($_FILES["supermercadologo"]["type"] == "image/png"){

					 $directorio = "img/otros";
				     $ruta = "../view/".$directorio."/".$aleatorio.".png";
				     $rutaDB = $directorio."/".$aleatorio.".png";

				     $origen = imagecreatefrompng($_FILES["supermercadologo"]["tmp_name"]);      

				     $destino = imagecreatetruecolor(160, 160);
				     imagealphablending($destino, false);
				     imagesavealpha($destino, true);

				     imagecopyresized($destino, $origen, 0, 0, 0, 0,160, 160, $ancho, $alto);

				     imagepng($destino, $ruta);

	    		}
		}

		}
		

		
		

		$_SESSION['logo'] = $rutaDB;


		$resultado = $configuracionBusiness->actualizarInformacion($id,$nombre,$telefono,$correo,$direccion,$rutaDB);	
		if($resultado == 1){
	    	echo json_encode(array("statusCode"=>200));	
	    }else{
	    	echo json_encode(array("statusCode"=>400));	
	    }



	}


?>
<?php 

echo $_GET['eliminar'];
echo $_GET['id'];
echo $_GET['imagen'];
	// if(isset($_POST['insertar'])){
	// 	if(isset($_POST['categoriadescripcion'])){
	// 		echo $descripcion = $_POST['categoriadescripcion'];
	// 		$_FILES["nuevaImagen"]["tmp_name"];

	// 		if(isset($_FILES["nuevaImagen"]["tmp_name"])){
	// 			echo $ruta = "img/categorias/default/anonymous.png";
	// 			list($ancho, $alto) = getimagesize($_FILES["nuevaImagen"]["tmp_name"]);


	// 			// $nuevoAncho = 160;
	//    //  		$nuevoAlto = 200;
	//     		$aleatorio = mt_rand(100,999);
	//     		$directorio = "img/categorias/".$aleatorio;
	// 			mkdir($directorio, 0755);

	// 			 if($_FILES["nuevaImagen"]["type"] == "image/jpeg"){


	// 			     $ruta = "img/categorias/".$aleatorio."/".$aleatorio.".jpg";

	// 			     $origen = imagecreatefromjpeg($_FILES["nuevaImagen"]["tmp_name"]);      

	// 			     $destino = imagecreatetruecolor($nuevoAncho, $nuevoAlto);

	// 			     imagecopyresized($destino, $origen, 0, 0, 0, 0, 160, 200, $ancho, $alto);

	// 			     imagejpeg($destino, $ruta);

	// 		    }

	// 		    if($_FILES["nuevaImagen"]["type"] == "image/png"){


	// 			    echo $ruta = "img/categorias/".$aleatorio."/".$aleatorio.".png";

	// 			     $origen = imagecreatefrompng($_FILES["nuevaImagen"]["tmp_name"]);      

	// 			     $destino = imagecreatetruecolor($nuevoAncho, $nuevoAlto);

	// 			     imagecopyresized($destino, $origen, 0, 0, 0, 0, 160, 200, $ancho, $alto);

	// 			     imagepng($destino, $ruta);

	//     		}

	    		
	// 		}

	// 		// $categoriaBusiness = new CategoriaBusiness();
	//   //   	$categoria = new Categoria();
	//   //   	$categoria->setDescripcion($descripcion);
	//   //   	$categoria->setImagen($ruta);
	//   //   	$resultado = $categoriaBusiness->insertarCategoria($categoria);

	//   //   	if($resultado == 1){
	//   //   		header("location: ../view/backend/categoriaview.php" );
	//   //   	}
			


	// 	}
	// }

 ?>
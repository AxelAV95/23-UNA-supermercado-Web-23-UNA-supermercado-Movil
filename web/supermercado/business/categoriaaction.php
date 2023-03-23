<?php 	 
	
	include 'categoriabusiness.php';

	if(isset($_POST['insertar'])){
		if(isset($_POST['categoriadescripcion'])){
			$categoriaBusiness = new CategoriaBusiness();
			$descripcion = $_POST['categoriadescripcion'];
			$ruta = "img/categorias/default/anonymous.png";
			$codigo = $categoriaBusiness->getUltimoIdInsertado()+11;
			if(isset($_FILES["nuevaImagen"]["tmp_name"])){

				list($ancho, $alto) = getimagesize($_FILES["nuevaImagen"]["tmp_name"]);


				// $nuevoAncho = 160;
	   //  		$nuevoAlto = 200;
	    		$aleatorio = mt_rand(100,999);
	    		$directorio = "../view/backend/img/categorias/".$codigo;
				mkdir($directorio, 0755);

				 if($_FILES["nuevaImagen"]["type"] == "image/jpeg"){


				     $ruta = $directorio."/".$aleatorio.".jpg";
				     $rutaAux = "img/categorias/".$codigo."/".$aleatorio.".jpg";
				     $origen = imagecreatefromjpeg($_FILES["nuevaImagen"]["tmp_name"]);      

				     $destino = imagecreatetruecolor(160, 160);

				     imagecopyresized($destino, $origen, 0, 0, 0, 0, 160, 160, $ancho, $alto);

				     imagejpeg($destino, $ruta);

			    }

			    if($_FILES["nuevaImagen"]["type"] == "image/png"){


				     $ruta = $directorio."/".$aleatorio.".png";
				     $rutaAux = "img/categorias/".$codigo."/".$aleatorio.".png";

				     $origen = imagecreatefrompng($_FILES["nuevaImagen"]["tmp_name"]);      

				     $destino = imagecreatetruecolor(160, 160);
				      imagealphablending($destino, false);
				     imagesavealpha($destino, true);

				     imagecopyresized($destino, $origen, 0, 0, 0, 0,160, 160, $ancho, $alto);

				     imagepng($destino, $ruta);

	    		}

	    		
			}

			
	    	$categoria = new Categoria();
	    	$categoria->setDescripcion($descripcion);
	    	$categoria->setImagen($rutaAux);
	    	$categoria->setCodigo($codigo);
	    	$resultado = $categoriaBusiness->insertarCategoria($categoria);

	    	if($resultado == 1){
	    		header("location: ../view/backend/categoriaview.php?mensaje=1" );
	    	}else{
	    		header("location: ../view/backend/categoriaview.php?mensaje=4" );
	    	}
			


		}
	}else if(isset($_POST['actualizar'])){
		if(isset($_POST['categoriadescripcion']) && isset($_POST['categoriaid'])){
			$id = $_POST['categoriaid'];
			$descripcion = $_POST['categoriadescripcion'];
			$ruta = $_POST["imagenActual"];
			if(isset($_FILES["editarImagen"]["tmp_name"]) && !empty($_FILES["editarImagen"]["tmp_name"])){
				
				list($ancho, $alto) = getimagesize($_FILES["editarImagen"]["tmp_name"]);
				$directorio = "../view/backend/img/categorias/".$_POST["categoriacodigo"];

				if(!empty($_POST["imagenActual"]) && $_POST["imagenActual"] != "../view/backend/img/categorias/default/anonymous.png"){

						unlink("../view/backend/".$_POST["imagenActual"]);

				}else{

						mkdir($directorio, 0755);	
					
				}

				if($_FILES["editarImagen"]["type"] == "image/jpeg"){

						

						$aleatorio = mt_rand(100,999);

						$ruta = $directorio."/".$aleatorio.".jpg";
				     	$rutaAux = "img/categorias/".$_POST["categoriacodigo"]."/".$aleatorio.".jpg";

						$origen = imagecreatefromjpeg($_FILES["editarImagen"]["tmp_name"]);						

						$destino = imagecreatetruecolor(160, 160);
						 imagealphablending($destino, false);
				     	imagesavealpha($destino, true);

						imagecopyresized($destino, $origen, 0, 0, 0, 0, 160, 160, $ancho, $alto);

						imagejpeg($destino, $ruta);

				}

				if($_FILES["editarImagen"]["type"] == "image/png"){

						

						$aleatorio = mt_rand(100,999);

						$ruta = $directorio."/".$aleatorio.".png";
				     	$rutaAux = "img/categorias/".$_POST["categoriacodigo"]."/".$aleatorio.".png";

						$origen = imagecreatefrompng($_FILES["editarImagen"]["tmp_name"]);						

						$destino = imagecreatetruecolor(160, 160);
						 imagealphablending($destino, false);
				     imagesavealpha($destino, true);

						imagecopyresized($destino, $origen, 0, 0, 0, 0, 160, 160, $ancho, $alto);

						imagepng($destino, $ruta);

				}

				$categoriaBusiness = new CategoriaBusiness();
				$categoria = new Categoria();
				$categoria->setId($id);
				$categoria->setDescripcion($descripcion);
	    		$categoria->setImagen($rutaAux);
	    		$resultado = $categoriaBusiness->modificarCategoria($categoria);

	    		if($resultado == 1){
	    			header("location: ../view/backend/categoriaview.php?mensaje=2" );
	    		}else{
	    			header("location: ../view/backend/categoriaview.php?mensaje=4" );
	    		}

			}
		}


	} else if(isset($_GET['eliminar'])){
		$id = $_GET['id'];
		$rutaImagen = $_GET['imagen'];
		$codigo = $_GET['codigo'];


		if($_GET["imagen"] != "" && $_GET["imagen"] != "../view/backend/img/categorias/default/anonymous.png"){

				unlink("../view/backend/".$_GET["imagen"]);
				rmdir('../view/backend/img/categorias/'.$_GET["codigo"]);

		}

		$categoriaBusiness = new CategoriaBusiness();
		$resultado = $categoriaBusiness->eliminarCategoria($id);

		if($resultado == 1){
			header("location: ../view/backend/categoriaview.php?mensaje=3" );
		}else{
			header("location: ../view/backend/categoriaview.php?mensaje=4" );
		}


	}else if(isset($_GET['obtenerCategorias'])){

		$categoriaBusiness = new CategoriaBusiness();
		$categorias = $categoriaBusiness ->getAllTBCategorias();
		$opciones = "";

		if(isset($_GET['categoriaid'])){
			$id = $_GET['categoriaid'];

			foreach ($categorias as $categoria) {
				if($categoria['categoriaid'] == $id){
					$opciones .= '<option selected value="'.$categoria['categoriaid'].'">'.$categoria['categoriadescripcion'].'</option>';
				}else{
					$opciones .= '<option value="'.$categoria['categoriaid'].'">'.$categoria['categoriadescripcion'].'</option>';
				}
				
			}


		}else{
			foreach ($categorias as $categoria) {
				$opciones .= '<option value="'.$categoria['categoriaid'].'">'.$categoria['categoriadescripcion'].'</option>';
			}
		}
		

		

		
		echo $opciones;
	}


?>
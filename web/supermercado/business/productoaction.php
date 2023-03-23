<?php 	 
	
	include 'productobusiness.php';
	//var_dump($_FILES["nuevaImagen"]["tmp_name"]);
	if(isset($_POST['insertar'])){
		if(isset($_POST['productonombre'])
		&&isset($_POST['productoprecio']) && isset($_POST['productoestado'])
		&&isset($_POST['productocategoriaid']) 
		){
			$productoBusiness = new ProductoBusiness();		
			$nombre = $_POST['productonombre'];	
			$ruta = "img/productos/default/anonymous.png";	
			$precio = $_POST['productoprecio'];
			$estado = $_POST['productoestado'];
			$categoria = $_POST['productocategoriaid'];
			$codigo = $productoBusiness->getUltimoIdInsertado()+11;
			
			

			if(isset($_FILES["nuevaImagen"]["tmp_name"])){

				list($ancho, $alto) = getimagesize($_FILES["nuevaImagen"]["tmp_name"]);


				// $nuevoAncho = 160;
	   //  		$nuevoAlto = 200;
	    		$aleatorio = mt_rand(100,999);
	    		$directorio = "../view/backend/img/productos/".$codigo;
				mkdir($directorio, 0755);

				 if($_FILES["nuevaImagen"]["type"] == "image/jpeg"){


				     $ruta = $directorio."/".$aleatorio.".jpg";
				     $rutaAux = "img/productos/".$codigo."/".$aleatorio.".jpg";
				     $origen = imagecreatefromjpeg($_FILES["nuevaImagen"]["tmp_name"]);      

				     $destino = imagecreatetruecolor(160, 160);

				     imagecopyresized($destino, $origen, 0, 0, 0, 0, 160, 160, $ancho, $alto);

				     imagejpeg($destino, $ruta);

			    }

			    if($_FILES["nuevaImagen"]["type"] == "image/png"){

					echo "entro";
				     $ruta = $directorio."/".$aleatorio.".png";
				     $rutaAux = "img/productos/".$codigo."/".$aleatorio.".png";

				     $origen = imagecreatefrompng($_FILES["nuevaImagen"]["tmp_name"]);      

				     $destino = imagecreatetruecolor(160, 160);
				     imagealphablending($destino, false);
				     imagesavealpha($destino, true);

				     imagecopyresized($destino, $origen, 0, 0, 0, 0,160, 160, $ancho, $alto);

				     imagepng($destino, $ruta);

	    		}

	    		
			}

		
	    	$producto = new Producto(0,$nombre,$rutaAux,$precio,$estado,$categoria,$codigo);				 
			
			
	    	$resultado = $productoBusiness->insertarProducto($producto);


	    	if($resultado == 1){
	    		header("location: ../view/backend/productoview.php?mensaje=1" );
	    	}else{
	    		header("location: ../view/backend/productoview.php?mensaje=4" );
	    	}
			 
		}

		
	}else if(isset($_POST['actualizar'])){
		if(isset($_POST['productonombre']) && isset($_POST['productoprecio'])
        && isset($_POST['productoestado']) && isset($_POST['productocategoriaid'])
        && isset($_POST['productoid'])){
			$productoid = $_POST['productoid'];		
            $productonombre = $_POST['productonombre'];			
            $productoprecio = $_POST['productoprecio'];
            $productoestado = $_POST['productoestado'];
            $productocategoriaid = $_POST['productocategoriaid'];
            $productocodigo = $_POST['productocodigo'];			
			$ruta = $_POST["imagenActual"];
			if(isset($_FILES["editarImagen"]["tmp_name"]) && !empty($_FILES["editarImagen"]["tmp_name"])){
				
				list($ancho, $alto) = getimagesize($_FILES["editarImagen"]["tmp_name"]);
				$directorio = "../view/backend/img/productos/".$productocodigo;

				if(!empty($_POST["imagenActual"]) && $_POST["imagenActual"] != "../view/backend/img/productos/default/anonymous.png"){

						unlink("../view/backend/".$_POST["imagenActual"]);

				}else{

						mkdir($directorio, 0755);	
					
				}

				if($_FILES["editarImagen"]["type"] == "image/jpeg"){

						

						$aleatorio = mt_rand(100,999);

						$ruta = $directorio."/".$aleatorio.".jpg";
				     	$rutaAux = "img/productos/".$productocodigo."/".$aleatorio.".jpg";

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
				     	$rutaAux = "img/productos/".$productocodigo."/".$aleatorio.".png";

						$origen = imagecreatefrompng($_FILES["editarImagen"]["tmp_name"]);						

						$destino = imagecreatetruecolor(160, 160);
						 imagealphablending($destino, false);
				     imagesavealpha($destino, true);


						imagecopyresized($destino, $origen, 0, 0, 0, 0, 160, 160, $ancho, $alto);

						imagepng($destino, $ruta);

				}

				$productoBusiness = new ProductoBusiness();
				$producto = new Producto($productoid, $productonombre, $rutaAux,$productoprecio,$productoestado,$productocategoriaid,$productocodigo);
				

	    		$resultado = $productoBusiness->modificarProducto($producto);

	    		if($resultado == 1){
	    			header("location: ../view/backend/productoview.php?mensaje=2" );
	    		}else{
	    			header("location: ../view/backend/productoview.php?mensaje=4" );
	    		}

			}
		}


	


	}else if(isset($_GET['eliminar'])){
		$productoid = $_GET['productoid'];
		$rutaImagen = $_GET['productoimg'];
	
		$productocodigo = $_GET['productocodigo'];


		if($_GET["imagen"] != "" && $_GET["imagen"] != "../view/backend/img/productos/default/anonymous.png"){

				unlink("../view/backend/".$_GET["imagen"]);
				rmdir('../view/backend/img/productos/'.$_GET["codigo"]);

		}

		$productoBusiness = new ProductoBusiness();
		$resultado = $productoBusiness->eliminarProducto($productoid);

		if($resultado == 1){
			header("location: ../view/backend/productoview.php?mensaje=3" );
		}else{
			header("location: ../view/backend/productoview.php?mensaje=4" );
		}
	}






?>
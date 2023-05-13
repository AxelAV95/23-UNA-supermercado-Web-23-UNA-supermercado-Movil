<?php 	 
	
	include 'productobusiness.php';
	//var_dump($_FILES["nuevaImagen"]["tmp_name"]);
	if(isset($_POST['insertar'])){
		if(isset($_POST['productonombre'])
		&&isset($_POST['productoprecio']) && isset($_POST['productoestado'])
		&&isset($_POST['productocategoriaid']) && isset($_POST['productofechaingreso'])&&isset($_POST['productostock'])
		&&isset($_POST['productoproveedorid'])
		){
			$productoBusiness = new ProductoBusiness();		
			$nombre = $_POST['productonombre'];	
			$precio = $_POST['productoprecio'];
			$fecha = $_POST['productofechaingreso'];
			$stock = $_POST['productostock'];
			$estado = $_POST['productoestado'];
			$categoria = $_POST['productocategoriaid'];
			$proveedor = $_POST['productoproveedorid'];

			

		

		
	    	$producto = new Producto(0,$nombre,$precio,$estado,$fecha,$stock,$categoria,$proveedor);				 
			
			
	    	$resultado = $productoBusiness->insertarProducto($producto);


	    	if($resultado == 1){
	    		header("location: ../view/productoview.php?mensaje=1" );
	    	}else{
	    		header("location: ../view/productoview.php?mensaje=4" );
	    	}
			 
		}

		
	}else if(isset($_POST['actualizar'])){
		if(isset($_POST['productonombre']) && isset($_POST['productoprecio'])
        && isset($_POST['productoestado']) && isset($_POST['productocategoriaid'])
		&& isset($_POST['productofechaingreso'])  && isset($_POST['productostock'])
        && isset($_POST['productoid']) && isset($_POST['productoproveedorid'])){
			$productoid = $_POST['productoid'];		
            $productonombre = $_POST['productonombre'];			
            $productoprecio = $_POST['productoprecio'];
            $productoestado = $_POST['productoestado'];
			$productofechaingreso = $_POST['productofechaingreso'];
            $productostock = $_POST['productostock'];
            $productocategoriaid = $_POST['productocategoriaid'];
            $productoproveedorid = $_POST['productoproveedorid'];
			

			
			

				$productoBusiness = new ProductoBusiness();
				$producto = new Producto($productoid, $productonombre,$productoprecio,$productoestado
				,$productofechaingreso,$productostock,$productocategoriaid,$productoproveedorid);
				

	    		$resultado = $productoBusiness->modificarProducto($producto);

	    		if($resultado == 1){
	    			header("location: ../view/productoview.php?mensaje=2" );
	    		}else{
	    			header("location: ../view/productoview.php?mensaje=4" );
	    		}

			
		}


	


	}else if(isset($_GET['eliminar'])){
		$productoid = $_GET['productoid'];
	


	

		$productoBusiness = new ProductoBusiness();
		$resultado = $productoBusiness->eliminarProducto($productoid);

		if($resultado == 1){
			header("location: ../view/productoview.php?mensaje=3" );
		}else{
			header("location: ../view/productoview.php?mensaje=4" );
		}
	}






?>
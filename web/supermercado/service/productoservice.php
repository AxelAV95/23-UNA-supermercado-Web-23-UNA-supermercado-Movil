<?php 
	
	include '../business/productobusiness.php';

	if($_SERVER['REQUEST_METHOD'] == "GET"){
		$productobusiness = new ProductoBusiness();
  		$productos = $productobusiness->getAllTBProductos2();
  		echo json_encode($productos);
	}else if($_SERVER['REQUEST_METHOD'] == "POST"){
		//se utiliza en PHP para obtener el contenido de la solicitud HTTP entrante
		$json = file_get_contents('php://input');
		$data = json_decode($json);

		if($data->metodo == "insertar"){
			$producto = new Producto();
			$producto->setNombre($data->nombre);
			$producto->setPrecioProducto($data->precio);
			$producto->setEstadoProducto($data->estado);
            $producto->setProductoFechaIngresoProducto($data->fechaIngreso);
			$producto->setStockProducto($data->stock);
			$producto->setCategoriaProducto($data->categoria);
			$producto->setProductoproveedor($data->productoproveedor);
			

			$productobusiness = new ProductoBusiness();

			$resultado = $productobusiness->insertarProducto($producto);

			if($resultado == 1){
		    		echo json_encode(array("statusCode"=>200));	
		    }else{
		    		echo json_encode(array("statusCode"=>400));	
		    }
		}
	}else if($_SERVER['REQUEST_METHOD'] == "PUT"){
		$json = file_get_contents('php://input');
		$data = json_decode($json);

        $producto = new Producto();
		$producto ->setIdProducto($data->productoid);		
		
			$producto->setNombre($data->nombre);
			$producto->setPrecioProducto($data->precio);
			$producto->setEstadoProducto($data->estado);
            $producto->setProductoFechaIngresoProducto($data->fechaIngreso);
			$producto->setStockProducto($data->stock);
			$producto->setCategoriaProducto($data->categoria);
			$producto->setProductoproveedor($data->productoproveedor);
        $productobusiness = new ProductoBusiness();

		$resultado = $productobusiness->modificarProducto($producto);

		if($resultado == 1){
	    		echo json_encode(array("statusCode"=>200));	
	    }else{
	    		echo json_encode(array("statusCode"=>400));	
	    }
	}else if($_SERVER['REQUEST_METHOD'] === "DELETE"){
		
		$id = $_GET['id'];

        $productobusiness = new ProductoBusiness();
		
		$resultado = $productobusiness->eliminarProducto($id);

		if($resultado == 1){
	    		echo json_encode(array("statusCode"=>200));	
	    }else{
	    		echo json_encode(array("statusCode"=>400));	
	    }
	}


 ?>
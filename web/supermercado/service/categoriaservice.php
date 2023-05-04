<?php 
	
	include '../business/categoriabusiness.php';

	if($_SERVER['REQUEST_METHOD'] == "GET"){
		$categoriabusiness = new CategoriaBusiness();
  		$categorias = $categoriabusiness->getAllTBCategorias();
  		echo json_encode($categorias);
	}else if($_SERVER['REQUEST_METHOD'] == "POST"){
		//se utiliza en PHP para obtener el contenido de la solicitud HTTP entrante
		$json = file_get_contents('php://input');
		$data = json_decode($json);

		if($data->metodo == "insertar"){
			$categoria = new Categoria();
			$categoria->setNombre($data->nombre);

			$categoriabusiness = new CategoriaBusiness();

			$resultado = $categoriabusiness->insertarCategoria($categoria);

			if($resultado == 1){
		    		echo json_encode(array("statusCode"=>200));	
		    }else{
		    		echo json_encode(array("statusCode"=>400));	
		    }
		}
	}else if($_SERVER['REQUEST_METHOD'] == "PUT"){
		$json = file_get_contents('php://input');
		$data = json_decode($json);

        $categoria = new Categoria();
		$categoria->setId($data->id);
		$categoria->setNombre($data->nombre);
        $categoriabusiness = new CategoriaBusiness();

		$resultado = $categoriabusiness->modificarCategoria($categoria);

		if($resultado == 1){
	    		echo json_encode(array("statusCode"=>200));	
	    }else{
	    		echo json_encode(array("statusCode"=>400));	
	    }
	}else if($_SERVER['REQUEST_METHOD'] === "DELETE"){
		
		$id = $_GET['id'];

        $categoriabusiness = new CategoriaBusiness();
		
		$resultado = $categoriabusiness->eliminarCategoria($id);

		if($resultado == 1){
	    		echo json_encode(array("statusCode"=>200));	
	    }else{
	    		echo json_encode(array("statusCode"=>400));	
	    }
	}


 ?>
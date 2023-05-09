<?php 


	include 'categoriabusiness.php';
	//post de insercion de categoria
	if(isset($_POST['Insertar'])){
		if (isset($_POST['categorianombre'])) {
		$nombre = $_POST['categorianombre'];
	 
	 $categoriaBusiness = new CategoriaBusiness();
	
	
	
		$categoria = new Categoria();
		$categoria->setNombre($nombre);
		$resultado = $categoriaBusiness->insertarCategoria($categoria);
	
		if ($resultado == 1) {
			Header("Location: ../view/categoriaview.php?success=inserted");
		} else {
			Header("Location: ../view/categoriaview.php?error=dbError");
		}
		}
	
	} 
	
	//metodo de actualizar categoria
	if(isset($_POST['actualizar'])){
		if (isset($_POST['categorianombre']) && isset($_POST['categoriaid']) 
		) 
		$id = $_POST['categoriaid'];
		$nombre = $_POST['categorianombre'];
		$categoria = new Categoria();
		$categoria->setId($id);
		$categoria->setNombre($nombre);
		$categoriaBusiness = new CategoriaBusiness();

		$resultado = $categoriaBusiness->modificarCategoria($categoria);
		if ($resultado == 1) {
			Header("Location: ../view/categoriaview.php?success=update");
		} else {
			Header("Location: ../view/categoriaview.php?error=dbError");
		}
	}
	else if(isset($_GET['eliminar'])){
		$id = $_GET['categoriaid'];
		$categoriaBusiness = new CategoriaBusiness();
		$resultado = $categoriaBusiness->eliminarCategoria($id);

		if ($resultado == 1) {
			Header("Location: ../view/categoriaview.php?success=deleted");
		} else {
			Header("Location: ../view/categoriaview.php?error=dbError");
		}


	}

?>
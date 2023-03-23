<?php 


	include 'tipousuariobusiness.php';
	

	if(isset($_POST['insertar']) && isset($_POST['tipodescripcion'])){
		$descripcion = $_POST['tipodescripcion'];
		$tipo = new TipoUsuario($descripcion );
		//$tipo->setDescripcion($descripcion);
		$tipoBusiness = new TipoUsuarioBusiness();
		

		$resultado = $tipoBusiness->insertarTipo($tipo);

		if($resultado == 1){
	    		header("location: ../view/backend/tipousuarioview.php?mensaje=1" );
	    }else{
	    		header("location: ../view/backend/tipousuarioview.php?mensaje=4" );
	    }

	}else if(isset($_POST['actualizar'])){
		$id = $_POST['tipoid'];
		$descripcion = $_POST['tipodescripcion'];

		$tipo = new TipoUsuario($descripcion );
		$tipo->setId($id);

		$tipoBusiness = new TipoUsuarioBusiness();

		$resultado = $tipoBusiness->modificarTipo($tipo);

		if($resultado == 1){
	    		header("location: ../view/backend/tipousuarioview.php?mensaje=2" );
	    }else{
	    		header("location: ../view/backend/tipousuarioview.php?mensaje=4" );
	    }
	}else if(isset($_GET['eliminar'])){
		$id = $_GET['id'];

		$tipoBusiness = new TipoUsuarioBusiness();
		

		$resultado = $tipoBusiness->eliminarTipo($id);

		if($resultado == 1){
	    		header("location: ../view/backend/tipousuarioview.php?mensaje=3" );
	    }else{
	    		header("location: ../view/backend/tipousuarioview.php?mensaje=4" );
	    }

	}

?>
<?php 
	
	include 'proveedorbusiness.php';


	if(isset($_POST['insertar'])){
		if(isset($_POST['proveedornombre'])&& isset($_POST['proveedordireccion']) && isset($_POST['proveedorcorreo'])
        && isset($_POST['proveedortelefono'])
        ){
			$proveedorBusiness = new ProveedorBusiness();
			$nombre = $_POST['proveedornombre'];
			$direccion = $_POST['proveedordireccion'];
            $correo = $_POST['proveedorcorreo'];
            $telefono = $_POST['proveedortelefono'];
           
			

			
	    	$proveedor = new Proveedor();
			$proveedor->setNombre($nombre);	 
			$proveedor->setDireccion($direccion);			   		    	
	    	$proveedor->setCorreo($correo);
			$proveedor->setTelefono($telefono);
			
			
			
	    	$resultado = $proveedorBusiness->insertarproveedor($proveedor);

	    	if($resultado == 1){
	    		header("location: ../view/proveedorview.php?mensaje=1" );
	    	}else{
	    		header("location: ../view/proveedorview.php?mensaje=4" );
	    	}

		 }
			 
		
	}else if(isset($_POST['actualizar'])){
		if(isset($_POST['proveedornombre']) && isset($_POST['proveedordireccion']) && isset($_POST['proveedorcorreo'])
        && isset($_POST['proveedortelefono'])){
			$id = $_POST['proveedorid'];
			$nombre = $_POST['proveedornombre'];
			$direccion = $_POST['proveedordireccion'];
            $correo = $_POST['proveedorcorreo'];
            $telefono = $_POST['proveedortelefono'];
           
			

				$proveedorBusiness = new ProveedorBusiness();
				$proveedor = new Proveedor(); 
				$proveedor->setId($id);
				$proveedor->setNombre($nombre);
	    		$proveedor->setDireccion($direccion);
                $proveedor->setCorreo($correo);
                $proveedor->setTelefono($telefono);
              
				
	    		$resultado = $proveedorBusiness->modificarproveedor($proveedor);

	    		if($resultado == 1){
	    			header("location: ../view/proveedorview.php?mensaje=2" );
	    		}else{
	    			header("location: ../view/proveedorview.php?mensaje=4" );
	    		}

			}
		


	} else if(isset($_GET['eliminar'])){
		$id = $_GET['proveedorid'];
		$proveedorBusiness = new ProveedorBusiness();
		$resultado = $proveedorBusiness->eliminarproveedor($id);

		if($resultado == 1){
			header("location: ../view/proveedorview.php?mensaje=3" );
		}else{
			header("location: ../view/proveedorview.php?mensaje=4" );
		}


	}
	

 ?>
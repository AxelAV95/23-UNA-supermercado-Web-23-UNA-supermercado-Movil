<?php 
	
	include 'proveedorbusiness.php';


	if(isset($_POST['insertar'])){
		if(isset($_POST['proveedornombre'])&& isset($_POST['proveedordireccion']) && isset($_POST['proveedorcorreo'])
        && isset($_POST['proveedortelefono'])  && isset($_POST['proveedorlat'])  && isset($_POST['proveedorlong'])
        ){
			$proveedorBusiness = new ProveedorBusiness();
			$nombre = $_POST['proveedornombre'];
			$direccion = $_POST['proveedordireccion'];
            $correo = $_POST['proveedorcorreo'];
            $telefono = $_POST['proveedortelefono'];
            $latitud = $_POST['proveedorlat'];
            $longitud = $_POST['proveedorlong'];
			

			
	    	$proveedor = new Proveedor();
			$proveedor->setNombre($nombre);	 
			$proveedor->setDireccion($direccion);			   		    	
	    	$proveedor->setCorreo($correo);
			$proveedor->setTelefono($telefono);
			$proveedor->setLatitud($latitud);
            $proveedor->setLongitud($longitud);
			
			
	    	$resultado = $proveedorBusiness->insertarproveedor($proveedor);

	    	if($resultado == 1){
	    		header("location: ../view/proveedorview.php?mensaje=1" );
	    	}else{
	    		header("location: ../view/proveedorview.php?mensaje=4" );
	    	}

		 }
			 
		
	}else if(isset($_POST['actualizar'])){
		if(isset($_POST['proveedornombre']) && isset($_POST['proveedordireccion']) && isset($_POST['proveedorcorreo'])
        && isset($_POST['proveedortelefono']) && isset($_POST['proveedorlat']) && isset($_POST['proveedorlong'])){
			$id = $_POST['proveedorid'];
			$nombre = $_POST['proveedornombre'];
			$direccion = $_POST['proveedordireccion'];
            $correo = $_POST['proveedorcorreo'];
            $telefono = $_POST['proveedortelefono'];
            $latitud = $_POST['proveedorlat'];
            $longitud = $_POST['proveedorlong'];
			

				$proveedorBusiness = new ProveedorBusiness();
				$proveedor = new Proveedor(); 
				$proveedor->setId($id);
				$proveedor->setNombre($nombre);
	    		$proveedor->setDireccion($direccion);
                $proveedor->setCorreo($correo);
                $proveedor->setTelefono($telefono);
				$proveedor->setLatitud($latitud);
                $proveedor->setLongitud($longitud);
				
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
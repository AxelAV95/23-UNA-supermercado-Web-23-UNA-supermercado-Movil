<?php 
	
	include 'proveedorbusiness.php';


	if(isset($_POST['insertar'])){
		if(isset($_POST['proveedornombre'])&& isset($_POST['proveedordireccion']) && isset($_POST['proveedorcorreo'])
        && isset($_POST['proveedortelefono'])  /*&& isset($_POST['proveedorlat'])  && isset($_POST['proveedorlong'])*/
        ){
			$proveedorBusiness = new ProveedorBusiness();
			$nombre = $_POST['proveedornombre'];
			$direccion = $_POST['proveedordireccion'];
         		$correo = $_POST['proveedorcorreo'];
                        $telefono = $_POST['proveedortelefono'];
			$provincia = $_POST['proveedorprovincia'];
         //   $latitud = $_POST['proveedorlat'];
         //   $longitud = $_POST['proveedorlong'];
		 
		 switch($provincia){
			case "1": 
				$latitud = 9.9333300;
				$longitud = -84.0833300;
				break;
			case "2":
				$latitud = 10.0162500;
				$longitud = -84.2116300;
				break;
				case "3":
					$latitud = 9.8644400;
					$longitud = -83.9194400;
					break;
					case "4":
						$latitud = 10.0023600;
						$longitud = -84.1165100;
						break;
						case "5":
							$latitud = 10.37884405;
							$longitud = -85.4338287457323;
							break;
							case "6":
								$latitud = 9.9762500;
								$longitud = -84.8383600;
								break;
								case "7":
									$latitud = 9.9907400;
									$longitud = -83.0359600;
									break;
		 }
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
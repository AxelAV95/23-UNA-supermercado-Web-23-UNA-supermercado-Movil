<?php 
	session_start();
	include 'ordenbusiness.php';
	

	if(isset($_POST['ordenar'])){
		$total = 0;
		$nombre = $_POST['clientenombre'];
		$telefono = $_POST['clientetelefono'];
		$correo = $_POST['clientecorreo'];
		
		$metodo =$_POST['clientemetodo'];

		foreach($_SESSION['carrito'] as $indice=>$producto){
			$total+=($producto['productoprecio']*$producto['productocantidad']);
		}
		$orden = new Orden();
		$orden->setClienteOrden($nombre);
		$orden->setTelefonoOrden($telefono);
		$orden->setCorreoOrden($correo);
		$orden->setMetodoOrden($metodo);
		$orden->setFechaOrden(date("Y-m-d"));
		$orden->setTotalOrden($total);
		$orden->setEstadoOrden(2);

		$ordenBusiness = new OrdenBusiness();
		$ordeninsertada = $ordenBusiness->insertarTBOrden($orden);
		$ordenultimoid = $ordenBusiness->getUltimoIdOrden();
		$detalle = null;
		foreach($_SESSION['carrito'] as $indice=>$producto){
			$detalle = new Detalle();
			$detalle->setOrdenId($ordenultimoid);
			$detalle->setProductoId($producto['productoid']);
			$detalle->setPrecio($producto['productoprecio']);
			$detalle->setCantidad($producto['productocantidad']);


			$ordenBusiness->insertarTBDetalle($detalle);
		}


		if($ordeninsertada == 1){
			unset($_SESSION['carrito']);
			header("location: ../view/frontend/index.php?pagina=1&mensaje=2&ordenid=".$ordenultimoid );
		}else{
			header("location: ../view/frontend/index.php?pagina=1&mensaje=3" );
		}
		// echo $total;
	}else if(isset($_POST['id'])){

		$ordenid = $_POST['id'];
		$ordenBusiness = new OrdenBusiness();
		$resultado = $ordenBusiness->getAllTBDetalles($ordenid);

		$datos = "";
		for ($i=0; $i < count($resultado) ; $i++) { 
			$datos .= "<tr>";
			$datos .= "<td>".$resultado[$i]['detalleid']."</td>";
			$datos .= "<td>".$resultado[$i]['detalleordenid']."</td>";
			$datos .= "<td>".$resultado[$i]['productonombre']."</td>";
			$datos .= "<td>".$resultado[$i]['detalleprecio']."</td>";
			$datos .= "<td>".$resultado[$i]['detallecantidad']."</td>";
			$datos .= "</tr>";
			
		}
		echo $datos;
		
		

	}else if(isset($_POST['actualizar'])){
		$ordenid = $_POST['ordenid'];
		$ordenestado = $_POST['ordenestado'];
		$usuarioid = $_SESSION['id'];
		$ordenBusiness = new OrdenBusiness();
		$resultado = $ordenBusiness->modificarOrden($ordenid,$ordenestado,$usuarioid );


		if($resultado == 1){
			header("location: ../view/backend/ordenview.php?mensaje=2" );
		}else{
			header("location: ../view/backend/ordenview.php?mensaje=4" );
		}


	}else if(isset($_GET['eliminar'])){
		$ordenid = $_GET['id'];
		$ordenBusiness = new OrdenBusiness();
		$resultado = $ordenBusiness->eliminarOrden($ordenid);
		if($resultado == 1){
			header("location: ../view/backend/ordenview.php?mensaje=3" );
		}else{
			header("location: ../view/backend/ordenview.php?mensaje=4" );
		}
	}else if(isset($_POST['vista'])){
		$ordenBusiness = new OrdenBusiness();
		$notificaciones = "";
		if($_POST["vista"] != ''){
			$ordenBusiness->modificarNotificacion();
		}else{
			$totalOrdenesNuevas = $ordenBusiness->obtenerNotificaciones();
			if($totalOrdenesNuevas >=1){
				$notificaciones.= '<div class="dropdown-divider"></div>
				<span  class="dropdown-item">
				<i class="fas fa-file mr-2 "></i> '.$totalOrdenesNuevas.' órdenes nuevas

				</span>';
			}else{
				$notificaciones.= '<div class="dropdown-divider"></div>
				<span  class="dropdown-item">
				<i class="fas fa-file mr-2 "></i> Sin notificaciones nuevas

				</span>';
			}

			$data = array(
				'notification'   => $notificaciones,
				'unseen_notification' => $totalOrdenesNuevas
			);
			echo json_encode($data);
		}
	}



 ?>
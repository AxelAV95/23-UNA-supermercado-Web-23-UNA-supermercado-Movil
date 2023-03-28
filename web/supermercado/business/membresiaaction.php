<?php 


	include 'membresiabusiness.php';
	if(isset($_POST['insertar'])){

		if (isset($_POST['membresiadescripcion'])
	) {
		$membresiadescripcion = $_POST['membresiadescripcion'];
	 
		$membresiaBusiness = new MembresiaBusiness();
	
		$membresia = new Membresia(0,$membresiadescripcion);
	
		$resultado = $membresiaBusiness->insertarMembresia($membresia);
	
		if ($resultado == 1) {
			Header("Location: ../view/membresiaview.php?success=inserted");
		} else {
			Header("Location: ../view/membresiaview.php?error=dbError");
		}
		}


	}

//metodo de actualizar categoria
else if(isset($_POST['actualizar'])){
	if (isset($_POST['membresiadescripcion']) && isset($_POST['membresiaid']) 
	) 
	$membresiaid = $_POST['membresiaid'];
	$membresiadescripcion = $_POST['membresiadescripcion'];
	$membresia = new Membresia($membresiaid,$membresiadescripcion);
	
	$membresiaBusiness = new MembresiaBusiness();

	$resultado = $membresiaBusiness->modificarMembresia($membresia);
	if ($resultado == 1) {
		Header("Location: ../view/membresiaview.php?success=update&id=");
	} else {
		Header("Location: ../view/membresiaview.php?error=dbError");
	}
}

else if(isset($_GET['eliminar'])){
	if(isset($_GET['membresiaid'])) {
	  $id = $_GET['membresiaid'];
  
	
	  $membresiaBusiness = new MembresiaBusiness();
  
	  $resultado = $membresiaBusiness->eliminarMembresia($id);
	
	  if($resultado == 1){
		echo "<script>window.location.reload();</script>";
	} else {
		header("location: ../view/membresiaview.php?mensaje=4" );
	}
	
		  
  }

}
  
else if(isset($_GET['metodo']) && $_GET['metodo'] == "obtener" ){
    $membresiaBusiness = new MembresiaBusiness();
      $membresias = $membresiaBusiness->getAllTBMembresias();

      $data = array();
      foreach ($membresias as $row) {
      $data[] = array(
         "membresiaid"=>$row['membresiaid'],
         "membresiadescripcion"=>$row['membresiadescripcion']
      );
       }

       $response = array(
           "iTotalRecords" => count($data),
      "aaData" => $data
       );

      echo json_encode($response);
  


}



?>
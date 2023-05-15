
<?php
include 'membresiabusiness.php';

	if(isset($_POST['metodo']) && $_POST['metodo'] == "agregar" && isset($_POST['membresiadescripcion'])){
		$membresiadescripcion = $_POST['membresiadescripcion'];
		$membresiaBusiness = new MembresiaBusiness();
		$membresia = new Membresia();
        $membresia->setMembresiadescripcion($membresiadescripcion);
				
		$resultado = $membresiaBusiness->insertarMembresia($membresia);

		if($resultado == 1){
	    		echo json_encode(array("statusCode"=>200));	
	    }else{
	    		echo json_encode(array("statusCode"=>400));	
	    }


}else if(isset($_POST['metodo']) && $_POST['metodo'] == "actualizar" && isset($_POST['membresiadescripcion']) && isset($_POST['membresiaid']) ){
    $membresiaid = $_POST['membresiaid'];
    $membresiadescripcion = $_POST['membresiadescripcion'];
    $membresia = new Membresia();
    $membresia->setMembresiaid($membresiaid);
    $membresia->setMembresiadescripcion($membresiadescripcion);
            
	
	$membresiaBusiness = new MembresiaBusiness();

	$resultado = $membresiaBusiness->modificarMembresia($membresia);

    if($resultado == 1){
        header("Location: ../view/membresiaview.php?sucess=update");
    }else{
        header("Location: ../view/membresiaview.php?error=dbError");
    }




}else if(isset($_GET['metodo']) && $_GET['metodo'] == "eliminar"){
	$membresiaid = $_GET['membresiaid'];

	
	$membresiaBusiness = new MembresiaBusiness();
  
	$resultado = $membresiaBusiness->eliminarMembresia($membresiaid);
  

    if($resultado == 1){
		echo json_encode(array("statusCode"=>200));	
}else{
		echo json_encode(array("statusCode"=>400));	
}





}else if(isset($_GET['metodo']) && $_GET['metodo'] == "obtener" ){
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

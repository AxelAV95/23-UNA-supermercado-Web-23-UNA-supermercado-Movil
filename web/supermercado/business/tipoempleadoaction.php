<?php
include 'tipoempleadobusiness.php';

	if(isset($_POST['metodo']) && $_POST['metodo'] == "agregar" && isset($_POST['tipodescripcion'])){
		$descripcion = $_POST['tipodescripcion'];
		$tipo = new TipoEmpleado(0, $descripcion);
		
		$tipoBusiness = new TipoEmpleadoBusiness();
		

		$resultado = $tipoBusiness->insertarTipo($tipo);

		if($resultado == 1){
	    		echo json_encode(array("statusCode"=>200));	
	    }else{
	    		echo json_encode(array("statusCode"=>400));	
	    }


}else if(isset($_POST['metodo']) && $_POST['metodo'] == "actualizar" && isset($_POST['tipoid']) && isset($_POST['tipodescripcion']) ){
    $id = $_POST['tipoid'];
    $descripcion = $_POST['tipodescripcion'];

    $tipo = new TipoEmpleado($id, $descripcion);
    $tipoBusiness = new TipoEmpleadoBusiness();

    $resultado = $tipoBusiness->actualizar($tipo);

    if($resultado == 1){
        echo json_encode(array("statusCode"=>200)); 
    }else{
        echo json_encode(array("statusCode"=>400)); 
    }




}else if(isset($_GET['metodo']) && $_GET['metodo'] == "eliminar"){
	$id = $_GET['id'];

	$tipoBusiness = new TipoEmpleadoBusiness();	

	$resultado = $tipoBusiness->eliminarTipo($id);

    if($resultado == 1){
		echo json_encode(array("statusCode"=>200));	
}else{
		echo json_encode(array("statusCode"=>400));	
}





}else if(isset($_GET['metodo']) && $_GET['metodo'] == "obtener" ){
    $tipoempleadoBusiness = new TipoEmpleadoBusiness();
      $tipos = $tipoempleadoBusiness->obtener();

      $data = array();
      foreach ($tipos as $row) {
      $data[] = array(
         "tipoid"=>$row['tipoid'],
         "tipodescripcion"=>$row['tipodescripcion']
      );
       }

       $response = array(
           "iTotalRecords" => count($data),
      "aaData" => $data
       );

      echo json_encode($response);
  }





?>
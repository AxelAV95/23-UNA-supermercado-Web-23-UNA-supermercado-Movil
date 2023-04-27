
<?php
include 'descuentobusiness.php';

	if(isset($_POST['accion']) && $_POST['accion'] == "agregar" && isset($_POST['descuentotarifa']) && isset($_POST['descuentomembresiaid'])){
		$descuentotarifa = $_POST['descuentotarifa'];
        $descuentomembresiaid = $_POST['descuentomembresiaid'];
		$descuentoBusiness = new DescuentoBusiness();
		$descuento = new Descuento(0,$descuentotarifa,$descuentomembresiaid);
	
		$resultado = $descuentoBusiness->insertarDescuento($descuento);
        if($resultado == 1){
            header("Location: ../view/descuentoview.php?sucess=update");
        }else{
            header("Location: ../view/descuentoview.php?error=dbError");
        }
    

}else if(isset($_POST['accion']) && $_POST['accion'] == "actualizar" && isset($_POST['descuentotarifa']) && isset($_POST['descuentomembresiaid']) && isset($_POST['descuentoid']) ){
    $descuentoid = $_POST['descuentoid'];
    $descuentotarifa = $_POST['descuentotarifa'];
    $descuentomembresiaid = $_POST['descuentomembresiaid'];

	$descuento = new Descuento($descuentoid,$descuentotarifa,$descuentomembresiaid);
	
	$descuentoBusiness = new DescuentoBusiness();

	$resultado = $descuentoBusiness->modificarDescuento($descuento);

    if($resultado == 1){
        header("Location: ../view/descuentoview.php?sucess=update");
    }else{
        header("Location: ../view/descuentoview.php?error=dbError");
    }




}else if(isset($_GET['metodo']) && $_GET['metodo'] == "eliminar"){
	$descuentoid = $_GET['descuentoid'];

	
	$descuentoBusiness = new DescuentoBusiness();
    
	$resultado = $descuentoBusiness->eliminarDescuento($descuentoid);
  

    if($resultado == 1){
		echo json_encode(array("statusCode"=>200));	
}else{
		echo json_encode(array("statusCode"=>400));	
}





}else if(isset($_GET['metodo']) && $_GET['metodo'] == "obtener" ){
    $descuentoBusiness = new DescuentoBusiness();
      $descuentos = $descuentoBusiness->getAllTBDescuentos();

      $data = array();
      foreach ($descuentos as $row) {
      $data[] = array(
         "descuentoid"=>$row['descuentoid'],
         "descuentotarifa"=>$row['descuentotarifa'],
         "descuentomembresiaid"=>$row['descuentomembresiaid']
      );
       }

       $response = array(
           "iTotalRecords" => count($data),
      "aaData" => $data
       );

      echo json_encode($response);
  


}


?>

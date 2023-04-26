<?php
include 'clientebusiness.php';

	if(isset($_POST['accion']) && $_POST['accion'] == "agregar" && isset($_POST['clientenombre'])&& isset($_POST['clienteapellidos'])&& isset($_POST['clientecedula'])
    && isset($_POST['clientedireccion'])&& isset($_POST['clientetelefono'])&& isset($_POST['clientecorreo'])&& isset($_POST['clientefechaafiliacion'])
    && isset($_POST['clientetipomembresia'])){


        $clientenombre = $_POST['clientenombre'];
        $clienteapellidos = $_POST['clienteapellidos'];
        $clientecedula = $_POST['clientecedula'];
        $clientedireccion = $_POST['clientedireccion'];
        $clientetelefono = $_POST['clientetelefono'];
        $clientecorreo = $_POST['clientecorreo'];
        $clientefechaafiliacion = $_POST['clientefechaafiliacion'];
        $clientetipomembresia = $_POST['clientetipomembresia'];
		$clienteBusiness = new clienteBusiness();
		$cliente = new Cliente(0,$clientenombre,$clienteapellidos,$clientecedula,$clientedireccion,$clientetelefono,
        $clientecorreo,$clientefechaafiliacion,$clientetipomembresia);
    
		$resultado = $clienteBusiness->insertarCliente($cliente);

	   if($resultado == 1){
        header("Location: ../view/clienteview.php?sucess=update");
    }else{
        header("Location: ../view/clienteview.php?error=dbError");
    }


}else if(isset($_POST['accion']) && $_POST['accion'] == "actualizar" && isset($_POST['clientenombre'])&& isset($_POST['clienteapellidos'])&& isset($_POST['clientecedula'])
&& isset($_POST['clientedireccion'])&& isset($_POST['clientetelefono'])&& isset($_POST['clientecorreo'])&& isset($_POST['clientefechaafiliacion'])&& isset($_POST['clientetipomembresia'])
&& isset($_POST['clienteid'])){
        $clienteid = $_POST['clienteid'];
        $clientenombre = $_POST['clientenombre'];
        $clienteapellidos = $_POST['clienteapellidos'];
        $clientecedula = $_POST['clientecedula'];
        $clientedireccion = $_POST['clientedireccion'];
        $clientetelefono = $_POST['clientetelefono'];
        $clientecorreo = $_POST['clientecorreo'];
        $clientefechaafiliacion = $_POST['clientefechaafiliacion'];
        $clientetipomembresia = $_POST['clientetipomembresia'];

        $clienteBusiness = new clienteBusiness();
		$cliente = new Cliente($clienteid,$clientenombre,$clienteapellidos,$clientecedula,$clientedireccion,$clientetelefono,
        $clientecorreo,$clientefechaafiliacion,$clientetipomembresia);
	$resultado = $clienteBusiness->modificarCliente($cliente);

    if($resultado == 1){
        header("Location: ../view/clienteview.php?sucess=update");
    }else{
        header("Location: ../view/clienteview.php?error=dbError");
    }




}else if(isset($_GET['metodo']) && $_GET['metodo'] == "eliminar"){
	$clienteid = $_GET['clienteid'];

    $clienteBusiness = new clienteBusiness();
  
	$resultado = $clienteBusiness->eliminarCliente($clienteid);
  

    if($resultado == 1){
		echo json_encode(array("statusCode"=>200));	
}else{
		echo json_encode(array("statusCode"=>400));	
}





}else if(isset($_GET['metodo']) && $_GET['metodo'] == "obtener" ){
    $clienteBusiness = new clienteBusiness();
      $clientes = $clienteBusiness->getAllTBClientes();

      $data = array();
      foreach ($clientes as $row) {
      $data[] = array(
         "clienteid"=>$row['clienteid'],
         "clientenombre"=>$row['clientenombre'],
         "clienteapellidos"=>$row['clienteapellidos'],
         "membresiadescripcion"=>$row['membresiadescripcion'],
         "clientecedula"=>$row['clientecedula'],
         "clientedireccion"=>$row['clientedireccion'],
         "clientetelefono"=>$row['clientetelefono'],
         "clientecorreo"=>$row['clientecorreo'],
         "clientefechaafiliacion"=>$row['clientefechaafiliacion'],
         "clientetipomembresia"=>$row['clientetipomembresia'],


      );
       }

       $response = array(
           "iTotalRecords" => count($data),
      "aaData" => $data
       );

      echo json_encode($response);
  


}


?>

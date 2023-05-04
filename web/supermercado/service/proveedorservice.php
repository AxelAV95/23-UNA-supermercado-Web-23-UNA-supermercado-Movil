<?php 

	include '../business/proveedorbusiness.php';

	if($_SERVER['REQUEST_METHOD'] == "GET"){
		$proveedorbusiness = new ProveedorBusiness();
  		$proveedores = $proveedorbusiness->getAllTBProveedores();
  		echo json_encode($proveedores);
	}

?>
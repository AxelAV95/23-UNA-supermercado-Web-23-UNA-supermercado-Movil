<?php 	 
	
	include_once 'data.php';
    include ("../domain/proveedor.php");
    
	
class ProveedorData extends Database{

	public function __construct(){}

	public function getUltimoIdInsertado(){
        	$pdo = Database::conectar();
	        $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
	        $stmt = $pdo ->prepare("SELECT MAX(provedorid) AS proveedorid  FROM tbproveedor");
	        $stmt -> execute();
	        $nextId = 1;
	                
	        if($row = $stmt->fetch()){
	           $nextId = $row[0]+1;
	        }

	        return $nextId;
        }


		//insertar proveedor
		public function insertarProveedor($proveedor){
			$pdo = Database::conectar();
            $stm = $pdo->prepare("CALL insertarProveedor(?,?,?,?,?,?,?)");

            $max = $pdo ->prepare("SELECT MAX(proveedorid) AS proveedorid  FROM tbproveedor");
	        $max -> execute();
	        $nextId = 1;
	                
	        if($row = $max->fetch()){
	           $nextId = $row[0]+1;
	        }
	        $nombre = $proveedor->getNombre();
	        $direccion = $proveedor->getDireccion();
	        $correo = $proveedor->getCorreo();
			$telefono = $proveedor->getTelefono();
			$latitud = $proveedor->getLatitud();
			$longitud = $proveedor->getLongitud();

            $stm ->bindParam(1,$nextId,PDO::PARAM_INT);
            $stm ->bindParam(2,$nombre,PDO::PARAM_STR);
			$stm ->bindParam(3,$direccion,PDO::PARAM_STR);
            $stm ->bindParam(4,$correo,PDO::PARAM_STR);
            $stm ->bindParam(5,$telefono,PDO::PARAM_INT);
			$stm ->bindParam(6,$latitud,PDO::PARAM_STR);
            $stm ->bindParam(7,$longitud,PDO::PARAM_STR);
            $resultado = $stm->execute();
            Database::desconectar();
	           
	        return $resultado;
		}
		//actualizar proveedor
		public function modificarProveedor($proveedor){
			$pdo = Database::conectar();
            $stm = $pdo->prepare("CALL modificarProveedor(?,?,?,?,?,?,?)");
            $id = $proveedor->getId();
            $nombre = $proveedor->getNombre();
	        $direccion = $proveedor->getDireccion();
			$correo = $proveedor->getCorreo();
            $telefono = $proveedor->getTelefono();
			$latitud = $proveedor->getLatitud();
			$longitud = $proveedor->getLongitud();
            $stm ->bindParam(1,$id,PDO::PARAM_INT);
            $stm ->bindParam(2,$nombre,PDO::PARAM_STR);
            $stm ->bindParam(3,$direccion,PDO::PARAM_STR);
			$stm ->bindParam(4,$correo,PDO::PARAM_STR);
            $stm ->bindParam(5,$telefono,PDO::PARAM_INT);
			$stm ->bindParam(6,$latitud,PDO::PARAM_STR);
            $stm ->bindParam(7,$longitud,PDO::PARAM_STR);
            $resultado = $stm->execute();
            Database::desconectar();
	           
	        return $resultado;
		}
		//eliminar proveedor
		public function eliminarproveedor($proveedorid){
			$pdo = Database::conectar();
            $stm = $pdo->prepare("CALL eliminarProveedor(?)");
            $stm ->bindParam(1,$proveedorid,PDO::PARAM_INT);
            $resultado = $stm->execute();
            Database::desconectar();
	           
	        return $resultado;

		}
		//obtener proveedores
		public function getAllTBProveedores() {
            $pdo = Database::conectar();
            $stm = $pdo->prepare("CALL obtenerProveedores()");
            $stm->execute();
            Database::desconectar();
            return $stm->fetchAll(PDO::FETCH_ASSOC);
        }

		public function getNombreProveedor($id){
        	$pdo = Database::conectar();
            $stm = $pdo->prepare("CALL obtenerProveedorNombre(?)");
            $stm->bindParam(1,$id, PDO::PARAM_INT);
            $stm->execute();
            Database::desconectar();
            return $stm->fetchAll(PDO::FETCH_ASSOC);
        }

		public function obtenerproveerdorid($id){
        	$pdo = Database::conectar();
            $stm = $pdo->prepare("CALL obtenerproveerdorid(?)");
            $stm->bindParam(1,$id, PDO::PARAM_INT);
            $stm->execute();
            Database::desconectar();
            return $stm->fetchAll(PDO::FETCH_ASSOC);
        }

         public function obtenerTotalProveedores(){
            $pdo = Database::conectar();
            $stm = $pdo->prepare("CALL obtenerTotalProveedores");
            $stm->execute();
            Database::desconectar();
            return $stm->fetchAll(PDO::FETCH_ASSOC)[0]['total'];
        }

        public function obtenerTotalProductosProveedorChart() {
            $pdo = Database::conectar();
            $stm = $pdo->prepare("CALL obtenerTotalProductosProveedor()");
            $stm->execute();

            $proveedores = array();
            $total = array();

             while ($row = $stm->fetch(PDO::FETCH_ASSOC)) {
                $proveedores[] = $row['proveedornombre'];
                $total[] = $row['totalproductos'];
            }
             $data = array(
                'labels' => $proveedores,
                'datasets' => array(
                    array(
                        'label' => 'Total de productos suministrados por proveedor',
                       'backgroundColor' => array(
                            'rgb(255, 99, 132)',
                            'rgb(54, 162, 235)',
                            'rgb(255, 206, 86)',
                            'rgb(75, 192, 192)',
                            'rgb(153, 102, 255)',
                            'rgb(255, 159, 64)'
                        ),
                        'borderColor' => array(
                            'rgb(255,99,132)',
                            'rgb(54, 162, 235)',
                            'rgb(255, 206, 86)',
                            'rgb(75, 192, 192)',
                            'rgb(153, 102, 255)',
                            'rgb(255, 159, 64)'
                        ),

                        'borderWidth' => 1,
                        'data' => $total
                    )
                )
            );

            $json_data = json_encode($data);

            Database::desconectar();
            return $json_data;
        }
	
}




?>

<?php 

//	$data = new ProveedorData();
//	echo $data->obtenerTotalProductosProveedorChart();
 ?>
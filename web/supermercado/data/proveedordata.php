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
            $stm = $pdo->prepare("CALL insertarProveedor(?,?,?,?,?)");

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
            $stm ->bindParam(1,$nextId,PDO::PARAM_INT);
            $stm ->bindParam(2,$nombre,PDO::PARAM_STR);
			$stm ->bindParam(3,$direccion,PDO::PARAM_STR);
            $stm ->bindParam(4,$correo,PDO::PARAM_STR);
            $stm ->bindParam(5,$telefono,PDO::PARAM_INT);
            $resultado = $stm->execute();
            Database::desconectar();
	           
	        return $resultado;
		}
		//actualizar proveedor
		public function modificarProveedor($proveedor){
			$pdo = Database::conectar();
            $stm = $pdo->prepare("CALL modificarProveedor(?,?,?,?,?)");
            $id = $proveedor->getId();
            $nombre = $proveedor->getNombre();
	        $direccion = $proveedor->getDireccion();
			$correo = $proveedor->getCorreo();
            $telefono = $proveedor->getTelefono();
            $stm ->bindParam(1,$id,PDO::PARAM_INT);
            $stm ->bindParam(2,$nombre,PDO::PARAM_STR);
            $stm ->bindParam(3,$direccion,PDO::PARAM_STR);
			$stm ->bindParam(4,$correo,PDO::PARAM_STR);
            $stm ->bindParam(5,$telefono,PDO::PARAM_INT);
			
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
	
}




?>
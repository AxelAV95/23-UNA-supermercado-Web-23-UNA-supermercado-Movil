<?php 	 
	
	include_once 'data.php';
    if (is_file("../domain/membresia.php")){
        include ("../domain/membresia.php");
    }else{
        include ("../../domain/membresia.php");
    }
	
class membresiaData extends Database{

	public function __construct(){}

		//insertar
		public function insertarMembresia($membresia){
			$pdo = Database::conectar();
            $stm = $pdo->prepare("CALL insertarMembresia(?,?)");
            $max = $pdo ->prepare("SELECT MAX(membresiaid) AS membresiaid  FROM tbmembresia");
	        $max -> execute();
	        $nextId = 1;
	                
	        if($row = $max->fetch()){
	           $nextId = $row[0]+1;
	        }
	        $membresiadescripcion = $membresia->getMembresiadescripcion();
            $stm ->bindParam(2,$membresiadescripcion,PDO::PARAM_STR);
            $resultado = $stm->execute();
            Database::desconectar();
	           
	        return $resultado;
		}
		//actualizar
		public function modificarMembresia($membresia){
			$pdo = Database::conectar();
            $stm = $pdo->prepare("CALL modificarMembresia(?,?)");
            $membresiaid = $membresia->getMembresiaid();
            $membresiadescripcion = $membresia->getMembresiadescripcion();
            $stm ->bindParam(1,$membresiaid,PDO::PARAM_INT);
            $stm ->bindParam(2,$membresiadescripcion,PDO::PARAM_STR);
			
            $resultado = $stm->execute();
            Database::desconectar();
	           
	        return $resultado;
		}
		//eliminar
		public function eliminarMembresia($membresiaid){
			$pdo = Database::conectar();
            $stm = $pdo->prepare("CALL eliminarMembresia(?)");
            $stm ->bindParam(1,$membresiaid,PDO::PARAM_INT);
            $resultado = $stm->execute();
            Database::desconectar();
	           
	        return $resultado;

		}
		//obtener
		public function getAllTBMembresias() {
            $pdo = Database::conectar();
            $stm = $pdo->prepare("CALL obtenerMembrias()");
            $stm->execute();
            Database::desconectar();
            return $stm->fetchAll(PDO::FETCH_ASSOC);
        }
	
		//insertar
		//actualizar
		//eliminar
		//obtener

}




?>
<?php 	 
	
	include_once 'data.php';
  
        include "../domain/descuento.php";

class descuentoData extends Database{

	public function __construct(){}

		//insertar
		public function insertarDescuento($descuento){
			$pdo = Database::conectar();
            $stm = $pdo->prepare("CALL insertarDescuento(?,?,?)");
            $max = $pdo ->prepare("SELECT MAX(descuentoid) AS descuentoid  FROM tbdescuento");
	        $max -> execute();
	        $nextId = 1;
	        if($row = $max->fetch()){
	           $nextId = $row[0]+1;
	        }
	        $descuentotarifa = $descuento->getDescuentotarifa();
            $descuentomembresiaid=$descuento->getDescuentomembresiaid();
                $stm->bindParam(1, $nextId, PDO::PARAM_INT);
                $stm->bindParam(2, $descuentotarifa, PDO::PARAM_STR);
                $stm->bindParam(3, $descuentomembresiaid, PDO::PARAM_INT);
            $resultado = $stm->execute();
            Database::desconectar();
	           
	        return $resultado;
		}
		//actualizar
		public function modificarDescuento($descuento){
			$pdo = Database::conectar();
            $stm = $pdo->prepare("CALL modificarDescuento(?,?,?)");
            $descuentoid = $descuento->getDescuentoid();
            $descuentotarifa = $descuento->getDescuentotarifa();
            $descuentomembresiaid=$descuento->getDescuentomembresiaid();
			
			$stm ->bindParam(1,$descuentoid,PDO::PARAM_INT);
            $stm ->bindParam(2,$descuentotarifa,PDO::PARAM_STR);
            $stm ->bindParam(3,$descuentomembresiaid,PDO::PARAM_INT);
			
            $resultado = $stm->execute();
            Database::desconectar();
	           
	        return $resultado;
		}

		public function modificarDescuento1($descuento){
			$pdo = Database::conectar();
            $stm = $pdo->prepare("CALL modificarDes(?,?)");
            $descuentoid = $descuento->getDescuentoid();
            $descuentotarifa = $descuento->getDescuentotarifa();
         
			
			$stm ->bindParam(1,$descuentoid,PDO::PARAM_INT);
            $stm ->bindParam(2,$descuentotarifa,PDO::PARAM_STR);
        
			
            $resultado = $stm->execute();
            Database::desconectar();
	           
	        return $resultado;
		}
		//eliminar
		public function eliminarDescuento($descuentoid){
			$pdo = Database::conectar();
            $stm = $pdo->prepare("CALL eliminarDescuento(?)");
            $stm ->bindParam(1,$descuentoid,PDO::PARAM_INT);
            $resultado = $stm->execute();
            Database::desconectar();

	        return $resultado;

		}
		//obtener
		public function getAllTBDescuentos() {
            $pdo = Database::conectar();
            $stm = $pdo->prepare("CALL obtenerDescuentos()");
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
<?php
include_once 'data.php';
include "../domain/tipoempleado.php";

class TipoEmpleadoData extends Database{

    //insertar
		public function insertarTipo($tipoEmpleado){
			$pdo = Database::conectar();
            $stm = $pdo->prepare("CALL insertarTipoEmpleado(?,?)");

            $max = $pdo ->prepare("SELECT MAX(tipoid) AS tipoid FROM tbtipoempleado");
	        $max -> execute();
	        $nextId = 1;
	                
	        if($row = $max->fetch()){
	           $nextId = $row[0]+1;
	        }
	        $id = $tipoEmpleado->getTipoEmpleadoId();
	        $tipo = $tipoEmpleado->getTipoEmpleadoDescripcion();
            $stm ->bindParam(1,$nextId,PDO::PARAM_INT);
            $stm ->bindParam(2,$tipo,PDO::PARAM_STR);
            $resultado = $stm->execute();
            Database::desconectar();
	           
	        return $resultado;
		}

		//obtener
		public function obtener() {
            $pdo = Database::conectar();
            $stm = $pdo->prepare("CALL obtenerTipoEmpleado()");
            $stm->execute();
            Database::desconectar();
            return $stm->fetchAll(PDO::FETCH_ASSOC);
        }

        //actualizar
		public function actualizar($tipoempleado){
			$pdo = Database::conectar();
            $stm = $pdo->prepare("CALL actualizarTipoEmpleado(?,?)");
            $id = $tipoempleado->getTipoEmpleadoId();
            $descripcion = $tipoempleado->getTipoEmpleadoDescripcion();
	     
            $stm ->bindParam(1,$id, PDO::PARAM_INT);
            $stm ->bindParam(2,$descripcion,PDO::PARAM_STR);
            
            
            $resultado = $stm->execute();
            Database::desconectar();
	           
	        return $resultado;
		}

        //eliminar
		public function eliminarTipo($id){
			$pdo = Database::conectar();
            $stm = $pdo->prepare("CALL eliminarTipoEmpleado(?)");
            $stm ->bindParam(1,$id,PDO::PARAM_INT);
            $resultado = $stm->execute();
            Database::desconectar();
	           
	        return $resultado;
        }
		


}


?>
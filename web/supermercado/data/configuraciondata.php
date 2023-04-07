<?php 
	
	include_once 'data.php';

	class ConfiguracionData extends Database{

		public function __construct(){

		}

		public function actualizarInformacion($id,$nombre,$telefono,$correo,$direccion,$rutaLogo){
			$pdo = Database::conectar();
            $stm = $pdo->prepare("CALL actualizarInformacionSupermercado(?,?,?,?,?,?)");
          
            $stm ->bindParam(1,$nombre,PDO::PARAM_STR);
            $stm ->bindParam(2,$telefono,PDO::PARAM_INT);
            $stm ->bindParam(3,$correo,PDO::PARAM_STR);
            $stm ->bindParam(4,$direccion,PDO::PARAM_STR);
            $stm ->bindParam(5,$rutaLogo,PDO::PARAM_STR);
            $stm ->bindParam(6,$id,PDO::PARAM_INT);
						
            $resultado = $stm->execute();
            Database::desconectar();
	           
	        return $resultado;

		}

		public function obtenerInformacion(){
			$pdo = Database::conectar();
            $stm = $pdo->prepare("CALL obtenerInformacionSupermercado()");
            $stm->execute();
            Database::desconectar();
            return $stm->fetchAll(PDO::FETCH_ASSOC);
		}
	}
  



 ?>
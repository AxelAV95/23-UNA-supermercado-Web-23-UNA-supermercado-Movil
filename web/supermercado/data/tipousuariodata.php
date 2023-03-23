<?php 
	
	include_once 'data.php';
	if (is_file("../domain/tipousuario.php")){
        include ("../domain/tipousuario.php");
    }else{
        include ("../../domain/tipousuario.php");
    }

   	class TipoUsuarioData extends Database{

   		public function __construct(){}

		//insertar
		public function insertarTipo($tipousuario){
			$pdo = Database::conectar();
            $stm = $pdo->prepare("CALL insertarTipoUsuario(?,?)");

            $max = $pdo ->prepare("SELECT MAX(tipoid) AS tipoid  FROM tbtipousuario");
	        $max -> execute();
	        $nextId = 1;
	                
	        if($row = $max->fetch()){
	           $nextId = $row[0]+1;
	        }
	        $descripcion = $tipousuario->getDescripcion();
	        
            $stm ->bindParam(1,$nextId,PDO::PARAM_INT);
            $stm ->bindParam(2,$descripcion,PDO::PARAM_STR);
           
            $resultado = $stm->execute();
            Database::desconectar();
	           
	        return $resultado;
		}
		//actualizar
		public function modificarTipo($tipousuario){
			$pdo = Database::conectar();
            $stm = $pdo->prepare("CALL modificarTipoUsuario(?,?)");
            $id = $tipousuario->getId();
            $descripcion = $tipousuario->getDescripcion();
	     
            $stm ->bindParam(1,$descripcion,PDO::PARAM_STR);
            $stm ->bindParam(2,$id, PDO::PARAM_INT);
            
            $resultado = $stm->execute();
            Database::desconectar();
	           
	        return $resultado;
		}
		//eliminar
		public function eliminarTipo($id){
			$pdo = Database::conectar();
            $stm = $pdo->prepare("CALL eliminarTipoUsuario(?)");
            $stm ->bindParam(1,$id,PDO::PARAM_INT);
            $resultado = $stm->execute();
            Database::desconectar();
	           
	        return $resultado;

		}
		//obtener
		public function getAllTBTipoUsuarios() {
            $pdo = Database::conectar();
            $stm = $pdo->prepare("CALL obtenerTipoUsuario()");
            $stm->execute();
            Database::desconectar();
            return $stm->fetchAll(PDO::FETCH_ASSOC);
        }
   	}


?>
<?php 	 
	
	include_once 'data.php';
    if (is_file("../domain/usuario.php")){
        include ("../domain/usuario.php");
    }else{
        include ("../../domain/usuario.php");
    }
	
class UsuarioData extends Database{

	public function __construct(){}

	public function getUltimoIdInsertado(){
        	$pdo = Database::conectar();
	        $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
	        $stmt = $pdo ->prepare("SELECT MAX(usuarioid) AS usuarioid  FROM tbusuario");
	        $stmt -> execute();
	        $nextId = 1;
	                
	        if($row = $stmt->fetch()){
	           $nextId = $row[0]+1;
	        }

	        return $nextId;
        }


		//insertar
		public function insertarUsuario($usuario){
			$pdo = Database::conectar();
            $stm = $pdo->prepare("CALL insertarUsuario(?,?,?,?,?,?)");

            $max = $pdo ->prepare("SELECT MAX(usuarioid) AS usuarioid  FROM tbusuario");
	        $max -> execute();
	        $nextId = 1;
	                
	        if($row = $max->fetch()){
	           $nextId = $row[0]+1;
	        }
	        $nombre = $usuario->getNombre();
	        $telefono = $usuario->getTelefono();
	        $correo = $usuario->getCorreo();
			$password = $usuario->getPassword();
	        $tipoid = $usuario->getTipoid();
            $stm ->bindParam(1,$nextId,PDO::PARAM_INT);
            $stm ->bindParam(2,$nombre,PDO::PARAM_STR);
			$stm ->bindParam(3,$telefono,PDO::PARAM_INT);
            $stm ->bindParam(4,$correo,PDO::PARAM_STR);
            $stm ->bindParam(5,$password,PDO::PARAM_INT);
			$stm ->bindParam(6,$tipoid,PDO::PARAM_INT);
            $resultado = $stm->execute();
            Database::desconectar();
	           
	        return $resultado;
		}
		//actualizar
		public function modificarUsuario($usuario){
			$pdo = Database::conectar();
            $stm = $pdo->prepare("CALL modificarUsuario(?,?,?,?,?,?)");
            $id = $usuario->getId();
            $nombre = $usuario->getNombre();
	        $telefono = $usuario->getTelefono();
			$correo = $usuario->getCorreo();
            $password = $usuario->getPassword();
	        $tipoid = $usuario->getTipoid();
            $stm ->bindParam(1,$id,PDO::PARAM_INT);
            $stm ->bindParam(2,$nombre,PDO::PARAM_STR);
            $stm ->bindParam(3,$telefono,PDO::PARAM_INT);
			$stm ->bindParam(4,$correo,PDO::PARAM_STR);
            $stm ->bindParam(5,$password,PDO::PARAM_STR);
            $stm ->bindParam(6,$tipoid,PDO::PARAM_INT);
			
            $resultado = $stm->execute();
            Database::desconectar();
	           
	        return $resultado;
		}
		//eliminar
		public function eliminarusuario($usuarioid){
			$pdo = Database::conectar();
            $stm = $pdo->prepare("CALL eliminarUsuario(?)");
            $stm ->bindParam(1,$usuarioid,PDO::PARAM_INT);
            $resultado = $stm->execute();
            Database::desconectar();
	           
	        return $resultado;

		}
		//obtener
		public function getAllTBusuarios() {
            $pdo = Database::conectar();
            $stm = $pdo->prepare("CALL obtenerUsuarios()");
            $stm->execute();
            Database::desconectar();
            return $stm->fetchAll(PDO::FETCH_ASSOC);
        }
	
		//insertar
		//actualizar
		//eliminar
		//obtener

		//iniciar sesión
	public function obtenerUsuarioLogin($correo, $password){
		$pdo = Database::conectar();
		$stm = $pdo->prepare("CALL obtenerDatosUsuario(?,?)");
		$stm->bindParam(1,$correo, PDO::PARAM_STR);
		$stm->bindParam(2,$password, PDO::PARAM_STR);
		$stm->execute();
		Database::desconectar();
		return $stm->fetchAll(PDO::FETCH_ASSOC);
		
	}
}




?>
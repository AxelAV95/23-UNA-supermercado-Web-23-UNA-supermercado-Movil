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
            $stm = $pdo->prepare("CALL insertarUsuario(?,?,?)");

            
	          
			$password = $usuario->getPassword();
			$hashed_password = password_hash($password, PASSWORD_BCRYPT);
			$empleadoid = $usuario->getEmpleadoId();
	        $tipoid = $usuario->getTipoid();

        
            $stm ->bindParam(1,$hashed_password,PDO::PARAM_STR);
			$stm ->bindParam(2,$empleadoid,PDO::PARAM_INT);
            $stm ->bindParam(3,$tipoid,PDO::PARAM_INT);
        
            $resultado = $stm->execute();
            Database::desconectar();
	           
	        return $resultado;
		}
		//actualizar
		public function modificarUsuario($usuario){
			$pdo = Database::conectar();
            $stm = $pdo->prepare("CALL modificarUsuario(?,?,?)");

            $id = $usuario->getId();
            $password = $usuario->getPassword();
            $hashed_password = password_hash($password, PASSWORD_BCRYPT);
	        $tipoid = $usuario->getTipoid();

            $stm ->bindParam(1,$hashed_password ,PDO::PARAM_STR);
            $stm ->bindParam(2,$tipoid,PDO::PARAM_INT);
            $stm ->bindParam(3,$id,PDO::PARAM_INT);
			
			
            $resultado = $stm->execute();
            Database::desconectar();
	           
	        return $resultado;
		}
		//eliminar
		public function eliminarUsuario($usuarioid){
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

        public function verificarEmpleadoUsuario($id) {
            $pdo = Database::conectar();
            $stm = $pdo->prepare("CALL verificarEmpleado(?)");
            $stm ->bindParam(1,$id,PDO::PARAM_INT);
            $stm->execute();
			Database::desconectar();

			if($stm ->rowCount() > 0){
				$resultado = 1;
			}else{
				$resultado = 0;
			}

            return $resultado;
        }

         public function verificarCuentaUsuario($cedula) {
            $pdo = Database::conectar();
            $stm = $pdo->prepare("CALL verificarCuentaUsuario(?)");
            $stm ->bindParam(1,$cedula,PDO::PARAM_INT);
            $stm->execute();
			Database::desconectar();

			if($stm ->rowCount() > 0){
				$resultado = 1;
			}else{
				$resultado = 0;
			}

            return $resultado;
        }
	
	
		//insertar
		//actualizar
		//eliminar
		//obtener

		//iniciar sesión
	/*public function obtenerUsuarioLogin($correo, $password){
		$pdo = Database::conectar();
		$stm = $pdo->prepare("CALL obtenerDatosUsuario(?,?)");
		$stm->bindParam(1,$correo, PDO::PARAM_STR);
		$stm->bindParam(2,$password, PDO::PARAM_STR);
		$stm->execute();
		Database::desconectar();
		return $stm->fetchAll(PDO::FETCH_ASSOC);
		
	}*/

	public function obtenerUsuarioLogin($cedula){
		$pdo = Database::conectar();
		$stm = $pdo->prepare("CALL obtenerDatosSesion(?)");
		$stm->bindParam(1,$cedula, PDO::PARAM_INT);
		
		$stm->execute();
		Database::desconectar();
		return $stm->fetchAll(PDO::FETCH_ASSOC);
		
	}




}




?>
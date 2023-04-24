<?php 	 
	
	include_once 'data.php';
  
        include "../domain/cliente.php";

class clienteData extends Database{

	public function __construct(){}

		//insertar
		public function insertarCliente($cliente){
			$pdo = Database::conectar();
            $stm = $pdo->prepare("CALL insertarCliente(?,?,?,?,?,?,?,?,?)");
            $max = $pdo ->prepare("SELECT MAX(clienteid) AS clienteid  FROM tbcliente");
	        $max -> execute();
	        $nextId = 1;
	                
	        if($row = $max->fetch()){
	           $nextId = $row[0]+1;
	        }
	        $clientenombre = $cliente->getClientenombre();
            $clienteapellidos = $cliente->getClienteapellidos();
            $clientecedula = $cliente->getClientecedula();
            $clientedireccion = $cliente->getClientedireccion();
            $clientetelefono = $cliente->getClientetelefono();
            $clientecorreo = $cliente->getClientecorreo();
            $clientefechaafiliacion = $cliente->getClientefechaafiliacion();
            $clientetipomembresia = $cliente->getClientetipomembresia();
            

			$stm ->bindParam(1,$nextId,PDO::PARAM_INT);
            $stm ->bindParam(2,$clientenombre,PDO::PARAM_STR);
            $stm ->bindParam(3,$clienteapellidos,PDO::PARAM_STR);
            $stm ->bindParam(4,$clientecedula,PDO::PARAM_INT);
            $stm ->bindParam(5,$clientedireccion,PDO::PARAM_STR);
            $stm ->bindParam(6,$clientetelefono,PDO::PARAM_INT);
            $stm ->bindParam(7,$clientecorreo,PDO::PARAM_STR);
            $stm ->bindParam(8,$clientefechaafiliacion,PDO::PARAM_STR);
            $stm ->bindParam(9,$clientetipomembresia,PDO::PARAM_INT);
            $resultado = $stm->execute();
            Database::desconectar();
	           
	        return $resultado;
		}
		//actualizar
		public function modificarCliente($cliente){
			$pdo = Database::conectar();
            $stm = $pdo->prepare("CALL modificarCliente(?,?,?,?,?,?,?,?,?)");
            $clienteid = $cliente->getClienteid();
            $clientenombre = $cliente->getClientenombre();
            $clienteapellidos = $cliente->getClienteapellidos();
            $clientecedula = $cliente->getClientecedula();
            $clientedireccion = $cliente->getClientedireccion();
            $clientetelefono = $cliente->getClientetelefono();
            $clientecorreo = $cliente->getClientecorreo();
            $clientefechaafiliacion = $cliente->getClientefechaafiliacion();
            $clientetipomembresia = $cliente->getClientetipomembresia();


            $stm ->bindParam(1,$clientenombre,PDO::PARAM_STR);
            $stm ->bindParam(2,$clienteapellidos,PDO::PARAM_STR);
            $stm ->bindParam(3,$clientecedula,PDO::PARAM_INT);
            $stm ->bindParam(4,$clientedireccion,PDO::PARAM_STR);
            $stm ->bindParam(5,$clientetelefono,PDO::PARAM_INT);
            $stm ->bindParam(6,$clientecorreo,PDO::PARAM_STR);
            $stm ->bindParam(7,$clientefechaafiliacion,PDO::PARAM_STR);
            $stm ->bindParam(8,$clientetipomembresia,PDO::PARAM_INT);
            $stm ->bindParam(9,$clienteid,PDO::PARAM_INT);
			
            $resultado = $stm->execute();
            Database::desconectar();
	           
	        return $resultado;
		}
		//eliminar
		public function eliminarCliente($clienteid){
			$pdo = Database::conectar();
            $stm = $pdo->prepare("CALL eliminarCliente(?)");
            $stm ->bindParam(1,$clienteid,PDO::PARAM_INT);
            $resultado = $stm->execute();
            Database::desconectar();

	        return $resultado;

		}
		//obtener
		public function getAllTBClientes() {
            $pdo = Database::conectar();
            $stm = $pdo->prepare("CALL obtenerClientes()");
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
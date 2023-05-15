<?php 

include_once 'data.php';
include '../domain/empleado.php';

class EmpleadoData extends Database{
	
	public function __construct(){}

    //OBTENER EMPLEADOS
	public function obtenerempleados(){
		$pdo = Database::conectar();
		$stm = $pdo->prepare("CALL obtenerempleados");
		$stm->execute();
		Database::desconectar();
		return $stm->fetchAll(PDO::FETCH_ASSOC);
	}

	public function obtenerTotalEmpleados(){
		$pdo = Database::conectar();
		$stm = $pdo->prepare("CALL obtenerTotalEmpleados");
		$stm->execute();
		Database::desconectar();
		return $stm->fetchAll(PDO::FETCH_ASSOC)[0]['total'];
	}

	//INSERTAR NUEVO EMPLEADO
	public function insertarempleado($empleado){
		$pdo = Database::conectar();

		$stm = $pdo->prepare("CALL insertarempleado(?,?,?,?,?,?,?,?,?,?)");

		$stmt = $pdo->prepare("SELECT MAX(empleadoid) AS empleadoid  FROM tbempleado");
		$stmt->execute();
		$nextId = 1;

		if ($row = $stmt->fetch()) {
			$nextId = $row[0] + 1;
		}

		$empleadocedula = $empleado->getEmpleadocedula();
		$empleadonombre = $empleado->getEmpleadonombre();
		$empleadoapellidos = $empleado->getEmpleadoapellidos();
		$empleadotelefono = $empleado->getEmpleadotelefono();
        $empleadodireccion = $empleado->getEmpleadodireccion();
		$empleadofechaingreso = $empleado->getEmpleadofechaingreso();
		$empleadofechasalida = $empleado->getEmpleadofechasalida();
		$empleadoestado = $empleado->getEmpleadoestado();
        $empleadotipoid = $empleado->getEmpleadotipoid();
		
		$stm->bindParam(1, $nextId, PDO::PARAM_INT);
		$stm->bindParam(2, $empleadocedula, PDO::PARAM_INT);
		$stm->bindParam(3, $empleadonombre, PDO::PARAM_STR);
		$stm->bindParam(4, $empleadoapellidos, PDO::PARAM_STR);
		$stm->bindParam(5, $empleadotelefono, PDO::PARAM_INT);
        $stm->bindParam(6, $empleadodireccion, PDO::PARAM_STR);
		$stm->bindParam(7, $empleadofechaingreso, PDO::PARAM_STR);
		$stm->bindParam(8, $empleadofechasalida, PDO::PARAM_STR);
		$stm->bindParam(9, $empleadoestado, PDO::PARAM_INT);
		$stm->bindParam(10, $empleadotipoid, PDO::PARAM_INT);
		$resultado = $stm->execute();

		Database::desconectar();
		return $resultado;
	}

	public function modificarempleado($empleado){
		
		$pdo = Database::conectar();
		$stm = $pdo->prepare("CALL modificarempleado(?,?,?,?,?,?,?,?,?,?)");
		
		$empleadoid = $empleado->getEmpleadoid();
		$empleadocedula = $empleado->getEmpleadocedula();
		$empleadonombre = $empleado->getEmpleadonombre();
		$empleadoapellidos = $empleado->getEmpleadoapellidos();
		$empleadotelefono = $empleado->getEmpleadotelefono();
        $empleadodireccion = $empleado->getEmpleadodireccion();
		$empleadofechaingreso = $empleado->getEmpleadofechaingreso();
		$empleadofechasalida = $empleado->getEmpleadofechasalida();
		$empleadoestado = $empleado->getEmpleadoestado();
        $empleadotipoid = $empleado->getEmpleadotipoid();
		
		$stm->bindParam(1, $empleadoid, PDO::PARAM_INT);
		$stm->bindParam(2, $empleadocedula, PDO::PARAM_INT);
		$stm->bindParam(3, $empleadonombre, PDO::PARAM_STR);
		$stm->bindParam(4, $empleadoapellidos, PDO::PARAM_STR);
		$stm->bindParam(5, $empleadotelefono, PDO::PARAM_INT);
        $stm->bindParam(6, $empleadodireccion, PDO::PARAM_STR);
		$stm->bindParam(7, $empleadofechaingreso, PDO::PARAM_STR);
		$stm->bindParam(8, $empleadofechasalida, PDO::PARAM_STR);
		$stm->bindParam(9, $empleadoestado, PDO::PARAM_INT);
		$stm->bindParam(10, $empleadotipoid, PDO::PARAM_INT);

		$resultado = $stm->execute();
		
		$resultado = $stm->execute();
		Database::desconectar();
		return $resultado;
	}


	public function eliminarempleado($empleado){
		$pdo = Database::conectar();
		$stm = $pdo->prepare("CALL eliminarempleado(?)");
		$stm->bindParam(1, $empleado, PDO::PARAM_INT);
		$resultado = $stm->execute();
		Database::desconectar();

		return $resultado;
	}

    
	
}

?>

<?php 
	//$data = new EmpleadoData();

	//print_r($data->obtenerTotalEmpleados()[0]['total']);
 ?>
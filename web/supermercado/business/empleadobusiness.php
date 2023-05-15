<?php 

include '../data/empleadodata.php';

class EmpleadoBusiness{

	private $empleadoData;

	public function EmpleadoBusiness(){
		$this->empleadoData = new EmpleadoData();
	}

	public function insertarempleado($empleado){
        return $this->empleadoData->insertarempleado($empleado);
    }

     public function modificarempleado($empleado){
        return $this->empleadoData->modificarempleado($empleado);
    }

    public function eliminarempleado($id){
        return $this->empleadoData->eliminarempleado($id);
    }

    public function obtenerempleados(){
        return $this->empleadoData->obtenerempleados();
    }

    public function obtenerTotalEmpleados(){
        return $this->empleadoData->obtenerTotalEmpleados();
    }


}


?>
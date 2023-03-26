<?php
include "../data/tipoempleadodata.php";

class TipoEmpleadoBusiness{

    private $tipoEmpledadoData;

    public function TipoEmpleadoBusiness(){
        $this->tipoEmpledadoData = new TipoEmpleadoData();
    }

    public function insertarTipo($tipoEmpleado) {
        return $this->tipoEmpledadoData->insertarTipo($tipoEmpleado);
    }

    public function actualizar($tipoEmpleado) {
        return $this->tipoEmpledadoData->actualizar($tipoEmpleado);
    }

    public function eliminarTipo($id) {
        return $this->tipoEmpledadoData->eliminarTipo($id);
    }

    public function obtener() {
        return $this->tipoEmpledadoData->obtener();
    }



}

?>
<?php
class TipoEmpleado{

    private $tipoEmpleadoId;
    private $tipoEmpleadoDescripcion;

    function TipoEmpleado($tipoEmpleadoId, $tipoEmpleadoDescripcion){
        $this->tipoEmpleadoId = $tipoEmpleadoId;
        $this->tipoEmpleadoDescripcion = $tipoEmpleadoDescripcion;
    }

    function getTipoEmpleadoId(){
        return $this->tipoEmpleadoId;
    }
    
    function setTipoEmpleadoId($tipoEmpleadoId){
        $this->tipoEmpleadoId = $tipoEmpleadoId;
    }

    function getTipoEmpleadoDescripcion(){
        return $this->tipoEmpleadoDescripcion;
    }
    
    function setTipoEmpleadoDescripcion($tipoEmpleadoDescripcion){
        $this->tipoEmpleadoDescripcion = $tipoEmpleadoDescripcion;
    }




}

?>
<?php

class Empleado{

    private $empleadoid;
    private $empleadocedula;
    private $empleadonombre;
    private $empleadoapellidos;
    private $empleadotelefono;
    private $empleadodireccion;
    private $empleadofechaingreso;
    private $empleadofechasalida;
    private $empleadoestado;
    private $empleadotipoid;

    function Empleado($empleadoid, $empleadocedula,$empleadonombre,$empleadoapellidos,
    $empleadotelefono,$empleadodireccion,$empleadofechaingreso,$empleadofechasalida,
    $empleadoestado,$empleadotipoid){
        $this->empleadoid = $empleadoid;
        $this->empleadocedula = $empleadocedula;
        $this->empleadonombre = $empleadonombre;
        $this->empleadoapellidos = $empleadoapellidos;
        $this->empleadotelefono = $empleadotelefono;
        $this->empleadodireccion = $empleadodireccion;
        $this->empleadofechaingreso = $empleadofechaingreso;
        $this->empleadofechasalida = $empleadofechasalida;
        $this->empleadoestado = $empleadoestado;
        $this->empleadotipoid = $empleadotipoid;
    }

     // Métodos set
     public function setEmpleadoid($empleadoid) {
        $this->empleadoid = $empleadoid;
    }
    public function setEmpleadocedula($empleadocedula) {
        $this->empleadocedula = $empleadocedula;
    }
    public function setEmpleadonombre($empleadonombre) {
        $this->empleadonombre = $empleadonombre;
    }
    public function setEmpleadoapellidos($empleadoapellidos) {
        $this->empleadoapellidos = $empleadoapellidos;
    }
    public function setEmpleadotelefono($empleadotelefono) {
        $this->empleadotelefono = $empleadotelefono;
    }
    public function setEmpleadodireccion($empleadodireccion) {
        $this->empleadodireccion = $empleadodireccion;
    }
    public function setEmpleadofechaingreso($fecha) {
        $this->empleadofechaingreso = $fecha;
    }
    public function setEmpleadofechasalida($fecha) {
        $this->empleadofechasalida = $fecha;
    }
    public function setEmpleadoestado($estado) {
        $this->empleadoestado = $estado;
    }
    public function setEmpleadotipoid($tipo) {
        $this->empleadotipoid = $tipo;
    }
    
    // Métodos get
    public function getEmpleadoid() {
        return $this->empleadoid;
    }
    public function getEmpleadocedula() {
        return $this->empleadocedula;
    }
    public function getEmpleadonombre() {
        return $this->empleadonombre;
    }
    public function getEmpleadoapellidos() {
        return $this->empleadoapellidos;
    }
    public function getEmpleadotelefono() {
        return $this->empleadotelefono;
    }
    public function getEmpleadodireccion() {
        return $this->empleadodireccion;
    }
    public function getEmpleadofechaingreso() {
        return $this->empleadofechaingreso;
    }
    public function getEmpleadofechasalida() {
        return $this->empleadofechasalida;
    }
    public function getEmpleadoestado() {
        return $this->empleadoestado;
    }
    public function getEmpleadotipoid() {
        return $this->empleadotipoid;
    }
}

?>
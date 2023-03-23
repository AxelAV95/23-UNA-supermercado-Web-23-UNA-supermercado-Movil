<?php 

class Orden{
	private $ordenid;
	private $clientenombre;
	private $telefono;
	private $correo;
	private $metodo;
	private $fecha;
	private $total;
	private $estado;
	

	
	function setIdOrden($id){
		$this->ordenid = $id;
	}

	function setClienteOrden($cliente){
		$this->clientenombre = $cliente;
	}

	function setTelefonoOrden($telefono){
		$this->telefono = $telefono;
	}

	function setCorreoOrden($correo){
		$this->correo = $correo;
	}

	function setMetodoOrden($metodo){
		$this->metodo = $metodo;
	}

	function setFechaOrden($fecha){
		$this->fecha = $fecha;
	}

	function setTotalOrden($total){
		$this->total = $total;
	}

	function setEstadoOrden($estado){
		$this->estado = $estado;
	}


	public function getIdOrden()
	{
		return $this->ordenid;
	}

	public function getClienteOrden()
	{
		return $this->clientenombre;
	}


	public function getTelefonoOrden()
	{
		return $this->telefono;
	}


	public function getCorreoOrden()
	{
		return $this->correo;
	}


	public function getMetodoOrden()
	{
		return $this->metodo;
	}

	public function getFechaOrden()
	{
		return $this->fecha;
	}


	public function getTotalOrden()
	{
		return $this->total;
	}


	public function getEstadoOrden()
	{
		return $this->estado;
	}

}


?>   
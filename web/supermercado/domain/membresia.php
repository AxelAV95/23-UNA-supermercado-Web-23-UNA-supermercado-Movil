<?php 

	class Membresia{
		
		private $membresiaid;
		private $membresiadescripcion;

        public function __construct(){
          
        }
   
    public function getMembresiaid()
    {
        return $this->membresiaid;
    }

    public function setMembresiaid($membresiaid)
    {
        $this->membresiaid = $membresiaid;

  
    }

    public function getMembresiadescripcion()
    {
        return $this->membresiadescripcion;
    }


    public function setMembresiadescripcion($membresiadescripcion)
    {
        $this->membresiadescripcion = $membresiadescripcion;


    }

}
?>
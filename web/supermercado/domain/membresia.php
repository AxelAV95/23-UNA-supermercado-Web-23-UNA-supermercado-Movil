<?php 

	class Membresia{
		
		private $membresiaid;
		private $membresiadescripcion;
		
    /**
     * @return mixed
     */
    public function getMembresiaid()
    {
        return $this->membresiaid;
    }

    /**
     * @param mixed $membresiaid
     *
     * @return self
     */
    public function setMembresiaid($membresiaid)
    {
        $this->membresiaid = $membresiaid;

        return $this;
    }

    /**
     * @return mixed
     */
    public function getMembresiadescripcion()
    {
        return $this->membresiadescripcion;
    }

    /**
     * @param mixed $membresiadescripcion
     *
     * @return self
     */
    public function setMembresiadescripcion($membresiadescripcion)
    {
        $this->membresiadescripcion = $membresiadescripcion;

        return $this;
    }

    /**
     * @return mixed
     */
    
}
?>
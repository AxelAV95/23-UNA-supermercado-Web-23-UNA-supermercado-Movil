<?php 

	class Usuario{
		
		private $id;
	    private $empleadoid;
		private $password;
		private $tipoid;

				
		
	
    /**
     * @return mixed
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * @param mixed $id
     *
     * @return self
     */
    public function setId($id)
    {
        $this->id = $id;

        return $this;
    }

    public function setEmpleadoId($id)
    {
        $this->empleadoid = $id;

    }

   


    /**
     * @return mixed
     */
    public function getPassword()
    {
        return $this->password;
    }

    /**
     * @param mixed $password
     *
     * @return self
     */
    public function setPassword($password)
    {
        $this->password = $password;

        return $this;
    }

    /**
     * @return mixed
     */
    public function getTipoid()
    {
        return $this->tipoid;
    }

    public function getEmpleadoId()
    {
        return $this->empleadoid;
    }

    /**
     * @param mixed $tipoid
     *
     * @return self
     */
    public function setTipoid($tipoid)
    {
        $this->tipoid = $tipoid;

        return $this;
    }
}
?>
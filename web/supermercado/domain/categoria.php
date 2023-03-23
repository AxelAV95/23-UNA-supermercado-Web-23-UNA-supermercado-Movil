<?php 

	class Categoria {
		private $id;
		private $descripcion;
		private $imagen;
		private $codigo;


    public function getId()
    {
        return $this->id;
    }

    public function setId($id)
    {
        $this->id = $id;

        return $this;
    }

    public function getDescripcion()
    {
        return $this->descripcion;
    }

 
    public function setDescripcion($descripcion)
    {
        $this->descripcion = $descripcion;

        return $this;
    }


    public function getImagen()
    {
        return $this->imagen;
    }

    public function setImagen($imagen)
    {
        $this->imagen = $imagen;

        return $this;
    }

    /**
     * @return mixed
     */
    public function getCodigo()
    {
        return $this->codigo;
    }

    /**
     * @param mixed $codigo
     *
     * @return self
     */
    public function setCodigo($codigo)
    {
        $this->codigo = $codigo;

        return $this;
    }
}


 ?>
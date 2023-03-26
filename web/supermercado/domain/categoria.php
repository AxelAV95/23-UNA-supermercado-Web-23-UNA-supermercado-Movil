<?php 

	class Categoria {
		private $id;
		private $nombre;



    public function getId()
    {
        return $this->id;
    }

    public function setId($id)
    {
        $this->id = $id;

        return $this;
    }

 
	
		public function getNombre()
		{
				return $this->nombre;
		}

	
		public function setNombre($nombre)
		{
				$this->nombre = $nombre;

				return $this;
		}
}


 ?>
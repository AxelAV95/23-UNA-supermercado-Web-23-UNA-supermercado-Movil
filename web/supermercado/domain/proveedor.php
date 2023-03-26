<?php 

	class Proveedor{
		
		private $id;
		private $nombre;
		private $direccion;
        private $correo;
        private $telefono;


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
       
        public function getDireccion()
        {
            return $this->direccion;
        }
    
       
        public function setDireccion($direccion)
        {
            $this->direccion = $direccion;
    
            return $this;
        }

        public function getCorreo()
        {
            return $this->correo;
        }
    
       
        public function setCorreo($correo)
        {
            $this->correo = $correo;
    
            return $this;
        }

        
        public function getTelefono()
        {
            return $this->telefono;
        }
    
       
        public function setTelefono($telefono)
        {
            $this->telefono = $telefono;
    
            return $this;
        }
        
      
        
     

       
        

        
      

        
       

       

	
}
?>
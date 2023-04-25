<?php 

	class Cliente{
		
		private $clienteid;
		private $clientenombre;
		private $clienteapellidos;
        private $clientecedula;
        private $clientedireccion;
        private $clientetelefono;
        private $clientecorreo;
        private $clientefechaafiliacion;
        private $clientetipomembresia;


        public function __construct($clienteid, $clientenombre,$clienteapellidos,$clientecedula,$clientedireccion,
        $clientetelefono, $clientecorreo,$clientefechaafiliacion,$clientetipomembresia ){
            $this->clienteid = $clienteid;
            $this->clientenombre = $clientenombre;
            $this->clienteapellidos = $clienteapellidos;
            $this->clientecedula = $clientecedula;
            $this->clientedireccion = $clientedireccion;
            $this->clientetelefono = $clientetelefono;
            $this->clientecorreo = $clientecorreo;
            $this->clientefechaafiliacion = $clientefechaafiliacion;
            $this->clientetipomembresia = $clientetipomembresia;
        }
   
        public function getClienteid()
        {
            return $this->clienteid;
        }
    
        public function setClienteid($clienteid)
        {
            $this->clienteid = $clienteid;
    
      
        }
    
        public function getClientenombre()
        {
            return $this->clientenombre;
        }
    
    
        public function setClientenombre($clientenombre)
        {
            $this->clientenombre = $clientenombre;
    
    
        }



        public function getClienteapellidos()
        {
            return $this->clienteapellidos;
        }
    
    
        public function setClienteapellidos($clienteapellidos)
        {
            $this->clienteapellidos = $clienteapellidos;
    
    
        }


        public function getClientecedula()
        {
            return $this->clientecedula;
        }
    
        public function setClientecedula($clientecedula)
        {
            $this->clientecedula = $clientecedula;
    
    
        }
        public function getClientedireccion()
        {
            return $this->clientedireccion;
        }
    


        public function setClientedireccion($clientedireccion)
        {
            $this->clientedireccion = $clientedireccion;
    
    
        }
         public function getClientetelefono()
        {
            return $this->clientetelefono;
        }
    

        
        public function setClientetelefono($clientetelefono)
        {
            $this->clientetelefono = $clientetelefono;
    
    
        }
        public function getClientecorreo()
        {
            return $this->clientecorreo;
        }
    

        
        public function setClientecorreo($clientecorreo)
        {
            $this->clientecorreo = $clientecorreo;
    
    
        }
        public function getClientefechaafiliacion()
        {
            return $this->clientefechaafiliacion;
        }
    

        
        public function setClientefechaafiliacion($clientefechaafiliacion)
        {
            $this->clientefechaafiliacion = $clientefechaafiliacion;
    
    
        }

        public function getClientetipomembresia()
        {
            return $this->clientetipomembresia;
        }
    

        
        public function setClientetipomembresia($clientetipomembresia)
        {
            $this->clientetipomembresia = $clientetipomembresia;
    
    
        }

}
?>
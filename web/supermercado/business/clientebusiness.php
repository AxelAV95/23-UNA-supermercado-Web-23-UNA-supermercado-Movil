<?php 	 
	

      	include "../data/clientedata.php";

	class clienteBusiness {
		private $clienteData;

		public function ClienteBusiness(){
        	$this->clienteData = new ClienteData();
    	}
    
    	public function insertarCliente($cliente){
    		return $this->clienteData->insertarCliente($cliente);
    	}
    	public function modificarCliente($cliente){
    		return $this->clienteData->modificarCliente($cliente);
    	}
    	public function eliminarCliente($id){

    		return $this->clienteData->eliminarCliente($id);
			
		
    	}
		public function getAllTBClientes(){
			 return $this->clienteData->getAllTBClientes();
		}
	}



?>
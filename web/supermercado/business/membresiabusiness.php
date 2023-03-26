<?php 	 
	
	if (is_file("../data/membresiadata.php")){
      	include ("../data/membresiadata.php");
    }else{
    	include ("../../data/membresiadata.php");
    }

	
		
	class membresiaBusiness {
		private $membresiaData;

		public function MembresiaBusiness(){
        	$this->membresiaData = new MembresiaData();
    	}
    
    	
    	public function insertarMembresia($membresia){
    		return $this->membresiaData->insertarMembresia($membresia);
    	}
    	public function modificarMembresia($membresia){
    		return $this->membresiaData->modificarMembresia($membresia);
    	}
    	public function eliminarMembresia($id){
    		return $this->membresiaData->eliminarMembresia($id);
    	}
		public function getAllTBMembresias(){
			 return $this->membresiaData->getAllTBMembresias();
		}
	}



?>
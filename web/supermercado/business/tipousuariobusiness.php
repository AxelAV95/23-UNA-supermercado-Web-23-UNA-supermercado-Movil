<?php 	 
	
	if (is_file("../data/tipousuariodata.php")){
      	include ("../data/tipousuariodata.php");
    }else{
    	include ("../../data/tipousuariodata.php");
    }

	
		
	class TipoUsuarioBusiness {
		private $tipoUsuarioData;

		public function TipoUsuarioBusiness(){
        	$this->tipoUsuarioData = new TipoUsuarioData();
    	}
    
    	
    	public function insertarTipo($tipousuario){
    		return $this->tipoUsuarioData->insertarTipo($tipousuario);
    	}
    	public function modificarTipo($tipousuario){
    		return $this->tipoUsuarioData->modificarTipo($tipousuario);
    	}
    	public function eliminarTipo($id){
    		return $this->tipoUsuarioData->eliminarTipo($id);
    	}
		public function getAllTBTipoUsuarios(){
			 return $this->tipoUsuarioData->getAllTBTipoUsuarios();
		}
	}



?>
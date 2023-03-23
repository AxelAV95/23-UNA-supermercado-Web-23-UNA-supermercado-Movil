<?php 	 
	
	if (is_file("../data/categoriadata.php")){
      	include ("../data/categoriadata.php");
    }else{
    	include ("../../data/categoriadata.php");
    }

	//include '../../data/categoriadata.php';
		
	class CategoriaBusiness {
		private $categoriaData;

		public function CategoriaBusiness(){
        	$this->categoriaData = new CategoriaData();
    	}
    
    	public function getUltimoIdInsertado(){
    		return $this->categoriaData->getUltimoIdInsertado();
    	}
    	public function insertarCategoria($categoria){
    		return $this->categoriaData->insertarCategoria($categoria);
    	}
    	public function modificarCategoria($categoria){
    		return $this->categoriaData->modificarCategoria($categoria);
    	}
    	public function eliminarCategoria($id){
    		return $this->categoriaData->eliminarCategoria($id);
    	}
		public function getAllTBCategorias(){
			 return $this->categoriaData->getAllTBCategorias();
		}

        public function getDescripcionCategoria($id){
            return $this->categoriaData->getDescripcionCategoria($id);
        }
	}



?>
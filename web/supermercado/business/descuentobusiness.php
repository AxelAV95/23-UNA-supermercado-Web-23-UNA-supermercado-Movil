<?php 	 
	

      	include "../data/descuentodata.php";

	class descuentoBusiness {
		private $descuentoData;

		public function DescuentoBusiness(){
        	$this->descuentoData = new DescuentoData();
    	}
    
    	public function insertarDescuento($descuento){
    		return $this->descuentoData->insertarDescuento($descuento);
    	}
    	public function modificarDescuento($descuento){
    		return $this->descuentoData->modificarDescuento($descuento);
    	}
		public function modificarDescuento1($descuento){
    		return $this->descuentoData->modificarDescuento1($descuento);
    	}
    	public function eliminarDescuento($id){

    		return $this->descuentoData->eliminarDescuento($id);
			
		
    	}
		public function getAllTBDescuentos(){
			 return $this->descuentoData->getAllTBDescuentos();
		}

	

	}
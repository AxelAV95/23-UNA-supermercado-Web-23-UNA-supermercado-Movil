<?php 

	include '../data/configuraciondata.php';

	class ConfiguracionBusiness{

		private $configuracionData;

		public function ConfiguracionBusiness(){
			$this->configuracionData = new ConfiguracionData();
		}

		public function obtenerInformacion(){
			return $this->configuracionData->obtenerInformacion();
		}

		public function actualizarInformacion($id,$nombre,$telefono,$correo,$direccion,$rutaLogo){
			return $this->configuracionData->actualizarInformacion($id,$nombre,$telefono,$correo,$direccion,$rutaLogo);	
		}

	}


 ?>
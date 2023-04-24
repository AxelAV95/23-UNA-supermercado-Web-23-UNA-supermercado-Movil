<?php    
    
   

    include '../data/proveedordata.php';
        
    class ProveedorBusiness {
        private $proveedorData;

        public function ProveedorBusiness(){
            $this->proveedorData = new ProveedorData();
        }
    
        public function getUltimoIdInsertado(){
            return $this->proveedorData->getUltimoIdInsertado();
        }
        public function insertarProveedor($proveedor){
            return $this->proveedorData->insertarProveedor($proveedor);
        }
        public function modificarproveedor($proveedor){
            return $this->proveedorData->modificarProveedor($proveedor);
        }
        public function eliminarproveedor($id){
            return $this->proveedorData->eliminarProveedor($id);
        }
        public function getAllTBProveedores(){
             return $this->proveedorData->getAllTBProveedores();
        }
        public function getProveedorNombre($id){
            return $this->proveedorData->getNombreProveedor($id);
       }

       public function obtenerproveerdorid($id){
        return $this->proveedorData->obtenerproveerdorid($id);
   }

        
    }



?>
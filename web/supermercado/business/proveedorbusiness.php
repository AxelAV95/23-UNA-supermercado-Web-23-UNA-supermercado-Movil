<?php    
    
   

<<<<<<< HEAD
    include '../data/proveedordata.php';
=======
    include '../../data/proveedordata.php';
>>>>>>> e9d9ffd761021265a8dfaa2568b81956a94eaa02
        
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

        
    }



?>
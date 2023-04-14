<?php    
  
    include ("../data/usuariodata.php");
        
    class UsuarioBusiness {
        private $usuarioData;

        public function UsuarioBusiness(){
            $this->usuarioData = new UsuarioData();
        }
    
        public function getUltimoIdInsertado(){
            return $this->usuarioData->getUltimoIdInsertado();
        }
        public function insertarUsuario($usuario){
            return $this->usuarioData->insertarUsuario($usuario);
        }

        public function verificarEmpleadoUsuario($id){
            return $this->usuarioData->verificarEmpleadoUsuario($id);
        }
        public function modificarusuario($usuario){
            return $this->usuarioData->modificarUsuario($usuario);
        }
        public function eliminarUsuario($id){
            return $this->usuarioData->eliminarUsuario($id);
        }
        public function getAllTBUsuarios(){
             return $this->usuarioData->getAllTBUsuarios();
        }

        public function obtenerUsuarioLogin($cedula){
            return $this->usuarioData->obtenerUsuarioLogin($cedula);
        }

        public function verificarCuentaUsuario($cedula){
            return $this->usuarioData->verificarCuentaUsuario($cedula);
        }
    }



?>
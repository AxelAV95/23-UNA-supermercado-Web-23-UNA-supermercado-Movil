<?php    
    
    if (is_file("../data/usuariodata.php")){
        include ("../data/usuariodata.php");
    }else{
        include ("../../data/usuariodata.php");
    }

    //include '../../data/usuariodata.php';
        
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
        public function modificarusuario($usuario){
            return $this->usuarioData->modificarUsuario($usuario);
        }
        public function eliminarusuario($id){
            return $this->usuarioData->eliminarusuario($id);
        }
        public function getAllTBUsuarios(){
             return $this->usuarioData->getAllTBUsuarios();
        }

        public function obtenerUsuarioLogin($correo, $password){
            return $this->usuarioData->obtenerUsuarioLogin($correo, $password);
        }
    }



?>
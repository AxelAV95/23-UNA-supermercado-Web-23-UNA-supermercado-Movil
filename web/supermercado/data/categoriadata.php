<?php 
	include_once 'data.php';

	if (is_file("../domain/categoria.php")){
      	include ("../domain/categoria.php");
    }else{
    	include ("../../domain/categoria.php");
    }

//	include '../domain/categoria.php';
	class CategoriaData extends Database {
		public function __construct(){}


		public function getUltimoIdInsertado(){
        	$pdo = Database::conectar();
	        $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
	        $stmt = $pdo ->prepare("SELECT MAX(categoriaid) AS categoriaid  FROM tbcategoria");
	        $stmt -> execute();
	        $nextId = 1;
	                
	        if($row = $stmt->fetch()){
	           $nextId = $row[0]+1;
	        }

	        return $nextId;
        }


		//insertar
		public function insertarCategoria($categoria){
			$pdo = Database::conectar();
            $stm = $pdo->prepare("CALL insertarCategoria(?,?,?,?)");

            $max = $pdo ->prepare("SELECT MAX(categoriaid) AS categoriaid  FROM tbcategoria");
	        $max -> execute();
	        $nextId = 1;
	                
	        if($row = $max->fetch()){
	           $nextId = $row[0]+1;
	        }
	        $descripcion = $categoria->getDescripcion();
	        $imagen = $categoria->getImagen();
	        $codigo = $categoria->getCodigo();
            $stm ->bindParam(1,$nextId,PDO::PARAM_INT);
            $stm ->bindParam(2,$descripcion,PDO::PARAM_STR);
            $stm ->bindParam(3,$imagen,PDO::PARAM_STR);
            $stm ->bindParam(4,$codigo,PDO::PARAM_INT);
            $resultado = $stm->execute();
            Database::desconectar();
	           
	        return $resultado;
		}
		//actualizar
		public function modificarCategoria($categoria){
			$pdo = Database::conectar();
            $stm = $pdo->prepare("CALL modificarCategoria(?,?,?)");
            $id = $categoria->getId();
            $descripcion = $categoria->getDescripcion();
	        $imagen = $categoria->getImagen();
            $stm ->bindParam(1,$id,PDO::PARAM_INT);
            $stm ->bindParam(2,$descripcion,PDO::PARAM_STR);
            $stm ->bindParam(3,$imagen,PDO::PARAM_STR);
            $resultado = $stm->execute();
            Database::desconectar();
	           
	        return $resultado;
		}
		//eliminar
		public function eliminarCategoria($id){
			$pdo = Database::conectar();
            $stm = $pdo->prepare("CALL eliminarCategoria(?)");
            $stm ->bindParam(1,$id,PDO::PARAM_INT);
            $resultado = $stm->execute();
            Database::desconectar();
	           
	        return $resultado;

		}
		//obtener
		public function getAllTBCategorias() {
            $pdo = Database::conectar();
            $stm = $pdo->prepare("CALL obtenerCategorias()");
            $stm->execute();
            Database::desconectar();
            return $stm->fetchAll(PDO::FETCH_ASSOC);
        }

        public function getDescripcionCategoria($id){
        	$pdo = Database::conectar();
            $stm = $pdo->prepare("CALL obtenerCategoriaDescripcion(?)");
            $stm->bindParam(1,$id, PDO::PARAM_INT);
            $stm->execute();
            Database::desconectar();
            return $stm->fetchAll(PDO::FETCH_ASSOC);
        }
	}


 ?>

 <?php 

 // $data = new CategoriaData();
 // $cat = new Categoria();
 // // $cat->setDescripcion("test");
 // // $cat->setImagen("test");
 // // echo $data->insertarCategoria($cat);
 // $cat->setId(1);
 // $cat->setDescripcion("test2");
 // $cat->setImagen("test2");
 // echo $data->modificarCategoria($cat);
 //  echo $data->eliminarCategoria(1);

  ?>
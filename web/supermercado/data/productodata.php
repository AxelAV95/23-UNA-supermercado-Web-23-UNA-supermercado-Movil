<?php
    include_once 'data.php';
    if (is_file("../domain/producto.php")){
        include ("../domain/producto.php");
    }else{
        include ("../../domain/producto.php");
    }
	//include '../../domain/producto.php';

	class ProductoData extends Database{

        public function __construct(){}

        public function getUltimoIdInsertado(){
            $pdo = Database::conectar();
            $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
            $stmt = $pdo ->prepare("SELECT MAX(productoid) AS productoid  FROM tbproducto");
            $stmt -> execute();
            $nextId = 1;
                    
            if($row = $stmt->fetch()){
               $nextId = $row[0]+1;
            }

            return $nextId;
        }

        public function insertarProducto($producto){
            $pdo = Database::conectar();
            $stm = $pdo->prepare("CALL insertarProducto(?,?,?,?,?,?,?)");

            $max = $pdo ->prepare("SELECT MAX(productoid) AS productoid  FROM tbproducto");
            $max -> execute();
            $nextId = 1;
                    
            if($row = $max->fetch()){
               $nextId = $row[0]+1;
            } 
        
            $imagen = $producto->getImagenProducto();     
            $nombre = $producto->getNombreProducto();                       
            $precio = $producto->getPrecioProducto();
            $estado = $producto->getEstadoProducto();
            $categoria = $producto->getCategoriaProducto();
            $codigo = $producto->getProductocodigo();
            $stm ->bindParam(1,$nextId,PDO::PARAM_INT);
            $stm ->bindParam(2,$imagen,PDO::PARAM_STR);
            $stm ->bindParam(3,$nombre,PDO::PARAM_STR);          
            $stm ->bindParam(4,$precio,PDO::PARAM_INT);
            $stm ->bindParam(5,$estado,PDO::PARAM_INT);
            $stm ->bindParam(6,$categoria,PDO::PARAM_INT);
            $stm ->bindParam(7,$codigo,PDO::PARAM_INT);
            $resultado = $stm->execute();
            Database::desconectar();
               
            return $resultado;
        }

         public function modificarProducto($producto){
            $pdo = Database::conectar();
            $stm = $pdo->prepare("CALL modificarProducto(?,?,?,?,?,?,?)");
            $productoid = $producto->getIdProducto();
            $productoimg = $producto->getImagenProducto();
            $productonombre = $producto->getNombreProducto();
            $productoprecio = $producto->getPrecioProducto();
            $productoestado = $producto->getEstadoProducto();
            $productocategoriaid = $producto->getCategoriaProducto();
            $productocodigo = $producto->getProductocodigo();
            $stm ->bindParam(1,$productoid,PDO::PARAM_INT);
            $stm ->bindParam(2,$productoimg,PDO::PARAM_STR);
            $stm ->bindParam(3,$productonombre,PDO::PARAM_STR);           
            $stm ->bindParam(4,$productoprecio,PDO::PARAM_INT);
            $stm ->bindParam(5,$productoestado,PDO::PARAM_INT);
            $stm ->bindParam(6,$productocategoriaid,PDO::PARAM_INT);
            $stm ->bindParam(7,$productocodigo,PDO::PARAM_INT);
            $resultado = $stm->execute();
            Database::desconectar();
               
            return $resultado;
        }

        public function getTotalProductos(){
            $pdo = Database::conectar();
            $stm = $pdo->prepare("SELECT * FROM tbproducto ");
            $stm->execute();
            
            Database::desconectar();
            return $stm->rowCount();
        }

        public function getTotalProductosCategoria($categoriaid){
            $pdo = Database::conectar();
            $stm = $pdo->prepare("SELECT * FROM tbproducto WHERE productocategoriaid=?");
            $stm->bindParam(1,$categoriaid,PDO::PARAM_INT);
            $stm->execute();
            
            Database::desconectar();
            return $stm->rowCount();
        }

        public function getPaginasProducto($inicio, $cantidad){
            $pdo = Database::conectar();
            $stm = $pdo->prepare("CALL obtenerPaginasProducto(?,?)");
            $stm ->bindParam(1,$inicio,PDO::PARAM_INT);
            $stm ->bindParam(2,$cantidad,PDO::PARAM_INT);
            $stm->execute();
            Database::desconectar();
            return $stm->fetchAll(PDO::FETCH_ASSOC);

        }

        public function getPaginasProductoCategoria($inicio, $cantidad,$categoriaid){
            $pdo = Database::conectar();
            $stm = $pdo->prepare("CALL ObtenerPaginasProductoCategoria(?,?,?)");
            $stm ->bindParam(1,$inicio,PDO::PARAM_INT);
            $stm ->bindParam(2,$cantidad,PDO::PARAM_INT);
            $stm ->bindParam(3,$categoriaid,PDO::PARAM_INT);
            $stm->execute();
            Database::desconectar();
            return $stm->fetchAll(PDO::FETCH_ASSOC);

        }

        public function getAllTBProductos() {
            $pdo = Database::conectar();
            $stm = $pdo->prepare("SELECT * FROM tbproducto");
            $stm->execute();
            Database::desconectar();
            return $stm->fetchAll(PDO::FETCH_ASSOC);
         }

         public function getAllTBHistorialProducto() {
            $pdo = Database::conectar();
            $stm = $pdo->prepare("CALL obtenerHistorialProducto()");
            $stm->execute();
            Database::desconectar();
            return $stm->fetchAll(PDO::FETCH_ASSOC);
         }

        public function eliminarCategoria($id){
            $pdo = Database::conectar();
            $stm = $pdo->prepare("CALL eliminarCategoria(?)");
            $stm ->bindParam(1,$id,PDO::PARAM_INT);
            $resultado = $stm->execute();
            Database::desconectar();
               
            return $resultado;

        }

        public function eliminar($id){
            $pdo = Database::conectar();
            $stm = $pdo->prepare("CALL eliminarCategoria(?)");
            $stm ->bindParam(1,$id,PDO::PARAM_INT);
            $resultado = $stm->execute();
            Database::desconectar();
               
            return $resultado;

        }

        public function eliminarProducto($id){
            $pdo = Database::conectar();
            $stm = $pdo->prepare("CALL eliminarProducto(?)");
            $stm ->bindParam(1,$id,PDO::PARAM_INT);
            $resultado = $stm->execute();
            Database::desconectar();
               
            return $resultado;

        }

      

    }
?>
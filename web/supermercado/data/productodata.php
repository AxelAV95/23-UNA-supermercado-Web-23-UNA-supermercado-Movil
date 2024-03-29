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
            $stm = $pdo->prepare("CALL insertarProducto(?,?,?,?,?,?,?,?)");
        
            $max = $pdo ->prepare("SELECT MAX(productoid) AS productoid  FROM tbproducto");
            $max -> execute();
            $nextId = 1;
                        
            if($row = $max->fetch()){
               $nextId = $row[0]+1;
            } 
        
            $nombre = $producto->getNombreProducto();                       
            $precio = $producto->getPrecioProducto();
            $fechaIngreso = $producto->getProductoFechaIngresoProducto();
            $stock = $producto->getStockProducto();
            $estado = $producto->getEstadoProducto();
            $categoriaId = $producto->getCategoriaProducto();
            $proveedorId = $producto->getProductoproveedor();
            
            $stm ->bindParam(1,$nextId,PDO::PARAM_INT);
            $stm ->bindParam(2,$nombre,PDO::PARAM_STR);          
            $stm ->bindParam(3,$precio,PDO::PARAM_INT);
            $stm ->bindParam(4,$fechaIngreso,PDO::PARAM_STR);
            $stm ->bindParam(5,$stock,PDO::PARAM_INT);
            $stm ->bindParam(6,$estado,PDO::PARAM_STR);
            $stm ->bindParam(7,$categoriaId,PDO::PARAM_INT);
            $stm ->bindParam(8,$proveedorId,PDO::PARAM_INT);
            
            $resultado = $stm->execute();
            Database::desconectar();
               
            return $resultado;
        }
        
         public function modificarProducto($producto){
            $pdo = Database::conectar();
            $stm = $pdo->prepare("CALL modificarProducto(?,?,?,?,?,?,?,?)");
            $productoid = $producto->getIdProducto();
            $productonombre = $producto->getNombreProducto();
            $productoprecio = $producto->getPrecioProducto();
            $productofecha = $producto->getProductoFechaIngresoProducto();
            $productostock = $producto->getStockProducto();
            $productoestado = $producto->getEstadoProducto();
            $productocategoriaid = $producto->getCategoriaProducto();
            $productoproveedorid = $producto->getProductoproveedor();
            $stm->bindParam(1, $productoid, PDO::PARAM_INT);
            $stm->bindParam(2, $productonombre, PDO::PARAM_STR);
            $stm->bindParam(3, $productoprecio, PDO::PARAM_STR);
            $stm->bindParam(4, $productofecha, PDO::PARAM_STR);
            $stm->bindParam(5, $productostock, PDO::PARAM_INT);
            $stm->bindParam(6, $productoestado, PDO::PARAM_INT);
            $stm->bindParam(7, $productocategoriaid, PDO::PARAM_INT);
            $stm->bindParam(8, $productoproveedorid, PDO::PARAM_INT);
            
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
         public function getAllTBProductos2() {
            $pdo = Database::conectar();
            $stm = $pdo->prepare("Call ObtenerProductosConNombres()");
            $stm->execute();
            Database::desconectar();
            return $stm->fetchAll(PDO::FETCH_ASSOC);
         }
          public function obtenerProductosRecientes() {
            $pdo = Database::conectar();
            $stm = $pdo->prepare("CALL obtenerProductosRecientes()");
            $stm->execute();
            Database::desconectar();
            return $stm->fetchAll(PDO::FETCH_ASSOC);
         }

         public function obtenerTotalProductos(){
            $pdo = Database::conectar();
            $stm = $pdo->prepare("CALL obtenerTotalProductos");
            $stm->execute();
            Database::desconectar();
            return $stm->fetchAll(PDO::FETCH_ASSOC)[0]['total'];
        }

         public function obtenerTotalProductosCategoria(){
            $pdo = Database::conectar();
            $stm = $pdo->prepare("CALL obtenerTotalProductosCategoria");
            $stm->execute();

            $categorias = array();
            $total = array();

            while ($row = $stm->fetch(PDO::FETCH_ASSOC)) {
                $categorias[] = $row['categorianombre'];
                $total[] = $row['total'];
            }

            $data = array(
                'labels' => $categorias,
                'datasets' => array(
                    array(
                        'label' => 'Total de productos por categoría',
                       'backgroundColor' => array(
                            'rgb(255, 99, 132)',
                            'rgb(54, 162, 235)',
                            'rgb(255, 206, 86)',
                            'rgb(75, 192, 192)',
                            'rgb(153, 102, 255)',
                            'rgb(255, 159, 64)'
                        ),
                        'borderColor' => array(
                            'rgb(255,99,132)',
                            'rgb(54, 162, 235)',
                            'rgb(255, 206, 86)',
                            'rgb(75, 192, 192)',
                            'rgb(153, 102, 255)',
                            'rgb(255, 159, 64)'
                        ),

                        'borderWidth' => 1,
                        'data' => $total
                    )
                )
            );

            $json_data = json_encode($data);

            Database::desconectar();
            return $json_data;
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

<?php 

    //$data = new ProductoData();
   // echo $data->obtenerTotalProductosCategoria();
 ?>
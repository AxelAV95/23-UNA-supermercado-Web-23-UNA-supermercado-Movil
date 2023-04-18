<?php 

    class Producto{
        private $productoid ;
        private $productonombre;
        private $productoprecio;
        private $productoFechaIngresoProducto;
        private $stockProducto;
        private $productoestado;
        private $productocategoria;
        private $productoproveedor;

        public function __construct($id, $nombre, $precio,  $fechaIngreso, $stock, $estado, $categoria, $codigo) {
            $this->productoid = $id;
            $this->productonombre = $nombre;
            $this->productoprecio = $precio;
            $this->productoestado = $estado;
            $this->productocategoria = $categoria;
            $this->productoproveedor = $codigo;
            $this->productoFechaIngresoProducto = $fechaIngreso;
            $this->stockProducto = $stock;
        }

        function setIdProducto($id){
            $this->productoid = $id;
        }

        function getIdProducto(){
            return $this->productoid;
        }

        function setNombre($nombre){
            $this->productonombre = $nombre;
        }

        function getNombreProducto(){
            return $this->productonombre;
        }

        function setPrecioProducto($precio){
            $this->productoprecio = $precio;
        }

        function getPrecioProducto(){
            return $this->productoprecio;
        }

        function setEstadoProducto($estado){
            $this->productoestado = $estado;
        }

   


        function getEstadoProducto(){
            return $this->productoestado;
        }

        function setCategoriaProducto($productocategoria){
            $this->productocategoria = $productocategoria;
        }

        function getCategoriaProducto(){
            return $this->productocategoria;
        }

    
    /**
     * @return mixed
     */
    public function getProductoproveedor()
    {
        return $this->productoproveedor;
    }

    /**
     * @param mixed $productoproveedor
     *
     * @return self
     */
    public function setProductoproveedor($productoproveedor)
    {
        $this->productoproveedor = $productoproveedor;

        return $this;
    }

        /**
         * Get the value of productoFechaIngresoProducto
         */ 
        public function getProductoFechaIngresoProducto()
        {
                return $this->productoFechaIngresoProducto;
        }

        /**
         * Set the value of productoFechaIngresoProducto
         *
         * @return  self
         */ 
        public function setProductoFechaIngresoProducto($productoFechaIngresoProducto)
        {
                $this->productoFechaIngresoProducto = $productoFechaIngresoProducto;

                return $this;
        }
        public function getStockProducto() {
            return $this->stockProducto;
        }
    
        public function setStockProducto($stock) {
            $this->stockProducto = $stock;
        }
}


?>
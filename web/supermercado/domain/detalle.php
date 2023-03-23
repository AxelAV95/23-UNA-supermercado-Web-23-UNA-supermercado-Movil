<?php 

class Detalle {

	private $id;
	private $ordenid;
	private $preciounitario;
	private $productoid;
	private $precio;
	private $cantidad;

    public function getId()
    {
    	return $this->id;
    }

 
    public function setId($id)
    {
    	$this->id = $id;

    	return $this;
    }


    public function getOrdenId()
    {
    	return $this->ordenid;
    }

   
    public function setOrdenId($ordenid)
    {
    	$this->ordenid = $ordenid;

    	return $this;
    }


    public function getPrecioUnitario()
    {
    	return $this->preciounitario;
    }


    public function setPrecioUnitario($preciounitario)
    {
    	$this->preciounitario = $preciounitario;

    	return $this;
    }

  
    public function getProductoId()
    {
    	return $this->productoid;
    }


    public function setProductoId($productoid)
    {
    	$this->productoid = $productoid;

    	return $this;
    }

  
    public function getPrecio()
    {
    	return $this->precio;
    }


    public function setPrecio($precio)
    {
    	$this->precio = $precio;

    	return $this;
    }

    public function getCantidad()
    {
    	return $this->cantidad;
    }


    public function setCantidad($cantidad)
    {
    	$this->cantidad = $cantidad;

    	return $this;
    }
}

?>
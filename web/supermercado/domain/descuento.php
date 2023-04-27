<?php 

	class Descuento{
		
		private $descuentoid;
		private $descuentotarifa;

        private $descuentomembresiaid;

        public function __construct($descuentoid, $descuentotarifa, $descuentomembresiaid){
            $this->descuentoid = $descuentoid;
            $this->descuentotarifa = $descuentotarifa;
            $this->descuentomembresiaid = $descuentomembresiaid;

        }
   
    public function getDescuentoid()
    {
        return $this->descuentoid;
    }

    public function setDescuentoid($descuentoid)
    {
        $this->descuentoid = $descuentoid;

  
    }

    public function getDescuentotarifa()
    {
        return $this->descuentotarifa;
    }


    public function setDescuentotarifa($descuentotarifa)
    {
        $this->descuentotarifa = $descuentotarifa;


    }

    public function getDescuentomembresiaid()
    {
        return $this->descuentomembresiaid;
    }

    public function setDescuentomembresiaid($descuentomembresiaid)
    {
        $this->descuentomembresiaid = $descuentomembresiaid;

  
    }

}
?>
<?php
    include_once 'data.php';

   include ("../domain/orden.php");
   include ("../domain/detalle.php");

	//include '../domain/orden.php';
	//include '../domain/detalle.php';

	class OrdenData extends Database{

        public function __construct(){}

        public function getUltimoIdInsertado(){
        	$pdo = Database::conectar();
	        $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
	        $stmt = $pdo ->prepare("SELECT MAX(ordenid) AS ordenid  FROM tborden");
	        $stmt -> execute();
	        $nextId = 1;
	                
	        if($row = $stmt->fetch()){ 
	           $nextId = $row[0];
	        }

	        return $nextId;
        }

        public function insertarTBOrden($orden) {
	       
	        $pdo = Database::conectar();
	        $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
	        $stmt = $pdo ->prepare("SELECT MAX(ordenid) AS ordenid  FROM tborden");
	        $stmt -> execute();
	        $nextId = 1;
	                
	        if($row = $stmt->fetch()){
	           $nextId = $row[0]+1;
	        }
	        $cliente = $orden->getClienteOrden();
	        $telefono = $orden->getTelefonoOrden();
	        $correo = $orden->getCorreoOrden();
	        $metodo = $orden->getMetodoOrden();
	        $fecha = $orden->getFechaOrden();
	        $total = $orden->getTotalOrden();
	        $estado = $orden->getEstadoOrden();

	        
	        $insertar = "CALL insertarOrden (?,?,?,?,?,?,?,?)";
	        $q = $pdo->prepare($insertar);
	        $q ->bindParam(1,$nextId,PDO::PARAM_INT);
	        $q ->bindParam(2,$cliente,PDO::PARAM_STR);
	        $q ->bindParam(3,$telefono,PDO::PARAM_INT);
	        $q ->bindParam(4,$correo,PDO::PARAM_STR);
	        $q ->bindParam(5,$metodo,PDO::PARAM_INT);
	        $q ->bindParam(6,$fecha,PDO::PARAM_STR);
	        $q ->bindParam(7,$total,PDO::PARAM_INT);
	        $q ->bindParam(8,$estado,PDO::PARAM_INT);
			
			$resultado = $q->execute();

            Database::desconectar();

            $ordenData = new OrdenData();
			$ordenData->insertarNotificacion($nextId);
	           
	        return $resultado;
    	}


    	public function insertarNotificacion($ordenid){
    		$pdo = Database::conectar();
	        $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
	        $insertar = "CALL insertarNotificacion(?)";
	       
	        $q = $pdo->prepare($insertar);
	        $q ->bindParam(1,$ordenid,PDO::PARAM_INT);
	       
	        $resultado = $q->execute();
            Database::desconectar();

    	}

    	public function modificarNotificacion(){
    		$pdo = Database::conectar();
	        $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
	        $modificar = "CALL modificarNotificacion()";
	        $q = $pdo->prepare($modificar);
	        $resultado = $q->execute();

	        Database::desconectar();

	        return $resultado;

    	}

    	public function obtenerNotificaciones(){
    		
    		$pdo = Database::conectar();
            $stm = $pdo->prepare("CALL obtenerNotificaciones()");
            $stm->execute();
            $count = $stm->rowCount();
            Database::desconectar();
            return $count;
    	}

    	public function modificarOrden($ordenid,$ordenestado,$usuarioid ){
			$pdo = Database::conectar();
            $stm = $pdo->prepare("CALL actualizarEstadoOrden(?,?,?)");
           
            $stm ->bindParam(1,$ordenid,PDO::PARAM_INT);          
            $stm ->bindParam(2,$ordenestado,PDO::PARAM_INT); 
            $stm ->bindParam(3,$usuarioid,PDO::PARAM_INT); 

            $resultado = $stm->execute();
            Database::desconectar();
	           
	        return $resultado;
		}


    	public function insertarTBDetalle($detalle) {
	       
	        $pdo = Database::conectar();
	        $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
	        $stmt = $pdo ->prepare("SELECT MAX(detalleid) AS detalleid  FROM tbdetalle");
	        $stmt -> execute();
	        $nextId = 1;
	                
	        if($row = $stmt->fetch()){
	           $nextId = $row[0]+1;
	        }

	        $insertar = "CALL insertarDetalle (?,?,?,?,?)";
	        $ordenid = $detalle->getOrdenId();
	        $productoid = $detalle->getProductoId();
	        $precio = $detalle->getPrecio();
	        $cantidad = $detalle->getCantidad();

	        $q = $pdo->prepare($insertar);
	        $q ->bindParam(1,$nextId,PDO::PARAM_INT);
	        $q ->bindParam(2,$ordenid,PDO::PARAM_INT);
	        $q ->bindParam(3,$productoid,PDO::PARAM_INT);
	        $q ->bindParam(4,$precio,PDO::PARAM_INT);
	        $q ->bindParam(5,$cantidad,PDO::PARAM_INT);

	        $resultado = $q->execute();

	        Database::desconectar();
	           
	        return $resultado;
    	}

        public function getAllTBOrdenes() {
            $pdo = Database::conectar();
            $stm = $pdo->prepare("CALL obtenerOrdenes()");
            $stm->execute();
            Database::desconectar();
            return $stm->fetchAll(PDO::FETCH_ASSOC);
         }

         public function getAllTBDetalles($ordenid) {
            $pdo = Database::conectar();
            $stm = $pdo->prepare("CALL obtenerDetallesOrden(?)");
            $stm->bindParam(1,$ordenid ,PDO::PARAM_INT);
            $stm->execute();
            Database::desconectar();
            return $stm->fetchAll(PDO::FETCH_ASSOC);
         }

         public function getTotalOrdenes() {
            $pdo = Database::conectar();
            $stm = $pdo->prepare("CALL obtenerTotalOrdenes ()");
            $stm->execute();
            Database::desconectar();
            return $stm->fetchAll(PDO::FETCH_ASSOC);
         }

         public function getTotalGanancias(){
         	$pdo = Database::conectar();
            $stm = $pdo->prepare("CALL obtenerTotalGanancias ()");
            $stm->execute();
            Database::desconectar();
            return $stm->fetchAll(PDO::FETCH_ASSOC);
         }

         public function eliminarOrden($id){
			$pdo = Database::conectar();
            $stm = $pdo->prepare("CALL eliminarOrden(?)");
            $stm ->bindParam(1,$id,PDO::PARAM_INT);
            $resultado = $stm->execute();
            Database::desconectar();
	           
	        return $resultado;

		}

    }
?>
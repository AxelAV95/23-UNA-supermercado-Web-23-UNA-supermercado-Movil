<?php 


class Database{

     private static $dbName = "bdsupermercado";
    private static $dbHost = "localhost" ;
    private static $dbUsername = "root";
    private static $dbUserPassword = "";

 
    private static $cont  = null;
    
    public function __construct() {
        die('Inicialización no permitida.');
    }
    
    public static function conectar()
    {
    // One connection through whole application
    if ( null == self::$cont )
    {     
        try
        {
        self::$cont =  new PDO( "mysql:host=".self::$dbHost.";"."dbname=".self::$dbName, self::$dbUsername, self::$dbUserPassword); 
     
        }
        catch(PDOException $e)
        {
        die($e->getMessage()); 
        }
    }
    return self::$cont;
    }
    
    public static function desconectar()
    {
        self::$cont = null;
    }
}



?>
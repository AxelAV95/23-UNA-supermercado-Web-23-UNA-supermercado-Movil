<?php 


class Database{

     private static $dbName = "bdcomida";
    private static $dbHost = "localhost" ;
    private static $dbUsername = "root";
    private static $dbUserPassword = "";
    // private static $dbName = "bdcomida";
    // private static $dbHost = "bdcomida.cia11fjdhsfh.us-east-1.rds.amazonaws.com" ;
    // private static $dbUsername = "admin";
    // private static $dbUserPassword = "12345678";
 
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
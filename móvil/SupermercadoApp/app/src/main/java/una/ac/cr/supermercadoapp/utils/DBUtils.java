package una.ac.cr.supermercadoapp.utils;

public class DBUtils {

    //NOMBRE DE TABLAS Y BASE DE DATOS
    public static final String BASE_DE_DATOS = "bdsupermercado";
    public static final String CATEGORIA = "tbcategoria";
    public static final String CLIENTE = "tbcliente";
    public static final String DESCUENTO = "tbdescuento";
    public static final String EMPLEADO = "tbempleado";
    public static final String MEMBRESIA = "tbmembresia";
    public static final String PRODUCTO = "tbproducto";
    public static final String PROVEEDOR = "tbproveedor";
    public static final String SUPERMERCADO = "tbsupermercado";
    public static final String TIPO_EMPLEADO = "tbtipoempleado";
    public static final String TIPO_USUARIO = "tbtipousuario";
    public static final String USUARIO = "tbusuario";

    //VERSIÓN
    public static final int VERSION = 1;

    //CONSULTAS DDL
    public static final String CREAR_TABLA_CATEGORIA = "CREATE TABLE "+ CATEGORIA+
            " (categoriaid INTEGER PRIMARY KEY AUTOINCREMENT, categorianombre VARCHAR(225));";
    public static final String CREAR_TABLA_CLIENTE = "CREATE TABLE "+ CLIENTE+
            " (clienteid INTEGER PRIMARY KEY AUTOINCREMENT, clientenombre VARCHAR(225),clienteapellidos VARCHAR(255)," +
            "clientecedula INTEGER, clientedireccion VARCHAR(255), clientecorreo VARCHAR(255), clientefechaafiliacion DATE, " +
            "clientetipomembresia INTEGER);";
    public static final String CREAR_TABLA_DESCUENTO = "CREATE TABLE "+ DESCUENTO+
            " (descuentoid INTEGER PRIMARY KEY AUTOINCREMENT, descuentotarifa FLOAT,descuentomembresiaid INTEGER);";
    public static final String CREAR_TABLA_EMPLEADO = "CREATE TABLE "+ EMPLEADO+
            " (empleadoid INTEGER PRIMARY KEY AUTOINCREMENT, empleadocedula INTEGER, empleadonombre VARCHAR(255)," +
            "empleadoapellidos VARCHAR(255), empleadotelefono INTEGER, empleadodireccion VARCHAR(255), empleadofechaaingreso DATE, " +
            "empleadofechasalida DATE, empleadotipo INTEGER);";
    public static final String CREAR_TABLA_MEMBRESIA = "CREATE TABLE "+ MEMBRESIA+
            " (membresiaid INTEGER PRIMARY KEY AUTOINCREMENT, membresiadescripcion VARCHAR(255));";
    public static final String CREAR_TABLA_PRODUCTO = "CREATE TABLE "+ PRODUCTO+
            " (productoid INTEGER PRIMARY KEY AUTOINCREMENT, productonombre VARCHAR(255), productoprecio FLOAT," +
            "productofechaingreso DATE, productostock INTEGER, productoestado INTEGER, productocategoriaid INTEGER, " +
            "productoproveedorid INTEGER);";
    public static final String CREAR_TABLA_PROVEEDOR = "CREATE TABLE "+ PROVEEDOR+
            " (proveedorid INTEGER PRIMARY KEY AUTOINCREMENT, proveedornombre VARCHAR(255), proveedordireccion VARCHAR(255)," +
            "proveedortelefono INTEGER, proveedorcorreo VARCHAR(255), proveedorlat INTEGER, proveedorlong INTEGER);";
    public static final String CREAR_TABLA_SUPERMERCADO = "CREATE TABLE "+ SUPERMERCADO+
            " (supermercadoid INTEGER PRIMARY KEY AUTOINCREMENT, supermercadonombre VARCHAR(255), supermercadotelefono INTEGER," +
            "supermercadocorreo VARCHAR(255), supermercadodireccion VARCHAR(255), supermercadologo VARCHAR(255));";
    public static final String CREAR_TABLA_TIPO_EMPLEADO = "CREATE TABLE "+ TIPO_EMPLEADO+
            " (tipoid INTEGER PRIMARY KEY AUTOINCREMENT, tipodescripcion VARCHAR(255));";
    public static final String CREAR_TABLA_TIPO_USUARIO = "CREATE TABLE "+ TIPO_USUARIO +
            " (tipoid INTEGER PRIMARY KEY AUTOINCREMENT, tipodescripcion VARCHAR(255));";
    public static final String CREAR_TABLA_USUARIO = "CREATE TABLE "+ USUARIO +
            " (usuarioid INTEGER PRIMARY KEY AUTOINCREMENT, usuariopassword VARCHAR(255), usuarioempleadoid INTEGER, usuariotipo INTEGER);";


    //CONSULTAS DML
    public static final String CONSULTAR_CATEGORIAS_POR_ID = " SELECT * FROM `tbcategoria` WHERE `categoriaid` = ?;";
    public static final String CONSULTAR_CATEGORIAS = " SELECT * FROM `tbcategoria`";
    public static final String CONSULTAR_DATOS_SESION = "SELECT * FROM tbempleado INNER JOIN tbusuario ON tbempleado.empleadoid = tbusuario.usuarioempleadoid INNER JOIN tbtipousuario ON tbusuario.usuariotipo = tbtipousuario.tipoid WHERE tbempleado.empleadocedula = ?";
    public static final String CONSULTAR_SUPERMERCADO = "SELECT * FROM tbsupermercado";
    public static final String CONSULTAR_MEMBRESIAS = "SELECT * FROM `tbmembresia`";
    public static final String CONSULTAR_TIPOEMPLEADO = "SELECT * FROM `tbtipoempleado`";
    public static final String CONSULTAR_TIPOUSUARIO = "SELECT * FROM `tbtipousuario`";
    public static final String CONSULTAR_USUARIOS = "SELECT * FROM `tbusuario` INNER JOIN tbempleado ON tbusuario.usuarioempleadoid = tbempleado.empleadoid INNER JOIN tbtipousuario ON tbusuario.usuariotipo = tbtipousuario.tipoid";
    public static final String CONSULTAR_VERIFICAR_USUARIO = "SELECT * FROM `tbusuario` INNER JOIN tbempleado ON tbusuario.usuarioempleadoid = tbempleado.empleadoid WHERE tbempleado.empleadocedula = ?";
    public static final String CONSULTAR_VERIFICAR_EMPLEADO = "SELECT * FROM `tbusuario` WHERE `usuarioempleadoid` = ?";
    public static final String CONSULTAR_CLIENTES = "SELECT * FROM `tbcliente`";
    public static final String CONSULTAR_DESCUENTOS = "SELECT * FROM `tbdescuento`";
    public static final String CONSULTAR_EMPLEADOS = "SELECT * FROM `tbempleado`";
    public static final String CONSULTAR_PRODUCTOS = "SELECT * FROM `tbproducto`";
    public static final String CONSULTAR_PROVEEDOR = "SELECT * FROM `tbproveedor`";




}


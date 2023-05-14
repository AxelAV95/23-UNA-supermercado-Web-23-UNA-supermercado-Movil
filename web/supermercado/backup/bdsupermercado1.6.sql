-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 15-05-2023 a las 01:34:24
-- Versión del servidor: 10.4.27-MariaDB
-- Versión de PHP: 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bdsupermercado`
--
CREATE DATABASE IF NOT EXISTS `bdsupermercado` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `bdsupermercado`;

DELIMITER $$
--
-- Procedimientos
--
DROP PROCEDURE IF EXISTS `actualizarInformacionSupermercado`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `actualizarInformacionSupermercado` (IN `nombre` VARCHAR(255), IN `telefono` INT, IN `correo` VARCHAR(255), IN `direccion` VARCHAR(255), IN `logo` VARCHAR(255), IN `id` INT)   UPDATE `tbsupermercado` SET `supermercadonombre`= nombre,`supermercadotelefono`= telefono,`supermercadocorreo`=correo,
`supermercadodireccion`=direccion,`supermercadologo`= logo WHERE `supermercadoid` = id$$

DROP PROCEDURE IF EXISTS `actualizarTipoEmpleado`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `actualizarTipoEmpleado` (IN `id` INT, IN `descripcion` VARCHAR(255))   UPDATE `tbtipoempleado` SET `tipodescripcion`= descripcion WHERE `tipoid` = id$$

DROP PROCEDURE IF EXISTS `eliminarCategoria`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminarCategoria` (IN `id` INT)   BEGIN
    DECLARE error INT;
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        GET DIAGNOSTICS CONDITION 1 error = MYSQL_ERRNO;
        SELECT error AS MYSQL_ERROR;
        ROLLBACK;
        RESIGNAL;
    END;
    
    START TRANSACTION;
        DELETE FROM `tbcategoria` WHERE `categoriaid` = id;
    COMMIT;
END$$

DROP PROCEDURE IF EXISTS `eliminarCliente`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminarCliente` (IN `id` INT(11))   DELETE FROM `tbcliente` WHERE `clienteid` = id$$

DROP PROCEDURE IF EXISTS `eliminarDescuento`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminarDescuento` (IN `id` INT(11))   DELETE FROM `tbdescuento` WHERE `descuentoid` = id$$

DROP PROCEDURE IF EXISTS `eliminarempleado`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminarempleado` (IN `id` INT)   DELETE FROM `tbempleado` WHERE `empleadoid` = id$$

DROP PROCEDURE IF EXISTS `eliminarMembresias`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminarMembresias` (IN `id` INT)   DELETE FROM `tbmembresia` WHERE `membresiaid` = id$$

DROP PROCEDURE IF EXISTS `eliminarProducto`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminarProducto` (IN `p_id` INT)   BEGIN
  DELETE FROM tbproducto WHERE productoid = p_id;
END$$

DROP PROCEDURE IF EXISTS `eliminarProveedor`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminarProveedor` (IN `id` INT(20))  NO SQL BEGIN
	DECLARE error INT;
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
    	GET CURRENT DIAGNOSTICS CONDITION 1 error = MYSQL_ERRNO;
    	SELECT error AS MYSQL_ERROR;
    	ROLLBACK;
        RESIGNAL;
        
    END;
    
    START TRANSACTION;
    	DELETE FROM `tbproveedor` WHERE `proveedorid` = id;
    COMMIT;
END$$

DROP PROCEDURE IF EXISTS `eliminarTipoEmpleado`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminarTipoEmpleado` (IN `id` INT)   DELETE FROM `tbtipoempleado` WHERE `tipoid` = id$$

DROP PROCEDURE IF EXISTS `eliminarTipoUsuario`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminarTipoUsuario` (IN `id` INT)   DELETE FROM `tbtipousuario` WHERE `tipoid` = id$$

DROP PROCEDURE IF EXISTS `eliminarUsuario`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminarUsuario` (IN `id` INT)   DELETE FROM `tbusuario` WHERE `usuarioid` = id$$

DROP PROCEDURE IF EXISTS `insertarCategoria`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarCategoria` (IN `nombre` VARCHAR(50))   BEGIN
    DECLARE descripcion_count INT;
    SET descripcion_count = (SELECT COUNT(*) FROM tbcategoria WHERE categorianombre = nombre);

    IF descripcion_count = 0 AND nombre != ''
    THEN
        START TRANSACTION;
        INSERT INTO tbcategoria(categorianombre) VALUES (nombre);
        COMMIT;
    ELSE
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: Descripción duplicada o ID incorrecto.';
    END IF;
END$$

DROP PROCEDURE IF EXISTS `insertarCliente`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarCliente` (IN `id` INT(11), IN `nombre` VARCHAR(250), IN `apellidos` VARCHAR(250), IN `cedula` INT(11), IN `direccion` VARCHAR(250), IN `telefono` INT(11), IN `correo` VARCHAR(250), IN `fechaafiliacion` VARCHAR(250), IN `tipomembresia` INT(11))   INSERT INTO `tbcliente`(`clienteid`, `clientenombre`, `clienteapellidos`, `clientecedula`, `clientedireccion`, `clientetelefono`, `clientecorreo`, `clientefechaafiliacion`, `clientetipomembresia`) VALUES (id,nombre,apellidos,cedula,direccion,telefono,correo,fechaafiliacion,tipomembresia)$$

DROP PROCEDURE IF EXISTS `insertarDescuento`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarDescuento` (IN `id` INT(11), IN `tarifa` FLOAT(11), IN `tipomembresia` INT(11))   INSERT INTO `tbdescuento` (`descuentoid`, `descuentotarifa`, `descuentomembresiaid`) VALUES (id, tarifa, tipomembresia)$$

DROP PROCEDURE IF EXISTS `insertarempleado`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarempleado` (IN `id` INT, IN `cedula` INT, IN `nombre` VARCHAR(255), IN `apellidos` VARCHAR(255), IN `telefono` INT, IN `direccion` VARCHAR(255), IN `fechaingreso` DATE, IN `fechasalida` DATE, IN `estado` INT, IN `tipoempleado` INT)   INSERT INTO `tbempleado`(`empleadoid`,`empleadocedula`, `empleadonombre`,`empleadoapellidos`, `empleadotelefono`,`empleadodireccion`, `empleadofechaingreso`,`empleadofechasalida`, `empleadoestado`,`empleadotipoid`) VALUES (id,cedula,nombre,apellidos,telefono,direccion,fechaingreso,fechasalida,estado,tipoempleado)$$

DROP PROCEDURE IF EXISTS `insertarMembresia`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarMembresia` (IN `membresiaid` INT, IN `membresiadescripcion` VARCHAR(250))   INSERT INTO `tbmembresia` (`membresiaid`, `membresiadescripcion`) VALUES (membresiaid,membresiadescripcion)$$

DROP PROCEDURE IF EXISTS `insertarProducto`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarProducto` (IN `p_id` INT, IN `p_nombre` VARCHAR(100), IN `p_precio` DECIMAL(10,2), IN `p_fecha_ingreso` DATE, IN `p_stock` INT, IN `p_estado` VARCHAR(50), IN `p_categoria_id` INT, IN `p_proveedor_id` INT)   BEGIN
  DECLARE nombre_existente INT;

  -- Verificar si el nombre de producto ya existe en la tabla
  SELECT COUNT(*) INTO nombre_existente
  FROM tbproducto
  WHERE productonombre = p_nombre;

  IF nombre_existente > 0 THEN
    SIGNAL SQLSTATE '45000'
      SET MESSAGE_TEXT = 'El nombre de producto ya existe en la tabla.';
  ELSE
    -- Insertar el nuevo producto
    INSERT INTO tbproducto (productoid, productonombre, productoprecio, productofechaingreso, productostock, productoestado, productocategoriaid, productoproveedorid)
    VALUES (p_id, p_nombre, p_precio, p_fecha_ingreso, p_stock, p_estado, p_categoria_id, p_proveedor_id);
  END IF;
END$$

DROP PROCEDURE IF EXISTS `insertarProveedor`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarProveedor` (IN `id` INT(20), IN `nombre` VARCHAR(255), IN `direccion` VARCHAR(255), IN `correo` VARCHAR(255), IN `telefono` INT(20), IN `latitud` VARCHAR(255), IN `longitud` VARCHAR(255))  NO SQL BEGIN
	DECLARE error INT;
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
    	GET CURRENT DIAGNOSTICS CONDITION 1 error = MYSQL_ERRNO;
    	SELECT error AS MYSQL_ERROR;
    	ROLLBACK;
        RESIGNAL;
        
    END;
    
    START TRANSACTION;
    	INSERT INTO `tbproveedor`(`proveedorid`, `proveedornombre`, `proveedordireccion`, `proveedorcorreo`, `proveedortelefono`,`proveedorlat`,`proveedorlong`) VALUES (id,nombre,direccion,correo,telefono,latitud,longitud);
    COMMIT;
END$$

DROP PROCEDURE IF EXISTS `insertarTipoEmpleado`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarTipoEmpleado` (IN `id` INT, IN `descripcion` VARCHAR(150))   INSERT INTO `tbtipoempleado`(`tipoid`, `tipodescripcion`) VALUES (id,descripcion)$$

DROP PROCEDURE IF EXISTS `insertarTipoUsuario`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarTipoUsuario` (IN `id` INT, IN `descripcion` VARCHAR(255))   INSERT INTO `tbtipousuario`(`tipoid`, `tipodescripcion`) VALUES (id,descripcion)$$

DROP PROCEDURE IF EXISTS `insertarUsuario`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarUsuario` (IN `pass` VARCHAR(255), IN `empleado` INT, IN `tipo` INT)   INSERT INTO `tbusuario`(`usuariopassword`, `usuarioempleadoid`, `usuariotipo`) VALUES (pass,empleado,tipo)$$

DROP PROCEDURE IF EXISTS `modificarCategoria`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `modificarCategoria` (IN `id` INT, IN `nombre` VARCHAR(255))   BEGIN
    DECLARE error INT;
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        GET DIAGNOSTICS CONDITION 1 error = MYSQL_ERRNO;
        SELECT error AS MYSQL_ERROR;
        ROLLBACK;
        RESIGNAL;
    END;
    
    START TRANSACTION;
        UPDATE `tbcategoria` SET `categorianombre` = nombre WHERE `categoriaid` = id;
    COMMIT;
END$$

DROP PROCEDURE IF EXISTS `modificarCliente`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `modificarCliente` (IN `nombre` VARCHAR(250), IN `apellidos` VARCHAR(250), IN `cedula` INT(11), IN `direccion` VARCHAR(250), IN `telefono` INT(11), IN `correo` VARCHAR(250), IN `fecha` VARCHAR(250), IN `tipomembresia` INT(11), IN `id` INT(11))   UPDATE `tbcliente` SET `clientenombre`= nombre, `clienteapellidos`= apellidos, `clientecedula`= cedula, `clientedireccion`= direccion, `clientetelefono`= telefono, `clientecorreo`= correo, `clientefechaafiliacion`= fecha, `clientetipomembresia`= tipomembresia WHERE `clienteid` = id$$

DROP PROCEDURE IF EXISTS `modificarDescuento`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `modificarDescuento` (IN `tarifa` FLOAT(7), IN `membresiaid` INT(11), IN `id` INT(11))   UPDATE `tbdescuento` SET `descuentotarifa`= tarifa, `descuentomembresiaid`= membresiaid WHERE `descuentoid` = id$$

DROP PROCEDURE IF EXISTS `modificarempleado`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `modificarempleado` (IN `id` INT, IN `cedula` INT, IN `nombre` VARCHAR(255), IN `apellidos` VARCHAR(255), IN `telefono` INT, IN `direccion` VARCHAR(255), IN `fechaingreso` DATE, IN `fechasalida` DATE, IN `estado` INT, IN `tipoempleado` INT)   UPDATE `tbempleado` SET `empleadocedula`= cedula,
`empleadonombre`= nombre,
`empleadoapellidos`= apellidos,
`empleadotelefono`= telefono,
`empleadodireccion`= direccion,
`empleadofechaingreso`= fechaingreso,
`empleadofechasalida`= fechasalida,
`empleadoestado`= estado,
`empleadotipoid`= tipoempleado
WHERE `empleadoid` = id$$

DROP PROCEDURE IF EXISTS `modificarMembresia`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `modificarMembresia` (IN `descripcion` VARCHAR(250), IN `id` INT)   UPDATE `tbmembresia` SET `membresiadescripcion`= descripcion WHERE `membresiaid` = id$$

DROP PROCEDURE IF EXISTS `modificarProducto`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `modificarProducto` (IN `p_id` INT, IN `p_nombre` VARCHAR(255), IN `p_precio` DECIMAL(10,2), IN `p_fecha_ingreso` DATE, IN `p_stock` INT(11), IN `p_estado` VARCHAR(20), IN `p_categoria_id` INT(11), IN `p_proveedor_id` INT(11))   BEGIN
  UPDATE tbproducto
  SET productonombre = p_nombre,
      productoprecio = p_precio,
      productofechaingreso = p_fecha_ingreso,
      productostock = p_stock,
      productoestado = p_estado,
      productocategoriaid = p_categoria_id,
      productoproveedorid = p_proveedor_id
  WHERE productoid = p_id;
END$$

DROP PROCEDURE IF EXISTS `modificarProveedor`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `modificarProveedor` (IN `id` INT(20), IN `nombre` VARCHAR(255), IN `direccion` VARCHAR(255), IN `correo` VARCHAR(255), IN `telefono` INT(20), IN `latitud` VARCHAR(255), IN `longitud` VARCHAR(255))  NO SQL BEGIN
	DECLARE error INT;
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
    	GET CURRENT DIAGNOSTICS CONDITION 1 error = MYSQL_ERRNO;
    	SELECT error AS MYSQL_ERROR;
    	ROLLBACK;
        RESIGNAL;
        
    END;
    
    START TRANSACTION;
    	UPDATE `tbproveedor`SET `proveedornombre`=nombre,`proveedordireccion`=direccion,`proveedorcorreo`=correo,`proveedortelefono`=telefono,`proveedorlat`=latitud,`proveedorlong`=longitud WHERE `proveedorid` = id;
        
    COMMIT;
END$$

DROP PROCEDURE IF EXISTS `modificarTipoUsuario`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `modificarTipoUsuario` (IN `descripcion` VARCHAR(255), IN `id` INT)   UPDATE `tbtipousuario` SET `tipodescripcion`= descripcion WHERE `tipoid` = id$$

DROP PROCEDURE IF EXISTS `modificarUsuario`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `modificarUsuario` (IN `pass` VARCHAR(255), IN `tipo` INT, IN `id` INT)   UPDATE `tbusuario` SET `usuariopassword`= pass, `usuariotipo`= tipo WHERE `usuarioid` = id$$

DROP PROCEDURE IF EXISTS `obtenerCategoriaNombre`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerCategoriaNombre` (IN `categoria_id` INT)   SELECT categorianombre FROM tbcategoria where categoriaid = categoria_id$$

DROP PROCEDURE IF EXISTS `obtenerCategoriaPorId`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerCategoriaPorId` (IN `id` INT)   BEGIN
    SELECT * FROM `tbcategoria` WHERE `categoriaid` = id;
END$$

DROP PROCEDURE IF EXISTS `obtenerCategorias`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerCategorias` ()   BEGIN
    SELECT * FROM `tbcategoria`;
END$$

DROP PROCEDURE IF EXISTS `obtenerClientes`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerClientes` ()   SELECT * FROM `tbcliente`$$

DROP PROCEDURE IF EXISTS `obtenerDatosSesion`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerDatosSesion` (IN `cedula` INT)   SELECT * FROM tbempleado INNER JOIN tbusuario ON tbempleado.empleadoid = tbusuario.usuarioempleadoid INNER JOIN tbtipousuario ON tbusuario.usuariotipo = tbtipousuario.tipoid WHERE tbempleado.empleadocedula = cedula$$

DROP PROCEDURE IF EXISTS `obtenerDescuentos`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerDescuentos` ()   SELECT * FROM `tbdescuento`$$

DROP PROCEDURE IF EXISTS `obtenerempleados`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerempleados` ()   SELECT * FROM `tbempleado` 
INNER JOIN tbtipoempleado ON tbempleado.empleadotipoid = tbtipoempleado.tipoid$$

DROP PROCEDURE IF EXISTS `obtenerInformacionSupermercado`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerInformacionSupermercado` ()   SELECT * FROM tbsupermercado$$

DROP PROCEDURE IF EXISTS `obtenerMembresiaNombre`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerMembresiaNombre` (IN `id` INT(11))   BEGIN
    SELECT * FROM `tbmembresia` WHERE `membresiaid` = id;
END$$

DROP PROCEDURE IF EXISTS `obtenerMembresias`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerMembresias` ()   SELECT * FROM `tbmembresia`$$

DROP PROCEDURE IF EXISTS `obtenerProveedores`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerProveedores` ()  NO SQL SELECT * FROM `tbproveedor`$$

DROP PROCEDURE IF EXISTS `obtenerProveedorNombre`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerProveedorNombre` (IN `proveedor_id` INT)   SELECT proveedornombre FROM tbproveedor WHERE proveedorid = proveedor_id$$

DROP PROCEDURE IF EXISTS `obtenerproveerdorid`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerproveerdorid` (IN `id` INT(20))  NO SQL SELECT * FROM `tbproveedor` WHERE `proveedorid`= id$$

DROP PROCEDURE IF EXISTS `obtenerTipoEmpleado`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerTipoEmpleado` ()   SELECT * FROM `tbtipoempleado`$$

DROP PROCEDURE IF EXISTS `obtenerTipoUsuario`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerTipoUsuario` ()   SELECT * FROM `tbtipousuario`$$

DROP PROCEDURE IF EXISTS `obtenerUsuarios`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerUsuarios` ()   SELECT * FROM `tbusuario` INNER JOIN tbempleado ON tbusuario.usuarioempleadoid = tbempleado.empleadoid INNER JOIN tbtipousuario ON tbusuario.usuariotipo = tbtipousuario.tipoid$$

DROP PROCEDURE IF EXISTS `verificarCuentaUsuario`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `verificarCuentaUsuario` (IN `cedula` INT)   SELECT * FROM `tbusuario` INNER JOIN tbempleado ON tbusuario.usuarioempleadoid = tbempleado.empleadoid WHERE tbempleado.empleadocedula = cedula$$

DROP PROCEDURE IF EXISTS `verificarEmpleado`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `verificarEmpleado` (IN `id` INT)   SELECT * FROM `tbusuario` WHERE `usuarioempleadoid` = id$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbcategoria`
--

DROP TABLE IF EXISTS `tbcategoria`;
CREATE TABLE `tbcategoria` (
  `categoriaid` int(11) NOT NULL,
  `categorianombre` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbcliente`
--

DROP TABLE IF EXISTS `tbcliente`;
CREATE TABLE `tbcliente` (
  `clienteid` int(11) NOT NULL,
  `clientenombre` varchar(255) NOT NULL,
  `clienteapellidos` varchar(255) NOT NULL,
  `clientecedula` int(11) NOT NULL,
  `clientedireccion` varchar(255) NOT NULL,
  `clientetelefono` int(11) NOT NULL,
  `clientecorreo` varchar(255) NOT NULL,
  `clientefechaafiliacion` date NOT NULL,
  `clientetipomembresia` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tbcliente`
--

INSERT INTO `tbcliente` (`clienteid`, `clientenombre`, `clienteapellidos`, `clientecedula`, `clientedireccion`, `clientetelefono`, `clientecorreo`, `clientefechaafiliacion`, `clientetipomembresia`) VALUES
(2, '33', '33', 33, '33', 33, '33', '2023-05-26', 2),
(3, '', '', 2, '', 0, '', '0000-00-00', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbdescuento`
--

DROP TABLE IF EXISTS `tbdescuento`;
CREATE TABLE `tbdescuento` (
  `descuentoid` int(11) NOT NULL,
  `descuentotarifa` float NOT NULL,
  `descuentomembresiaid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tbdescuento`
--

INSERT INTO `tbdescuento` (`descuentoid`, `descuentotarifa`, `descuentomembresiaid`) VALUES
(1, 0, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbempleado`
--

DROP TABLE IF EXISTS `tbempleado`;
CREATE TABLE `tbempleado` (
  `empleadoid` int(11) NOT NULL,
  `empleadocedula` int(11) NOT NULL,
  `empleadonombre` varchar(255) NOT NULL,
  `empleadoapellidos` varchar(255) NOT NULL,
  `empleadotelefono` int(11) NOT NULL,
  `empleadodireccion` varchar(255) NOT NULL,
  `empleadofechaingreso` date NOT NULL,
  `empleadofechasalida` date NOT NULL,
  `empleadoestado` int(11) NOT NULL,
  `empleadotipoid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tbempleado`
--

INSERT INTO `tbempleado` (`empleadoid`, `empleadocedula`, `empleadonombre`, `empleadoapellidos`, `empleadotelefono`, `empleadodireccion`, `empleadofechaingreso`, `empleadofechasalida`, `empleadoestado`, `empleadotipoid`) VALUES
(1, 22, '22', '22', 22, '22', '2023-04-05', '2023-04-11', 1, 1),
(2, 12345678, '5454', '45', 45, '454', '2023-04-19', '2023-04-20', 1, 1),
(3, 207380286, 'Axel', 'Andrade Villalobos', 86253019, 'Finca 2', '2023-05-06', '0000-00-00', 1, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbmembresia`
--

DROP TABLE IF EXISTS `tbmembresia`;
CREATE TABLE `tbmembresia` (
  `membresiaid` int(11) NOT NULL,
  `membresiadescripcion` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tbmembresia`
--

INSERT INTO `tbmembresia` (`membresiaid`, `membresiadescripcion`) VALUES
(2, 'dd333a'),
(3, 'asd'),
(4, 'sd');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbproducto`
--

DROP TABLE IF EXISTS `tbproducto`;
CREATE TABLE `tbproducto` (
  `productoid` int(11) NOT NULL,
  `productonombre` varchar(255) NOT NULL,
  `productoprecio` float(9,0) NOT NULL,
  `productofechaingreso` date NOT NULL,
  `productostock` int(11) NOT NULL,
  `productoestado` int(11) NOT NULL,
  `productocategoriaid` int(11) NOT NULL,
  `productoproveedorid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbproveedor`
--

DROP TABLE IF EXISTS `tbproveedor`;
CREATE TABLE `tbproveedor` (
  `proveedorid` int(11) NOT NULL,
  `proveedornombre` varchar(255) NOT NULL,
  `proveedordireccion` varchar(255) NOT NULL,
  `proveedortelefono` int(11) NOT NULL,
  `proveedorcorreo` varchar(255) NOT NULL,
  `proveedorlat` varchar(255) DEFAULT NULL,
  `proveedorlong` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tbproveedor`
--

INSERT INTO `tbproveedor` (`proveedorid`, `proveedornombre`, `proveedordireccion`, `proveedortelefono`, `proveedorcorreo`, `proveedorlat`, `proveedorlong`) VALUES
(3, 'AAA', '22', 22, '1@gmail.com', '10.345677', '-84.6650668'),
(4, '33', '1', 33, '1@gmail.com', '10.00236', '-84.11651'),
(5, '22', '33', 22222222, '1@gmail.com', '9.93333', '-84.08333');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbsupermercado`
--

DROP TABLE IF EXISTS `tbsupermercado`;
CREATE TABLE `tbsupermercado` (
  `supermercadoid` int(11) NOT NULL,
  `supermercadonombre` varchar(255) DEFAULT NULL,
  `supermercadotelefono` int(11) DEFAULT NULL,
  `supermercadocorreo` varchar(255) DEFAULT NULL,
  `supermercadodireccion` varchar(255) DEFAULT NULL,
  `supermercadologo` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tbsupermercado`
--

INSERT INTO `tbsupermercado` (`supermercadoid`, `supermercadonombre`, `supermercadotelefono`, `supermercadocorreo`, `supermercadodireccion`, `supermercadologo`) VALUES
(1, 'Supermercado', 88888888, 'supermercado@gmail.com', 'Finca 2', 'img/otros/353.png');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbtipoempleado`
--

DROP TABLE IF EXISTS `tbtipoempleado`;
CREATE TABLE `tbtipoempleado` (
  `tipoid` int(11) NOT NULL,
  `tipodescripcion` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tbtipoempleado`
--

INSERT INTO `tbtipoempleado` (`tipoid`, `tipodescripcion`) VALUES
(1, '334'),
(2, '44'),
(3, '44'),
(4, '221ad* Validar todos los campos y que no tengan caracteres especiales, cédula está aceptando letras');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbtipousuario`
--

DROP TABLE IF EXISTS `tbtipousuario`;
CREATE TABLE `tbtipousuario` (
  `tipoid` int(11) NOT NULL,
  `tipodescripcion` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tbtipousuario`
--

INSERT INTO `tbtipousuario` (`tipoid`, `tipodescripcion`) VALUES
(2, 'Empleado'),
(5, 'Administrador');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbusuario`
--

DROP TABLE IF EXISTS `tbusuario`;
CREATE TABLE `tbusuario` (
  `usuarioid` int(11) NOT NULL,
  `usuariopassword` varchar(255) NOT NULL,
  `usuarioempleadoid` int(11) NOT NULL,
  `usuariotipo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tbusuario`
--

INSERT INTO `tbusuario` (`usuarioid`, `usuariopassword`, `usuarioempleadoid`, `usuariotipo`) VALUES
(9, '$2y$10$cj9AiGyPMjYR5gSyipNJF.KT0MZcUpfuzOZN2ah7gsjw0gNNAlJcW', 2, 5),
(11, '$2y$10$bjBFI.HfwTm0Z6ln5TmlveDFYbDdErgAfQ9BR..ND2/suWlKEskja', 1, 2);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `tbcategoria`
--
ALTER TABLE `tbcategoria`
  ADD PRIMARY KEY (`categoriaid`);

--
-- Indices de la tabla `tbcliente`
--
ALTER TABLE `tbcliente`
  ADD PRIMARY KEY (`clienteid`);

--
-- Indices de la tabla `tbdescuento`
--
ALTER TABLE `tbdescuento`
  ADD PRIMARY KEY (`descuentoid`);

--
-- Indices de la tabla `tbempleado`
--
ALTER TABLE `tbempleado`
  ADD PRIMARY KEY (`empleadoid`);

--
-- Indices de la tabla `tbmembresia`
--
ALTER TABLE `tbmembresia`
  ADD PRIMARY KEY (`membresiaid`);

--
-- Indices de la tabla `tbproducto`
--
ALTER TABLE `tbproducto`
  ADD PRIMARY KEY (`productoid`);

--
-- Indices de la tabla `tbproveedor`
--
ALTER TABLE `tbproveedor`
  ADD PRIMARY KEY (`proveedorid`);

--
-- Indices de la tabla `tbsupermercado`
--
ALTER TABLE `tbsupermercado`
  ADD PRIMARY KEY (`supermercadoid`);

--
-- Indices de la tabla `tbtipoempleado`
--
ALTER TABLE `tbtipoempleado`
  ADD PRIMARY KEY (`tipoid`);

--
-- Indices de la tabla `tbtipousuario`
--
ALTER TABLE `tbtipousuario`
  ADD PRIMARY KEY (`tipoid`);

--
-- Indices de la tabla `tbusuario`
--
ALTER TABLE `tbusuario`
  ADD PRIMARY KEY (`usuarioid`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `tbcategoria`
--
ALTER TABLE `tbcategoria`
  MODIFY `categoriaid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT de la tabla `tbcliente`
--
ALTER TABLE `tbcliente`
  MODIFY `clienteid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `tbdescuento`
--
ALTER TABLE `tbdescuento`
  MODIFY `descuentoid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `tbempleado`
--
ALTER TABLE `tbempleado`
  MODIFY `empleadoid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `tbsupermercado`
--
ALTER TABLE `tbsupermercado`
  MODIFY `supermercadoid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `tbtipoempleado`
--
ALTER TABLE `tbtipoempleado`
  MODIFY `tipoid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `tbusuario`
--
ALTER TABLE `tbusuario`
  MODIFY `usuarioid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

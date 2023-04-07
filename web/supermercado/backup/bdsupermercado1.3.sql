-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 07-04-2023 a las 08:43:06
-- Versión del servidor: 10.4.21-MariaDB
-- Versión de PHP: 7.4.24

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

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `actualizarInformacionSupermercado` (IN `nombre` VARCHAR(255), IN `telefono` INT, IN `correo` VARCHAR(255), IN `direccion` VARCHAR(255), IN `logo` VARCHAR(255), IN `id` INT)  UPDATE `tbsupermercado` SET `supermercadonombre`= nombre,`supermercadotelefono`= telefono,`supermercadocorreo`=correo,
`supermercadodireccion`=direccion,`supermercadologo`= logo WHERE `supermercadoid` = id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `actualizarTipoEmpleado` (IN `id` INT, IN `descripcion` VARCHAR(255))  UPDATE `tbtipoempleado` SET `tipodescripcion`= descripcion WHERE `tipoid` = id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminarCategoria` (IN `id` INT)  BEGIN
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

CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminarMembresias` (IN `id` INT)  DELETE FROM `tbmembresia` WHERE `membresiaid` = id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminarProveedor` (IN `id` INT(20))  NO SQL
BEGIN
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

CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminarTipoEmpleado` (IN `id` INT)  DELETE FROM `tbtipoempleado` WHERE `tipoid` = id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminarTipoUsuario` (IN `id` INT)  DELETE FROM `tbtipousuario` WHERE `tipoid` = id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminarUsuario` (IN `id` INT)  DELETE FROM `tbusuario` WHERE `usuarioid` = id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarCategoria` (IN `id` INT, IN `nombre` VARCHAR(255))  BEGIN
    DECLARE error INT;
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        GET DIAGNOSTICS CONDITION 1 error = MYSQL_ERRNO;
        SELECT error AS MYSQL_ERROR;
        ROLLBACK;
        RESIGNAL;
    END;
    
    START TRANSACTION;
        INSERT INTO `tbcategoria`(`categoriaid`, `categorianombre`) VALUES (id, nombre);
    COMMIT;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarMembresia` (IN `membresiaid` INT, IN `membresiadescripcion` VARCHAR(250))  INSERT INTO `tbmembresia` (`membresiaid`, `membresiadescripcion`) VALUES (membresiaid,membresiadescripcion)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarProveedor` (IN `id` INT(20), IN `nombre` VARCHAR(255), IN `direccion` VARCHAR(255), IN `correo` VARCHAR(255), IN `telefono` INT(20))  NO SQL
BEGIN
	DECLARE error INT;
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
    	GET CURRENT DIAGNOSTICS CONDITION 1 error = MYSQL_ERRNO;
    	SELECT error AS MYSQL_ERROR;
    	ROLLBACK;
        RESIGNAL;
        
    END;
    
    START TRANSACTION;
    	INSERT INTO `tbproveedor`(`proveedorid`, `proveedornombre`, `proveedordireccion`, `proveedorcorreo`, `proveedortelefono`) VALUES (id,nombre,direccion,correo,telefono);
    COMMIT;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarTipoEmpleado` (IN `id` INT, IN `descripcion` VARCHAR(150))  INSERT INTO `tbtipoempleado`(`tipoid`, `tipodescripcion`) VALUES (id,descripcion)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarTipoUsuario` (IN `id` INT, IN `descripcion` VARCHAR(255))  INSERT INTO `tbtipousuario`(`tipoid`, `tipodescripcion`) VALUES (id,descripcion)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarUsuario` (IN `pass` VARCHAR(255), IN `empleado` INT, IN `tipo` INT)  INSERT INTO `tbusuario`(`usuariopassword`, `usuarioempleadoid`, `usuariotipo`) VALUES (pass,empleado,tipo)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `modificarCategoria` (IN `id` INT, IN `nombre` VARCHAR(255))  BEGIN
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

CREATE DEFINER=`root`@`localhost` PROCEDURE `modificarMembresia` (IN `descripcion` VARCHAR(250), IN `id` INT)  UPDATE `tbmembresia` SET `membresiadescripcion`= descripcion WHERE `membresiaid` = id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `modificarProveedor` (IN `id` INT(20), IN `nombre` VARCHAR(255), IN `direccion` VARCHAR(255), IN `correo` VARCHAR(255), IN `telefono` INT(20))  NO SQL
BEGIN
	DECLARE error INT;
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
    	GET CURRENT DIAGNOSTICS CONDITION 1 error = MYSQL_ERRNO;
    	SELECT error AS MYSQL_ERROR;
    	ROLLBACK;
        RESIGNAL;
        
    END;
    
    START TRANSACTION;
    	UPDATE `tbproveedor`SET `proveedornombre`=nombre,`proveedordireccion`=direccion,`proveedorcorreo`=correo,`proveedortelefono`=telefono WHERE `proveedorid` = id;
        
    COMMIT;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `modificarTipoUsuario` (IN `descripcion` VARCHAR(255), IN `id` INT)  UPDATE `tbtipousuario` SET `tipodescripcion`= descripcion WHERE `tipoid` = id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `modificarUsuario` (IN `pass` VARCHAR(255), IN `tipo` INT, IN `id` INT)  UPDATE `tbusuario` SET `usuariopassword`= pass, `usuariotipo`= tipo WHERE `usuarioid` = id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerCategoriaPorId` (IN `id` INT)  BEGIN
    SELECT * FROM `tbcategoria` WHERE `categoriaid` = id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerCategorias` ()  BEGIN
    SELECT * FROM `tbcategoria`;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerDatosSesion` (IN `cedula` INT)  SELECT * FROM tbempleado INNER JOIN tbusuario ON tbempleado.empleadoid = tbusuario.usuarioempleadoid INNER JOIN tbtipousuario ON tbusuario.usuariotipo = tbtipousuario.tipoid WHERE tbempleado.empleadocedula = cedula$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerInformacionSupermercado` ()  SELECT * FROM tbsupermercado$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerMembresias` ()  SELECT * FROM `tbmembresia`$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerProveedores` ()  NO SQL
SELECT * FROM `tbproveedor`$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerTipoEmpleado` ()  SELECT * FROM `tbtipoempleado`$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerTipoUsuario` ()  SELECT * FROM `tbtipousuario`$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerUsuarios` ()  SELECT * FROM `tbusuario` INNER JOIN tbempleado ON tbusuario.usuarioempleadoid = tbempleado.empleadoid INNER JOIN tbtipousuario ON tbusuario.usuariotipo = tbtipousuario.tipoid$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `verificarCuentaUsuario` (IN `cedula` INT)  SELECT * FROM `tbusuario` INNER JOIN tbempleado ON tbusuario.usuarioempleadoid = tbempleado.empleadoid WHERE tbempleado.empleadocedula = cedula$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `verificarEmpleado` (IN `id` INT)  SELECT * FROM `tbusuario` WHERE `usuarioempleadoid` = id$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbcategoria`
--

CREATE TABLE `tbcategoria` (
  `categoriaid` int(11) NOT NULL,
  `categorianombre` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbcliente`
--

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbdescuento`
--

CREATE TABLE `tbdescuento` (
  `descuentoid` int(11) NOT NULL,
  `descuentotarifa` float NOT NULL,
  `descuentomembresiaid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbempleado`
--

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tbempleado`
--

INSERT INTO `tbempleado` (`empleadoid`, `empleadocedula`, `empleadonombre`, `empleadoapellidos`, `empleadotelefono`, `empleadodireccion`, `empleadofechaingreso`, `empleadofechasalida`, `empleadoestado`, `empleadotipoid`) VALUES
(1, 22, '22', '22', 22, '22', '2023-04-05', '2023-04-11', 1, 1),
(2, 12345678, '5454', '45', 45, '454', '2023-04-19', '2023-04-20', 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbmembresia`
--

CREATE TABLE `tbmembresia` (
  `membresiaid` int(11) NOT NULL,
  `membresiadescripcion` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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

CREATE TABLE `tbproducto` (
  `productoid` int(11) NOT NULL,
  `productonombre` varchar(255) NOT NULL,
  `productoprecio` float NOT NULL,
  `productoimagen` varchar(255) NOT NULL,
  `productofechaingreso` date NOT NULL,
  `productostock` int(11) NOT NULL,
  `productoestado` int(11) NOT NULL,
  `productocategoriaid` int(11) NOT NULL,
  `productoproveedorid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbproveedor`
--

CREATE TABLE `tbproveedor` (
  `proveedorid` int(11) NOT NULL,
  `proveedornombre` varchar(255) NOT NULL,
  `proveedordireccion` varchar(255) NOT NULL,
  `proveedortelefono` int(11) NOT NULL,
  `proveedorcorreo` varchar(255) NOT NULL,
  `proveedorlat` varchar(255) DEFAULT NULL,
  `proveedorlong` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tbproveedor`
--

INSERT INTO `tbproveedor` (`proveedorid`, `proveedornombre`, `proveedordireccion`, `proveedortelefono`, `proveedorcorreo`, `proveedorlat`, `proveedorlong`) VALUES
(2, 'asd', 'da', 2, '22@gmail.com', '', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbsupermercado`
--

CREATE TABLE `tbsupermercado` (
  `supermercadoid` int(11) NOT NULL,
  `supermercadonombre` varchar(255) DEFAULT NULL,
  `supermercadotelefono` int(11) DEFAULT NULL,
  `supermercadocorreo` varchar(255) DEFAULT NULL,
  `supermercadodireccion` varchar(255) DEFAULT NULL,
  `supermercadologo` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tbsupermercado`
--

INSERT INTO `tbsupermercado` (`supermercadoid`, `supermercadonombre`, `supermercadotelefono`, `supermercadocorreo`, `supermercadodireccion`, `supermercadologo`) VALUES
(1, 'Supermercado', 88888888, 'supermercado@gmail.com', 'Finca 2', 'img/otros/353.png');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbtipoempleado`
--

CREATE TABLE `tbtipoempleado` (
  `tipoid` int(11) NOT NULL,
  `tipodescripcion` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tbtipoempleado`
--

INSERT INTO `tbtipoempleado` (`tipoid`, `tipodescripcion`) VALUES
(1, '334'),
(2, '44'),
(3, '44');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbtipousuario`
--

CREATE TABLE `tbtipousuario` (
  `tipoid` int(11) NOT NULL,
  `tipodescripcion` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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

CREATE TABLE `tbusuario` (
  `usuarioid` int(11) NOT NULL,
  `usuariopassword` varchar(255) NOT NULL,
  `usuarioempleadoid` int(11) NOT NULL,
  `usuariotipo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
  MODIFY `categoriaid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `tbcliente`
--
ALTER TABLE `tbcliente`
  MODIFY `clienteid` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tbdescuento`
--
ALTER TABLE `tbdescuento`
  MODIFY `descuentoid` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tbempleado`
--
ALTER TABLE `tbempleado`
  MODIFY `empleadoid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `tbproveedor`
--
ALTER TABLE `tbproveedor`
  MODIFY `proveedorid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

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
  MODIFY `usuarioid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

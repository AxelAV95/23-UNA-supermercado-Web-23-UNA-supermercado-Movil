-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 10-11-2022 a las 03:58:27
-- Versión del servidor: 10.4.11-MariaDB
-- Versión de PHP: 7.4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bdcomida`
--
CREATE DATABASE IF NOT EXISTS `bdcomida` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `bdcomida`;

DELIMITER $$
--
-- Procedimientos
--
DROP PROCEDURE IF EXISTS `actualizarEstadoOrden`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `actualizarEstadoOrden` (IN `id` INT, IN `estado` INT, IN `usuario` INT)  NO SQL
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
    	UPDATE `tborden` SET `ordenestado`= estado WHERE `ordenid` = id;
        INSERT INTO `tbusuarioorden`( `ordenid`, `usuarioid`) VALUES (id,usuario);
    COMMIT;
END$$

DROP PROCEDURE IF EXISTS `eliminarCategoria`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminarCategoria` (IN `id` INT)  NO SQL
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
    	DELETE FROM `tbcategoria` WHERE `categoriaid` = id;
    COMMIT;
END$$

DROP PROCEDURE IF EXISTS `eliminarOrden`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminarOrden` (IN `id` INT)  NO SQL
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
    	DELETE FROM `tborden` WHERE `ordenid` = id;
        DELETE FROM `tbdetalle` WHERE `detalleordenid` = id;
    COMMIT;
END$$

DROP PROCEDURE IF EXISTS `eliminarProducto`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminarProducto` (IN `id` INT)  NO SQL
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
    	DELETE FROM `tbproducto` WHERE `productoid` = id;
    COMMIT;
END$$

DROP PROCEDURE IF EXISTS `eliminarTipoUsuario`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminarTipoUsuario` (IN `id` INT)  NO SQL
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
    	DELETE FROM `tbtipousuario` WHERE `tipoid` =  id;
    COMMIT;
END$$

DROP PROCEDURE IF EXISTS `eliminarUsuario`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminarUsuario` (IN `id` INT)  NO SQL
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
    	DELETE FROM `tbusuario` WHERE `usuarioid` = id;
    COMMIT;
END$$

DROP PROCEDURE IF EXISTS `insertarCategoria`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarCategoria` (IN `id` INT, IN `descripcion` VARCHAR(255), IN `img` VARCHAR(500), IN `codigo` INT)  NO SQL
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
    	INSERT INTO `tbcategoria`(`categoriaid`, `categoriadescripcion`, `categoriaimg`,`categoriacodigo`) VALUES (id,descripcion,img,codigo);
    COMMIT;
END$$

DROP PROCEDURE IF EXISTS `insertarDetalle`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarDetalle` (IN `id` INT, IN `ordenid` INT, IN `productoid` INT, IN `precio` INT, IN `cantidad` INT)  NO SQL
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
    	INSERT INTO `tbdetalle`(`detalleid`, `detalleordenid`, `detalleproductoid`, `detalleprecio`, `detallecantidad`) VALUES (id, ordenid,productoid,precio,cantidad);
    COMMIT;
END$$

DROP PROCEDURE IF EXISTS `insertarNotificacion`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarNotificacion` (IN `orden` INT)  NO SQL
INSERT INTO tbnotificacion(notificacionordenid,notificacionestado) VALUES(orden,0)$$

DROP PROCEDURE IF EXISTS `insertarOrden`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarOrden` (IN `id` INT, IN `cliente` VARCHAR(255), IN `telefono` INT, IN `correo` VARCHAR(255), IN `metodo` INT, IN `fecha` DATE, IN `total` INT, IN `estado` INT)  NO SQL
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
    	INSERT INTO `tborden`(`ordenid`, `ordenclientenombre`, `ordenclientetelefono`, `ordenclientecorreo`, `ordenmetodo`, `ordenfecha`, `ordentotal`, `ordenestado`) VALUES (id,cliente,telefono,correo,metodo,fecha,total,estado);
    COMMIT;
END$$

DROP PROCEDURE IF EXISTS `insertarProducto`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarProducto` (IN `id` INT, IN `img` VARCHAR(255), IN `nombre` VARCHAR(255), IN `precio` INT, IN `estado` INT, IN `categoria` INT, IN `codigo` INT)  NO SQL
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
    	INSERT INTO `tbproducto`(`productoid`, `productoimg`, `productonombre`, `productoprecio`, `productoestado`, `productocategoriaid`, `productocodigo`) VALUES (id,img,nombre,precio,estado,categoria,codigo);
    COMMIT;
END$$

DROP PROCEDURE IF EXISTS `insertarTipoUsuario`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarTipoUsuario` (IN `id` INT, IN `descripcion` VARCHAR(255))  NO SQL
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
    	INSERT INTO `tbtipousuario`(`tipoid`, `tipodescripcion`) VALUES (id,descripcion);
    COMMIT;
END$$

DROP PROCEDURE IF EXISTS `insertarUsuario`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarUsuario` (IN `id` INT, IN `nombre` VARCHAR(255), IN `telefono` INT, IN `correo` VARCHAR(255), IN `password` VARCHAR(255), IN `tipo` INT)  NO SQL
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
    	INSERT INTO `tbusuario`(`usuarioid`, `usuarionombre`, `usuariotelefono`, `usuariocorreo`, `usuariopassword`, `tipoid`) VALUES (id,nombre,telefono,correo,password,tipo);
    COMMIT;
END$$

DROP PROCEDURE IF EXISTS `insertarUsuarioOrden`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarUsuarioOrden` (IN `idorden` INT, IN `idusuario` INT)  NO SQL
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
    	INSERT INTO `tbusuarioorden`(`ordenid`, `usuarioid`) VALUES (idorden,idusuario);
    COMMIT;
END$$

DROP PROCEDURE IF EXISTS `modificarCategoria`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `modificarCategoria` (IN `id` INT, IN `descripcion` VARCHAR(255), IN `img` VARCHAR(500))  NO SQL
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
    	UPDATE `tbcategoria` SET `categoriadescripcion`=descripcion,`categoriaimg`=img WHERE `categoriaid` = id;
    COMMIT;
END$$

DROP PROCEDURE IF EXISTS `modificarNotificacion`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `modificarNotificacion` ()  NO SQL
UPDATE `tbnotificacion` SET `notificacionestado`= 1 WHERE `notificacionestado` = 0$$

DROP PROCEDURE IF EXISTS `modificarProducto`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `modificarProducto` (IN `id` INT, IN `img` VARCHAR(255), IN `nombre` VARCHAR(255), IN `precio` INT, IN `estado` INT, IN `categoria` INT, IN `codigo` INT)  NO SQL
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
    	UPDATE `tbproducto` SET `productoimg`= img,`productonombre`=nombre,`productoprecio`= precio,`productoestado`=estado,`productocategoriaid`=categoria,`productocodigo`=codigo WHERE `productoid` = id;
    COMMIT;
END$$

DROP PROCEDURE IF EXISTS `modificarTipoUsuario`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `modificarTipoUsuario` (IN `descripcion` VARCHAR(255), IN `id` INT)  NO SQL
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
    	UPDATE `tbtipousuario` SET `tipodescripcion`=descripcion WHERE `tipoid` = id;
    COMMIT;
END$$

DROP PROCEDURE IF EXISTS `modificarUsuario`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `modificarUsuario` (IN `id` INT, IN `nombre` VARCHAR(255), IN `telefono` INT, IN `correo` VARCHAR(255), IN `password` VARCHAR(255), IN `tipo` INT)  NO SQL
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
    	UPDATE `tbusuario` SET `usuarionombre`=nombre,`usuariotelefono`=telefono,`usuariocorreo`=correo,`usuariopassword`=password,`tipoid`=tipo WHERE `usuarioid` = id;
        
    COMMIT;
END$$

DROP PROCEDURE IF EXISTS `obtenerCategoriaDescripcion`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerCategoriaDescripcion` (IN `id` INT)  NO SQL
SELECT categoriadescripcion FROM tbcategoria WHERE categoriaid = id$$

DROP PROCEDURE IF EXISTS `obtenerCategorias`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerCategorias` ()  NO SQL
SELECT * FROM `vistacategorias`$$

DROP PROCEDURE IF EXISTS `obtenerDatosUsuario`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerDatosUsuario` (IN `correo` VARCHAR(255), IN `password` VARCHAR(255))  NO SQL
SELECT * FROM `tbusuario` WHERE `usuariocorreo`  = correo AND `usuariopassword` = password$$

DROP PROCEDURE IF EXISTS `obtenerDetallesOrden`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerDetallesOrden` (IN `ordenid` INT)  NO SQL
SELECT `detalleid`,`detalleordenid`, `productonombre`,`detalleprecio`,`detallecantidad` FROM tbdetalle INNER JOIN tborden ON tbdetalle.detalleordenid = tborden.ordenid INNER JOIN tbproducto ON tbproducto.productoid = tbdetalle.detalleproductoid WHERE tborden.ordenid = ordenid$$

DROP PROCEDURE IF EXISTS `obtenerHistorialProducto`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerHistorialProducto` ()  NO SQL
SELECT * FROM `verHistorialProductos`$$

DROP PROCEDURE IF EXISTS `obtenerOrdenes`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerOrdenes` ()  NO SQL
SELECT * FROM `verordenes`$$

DROP PROCEDURE IF EXISTS `obtenerPaginasProducto`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerPaginasProducto` (IN `inicio` INT, IN `limite` INT)  NO SQL
SELECT * FROM tbproducto LIMIT inicio, limite$$

DROP PROCEDURE IF EXISTS `ObtenerPaginasProductoCategoria`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ObtenerPaginasProductoCategoria` (IN `inicio` INT, IN `limite` INT, IN `categoria` INT)  NO SQL
SELECT * FROM tbproducto WHERE productocategoriaid = categoria LIMIT inicio, limite$$

DROP PROCEDURE IF EXISTS `obtenerProductos`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerProductos` ()  NO SQL
SELECT * FROM `verProductos`$$

DROP PROCEDURE IF EXISTS `obtenerTipoUsuario`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerTipoUsuario` ()  NO SQL
SELECT * FROM `vertipos`$$

DROP PROCEDURE IF EXISTS `obtenerTotalGanancias`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerTotalGanancias` ()  NO SQL
SELECT SUM(`ordentotal`) as totalganancias FROM `tborden` WHERE tborden.ordenestado = 1$$

DROP PROCEDURE IF EXISTS `obtenerTotalOrdenes`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerTotalOrdenes` ()  NO SQL
SELECT COUNT(*) totalordenes FROM `tborden`$$

DROP PROCEDURE IF EXISTS `obtenerTotalProductos`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerTotalProductos` ()  NO SQL
SELECT COUNT(*) as totalproductos FROM `tbproducto`$$

DROP PROCEDURE IF EXISTS `obtenerUsuarios`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerUsuarios` ()  NO SQL
SELECT * FROM `verusuarios`$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbcategoria`
--

DROP TABLE IF EXISTS `tbcategoria`;
CREATE TABLE `tbcategoria` (
  `categoriaid` int(11) NOT NULL,
  `categoriadescripcion` varchar(500) NOT NULL,
  `categoriaimg` varchar(500) NOT NULL,
  `categoriacodigo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tbcategoria`
--

INSERT INTO `tbcategoria` (`categoriaid`, `categoriadescripcion`, `categoriaimg`, `categoriacodigo`) VALUES
(1, 'Bebidas', 'img/categorias/12/892.png', 12),
(2, 'Pizza', 'img/categorias/13/271.png', 13),
(3, 'Café', 'img/categorias/14/872.png', 14),
(4, 'Hamburguesas', 'img/categorias/15/348.png', 15),
(5, 'Sandwich', 'img/categorias/16/731.png', 16),
(6, 'Papas fritas', 'img/categorias/17/581.png', 17),
(7, 'Ensaladas', 'img/categorias/18/592.png', 18);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbdetalle`
--

DROP TABLE IF EXISTS `tbdetalle`;
CREATE TABLE `tbdetalle` (
  `detalleid` int(11) NOT NULL,
  `detalleordenid` int(11) NOT NULL,
  `detalleproductoid` int(11) NOT NULL,
  `detalleprecio` int(11) NOT NULL,
  `detallecantidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tbdetalle`
--

INSERT INTO `tbdetalle` (`detalleid`, `detalleordenid`, `detalleproductoid`, `detalleprecio`, `detallecantidad`) VALUES
(1, 1, 1, 1500, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbhistorialproducto`
--

DROP TABLE IF EXISTS `tbhistorialproducto`;
CREATE TABLE `tbhistorialproducto` (
  `historialid` int(11) NOT NULL,
  `historialfecha` date NOT NULL,
  `historialusuario` varchar(255) NOT NULL,
  `historialproductoid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbnotificacion`
--

DROP TABLE IF EXISTS `tbnotificacion`;
CREATE TABLE `tbnotificacion` (
  `notificacionid` int(11) NOT NULL,
  `notificacionordenid` int(11) NOT NULL,
  `notificacionestado` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tborden`
--

DROP TABLE IF EXISTS `tborden`;
CREATE TABLE `tborden` (
  `ordenid` int(11) NOT NULL,
  `ordenclientenombre` varchar(255) NOT NULL,
  `ordenclientetelefono` int(11) NOT NULL,
  `ordenclientecorreo` varchar(255) NOT NULL,
  `ordenmetodo` int(11) NOT NULL,
  `ordenfecha` date NOT NULL,
  `ordentotal` decimal(60,2) NOT NULL,
  `ordenestado` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tborden`
--

INSERT INTO `tborden` (`ordenid`, `ordenclientenombre`, `ordenclientetelefono`, `ordenclientecorreo`, `ordenmetodo`, `ordenfecha`, `ordentotal`, `ordenestado`) VALUES
(1, 'Axel Andrade Villalobos', 86253019, 'villalobos.axel@yahoo.es', 1, '2022-11-10', '3000.00', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbproducto`
--

DROP TABLE IF EXISTS `tbproducto`;
CREATE TABLE `tbproducto` (
  `productoid` int(11) NOT NULL,
  `productoimg` varchar(500) NOT NULL,
  `productonombre` varchar(255) NOT NULL,
  `productoprecio` int(11) NOT NULL,
  `productoestado` int(11) NOT NULL,
  `productocategoriaid` int(11) NOT NULL,
  `productocodigo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tbproducto`
--

INSERT INTO `tbproducto` (`productoid`, `productoimg`, `productonombre`, `productoprecio`, `productoestado`, `productocategoriaid`, `productocodigo`) VALUES
(1, 'img/productos/12/331.jpg', 'Hamburguesa sencilla', 1500, 1, 4, 12);

--
-- Disparadores `tbproducto`
--
DROP TRIGGER IF EXISTS `insertarHistorialProducto`;
DELIMITER $$
CREATE TRIGGER `insertarHistorialProducto` AFTER INSERT ON `tbproducto` FOR EACH ROW INSERT INTO tbhistorialproducto (historialfecha,historialusuario,historialproductoid) VALUES(NOW(), CURRENT_USER, NEW.productoid)
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbtipousuario`
--

DROP TABLE IF EXISTS `tbtipousuario`;
CREATE TABLE `tbtipousuario` (
  `tipoid` int(11) NOT NULL,
  `tipodescripcion` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tbtipousuario`
--

INSERT INTO `tbtipousuario` (`tipoid`, `tipodescripcion`) VALUES
(1, 'Administrador'),
(2, 'Empleado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbusuario`
--

DROP TABLE IF EXISTS `tbusuario`;
CREATE TABLE `tbusuario` (
  `usuarioid` int(11) NOT NULL,
  `usuarionombre` varchar(255) NOT NULL,
  `usuariotelefono` int(11) NOT NULL,
  `usuariocorreo` varchar(255) NOT NULL,
  `usuariopassword` varchar(255) NOT NULL,
  `tipoid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tbusuario`
--

INSERT INTO `tbusuario` (`usuarioid`, `usuarionombre`, `usuariotelefono`, `usuariocorreo`, `usuariopassword`, `tipoid`) VALUES
(1, 'Administrador', 88888888, 'admin@gmail.com', '12345', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbusuarioorden`
--

DROP TABLE IF EXISTS `tbusuarioorden`;
CREATE TABLE `tbusuarioorden` (
  `usuarioordenid` int(11) NOT NULL,
  `ordenid` int(11) NOT NULL,
  `usuarioid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tbusuarioorden`
--

INSERT INTO `tbusuarioorden` (`usuarioordenid`, `ordenid`, `usuarioid`) VALUES
(1, 2, 1);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `verhistorialproductos`
-- (Véase abajo para la vista actual)
--
DROP VIEW IF EXISTS `verhistorialproductos`;
CREATE TABLE `verhistorialproductos` (
`historialid` int(11)
,`historialfecha` date
,`historialusuario` varchar(255)
,`productonombre` varchar(255)
);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `verordenes`
-- (Véase abajo para la vista actual)
--
DROP VIEW IF EXISTS `verordenes`;
CREATE TABLE `verordenes` (
`ordenid` int(11)
,`ordenclientenombre` varchar(255)
,`ordenclientetelefono` int(11)
,`ordenclientecorreo` varchar(255)
,`ordenmetodo` int(11)
,`ordenfecha` date
,`ordentotal` decimal(60,2)
,`ordenestado` int(4)
);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `verproductos`
-- (Véase abajo para la vista actual)
--
DROP VIEW IF EXISTS `verproductos`;
CREATE TABLE `verproductos` (
`productoid` int(11)
,`productoimg` varchar(500)
,`productonombre` varchar(255)
,`productoprecio` int(11)
,`productoestado` int(11)
,`productocategoriaid` int(11)
,`productocodigo` int(11)
);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `vertipos`
-- (Véase abajo para la vista actual)
--
DROP VIEW IF EXISTS `vertipos`;
CREATE TABLE `vertipos` (
`tipoid` int(11)
,`tipodescripcion` varchar(255)
);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `verusuarios`
-- (Véase abajo para la vista actual)
--
DROP VIEW IF EXISTS `verusuarios`;
CREATE TABLE `verusuarios` (
`usuarioid` int(11)
,`usuarionombre` varchar(255)
,`usuariotelefono` int(11)
,`usuariocorreo` varchar(255)
,`usuariopassword` varchar(255)
,`tipoid` int(11)
);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `vistacategorias`
-- (Véase abajo para la vista actual)
--
DROP VIEW IF EXISTS `vistacategorias`;
CREATE TABLE `vistacategorias` (
`categoriaid` int(11)
,`categoriadescripcion` varchar(500)
,`categoriaimg` varchar(500)
,`categoriacodigo` int(11)
);

-- --------------------------------------------------------

--
-- Estructura para la vista `verhistorialproductos`
--
DROP TABLE IF EXISTS `verhistorialproductos`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `verhistorialproductos`  AS  select `tbhistorialproducto`.`historialid` AS `historialid`,`tbhistorialproducto`.`historialfecha` AS `historialfecha`,`tbhistorialproducto`.`historialusuario` AS `historialusuario`,`tbproducto`.`productonombre` AS `productonombre` from (`tbhistorialproducto` join `tbproducto` on(`tbhistorialproducto`.`historialproductoid` = `tbproducto`.`productoid`)) ;

-- --------------------------------------------------------

--
-- Estructura para la vista `verordenes`
--
DROP TABLE IF EXISTS `verordenes`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `verordenes`  AS  select `tborden`.`ordenid` AS `ordenid`,`tborden`.`ordenclientenombre` AS `ordenclientenombre`,`tborden`.`ordenclientetelefono` AS `ordenclientetelefono`,`tborden`.`ordenclientecorreo` AS `ordenclientecorreo`,`tborden`.`ordenmetodo` AS `ordenmetodo`,`tborden`.`ordenfecha` AS `ordenfecha`,`tborden`.`ordentotal` AS `ordentotal`,`tborden`.`ordenestado` AS `ordenestado` from `tborden` order by `tborden`.`ordenestado` = 2 desc ;

-- --------------------------------------------------------

--
-- Estructura para la vista `verproductos`
--
DROP TABLE IF EXISTS `verproductos`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `verproductos`  AS  select `tbproducto`.`productoid` AS `productoid`,`tbproducto`.`productoimg` AS `productoimg`,`tbproducto`.`productonombre` AS `productonombre`,`tbproducto`.`productoprecio` AS `productoprecio`,`tbproducto`.`productoestado` AS `productoestado`,`tbproducto`.`productocategoriaid` AS `productocategoriaid`,`tbproducto`.`productocodigo` AS `productocodigo` from `tbproducto` ;

-- --------------------------------------------------------

--
-- Estructura para la vista `vertipos`
--
DROP TABLE IF EXISTS `vertipos`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vertipos`  AS  select `tbtipousuario`.`tipoid` AS `tipoid`,`tbtipousuario`.`tipodescripcion` AS `tipodescripcion` from `tbtipousuario` ;

-- --------------------------------------------------------

--
-- Estructura para la vista `verusuarios`
--
DROP TABLE IF EXISTS `verusuarios`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `verusuarios`  AS  select `tbusuario`.`usuarioid` AS `usuarioid`,`tbusuario`.`usuarionombre` AS `usuarionombre`,`tbusuario`.`usuariotelefono` AS `usuariotelefono`,`tbusuario`.`usuariocorreo` AS `usuariocorreo`,`tbusuario`.`usuariopassword` AS `usuariopassword`,`tbusuario`.`tipoid` AS `tipoid` from `tbusuario` ;

-- --------------------------------------------------------

--
-- Estructura para la vista `vistacategorias`
--
DROP TABLE IF EXISTS `vistacategorias`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vistacategorias`  AS  select `tbcategoria`.`categoriaid` AS `categoriaid`,`tbcategoria`.`categoriadescripcion` AS `categoriadescripcion`,`tbcategoria`.`categoriaimg` AS `categoriaimg`,`tbcategoria`.`categoriacodigo` AS `categoriacodigo` from `tbcategoria` ;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `tbcategoria`
--
ALTER TABLE `tbcategoria`
  ADD PRIMARY KEY (`categoriaid`);

--
-- Indices de la tabla `tbdetalle`
--
ALTER TABLE `tbdetalle`
  ADD PRIMARY KEY (`detalleid`);

--
-- Indices de la tabla `tbhistorialproducto`
--
ALTER TABLE `tbhistorialproducto`
  ADD PRIMARY KEY (`historialid`);

--
-- Indices de la tabla `tbnotificacion`
--
ALTER TABLE `tbnotificacion`
  ADD PRIMARY KEY (`notificacionid`);

--
-- Indices de la tabla `tborden`
--
ALTER TABLE `tborden`
  ADD PRIMARY KEY (`ordenid`);

--
-- Indices de la tabla `tbproducto`
--
ALTER TABLE `tbproducto`
  ADD PRIMARY KEY (`productoid`);

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
-- Indices de la tabla `tbusuarioorden`
--
ALTER TABLE `tbusuarioorden`
  ADD PRIMARY KEY (`usuarioordenid`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `tbhistorialproducto`
--
ALTER TABLE `tbhistorialproducto`
  MODIFY `historialid` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tbnotificacion`
--
ALTER TABLE `tbnotificacion`
  MODIFY `notificacionid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `tbusuarioorden`
--
ALTER TABLE `tbusuarioorden`
  MODIFY `usuarioordenid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

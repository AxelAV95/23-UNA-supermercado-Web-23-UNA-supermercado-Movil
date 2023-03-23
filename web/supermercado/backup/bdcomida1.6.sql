-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 05-11-2022 a las 03:36:09
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

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `actualizarEstadoOrden` (IN `id` INT, IN `estado` INT)  NO SQL
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
    COMMIT;
END$$

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

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarProducto` (IN `id` INT, IN `img` VARCHAR(255), IN `nombre` INT(255), IN `precio` INT, IN `estado` INT, IN `categoria` INT, IN `codigo` INT)  NO SQL
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

CREATE DEFINER=`root`@`localhost` PROCEDURE `modificarProducto` ()  NO SQL
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
    	UPDATE `tbproducto` SET `productoimg`= img,`productonombre`=nombre,`productoprecio`= precio,`productocategoriaid`=categoria,`productocodigo`=codigo WHERE `productoid` = id;
    COMMIT;
END$$

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

CREATE DEFINER=`root`@`localhost` PROCEDURE `modificarUsuario` (IN `nombre` VARCHAR(255), IN `telefono` INT, IN `correo` VARCHAR(255), IN `password` VARCHAR(255), IN `tipo` INT, IN `id` INT)  NO SQL
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

CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerCategorias` ()  NO SQL
SELECT * FROM `tbcategoria`$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerDetallesOrden` (IN `ordenid` INT)  NO SQL
SELECT `detalleid`,`detalleordenid`, `productonombre`,`detalleprecio`,`detallecantidad` FROM tbdetalle INNER JOIN tborden ON tbdetalle.detalleordenid = tborden.ordenid INNER JOIN tbproducto ON tbproducto.productoid = tbdetalle.detalleproductoid WHERE tborden.ordenid = ordenid$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerOrdenes` ()  NO SQL
SELECT * FROM `tborden` ORDER BY `ordenestado` = 2 DESC$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerPaginasProducto` (IN `inicio` INT, IN `limite` INT)  NO SQL
SELECT * FROM tbproducto LIMIT inicio, limite$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `ObtenerPaginasProductoCategoria` (IN `inicio` INT, IN `limite` INT, IN `categoria` INT)  NO SQL
SELECT * FROM tbproducto WHERE productocategoriaid = categoria LIMIT inicio, limite$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerProductos` ()  NO SQL
SELECT * FROM `tbusuario`$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerTipoUsuario` ()  NO SQL
SELECT * FROM `tbtipousuario`$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerTotalGanancias` ()  NO SQL
SELECT SUM(`ordentotal`) as totalganancias FROM `tborden`$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerTotalOrdenes` ()  NO SQL
SELECT COUNT(*) totalordenes FROM `tborden`$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerTotalProductos` ()  NO SQL
SELECT COUNT(*) as totalproductos FROM `tbproducto`$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerUsuarios` ()  NO SQL
SELECT * FROM tbusuario$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbcategoria`
--

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
(1, 5, 1, 1200, 1),
(2, 6, 1, 1200, 1),
(3, 7, 1, 1200, 4),
(4, 8, 2, 1200, 2),
(5, 9, 2, 1200, 1),
(6, 10, 1, 1200, 5),
(7, 11, 2, 1200, 1),
(8, 12, 2, 1200, 1),
(9, 13, 4, 1200, 1),
(10, 14, 3, 1200, 1),
(11, 15, 3, 1200, 2),
(12, 16, 7, 1200, 3),
(13, 17, 7, 1200, 2),
(14, 18, 4, 1200, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbhistorialproducto`
--

CREATE TABLE `tbhistorialproducto` (
  `historialid` int(11) NOT NULL,
  `historialfecha` date NOT NULL,
  `historialusuario` varchar(255) NOT NULL,
  `historialproductoid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tborden`
--

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
(1, 'Axel Andrade Villalobos', 86252019, 'villalobos.axel@yahoo.es', 2, '2022-10-26', '1200.00', 1),
(2, 'Axel Andrade Villalobos', 86253019, 'villalobos.axel@yahoo.es', 2, '2022-10-26', '1200.00', 2),
(3, 'Axel Andrade Villalobos', 22321, 'villalobos.axel@yahoo.es', 1, '2022-10-26', '1200.00', 2),
(4, 'Axel Andrade Villalobos', 3423, 'villalobos.axel@yahoo.es', 1, '2022-10-26', '1200.00', 2),
(5, 'Axel Andrade Villalobos', 32, 'villalobos.axel@yahoo.es', 2, '2022-10-27', '1200.00', 2),
(6, 'Axel Andrade Villalobos', 4324, 'villalobos.axel@yahoo.es', 2, '2022-10-27', '4800.00', 2),
(7, 'Axel Andrade Villalobos', 22, 'villalobos.axel@yahoo.es', 2, '2022-10-29', '2400.00', 2),
(8, 'Axel Andrade Villalobos', 23, 'villalobos.axel@yahoo.es', 1, '2022-10-29', '1200.00', 2),
(9, 'Axel Andrade Villalobos', 123, 'villalobos.axel@yahoo.es', 2, '2022-10-29', '6000.00', 2),
(10, 'Axel Andrade Villalobos', 123, 'villalobos.axel@yahoo.es', 2, '2022-11-01', '1200.00', 2),
(11, 'Axel Andrade Villalobos', 34243, 'villalobos.axel@yahoo.es', 1, '2022-11-01', '1200.00', 2),
(12, 'dasdsa', 111, 'villalobos.axel@yahoo.es', 1, '2022-11-01', '1200.00', 2),
(13, 'dadsa3', 22, 'villalobos.axel@yahoo.es', 1, '2022-11-01', '1200.00', 2),
(14, '12321321', 233, 'villalobos.axel@yahoo.es', 1, '2022-11-01', '2400.00', 1),
(15, 'Axel Andrade Villalobos1', 23, 'villalobos.axel@yahoo.es', 1, '2022-11-01', '3600.00', 2),
(16, 'test', 23423, 'villalobos.axel@yahoo.es', 2, '2022-11-01', '2400.00', 2),
(17, 'Axel Andrade Villalobos', 524, 'villalobos.axel@yahoo.es', 2, '2022-11-04', '2400.00', 2),
(18, 'Axel Andrade Villalobos', 321, 'villalobos.axel@yahoo.es', 2, '2022-11-04', '1200.00', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbproducto`
--

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
(1, 'https://assets.stickpng.com/thumbs/5882488ae81acb96424ffaaf.png\r\n', 'Hamburguesa asd asdasd', 1200, 1, 0, 0),
(2, 'https://assets.stickpng.com/thumbs/5882488ae81acb96424ffaaf.png', 'Hamburguesa', 1200, 1, 0, 0),
(3, 'https://assets.stickpng.com/thumbs/5882488ae81acb96424ffaaf.png', 'Hamburguesa', 1200, 1, 0, 0),
(4, 'https://assets.stickpng.com/thumbs/5882488ae81acb96424ffaaf.png', 'Hamburguesa', 1200, 1, 0, 0),
(5, 'https://assets.stickpng.com/thumbs/5882488ae81acb96424ffaaf.png', 'Hamburguesa', 1200, 1, 4, 0),
(6, 'https://assets.stickpng.com/thumbs/5882488ae81acb96424ffaaf.png', 'Hamburguesa', 1200, 1, 0, 0),
(7, 'https://assets.stickpng.com/thumbs/5882488ae81acb96424ffaaf.png', 'Hamburguesa', 1200, 1, 0, 0),
(8, 'https://assets.stickpng.com/thumbs/5882488ae81acb96424ffaaf.png', 'Hamburguesa', 1200, 1, 0, 0),
(9, 'https://assets.stickpng.com/thumbs/5882488ae81acb96424ffaaf.png', 'Hamburguesa', 1200, 1, 0, 0),
(10, 'https://assets.stickpng.com/thumbs/5882488ae81acb96424ffaaf.png', 'Hamburguesa', 1200, 1, 0, 0),
(11, 'https://assets.stickpng.com/thumbs/5882488ae81acb96424ffaaf.png', 'Hamburguesa', 1200, 1, 0, 0),
(12, 'https://assets.stickpng.com/thumbs/5882488ae81acb96424ffaaf.png', 'Hamburguesa', 1200, 1, 0, 0),
(13, 'https://assets.stickpng.com/thumbs/5882488ae81acb96424ffaaf.png', 'Hamburguesa', 1200, 1, 0, 0),
(14, 'https://assets.stickpng.com/thumbs/5882488ae81acb96424ffaaf.png', 'Hamburguesa', 1200, 1, 0, 0);

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
(1, 'Administrador'),
(2, 'Empleado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbusuario`
--

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
(1, 'Administrador', 88888888, 'admin@gmail.com', '12345', 1),
(2, 'Axel', 33333, 'villalobos.axel@yahoo.es', '12345', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbusuarioorden`
--

CREATE TABLE `tbusuarioorden` (
  `usuarioordenid` int(11) NOT NULL,
  `ordenid` int(11) NOT NULL,
  `usuarioid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
-- AUTO_INCREMENT de la tabla `tbusuarioorden`
--
ALTER TABLE `tbusuarioorden`
  MODIFY `usuarioordenid` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

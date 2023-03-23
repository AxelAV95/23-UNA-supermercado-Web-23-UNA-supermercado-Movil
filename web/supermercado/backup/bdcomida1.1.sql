-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 29-10-2022 a las 01:06:05
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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbcategoria`
--

CREATE TABLE `tbcategoria` (
  `categoriaid` int(11) NOT NULL,
  `categoriadescripcion` varchar(500) NOT NULL,
  `categoriaimg` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
(3, 7, 1, 1200, 4);

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
  `ordenestado` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tborden`
--

INSERT INTO `tborden` (`ordenid`, `ordenclientenombre`, `ordenclientetelefono`, `ordenclientecorreo`, `ordenmetodo`, `ordenfecha`, `ordentotal`, `ordenestado`) VALUES
(1, 'Axel Andrade Villalobos', 86252019, 'villalobos.axel@yahoo.es', 2, '2022-10-26', '1200.00', 2),
(2, 'Axel Andrade Villalobos', 86253019, 'villalobos.axel@yahoo.es', 2, '2022-10-26', '1200.00', 2),
(3, 'Axel Andrade Villalobos', 22321, 'villalobos.axel@yahoo.es', 1, '2022-10-26', '1200.00', 2),
(4, 'Axel Andrade Villalobos', 3423, 'villalobos.axel@yahoo.es', 1, '2022-10-26', '1200.00', 2),
(5, 'Axel Andrade Villalobos', 32, 'villalobos.axel@yahoo.es', 2, '2022-10-27', '1200.00', 2),
(6, 'Axel Andrade Villalobos', 4324, 'villalobos.axel@yahoo.es', 2, '2022-10-27', '4800.00', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbproducto`
--

CREATE TABLE `tbproducto` (
  `productoid` int(11) NOT NULL,
  `productoimg` varchar(500) NOT NULL,
  `productonombre` varchar(255) NOT NULL,
  `productoprecio` int(11) NOT NULL,
  `productoestado` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tbproducto`
--

INSERT INTO `tbproducto` (`productoid`, `productoimg`, `productonombre`, `productoprecio`, `productoestado`) VALUES
(1, 'https://assets.stickpng.com/thumbs/5882488ae81acb96424ffaaf.png\r\n', 'Hamburguesa asd asdasd', 1200, 1),
(2, 'https://assets.stickpng.com/thumbs/5882488ae81acb96424ffaaf.png', 'Hamburguesa', 1200, 1),
(3, 'https://assets.stickpng.com/thumbs/5882488ae81acb96424ffaaf.png', 'Hamburguesa', 1200, 1),
(4, 'https://assets.stickpng.com/thumbs/5882488ae81acb96424ffaaf.png', 'Hamburguesa', 1200, 1),
(5, 'https://assets.stickpng.com/thumbs/5882488ae81acb96424ffaaf.png', 'Hamburguesa', 1200, 1),
(6, 'https://assets.stickpng.com/thumbs/5882488ae81acb96424ffaaf.png', 'Hamburguesa', 1200, 1),
(7, 'https://assets.stickpng.com/thumbs/5882488ae81acb96424ffaaf.png', 'Hamburguesa', 1200, 1),
(8, 'https://assets.stickpng.com/thumbs/5882488ae81acb96424ffaaf.png', 'Hamburguesa', 1200, 1),
(9, 'https://assets.stickpng.com/thumbs/5882488ae81acb96424ffaaf.png', 'Hamburguesa', 1200, 1),
(10, 'https://assets.stickpng.com/thumbs/5882488ae81acb96424ffaaf.png', 'Hamburguesa', 1200, 1),
(11, 'https://assets.stickpng.com/thumbs/5882488ae81acb96424ffaaf.png', 'Hamburguesa', 1200, 1),
(12, 'https://assets.stickpng.com/thumbs/5882488ae81acb96424ffaaf.png', 'Hamburguesa', 1200, 1),
(13, 'https://assets.stickpng.com/thumbs/5882488ae81acb96424ffaaf.png', 'Hamburguesa', 1200, 1),
(14, 'https://assets.stickpng.com/thumbs/5882488ae81acb96424ffaaf.png', 'Hamburguesa', 1200, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbtipousuario`
--

CREATE TABLE `tbtipousuario` (
  `tipoid` int(11) NOT NULL,
  `tipodescripcion` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

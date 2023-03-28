-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 28-03-2023 a las 10:01:35
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

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminarMembresias` (IN `id` INT)   DELETE FROM `tbmembresia` WHERE `membresiaid` = id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarMembresia` (IN `membresiaid` INT, IN `membresiadescripcion` VARCHAR(250))   INSERT INTO `tbmembresia` (`membresiaid`, `membresiadescripcion`) VALUES (membresiaid,membresiadescripcion)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `modificarMembresia` (IN `descripcion` VARCHAR(250), IN `id` INT)   UPDATE `tbmembresia` SET `membresiadescripcion`= descripcion WHERE `membresiaid` = id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerMembresias` ()   SELECT * FROM `tbmembresia`$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbcategoria`
--

CREATE TABLE `tbcategoria` (
  `categoriaid` int(11) NOT NULL,
  `categorianombre` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbdescuento`
--

CREATE TABLE `tbdescuento` (
  `descuentoid` int(11) NOT NULL,
  `descuentotarifa` float NOT NULL,
  `descuentomembresiaid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
  `empleadotipoid` int(11) NOT NULL,
  `empleadousuarioid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbmembresia`
--

CREATE TABLE `tbmembresia` (
  `membresiaid` int(11) NOT NULL,
  `membresiadescripcion` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tbmembresia`
--

INSERT INTO `tbmembresia` (`membresiaid`, `membresiadescripcion`) VALUES
(1, 'General'),
(2, 'Premium');

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
  `productocategoriaid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbproveedor`
--

CREATE TABLE `tbproveedor` (
  `proveedorid` int(11) NOT NULL,
  `proveedornombre` varchar(255) NOT NULL,
  `proveedordireccion` varchar(255) NOT NULL,
  `proveedortelefono` int(11) NOT NULL,
  `proveedorcorreo` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbproveedorproducto`
--

CREATE TABLE `tbproveedorproducto` (
  `provproid` int(11) NOT NULL,
  `proveedorid` int(11) NOT NULL,
  `productoid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbsupermercado`
--

CREATE TABLE `tbsupermercado` (
  `supermercadoid` int(11) NOT NULL,
  `supermercadonombre` varchar(255) NOT NULL,
  `supermercadotelefono` int(11) NOT NULL,
  `supermercadocorreo` varchar(255) NOT NULL,
  `supermercadodireccion` varchar(255) NOT NULL,
  `supermercadologo` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbtipoempleado`
--

CREATE TABLE `tbtipoempleado` (
  `tipoid` int(11) NOT NULL,
  `tipodescripcion` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbtipousuario`
--

CREATE TABLE `tbtipousuario` (
  `tipousuarioid` int(11) NOT NULL,
  `tipousuariodescripcion` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbusuario`
--

CREATE TABLE `tbusuario` (
  `usuarioid` int(11) NOT NULL,
  `usuariopassword` varchar(255) NOT NULL,
  `usuariotipo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
-- Indices de la tabla `tbproveedorproducto`
--
ALTER TABLE `tbproveedorproducto`
  ADD PRIMARY KEY (`provproid`);

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
  MODIFY `categoriaid` int(11) NOT NULL AUTO_INCREMENT;

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
  MODIFY `empleadoid` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tbproveedor`
--
ALTER TABLE `tbproveedor`
  MODIFY `proveedorid` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tbproveedorproducto`
--
ALTER TABLE `tbproveedorproducto`
  MODIFY `provproid` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tbsupermercado`
--
ALTER TABLE `tbsupermercado`
  MODIFY `supermercadoid` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tbtipoempleado`
--
ALTER TABLE `tbtipoempleado`
  MODIFY `tipoid` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tbusuario`
--
ALTER TABLE `tbusuario`
  MODIFY `usuarioid` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

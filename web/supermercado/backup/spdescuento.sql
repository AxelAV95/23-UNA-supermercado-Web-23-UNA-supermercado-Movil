-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 27-04-2023 a las 05:03:54
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
CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminarDescuento` (IN `id` INT(11))   DELETE FROM `tbdescuento` WHERE `descuentoid` = id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarDescuento` (IN `id` INT(11), IN `tarifa` FLOAT(11), IN `tipomembresia` INT(11))   INSERT INTO `tbdescuento` (`descuentoid`, `descuentotarifa`, `descuentomembresiaid`) VALUES (id, tarifa, tipomembresia)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `modificarDescuento` (IN `tarifa` FLOAT(7), IN `membresiaid` INT(11), IN `id` INT(11))   UPDATE `tbdescuento` SET `descuentotarifa`= tarifa, `descuentomembresiaid`= membresiaid WHERE `descuentoid` = id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerDescuentos` ()   SELECT * FROM `tbdescuento`$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbdescuento`
--

CREATE TABLE `tbdescuento` (
  `descuentoid` int(11) NOT NULL,
  `descuentotarifa` float DEFAULT NULL,
  `descuentomembresiaid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tbdescuento`
--

INSERT INTO `tbdescuento` (`descuentoid`, `descuentotarifa`, `descuentomembresiaid`) VALUES
(1, 213, 2);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `tbdescuento`
--
ALTER TABLE `tbdescuento`
  ADD PRIMARY KEY (`descuentoid`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `tbdescuento`
--
ALTER TABLE `tbdescuento`
  MODIFY `descuentoid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

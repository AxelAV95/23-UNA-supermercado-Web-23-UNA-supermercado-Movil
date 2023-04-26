-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 26-04-2023 a las 08:10:13
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

--
-- Volcado de datos para la tabla `tbcliente`
--

INSERT INTO `tbcliente` (`clienteid`, `clientenombre`, `clienteapellidos`, `clientecedula`, `clientedireccion`, `clientetelefono`, `clientecorreo`, `clientefechaafiliacion`, `clientetipomembresia`) VALUES
(1, 'Yery Steve', 'Marin Ortiz', 45646, 'Guacimo', 32435, 'yerysteven3030@gmail.com', '2023-04-20', 2);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `tbcliente`
--
ALTER TABLE `tbcliente`
  ADD PRIMARY KEY (`clienteid`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `tbcliente`
--
ALTER TABLE `tbcliente`
  MODIFY `clienteid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

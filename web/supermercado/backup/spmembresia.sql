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


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

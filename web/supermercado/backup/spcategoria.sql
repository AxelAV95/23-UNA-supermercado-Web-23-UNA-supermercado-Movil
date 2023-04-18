-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 26-03-2023 a las 10:17:01
-- Versión del servidor: 10.4.24-MariaDB
-- Versión de PHP: 7.4.29

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

CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerCategoriaPorId` (IN `id` INT)   BEGIN
    SELECT * FROM `tbcategoria` WHERE `categoriaid` = id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerCategorias` ()   BEGIN
    SELECT * FROM `tbcategoria`;
END$$



DELIMITER ;
COMMIT;


DELIMITER //
CREATE PROCEDURE obtenerCategoriaNombre (IN categoria_id INT)
BEGIN
    SELECT categorianombre FROM tbcategoria where categoriaid = categoria_id
END //
DELIMITER ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

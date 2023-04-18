-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 26-03-2023 a las 07:40:22
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

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarProveedor` (IN `id` INT(20), IN `nombre` VARCHAR(255), IN `direccion` VARCHAR(255), IN `correo` VARCHAR(255), IN `telefono` INT(20))  NO SQL BEGIN
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

CREATE DEFINER=`root`@`localhost` PROCEDURE `modificarProveedor` (IN `id` INT(20), IN `nombre` VARCHAR(255), IN `direccion` VARCHAR(255), IN `correo` VARCHAR(255), IN `telefono` INT(20))  NO SQL BEGIN
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

CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerProveedores` ()  NO SQL SELECT * FROM `tbproveedor`$$

DELIMITER ;
DELIMITER //

CREATE PROCEDURE obtenerProveedorNombre (IN proveedor_id INT)
BEGIN
    SELECT proveedornombre FROM tbproveedor WHERE proveedorid = proveedor_id;
END //

DELIMITER ;

-- --------------------------------------------------------


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

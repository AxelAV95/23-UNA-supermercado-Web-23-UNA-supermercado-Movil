DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminarempleado`(IN `id` INT)
DELETE FROM `tbempleado` WHERE `empleadoid` = id$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarempleado`(IN `id` INT, IN `cedula` INT, IN `nombre` VARCHAR(255), IN `apellidos` VARCHAR(255), IN `telefono` INT, IN `direccion` VARCHAR(255), IN `fechaingreso` DATE, IN `fechasalida` DATE, IN `estado` INT, IN `tipoempleado` INT)
INSERT INTO `tbempleado`(`empleadoid`,`empleadocedula`, `empleadonombre`,`empleadoapellidos`, `empleadotelefono`,`empleadodireccion`, `empleadofechaingreso`,`empleadofechasalida`, `empleadoestado`,`empleadotipoid`) VALUES (id,cedula,nombre,apellidos,telefono,direccion,fechaingreso,fechasalida,estado,tipoempleado)$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `modificarempleado`(IN `id` INT, IN `cedula` INT, IN `nombre` VARCHAR(255), IN `apellidos` VARCHAR(255), IN `telefono` INT, IN `direccion` VARCHAR(255), IN `fechaingreso` DATE, IN `fechasalida` DATE, IN `estado` INT, IN `tipoempleado` INT)
UPDATE `tbempleado` SET `empleadocedula`= cedula,
`empleadonombre`= nombre,
`empleadoapellidos`= apellidos,
`empleadotelefono`= telefono,
`empleadodireccion`= direccion,
`empleadofechaingreso`= fechaingreso,
`empleadofechasalida`= fechasalida,
`empleadoestado`= estado,
`empleadotipoid`= tipoempleado
WHERE `empleadoid` = id$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerempleados`()
SELECT * FROM `tbempleado`$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerempleados`()
SELECT * FROM `tbempleado` 
INNER JOIN tbtipoempleado ON tbempleado.empleadotipoid = tbtipoempleado.tipoid$$
DELIMITER ;
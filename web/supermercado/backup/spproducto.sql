DELIMITER //

CREATE PROCEDURE insertarProducto(
  IN p_id INT,
  IN p_nombre VARCHAR(255),
  IN p_precio DECIMAL(10,2),
  IN p_fecha_ingreso DATE,
  IN p_stock INT(11),
  IN p_estado VARCHAR(20),
  IN p_categoria_id INT(11),
  IN p_proveedor_id INT(11)
)
BEGIN
  INSERT INTO tbproducto(productoid, productonombre, productoprecio, productofechaingreso, productostock, productoestado, productocategoriaid, productoproveedorid)
  VALUES(p_id, p_nombre, p_precio, p_fecha_ingreso, p_stock, p_estado, p_categoria_id, p_proveedor_id);
END//

DELIMITER ;

DELIMITER //

CREATE PROCEDURE eliminarProducto(
  IN p_id INT
)
BEGIN
  DELETE FROM tbproducto WHERE productoid = p_id;
END//

DELIMITER ;

DELIMITER //


CREATE PROCEDURE modificarProducto(
  IN p_id INT,
  IN p_nombre VARCHAR(255),
  IN p_precio DECIMAL(10,2),
  IN p_fecha_ingreso DATE,
  IN p_stock INT(11),
  IN p_estado VARCHAR(20),
  IN p_categoria_id INT(11),
  IN p_proveedor_id INT(11)
)
BEGIN
  UPDATE tbproducto
  SET productonombre = p_nombre,
      productoprecio = p_precio,
      productofechaingreso = p_fecha_ingreso,
      productostock = p_stock,
      productoestado = p_estado,
      productocategoriaid = p_categoria_id,
      productoproveedorid = p_proveedor_id
  WHERE productoid = p_id;
END//

DELIMITER ;


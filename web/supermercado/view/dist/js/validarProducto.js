function validarFormulario() {
    var nombre = document.getElementById('productonombre').value;
    var precio = document.getElementById('productoprecio').value;
    var stock = document.getElementById('productostock').value;
    var fecha = document.getElementById('productofechaingreso').value;
    var categoria = document.getElementById('productocategoriaid').value;
    var proveedor = document.getElementById('productoproveedorid').value;
  
    // Validar que todos los campos estén llenos
    if (nombre === '' || precio === '' || stock === '' || fecha === '' || categoria === 'Seleccione la categoría' || proveedor === 'Seleccione el proveedor') {
      Swal.fire({
        icon: 'error',
        title: 'Campos incompletos',
        text: 'Por favor, complete todos los campos.',
      });
      return false;
    }
      
      // Validar que el nombre solo contenga letras
    var letras = /^[A-Za-zñÑ\s]+$/;

    if (!nombre.match(letras)) {
      Swal.fire({
        icon: 'error',
        title: 'Nombre inválido',
        text: 'El nombre solo debe contener letras.',
      });
      return false;
    }

  
   // Validar que el precio y el stock solo contengan números
var numeros = /^[0-9]{1,6}$/;
if (!precio.match(numeros) || !stock.match(numeros)) {
  Swal.fire({
    icon: 'error',
    title: 'Valores inválidos',
    text: 'El precio y el stock deben contener solo números.',
  });
  return false;
}

  
    // El formulario se envía si pasa todas las validaciones
    return true;
  }
  

// Definir función de validación del formulario
function validarFormulario() {
  var descripcion = document.getElementById('membresiadescripcion').value;
  
  // Validar que todos los campos estén llenos
  if (descripcion === '') {
    Swal.fire({
      icon: 'error',
      title: 'Campos incompletos',
      text: 'Por favor, complete todos los campos.',
    });
    return false;
  }
  
  // Validar que el nombre solo contenga letras
  var letras = /^[A-Za-z\s]+$/;
  if (!descripcion.match(letras)) {
    Swal.fire({
      icon: 'error',
      title: 'Nombre inválido',
      text: 'El nombre solo debe contener letras.',
    });
    return false;
  }
  
  // El formulario se envía si pasa todas las validaciones
  return true;
}

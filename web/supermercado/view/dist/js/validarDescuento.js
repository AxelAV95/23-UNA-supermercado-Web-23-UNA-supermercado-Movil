
function validarFormulario() {
  var descuentotarifa = document.getElementById('descuentotarifa').value;
  var descuentomembresiaid = document.getElementById('descuentomembresiaid').value;

  // Validar que todos los campos estén llenos
  if (descuentotarifa === '' || descuentomembresiaid ==='Seleccione la membresía') {
    Swal.fire({
      icon: 'error',
      title: 'Campos incompletos',
      text: 'Por favor, complete todos los campos.',
    });
    return false;
  }


 // Validar que el precio y el stock solo contengan números
var numeros = /^[0-9]{1,6}$/;
if (!descuentotarifa.match(numeros)) {
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
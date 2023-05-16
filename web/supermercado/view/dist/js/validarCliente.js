
function validarFormulario() {
  var clientenombre = document.getElementById('clientenombre').value;
  var clienteapellidos = document.getElementById('clienteapellidos').value;
  var clientecedula = document.getElementById('clientecedula').value;
  var clientedireccion = document.getElementById('clientedireccion').value;
  var clientetelefono = document.getElementById('clientetelefono').value;
  var clientecorreo = document.getElementById('clientecorreo').value;
  var clientefechaafiliacion = document.getElementById('clientefechaafiliacion').value;

  // Validar que todos los campos estén llenos
  if (clientenombre === ''|| clienteapellidos === ''|| clientecedula === ''|| clientedireccion === ''|| clientetelefono === ''|| clientefechaafiliacion ==='' || clientetipomembresia === 'Seleccione la membresía') {
    Swal.fire({
      icon: 'error',
      title: 'Campos incompletos',
      text: 'Por favor, complete todos los campos.',
    });
    return false;
  }

  // Validar que el nombre solo contenga letras
  var letras = /^[A-Za-z\s]+$/;
  if (!nombre.match(letras) || !clienteapellidos.match(letras) || !clientedireccion.match(letras)) {
    Swal.fire({
      icon: 'error',
      title: 'Nombre inválido',
      text: 'El nombre solo debe contener letras.',
    });
    return false;
  }

 // Validar que el precio y el stock solo contengan números
var numeros = /^[0-9]{1,6}$/;
if (!clientetelefono.match(numeros) || !clientecedula.match(numeros)) {
Swal.fire({
  icon: 'error',
  title: 'Valores inválidos',
  text: 'El precio y el stock deben contener solo números.',
});
return false;
}
// Validar el formato del correo electrónico
if (!validarCorreo(clientecorreo)) {
  return false;
}
  // El formulario se envía si pasa todas las validaciones
  return true;
}

function validarCorreo(correo) {
  var expresionRegular = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,4})+$/;
  if (expresionRegular.test(correo)) {
    return true;
  } else {
    Swal.fire({
      icon: 'error',
      title: 'Correo inválido',
      text: 'Por favor, ingrese un correo electrónico válido.',
    });
    return false;
  }
}

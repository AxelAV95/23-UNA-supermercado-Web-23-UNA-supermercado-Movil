var Toast = Swal.mixin({
       toast: true,
       position: 'top-end',
       showConfirmButton: false,
       timer: 3000,
       timerProgressBar: true
     });

/*
 $(function () {

    $('#usuarios').DataTable({
       "language": {
            "url": "//cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json"
        },
      "paging": true,
      "lengthChange": false,
      "searching": true,
      "ordering": true,
      "info": true,
      "autoWidth": false,
      "responsive": true,
    });
  });*/


 $('#empleados2').dataTable({
          'processing': true,
            'serverSide': true,
            'serverMethod': 'get',
            'ajax': {
                'url':'../business/empleadoaction.php?metodo=obtener'
            },          
          "columns": [
           
          {"data": "empleadocedula"},
          {"data": "empleadonombre"},
          {"data": "empleadoapellidos"},
          {"data": "empleadodireccion"},
          {"data": "empleadotipoid"},
              {"data": null,
              render: function (data, type, row, meta) {
                return "<div class='btn-group'><button class='btn btn-warning btnEditarEmpleado' data-empleadoid='" + data.empleadoid + "' data-empleadocedula='" + data.empleadocedula + "' data-empleadonombre='" + data.empleadonombre + "' data-empleadoapellidos='" + data.empleadoapellidos + "' data-empleadotelefono='" + data.empleadotelefono + "' data-empleadodireccion='" + data.empleadodireccion + "' data-empleadofechaingreso='" + data.empleadofechaingreso + "' data-empleadofechasalida='" + data.empleadofechasalida + "' data-empleadoestado='" + data.empleadoestado + "' data-empleadotipoid='" + data.empleadotipoid + "' data-toggle='modal' data-target='#modalEditarEmpleado'><i class='fa fa-pencil-alt'></i></button><button class='btn btn-danger btnEliminarEmpleado' data-empleadoid='" + data.empleadoid + "'><i class='fa fa-times'></i></button></div>";
            }
              }
              
          ]
      
        })


        // Obtener una referencia al formulario
var formulario = document.getElementById("formulario-insertar");

// Agregar un evento de escucha al evento submit del formulario
formulario.addEventListener("submit", function(event) {
  // Detener el envío del formulario
  event.preventDefault();

  // Verificar cada campo del formulario
  var cedula = document.getElementById("empleadocedula").value.trim();
  var nombre = document.getElementById("empleadonombre").value.trim();
  var apellidos = document.getElementById("empleadoapellidos").value.trim();
  var telefono = document.getElementById("empleadotelefono").value.trim();
  var direccion = document.getElementById("empleadodireccion").value.trim();
  var fechaIngreso = document.getElementById("empleadofechaingreso").value.trim();
  var tipoEmpleado = document.getElementById("empleadotipoid").value;

  // Realizar la validación de cada campo
  if (cedula === "") {
    Toast.fire({
      icon: 'warning',
      title: '<div style=margin-top:0.5rem;>Ingrese la cédula.</div>'
    });
    return;
  }

  if (!/^\d+$/.test(cedula)) {
    Toast.fire({
      icon: 'warning',
      title: '<div style=margin-top:0.5rem;>La cédula debe contener solo números.</div>'
    });
    return;
  }

  if (nombre === "") {
    Toast.fire({
      icon: 'warning',
      title: '<div style=margin-top:0.5rem;>Ingrese el nombre.</div>'
    });
    return;
  }

  if (!/^[a-zA-Z\s]+$/.test(nombre)) {
    Toast.fire({
      icon: 'warning',
      title: '<div style=margin-top:0.5rem;>El nombre debe contener solo letras.</div>'
    });
    return;
  }

  if (apellidos === "") {
    Toast.fire({
      icon: 'warning',
      title: '<div style=margin-top:0.5rem;>Ingrese los apellidos.</div>'
    });
    return;
  }

  if (!/^[a-zA-Z\s]+$/.test(apellidos)) {
    Toast.fire({
      icon: 'warning',
      title: '<div style=margin-top:0.5rem;>Los apellidos deben contener solo letras.</div>'
    });
    return;
  }

  if (telefono === "") {
    Toast.fire({
      icon: 'warning',
      title: '<div style=margin-top:0.5rem;>Ingrese el teléfono.</div>'
    });
    return;
  }

  if (!/^\d+$/.test(telefono)) {
    Toast.fire({
      icon: 'warning',
      title: '<div style=margin-top:0.5rem;>El teléfono debe contener solo números.</div>'
    });
    return;
  }

  if (direccion === "") {
    Toast.fire({
      icon: 'warning',
      title: '<div style=margin-top:0.5rem;>Ingrese la dirección.</div>'
    });
    return;
  }

  if (!/^[a-zA-Z0-9\s]+$/.test(direccion)) {
    Toast.fire({
      icon: 'warning',
      title: '<div style=margin-top:0.5rem;>La dirección debe contener solo letras y números.</div>'
    });
    return;
  }


  if (fechaIngreso === "") {
    Toast.fire({
      icon: 'warning',
      title: '<div style=margin-top:0.5rem;>Ingrese la fecha de ingreso.</div>'
})
    return;
  }

  if (tipoEmpleado === "") {
    Toast.fire({
      icon: 'warning',
      title: '<div style=margin-top:0.5rem;>Seleccione el tipo de empleado.</div>'
})
    return;
  }

  // Si todos los campos están llenos, se puede enviar el formulario
  formulario.submit();
});
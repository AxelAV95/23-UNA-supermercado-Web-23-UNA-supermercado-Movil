
  $(function () {

    $('#proveedores').DataTable({
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
  });


$(".tabla-proveedores tbody").on("click", "button.btnEditarProveedor", function(){

    var proveedorid = $(this).attr("proveedorid");
    var proveedornombre = $(this).attr("proveedornombre");
    var proveedordireccion = $(this).attr("proveedordireccion");
    var proveedorcorreo =  $(this).attr("proveedorcorreo");
    var proveedortelefono = $(this).attr("proveedortelefono");
    var proveedorlat =  $(this).attr("proveedorlat");
    var proveedorlong = $(this).attr("proveedorlong");
    
    //alert(proveedortelefono);
    $("#modalEditarProveedor #proveedorid").val(proveedorid);
    $("#modalEditarProveedor #proveedornombre").val(proveedornombre);
    $("#modalEditarProveedor #proveedordireccion").val(proveedordireccion);
    $("#modalEditarProveedor #proveedorcorreo").val(proveedorcorreo);
    $("#modalEditarProveedor #proveedortelefono").val(proveedortelefono);
    $("#modalEditarProveedor #proveedorlat").val(proveedorlat);
    $("#modalEditarProveedor #proveedorlong").val(proveedorlong);

  

});

  
$(".tabla-proveedores tbody").on("click", "button.btnEliminarProveedor", function(){

  var proveedorid = $(this).attr("proveedorid");

 Swal.fire({
       title: '¿Desea eliminar el proveedor?',
       text: "No se podrá revertir el cambio",
       icon: 'warning',
       showCancelButton: true,
       confirmButtonColor: '#3085d6',
       cancelButtonColor: '#d33',
        cancelButtonText: "Cancelar",
       confirmButtonText: 'Eliminar'
       }).then((result) => {
         if (result.isConfirmed) {
           window.location = "../business/proveedoraction.php?eliminar=true&proveedorid="+proveedorid;
    
         }
   })


});





wFORMS.behaviors['validation'].errMsg_email = "El email introducido no tiene un formato valido";
wFORMS.behaviors['validation'].errMsg_notification = "%% error(s) detectado(s). El formulario no se enviará.Por favor, chequea la información suministrada.";


  $.extend( $.validator.messages, {
    required: "Este campo es requerido.",
    remote: "Por favor modificar este campo.",
    email: "Ingrese un correo válido.",
    url: "Ingrese una URL válida.",
    date: "Ingrese una fecha válida.",
    dateISO: "Ingrese una fecha válida(ISO).",
    number: "Ingrese un número válido.",
    digits: "Ingrese solo dígitos.",
    equalTo: "Ingrese el mismo valor otra vez."
  } );


  var form = $("#example-advanced-form").show();

  form.steps({
    headerTag: "h3",
    bodyTag: "fieldset",
    transitionEffect: "slideLeft",
    labels:{
      finish: "Insertar"
    },
    onStepChanging: function (event, currentIndex, newIndex)
    {
        // Allways allow previous action even if the current form is not valid!
      if (currentIndex > newIndex)
      {
        return true;
      }
        // Forbid next action on "Warning" step if the user is to young
      if (newIndex === 3 && Number($("#age-2").val()) < 18)
      {
        return false;
      }
        // Needed in some cases if the user went back (clean up)
      if (currentIndex < newIndex)
      {
            // To remove error styles
        form.find(".body:eq(" + newIndex + ") label.error").remove();
        form.find(".body:eq(" + newIndex + ") .error").removeClass("error");
      }
      form.validate().settings.ignore = ":disabled,:hidden";
      return form.valid();
    },
    onStepChanged: function (event, currentIndex, priorIndex)
    {
        // Used to skip the "Warning" step if the user is old enough.
      if (currentIndex === 2 && Number($("#age-2").val()) >= 18)
      {
        form.steps("next");
      }
        // Used to skip the "Warning" step if the user is old enough and wants to the previous step.
      if (currentIndex === 2 && priorIndex === 3)
      {
        form.steps("previous");
      }
    },
    onFinishing: function (event, currentIndex)
    {
      form.validate().settings.ignore = ":disabled";
      return form.valid();
    },
    onFinished: function (event, currentIndex)
    {

      var form = $("#example-advanced-form").closest("form");
      var formData = new FormData(form[0]);
      console.log(formData);
      var data = $("#example-advanced-form :input").serializeArray();
      datos = {};
        jQuery.each(data, function(i, item){
            datos[item['name']]=item['value'];
        });
      console.log(data);

      $.ajax({
        url: "../business/proveedoraction.php",
        type: "POST",
        data: formData,
         processData: false,
    contentType: false,
        
        success: function(respuesta){
        // alert(respuesta);

         if(respuesta == 1){
          window.location = "proveedorview.php";
         }
        }
      });
   //   alert("Submitted!");
    }
  }).validate({
    errorPlacement: function errorPlacement(error, element) { element.before(error); },
    rules: {
      confirm: {
        equalTo: "#password-2"
      }
    }
  });



var Toast = Swal.mixin({
       toast: true,
       position: 'top-end',
       showConfirmButton: false,
       timer: 3000
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


 $('#empleados2').dataTable( {
          'processing': true,
            'serverSide': true,
            'serverMethod': 'get',
            'ajax': {
                'url':'../business/empleadoaction.php?metodo=obtener'
            },          
          "columns": [
           
          { "data": "empleadocedula" },
          { "data": "empleadonombre" },
          { "data": "empleadoapellidos"},
          { "data": "empleadodireccion"},
          { "data": "empleadotipoid"},
              { "data": null,
              render: function (data, type, row, meta) {
                return "<div class='btn-group'><button class='btn btn-warning btnEditarEmpleado' empleadoid ="+data.empleadoid+" empleadocedula="+data.empleadocedula+" empleadonombre="+data.empleadonombre+" empleadoapellidos="+data.empleadoapellidos+" empleadotelefono="+data.empleadotelefono+" empleadodireccion="+data.empleadodireccion+" empleadofechaingreso="+data.empleadofechaingreso+" empleadofechasalida="+data.empleadofechasalida+" empleadoestado="+data.empleadoestado+" empleadotipoid="+data.empleadotipoid+" data-toggle='modal' data-target='#modalEditarEmpleado'><i class='fa fa-pencil-alt'></i></button><button class='btn btn-danger btnEliminarEmpleado' empleadoid="+data.empleadoid+" ><i class='fa fa-times'></i></button></div>";
                }
              }
              
          ]
      
        })
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


 $('#usuarios').dataTable( {
          'processing': true,
            'serverSide': true,
            'serverMethod': 'get',
            'ajax': {
                'url':'../business/usuarioaction.php?metodo=obtener'
            },          
          "columns": [
             
              { "data": "empleadonombre" },
              { "data": "empleadoapellidos"} ,
              { "data": "tipodescripcion"} ,
              { "data": null,
              render: function ( data, type, row, meta) {
                return "<div class='btn-group'><button class='btn btn-warning btnEditarUsuario' tipoid="+data.tipoid+" usuarioid="+data.usuarioid+" data-toggle='modal' data-target='#modalEditarUsuario'><i class='fa fa-pencil-alt'></i></button><button class='btn btn-danger btnEliminarUsuario' usuarioid="+data.usuarioid+" ><i class='fa fa-times'></i></button></div>";
                }
              }
              
          ]
      
        })
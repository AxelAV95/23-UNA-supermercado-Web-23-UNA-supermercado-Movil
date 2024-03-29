

<!-- jQuery -->
<script src="plugins/jquery/jquery.min.js"></script>
<!-- jQuery UI 1.11.4 -->
<script src="plugins/jquery-ui/jquery-ui.min.js"></script>

<script type="text/javascript" src="dist/js/qrcode.min.js"></script>


<script type="text/javascript">
  
  var qrcode = new QRCode("qrcode");

  function makeCode () {    
    var dato = "<?php echo $cedula; ?>";
    qrcode.makeCode(dato);
  }

  $(".btn-qr").on('click', function(){
      makeCode();
  });

</script>

<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<script>
  $.widget.bridge('uibutton', $.ui.button)
</script>
<!-- Bootstrap 4 -->
<script src="plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- ChartJS -->
<script src="plugins/chart.js/Chart.min.js"></script>
<!-- Sparkline -->
<script src="plugins/sparklines/sparkline.js"></script>
<!-- JQVMap -->
<script src="plugins/jqvmap/jquery.vmap.min.js"></script>
<script src="plugins/jqvmap/maps/jquery.vmap.usa.js"></script>
<!-- jQuery Knob Chart -->
<script src="plugins/jquery-knob/jquery.knob.min.js"></script>
<!-- daterangepicker -->
<script src="plugins/moment/moment.min.js"></script>
<script src="plugins/daterangepicker/daterangepicker.js"></script>
<!-- Tempusdominus Bootstrap 4 -->
<script src="plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"></script>
<!-- Summernote -->
<script src="plugins/summernote/summernote-bs4.min.js"></script>
<!-- overlayScrollbars -->
<script src="plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
<!-- AdminLTE App -->
<script src="dist/js/adminlte.js"></script>
<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<script src="dist/js/pages/dashboard.js"></script>

<!-- DataTables  & Plugins -->
<script src="plugins/datatables/jquery.dataTables.min.js"></script>
<script src="plugins/datatables-bs4/js/dataTables.bootstrap4.min.js"></script>
<script src="plugins/datatables-responsive/js/dataTables.responsive.min.js"></script>
<script src="plugins/datatables-responsive/js/responsive.bootstrap4.min.js"></script>
<script src="plugins/datatables-buttons/js/dataTables.buttons.min.js"></script>
<script src="plugins/datatables-buttons/js/buttons.bootstrap4.min.js"></script>
<script src="plugins/jszip/jszip.min.js"></script>
<script src="plugins/pdfmake/pdfmake.min.js"></script>
<script src="plugins/pdfmake/vfs_fonts.js"></script>
<script src="plugins/datatables-buttons/js/buttons.html5.min.js"></script>
<script src="plugins/datatables-buttons/js/buttons.print.min.js"></script>
<script src="plugins/datatables-buttons/js/buttons.colVis.min.js"></script>
<script src="plugins/sweetalert2/sweetalert2.min.js"></script>
<script src="plugins/toastr/toastr.min.js"></script>

<script type="text/javascript">
  var currentURL = window.location.href;

  $('.sidebar li a').each(function() {

    var linkURL = $(this).attr('href');
    


    const myArray = currentURL.split("/");
    
    if(linkURL == myArray[myArray.length - 1]){
        $(this).closest('li a').addClass('active');
        
        var element = $(this).parent().parent().parent().children().parent()[0];
        $(element).addClass('menu-is-opening')
        $(element).addClass('menu-open')
        
    }
    if("tipousuarioview.php" == myArray[myArray.length - 1] ){
      $('a.nav-link[href="usuarioview.php').addClass('active');
    }

    if("tipoempleadoview.php" == myArray[myArray.length - 1] ){
      $('a.nav-link[href="empleadoview.php').addClass('active');
    }

    if("membresiaview.php" == myArray[myArray.length - 1] ){
      $('a.nav-link[href="clienteview.php').addClass('active');
    }
  });
  /*
 $.ajax({
      type: "GET",
      url: '../business/configuracionaction.php?metodo=obtener',
      dataType: 'json',
      success: function(data) {

          $("#logo-super").attr("src", data[0].supermercadologo);
          
          
          console.log(data[0] .supermercadologo)

      }
    });*/
</script>
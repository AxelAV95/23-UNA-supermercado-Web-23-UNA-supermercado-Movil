<div id="modalAgregarEmpleado" class="modal fade" role="dialog">
  
  <div class="modal-dialog">

    <div class="modal-content">

        <div class="modal-header">
          <h4 class="modal-title">Agregar empleado</h4>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true" style="font-size:30px">&times;</span>
          </button>
        </div>

        <div class="modal-body">

          <div class="box-body">
            <form id="formulario-insertar" method="POST" action="../business/empleadoaction.php"  enctype="multipart/form-data">
           <input type="hidden" name="metodo" id="metodo" value="agregar">
           <input type="hidden" name="empleadofechasalida" id="empleadofechasalida" value="0000-00-00">
           <input type="hidden" name="empleadoestado" id="empleadoestado" value="1">

              <div class="form-group">
                <label >Cédula:</label>
                <input type="text" class="form-control" name="empleadocedula" id="empleadocedula" placeholder="Ingrese cédula" oninput="limitarLongitud(event, 9)">
              </div>

              <div class="form-group">
                <label >Nombre:</label>
                <input type="text" class="form-control" name="empleadonombre" id="empleadonombre" placeholder="Ingrese nombre">
              </div>

              <div class="form-group">
                <label >Apellidos:</label>
                <input type="text" class="form-control" name="empleadoapellidos" id="empleadoapellidos" placeholder="Ingrese apellidos" >
              </div>

              <div class="form-group">
                <label >Teléfono:</label>
                <input type="text" class="form-control" name="empleadotelefono" id="empleadotelefono" placeholder="Ingrese teléfono" oninput="limitarLongitud(event, 8)">
              </div>

              <div class="form-group">
                <label >Dirección:</label>
                <input type="text" class="form-control" name="empleadodireccion" id="empleadodireccion" placeholder="Ingrese direccion">
              </div>

              <div class="form-group">
                <label >Fecha de ingreso:</label>
                <input type="date" class="form-control" name="empleadofechaingreso" id="empleadofechaingreso" placeholder="Ingrese fecha de ingreso">
              </div>

              <div class="form-group">
                <label >Tipo de empleado:</label>
                <select class="tipoid form-control" name="empleadotipoid" id="empleadotipoid" >
                  
                  <?php                  
                  foreach($tipos as $tipoempleado){
                    echo ' <option value="'.$tipoempleado['tipoid'].'" class="badge badge-pill badge-warning" style="font-size: 15px;">'.$tipoempleado['tipodescripcion'].'</option>';
                  }
                  ?>
                </select>
              </div>
              
             <center><button type="submit" name="insertar" class="btn btn-primary">Insertar</button></center> 
            </form>

    </div>

  </div>

</div>
  </div>

</div>

<script>
  function limitarLongitud(event, maxCaracteres) {
  var input = event.target;
  if (input.value.length > maxCaracteres) {
    input.value = input.value.slice(0, maxCaracteres);
  }
}

</script>

<div id="modalAgregarDescuento" class="modal fade" role="dialog">
  
  <div class="modal-dialog">

    <div class="modal-content">

        <div class="modal-header">
          <h4 class="modal-title">Agregar Descuento</h4>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true" style="font-size: 30px">&times;</span>
          </button>
        </div>

        <div class="modal-body">

          <div class="box-body">
          <form method="POST" action="../business/descuentoaction.php"  enctype="multipart/form-data">

            <div class="form-group">
            <input type="hidden" name="accion" id="accion" value="agregar">
                <input type="hidden" name="descuentoid" id="descuentoid">
                <input type="hidden" name="descuentotarifa" id="descuentotarifa">
                <input type="hidden" name="descuentomembresiaid" id="descuentomembresiaid">
           
              </div>

              <div class="form-group">
                <label >Tarifa:</label>
                <input type="number" class="form-control" name="descuentotarifa" id="descuentotarifa" placeholder="Ingrese tarifa" required pattern="^\d{1,11}(\.\d{1,2})?$" step="0.01" maxlength="11">
        
                </div>
       
                <div class="form-group">
                <label>Tipo de membresía:</label>
                <select class="descuentomembresiaid form-control" name="descuentomembresiaid" id="descuentomembresiaid" required>
                <option value="" selected disabled>Seleccione la membresía</option>
    <?php 
        foreach($membresias as $membresia) {
          if ($categoria['membresiaid'] == $membresia['membresiaid']) {
            echo '<option value="'.$membresia['membresiaid'].'" class="badge badge-pill badge-warning" style="font-size: 15px; "selected>'.$membresia['membresiadescripcion'].'</option>';
        } else {
          echo '<option value="'.$membresia['membresiaid'].'" class="badge badge-pill badge-warning" style="font-size: 15px;">'.$membresia['membresiadescripcion'].'</option>';
        }
      }

    ?>
</select>
              </div>
              
             <center><button type="submit" name="insertar" id="insertar" class="btn btn-primary">Insertar</button></center> 
            </form>

    </div>

  </div>

</div>
  </div>

</div>
<script src="dist/js/validarDescuento.js"></script>

<div id="modalEditarlCiente" class="modal fade" role="dialog">
  
  <div class="modal-dialog">

    <div class="modal-content">

        <div class="modal-header">
          <h4 class="modal-title">Editar cliente</h4>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true" style="font-size:30px">&times;</span>
          </button>
        </div>

        <div class="modal-body">

          <div class="box-body">

            <form method="POST" action="../business/clienteaction.php" enctype="multipart/form-data">
              
              <div class="form-group">
              <input type="hidden" name="accion" id="accion" value="actualizar">
                <input type="hidden" name="clienteid" id="clienteid">
                <input type="hidden" name="clientenombre" id="clientenombre">
                <input type="hidden" name="clienteapellidos" id="clienteapellidos">
                <input type="hidden" name="clientecedula" id="clientecedula">
                <input type="hidden" name="clientedireccion" id="clientedireccion">
                <input type="hidden" name="clientetelefono" id="clientetelefono">
                <input type="hidden" name="clientecorreo" id="clientecorreo">
                <input type="hidden" name="clientefechaafiliacion" id="clientefechaafiliacion">
                <input type="hidden" name="clientetipomembresia" id="clientetipomembresia">
              </div>

              <div class="form-group">
                <label >Nombre:</label>
                <input type="text" class="form-control" name="clientenombre" id="clientenombre" placeholder="Ingrese nombre" required pattern="^[A-Za-z\s]+$" title="Solo se permiten letras">
                <label >Apellidos:</label>
                <input type="text" class="form-control" name="clienteapellidos" id="clienteapellidos" placeholder="Ingrese Apellidos"  required pattern="^[A-Za-z\s]+$" title="Solo se permiten letras">
                <label >Cédula:</label>
                <input type="number" class="form-control" name="clientecedula" id="clientecedula" placeholder="Ingrese cédula" maxlength="9" pattern="\d{9}" required>
                <label >Dirección:</label>
                <input type="text" class="form-control" name="clientedireccion" id="clientedireccion" placeholder="Ingrese dirección" required pattern="[A-Za-z0-9\s]+" title="Solo se permiten letras y números">
                <label >Télefono:</label>
                <input type="number" class="form-control" name="clientetelefono" id="clientetelefono" placeholder="Ingrese télefono"  maxlength="8" pattern="\d{9}" required>
                <label >Correo:</label>
                <input type="email" class="form-control" name="clientecorreo" id="clientecorreo" placeholder="Ingrese correo" required>
                </div>
       
       
                <div class="form-group">
                <label>Tipo de membresía:</label>
        <select class="clientetipomembresia form-control" name="clientetipomembresia" id="clientetipomembresia" required>
        <option value="" selected disabled>Seleccione la membresía</option>
        <?php 
                
                foreach($membresias as $membresia){

                  echo '<script>var idc = $("#clientetipomembresia").val()</script>';
                  echo $variable = "<script>document.write(idc)</script>";
                  if($categoria['membresiaid'] == $variable){
                      echo ' <option value="'.$membresia['membresiaid'].'" class="badge badge-pill badge-warning" style="font-size: 15px;" selected>'.$membresia['membresiadescripcion'].'</option>';
                  }else{
                      echo ' <option value="'.$membresia['membresiaid'].'" class="badge badge-pill badge-warning" style="font-size: 15px;">'.$membresia['membresiadescripcion'].'</option>';
                  }
                  
                }?>
</select>
              </div>
              
             <center><button type="submit" name="actualizar" id="actualizar" class="btn btn-primary">Actualizar</button></center> 
            </form>

          </div>

        </div>

      </div>
  </div>

</div>

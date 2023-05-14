<div id="modalEditarEmpleado" class="modal fade" role="dialog">
  
  <div class="modal-dialog">

    <div class="modal-content">

        <div class="modal-header">
          <h4 class="modal-title">Editar Empelado</h4>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>

        <div class="modal-body">

          <div class="box-body">

            <form id="formulario-editar" method="POST" action="../business/empleadoaction.php" enctype="multipart/form-data">
            <input type="hidden" name="metodo" id="metodo" value="editar">
              <div class="form-group">
              <input type="hidden" name="empleadoid" id="empleadoid">
                <input type="hidden" name="categoriaid" id="categoriaid">
                <input type="hidden" name="categoriacodigo" id="categoriacodigo">
              </div>

              <div class="form-group">
                <label >Cédula:</label>
                <input type="text" class="form-control" name="empleadocedula" id="empleadocedula" placeholder="Ingrese cédula" required pattern="[0-9]+">
              </div>

              <div class="form-group">
                <label >Nombre:</label>
                <input type="text" class="form-control" name="empleadonombre" id="empleadonombre" placeholder="Ingrese nombre" required pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ\s]+">
              </div>

              <div class="form-group">
                <label >Apellidos:</label>
                <input type="text" class="form-control" name="empleadoapellidos" id="empleadoapellidos" placeholder="Ingrese apellidos" required pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ\s]+">
              </div>

              <div class="form-group">
                <label >Teléfono:</label>
                <input type="text" class="form-control" name="empleadotelefono" id="empleadotelefono" placeholder="Ingrese teléfono" maxlength="8" required pattern="[0-9]{8}">
              </div>

              <div class="form-group">
                <label >Dirección:</label>
                <input type="text" class="form-control" name="empleadodireccion" id="empleadodireccion" placeholder="Ingrese direccion" required pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ\s]+">
              </div>

              <div class="form-group">
                <label >Fecha de ingreso:</label>
                <input type="date" class="form-control" name="empleadofechaingreso" id="empleadofechaingreso" placeholder="Ingrese fecha de ingreso" required>
              </div>

              <div class="form-group">
                <label >Fecha de salida:</label>
                <input type="date" class="form-control" name="empleadofechasalida" id="empleadofechasalida" placeholder="Ingrese fecha de salida">
              </div>

              <div class="form-group">
                <label >Estado de empleado:</label>
                <label>
                <input type="radio" name="empleadoestado" id="empleadoestado" value="1" checked>
                Activo
                </label>
                <label>
                <input type="radio" name="empleadoestado" id="empleadoestado" value="0">
                Inactivo
                </label>
              </div>

              <div class="form-group">
                <label >Tipo de empleado:</label>
                <select class="tipoid form-control" name="empleadotipoid" id="empleadotipoid" required > 
                  <?php                  
                  foreach($tipos as $tipoempleado){
                    echo ' <option value="'.$tipoempleado['tipoid'].'" class="badge badge-pill badge-warning" style="font-size: 15px;">'.$tipoempleado['tipodescripcion'].'</option>';
                  } 
                  ?>
                </select>
              </div>
              
             <center><button type="submit" name="actualizar" class="btn btn-primary">Actualizar</button></center> 
            </form>

    </div>

  </div>

</div>
  </div>

</div>
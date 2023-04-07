<div id="modalAgregarUsuario" class="modal fade" role="dialog">
  
  <div class="modal-dialog">

    <div class="modal-content">

        <div class="modal-header">
          <h4 class="modal-title">Agregar usuario</h4>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true" style="font-size: 30px">&times;</span>
          </button>
        </div>

        <div class="modal-body">

          <div class="box-body">

            <form id="formulario-insertar">
             <input type="hidden" name="metodo" value="agregar">

              <div class="form-group">

                <label >Empleado: </label>
                <select id="empleados" class="form-control" name="empleadoid" >
                  <option selected>Seleccione un empleado</option>
                </select>
               
              </div>

              <div class="form-group">
                 <label>Tipo de usuario: </label>
              <select class="tipoid form-control" name="tipoid" id="tipos" >

                <option selected>Seleccione el tipo de usuario</option>
              
                  <?php 
                  /*
                  foreach($tipousuarios as $tipousuario){

                    echo ' <option value="'.$tipousuario['tipoid'].'" class="badge badge-pill badge-warning" style="font-size: 15px;">'.$tipousuario['tipodescripcion'].'</option>';
                  }*/
                  ?>

                </select>
                
              </div>
               
              
              <div class="form-group">
                <label >Password:</label>
                <input type="text" class="form-control" name="usuariopassword" id="usuariopassword" placeholder="Ingrese contraseña">
                      </div>

              
            <br><br>

             
              
             <center><button type="button" name="insertar" id="insertar" class="btn btn-primary">Insertar</button></center> 
            </form>

        </div>

      </div>

    </div>
  </div>

</div>
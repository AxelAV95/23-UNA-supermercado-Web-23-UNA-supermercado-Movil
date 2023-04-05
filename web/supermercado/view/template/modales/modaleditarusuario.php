<div id="modalEditarUsuario" class="modal fade" role="dialog">

    <div class="modal-dialog">

      <div class="modal-content">

        <div class="modal-header">
          <h4 class="modal-title">Editar usuario</h4>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>

        <div class="modal-body">

          <div class="box-body">

            <form method="POST" action="../../business/usuarioaction.php" enctype="multipart/form-data">

              <div class="form-group">
                <input type="hidden" name="usuarioid" id="usuarioid">

              </div>

              <div class="form-group">
                <label >Nombre:</label>
                <input type="text" class="form-control" name="usuarionombre" id="usuarionombre" placeholder="Ingrese un nombre">
               
              </div>
              
              <div class="form-group">
                <label >Telefono:</label>
                <input type="text" class="form-control" name="usuariotelefono" id="usuariotelefono" placeholder="Ingrese un telefono">
               
              </div>
              <div class="form-group">
                <label >Correo:</label>
                <input type="text" class="form-control" name="usuariocorreo" id="usuariocorreo" placeholder="Ingrese un correo">
               
              </div>
              <div class="form-group">
                <label >Password:</label>
                
                
                <input type="password" class="form-control" name="usuariopassword" id="usuariopassword" placeholder="Ingrese contraseña">
                <div class="form-group">
                   <label class="">
                      <input type="checkbox" id="mostrarPassword" >  Mostrar contraseña
                    </label>

                </div>
                      </div>

              <label>Tipo de usuario: </label>
              <select class="tipoid form-control" name="tipoid" id="tipoid">

                <option selected>Seleccione el tipo de usuario</option>
              
                  <?php foreach($tipousuarios as $tipousuario){

                    echo ' <option value="'.$tipousuario['tipoid'].'" class="badge badge-pill badge-warning" style="font-size: 15px;">'.$tipousuario['tipodescripcion'].'</option>';
                  }?>

            </select>
            <br>
              <center><button type="submit" name="actualizar" class="btn btn-primary">Actualizar</button></center>
            </form>

          </div>

        </div>

      </div>
    </div>

  </div>

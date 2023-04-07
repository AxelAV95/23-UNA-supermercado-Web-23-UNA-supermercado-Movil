<div id="modalEditarUsuario" class="modal fade" role="dialog">

    <div class="modal-dialog">

      <div class="modal-content">

        <div class="modal-header">
          <h4 class="modal-title">Editar usuario</h4>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true" style="font-size:30px">&times;</span>
          </button>
        </div>

        <div class="modal-body">

          <div class="box-body">

            <form id="formulario-editar">

               <input type="hidden" name="usuarioid" id="usuarioid">
                <input type="hidden" name="metodo" value="actualizar">

            

              <div class="form-group">
                     <label>Tipo de usuario: </label>
              <select class="tipoid form-control" name="tipoid" id="tipos">

                <option selected>Seleccione el tipo de usuario</option>
              
                 
            </select>
              </div>
            
            
              <div class="form-group">
                <label >Password:</label>
                
                
                <input type="password" class="form-control" name="usuariopassword" id="usuariopassword" placeholder="Ingrese nueva contraseña">
            
              </div>

           
            <br>
              <center><button type="button" name="actualizar" id="actualizar" class="btn btn-primary">Actualizar</button></center>
            </form>

          </div>

        </div>

      </div>
    </div>

  </div>

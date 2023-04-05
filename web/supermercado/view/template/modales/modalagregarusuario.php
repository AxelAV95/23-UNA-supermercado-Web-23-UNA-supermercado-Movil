<div id="modalAgregarUsuario" class="modal fade" role="dialog">
  
  <div class="modal-dialog">

    <div class="modal-content">

        <div class="modal-header">
          <h4 class="modal-title">Agregar usuario</h4>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>

        <div class="modal-body">

          <div class="box-body">

            <form method="POST" action="../../business/usuarioaction.php"  enctype="multipart/form-data">
           

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
                <input type="text" class="form-control" name="usuariopassword" id="usuariopassword" placeholder="Ingrese contraseña">
                      </div>

                <label>Tipo de Usuario: </label>
              <select class="tipoid form-control" name="tipoid" id="tipoid" >

                <option selected>Seleccione el tipo de usuario</option>
              
                  <?php foreach($tipousuarios as $tipousuario){

                    echo ' <option value="'.$tipousuario['tipoid'].'" class="badge badge-pill badge-warning" style="font-size: 15px;">'.$tipousuario['tipodescripcion'].'</option>';
                  }?>

            </select>
            <br><br>

             
              
             <center><button type="submit" name="insertar" class="btn btn-primary">Insertar</button></center> 
            </form>

        </div>

      </div>

    </div>
  </div>

</div>
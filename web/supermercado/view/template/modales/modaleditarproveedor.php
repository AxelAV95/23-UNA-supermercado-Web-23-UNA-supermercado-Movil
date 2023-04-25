<div id="modalEditarProveedor" class="modal fade" role="dialog">

    <div class="modal-dialog">

      <div class="modal-content">

        <div class="modal-header">
          <h4 class="modal-title">Editar proveedor</h4>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>

        <div class="modal-body">

          <div class="box-body">

            <form method="POST" action="../business/proveedoraction.php" enctype="multipart/form-data" >

              <div class="form-group">
                <input type="hidden" name="proveedorid" id="proveedorid">

              </div>

              <div class="form-group">
                <label >Nombre:</label>
                <input type="text" class="form-control" name="proveedornombre" id="proveedornombre" placeholder="Ingrese un nombre">
               
              </div>
              
              <div class="form-group">
                <label >Dirección:</label>
                <input type="text" class="form-control" name="proveedordireccion" id="proveedordireccion" placeholder="Ingrese una dirección">
               
              </div>
              <div class="form-group">
                <label >Correo:</label>
                <input type="email" class="form-control" name="proveedorcorreo" id="proveedorcorreo" placeholder="Ingrese un correo">
               
              </div>

              <div class="form-group">
                <label >Teléfono:</label>
                <input type="number" class="form-control" name="proveedortelefono" id="proveedortelefono" placeholder="Ingrese un teléfono" min="1" required pattern="[0-9]+">
               
              </div>
              <div class="form-group">
                <label >Latitud:</label>
                <input type="text" class="form-control" name="proveedorlat" id="proveedorlat" placeholder="Ingrese la latitud">
               
              </div>

              <div class="form-group">
                <label >Longitud:</label>
                <input type="text" class="form-control" name="proveedorlong" id="proveedorlong" placeholder="Ingrese la longitud" >
               
              </div>
              
            <br>
              <center><button type="submit" name="actualizar" class="btn btn-primary">Actualizar</button></center>
            </form>

          </div>

        </div>

      </div>
    </div>

  </div>

<div id="modalEditarProveedor" class="modal fade" role="dialog">

    <div class="modal-dialog">

      <div class="modal-content">

        <div class="modal-header">
          <h4 class="modal-title">Editar proveedor</h4>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true" style="font-size:30px">&times;</span>
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
                <input type="text" class="form-control" name="proveedornombre" id="proveedornombre" placeholder="Ingrese un nombre" required pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ\s]+">
               
              </div>
              
              <div class="form-group">
                <label >Dirección:</label>
                <input type="text" class="form-control" name="proveedordireccion" id="proveedordireccion" placeholder="Ingrese una dirección" required pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ\s]+">
               
              </div>
              <div class="form-group">
                <label >Correo:</label>
                <input type="email" class="form-control" name="proveedorcorreo" id="proveedorcorreo" placeholder="Ingrese un correo" class="validate-email" required pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$">
               
              </div>

              <div class="form-group">
                <label >Teléfono:</label>
                <input type="text" class="form-control" name="proveedortelefono" id="proveedortelefono" placeholder="Ingrese un teléfono" maxlength="8" required pattern="[0-9]{8}">


              </div>

              <div class="form-group">
                <label >Latitud:</label>
                <input type="text" class="form-control" readonly name="proveedorlat" id="proveedorlat" placeholder="Ingrese la latitud">
              </div>

              <div class="form-group">
                <label >Longitud:</label>
                <input type="text" class="form-control" readonly name="proveedorlong" id="proveedorlong" placeholder="Ingrese la longitud" >
              </div> 

           <!--   <div class="form-group">
              <label for="proveedorprovincia">Provincia:</label>
                <select class="form-select" required name="proveedorprovincia" id="proveedorprovincia" required >
                  <option value="1">San José</option>
                  <option value="2">Alajuela</option>
                  <option value="3">Cartago</option>
                  <option value="4">Heredia</option>
                  <option value="5">Guanacaste</option>
                  <option value="6">Puntarenas</option>
                  <option value="7">Limón</option>
                </select>
              </div>-->
              
            <br>
              <center><button type="submit" name="actualizar" class="btn btn-primary">Actualizar</button></center>
            </form>

          </div>

        </div>

      </div>
    </div>

  </div>

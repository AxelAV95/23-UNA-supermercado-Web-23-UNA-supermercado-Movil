<div id="modalAgregarProveedor" class="modal fade" role="dialog">
  
  <div class="modal-dialog">

    <div class="modal-content">

        <div class="modal-header">
          <h4 class="modal-title">Agregar proveedor</h4>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true" style="font-size: 30px">&times;</span>
          </button>
        </div>

        <div class="modal-body">

          <div class="box-body">

            <form method="POST" action="../business/proveedoraction.php"  enctype="multipart/form-data">
           

              <div class="form-group">
                <label >Nombre:</label>
                <input type="text" class="form-control" name="proveedornombre" id="proveedornombre" placeholder="Ingrese un nombre" required>
               
              </div>
              
              <div class="form-group">
                <label >Dirección:</label>
                <input type="text" class="form-control" name="proveedordireccion" id="proveedordireccion" placeholder="Ingrese una direccion" required>
               
              </div>
              <div class="form-group">
                <label >Correo:</label>
                <input type="email" class="form-control" name="proveedorcorreo" id="proveedorcorreo" placeholder="Ingrese un correo" class="validate-email" required>
               
              </div>
              <div class="form-group">
                <label >Teléfono:</label>
                <input type="number" class="form-control" name="proveedortelefono" id="proveedortelefono" placeholder="Ingrese un teléfono" min="1" required pattern="[0-9]+" >
                      </div>

            <br><br>

             
              
             <center><button type="submit" name="insertar" class="btn btn-primary">Insertar</button></center> 
            </form>

        </div>

      </div>

    </div>
  </div>

</div>

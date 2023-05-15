
<div id="modalAgregarTipo" class="modal fade" role="dialog">
  
  <div class="modal-dialog">

    <div class="modal-content">

        <div class="modal-header">
          <h4 class="modal-title">Agregar tipo membresía</h4>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true" style="font-size: 30px;">&times;</span>
          </button>
        </div>

        <div class="modal-body">

          <div class="box-body">

            <form id="formulario-insertar" onsubmit="return validarFormulario();">
           
              <div class="form-group">
                <input type="hidden" name="metodo" value="agregar" onsubmit="return validarFormulario();">
    
                <label>Descripción:</label>
                <input type="text" class="form-control" name="membresiadescripcion" id="membresiadescripcion" placeholder="Ingrese tipo de membresía">
  </div>   
              </div>
                         
             <center><button type="button" name="insertar" id="insertar" class="btn btn-primary">Insertar</button></center> 
            </form>

    </div>

  </div>

</div>
  </div>

</div>
<script src="dist/js/validarMembresia.js"></script>


<div id="modalEditarTipo" class="modal fade mt-5" role="dialog">
  
  <div class="modal-dialog">

    <div class="modal-content">

        <div class="modal-header">
          <h4 class="modal-title">Editar tipo</h4>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>

        <div class="modal-body">

          <div class="box-body">

            <form id="formulario-editar">
              
              <div class="form-group">
                <input type="hidden" name="tipoid" id="tipoid">
                 <input type="hidden" name="metodo" value="actualizar">
              
              </div>

              <div class="form-group">
                <label >Descripción:</label>
                <input type="text" class="form-control" name="tipodescripcion" id="tipodescripcion" placeholder="Ingrese tipo">
               
              </div>
              

             <center><button type="button" name="actualizar" id="actualizar" class="btn btn-primary">Actualizar</button></center> 
            </form>

    </div>

  </div>

</div>
  </div>

</div>
<div id="modalAgregarCategoria" class="modal fade mt-5" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">Agregar categoría</h4>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true" style="font-size: 30px">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <div class="box-body">
          <form method="POST" action="../business/categoriaaction.php" onsubmit="return validarFormulario()">
            <div class="form-group">
              <label>Nombre:</label>
              <input type="text" class="form-control" name="categorianombre" id="categorianombre" placeholder="Ingrese categoría">
              <span id="errorMensaje" style="color: red;"></span>
            </div>
            <center><button type="submit" value="Insertar" name="Insertar" id="Insertar" class="btn btn-primary">Insertar</button></center>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>
<script src="dist/js/validarCategoria.js"></script>


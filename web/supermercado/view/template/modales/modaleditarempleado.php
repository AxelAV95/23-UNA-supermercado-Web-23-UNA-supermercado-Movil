<div id="modalEditarCategoria" class="modal fade" role="dialog">
  
  <div class="modal-dialog">

    <div class="modal-content">

        <div class="modal-header">
          <h4 class="modal-title">Editar categoría</h4>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>

        <div class="modal-body">

          <div class="box-body">

            <form method="POST" action="../../business/categoriaaction.php"  enctype="multipart/form-data">
              
              <div class="form-group">
                <input type="hidden" name="categoriaid" id="categoriaid">
                <input type="hidden" name="categoriacodigo" id="categoriacodigo">
              </div>

              <div class="form-group">
                <label >Descripción:</label>
                <input type="text" class="form-control" name="categoriadescripcion" id="categoriadescripcion" placeholder="Ingrese categoría">
               
              </div>
              


              <div class="form-group">
                <label >Imagen: </label>
                  <input type="file" class="nuevaImagen" name="editarImagen">

                <p class="help-block">Peso máximo de la imagen 2MB</p>

                <img src="img/categorias/default/anonymous.png" class="img-thumbnail previsualizar" width="100px">

              <input type="hidden" name="imagenActual" id="imagenActual">

              </div>
              
             <center><button type="submit" name="actualizar" class="btn btn-primary">Actualizar</button></center> 
            </form>

    </div>

  </div>

</div>
  </div>

</div>

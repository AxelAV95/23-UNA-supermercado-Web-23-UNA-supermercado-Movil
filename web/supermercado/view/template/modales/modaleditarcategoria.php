<div id="modalEditarCategoria" class="modal fade" role="dialog">
  
  <div class="modal-dialog">

    <div class="modal-content">

        <div class="modal-header">
          <h4 class="modal-title">Editar categoría</h4>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true" style="font-size: 30px;">&times;</span>
          </button>
        </div>

        <div class="modal-body">

          <div class="box-body">

            <form method="POST" action="../business/categoriaaction.php"  >
              
              <div class="form-group">
                <input type="hidden" name="categoriaid" id="categoriaid" >

              </div>

              <div class="form-group">
                <label >Nombre:</label>
                <input type="text" class="form-control" name="categorianombre" id="categorianombre" placeholder="Ingrese categoría">
               
              </div>
              


    
              
             <center><button type="submit"  value="actualizar" name="actualizar" id="actualizar"  class="btn btn-primary">Actualizar</button></center> 
            </form>
          </div>
        </div>
      </div>
    </div>
</div>
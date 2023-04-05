 <div id="modalEditarProducto" class="modal fade" role="dialog">

    <div class="modal-dialog">

      <div class="modal-content">

        <div class="modal-header">
          <h4 class="modal-title">Editar producto</h4>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>

        <div class="modal-body">

          <div class="box-body">

            <form method="POST" action="../business/productoaction.php" enctype="multipart/form-data">

              <div class="form-group">
                <input type="hidden" name="productoid" id="productoid">
                <input type="hidden" name="productocodigo" id="productocodigo">

              </div>

              <div class="form-group">
                <label>Nombre:</label>
                <input type="text" class="form-control" name="productonombre" id="productonombre" placeholder="Ingrese nombre">

              </div>
              <div class="form-group">
                <label>Precio: </label>
                <input type="text" class="form-control" name="productoprecio" id="productoprecio" placeholder="Ingrese precio">
              </div>

              <div class="form-group">
                <label>Estado: </label>
                <select class="form-control" name="productoestado" id="productoestado">
                  <option value="1">Disponible</option>
                  <option value="2">No disponible</option>
                </select>
               
              </div>

              <label>Categoria: </label>

              <div class="form-group">
                <select class="productocategoriaid form-control" name="productocategoriaid" id="productocategoriaid">

                <option selected>Seleccione la categoría</option>
              
                  <?php 
                  /*
                  foreach($categorias as $categoria){

                    echo '<script>var idc = $("#productocategoriaid").val()</script>';
                    echo $variable = "<script>document.write(idc)</script>";
                    if($categoria['categoriaid'] == $variable){
                        echo ' <option value="'.$categoria['categoriaid'].'" class="badge badge-pill badge-warning" style="font-size: 15px;" selected>'.$categoria['categoriadescripcion'].'</option>';
                    }else{
                        echo ' <option value="'.$categoria['categoriaid'].'" class="badge badge-pill badge-warning" style="font-size: 15px;">'.$categoria['categoriadescripcion'].'</option>';
                    }
                    
                  }*/?>

               
              </select>
              </div>
              

            

              <div class="form-group">
                <label>Imagen: </label>
                <input type="file" class="nuevaImagen" name="editarImagen">

                <p class="help-block">Peso máximo de la imagen 2MB</p>

                <img src="img/productos/default/anonymous.png" class="img-thumbnail previsualizar" width="100px">

                <input type="hidden" name="imagenActual" id="imagenActual">

              </div>

              <center><button type="submit" name="actualizar" class="btn btn-primary">Actualizar</button></center>
            </form>

          </div>

        </div>

      </div>
    </div>

  </div>
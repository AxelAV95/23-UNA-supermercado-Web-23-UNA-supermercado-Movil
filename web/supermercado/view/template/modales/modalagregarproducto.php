<div id="modalAgregarProducto" class="modal fade" role="dialog">

<div class="modal-dialog">

<div class="modal-content">

  <div class="modal-header">
    <h4 class="modal-title">Agregar producto</h4>
    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
      <span aria-hidden="true" style="font-size:30px">&times;</span>
    </button>
  </div>

  <div class="modal-body">

    <div class="box-body">

    <form method="POST" action="../business/productoaction.php" enctype="multipart/form-data" onsubmit="return validarFormulario();">


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
            <option value="0">No disponible</option>
          </select>
         
        </div>

        <div class="form-group">
          <label>Fecha de ingreso: </label>
          <input type="date" class="form-control" name="productofechaingreso" id="productofechaingreso">
        </div>
        <div class="form-group">
          <label>Stock: </label>
          <input type="text" class="form-control" name="productostock" id="productostock" placeholder="Ingrese stock">
        </div>
        <label>Categoria: </label>

        <div class="form-group">
          <select class="productocategoriaid form-control" name="productocategoriaid" id="productocategoriaid">

            <option selected>Seleccione la categoría</option>

            <?php foreach($categorias as $categoria){

              echo ' <option value="'.$categoria['categoriaid'].'" class="badge badge-pill badge-warning" style="font-size: 15px;">'.$categoria['categorianombre'].'</option>';
            }?>




          </select>
        </div>

        <label>Proveedor: </label>

        <div class="form-group">
          <select class="form-control" name="productoproveedorid" id="productoproveedorid">

            <option selected>Seleccione el proveedor</option>

            <?php foreach($proveedores as $proveedor){

              echo ' <option value="'.$proveedor['proveedorid'].'" class="badge badge-pill badge-warning" style="font-size: 15px;">'.$proveedor['proveedornombre'].'</option>';
            }?>

          </select>
        </div>

        <center><button type="submit" name="insertar" class="btn btn-primary">Insertar</button></center>
      </form>


    </div>

  </div>

</div>
</div>


  </div>
  <script src="dist/js/validarProducto.js"></script>

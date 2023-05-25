
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

          <form id="formulario-insertar" method="POST" action="../business/membresiaaction.php"  enctype="multipart/form-data" >
           
              <div class="form-group">
                <input type="hidden" name="metodo" value="agregar">
    <div>

                 <label>Descripción:</label>
                <input type="text" class="form-control" name="membresiadescripcion" id="membresiadescripcion" placeholder="Ingrese tipo de membresía" required required pattern="^[A-Za-z\s]+$" title="Solo se permiten letras">
   
                
  </div>   
              </div>
                         
             <center><button type="button" name="insertar" id="insertar" class="btn btn-primary">Insertar</button></center> 
            </form>

    </div>

  </div>

</div>
  </div>

</div>
<script>
  document.getElementById('formulario-insertar').addEventListener('submit', function(event) {
    var descripcion = document.getElementById('membresiadescripcion').value;

    // Validar que solo se ingresen letras
    var letras = /^[A-Za-z\s]+$/;
    if (!letras.test(descripcion)) {
      event.preventDefault(); // Evitar el envío del formulario

      // Mostrar alerta de error
      alert('Solo se permiten letras en el campo de descripción.');
    }
  });
</script>



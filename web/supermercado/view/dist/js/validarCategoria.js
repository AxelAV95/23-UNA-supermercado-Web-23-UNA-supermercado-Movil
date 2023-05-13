    function validarFormulario() {
        var input = document.getElementById("categorianombre");
        var inputValue = input.value.trim();
        var regex = /^[a-zA-Z]+$/; // Expresión regular para validar letras

        if (inputValue === "") {
        document.getElementById("errorMensaje").textContent = "El campo no puede estar vacío";
        input.classList.add("is-invalid");
        return false; // Previene el envío del formulario
        } else if (!regex.test(inputValue)) {
        document.getElementById("errorMensaje").textContent = "El campo solo puede contener letras";
        input.classList.add("is-invalid");
        return false; // Previene el envío del formulario
        } else {
        document.getElementById("errorMensaje").textContent = "";
        input.classList.remove("is-invalid");
        return true; // Permite el envío del formulario
        }
    }
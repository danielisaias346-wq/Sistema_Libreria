

//Selecciona el elemento HTML
const btnAgregar = document.getElementById('btn-agregar');
const formulario = document.getElementById('formulario');

//Escuchar el click del boton submit
formulario.addEventListener("submit", function (event) {
    //Evita que la pagina se recargue
    event.preventDefault();

    //llamar a los atributos del html
    const atributos = {
        // Capturamos los valores dentro de la promesa para obtener lo que el usuario escribió en ese instante
        nombre: document.getElementById("form-nombre").value.trim(),
        email: document.getElementById("form-correo").value.trim(),
        username: document.getElementById("form-username").value.trim(),
        password: document.getElementById("form-pass").value.trim()
    };

    //Fetch para realizar el ENDPOINT POST de spring boot 
    fetch("http://localhost:9090/usuario/crear", {//Accede al metodo POST
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(atributos) //Convierte el texto JS en JSON
    })
    //Entonces si reponse es ok resetea los campos y refresca la tabla
        .then(Response => {
            if (Response.ok) {
                formulario.reset();//Limpia los campos
                listarBd();
            }
            //si no emite error
            else {
                alert("No se pudo guardar el usuario en el servidor");
            }
        })
        .catch(error => alert("Error en la petición POST: ", error));

});



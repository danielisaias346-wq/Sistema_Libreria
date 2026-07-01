//Importamos la clase lista.js
import { listarBd } from "./lista.js";

//Selecciona el elemento HTML
const btnAgregar = document.getElementById('btn-primario');
const formulario = document.getElementById('formulario');

//Escuchar el click del boton submit
formulario.addEventListener("submit", function (event) {
    //Evita que la pagina se recargue
    event.preventDefault();

    //llamar a los atributos del html
    const atributos = {
        // Capturamos los valores dentro de la promesa para obtener lo que el usuario escribió en ese instante
        titulo: document.getElementById("form-titulo").value.trim(),
        autor: document.getElementById("form-autor").value.trim(),
        stock: parseInt(document.getElementById("form-stock").value)
    };

    //Fetch para realizar el ENDPOINT POST de spring boot 
    fetch("http://localhost:9090/libro/crear", {//Accede al metodo POST
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
                console.error("No se pudo guardar el libro en el servidor");
            }
        })
        .catch(error => console.error("Error en la petición POST: ", error));

});



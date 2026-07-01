//Js para el html
document.addEventListener("DOMContentLoaded", () => {

    // 1. Capturamos los botones por sus IDs correctos
    const botonAgregar = document.getElementById('btn-primario'); // Asegúrate que en HTML tenga id="btn-agregar"
    const botonModificar = document.getElementById('btn-modificar');

    // 2. FUNCIÓN CON LA PROMESA: Se encarga de validar y procesar la acción
     const procesarLibro = (tipoAccion) => {
        return new Promise((resolve, reject) => {
            // Capturamos los valores dentro de la promesa para obtener lo que el usuario escribió en ese instante
            const idLibro = parseInt(document.getElementById("form-id").value, 10);
            const titulo = document.getElementById("form-titulo").value.trim();
            const autor = document.getElementById("form-autor").value.trim();
            const stock = parseInt(document.getElementById("form-stock").value, 10);

            // Validación base para ambos casos (Campos vacíos o stock inválido)
            if (titulo === '' || autor === '' || isNaN(stock) || stock <= 0) {
                reject("No dejar campos vacíos ni con stock menor o igual a cero.");
                return;
            }

            //-----------------------------------------------------------------------------------------
            // Lógica según el tipo de acción que envió el botón
            if (tipoAccion === 'agregar') {
                resolve("Libro agregado con éxito!");
            }
            else if (tipoAccion === 'modificar') {
                // Para modificar es obligatorio un ID válido
                if (!isNaN(idLibro) && idLibro > 0) {
                    resolve("Libro modificado con éxito!");
                } else {
                    reject("Para modificar necesitas ingresar un ID válido mayor a 0.");
                }
            }
            else {
                reject(" Acción no reconocida.");
            }
            //-----------------------------------------------------------------------------------------

        });
    };

    //-----------------------------------------------------------------------------------------
    // 3. EVENTO CLICK DEL BOTÓN AGREGAR
    if (botonAgregar) {
        botonAgregar.addEventListener('click', () => {
            // Ejecutamos la función pasando la acción 'agregar'
            procesarLibro('agregar')
                .then((mensajeExito) => alert(mensajeExito))   // Si la promesa se resuelve (resolve)
                .catch((mensajeError) => alert(mensajeError)); // Si la promesa falla (reject)
        });
    }

    // 4. EVENTO CLICK DEL BOTÓN MODIFICAR
    if (botonModificar) {
        botonModificar.addEventListener('click', () => {
            // Ejecutamos la función pasando la acción 'modificar'
            procesarLibro('modificar')
                .then((mensajeExito) => alert(mensajeExito))
                .catch((mensajeError) => alert(mensajeError));
        });
    }
    //-----------------------------------------------------------------------------------------

    
});


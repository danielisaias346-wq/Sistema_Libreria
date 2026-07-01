const buscarIdInput = document.getElementById('buscar-id');
const btnBuscar = document.getElementById('btn-buscar');
const listaLibrosTbody = document.getElementById('lista-libros');

const API_URL = 'http://localhost:9090/libro/buscarId'; 

 export async function buscarLibroPorId() {
    const idBuscado = buscarIdInput.value.trim();
    
    if (!idBuscado) {
        alert('Por favor, ingresa un ID para realizar la búsqueda.');
        return;
    }

    try {
        const response = await fetch(`${API_URL}/${idBuscado}`);

        if (!response.ok) {
            throw new Error(`No se encontró ningún libro con el ID: ${idBuscado}`);
        }

        const libro = await response.json();
        
        // 1. Buscamos si la fila ya existe en la tabla visualmente
        // Recorremos todas las filas actuales del tbody
        const filas = listaLibrosTbody.getElementsByTagName('tr');
        let filaEncontrada = null;

        for (let fila of filas) {
            // El ID está en la primera celda (index 0) de la fila
            const idCelda = fila.cells[0].innerText.trim();
            
            if (idCelda === idBuscado) {
                filaEncontrada = fila;
                break;
            }
        }

        // 2. Si la fila ya existe en la pantalla, la resaltamos
        if (filaEncontrada) {
            // Quitamos el resaltado de cualquier otra fila antes
            for (let f of filas) f.style.backgroundColor = '';
            
            // Aplicamos un fondo suave para destacar la fila seleccionada
            filaEncontrada.style.backgroundColor = '#d1ecf1'; 
            filaEncontrada.scrollIntoView({ behavior: 'smooth', block: 'center' });
        } else {
            // 3. Si no estaba en la tabla, la agregamos al principio sin borrar las demás
            const nuevaFila = document.createElement('tr');
            nuevaFila.style.backgroundColor = '#d1ecf1'; // La dejamos resaltada

            // CORRECCIÓN DEL UNDEFINED: 
            // Intentamos con libro.id (minúscula). Si no funciona, verifica si en tu entidad Java se llama diferente.
            nuevaFila.innerHTML = `
                <td class="columna">${libro.id || idBuscado}</td>
                <td class="columna">${libro.titulo || 'N/A'}</td>
                <td class="columna">${libro.autor || 'N/A'}</td>
                <td class="columna">${libro.isbn || 'N/A'}</td>
                <td class="columna">${libro.stock ?? 0}</td>
            `;

            // Insertamos la fila arriba de todo en la tabla
            listaLibrosTbody.insertBefore(nuevaFila, listaLibrosTbody.firstChild);
        }

    } catch (error) {
        alert(error.message);
    }
}

btnBuscar.addEventListener('click', buscarLibroPorId);

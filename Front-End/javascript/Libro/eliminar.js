import { buscarLibroPorId } from "./buscar.js"


const idBuscado = buscarLibroPorId();
const btnEliminar = document.getElementById('btn-eliminar')
const API_URL = 'http://localhost:9090/libro/eliminar';

async function eliminarId(id) {
    const buscarIdInput = prompt("Ingresa el ID que quieres eliminar");

    if (!buscarIdInput || buscarIdInput === '') {
        return;
    }

    const confirmar=confirm("¿Estas Seguro de eliminar el libro?");

    if(!confirmar){
        return;
    }

    try {
        const end_point = await fetch(`${API_URL}/${buscarIdInput}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
                // 'Authorization': 'Bearer TU_TOKEN_AQUÍ' // Descomenta si requiere autenticación
            }
        });

        if (!end_point.ok) {
            alert("Error al eliminar el libro con el ID : " + buscarIdInput);
            return;
        }

        alert(`Libro con el ID ${buscarIdInput} eliminado!!`);

    } catch (error) {
        console.error('Hubo un problema con la petición:', error);
        return false;
    }


}

document.addEventListener('DOMContentLoaded', () => {
    const btnEliminar = document.getElementById('btn-eliminar');
    if (btnEliminar) {
        btnEliminar.addEventListener('click', eliminarId);
    } else {
        console.error("No se encontró el botón con el ID 'btn-eliminar' en el HTML.");
    }
});
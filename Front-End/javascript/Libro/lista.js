//export para exportar la funcion  a otras clases js
export function listarBd() {
    fetch("http://localhost:9090/libro/lista")
        .then(response => response.json())
        .then(libros => {
            // Seleccionamos el tbody de tu tabla
            const listaLibro = document.getElementById("lista-libros");

            // Limpiamos la tabla por si tiene datos previos
            listaLibro.innerHTML = "";

            libros.forEach(lib => {
                // 1. Creamos la fila para el libro
                const fila = document.createElement('tr');

                // 2. Insertamos las celdas (td) con las propiedades correspondientes
                // Nota: Agrega "lib.id" o como se llame el campo del ID en tu entidad de Spring Boot
                fila.innerHTML = `
                <tr data-id=${lib.idLibro}>
                <td>${lib.idLibro}</td>
                <td>${lib.titulo}</td>
                <td>${lib.autor}</td>
                <td>${lib.isbn}</td>
                <td>${lib.stock}</td>
                </tr> 
            `;

                // 3. Agregamos la fila armada al cuerpo de la tabla (tbody)
                listaLibro.appendChild(fila);
            });
        })
        .catch(error => console.error("Error al cargar datos : " + error));

}

listarBd();

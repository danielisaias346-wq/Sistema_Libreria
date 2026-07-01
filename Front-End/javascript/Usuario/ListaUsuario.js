//export para exportar la funcion  a otras clases js
 function listarBd() {
    fetch("http://localhost:9090/usuario/lista")
        .then(response => response.json())
        .then(usuarios => {
            // Seleccionamos el tbody de tu tabla
            const listaUsuario = document.getElementById("lista-usuarios");


            // Limpiamos la tabla por si tiene datos previos
            listaUsuario.innerHTML = "";

            usuarios.forEach(user => {
                // 1. Creamos la fila para el libro
                const fila = document.createElement('tr');

                
            // --- FORMATEO DE FECHA EN JAVASCRIPT ---
                let fechaFormateada = "Sin fecha";
                if (user.fechaRegistro) {
                    // Convertimos el array o string de Spring Boot a un objeto Date de JS
                    const fechaObj = new Date(user.fechaRegistro);
                    // Formato estándar Latinoamericano / Español (DD/MM/AAAA)
                    fechaFormateada = fechaObj.toLocaleDateString('es-ES', {
                        day: '2-digit',
                        month: '2-digit',
                        year: 'numeric'
                    });
                }

                // 2. Insertamos las celdas (td) con las propiedades correspondientes
                // Nota: Agrega "lib.id" o como se llame el campo del ID en tu entidad de Spring Boot
                fila.innerHTML = `
                <tr data-id=${user.idUsuario}>
                <td>${user.idUsuario}</td>
                <td>${user.nombre}</td>
                <td>${user.email}</td>
                <td><time datetime="${user.fechaRegistro}">${fechaFormateada}</time></td>
                <td>${user.username}</td>
                <td>${user.password}</td>

                </tr> 
            `;

                // 3. Agregamos la fila armada al cuerpo de la tabla (tbody)
                listaUsuario.appendChild(fila);
            });
        })
        .catch(error => console.error("Error al cargar datos : " + error));

}

listarBd();

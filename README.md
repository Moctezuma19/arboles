# Prueba técnica
Autor: Jorge Erick Rivera López
## Descripción
La API sirve para realizar operaciones sobre árboles binarios de búsqueda.
## Ambiente de desarrollo
Fue realizado con **Spring Tools Suite 4**, **Java 11**, **Spring boot**.
## Uso
Al correr la aplicación utilizando **maven** o **sts4**, la API estará disponible a través de la url ´http://localhost:8080/v1/b-trees´ a menos que se haya modificado el puerto de servicio.
## /height (disponible a través de POST)
Recibe un json con una arreglo de enteros, conforme se leen los enteros en orden se agregan al árbol de búsqueda, se devuelve como respuesta la altura del árbol (ej. `{ "toTree": [2,1,3]}` devuelve `{ "height": 1}`).
## /neighbors (disponible a través de POST)
Recibe un json con una arreglo de enteros y el valor a obtener su número de vecinos, conforme se leen los enteros en orden se agregan al árbol de búsqueda, se devuelve como respuesta la altura del árbol (ej. `{ "toTree": [2,1,3], "node": 1}` devuelve `{ "neighbors": { "left": "null","left": [3]}}`).
## /bfs (disponible a través de POST)
Recibe un json con una arreglo de enteros y el valor a obtener su número de vecinos, conforme se leen los enteros en orden se agregan al árbol de búsqueda, se devuelve como respuesta la altura del árbol (ej. `{ "toTree": [3,4,1,2,-1]}` devuelve `{ "bfs": [3,1,4,-1,2]}`).
### Nota final
El primer commit refleja la primera versión hecha.

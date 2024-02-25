# Prueba Tecnica
Este proyecto Java utiliza Docker para facilitar la ejecución y despliegue. Sigue las instrucciones a continuación para configurar y ejecutar el proyecto utilizando Docker y Docker Compose.

## Requisitos Previos
Docker debe estar instalado en tu sistema.
Instrucciones para Ejecutar el Proyecto
Clonar el Repositorio:

bash
Copy code
git clone https://github.com/DuvanR11/pruebaTecnica.git
cd pruebaTecnica
Construir la Imagen Docker:

bash
Copy code
docker build -t prueba-tecnica .
Ejecutar el Contenedor usando Docker Compose:

bash
Copy code
docker-compose up -d
Esto iniciará dos servicios: app (la aplicación Spring Boot) y mysql (la base de datos MySQL).

## Acceder a MySQL:

Abre tu cliente MySQL y conecta a la base de datos con las siguientes credenciales:
Host: localhost
Puerto: 3306
Usuario: root
Contraseña: (deja en blanco)
Crea la base de datos bank.
Acceder a la Aplicación:

La aplicación estará disponible en http://localhost:8080.

Detener y Limpiar los Contenedores:

bash
Copy code
docker-compose down

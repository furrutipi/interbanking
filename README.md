# Interbanking API

Este es un microservicio desarrollado en Java 21 para el manejo de operaciones interbancarias. La aplicación utiliza Docker para la infraestructura, Flyway para la gestión de migraciones de base de datos, y Swagger para la documentación de los endpoints.

## Requisitos

- **Java 21**: Asegúrate de tener Java 21 instalado en tu máquina para poder compilar y ejecutar la aplicación.
- **Docker**: Es necesario instalar Docker para soportar la infraestructura necesaria para el microservicio.

## Instalación

### 1. Instalación de Docker

Asegúrate de tener Docker instalado en tu máquina. Si no lo tienes, puedes seguir la [guía oficial de Docker](https://docs.docker.com/get-docker/) para instalarlo según tu sistema operativo.

### 2. Configuración de la infraestructura con Docker Compose

Una vez que Docker esté instalado, navega hasta la carpeta raíz del proyecto y ejecuta el archivo docker-compose.yml



### 3. Compilación y descarga de dependencias
Antes de ejecutar la aplicación, debes compilarla y descargar las dependencias. Para ello, ejecuta el siguiente comando en la raíz del proyecto:

mvn clean install

### 4. Acceso a Swagger
Una vez que la aplicación esté en funcionamiento, puedes acceder a la documentación de la API en Swagger a través del siguiente enlace:

Swagger UI

### 5. Flyway y Migraciones de Base de Datos
La aplicación utiliza Flyway para gestionar el esquema y las migraciones de base de datos. Los scripts de migración se encuentran en el siguiente directorio:

src/main/resources/db/migrations
Estos scripts se ejecutarán automáticamente durante el arranque de la aplicación para crear el esquema de la base de datos y poblarla con datos de prueba.

### 6. Datos de Conexión a la Base de Datos
Los datos de conexión a la base de datos se encuentran en los siguientes archivos:

docker-compose.yml

src/main/resources/application.properties

Revisa estos archivos para configurar las credenciales y otros parámetros de la base de datos según tus necesidades.

### 7. Pruebas de Integración
Existen pruebas de integración configuradas para verificar el correcto funcionamiento del microservicio. Estas pruebas utilizan una base de datos en memoria H2 para validar las funcionalidades sin afectar la base de datos de producción.

### 8. Endpoints
La API proporciona tres endpoints principales que están documentados en Swagger:

Endpoint 1: api/v.1/company/register

Endpoint 2: api/v.1/company/transfer_last_month

Endpoint 3: /api/v.1/company/adhered_last_month

Puedes consultar los detalles de estos endpoints directamente en la interfaz de Swagger.
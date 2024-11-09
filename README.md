# Computer Shop API

API REST para la gestión de computadoras y tiendas en una aplicación de inventario de equipos informáticos. Desarrollada con Spring Boot y diseñada para interactuar con una base de datos relacional usando JPA y Hibernate.

## Características

- **Gestión de Computadoras**: Permite agregar, listar, buscar, actualizar y eliminar computadoras.
- **Gestión de Tiendas**: Asocia cada computadora a una tienda específica y permite borrar computadoras de una tienda específica según la marca.
- **Validaciones**: Implementa validaciones de campos, como no permitir campos nulos para las propiedades esenciales.
- **Búsqueda**: Incluye métodos para encontrar computadoras por marca y para borrar computadoras de una tienda específica por marca.

## Tecnologías Utilizadas

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- H2 Database (para pruebas en memoria)
- Maven

## Instalación

1. **Clonar el repositorio**:
   
   git clone https://github.com//computer-shop-api.git
  
   
3.  **Configurar la Base de Datos**:
   El proyecto está configurado para usar una base de datos en memoria H2 por defecto. Puedes cambiar esto en el archivo application.properties si deseas usar una base de datos relacional como MySQL.

4. **Compilar y Ejecutar el Proyecto**:
   Ejecuta el siguiente comando para compilar y lanzar la aplicación:
   mvn spring-boot:run


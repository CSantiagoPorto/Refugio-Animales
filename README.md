# Refugio-Animales 

API REST para la gestión de un refugio de animales, desarrollada como práctica del módulo de Acceso a Datos.

## ¿Qué hace?

Permite gestionar los animales de un refugio y sus cuidadores a través de una API con operaciones CRUD completas, incluyendo validaciones de lógica de negocio.

**Animales**
- Listar todos los animales o buscar por ID
- Crear, actualizar y eliminar animales
- Respuestas con códigos HTTP apropiados (200, 204, 404)

**Cuidadores**
- Listar todos los cuidadores o buscar por ID
- Consultar los animales asignados a un cuidador concreto
- Crear, actualizar y eliminar cuidadores
- Login por email y contraseña
- Validación: no se puede eliminar un cuidador que tenga animales asignados

## Tecnologías utilizadas

- **Java**
- **Spring Boot** — framework principal
- **Spring Security** — configuración de seguridad y CORS
- **Spring Data JPA / Hibernate** — acceso a datos
- **MySQL** — base de datos relacional
- **Maven** — gestión de dependencias
- **Postman** — pruebas de los endpoints

## Estructura del proyecto

```
src/main/java/com/example/refugio/
├── controller/          # Endpoints REST (AnimalController, CuidadorController)
├── model/               # Entidades JPA (Animal, Cuidador)
├── repository/          # Repositorios Spring Data
├── services/            # Interfaces de servicio
│   └── implementacion/  # Implementaciones de los servicios
└── security/            # Configuración de seguridad y CORS
```

## Endpoints

### Animales `/api/animales`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/animales` | Listar todos los animales |
| GET | `/api/animales/{id}` | Obtener un animal por ID |
| POST | `/api/animales` | Crear un animal |
| PUT | `/api/animales/{id}` | Actualizar un animal |
| DELETE | `/api/animales/{id}` | Eliminar un animal |

### Cuidadores `/api/cuidadores`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/cuidadores` | Listar todos los cuidadores |
| GET | `/api/cuidadores/{id}` | Obtener un cuidador por ID |
| GET | `/api/cuidadores/{id}/animales` | Listar los animales de un cuidador |
| POST | `/api/cuidadores` | Crear un cuidador |
| POST | `/api/cuidadores/login` | Login por email y contraseña |
| PUT | `/api/cuidadores/{id}` | Actualizar un cuidador |
| DELETE | `/api/cuidadores/{id}` | Eliminar un cuidador |

##  Cómo ejecutar

1. Clonar el repositorio
2. Configurar la base de datos MySQL en `application.properties`
3. Abrir con IntelliJ IDEA
4. Ejecutar con Maven: `./mvnw spring-boot:run`
5. Probar los endpoints con Postman

##  Notas

Proyecto desarrollado durante la formación en el ciclo de Desarrollo de Aplicaciones Multiplataforma.

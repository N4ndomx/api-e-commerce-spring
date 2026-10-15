# TravelUno

API REST de una agencia de viajes construida con **Spring Boot**. Permite administrar
vuelos, hoteles, tickets, reservas, tours y clientes, exponiendo operaciones de
consulta, creación, actualización y borrado sobre una base de datos **PostgreSQL**.

- **Paquete base:** `com.nandomx.travelUno`
- **Context path:** `/best`
---

## Tecnologías y herramientas

| Categoría            | Herramienta / Versión                     |
| -------------------- | ----------------------------------------- |
| Lenguaje             | Java 17                                   |
| Framework            | Spring Boot 3.5.14                        |
| API REST             | Spring Web (Spring MVC)                   |
| Persistencia         | Spring Data JPA + Hibernate               |
| Base de datos        | PostgreSQL 15.2                           |
| Pool de conexiones   | HikariCP                                  |
| Validación           | Spring Boot Starter Validation (Jakarta)  |
| Reducción de boilerplate | Lombok                                |
| Desarrollo           | Spring Boot DevTools                      |
| Build / Wrapper      | Maven (`mvnw` / `mvnw.cmd`)               |
| Pruebas              | JUnit 5 + Spring Boot Test                |

---

## Arquitectura

Proyecto monolítico con **arquitectura en capas**. Cada capa tiene una
responsabilidad clara y se comunica con la siguiente mediante interfaces.

```
com.nandomx.travelUno
├── api                    → Capa de entrada (HTTP)
│   ├── controllers        → Endpoints REST
│   └── models
│       ├── requests       → DTOs de entrada (payloads)
│       └── responses      → DTOs de salida
├── domain                 → Capa de dominio / persistencia
│   ├── entities           → Entidades JPA (mapeo a tablas)
│   └── repositories       → Repositorios Spring Data JPA
├── infra                  → Capa de infraestructura / negocio
│   ├── abstrac_service    → Interfaces de servicios (contratos)
│   └── services           → Implementación de la lógica de negocio
└── util                   → Utilidades y enumeraciones de apoyo
```

### Flujo de una petición

```
Cliente HTTP
   │
   ▼
Controller (api)  ──usa──▶  Service (infra)  ──usa──▶  Repository (domain)
   ▲                              │                          │
   │                              ▼                          ▼
Response DTO  ◀────────────  Entity  ◀────────────  PostgreSQL
```

1. El **Controller** recibe la petición y valida el `Request` DTO.
2. El **Service** aplica la lógica de negocio (cálculo de precios, fechas, etc.).
3. El **Repository** accede a la base de datos a través de JPA/Hibernate.
4. Se mapea la entidad a un `Response` DTO (vía `BeanUtils.copyProperties`) y se
   devuelve envuelto en un `ResponseEntity`.

### Descripción de capas

- **api/controllers** — Controladores REST anotados con `@RestController`. Todos
  devuelven `ResponseEntity` y delegan la lógica a los servicios.
- **api/models** — DTOs desacoplados de las entidades: `*Request` para entrada y
  `*Response` para salida.
- **domain/entities** — Entidades JPA (`FlyEntity`, `HotelEntity`, `TicketEntity`,
  `ReservationEntity`, `TourEntity`, `CustomerEntity`) con relaciones
  `@OneToMany` / `@ManyToOne`.
- **domain/repositories** — Interfaces que extienden `JpaRepository`. Incluyen
  *query methods* derivados (ej. `findByPriceLessThan`) y consultas `@Query` en
  JPQL (ej. `selectOriginDestiny`).
- **infra/abstrac_service** — Contratos genéricos reutilizables:
  - `ICrudService<RQ, RS, ID>` → `create`, `read`, `update`, `delete`.
  - `SimpleCrudService<RQ, RS, ID>` → `create`, `read`, `delete`.
  - `ICatalogoService<RS>` → operaciones de catálogo con paginación y filtros.
- **infra/services** — Implementaciones anotadas con `@Service`; algunas usan
  `@Transactional` (`ReservationService`, `HotelService`).
- **util** — Enumeraciones (`SortType`, `AeroLine`) y utilidades de fechas
  (`TravelOneUtil`).

## Endpoints principales

Todas las rutas están prefijadas por el context path **`/best`**.

### Vuelos — `/best/fly`

| Método | Ruta                              | Descripción                                |
| ------ | --------------------------------- | ------------------------------------------ |
| GET    | `/fly`                            | Lista paginada (params `page`, `size`; header `sortType`) |
| GET    | `/fly/origin_destiny?ori=&des=`   | Vuelos por origen y destino                |
| GET    | `/fly/lessPrice?price=`           | Vuelos con precio menor al indicado        |
| GET    | `/fly/betwenPrice?priceMin=&priceMax=` | Vuelos dentro de un rango de precio   |

### Hoteles — `/best/hotel`

| Método | Ruta                              | Descripción                          |
| ------ | --------------------------------- | ------------------------------------ |
| GET    | `/hotel?page=&size=`              | Lista paginada (header `sortType`)   |
| GET    | `/hotel/lessPrice?price=`         | Hoteles con precio menor             |
| GET    | `/hotel/betwenPrice?min=&max=`    | Hoteles dentro de un rango de precio |
| GET    | `/hotel/rating?ranting=`          | Hoteles con rating mayor al indicado |

### Tickets — `/best/ticket`

| Método | Ruta                 | Descripción                        |
| ------ | -------------------- | ---------------------------------- |
| POST   | `/ticket`            | Crea un ticket                     |
| GET    | `/ticket?flyId=`     | Precio calculado de un vuelo       |
| GET    | `/ticket/{ticketId}` | Obtiene un ticket por UUID         |
| PUT    | `/ticket/{ticketId}` | Actualiza un ticket                |
| DELETE | `/ticket/{ticketId}` | Elimina un ticket                  |

### Reservas — `/best/reservation`

| Método | Ruta                        | Descripción                     |
| ------ | --------------------------- | ------------------------------- |
| POST   | `/reservation`              | Crea una reserva                |
| GET    | `/reservation/{reservationId}` | Obtiene una reserva por UUID |
| PUT    | `/reservation/{id}`         | Actualiza una reserva           |
| DELETE | `/reservation/{id}`         | Elimina una reserva             |

> El endpoint de **tours** (`TourService`) existe como esqueleto y aún no está
> expuesto vía controller.

---

## Puesta en marcha

### Requisitos

- Java 17
- Docker y Docker Compose (para la base de datos)

### 1. Levantar la base de datos

```bash
docker compose up -d
```

Esto arranca PostgreSQL 15.2 en el puerto `5432` y ejecuta automáticamente
`db/sql/create_schema.sql` y `db/sql/data.sql` para crear el esquema y cargar
datos de prueba.

Credenciales por defecto (ver `docker-compose.yml` y `application.properties`):

| Parámetro | Valor      |
| --------- | ---------- |
| Base      | `best_one` |
| Usuario   | `nando`    |
| Password  | `root`     |
| Puerto    | `5432`     |

### 2. Ejecutar la aplicación

```bash
./mvnw spring-boot:run
```

La API quedará disponible en `http://localhost:8080/best`.

### 3. Compilar / empaquetar

```bash
./mvnw clean package
```

### 4. Ejecutar pruebas

```bash
./mvnw test
```

---

## Configuración relevante (`application.properties`)

- `server.servlet.context-path=/best`
- `spring.datasource.url=jdbc:postgresql://localhost:5432/best_one`
- Pool HikariCP con `maximum-pool-size=5` y detección de fugas a 30s.
- Logging de SQL/Hibernate en `DEBUG` para desarrollo.
- Manejo de errores: se incluye el mensaje y la excepción, nunca el stacktrace.

---
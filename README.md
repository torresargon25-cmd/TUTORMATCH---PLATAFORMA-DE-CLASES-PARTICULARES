# TutorMatch

### Plataforma web para la gestión de clases particulares

Conecta **estudiantes** con **tutores** por materia y etapa educativa: búsqueda de profesores, reserva de sesiones con validaciones automáticas, cálculo del coste y un panel de administración con estadísticas.

---


## ¿Qué es TutorMatch?

La gestión de clases particulares suele llevarse de forma dispersa (papel, hojas de cálculo, chats), lo que provoca horarios solapados, dudas sobre el precio final y falta de un registro centralizado. **TutorMatch** resuelve ese problema con una aplicación web única donde:

- el **administrador** da de alta tutores, estudiantes y materias y controla todas las sesiones;
- el **estudiante** busca al tutor ideal por especialidad y **reserva** sus clases;
- el **tutor** consulta sus sesiones y sus estudiantes.

Toda la actividad queda registrada, **validada** (duración, solapamientos, nivel) y con su **coste calculado automáticamente**.

---

## Funcionalidades por rol

###  Administrador
- CRUD completo de **tutores, estudiantes, materias y sesiones**.
- **Panel** con totales y rankings: tutores con más sesiones, materias más demandadas y estudiantes con más tutorías.
- Consulta de **sesiones por rango de fechas** y **filtrado por estado** (programada / finalizada / cancelada).

###  Tutor
- Consulta de **sus sesiones** (con filtro por estado).
- Consulta de **sus estudiantes** asignados.

###  Estudiante
- **Búsqueda de tutores** por especialidad, viendo sus horarios ocupados.
- **Reserva de sesiones** con validaciones y cálculo de coste, recargos y descuentos.
- **Mis clases**: seguimiento con filtros, coste acumulado y cancelación de sesiones.
- **Mi perfil**, con los datos del tutor legal si el estudiante es menor de edad.

---

##  Reglas de negocio

| Regla | Descripción |
|-------|-------------|
|  Cálculo de coste | `horas × tarifa/hora` del tutor, redondeado a 2 decimales |
|  Sin solapamientos | Ni el tutor ni el estudiante pueden tener dos sesiones que se pisen |
|  Recargo por urgencia | **+20 %** si la sesión empieza en menos de 24 h |
|  Descuento por fidelidad | **−10 %** si el estudiante ya tiene más de 3 sesiones con ese tutor |

---

## Modelo de datos

`Usuario` es una clase **abstracta** (herencia `JOINED`) de la que heredan `Tutor` y `Estudiante`. `SesionTutoria` es la entidad central que une a un estudiante, un tutor y una materia.

**Enums:** `Rol` (ADMIN, TUTOR, ESTUDIANTE) · `NivelEstudiante` / `NivelDificultadM` (ALTO, MEDIO, BAJO) · `EtapaEducativa` (PRIMARIA, ESO, BACHILLERATO) · `EstadoSesion` (PROGRAMADA, CANCELADA, FINALIZADA).

>  El **administrador no es una entidad propia**: es un `Tutor` con `rol = ADMIN`.



##  Puesta en marcha

### Pasos


### 1. Abre la aplicación

---

##  Usuarios de prueba

| Usuario  | Contraseña | Rol           | Descripción |
|----------|------------|---------------|-------------|
| `admin`  | `admin`    | Administrador | Acceso total: gestión y estadísticas |
| `tutor`  | `tutor`    | Tutor         | Carlos Ruiz — ve sus sesiones y estudiantes |
| `user`   | `user`     | Estudiante    | Gonzalo Torres — busca y reserva clases |
| `ana`    | `ana123`   | Estudiante    | Estudiante **menor de edad** (con tutor legal) |
| `pablo`  | `pablo123` | Estudiante    | Estudiante de nivel alto |
| `laura`  | `tutor2`   | Tutor         | Segunda tutora de ejemplo |

---

##  Consola de la base de datos (H2)

Con la aplicación en marcha, accede a:


| Campo        | Valor |
|--------------|-------|
| JDBC URL     | `jdbc:h2:./db/basedatos` |
| Usuario      | `sa` |
| Contraseña   | _(vacía)_ |

---

## Autor

**Gonzalo Torres Arroyo**
Proyecto Final · 1.º DAM  · Salesianos Triana

---

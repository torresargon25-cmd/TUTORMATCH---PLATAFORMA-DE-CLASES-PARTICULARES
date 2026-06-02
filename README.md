# TUTORMATCH- App para gestión de sesiones entre alumnos y profesores

## Credenciales de acceso

### Username: user, Password: user
### Username: admin, Password: admin
### Username: tutor, Password: tutor

## Funcionalidades

### Administrador
- Gestión completa de tutores, estudiantes, materias y sesiones (CRUD)
- Panel de estadísticas: tutores con más sesiones y materias más demandadas
- Consulta de sesiones por rango de fechas
- Filtrado de sesiones por estado

### Tutor
- Visualización de sus sesiones programadas
- Listado de sus estudiantes asignados

### Estudiante
- Búsqueda de tutores por especialidad con visualización de horarios ocupados
- Reserva de sesiones con validaciones automáticas
- Consulta de sus clases con filtros por estado y coste acumulado
- Perfil personal con datos del tutor legal si es menor de edad

## Lógica de negocio

- Cálculo automático del coste de la sesión según tarifa del tutor y duración
- No solapamiento de sesiones para tutor y estudiante
- Restricción de nivel: estudiante BAJO no puede acceder a materias de dificultad ALTA

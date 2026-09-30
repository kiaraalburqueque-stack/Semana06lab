# TecsupStore - Lab 06

**Estudiante:** Kiara Alburqueque  

---

## I. Preguntas de reflexión

* **¿Por qué el DropdownMenu se declara dentro de un Box junto al ícono que lo activa, y no en cualquier parte de la pantalla?**  
  En Jetpack Compose, el `DropdownMenu` requiere un punto de anclaje posicional dentro del árbol de composición para saber dónde renderizarse. Al colocar tanto el `IconButton` de activación como el `DropdownMenu` dentro del mismo contenedor `Box`, el menú utiliza las coordenadas de ese contenedor como referencia directa para desplegarse justo debajo del ícono de tres puntos. Declararlo en otra parte rompería esa relación posicional.

* **¿Qué diferencia de alcance hay entre las opciones del DropdownMenu (afectan solo a un producto) y las del NavigationDrawer (afectan a toda la app)?**  
  * **DropdownMenu (Alcance Local / Componente):** Sus opciones gestionan únicamente el estado o la lógica de una tarjeta/producto en específico (por ejemplo, marcar un producto como favorito, ver detalles o reportarlo).  
  * **NavigationDrawer (Alcance Global / Aplicación):** Sus opciones controlan el flujo de la aplicación a nivel general, permitiendo alternar entre diferentes vistas/pantallas (`Inicio`, `Favoritos`, `Perfil`) y realizar acciones globales como cerrar sesión.

* **¿Cómo tuviste que estructurar tu código para que el contador de favoritos del drawer "se entere" de lo que pasa en el DropdownMenu de cada producto?**  
  Se implementó el patrón de **Elevación de Estado (State Hoisting)**. El estado que guarda la lista o el conteo de favoritos se ubicó en un nivel superior (`AppNavegacion`), fuera de las tarjetas individuales. Al presionar "Agregar a Favoritos" dentro del `DropdownMenu` de una `TarjetaProducto`, se ejecuta un *callback* hacia el contenedor padre que actualiza el contador general, re-renderizando el `NavigationDrawer` y su *badge* automáticamente.

* **¿Qué tuviste que corregir del código que te generó la IA para la mejora del badge de favoritos?**  
  Se corrigieron los errores de referencias no resueltas de íconos agregando la dependencia `androidx.compose.material:material-icons-extended` en el archivo `build.gradle.kts`. Además, se ajustaron las llamadas de los composables para pasar correctamente el parámetro `currentRoute` entre `AppNavegacion` y `AppDrawer`, asegurando que el ítem activo del drawer se resalte adecuadamente sin conflictos de compilación.

---

## VII. Observaciones y conclusiones

### Observaciones
1. **Resolución de dependencias en Gradle:** Durante la adición de íconos extendidos (`FavoriteBorder`, `Warning`, `ExitToApp`), aparecieron errores de `Unresolved reference 'Icons'`. Se resolvió agregando la librería `material-icons-extended` en `build.gradle.kts (Module :app)` y ejecutando la sincronización de Gradle (`Sync Now`).
2. **Organización del paquete y código:** Se debió verificar cuidadosamente que toda la estructura de componentes (`ui.components` y `ui.navigation`) perteneciera exclusivamente al paquete `com.alburqueque.tecsupstore` dentro de `main/java`, evitando ubicar por error archivos en las carpetas de pruebas (`androidTest` o `test`).

### Conclusiones
1. **Modularidad en Jetpack Compose:** Separar la interfaz en componentes independientes reutilizables (`TarjetaProducto`, `AppDrawer`, `AppNavegacion`) mejora la legibilidad y mantenimiento del proyecto, permitiendo escalar vistas sin duplicar código.
2. **Gestión de ramas y control de versiones con Git:** Organizar las actividades en dos fases (Fase 1 en `main` para la estructura básica y Fase 2 en `mejora-ia` para las optimizaciones) demostró ser una práctica fundamental para trabajar de manera ordenada, permitiendo validar cambios antes de integrarlos al flujo principal.

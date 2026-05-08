# Casa de empeño


## Descripción del Proyecto

Sistema de gestión integral para casas de empeño que permite controlar el ciclo completo del negocio: 
desde el registro del cliente y el cálculo automático del préstamo, pasando por el seguimiento de 
pagos e intereses, hasta la venta de artículos no reclamados.


## Equipo de Desarrollo

| Nombre                         | Rol                    | Contacto                 |
|--------------------------------|------------------------|--------------------------|
| Green Vizcarra Fabian Lombardo | Desarrollador Frontend | fgreen_24@alu.uabcs.mx   |
| Ornelas Ramírez Carlos Daniel  | Desarrollador Backend  | cornelas_24@alu.uabcs.mx |
| Mireles Jacome María Fernanda  | Diseñadora             | mmireles_24@alu.uabcs.mx |
| Mendez Cortez Isbeth           | QA                     | isbethm_24@alu.uabcs.mx  |



## Tecnologías Utilizadas

- **Java JDK 11** - Lenguaje de programación principal
- **Eclipse 2025-09 (4.37.0)** - Entorno de desarrollo
- **Git & GitHub** - https://github.com/FgreenZ/Casa-de-emepenio


### Prerrequisitos

- Java JDK 24

# Problema al importar el proyecto

Al importar el proyecto, este no incluirá la librería de Java necesaria. Aparecerá un signo de admiración "!" en el proyecto y no se encontrarán las clases al momento de ejecutar el `Main`.

## Solución

Para poder ejecutarlo correctamente es necesario seguir los siguientes pasos:

| Paso | Instrucción |
|------|-------------|
| 1 | Doble clic en el proyecto |
| 2 | Build Path |
| 3 | Configure Build Path |
| 4 | Pestaña Libraries |
| 5 | Borrar el JRE que aparezca |
| 6 | Clic en Modulepath |
| 7 | Add Library |
| 8 | Execution Environment |
| 9 | Seleccionar una librería que **no sea Unbound** |
| 10 | Apply and Close |


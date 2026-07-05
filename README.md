# task-manager-quality

[![CI Pipeline](https://github.com/Wikiniki15/task-manager-quality/actions/workflows/ci.yml/badge.svg)](https://github.com/Wikiniki15/task-manager-quality/actions/workflows/ci.yml)

Sistema de gestión de tareas por consola en Java, desarrollado como proyecto de la asignatura **Calidad de Software (ISWZ3208)**. Parte de una plantilla base con problemas de calidad y aplica **Clean Code**, principios **SOLID**, análisis estático y un pipeline de **CI/CD**.

## Características

- Alta, baja y listado de tareas en memoria.
- Validación de entradas (nombres nulos/vacíos, IDs inválidos, duplicados).
- Arquitectura por capas con inversión de dependencias.

## Arquitectura

```
Main → TaskService → TaskRepository (interfaz)
                          ▲
                          └── InMemoryTaskRepository
TaskPrinter → TaskService        Task (entidad inmutable)
```

- **SRP:** cada clase con una responsabilidad (`model`, `repository`, `service`, `ui`).
- **DIP:** `TaskService` depende de la abstracción `TaskRepository`.
- **OCP:** se pueden añadir nuevas implementaciones del repositorio sin modificar el servicio.

## Estructura del proyecto

```
src/
├── main/java/udla/taskmanager/
│   ├── Main.java
│   ├── model/Task.java
│   ├── repository/TaskRepository.java
│   ├── repository/InMemoryTaskRepository.java
│   ├── service/TaskService.java
│   └── ui/TaskPrinter.java
└── test/java/udla/taskmanager/
    ├── MainTest.java
    ├── repository/InMemoryTaskRepositoryTest.java
    ├── service/TaskServiceTest.java
    └── ui/TaskPrinterTest.java
```

## Requisitos

- JDK 17 (el proyecto compila con `release` 11).
- Apache Maven 3.9+.

## Compilar y ejecutar

```bash
mvn clean compile
mvn exec:java -Dexec.mainClass=udla.taskmanager.Main
```

## Pruebas y cobertura

```bash
mvn test
```

El reporte de cobertura (JaCoCo) queda en `target/site/jacoco/index.html`.
Cobertura de líneas actual: **98.2 %**.

## Análisis estático

```bash
mvn checkstyle:check   # estilo de código
mvn pmd:check          # malas prácticas y defectos
mvn spotbugs:check     # bugs sobre bytecode
```

| Herramienta | Configuración |
|---|---|
| Checkstyle | `checkstyle.xml` |
| PMD | `pmd-ruleset.xml` |
| SpotBugs | `spotbugs-exclude.xml` |
| JaCoCo | `jacoco-maven-plugin` |

## Integración continua

El workflow [`.github/workflows/ci.yml`](.github/workflows/ci.yml) se ejecuta en cada `push` y `pull_request` hacia `main` y `develop`: compila, corre las pruebas, ejecuta Checkstyle, PMD y SpotBugs, y publica el reporte de cobertura como artefacto.

## Flujo de trabajo

- **GitFlow:** ramas `feature/*` → `develop` → `main`.
- **Conventional Commits:** `feat:`, `fix:`, `test:`, `ci:`, `docs:`, `chore:`, `refactor:`.

# TP Patrones - Unit Testing & CI/CD

Este proyecto contiene la implementación de los tests unitarios y la configuración de integración y entrega continua (CI/CD) utilizando **GitHub Actions**.

## Tests Implementados
Los tests están ubicados en el directorio `src/test/java/observer/` e incluyen:
1. `ThresholdAlertServiceTest.java`: Verifica la lógica de umbrales en `ThresholdAlertService` aisladamente y sin dependencias.
2. `AlertObserverFakeTest.java`: Verifica `AlertObserver` usando un Fake (manual) de `AlertService` y otro Fake de `Logger`.
3. `AlertObserverMockTest.java`: Verifica el mismo `AlertObserver` pero utilizando la librería **Mockito**.

---

## Cómo ejecutar la Aplicación Principal

Para ejecutar el programa interactivo desde la terminal utilizando **Maven**, deben seguirse estos pasos:

1. **Abrir la terminal.**
2. **Posicionarse en la raíz del proyecto** (la carpeta `practico-ci-cd-ingenieria-de-software-BCCP`).
3. **Compilar y ejecutar el programa:**

```bash
mvn compile exec:java -Dexec.mainClass="Main"
```

*(Nota: Como no hay configuración de package en Main, `exec.mainClass` simplemente llama a `Main`)*

---

## Cómo ejecutar los tests localmente

Para ejecutar todas las pruebas desde la terminal, se debe:

1. **Abrir la terminal.**
2. **Situarse en la raíz del proyecto.**
3. **Ejecutar el siguiente comando:**

```bash
mvn clean test
```

Se verá una salida en consola compilando todo automáticamente y mostrando que los tests pasaron, con el mensaje `BUILD SUCCESS` (mostrando las 11 pruebas correctas).

---

## Automatización con GitHub Actions (CI/CD)

Se ha implementado una arquitectura de automatización robusta basada en **acciones compuestas locales** (*composite actions*) y **workflows**.

### Acciones Reutilizables (Composite Actions)

Las acciones compuestas se encuentran en la carpeta `.github/actions/` y encapsulan lógica reutilizable para mantener los archivos de workflow limpios y modulares:

1. **Compilar Proyecto (`.github/actions/build/action.yml`)**:
   - **Propósito**: Configura el entorno de desarrollo y verifica que el código fuente compile correctamente sin generar artefactos.
   - **Pasos**: 
     - Configura el Java Development Kit (JDK 21) utilizando la distribución Temurin.
     - Habilita la caché de dependencias de Maven para acelerar ejecuciones futuras.
     - Ejecuta `mvn compile` para comprobar la compilación de todas las clases del proyecto.

2. **Ejecutar Pruebas (`.github/actions/test/action.yml`)**:
   - **Propósito**: Configura el entorno y corre la suite completa de pruebas unitarias.
   - **Pasos**:
     - Configura JDK 21 con la distribución Temurin.
     - Habilita la caché de dependencias de Maven.
     - Ejecuta `mvn test` para lanzar los 11 tests unitarios del proyecto.

### Workflows Definidos

Los workflows están en `.github/workflows/` y definen las orquestaciones de las tareas en base a eventos de Git:

1. **Integración Continua (`.github/workflows/main.yml`)**:
   - **Disparador**: Se ejecuta automáticamente al abrir, actualizar o reabrir un **Pull Request** con destino a la rama `main`.
   - **Flujo**: 
     - **Job `build`**: Descarga el código y ejecuta la acción `./.github/actions/build`.
     - **Job `test`**: Depende explícitamente de la finalización exitosa de `build` (`needs: build`) y ejecuta la acción `./.github/actions/test`.

2. **Entrega Continua (`.github/workflows/release.yml`)**:
   - **Disparador**: Se inicia de forma automática al realizar un `push` de una etiqueta (tag) que coincida con el patrón de versionado semántico `v*.*.*` (por ejemplo, `v1.0.0`).
   - **Flujo**:
     - Descarga el código correspondiente a la etiqueta creada.
     - Configura JDK 21 y la caché de Maven.
     - Empaqueta el proyecto ejecutable corriendo `mvn clean package`.
     - Crea un **GitHub Release** en el repositorio utilizando la acción `softprops/action-gh-release@v2`.
     - Adjunta el archivo JAR generado (`target/tppatrones-1.0-SNAPSHOT.jar`) como un asset descargable del Release y genera notas de lanzamiento automáticas basándose en los commits.

---

### Conceptos: Integración Continua (CI) vs. Entrega Continua (CD)

En el contexto de este proyecto, la diferencia entre ambos conceptos radica en sus objetivos y momentos de ejecución dentro del ciclo de vida del desarrollo:

* **Integración Continua (CI)**:
  * **Objetivo**: Detectar fallos de integración lo antes posible.
  * **Acción**: Automatiza la descarga del código propuesto en un Pull Request, compila el código y ejecuta todas las pruebas unitarias.
  * **Resultado**: Otorga confianza sobre el cambio propuesto para que los mantenedores del repositorio decidan si es seguro fusionar (merge) el Pull Request a la rama principal (`main`). No genera software distribuible.

* **Entrega Continua (CD)**:
  * **Objetivo**: Poner a disposición una nueva versión estable del software listo para producción.
  * **Acción**: Se dispara cuando el desarrollo ha concluido en la rama principal y se marca un punto específico de estabilidad mediante un tag (`v1.0.0`). Compila, empaqueta el software en un archivo JAR y lo publica de manera formal.
  * **Resultado**: Crea un Release público en GitHub con notas de cambios y el instalable/binario adjunto para los usuarios finales o sistemas de despliegue.



# TP Patrones - Unit Testing

Este proyecto contiene la implementación de los tests unitarios requeridos en la Parte 2 del trabajo práctico "consigna_unit_testing.pdf".

## Tests Implementados
Los tests están ubicados en el directorio `test/observer/` e incluyen:
1. `ThresholdAlertServiceTest.java`: Verifica la lógica de umbrales en `ThresholdAlertService` aisladamente y sin dependencias.
2. `AlertObserverFakeTest.java`: Verifica `AlertObserver` usando un Fake (manual) de `AlertService` y otro Fake de `Logger`.
3. `AlertObserverMockTest.java`: Verifica el mismo `AlertObserver` pero utilizando la librería **Mockito**.

## Cómo ejecutar la Aplicación Principal

Para ejecutar el programa interactivo desde la terminal utilizando **Maven**, deben seguirse estos pasos:

1. **Abrir la terminal.**
2. **Posiciónarse en la raíz del proyecto** (la carpeta `TPpatrones`).
3. **Compilar y ejecutar el programa:**

```bash
# Ejecutar la clase Main usando Maven
mvn compile exec:java -Dexec.mainClass="Main"
```

*(Nota: Como no hay configuración de package en Main, `exec.mainClass` simplemente llama a `Main`)*

## Cómo ejecutar los tests

Para ejecutar todas las pruebas desde la terminal, se debe:

1. **Abrir la terminal.**
2. **Situarse en la raíz del proyecto** (`TPpatrones`).
3. **Ejecutar el siguiente comando:**

```bash
mvn clean test
```
*(Nota: Los test pueden ejecutarse simplemente con `mvn test`, pero se utiliza `mvn clean test` para una ejecución más limpia )*

Se verá una salida en consola compilando todo automáticamente y mostrando que los tests pasaron, con el mensaje `BUILD SUCCESS` (mostrando las 11 pruebas correctas).

# Loqutor Client

Idioma: [English](README.md) | Espanol

Loqutor Client es una aplicacion de escritorio Java (Swing) para sintesis de voz (TTS). Permite cargar o escribir un guion, reproducir audio generado desde una API TTS externa y exportar el resultado a archivos MP3.

## Informacion General

- **Version:** 1.0.0
- **Autor:** Adrian Sanchez Galera
- **Tecnologia base:** Java 17 + Swing + Maven

## Caracteristicas Principales

- Edicion y gestion de guiones `.loq`
- Reproduccion de texto completo o seleccion
- Exportacion de audio a MP3
- Seleccion de idioma, voz y efecto
- Barra de progreso durante la conversion
- Interfaz grafica con FlatLaf

## Configuracion

El programa necesita los siguientes `.json` junto al ejecutable:

- `ApiSettings.json`: parametros de conexion a la API TTS
- `Languages.json`: mapa de idiomas disponibles
- `Voices.json`: mapa de voces disponibles
- `Effects.json`: mapa de efectos disponibles

Si `ApiSettings.json` no es valido o no existe, la aplicacion mostrara un error al intentar convertir texto.

`ApiSettings.json` se puede generar desde las preferencias de la API en el programa.

## Requisitos

- Java 17
- Maven 3.8 o superior
- Conexion a la API TTS configurada

## Dependencias

- `tools.jackson.core:jackson-databind`
- `com.squareup.okhttp3:okhttp`
- `javazoom:jlayer`
- `com.formdev:flatlaf`
- `com.formdev:flatlaf-extras`

## Compilacion

Compilar jar ejecutable con dependencias incluidas:

```bash
mvn clean package
```

Artefacto generado:

- `target/loqutor-client-1.0.0.jar`

## Ejecucion

```bash
java -jar target/loqutor-client-1.0.0.jar
```

## Licencia

Licencia declarada en `LICENSE`:

**Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International (CC BY-NC-SA 4.0)**

Avisos de dependencias de terceros:

- `THIRD_PARTY_NOTICES.md`

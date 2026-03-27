# Loqutor Client

Language: English | [Español](README.es.md)

Loqutor Client is a Java desktop application (Swing) for text-to-speech (TTS). It allows you to load or write a script, play audio generated from an external TTS API, and export the result to MP3 files.

## General Information

- **Version:** 1.0.0
- **Author:** Adrian Sanchez Galera
- **Core stack:** Java 17 + Swing + Maven

## Main Features

- Editing and management of `.loq` scripts
- Full script or selection playback
- MP3 audio export
- Language, voice, and effect selection
- Progress bar during conversion
- FlatLaf-based user interface

## Configuration

The program requires the following `.json` files next to the executable:

- `ApiSettings.json`: TTS API connection parameters
- `Languages.json`: available language map
- `Voices.json`: available voice map
- `Effects.json`: available effects map

If `ApiSettings.json` is invalid or missing, the application will show an error when trying to convert text.

`ApiSettings.json` can be generated from the API settings menu in the application.

- [Examples](EXAMPLES.md)

## Requirements

- Java 17
- Maven 3.8 or later
- Configured connection to a TTS API

## Dependencies

- `tools.jackson.core:jackson-databind`
- `com.squareup.okhttp3:okhttp`
- `javazoom:jlayer`
- `com.formdev:flatlaf`
- `com.formdev:flatlaf-extras`

## Build

Build an executable JAR with dependencies included:

```bash
mvn clean package
```

Generated artifact:

- `target/loqutor-client-1.0.0.jar`

## Run

You can run the application in three ways:

1. Double-click the `.jar` file.
2. Double-click `.sh` (Linux) or `.bat` (Windows).
3. From terminal:

```bash
java -jar <jar-name>
```

## License

License declared in `LICENSE`:

**Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International (CC BY-NC-SA 4.0)**

Third-party dependency notices:

- `THIRD_PARTY_NOTICES.md`


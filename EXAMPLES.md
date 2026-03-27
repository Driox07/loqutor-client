# Examples

Language: English | [Español](EXAMPLES.es.md)

This document explains the format of the JSON files used by Loqutor Client and how they affect the TTS request URL.

## 1) ApiSettings.json

Defines the base API configuration: URL, parameter names, and text limit per request.

Example:

```json
{
  "url": "https://tts-example-api.com",
  "engineParameter": "engine",
  "languageParameter": "language",
  "voiceParameter": "voice",
  "textParameter": "text",
  "formatParameter": "format",
  "defaultFormat": "mp3",
  "effectParameter": "effect",
  "levelParameter": "level",
  "textLimit": 100
}
```

Fields:

- `url`: base endpoint of the TTS API.
- `engineParameter`: name of the engine query parameter.
- `languageParameter`: name of the language query parameter.
- `voiceParameter`: name of the voice query parameter.
- `textParameter`: name of the text query parameter.
- `formatParameter`: name of the output format query parameter.
- `defaultFormat`: default output format value sent in `formatParameter` (for example, `mp3`).
- `effectParameter`: name of the effect type query parameter.
- `levelParameter`: name of the effect level query parameter.
- `textLimit`: maximum number of characters per request. If the text exceeds this value, the app splits it into fragments.

*The names shown here are examples, this is not a real API, and the program will not work with them.*

## 2) Languages.json

Map from language options shown in the UI to the actual value sent to the API.

Example:

```json
{
  "Español": "spanish",
  "English": "english"
}
```

Interpretation:

- JSON key: label shown to the user in the combo box.
- JSON value: value sent to the API.
- If the value is `null`, that parameter is removed from the URL (`languageParameter`).

## 3) Voices.json

Map from voice options shown in the UI to the actual value sent to the API.

Voice values now use this format:

- `engine;voiceId`

Example:

```json
{
  "Paco": "engine_a;6",
  "Lucía": "engine_b;lucia"
}
```

Interpretation:

- JSON key: option label shown in the UI.
- JSON value: a compound value in the format `engine;voiceId`.
- `engine`: engine identifier to send in `engineParameter`.
- `voiceId`: voice identifier to send in `voiceParameter`.
- If the value is `null`, that parameter is removed from the URL (`voiceParameter`).

## 4) Effects.json

Map from effect options shown in the UI to the actual value sent to the API.

Example:

```json
{
  "Ninguno": null,
  "Robot": "2",
  "Eco": "8"
}
```

Interpretation:

- JSON key: option label shown in the UI.
- JSON value: actual value for the API.
- If the value is `null`, that parameter is removed from the URL (`effectParameter`).

## Important Rules for Maps (language, voice, effect)

- In all three maps, keys (`"Visible name"`) are labels for the UI.
- The value of each key is what is actually sent to the API.
- Any entry with a `null` value causes that parameter to be omitted from the final URL.

## Recommendations

- Keep map keys unique.
- Avoid empty string values (`""`): use `null` when you want to remove a parameter.
- If you change parameter names in `ApiSettings.json`, they must match what your TTS API expects.

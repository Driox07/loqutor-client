# Examples

Idioma: [English](EXAMPLES.md) | Español

Este documento explica el formato de los archivos JSON usados por Loqutor Client y como afectan a la URL de la llamada TTS.

## 1) ApiSettings.json

Define la configuracion base de la API: URL, nombres de parametros y limite de texto por peticion.

Ejemplo:

```json
{
  "url": "https://tts-example-api.com",
  "engineParameter": "engine",
  "languageParameter": "language",
  "voiceParameter": "voice",
  "textParameter": "text",
  "formatParameter": "format",
  "effectParameter": "effect",
  "levelParameter": "level",
  "textLimit": 100
}
```

Campos:

- `url`: endpoint base de la API TTS.
- `engineParameter`: nombre del parametro de engine en la query.
- `languageParameter`: nombre del parametro de idioma.
- `voiceParameter`: nombre del parametro de voz.
- `textParameter`: nombre del parametro de texto.
- `formatParameter`: nombre del parametro de formato.
- `effectParameter`: nombre del parametro de tipo de efecto.
- `levelParameter`: nombre del parametro de nivel de efecto.
- `textLimit`: numero maximo de caracteres por peticion. Si el texto supera este valor, la app lo divide en fragmentos.

*Los nombres mostrados aquí son ejemplos, no es una API real y el programa no funcionará con ellos.*

## 2) Languages.json

Mapa de opciones de idioma visibles en UI a valor real enviado a la API.

Ejemplo:

```json
{
  "Español": "spanish",
  "English": "english"
}
```

Interpretacion:

- Clave JSON: texto que ve el usuario en el combo.
- Valor JSON: valor que se envia a la API.
- Si el valor es `null`, se elimina ese parametro de la URL (`languageParameter`).

## 3) Voices.json

Mapa de opciones de voz visibles en UI a valor real enviado a la API.

Ejemplo:

```json
{
  "Paco": "paco",
  "Lucía": "lucia"
}
```

Interpretacion:

- Clave JSON: texto de la opcion en UI.
- Valor JSON: valor real para la API.
- Si el valor es `null`, se elimina ese parametro de la URL (`voiceParameter`).

## 4) Effects.json

Mapa de efectos visibles en UI a valor real enviado a la API.

Ejemplo:

```json
{
  "Ninguno": null,
  "Robot": "2",
  "Eco": "8"
}
```

Interpretacion:

- Clave JSON: texto de la opcion en UI.
- Valor JSON: valor real para la API.
- Si el valor es `null`, se elimina ese parametro de la URL (`effectParameter`).

## Reglas importantes para mapas (idioma, voz, efecto)

- En los tres mapas, las claves (`"Nombre visible"`) son etiquetas para UI.
- El valor de cada clave es lo que se envia realmente a la API.
- Cualquier entrada con valor `null` provoca que ese parametro no se incluya en la URL final.

## Recomendaciones

- Mantener las claves de cada mapa unicas.
- Evitar valores vacios (`""`): usa `null` si quieres quitar el parametro.
- Si cambias los nombres de parametros en `ApiSettings.json`, deben coincidir con los esperados por tu API TTS.

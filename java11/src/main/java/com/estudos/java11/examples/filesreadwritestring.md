# `FilesReadWriteString`

Exemplo: `FilesReadWriteString.java`.

## Objetivo

Atalhos **`Files.readString` / `writeString`** para arquivos pequenos em **UTF-8** (default), sem boilerplate de streams.

## Nuances

- Adequado a **tamanho moderado**; arquivos grandes → `newBufferedReader` / `lines()` com streaming.
- Encoding default UTF-8; overloads permitem `Charset` explícito.
- O exemplo usa arquivo temporário e remove ao fim.

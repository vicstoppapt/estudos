[PT](filesreadwritestring.md)

# `FilesReadWriteString`

Example: `FilesReadWriteString.java`.

## Goal

Shortcuts **`Files.readString` / `writeString`** for small files in **UTF-8** (default), without stream boilerplate.

## Nuances

- Suited to **moderate** size; large files → `newBufferedReader` / `lines()` with streaming.
- Default encoding UTF-8; overloads allow an explicit `Charset`.
- The example uses a temp file and deletes it at the end.

[PT](stringutilities.md)

# `StringUtilities`

Example: `StringUtilities.java`.

## Goal

**`String`** additions in Java 11 used day to day.

## Methods

- **`isBlank`:** true for empty or Unicode *whitespace* only (not just ASCII space).
- **`strip`:** removes leading/trailing whitespace per Unicode (`trim` is stricter for classic BMP).
- **`lines`:** `Stream<String>` of logical lines (no phantom trailing `\n` like raw `split`).
- **`repeat`:** efficient repetition without a manual loop.

## Nuances

`strip` vs `trim`: prefer `strip` in new code for Unicode consistency.

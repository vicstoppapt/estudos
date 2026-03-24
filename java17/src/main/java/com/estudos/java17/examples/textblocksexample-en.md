**PT:** [textblocksexample.md](textblocksexample.md)

# `TextBlocksExample`

Example: `TextBlocksExample.java`.

## Goal

**Multiline** literals with `"""` and stripping of incidental indentation (align content to the left of the block).

## Nuances

- Rare `\"` escape inside the block; real newlines in source.
- Useful for JSON, SQL, HTML embedded in tests and messages.

[PT](challengestreamwordcount.md)

# `ChallengeStreamWordCount`

Solution: `ChallengeStreamWordCount.java`. Tests in `src/test`.

## Goal

Count occurrences of **words** (tokens separated by whitespace), **case-insensitive**, with **`Collectors.groupingBy`** + **`counting`**.

## Nuances

- `split("\\s+")` and `trim` handle empty / whitespace-only string → empty map.
- `filter` removes empty tokens defensively.
- Alternative: `Collectors.toMap` with merge for counting.

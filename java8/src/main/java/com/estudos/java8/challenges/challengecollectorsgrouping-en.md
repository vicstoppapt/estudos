[PT](challengecollectorsgrouping.md)

# `ChallengeCollectorsGrouping`

Solution: `ChallengeCollectorsGrouping.java`.

## Goal

Group strings by a **derived key** — here **`String::length`** — producing `Map<Integer, List<String>>`.

## Nuances

- **`groupingBy`** with the default downstream is `toList()`; other downstreams: `counting`, `mapping`, `maxBy`.
- Order of lists in the map is not sorted unless you use `TreeMap` or post-processing.

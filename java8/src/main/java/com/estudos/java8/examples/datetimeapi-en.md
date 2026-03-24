[PT](datetimeapi.md)

# `DateTimeApi`

Example: `DateTimeApi.java`.

## Goal

**`java.time` API**: **immutable** types and separation between local date, local date-time, global instant, and duration.

## In the code

- **`LocalDate` / `LocalDateTime`:** JVM local clock, **no** explicit time zone.
- **`ZonedDateTime` with `ZoneId`:** civil time in a region; DST rules come from the JVM’s time zone database.
- **`Instant` + `Duration`:** UTC timeline; `between` measures the interval between instants.

## Nuances

- Avoid legacy `Date`/`Calendar` for new code.
- **Persistence:** prefer storing `Instant` or explicit ISO-8601; convert at the boundary (UI/API).
- **Parsing/formatting:** `DateTimeFormatter` is thread-safe.

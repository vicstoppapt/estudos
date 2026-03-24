**PT:** [challengerecordvalidation.md](challengerecordvalidation.md)

# `ChallengeRecordValidation`

Solution: `ChallengeRecordValidation.java`. Tests validate invariants.

## Goal

**Compact constructor** on a record: validates `Email` (non-empty, contains `@`) before exposing the instance.

## Nuances

- The compact constructor does not list parameters; field assignment happens after the block (record semantics).
- Throwing `IllegalArgumentException` is standard for invalid arguments.

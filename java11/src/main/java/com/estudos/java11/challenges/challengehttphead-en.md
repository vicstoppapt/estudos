[PT](challengehttphead.md)

# `ChallengeHttpHead`

Solution: `ChallengeHttpHead.java`. Tests use an `HttpClient` mock.

## Goal

**HEAD** request (no useful body) and return **status**; injectable **`statusHead(HttpClient, URI)`** method for testing.

## Nuances

- `BodyHandlers.discarding()` avoids materializing the body.
- Real `HttpClient` in `main` depends on the network; tests isolate with a mock.

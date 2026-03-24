**PT:** [challengeanagram.md](challengeanagram.md)

# `ChallengeAnagram`

Solution in: `ChallengeAnagram.java`. Tests: `ChallengeAnagramTest`.

## Goal

Decide whether two strings are **anagrams** considering **letters only** (ignore spaces and punctuation), **case-insensitive**.

## Algorithm

1. Extract code points, filter with `Character.isLetter`, normalize with `toLowerCase`.
2. Put into `char[]`, `Arrays.sort` on each side.
3. `Arrays.equals` — same multiset of letters → anagram.

## Nuances

- **Unicode:** using `codePoints()` avoids breaking surrogate pairs when iterating “logical character”.
- **Complexity:** O(k log k) with k = number of letters; alternative is frequency counts in a map O(k).
- **Null:** both `null` → `false` in the current implementation (explicit guard).

## Study

Compare counting in `int[26]` for ASCII-only vs a Unicode-safe approach.

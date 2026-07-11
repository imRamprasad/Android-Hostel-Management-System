# Definition of Done

A story is NOT done until every applicable box is true. This is what stops
"add feature X" from quietly breaking features A through W.

---

## Every story, before it's marked done

- [ ] Matches an acceptance criterion in `product-backlog.md` — no unrequested scope added
- [ ] Built on its own branch (`feature/AUTH-2-google-signin`), not directly on `main`
- [ ] Existing tests still pass (`mvn test` / Android instrumented tests) — a green build before you started must stay green
- [ ] New logic has a test where it touches money, auth, or notifications
- [ ] `api-contracts.md` / `database-schema.md` updated FIRST if this story needed new fields — code matches docs, not the reverse
- [ ] `/review` run against the change
- [ ] Checked off in `sprint-log.md`

---

## The Non-Destructive Change Rule

This is the rule that stops "add a feature" from turning into "accidentally
rewrite three other modules."

1. **Additive database changes only.** New features get a new Flyway
   migration file (`V6__add_x.sql`, never edit `V1`–`V5`). Never drop or
   rename an existing column without a dedicated, reviewed migration story of
   its own.
2. **Never touch a module marked done in `sprint-log.md`** unless the current
   story explicitly says so. If a new feature seems to require changing old
   code, that's a signal to stop and re-run `/architect` — not to proceed.
3. **Contract-first.** A new API field or endpoint is added to
   `api-contracts.md` before a single line of controller code is written.
   The Android side is only built against what's documented there — never
   against what the agent assumes the backend will return.
4. **One story, one branch, one PR.** Never bundle "add complaints" with "also
   refactored the fee module while I was in there." If refactoring is
   genuinely needed, that's its own backlog story.
5. **Every session starts with `/remember restore`** so the agent has the
   real current state, not a guess — this alone prevents most accidental
   regressions.

## If the AI agent breaks something anyway

Use `/recover` — it will diagnose whether it's a targeted fix, a polluted
session needing a reset, or a sign the last plan was wrong from the start.
Don't just keep prompting "fix it" — that's how small breaks become big ones.

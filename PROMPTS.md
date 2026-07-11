# Prompt Library

Copy-paste these into Claude Code (or this chat) at each stage. Written to
work with the `context/agile/` files and the JS Mastery skills
(`/architect`, `/remember`, `/review`, `/recover`, `/imprint`).

---

## 1. Start of every session

```
/remember restore
```
(If not using that skill: "Read context/agile/sprint-log.md and
context/agile/sprint-plan.md, then tell me exactly what's done, what's in
progress, and what's next before we do anything else.")

---

## 2. Sprint planning (start of a sprint)

```
We're starting Sprint [N] from context/agile/sprint-plan.md, covering
stories [list IDs, e.g. FEE-3, FEE-4]. Read their acceptance criteria in
context/agile/product-backlog.md, and context/api-contracts.md and
database-schema.md for current state. Propose an implementation plan
covering both backend and Android where relevant. Do not write code yet —
just the plan. Flag anything ambiguous in the story before proceeding.
```

---

## 3. Building a single story

```
/architect

Story: [STORY-ID] — [paste the story + acceptance criteria from
product-backlog.md]

Follow context/code-standards.md and the Non-Destructive Change Rule in
context/agile/definition-of-done.md — additive migrations only, don't touch
modules marked done in sprint-log.md, update api-contracts.md /
database-schema.md before writing code if this story needs new fields.
```

Then after the plan is approved:
```
Build it.
```

Then:
```
/review
```

Then:
```
Update context/agile/sprint-log.md — check off [STORY-ID] and note anything
worth remembering for next session.
```

---

## 4. Building an Android UI screen

```
Building the [screen name] screen for [STORY-ID].

Check context/agile/design-sourcing.md and context/mobile-ui-registry.md
first — match existing tokens/components, don't invent new spacing or
colors. Use Jetpack Compose, state hoisting (no direct API calls from
composables), ViewModel exposes StateFlow.

[If you want reference direction first, add:]
Before building, show me 3-4 reference images for [style, e.g. "clean
fintech-style mobile dashboards"] so I can pick a direction.
```

After building:
```
/imprint
```

---

## 5. Fixing a bug

```
/recover

Bug: [describe what's broken and how you noticed]
Expected: [what should happen]
Actual: [what happens instead]

Diagnose whether this is a targeted fix, a polluted session, or a wrong
foundation before proposing a fix. Don't touch any file outside what's
needed to fix this.
```

---

## 6. Adding a feature NOT yet in the backlog

```
New feature request: [describe it]

Before building anything: add this to context/agile/product-backlog.md as a
properly formatted story (role/capability/benefit + acceptance criteria +
point estimate), under the right epic (or a new epic if none fits). Show me
the story. Don't start building until I confirm it.
```

---

## 7. End of session

```
/remember save
```
(If not using that skill: "Summarize what changed this session, update
context/agile/sprint-log.md, and note any open questions or half-finished
work so the next session can pick up exactly where this one left off.")

---

## 8. End of sprint (retro)

```
Sprint [N] is done. Read through what we built and write a 3-line retro in
context/agile/sprint-log.md: what went well, what didn't, what to change
next sprint. Then check readiness for Sprint [N+1] — anything in
sprint-plan.md that needs adjusting based on what we learned?
```

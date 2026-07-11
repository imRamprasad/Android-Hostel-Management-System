# AGENTS.md

Entry point for any AI coding agent (Claude Code, Cursor, etc.) working on
this repo. Read this file first, every session.

---

## What this project is

PG/Hostel Room Allocation & Complaint Management System — Spring Boot +
MySQL backend, Android (Kotlin/Compose) resident app. Built in 2-week Agile
sprints. See `context/project-overview.md` for the full picture.

**Stack Details:**
- **Backend**: Spring Boot 3, Spring Security, JWT (jjwt), Firebase Admin SDK (OTP/Social), Hibernate/JPA.
- **Android**: Kotlin, Jetpack Compose, Navigation Compose, Firebase Auth, Google Sign-In SDK, Retrofit.
- **Database**: MariaDB (MySQL compatible).

## Read order (every new session)

1. `context/agile/sprint-log.md` — what's done, in progress, next, blockers
2. `context/agile/sprint-plan.md` — current and upcoming sprint scope
3. `docs/specs/` — technical build specs for approved features (read before building)
4. `context/agile/product-backlog.md` — full epics/stories with acceptance criteria
5. `context/database-schema.md` — tables, relationships (source of truth)
6. `context/api-contracts.md` — endpoint contracts (source of truth — never invent endpoints)
7. `context/mobile-ui-registry.md` — Android design tokens and components
8. `context/code-standards.md` + `context/agile/definition-of-done.md` — conventions and the Non-Destructive Change Rule
9. `context/library-docs.md` — JWT, Firebase Admin, FCM, Google Sign-In patterns

## Skills installed

Installed via `npx skills@latest add JavaScript-Mastery-Pro/skills`:

| Skill | When to use |
|---|---|
| `/architect` | Before starting any story — produces a plan you approve before code is written |
| `/remember save` / `restore` | End/start of every session |
| `/review` | After finishing any story |
| `/recover` | When something breaks |
| `/imprint` | After building any Compose UI component |

See `PROMPTS.md` at repo root for ready-to-use prompts per stage (sprint
planning, building a story, UI screens, bug fixes, adding new backlog items,
end of session/sprint).

## The Non-Destructive Change Rule (short version)

Additive DB migrations only. Never edit a module marked done in
`sprint-log.md` without an explicit story for it. Contract-first — update
`api-contracts.md`/`database-schema.md` before writing code. One story, one
branch. Full detail in `context/agile/definition-of-done.md`.

## Never

- Invent a REST endpoint, DTO shape, or DB column not in the contracts —flag it instead of guessing
- Mark a story done without meeting `context/agile/definition-of-done.md`
- Copy copyrighted UI designs/assets from the internet — see `context/agile/design-sourcing.md` for what's actually usable

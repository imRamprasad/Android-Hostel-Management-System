# Sprint Plan

2-week sprints. Backend and Android run in parallel once the API contract
for that epic exists — Android never starts a screen against an endpoint
that isn't in `context/api-contracts.md` yet.

Each sprint ends with: working demo of that sprint's stories + `/review` run
+ retro notes added to `sprint-log.md`.

---

## Sprint 0 — Foundation (no user-facing stories, infra only)

- Repo structure, this Agile context setup
- Spring Boot skeleton, MySQL connection, Flyway baseline
- Android project skeleton, navigation shell, Hilt/DI setup
- CI: basic build check on push (GitHub Actions)

**Exit criteria:** empty backend boots, empty Android app builds and runs.

---

## Sprint 1 — Auth

**Backend:** AUTH-1, AUTH-2, AUTH-3, AUTH-4
**Android:** AND-2 (nav shell, since login gates everything) + wire Google Sign-In screen to AUTH-2

**Exit criteria:** a resident can register/login/sign-in-with-Google from the
Android app and reach a blank home screen; an admin JWT works against a
protected test endpoint.

---

## Sprint 2 — Room Management + Resident Profile

**Backend:** ROOM-1, ROOM-2, ROOM-3, ROOM-4, RES-1, RES-2, RES-3
**Android:** AND-1 (home screen shows real room data), profile screen (RES-1/RES-2)

**Exit criteria:** admin can allocate/vacate rooms via API; resident sees
their real room on the home screen.

---

## Sprint 3 — Fees Part 1 (schedule + viewing)

**Backend:** FEE-1, FEE-2
**Android:** Fees screen — view dues (no payment yet)

**Exit criteria:** resident sees accurate fee dues on the app.

---

## Sprint 4 — Fees Part 2 (payment + receipts)

**Backend:** FEE-3, FEE-4
**Android:** Pay flow + receipt view/download

**Exit criteria:** resident can pay a due fee end-to-end and get a PDF receipt.

---

## Sprint 5 — Complaints

**Backend:** COMP-1, COMP-2, COMP-3
**Android:** Raise complaint screen, complaint status list

**Exit criteria:** resident can raise a complaint and track it; admin can
update its status via API (Postman/Swagger is fine if Epic 8 isn't built yet).

---

## Sprint 6 — Notifications

**Backend:** NOTIF-1, NOTIF-2, NOTIF-3 (FCM wiring)
**Android:** FCM token registration, notification handling

**Exit criteria:** resident receives real push notifications for the three
trigger events.

---

## Sprint 7 — Polish & UI Pass

**Android:** AND-3 (design system pass — apply consistent tokens across all
screens built so far, run `/imprint audit`)
**Backend:** security review (rate limiting on `/auth/login`, JWT expiry
tuning, input validation audit)

---

## Sprint 8 — Hardening & Deployment

- Load/edge-case tests on fee + allocation logic
- Deploy backend (Railway/Render/EC2 — pick one, document in `deployment.md`)
- Play Store internal testing track for Android (optional)

---

## How to run each sprint with the AI agent

1. Start of sprint: paste the "Sprint Planning" prompt from `PROMPTS.md`, listing that sprint's story IDs.
2. Per story: `/architect` → build → `/review` → check off in `sprint-log.md`.
3. End of sprint: `/remember save`, write a 3-line retro in `sprint-log.md` (what went well / what didn't / what changes next sprint).

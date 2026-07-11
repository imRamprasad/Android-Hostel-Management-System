# Product Backlog

Living document. Every feature request — yours or the AI agent's suggestion —
becomes a story here BEFORE any code is written. Nothing gets built off-backlog.

Format: `As a [role], I want [capability], so that [benefit].`
Story points: Fibonacci (1, 2, 3, 5, 8) — rough sizing, not a promise.

---

## Epic 1 — Authentication & Access

| ID | Story | Points | Priority |
|---|---|---|---|
| AUTH-1 | As a resident, I want to register with email/password, so that I can create an account | 3 | High |
| AUTH-2 | As a resident, I want to sign in with Google, so that I don't need a separate password | 5 | High |
| AUTH-3 | As a user, I want my session to stay valid via refresh tokens, so that I'm not logged out constantly | 3 | High |
| AUTH-4 | As an admin, I want a separate elevated role, so that only I can manage rooms/residents | 2 | High |

## Epic 2 — Room Management

| ID | Story | Points | Priority |
|---|---|---|---|
| ROOM-1 | As an admin, I want to create/edit rooms with capacity and rent, so that I can manage inventory | 3 | High |
| ROOM-2 | As an admin, I want to allocate a resident to a room, so that occupancy is tracked | 5 | High |
| ROOM-3 | As an admin, I want to vacate a resident from a room, so that the room becomes available again | 3 | High |
| ROOM-4 | As an admin, I want to see room occupancy at a glance, so that I know what's available | 2 | Medium |

## Epic 3 — Resident Profile

| ID | Story | Points | Priority |
|---|---|---|---|
| RES-1 | As a resident, I want to view my profile and current room, so that I know my status | 2 | High |
| RES-2 | As a resident, I want to update my contact details, so that my info stays current | 2 | Medium |
| RES-3 | As an admin, I want to view any resident's full history, so that I can answer queries | 3 | Medium |

## Epic 4 — Fee Management

| ID | Story | Points | Priority |
|---|---|---|---|
| FEE-1 | As an admin, I want to set a fee schedule per resident, so that dues are tracked | 5 | High |
| FEE-2 | As a resident, I want to see what I owe and by when, so that I can pay on time | 2 | High |
| FEE-3 | As a resident, I want to pay a due fee and get a PDF receipt, so that I have proof of payment | 8 | High |
| FEE-4 | As an admin, I want to see overdue fees across all residents, so that I can follow up | 3 | Medium |

## Epic 5 — Complaint Ticketing

| ID | Story | Points | Priority |
|---|---|---|---|
| COMP-1 | As a resident, I want to raise a complaint with category/description, so that issues get addressed | 3 | High |
| COMP-2 | As a resident, I want to track my complaint's status, so that I know it's being handled | 2 | High |
| COMP-3 | As an admin, I want to see all open complaints and update their status, so that I can manage repairs | 3 | High |

## Epic 6 — Notifications

| ID | Story | Points | Priority |
|---|---|---|---|
| NOTIF-1 | As a resident, I want a push notification when my fee is due soon, so that I don't miss it | 3 | Medium |
| NOTIF-2 | As a resident, I want a push notification when my complaint status changes, so that I stay informed | 2 | Medium |
| NOTIF-3 | As a resident, I want confirmation push after a successful payment, so that I know it went through | 2 | Medium |

## Epic 7 — Android App Shell & Navigation

| ID | Story | Points | Priority |
|---|---|---|---|
| AND-1 | As a resident, I want a clean home screen showing room + fee status, so that I get key info at a glance | 5 | High |
| AND-2 | As a resident, I want bottom navigation between Home/Fees/Complaints/Profile, so that I can move around easily | 3 | High |
| AND-3 | As a resident, I want the app to look polished (consistent spacing/colors/type), so that it feels trustworthy | 5 | Medium |

## Epic 8 — Admin Web/Backend Ops (optional, if you build an admin panel)

| ID | Story | Points | Priority |
|---|---|---|---|
| ADM-1 | As an admin, I want a simple dashboard (web or Postman/Swagger for now), so that I can manage the system without direct DB access | 5 | Low |

---

## Backlog Rules

- New feature idea → add it as a story here first, with acceptance criteria, before telling the AI agent to build it.
- Nothing moves to a sprint without a priority and a rough point estimate.
- If a story turns out bigger than expected mid-sprint, split it — don't silently expand scope.

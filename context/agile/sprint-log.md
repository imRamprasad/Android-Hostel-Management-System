# Sprint Log

Update at the end of every sprint. Any AI agent starting a new session reads
this first (along with `sprint-plan.md`) to know exactly where the project stands.

---

## Current Sprint

**Sprint:**
**Stories in progress:**
**Stories done:**
**Blockers:**

---

## History

### Sprint 0
- [x] Repo + Agile context setup
- [x] Backend skeleton boots
- [x] Android skeleton builds
- **Retro:** Found and fixed MySQL port conflict (3305 vs 3306) and dialect mismatch. Backend successfully connected to XAMPP MariaDB. Android build verified. Ready for Sprint 1.

### Sprint 1 — Auth
- [x] AUTH-1 (Email/Password registration)
- [x] AUTH-2 (Google Sign-In backend support)
- [x] AUTH-3 (JWT & Refresh tokens)
- [x] AUTH-4 (Admin role in DB)
- [x] AND-2 (Navigation shell + Auth screens)
- **Retro:** Implemented hybrid identity (Email, Phone/OTP, Google). Integrated Firebase Admin SDK for backend verification and jjwt for session management. Android app now gates Dashboard with a Login screen.

### Sprint 2 — Room Management + Resident Profile
- [ ] ROOM-1 / ROOM-2 / ROOM-3 / ROOM-4
- [ ] RES-1 / RES-2 / RES-3
- [ ] AND-1 (home screen)
- **Retro:**

### Sprint 3 — Fees Part 1
- [ ] FEE-1 / FEE-2
- [ ] Android fees screen (view only)
- **Retro:**

### Sprint 4 — Fees Part 2
- [ ] FEE-3 / FEE-4
- [ ] Android pay flow + receipts
- **Retro:**

### Sprint 5 — Complaints
- [ ] COMP-1 / COMP-2 / COMP-3
- [ ] Android complaint screens
- **Retro:**

### Sprint 6 — Notifications
- [ ] NOTIF-1 / NOTIF-2 / NOTIF-3
- [ ] Android FCM wiring
- **Retro:**

### Sprint 7 — Polish
- [ ] AND-3 design pass
- [ ] Backend security review
- **Retro:**

### Sprint 8 — Hardening & Deployment
- [ ] Load/edge-case tests
- [ ] Backend deployed
- [ ] Android internal test track
- **Retro:**

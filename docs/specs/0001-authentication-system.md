# Spec 0001: Authentication System

**Status**: Accepted
**Owner**: AI Agent
**Date**: 2026-07-12

## Summary
A hybrid authentication system for the Hostel Management System. It supports traditional Email/Password login, Mobile Number with OTP (via Firebase), and seamless Google Sign In.

## Context
The project needs to support both residents and admins. Residents prefer mobile/social login for ease of use, while admins may use traditional email/password.

## Requirements

### Functional (Acceptance Criteria)
- **AC-1 (Registration)**: Users can register with Email/Password or Mobile Number.
- **AC-2 (Identity)**: At least one identifier (Email or Mobile) is required. If one is provided, the other remains optional.
- **AC-3 (OTP Login)**: Users can sign in using a Mobile Number + OTP verified via Firebase.
- **AC-4 (Google Sign In)**: Users can sign in with Google. First time users are automatically registered.
- **AC-5 (Password Security)**: Passwords must be hashed with BCrypt.
- **AC-6 (Roles)**: Users are assigned a role (RESIDENT or ADMIN). Default is RESIDENT.

### Non-Functional
- **Security**: JWT based session management.
- **Validation**: Unique constraints on Email and Mobile Number.

## Decision

### 1. Data Model
Update the `users` table to support the new identity requirements.

| Column | Type | Constraints | Description |
|---|---|---|---|
| `id` | BIGINT | PK | |
| `email` | VARCHAR(255) | UNIQUE, NULLABLE | Mandatory if phone is null |
| `phone_number` | VARCHAR(20) | UNIQUE, NULLABLE | Mandatory if email is null |
| `password` | VARCHAR(255) | NULLABLE | Required for email login |
| `google_id` | VARCHAR(255) | UNIQUE, NULLABLE | For Google Auth linking |
| `role` | ENUM | NOT NULL | RESIDENT, ADMIN |

### 2. Stack Picks
- **Backend**: Spring Security, JWT (jjwt library), Firebase Admin SDK.
- **Android**: Google Sign In SDK, Firebase Auth SDK, Retrofit for API calls.

## Build Plan (Tracer Bullet)

### Phase 1: Core Backend & Database
1. **Migration**: Update `users` table with `role`, `google_id`, and nullability changes.
2. **Security Config**: Add BCrypt bean and initial Spring Security filter chain (permit all `/auth/**`).
3. **Registration API**: Implement `POST /api/v1/auth/register` (Email/Password logic).

### Phase 2: OTP & Social
4. **Firebase Integration**: Add Firebase Admin SDK to backend to verify SMS/OTP tokens.
5. **Google Auth API**: Implement `POST /api/v1/auth/google`.
6. **JWT Generation**: Implement JWT service to issue tokens upon successful auth.

### Phase 3: Android Shell
7. **Auth Repository**: Create Android repository for all auth API calls.
8. **Login Screen**: Build UI with Email/Password, OTP, and Google buttons.
9. **Navigation**: Wire successful login to navigate to the Home screen.

## Consequences
- **Pros**: Very low friction for users (OTP/Google).
- **Cons**: Managing three identity types adds complexity to the backend `AuthService`.
- **Mitigation**: Use a common `User` entity and abstract the "Principal" resolution logic.

## Rationale
Firebase is chosen for OTP because it provides a reliable, cross platform SMS verification service with a generous free tier, avoiding the need to manage SMS gateway contracts directly.

## References
- (basis: Project AGENTS.md stack)
- (basis: User design conversation 2026-07-12)

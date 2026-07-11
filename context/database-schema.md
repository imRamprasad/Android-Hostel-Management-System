# Database Schema

Source of truth for tables and relationships. Additive changes only.

---

## Tables

### `users`
Core user table for residents and admins.

| Column | Type | Constraints | Description |
|---|---|---|---|
| `id` | BIGINT | PK, AUTO_INC | Unique identifier |
| `full_name` | VARCHAR(255) | NOT NULL | User's full name |
| `email` | VARCHAR(255) | NOT NULL, UNIQUE | Primary login identifier |
| `phone_number` | VARCHAR(20) | | Contact number |
| `password` | VARCHAR(255) | NOT NULL | Hashed password |
| `is_active` | BOOLEAN | NOT NULL, DEFAULT TRUE | Soft delete/deactivation status |
| `created_at` | TIMESTAMP | NOT NULL | Audit timestamp |

---

## Relationships
- None yet.

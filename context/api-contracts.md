# API Contracts

Source of truth for all endpoints. Never invent an endpoint in code that isn't documented here.

---

## Authentication Epic

### `POST /api/v1/auth/register`
Creates a new user account.

**Request Body:**
```json
{
  "fullName": "John Doe",
  "email": "user@example.com",
  "password": "password123",
  "phoneNumber": "9876543210"
}
```

**Response (Success):**
```json
{
  "success": true,
  "message": "Registration successful",
  "data": {
    "user": {
      "id": 1,
      "fullName": "John Doe",
      "email": "user@example.com",
      "role": "RESIDENT"
    }
  }
}
```

### `POST /api/v1/auth/google`
Authenticates via Google ID Token.

**Request Body:**
```json
{
  "idToken": "google_id_token_string"
}
```

**Response (Success):** Same as `/login`

---

## Error Response Format
All errors follow this shape:
```json
{
  "success": false,
  "message": "Error description",
  "errors": ["Specific field error 1", "Specific field error 2"]
}
```

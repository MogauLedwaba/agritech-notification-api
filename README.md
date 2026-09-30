# AgriTech Notification API

Notification API for the AgriTech project.

## Endpoints

| Method | Endpoint | Purpose |
|---|---|---|
| `GET` | `/api/notifications/health` | Check if the API is running |
| `GET` | `/api/notifications` | Get all notifications |
| `GET` | `/api/notifications/supplier/{supplierId}` | Get notifications for a supplier |
| `POST` | `/api/notifications` | Create a notification |
| `PUT` | `/api/notifications/{id}/read` | Mark a notification as read |

## Create Notification

```json
{
  "supplierId": "SUPPLIER-UUID",
  "groupOrderId": null,
  "message": "Your group order has been updated."
}
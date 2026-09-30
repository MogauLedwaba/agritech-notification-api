# AgriTech Notification API

Notification API for the AgriTechSystem project.

This API manages notifications for suppliers and group orders.

## Features

* Check if the API is running
* Get all notifications
* Get notifications for a supplier
* Create a notification
* Mark notifications as read
* AI-assisted notification service using Ollama

## Endpoints

| Method | Endpoint                                   | Purpose                          |
| ------ | ------------------------------------------ | -------------------------------- |
| `GET`  | `/api/notifications/health`                | Check if the API is running      |
| `GET`  | `/api/notifications`                       | Get all notifications            |
| `GET`  | `/api/notifications/supplier/{supplierId}` | Get notifications for a supplier |
| `POST` | `/api/notifications`                       | Create a notification            |
| `PUT`  | `/api/notifications/{id}/read`             | Mark a notification as read      |

## Create Notification

Example request:

```json
{
  "supplierId": "SUPPLIER-UUID",
  "groupOrderId": null,
  "message": "Your group order has been updated."
}
```

## AI Notification Service

The API includes an AI notification service using Ollama.

The AI service can be used to generate notification messages based on information from the AgriTech application.

## Running the API

Run the application using:

```bash
mvn spring-boot:run
```

The API is available locally through the configured Spring Boot port.

## Docker

The project includes a `Dockerfile` for running the API in a Docker container.

## Project

This API is part of the AgriTechSystem project and provides the notification functionality for the application.

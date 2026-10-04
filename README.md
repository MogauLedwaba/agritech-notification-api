# AgriTech Notification API

Production-ready REST API for managing notifications for suppliers and group orders in the AgriTechSystem project.

## Features

- Check if the Notification API is running
- Get all notifications
- Get notifications for a specific supplier
- Create a notification
- Mark a notification as read
- PostgreSQL database integration
- Docker support
- GitHub Actions / GHCR deployment support

## Endpoints

| Method | Endpoint | Purpose |
|---|---|---|
| GET | `/api/notifications/health` | Check if the API is running |
| GET | `/api/notifications` | Get all notifications |
| GET | `/api/notifications/supplier/{supplierId}` | Get notifications for a supplier |
| POST | `/api/notifications` | Create a notification |
| PUT | `/api/notifications/{id}/read` | Mark a notification as read |

## Notification Object

A notification contains:

```json
{
  "id": "33baa5d3-58e6-483d-ac7c-ceea67d436e2",
  "supplierId": "7899ec2e-0ae7-49c4-8ff7-486641bb52a2",
  "groupOrderId": "0dabb1ff-8818-49c7-90d5-7b2ec47d3f5c",
  "message": "Your group order is ready for collection.",
  "createdAt": "2026-10-04T11:52:29.363238",
  "read": false
}
```

## Create Notification

Send a `POST` request to:

```text
http://localhost:3001/api/notifications
```

Example request:

```json
{
  "supplierId": "7899ec2e-8818-49c7-90d5-7b2ec47d3f5c",
  "groupOrderId": "0dabb1ff-8818-49c7-90d5-7b2ec47d3f5c",
  "message": "Your group order is ready for collection."
}
```

The API automatically generates the notification ID and creation timestamp when they are not provided.

New notifications are created with:

```json
"read": false
```

## Get All Notifications

Send a `GET` request to:

```text
http://localhost:3001/api/notifications
```

This returns all notifications stored in the PostgreSQL database.

## Get Notifications for a Supplier

Send a `GET` request to:

```text
http://localhost:3001/api/notifications/supplier/{supplierId}
```

Example:

```text
http://localhost:3001/api/notifications/supplier/7899ec2e-0ae7-49c4-8ff7-486641bb52a2
```

Notifications are returned with the newest notifications first.

## Mark Notification as Read

Send a `PUT` request to:

```text
http://localhost:3001/api/notifications/{id}/read
```

Example:

```text
http://localhost:3001/api/notifications/33baa5d3-58e6-483d-ac7c-ceea67d436e2/read
```

A successful response changes:

```json
"read": false
```

to:

```json
"read": true
```

## Health Check

Send a `GET` request to:

```text
http://localhost:3001/api/notifications/health
```

Successful response:

```text
Notification API is running
```

## Database

The Notification API uses PostgreSQL.

Default local database configuration:

```text
Database: agritech
Username: agritech_user
Password: change_me
Port: 5433
```

The Spring Boot application receives the database configuration through environment variables:

```text
SPRING_DATASOURCE_URL
SPRING_DATASOURCE_USERNAME
SPRING_DATASOURCE_PASSWORD
```

Example local configuration:

```text
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5433/agritech
SPRING_DATASOURCE_USERNAME=agritech_user
SPRING_DATASOURCE_PASSWORD=change_me
```

## Running Locally

Start the PostgreSQL database container first.

Then set the database environment variables in PowerShell:

```powershell
$env:SPRING_DATASOURCE_URL="jdbc:postgresql://localhost:5433/agritech"
$env:SPRING_DATASOURCE_USERNAME="agritech_user"
$env:SPRING_DATASOURCE_PASSWORD="change_me"
```

Run the Spring Boot application:

```powershell
mvn spring-boot:run
```

The Notification API runs on:

```text
http://localhost:3001
```

## Docker

The project includes a `Dockerfile` for containerizing the Notification API.

The application can be connected to the AgriTech PostgreSQL database using the required environment variables.

## Project Structure

```text
src/main/java/com/agritech/notification/
│
├── Notification.java
├── NotificationApiApplication.java
├── NotificationController.java
├── NotificationRepository.java
│
└── ai/
    ├── AiController.java
    ├── AiRequest.java
    └── AiService.java
```

## Testing

The API endpoints can be tested using Postman or another REST client.

Example endpoint:

```text
GET http://localhost:3001/api/notifications
```

A successful request returns the notifications stored in the database.

## Production

The project is configured for containerized deployment and can be published through GitHub Container Registry.

The repository is:

```text
agritech-notification-api
```

The API is part of the larger AgriTechSystem application and provides the notification functionality used by the platform.

## Project

**AgriTech Notification API**

Notification API for the AgriTechSystem project.
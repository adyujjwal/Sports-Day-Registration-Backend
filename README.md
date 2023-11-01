![SpringBoot](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)

# Sports Day Event Registration Backend

This is the backend service for the Sports Day Event Registration application. It provides the necessary APIs for user registration, event management, and event registration. Users can create accounts, log in, view events, register for events, and manage their registrations.

## Table of Contents

- [Features](#features)
- [Getting Started](#getting-started)
- [API Endpoints](#api-endpoints)

## Features

- Create User: Allows users to create accounts.
- Login User: Allows users to log in with their user IDs.
- Get All Events List: Provides a list of all available events.
- Get All Registered Events List: Lists events for which a user has already registered.
- Register Event: Registers a user for a specific event.
- Unregister Event: Unregisters a user from an event.
- Prevents duplicate event registration by the same user.
- Event registration conflict checking (events with overlapping times).
- Secure user authentication.
- Data storage and retrieval using a relational database.

## Getting Started

To get started with the Sports Day Event Registration Backend, follow these steps:

### Prerequisites

- Java Development Kit (JDK): [Download JDK](https://adoptium.net/).
- Gradle Build Tool: [Download Gradle](https://gradle.org/).

### API Endpoints

The following API endpoints are available:
```
POST /api/users - Create a user.
POST /api/users/login - Log in a user.
GET /api/events - Get a list of all events.
GET /api/events/registered - Get a list of events registered by the user.
POST /api/events/register - Register a user for an event.
DELETE /api/events/unregister - Unregister a user from an event.
```

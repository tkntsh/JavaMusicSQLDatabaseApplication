# Music Database Management Application

This repository contains the Java implementation of a Music Database Management Application. The project focuses on managing playlists, songs, and user information using Object-Oriented Programming (OOP) principles and database connectivity.

---

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Setup Instructions](#setup-instructions)
- [Project Structure](#project-structure)
- [Future Improvements](#future-improvements)

---

## Overview

This application is designed to manage and organize information about songs, playlists, and users in a music database. It connects to a Microsoft Access database and provides functionalities to add, update, delete, and search for records. The application leverages Java's JDBC for database operations and adheres to OOP practices to ensure scalability and maintainability.

---

## Features

### Song Management
- retrieve all songs from the database.
- add new songs with details such as name, release date, genre, duration, and album.
- search for songs by artist ID.
- update song information.
- delete song records.

### Playlist Management
- retrieve all playlists from the database.
- add new playlists with song and listener details.
- search for playlists by listener ID.
- update playlist information.
- delete playlists from the database.
- sort playlists alphabetically by listener ID.

### User Management
- retrieve all users from a file (`Login.txt`).
- search for users by username.
- validate usernames and passwords for login.
- display all user information.

---

## Technologies Used

- **Programming Languages**: Java, SQL
- **Database**: Microsoft Access
- **Libraries**: JDBC (via UCanAccess)
- **Development Environment**: NetBeans IDE

---

## Setup Instructions

1. **Prerequisites**:
   - Java Development Kit (JDK)
   - NetBeans IDE
   - Microsoft Access
   - UCanAccess library for database connectivity

2. **Database Configuration**:
   - ensure the database contains tables for songs and playlists (`tblSongs` and `tblPlaylists`).
   - populate tables with appropriate fields for song and playlist details.

3. **Running the Application**:
   - clone the repository:
     ```bash
     git clone <repository_url>
     ```
   - open the project in NetBeans IDE.
   - add the UCanAccess library to the project dependencies.
   - configure the database connection URL in the `DB` class.
   - place the `Login.txt` file in the appropriate directory.
   - build and run the project.

---

## Project Structure

- **Entities**:
  - `Playlist.java`: represents a playlist with song and listener details.
  - `Song.java`: represents a song with attributes like name, release date, genre, and album.
  - `Users.java`: represents a user with username and password details.

- **Managers**:
  - `PlaylistManager.java`: handles CRUD operations, sorting, and searching for playlists.
  - `SongManager.java`: manages CRUD operations, sorting, and searching for songs.
  - `UserManager.java`: manages user validation and retrieval from a text file.

- **Database Handler**:
  - `DB.java`: manages database connections and executes SQL queries.

---

## Future Improvements

- enhance error handling for database and file operations.
- add input validations for data consistency.
- implement a graphical user interface (GUI) for easier user interaction.
- optimize sorting and searching algorithms for scalability.

---

For any queries or contributions, feel free to reach out via GitHub or email.

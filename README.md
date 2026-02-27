# User Profile Manager - JavaFX Desktop Application

A modern desktop application built with JavaFX for managing user profiles with features like profile creation, image management, status updates, and friend tracking.

## Features

- **Profile Management**
  - Create new user profiles
  - Delete existing profiles
  - Lookup profile information
  - Clear profile data

- **Media Management**
  - Upload and change profile pictures
  - Support for JPG image format
  - Automatic image storage in profile directories

- **User Information**
  - Manage user status/bio
  - Add and track friends
  - Persistent data storage

- **Customization**
  - Multiple theme options (Default, Theme 1, Theme 2, Theme 3)
  - Dark and light mode support
  - Intuitive GUI with BorderPane layout

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/example/ourproject/
│   │       ├── FXApplication.java    # Main JavaFX application
│   │       └── Profile.java          # Profile management logic
│   └── resources/
│       └── com/example/ourproject/
│           └── hello-view.fxml       # FXML layout file
└── data/                             # User profile data storage
    └── [username]/                   # Profile folders
        ├── name.txt                  # Username
        ├── status.txt                # User status
        ├── friends.txt               # Friends list
        └── [profile_picture].jpg     # Profile image
```

## Technologies

- **Language**: Java 20
- **GUI Framework**: JavaFX 20.0.1
- **Build Tool**: Maven
- **Testing**: JUnit 5

## Prerequisites

- Java Development Kit (JDK) 20 or higher
- Maven 3.6+
- Windows, macOS, or Linux

## Getting Started

### Build the Project

```bash
mvn clean install
```

### Run the Application

```bash
mvn javafx:run
```

Or run directly from your IDE by executing the `FXApplication` class.

### Using Maven Wrapper

On Windows:
```bash
mvnw javafx:run
```

On macOS/Linux:
```bash
./mvnw javafx:run
```

## Usage

1. **Add Profile**: Enter a username in the text field and click "Add"
2. **Lookup Profile**: Enter a username and click "Lookup" to view profile details
3. **Delete Profile**: Enter a username and click "Delete"
4. **Change Picture**: Provide an image path and click "Change Picture"
5. **Change Status**: Enter status text and click "Change Status"
6. **Add Friends**: Enter friend names and click "Add Friends"
7. **Switch Themes**: Use the dropdown menu to select different themes

## Project Configuration

### Maven Dependencies

- **JavaFX Controls**: UI components
- **JavaFX FXML**: Layout definition
- **JUnit 5**: Unit testing framework

### Java Version

- Source & Target: Java 20
- Encoding: UTF-8

## Development Notes

- Profile data is stored in `src/data/[username]/` directory
- Each profile maintains separate data files for different information
- Images are automatically managed and stored in the profile directory
- The application uses BorderPane layout with Top, Left, and Center regions

## Future Enhancements

- Database integration for persistent storage
- Export/Import profile functionality
- Profile search and filter features
- User authentication system
- Cloud synchronization

## License

This project is provided as-is for educational and personal use.

## Author

Created as a JavaFX learning project demonstrating GUI development, file I/O, and event handling.

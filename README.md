# User Profile Manager - JavaFX Desktop Application

A desktop application built with JavaFX for managing user profiles with features like profile creation, image management, status updates, and friend tracking.

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

## Getting Started

 ```run directly from your IDE by executing the `FXApplication` class.```


## Usage

1. **Add Profile**: Enter a username in the text field and click "Add"
2. **Lookup Profile**: Enter a username and click "Lookup" to view profile details
3. **Delete Profile**: Enter a username and click "Delete"
4. **Change Picture**: Provide an image path and click "Change Picture"
5. **Change Status**: Enter status text and click "Change Status"
6. **Add Friends**: Enter friend names and click "Add Friends"
7. **Switch Themes**: Use the dropdown menu to select different themes






## Author
Ibrahim Alshayea
Created as a JavaFX learning project demonstrating GUI development, file I/O, and event handling.

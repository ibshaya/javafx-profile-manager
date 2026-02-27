package com.example.ourproject;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class FXApplication extends Application {
    BorderPane borderPane;
    HBox topHBox;
    Label nameLabel;
    private TextField nameTextField;
    Button addButton;
    Button deleteButton;
    Button lookupButton;
    TextField imagePathTextFiled;
    Button changePictureButton;
    TextField changeStatusTextField;
    Button changeStatusButton;
    TextField addFriendTextFiled;
    ComboBox<String> themeComboBox;
    Button clearButton;

    private ArrayList<String> profileNames = new ArrayList<>();

    @Override
    public void start(Stage stage) {
        // Creating the scene
        borderPane = new BorderPane();
        BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY);
        Background background = new Background(backgroundFill);

        // Top Region (HBox)
        topHBox = new HBox(10);
        topHBox.setAlignment(Pos.CENTER);
        topHBox.setBackground(background);
        nameLabel = new Label("Name");
        nameTextField = new TextField();
        addButton = new Button("Add");
        deleteButton = new Button("Delete");
        lookupButton = new Button("Lookup");
        clearButton = new Button("Clear");

        themeComboBox = new ComboBox<>();
        themeComboBox.getItems().addAll("Default", "Theme 1", "Theme 2", "Theme 3");
        themeComboBox.setValue("Default");
        topHBox.getChildren().add(themeComboBox);

        themeComboBox.setOnAction(e -> applyTheme());


        topHBox.getChildren().addAll(nameLabel, nameTextField, addButton, deleteButton, lookupButton, clearButton);


        borderPane.setTop(topHBox);

        // Left Region (VBox)
        VBox leftVBox = new VBox(20);
        leftVBox.setAlignment(Pos.CENTER);

        // Sub VBox 1
        VBox subVBox1 = new VBox(5);
        changeStatusTextField = new TextField();
        changeStatusButton = new Button("Change Status");
        subVBox1.getChildren().addAll(changeStatusTextField, changeStatusButton);

        // Sub VBox 2
        VBox subVBox2 = new VBox(5);
        imagePathTextFiled = new TextField();
        changePictureButton = new Button("Change Picture");
        subVBox2.getChildren().addAll(imagePathTextFiled, changePictureButton);

        // Sub VBox 3
        VBox subVBox3 = new VBox(5);
        addFriendTextFiled = new TextField();
        Button addFriendsButton = new Button("Add Friends");
        subVBox3.getChildren().addAll(addFriendTextFiled, addFriendsButton);
        leftVBox.setBackground(background);

        leftVBox.getChildren().addAll(subVBox1, subVBox2, subVBox3);
        borderPane.setLeft(leftVBox);
        // Creating the events
        addButton.setOnAction(e -> addProfile());
        deleteButton.setOnAction(e -> deleteProfile());
        changePictureButton.setOnAction(e -> changePic());
        changeStatusButton.setOnAction((e -> changeStatus()));
        addFriendsButton.setOnAction(e -> addFriends());
        lookupButton.setOnAction(e -> showUserInfo());
        clearButton.setOnAction(e -> clearInputFields());




        Scene scene = new Scene(borderPane, 637, 400);
        stage.setScene(scene);
        stage.setTitle("JavaFX Application");
        stage.show();


    }

    public void addProfile() {
        String newName = nameTextField.getText();
        Profile profile = new Profile(newName);

        boolean prfileCreated = profile.creatProfile();
        if (!newName.isEmpty() && prfileCreated) {
            showCenter("add");
        }
    }

    public void deleteProfile() {
        String newName = nameTextField.getText();
        Profile profile = new Profile(newName);
        System.out.println("Delete profile button clicked");
        if (profile.userProfileExists()) {
            profile.deleteProfile();
            showCenter("delete");
        }
    }

    public void changePic() {
        System.out.println("button");
        String imagePath = imagePathTextFiled.getText();
        String newName = nameTextField.getText();

        if (!newName.isEmpty()) {
            Profile profile = new Profile(newName);

            if (profile.userProfileExists()) {
                profile.creatImage("src\\" + imagePath);
                showCenter("changeImage");
            }
        } else {
            System.out.println("Name cannot be empty");
        }
    }

    public void changeStatus() {
        String newName = nameTextField.getText();
        Profile profile = new Profile(newName);


        if (!newName.isEmpty() && profile.userProfileExists()) {
            String status = changeStatusTextField.getText();
            profile.saveStatus(status);
            showCenter("changeStatus");
        } else {
            System.out.println("User folder does not exist. Cannot save status.");
        }
    }

    public void addFriends() {
        String currentUser = nameTextField.getText();
        String friendUser = addFriendTextFiled.getText();

        if (!friendUser.isEmpty()) {
            Profile currentUserFolder = new Profile(currentUser);
            if (currentUserFolder.userProfileExists() && !friendUser.equals(currentUser)) {
                currentUserFolder.friends(currentUser, friendUser);
                System.out.println("Friend added");
                showCenter("add friend");
            } else {
                System.out.println("Current user folder does not exist");
            }
        } else {
            System.out.println("Friend username is empty.");
        }
    }

    public void showUserInfo() {
        String newName = nameTextField.getText();
        Profile profile = new Profile(newName);
        String friends = "";
        String staus = "";
        String imagePath = "";
        String name = "";
        File[] fileInfo = profile.getUserFiles();
        if (fileInfo != null) {

            for (File info : fileInfo) {

                if (info.getName().startsWith("f")) {

                    try (Scanner scanner = new Scanner(info)) {
                        while (scanner.hasNext()) {
                            String line = scanner.nextLine();
                            Profile friendFolder = new Profile(line);
                            if (friendFolder.userProfileExists()) {
                                friends += line + " ";
                            }
                        }
                        System.out.println("Content of the file: " + friends);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else if (info.getName().startsWith("s")) {
                    try (Scanner scanner = new Scanner(info)) {
                        while (scanner.hasNext()) {
                            String line = scanner.nextLine();
                            staus += line + " ";
                        }
                        System.out.println("Content of the file: " + staus);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (info.getName().startsWith("im")) {
                    imagePath += info.getPath();
                    System.out.println(info.getPath());
                } else if (info.getName().startsWith("n")) {
                    try (Scanner scanner = new Scanner(info)) {
                        while (scanner.hasNext()) {
                            String line = scanner.nextLine();
                            name += line + " ";
                        }
                        System.out.println("Content of the file: " + name);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


            }
        }
        if (!name.isEmpty()) {
            showProfile(friends, staus, imagePath, name);
        } else {
            showCenter("noProfile");
        }
    }

    public void showProfile(String friends, String status, String imagePath, String name) {
        Pane centerPane = new Pane();
        Label label1 = new Label(name);
        label1.setTextFill(Color.BLUE);
        label1.setFont(new Font(33));
        label1.setLayoutX(10);
        label1.setLayoutY(1);
        Rectangle rectangle = new Rectangle(191, 169);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        rectangle.setArcWidth(5.0);
        rectangle.setArcHeight(3);
        rectangle.setLayoutX(10);
        rectangle.setLayoutY(40);
        Label label = new Label("No Image");
        label.setFont(new Font(23.0));
        label.setLayoutX(42.0);
        label.setLayoutY(64.0);
        label.setPrefSize(116.0, 41.0);
        label.setAlignment(Pos.CENTER);
        label.setCache(true);
        //Add the rectangle and label to the root pane
        Pane rootPane = new Pane();
        rootPane.getChildren().addAll(rectangle, label);
        ImageView imageView;
        if (!imagePath.isEmpty()) {
            imageView = new ImageView(new Image("file:" + imagePath));
            imageView.setFitHeight(140);
            imageView.setFitWidth(190);
            imageView.setLayoutX(10);
            imageView.setLayoutY(40);
            centerPane.getChildren().add(imageView);
        } else {
            centerPane.getChildren().add(rootPane);
        }
        Label label2;
        if (status.isEmpty()) {
            label2 = new Label("No current status");
        } else {
            label2 = new Label(status);
        }
        label2.setLayoutX(10);
        label2.setLayoutY(215);
        Label label4 = new Label("frindes :\n" + friends);
        label4.setLayoutX(298);
        label4.setLayoutY(35);
        Label label3 = new Label("Displaying " + name);
        label3.setLayoutX(240);
        label3.setLayoutY(350);
        centerPane.getChildren().addAll(label1, label2, label3, label4);
        borderPane.setCenter(centerPane);
    }


    public void showCenter(String cond) {


        String newName = nameTextField.getText();
        Profile profile = new Profile(newName);
        String friends = "";
        String staus = "";
        String imagePath = "";
        String name = "";
        File[] fileInfo = profile.getUserFiles();
        if (fileInfo != null) {

            for (File info : fileInfo) {

                if (info.getName().startsWith("f")) {
                    try (Scanner scanner = new Scanner(info)) {
                        while (scanner.hasNext()) {
                            String line = scanner.nextLine();
                            Profile friendFolder = new Profile(line);
                            if (friendFolder.userProfileExists()) {
                                friends += line + " ";
                            }
                        }
                        System.out.println("Content of the file: " + friends);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else if (info.getName().startsWith("s")) {
                    try (Scanner scanner = new Scanner(info)) {
                        while (scanner.hasNext()) {
                            String line = scanner.nextLine();
                            staus += line + " ";
                        }
                        System.out.println("Content of the file: " + staus);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (info.getName().startsWith("im")) {
                    imagePath += info.getPath();
                    System.out.println(info.getPath());
                } else if (info.getName().startsWith("n")) {
                    try (Scanner scanner = new Scanner(info)) {
                        while (scanner.hasNext()) {
                            String line = scanner.nextLine();
                            name += line + " ";
                        }
                        System.out.println("Content of the file: " + name);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


            }
        } else {
            System.out.println("Error retrieving user information.");
        }

        Pane centerPane = new Pane();
        Label label1 = new Label(newName);
        label1.setTextFill(Color.BLUE);
        label1.setFont(new Font(33));
        label1.setLayoutX(10);
        label1.setLayoutY(1);

        Rectangle rectangle = new Rectangle(191, 169);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        rectangle.setArcWidth(5.0);
        rectangle.setArcHeight(3);
        rectangle.setLayoutX(10);
        rectangle.setLayoutY(40);

        Label label = new Label("No Image");
        label.setFont(new Font(23.0));
        label.setLayoutX(42.0);
        label.setLayoutY(64.0);
        label.setPrefSize(116.0, 41.0);
        label.setAlignment(Pos.CENTER);
        label.setCache(true);

        //Add the rectangle and label to the root pane
        Pane rootPane = new Pane();
        rootPane.getChildren().addAll(rectangle, label);

        ImageView imageView = new ImageView(new Image("file:src\\data\\" + newName + "\\" + imagePathTextFiled.getText()));
        imageView.setFitHeight(140);
        imageView.setFitWidth(190);
        imageView.setLayoutX(10);
        imageView.setLayoutY(40);
        Label label2;
        if (staus.isEmpty()) {

            label2 = new Label("No current status");
            label2.setLayoutX(10);
            label2.setLayoutY(215);
        } else {
            label2 = new Label(staus);
            label2.setLayoutX(10);
            label2.setLayoutY(215);

        }

        Label label4 = new Label("frindes :\n" + friends);
        label4.setLayoutX(298);
        label4.setLayoutY(35);
        if (cond.equals("noProfile")) {
            Label label3 = new Label("A profile with the name" + newName + " does not exist");
            label3.setLayoutX(240);
            label3.setLayoutY(350);
            centerPane.getChildren().add(label3);
        } else if (cond.equals("add")) {

            Label label3 = new Label(newName + " added as a new profile");
            label3.setLayoutX(240);
            label3.setLayoutY(350);
            centerPane.getChildren().addAll(label1, rootPane, label2, label3, label4);
        } else if (cond.equals("delete")) {
            Label label3 = new Label("Profile of " + newName + " has been deleted");
            label3.setLayoutX(240);
            label3.setLayoutY(350);
            centerPane.getChildren().add(label3);
        } else if (cond.equals("changeImage")) {
            Label label3 = new Label("Profile picture of " + newName + " has been changed");
            label3.setLayoutX(240);
            label3.setLayoutY(350);
            centerPane.getChildren().addAll(label1, imageView, label2, label3, label4);
        } else if (cond.equals("changeStatus")) {


            Label label3 = new Label("Profile status of " + newName + " has been changed");
            label3.setLayoutX(240);
            label3.setLayoutY(350);
            if (!imagePath.isEmpty()) {
                centerPane.getChildren().add(imageView);
            } else {
                centerPane.getChildren().add(rootPane);
            }
            centerPane.getChildren().addAll(label1, label2, label3, label4);
        } else if (cond.equals("add friend")) {
            Label label3;
            if (friends.contains(addFriendTextFiled.getText())) {
                label3 = new Label(addFriendTextFiled.getText() + " added as friend");
                label3.setLayoutX(240);
                label3.setLayoutY(350);
                centerPane.getChildren().add(label3);
            }

            if (!imagePath.isEmpty()) {
                centerPane.getChildren().add(imageView);

            } else {
                centerPane.getChildren().add(rootPane);

            }
            centerPane.getChildren().addAll(label1, label2, label4);

        }
        borderPane.setCenter(centerPane);
    }

    private void applyTheme() {
        String selectedTheme = themeComboBox.getValue();

        switch (selectedTheme) {
            case "Default":
                borderPane.setStyle("");
                break;
            case "Theme 1":
                borderPane.setStyle("-fx-background-color: #C0C0C0;");
                break;
            case "Theme 2":
                borderPane.setStyle("-fx-background-color: #87CEEB;");
                break;
            case "Theme 3":
                borderPane.setStyle("-fx-background-color: #F08080;");
                break;
        }
    }


    public void clearInputFields() {
        //Clear the text fields in the application
        nameTextField.clear();
        changeStatusTextField.clear();
        imagePathTextFiled.clear();
        addFriendTextFiled.clear();
    }




    public static void main(String[] args) {

        launch(args);
    }
}


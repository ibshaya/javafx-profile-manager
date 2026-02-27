package com.example.ourproject;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Profile{
    String name;
    File folder;
    public Profile(String name){
        this.name = name;

    }
    public boolean creatProfile() {
        folder = new File("src\\data\\" + name);

        if (!folder.exists()) {
            boolean success = folder.mkdir();
            if (success) {
                System.out.println("New user folder has been created");
                String filePath = "src\\data\\" + name + "\\name.txt";
                File file = new File(filePath);

                try {
                    PrintWriter writer = new PrintWriter(new FileWriter(file));
                    writer.print(name);
                    writer.close();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                System.out.println("Failed to create the user folder");
            }

            return success;
        } else {
            System.out.println("The user folder already exists");
            return false;
        }
    }

    public void creatImage(String imagePath) {
        File sourceImage = new File(imagePath);

        if (folder.exists() && sourceImage.exists()) {
            File[] existingImages = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".jpg"));
            if (existingImages != null) {
                for (File existingImage : existingImages) {
                    if (!existingImage.delete()) {
                        System.out.println("Failed to delete existing image");
                    }
                }
            }

            try {
                Path sourcePath = sourceImage.toPath();
                Path targetPath = new File(folder, sourceImage.getName()).toPath();

                Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);

                System.out.println("Image moved successfully");
            } catch (FileAlreadyExistsException e) {
                System.out.println("File already exists in the target folder");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("target folder does not exist");
        }
    }

    public void deleteProfile() {
        System.out.println("Absolute Path: " + folder.getAbsolutePath());

        if (folder.exists()) {
            File[] contents = folder.listFiles();

            if (contents != null) {
                for (File file : contents) {
                    if (!file.delete()) {
                        System.out.println("Failed to delete file: " + file.getName());
                    }
                }
            }

            if (folder.delete()) {
                System.out.println("Profile folder has been deleted");
            } else {
                System.out.println("Failed to delete profile folder");
            }
        } else {
            System.out.println("Profile folder does not exist");
        }
    }


    public void saveStatus(String status) {
        if (folder != null) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(folder.getPath() + "\\status.txt"))) {
                writer.write(status);
                System.out.println("Status saved successfully");
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            System.out.println("User folder does not exist");
        }
    }

    public boolean userProfileExists() {
        folder = new File("src\\data\\" + name);
        return folder.exists();
    }


    public void friends(String currentUser, String friendUser) {
        File currentUserFolder = new File("src\\data\\" + currentUser);
        File friendUserFolder = new File("src\\data\\" + friendUser);

        if (currentUserFolder.exists() && friendUserFolder.exists()) {
            try {
                PrintWriter currentUserWriter = new PrintWriter(new FileWriter(currentUserFolder.getPath() + "\\friends.txt", true));
                currentUserWriter.println(friendUser);
                currentUserWriter.close();
                System.out.println("Friend added to " + currentUser + "friend list");

                PrintWriter friendUserWriter = new PrintWriter(new FileWriter(friendUserFolder.getPath() + "\\friends.txt", true));
                friendUserWriter.println(currentUser);
                friendUserWriter.close();
                System.out.println("Friend added to " + friendUser + "friend list");
            }
            catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            System.out.println("friend folder does not exist");
        }
    }
    public File[] getUserFiles() {
        if (userProfileExists() && folder.isDirectory()) {
            return folder.listFiles();
        }
        return null;
    }


}

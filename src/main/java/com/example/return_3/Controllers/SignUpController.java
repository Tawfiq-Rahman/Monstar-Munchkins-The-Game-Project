package com.example.return_3.Controllers;

import com.example.return_3.db.MyJDBC;
import com.example.return_3.main.AssetSetter;
import com.example.return_3.main.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.Objects;

import static com.example.return_3.main.Game.*;
import static com.example.return_3.main.Game.gameInstance;

public class SignUpController {
    public TextField username;
    public Button signUpBtn;
    public PasswordField password1;
    public PasswordField password2;
    public Button closeBtn;
    public Label failedText;
    public Button loginBtn;

    public void signUp(ActionEvent event) throws IOException {
        if(validateUserInput(username.getText(), password1.getText(), password2.getText())) {
            int userId = MyJDBC.register(username.getText(), password1.getText());
            if(userId != -1) { // Registration successful
               // MyJDBC.addInteractiveTile(userId, col, row, mapNum, destroyed); // Add interactive tiles
                //setup interactive Tile
                failedText.setText("");
                gameInstance.playSoundEffect(gameInstance.soundEffect.click);
                AssetSetter.addObjectToDB(userId);
                AssetSetter.addInventoryToDB(userId);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/return_3/login.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
            } else {
                gameInstance.playSoundEffect(gameInstance.soundEffect.error);
                failedText.setText("This username is already taken!");
            }
        }
    }

    public void close(ActionEvent event) {
        gameInstance.playSoundEffect(gameInstance.soundEffect.click);
        Game.exitWithConfirmation();
    }

    private boolean validateUserInput(String username, String password, String rePassword) {
        if (username.isEmpty()) {
            gameInstance.playSoundEffect(gameInstance.soundEffect.error);
            failedText.setText("Please fill in the username field!");
            return false;
        }
        if (password.isEmpty()) {
            gameInstance.playSoundEffect(gameInstance.soundEffect.error);
            failedText.setText("Please fill in the password field!");
            return false;
        }
        if (rePassword.isEmpty()) {
            gameInstance.playSoundEffect(gameInstance.soundEffect.error);
            failedText.setText("Please fill in the re-enter password field!");
            return false;
        }

        // Username has to be at least 5 characters long
        if (username.length() < 5) {
            gameInstance.playSoundEffect(gameInstance.soundEffect.error);
            failedText.setText("Username has to be at least 5 characters long!");
            return false;
        }

        // Password and rePassword must be the same
        if (!password.equals(rePassword)) {
            gameInstance.playSoundEffect(gameInstance.soundEffect.error);
            failedText.setText("Passwords do not match!");
            return false;
        }

        // Passes validation
        return true;
    }

    public void goToLogin(ActionEvent event) throws IOException {
        gameInstance.playSoundEffect(gameInstance.soundEffect.click);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/return_3/login.fxml"));
        loginScene = new Scene(fxmlLoader.load());
        primaryStage.setScene(loginScene);
    }
}

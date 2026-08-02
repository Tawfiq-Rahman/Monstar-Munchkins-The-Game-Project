package com.example.return_3.Controllers;

import com.example.return_3.globalChat.Client;
import com.example.return_3.main.Game;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.List;

public class GlobalChatController {
    public TextArea textField;
    public Button sendBtn;
    public Label label1;
    public Label label2;
    public Label label3;
    public Label label4;
    public Label label5;
    public Label label6;
    public Label label7;
    public Label label8;
    public Label label9;
    public Label label10;
    public Label label11;
    public Button exitBtn;

    private final List<Label> chatLabels = new ArrayList<>();

    public void initialize() {
        chatLabels.add(label1);
        chatLabels.add(label2);
        chatLabels.add(label3);
        chatLabels.add(label4);
        chatLabels.add(label5);
        chatLabels.add(label6);
        chatLabels.add(label7);
        chatLabels.add(label8);
        chatLabels.add(label9);
        chatLabels.add(label10);
        chatLabels.add(label11);
    }

    public String getMessage() {
        String msg = textField.getText();
        textField.clear();
        return msg;
    }

    public void updateChat(String msg) {
        Platform.runLater(() -> {
            for (int i = chatLabels.size() - 1; i > 0; i--) {
                chatLabels.get(i).setText(chatLabels.get(i - 1).getText());
            }
            chatLabels.get(0).setText(msg);
        });
    }

    public void onSend(ActionEvent actionEvent) {
        String message = getMessage();
        if (!message.trim().isEmpty()) {
            Client.clientWriterThread.sendMessage(message);
        }
    }

    public void onExit(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Confirmation");
        alert.setHeaderText("You are about to exit!");
        alert.setContentText("Are you sure you want to exit?");
        ButtonType buttonTypeYes = new ButtonType("Yes");
        ButtonType buttonTypeNo = new ButtonType("No");
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
        alert.getDialogPane().getStylesheets().add(Game.class.getResource("/com/example/return_3/alert.css").toExternalForm());
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == buttonTypeYes) {
                Game.gameInstance.keyHandler.setBooleanAll(false);
                Game.showGameScene();
            }
        });
    }
}

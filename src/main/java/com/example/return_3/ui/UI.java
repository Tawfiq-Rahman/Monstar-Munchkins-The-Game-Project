package com.example.return_3.ui;

import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;
import com.example.return_3.main.UtilityTool;
import com.example.return_3.object.OBJ_Heart;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


import java.awt.*;
import java.util.ArrayList;

public class UI {
    Game game;
    //public UI_MainGame uiMainGame = new UI_MainGame(Game.gameInstance);
    public UI_MainGame uiMainGame;
    //Entity entity;
    GraphicsContext gc;


    //CONSTRUCTOR START

    public UI(Game game){
        this.game = game;
        this.uiMainGame = new UI_MainGame(game);


    }

    public void draw(GraphicsContext gc) {
        //we did this because we need to use this gc in other methods also
        this.gc = gc;


            // <------PLAY STATE------>
            if (game.gameState == game.playState) {
                uiMainGame.drawPlayerLife();
                uiMainGame.drawPlayerLevel();
                uiMainGame.drawMessage();
//                uiMainGame.drawEnergy();
                uiMainGame.drawCoin();

            }

            // <-------Hospital State------->
            if (game.gameState == game.hospitalState) {
                uiMainGame.hospitalScreen();
            }


            // <-------MenuBar State------>
            if (game.gameState == game.menuBarState) {
                uiMainGame.menuBarScreen();
            }

            // <-------Pause State------>
            if (game.gameState == game.pauseState) {
                uiMainGame.pauseScreen();
            }

            // Level up state
            if (game.gameState == game.levelUpState) {
                uiMainGame.levelUpScreen();
            }

            // Level up state
            if (game.gameState == game.gameOverState) {
                //uiMainGame.drawBackGroundGame();
                uiMainGame.gameOverScreen();

            }


            // <-------DIALOGUE STATE------->
            if (game.gameState == game.dialogueState) {
                uiMainGame.drawDialogueScreen();
                uiMainGame.drawMessage();
            }

            // <-------Message STATE------->
            if (game.gameState == game.messageState) {
                uiMainGame.drawDialogueScreen();
                uiMainGame.drawMessage();
            }


            // <--------WizConversationState STATE-------->
            if (game.gameState == game.wizConversationState) {
                uiMainGame.drawWizConversationScreen();
            }


            // <-------Character State------->
            if (game.gameState == game.characterState) {
                uiMainGame.drawCharacterScreen();
                uiMainGame.drawInventory(game.player, true,18);
            }

            if (game.gameState == game.tradeState) {
                uiMainGame.drawTradeScreen();
                uiMainGame.drawMessageRight(24);
            }
            if (game.gameState == game.shipTeleportState) {
                uiMainGame.shipTeleportScreen();
            }if (game.gameState == game.fisheriesState) {
                uiMainGame.fisheriesScreen();
            }if (game.gameState == game.globalChatState) {
               uiMainGame.globalChatScreen();
            }

            // <----------Settings State----------->
            if (game.gameState == game.settingsState) {
                uiMainGame.drawSettingsScreen();
            } // <----------Guideline State----------->
            if (game.gameState == game.guidelineState) {
                uiMainGame.guidelineScreen();
            }
            // <----------Transition State----------->
            if (game.gameState == game.transitionState) {
                System.out.println("Transistion method is called in UI");
                uiMainGame.drawTransition();
            }

    }
}
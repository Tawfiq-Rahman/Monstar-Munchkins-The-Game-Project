package com.example.return_3.gameCenter.spaceInvaders;

import com.example.return_3.main.Game;
import com.example.return_3.main.UtilityTool;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.awt.*;

public class UI_GameSpaceInvaders {
    GraphicsContext gc;
    Game game;
    UtilityTool uTool;
    public int commandNum = 0; // this is for showing our menu specific commands
    Image titleImage;
    public int subState=0;
    public UI_GameSpaceInvaders(Game game,GraphicsContext gc) {
        this.gc = gc;
        this.game = game;
        uTool=new UtilityTool();
        titleImage= uTool.loadImage("/gameCenter/spaceInvaders/titleScreen.png",game.screenWidth,game.screenHeight);
    }

    public void draw() {
        // <------PLAY STATE------>
        if (game.gameState == game.playState) {
//            drawPlayerLife();
//            uiMainGame.drawPlayerLevel();
//            uiMainGame.drawMessage();
//            uiMainGame.drawEnergy();
//            uiMainGame.drawCoin();

        }




        // <-------MenuBar State------>
        if (game.gameState == game.menuBarState) {
            menuBarScreen();
        }
        // <-------TITLE State------>
        if (game.gameState == game.titleState) {
            titleScreen();
        }

    }

    public void menuBarScreen(){
        // Create a Frame...
        final int frameX = game.tileSize * 5;
        final int frameY = game.tileSize * 2;
        final int frameWidth = game.tileSize * 20;
        final int frameHeight = game.tileSize * 14;

        Color c = Color.rgb(255, 209, 184);
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        String text = "Game Name";
        int textX = getXForCenteredText(text);
        int textY = frameY + game.tileSize + 16;

        gc.setFill(Color.rgb(255, 255, 255));
        gc.setFont(Font.font("Arial", 40));
        gc.fillText(text, textX, textY);

        // Menu...
        gc.setFill(Color.rgb(255, 255, 255));
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        text = "Back To Town hall";
        textX = getXForCenteredText(text);
        textY = game.tileSize * 6;
        gc.fillText(text, textX, textY);
        if (commandNum == 0) {
            gc.fillText(">", textX-game.tileSize, textY);
        }

        text = "Exit Game";
        textX = getXForCenteredText(text);
        textY = game.tileSize * 7;
        gc.fillText(text, textX, textY);
        if (commandNum == 1) {
            gc.fillText(">", textX-game.tileSize, textY);
        }
    }
    public void titleScreen(){
        gc.drawImage(titleImage,0,0);
        String text="WELCOME TO SPACE INVADERS";
        int x=getXForCenteredText(text);
        int y=163;
        int width=568;
        int height=42;
        gc.setStroke(Color.rgb(170,104,227));
        gc.setLineWidth(1);
        gc.setFill(Color.rgb(239,244,253));
        gc.fillText(text,x,y);
        gc.strokeText(text,x,y);
        x=347;
        y=240;
        width=289;
        height=62;
        gc.setStroke(Color.rgb(102,53,204));
        gc.setLineWidth(4);
        gc.strokeRoundRect(x,y,width,height,20,20);
        gc.setFill(Color.rgb(102,53,204,.20));
        if(commandNum==0){
            gc.setFill(Color.rgb(102,53,204,.80));
        }
        gc.fillRoundRect(x,y,width,height,20,20);
        y+=74;
        gc.setStroke(Color.rgb(102,53,204));
        gc.setLineWidth(4);
        gc.strokeRoundRect(x,y,width,height,20,20);
        gc.setFill(Color.rgb(102,53,204,.20));
        if(commandNum==1){
            gc.setFill(Color.rgb(102,53,204,.80));
        }
        gc.fillRoundRect(x,y,width,height,20,20);
        y+=74;
        gc.setStroke(Color.rgb(102,53,204));
        gc.setLineWidth(4);
        gc.strokeRoundRect(x,y,width,height,20,20);
        gc.setFill(Color.rgb(102,53,204,.20));
        if(commandNum==2){
            gc.setFill(Color.rgb(102,53,204,.80));
        }
        gc.fillRoundRect(x,y,width,height,20,20);
        y+=74;
        gc.setStroke(Color.rgb(102,53,204));
        gc.setLineWidth(4);
        gc.strokeRoundRect(x,y,width,height,20,20);
        gc.setFill(Color.rgb(102,53,204,.20));
        if(commandNum==3){
            gc.setFill(Color.rgb(102,53,204,.80));
        }
        gc.fillRoundRect(x,y,width,height,20,20);


        gc.setStroke(Color.rgb(170,104,227));
        gc.setLineWidth(1);
        gc.setFill(Color.WHITE);

         text="Resume";
        int textY=246+20;
        int textX=getXForCenteredText(text);
        gc.fillText(text,textX,textY);
        gc.strokeText(text,textX,textY);
        textY+=77;
        text="New Game";
         textX=getXForCenteredText(text);
        gc.fillText(text,textX,textY);
        gc.strokeText(text,textX,textY);
        textY+=77;
        text="Option";
         textX=getXForCenteredText(text);
        gc.fillText(text,textX,textY);
        gc.strokeText(text,textX,textY);
        textY+=77;
        text="Exit";
         textX=getXForCenteredText(text);
        gc.fillText(text,textX,textY);
        gc.strokeText(text,textX,textY);
        textY+=77;
    }
    public int getXForCenteredText(String text) {
        Text textNode = new Text(text);
        textNode.setFont(gc.getFont());
        int length = (int)textNode.getBoundsInLocal().getWidth();
        int x = game.screenWidth/2 - length/2;
        return x;
    }
    public int getXForCenteredTextParticular(String text,int x,int width) {
        Text textNode = new Text(text);
        textNode.setFont(gc.getFont());
        int length = (int)textNode.getBoundsInLocal().getWidth();

        return  x+ (width/2 - length/2);

    }

    public void drawSubWindow(int x, int y, int width, int height){
        Color c = Color.rgb(0, 0, 0, .80);
        gc.setFill(c);
        gc.fillRoundRect(x,y,width,height,35,35);

        //c=Color.WHITE;
        // gc.setFill(c);
        //gc.setStroke(Color.WHITE);


        gc.setLineWidth(5); // Setting stroke width
        gc.setLineDashes(0); // Setting line dashes to 0 (solid line)
        gc.setLineCap(StrokeLineCap.ROUND); // Setting line cap to round
        gc.strokeRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
        gc.setFill(Color.rgb(255, 255, 255));
        gc.setFont(Font.font("Arial", 16));
    }

    public void drawSubWindow(int x, int y, int width, int height, Color color,Color strokeColor){
        gc.setFill(color);
        gc.fillRoundRect(x,y,width,height,35,35);

//        c = Color.BLACK;
        //gc.setFill(c);
        gc.setStroke(strokeColor);


        gc.setLineWidth(4); // Setting stroke width
        //gc.setLineDashes(5); // Setting line dashes to 0 (solid line)
        //gc.setLineCap(StrokeLineCap.ROUND); // Setting line cap to round
        gc.strokeRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }


}

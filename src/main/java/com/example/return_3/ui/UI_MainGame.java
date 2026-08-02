package com.example.return_3.ui;

import com.example.return_3.db.MyJDBC;
import com.example.return_3.entity.Entity;
import com.example.return_3.main.EventHandler;
import com.example.return_3.main.Game;
import com.example.return_3.main.UtilityTool;
import com.example.return_3.object.*;
import com.example.return_3.object.food.OBJ_Fish;
import com.example.return_3.shop.Shop;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.awt.*;
import java.io.InputStream;
import java.util.ArrayList;

public class UI_MainGame {
    Game game;
    Color cream, darkCream, darkDarkCream,lightCream;
    Entity entity;
    public String msgText="";

    GraphicsContext gc;
    Font titleFont, smallFont,mediumFont,largeFont,smallFontBold,mediumFontBold,largeFontBold;
    Image heartFull, starImage, energyImage, coinImage, playerImage,guideline1,guideline2;
    public boolean messageOn = false;

    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();

    public boolean gameFinished=false; // if game is finished then the message will be shown
    public String currentDialogue = ""; //for setting the dialogue
    public int commandNum = 0; // this is for showing our menu specific commands

    public int subState=0;
    public boolean isBuyState = false;

    public Entity heart;
    public int playerSlotCol=0;
    public int playerSlotRow=0;
    public int shopSlotCol=0;
    public int shopSlotRow=0;
    public int transitionCounter=0;

    public Entity npc;
    public Shop shop;
    public UI_MainGame(Game game) {
        this.game = game;
        this.gc = game.gc;

//        arial_40 = new Font("Arial",40);
//        arial_80B = new Font("Arial",80);

//NEW FONT
//    Font baseFont = Font.loadFont(getClass().getResourceAsStream("/font/FlyingBird-Yz9Ga.otf"), 12);
//    Font baseFont = Font.loadFont(getClass().getResourceAsStream("/font/ShantyHouseRegular-K7j4l.ttf"), 12);
//    Font baseFont = Font.loadFont(getClass().getResourceAsStream("/font/SuperPixel-m2L8j.ttf"), 12);
    Font baseFont = Font.loadFont(getClass().getResourceAsStream("/font/TechnoRaceItalic-eZRWe.otf"), 12);




// Create font objects with different sizes
//        Font baseFont = Font.loadFont(getClass().getResourceAsStream("/font/Teh And Kopi.ttf"), 12);
//        Font baseFont = Font.loadFont(getClass().getResourceAsStream("/font/neuropol x rg.otf"), 12);
//        Font baseFont = Font.loadFont(getClass().getResourceAsStream("/font/rexlia rg.otf"), 12);
//        Font baseFont = Font.loadFont(getClass().getResourceAsStream("/font/zekton rg.otf"), 12);
       // Font baseFont = Font.font("Dialog",FontWeight.BOLD,20);
        smallFont = Font.font(baseFont.getName(), FontWeight.NORMAL, FontPosture.REGULAR, 12); // Change size as needed
        mediumFont = Font.font(baseFont.getName(), FontWeight.NORMAL, FontPosture.REGULAR, 18); // Change size as needed
        largeFont = Font.font(baseFont.getName(), FontWeight.NORMAL, FontPosture.REGULAR, 24); // Change size as needed
        smallFontBold = Font.font(baseFont.getName(), FontWeight.BOLD, FontPosture.REGULAR, 12); // Change size as needed
        mediumFontBold = Font.font(baseFont.getName(), FontWeight.BOLD, FontPosture.REGULAR, 16); // Change size as needed
        largeFontBold = Font.font(baseFont.getName(), FontWeight.NORMAL, FontPosture.REGULAR, 24); // Change size as needed

//        titleFont= Font.loadFont(getClass().getResourceAsStream("/font/DapplegrimDemoRegular-ODop.otf"), 28);
//        Font baseTitleFont = Font.loadFont(getClass().getResourceAsStream("/font/LONDON PRESLEY.ttf"), 36);

//        titleFont= Font.font(baseTitleFont.getName(), FontWeight.BLACK, FontPosture.REGULAR, 30);
        titleFont= Font.loadFont(getClass().getResourceAsStream("/font/Teh And Kopi.ttf"), 34);
        UtilityTool uTool= new UtilityTool();
        heart = new OBJ_Heart(game);
        heartFull = heart.image1;
        guideline2 = uTool.loadImage("/objects/guideline_2.png",game.screenWidth,game.screenHeight);
        energyImage = uTool.loadImage("/objects/energy.png",game.tileSize-7,game.tileSize-7);
        starImage = uTool.loadImage("/objects/star1.png",game.tileSize+10,game.tileSize+10);
        guideline1 = uTool.loadImage("/objects/guideline_1.png",game.screenWidth,game.screenHeight);
        playerImage = uTool.loadImage("/player/boy_down_1.png", 2*game.tileSize, 2*game.tileSize);


        Entity coin= new OBJ_Coin(game);
        coinImage = coin.down1;
        lightCream= Color.rgb(241,224,203);
        cream= Color.rgb(246,202,156);
        darkCream= Color.rgb(172,128,83);
        darkDarkCream= Color.rgb(97,70,42);
    }
    public void drawPlayerLife() {
        int x = (game.screenWidth / 2) - game.tileSize * 3;
        double y = game.tileSize / 1.5;
        int tempY = 25;
        // Fill yellow bar
        gc.setFill(Color.rgb(255, 209, 184));
        //gc.fillRect(x, y, lifeWidth, game.tileSize);
        gc.fillRoundRect(x, tempY, game.tileSize * 6, game.tileSize / 2, 10, 10);

        // Dark yellow outline
        gc.setStroke(Color.rgb(26, 3, 5)); // Dark yellow color
        gc.setLineWidth(2); // Width of the outline

        gc.strokeRoundRect(x, tempY, game.tileSize * 6, game.tileSize / 2, 10, 10); // Outline of the bar

        // Calculate width of yellow bar based on energy and maxEnergy
        double lifeWidth = ((double) game.player.life / game.player.maxLife) * game.tileSize * 6;

        // Fill yellow bar
        gc.setFill(Color.rgb(238, 26, 49));
        //gc.fillRect(x, y, lifeWidth, game.tileSize);
        gc.fillRoundRect(x, tempY, lifeWidth, game.tileSize / 2, 10, 10);

        // Set font for text
        gc.setFont(Font.getDefault());

        gc.drawImage(heartFull, x - (game.tileSize - 8), y - 5);
    }


    public void drawEnergy(){
        int x = game.screenWidth - game.tileSize * 3;
        double y = game.tileSize / 1.5;
        int tempY=25;
        // Dark yellow outline
        gc.setStroke(Color.rgb(129, 61, 57)); // Dark yellow color
        gc.setLineWidth(2); // Width of the outline
        gc.strokeRoundRect(x, tempY, game.tileSize * 2.5, game.tileSize / 2,10,10); // Outline of the bar
        // Fill yellow bar
        gc.setFill(Color.rgb(255,209,184));
        //gc.fillRect(x, y, lifeWidth, game.tileSize);
        gc.fillRoundRect(x, tempY, game.tileSize * 2.5, game.tileSize/2,10,10);


        // Calculate width of yellow bar based on energy and maxEnergy
        double energyWidth = ((double) game.player.energy / game.player.maxEnergy) * game.tileSize * 2;

        // Fill yellow bar
        gc.setFill(Color.rgb(252, 190, 69));
        gc.fillRoundRect(x, tempY, energyWidth, game.tileSize / 2,10,10);

        // Set font for text
        gc.setFont(smallFontBold);

        // Draw "Energy" text
        gc.setFill(darkDarkCream);
        String text = "" + game.player.energy + "/" + game.player.maxEnergy;

        // Create a temporary Text node to measure the width
        Text textNode = new Text(text);
        textNode.setFont(gc.getFont());
        double textWidth = textNode.getBoundsInLocal().getWidth();

        // Draw text at adjusted x position
        gc.fillText(text, x+12, tempY+12);
        gc.drawImage(energyImage,x-15,y);
    }


    public void drawCoin(){

        int x = game.screenWidth - game.tileSize * 3;
        double y = game.tileSize / 1.5;
        double tempY = (game.tileSize / 1.5) + 5;

//        x+=game.tileSize-10;
        // Dark yellow outline
        gc.setStroke(Color.rgb(129, 61, 57)); // Dark yellow color
        gc.setLineWidth(2); // Width of the outline
        gc.strokeRoundRect(x, tempY, game.tileSize * 2.5, game.tileSize / 2,10,10); // Outline of the bar

        // Fill yellow bar
        gc.setFill(Color.rgb(255,209,184));
//        gc.setFill(Color.rgb(252, 190, 69));
        gc.fillRoundRect(x, tempY, game.tileSize * 2.5, game.tileSize / 2,10,10);

        // Set font for text
        gc.setFont(smallFontBold);

        // Draw "Energy" text
        gc.setFill(Color.rgb(125,89,9));
        String text = "" + game.player.coin;

        // Draw text at adjusted x position
        gc.fillText(text, x +15, y+17);
        gc.drawImage(coinImage,x-15,y);

    }


    public void drawPlayerLevel() {
        int x =game.tileSize/2;
        int y = 10;
        int tempX=x+30;
        int tempY=25;

        gc.setStroke(Color.rgb(129, 61, 57)); // Dark yellow color
        gc.setLineWidth(2); // Width of the outline
        gc.strokeRoundRect(tempX, tempY, game.tileSize * 3, game.tileSize / 2,10,10); // Outline of the bar

        // Fill yellow bar
        gc.setFill(Color.rgb(255,209,184));
        //gc.fillRect(x, y, lifeWidth, game.tileSize);
        gc.fillRoundRect(tempX, tempY, game.tileSize * 3, game.tileSize/2,10,10);
        // Dark yellow outline


        // Calculate width of yellow bar based on energy and maxEnergy
        double expWidth = ((double) game.player.exp / game.player.nextLevelExp) * game.tileSize * 3;

        // Fill yellow bar
        gc.setFill(Color.rgb(252, 190, 69));

        gc.fillRoundRect(tempX, tempY, expWidth, game.tileSize / 2,10,10);

        gc.drawImage(starImage,x,y);

        // Draw level number
        int level = game.player.level;
        gc.setFill(Color.BLACK);
        gc.setFont(mediumFontBold); // Customize font as needed
        if (game.player.level < 10) {
            gc.fillText("0" + game.player.level, 13 + x, tempY+12); // Adjust position as needed
        }else{
            gc.fillText("" + game.player.level, 13 + x, tempY+12); // Adjust position as needed
        }

        //Draw Player EXP
        String expText= game.player.exp + "/" + game.player.nextLevelExp;
        gc.setFont(smallFontBold); // Cusomize font as needed

        // Draw exp text at adjusted x position
        // gc.fillText(expText, tempX+10, tempY+5 );
        gc.fillText(expText, x+(game.tileSize+10), tempY+12);

    }


    public void hospitalScreen() {
        // Create a Frame...
        final int frameX = game.tileSize * 5;
        final int frameY = game.tileSize * 2;
        final int frameWidth = game.tileSize * 20;
        final int frameHeight = game.tileSize * 14;

        Color c = Color.rgb(255, 209, 184);
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        gc.setFont(titleFont);
        String text = "Welcome To Hospital";
        int textX = getXForCenteredTextInFrame(text,frameX,frameWidth);
        int textY = frameY + game.tileSize + 16;

        gc.setFill(Color.rgb(255, 255, 255));
        gc.fillText(text, textX, textY);

        // Menu...
        gc.setFill(Color.rgb(255, 255, 255));
        gc.setFont(largeFontBold);

        int calCoin = (game.player.maxLife - game.player.life) * 2;
        text = "You need " + calCoin + " coin to heal yourself.";
        textX = game.tileSize * 8;
        textY = game.tileSize * 6;
        gc.fillText(text, textX, textY);
        if (commandNum == 0) {
            gc.fillText("-->", textX-game.tileSize, textY);
        }

        text = "You need 300 coin to increase 10% of life.";
        textX = game.tileSize * 8;
        textY = game.tileSize * 8;
        gc.fillText(text, textX, textY);
        if (commandNum == 1) {
            gc.fillText("-->", textX-game.tileSize, textY);
        }


        text = "Back";
        textX = game.tileSize * 8;
        textY = game.tileSize * 13;
        gc.fillText(text, textX, textY);
        if (commandNum == 2) {
            gc.fillText("-->", textX-game.tileSize, textY);
        }
    }

    public void drawSettingsScreen() {
        // Create a Frame...
        final int frameX = game.tileSize * 5;
        final int frameY = game.tileSize * 2;
        final int frameWidth = game.tileSize * 20;
        final int frameHeight = game.tileSize * 14;

        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        String text = "Settings";
        gc.setFont(titleFont);
        int textX = getXForCenteredText(text);
        int textY = frameY + game.tileSize + 16;

        gc.setFill(Color.rgb(255, 255, 255));
        gc.fillText(text, textX, textY);


        gc.setFill(Color.rgb(255, 255, 255));
        gc.setFont(largeFontBold);
        text = "BG Music";
        textX = game.tileSize * 9;
        textY = game.tileSize * 6;
        gc.fillText(text, textX, textY);
        if (commandNum == 0) {
            gc.fillText("-->", textX-game.tileSize-8, textY);
        }

        text = "Sound Effect";
        textX = game.tileSize * 9;
        textY = game.tileSize * 8;
        gc.fillText(text, textX, textY);
        if (commandNum == 1) {
            gc.fillText("-->", textX-game.tileSize-8, textY);
        }

        text = "Back";
        textX = game.tileSize * 9;
        textY = game.tileSize * 13;
        gc.fillText(text, textX, textY);
        if (commandNum == 2) {
            gc.fillText("-->", textX-game.tileSize-8, textY);
        }

        textX = game.tileSize * 17;
        textY = game.tileSize * 6 - 24;
        //gc.fillRect(textX, textY, 120, 24);
        gc.setStroke(Color.WHITE);
        gc.strokeRect(textX, textY, 150, 24); // 150/5 = 30...
        int volumeWidth = 30 * game.music.volumeScale;
        gc.fillRect(textX, textY, volumeWidth, 24);

        textX = game.tileSize * 17;
        textY = game.tileSize * 8 - 24;
        //gc.fillRect(textX, textY, 120, 24);
        gc.setStroke(Color.WHITE);
        gc.strokeRect(textX, textY, 150, 24);
        volumeWidth = 30 * game.soundEffect.volumeScale;
        gc.fillRect(textX, textY, volumeWidth, 24);


    }

    public void guidelineScreen(){
        if(commandNum==0){
        gc.drawImage(guideline1,0,0);
        }if(commandNum==1){
        gc.drawImage(guideline2,0,0);
        }
    }



    public void menuBarScreen() {
        // Create a Frame...
        final int frameX = game.tileSize * 5;
        final int frameY = game.tileSize * 2;
        final int frameWidth = game.tileSize * 20;
        final int frameHeight = game.tileSize * 14;

        Color c = Color.rgb(255, 209, 184);
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        gc.setFont(titleFont);
        String text = "Monster MunchKins";
        int textX = getXForCenteredText(text);
        int textY = frameY + game.tileSize *2;

        gc.setFill(Color.rgb(255, 255, 255));
        gc.fillText(text, textX, textY);

        // Menu...
        gc.setFill(Color.rgb(255, 255, 255));
        gc.setFont(largeFontBold);

        text = "Settings";
        textX = getXForCenteredText(text);
        textY = game.tileSize * 6;
        gc.fillText(text, textX, textY);
        if (commandNum == 0) {
            gc.fillText("-->", textX-game.tileSize-8, textY);
        }

        text = "Exit Game";
        textX = getXForCenteredText(text);
        textY = game.tileSize * 8;
        gc.fillText(text, textX, textY);
        if (commandNum == 1) {
            gc.fillText("-->", textX-game.tileSize-8, textY);
        }

        text = "Logout";
        textX = getXForCenteredText(text);
        textY = game.tileSize * 12;
        gc.fillText(text, textX, textY);
        if (commandNum == 2) {
            gc.fillText("-->", textX-game.tileSize-8, textY);
        }
    }
    public void pauseScreen() {
        // Create a Frame...
        final int frameX = game.tileSize * 5;
        final int frameY = game.tileSize * 2;
        final int frameWidth = game.tileSize * 20;
        final int frameHeight = game.tileSize * 14;

        Color c = Color.rgb(255, 209, 184);
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        gc.setFont(titleFont);
        String text = "Monster MunchKins";
        int textX = getXForCenteredText(text);
        int textY = frameY + game.tileSize *2;

        gc.setFill(Color.rgb(255, 255, 255));
        gc.fillText(text, textX, textY);

        // Menu...
        gc.setFill(Color.rgb(255, 255, 255));
        gc.setFont(largeFontBold);

        text = "Press 'ENTER' to play";
        textX = getXForCenteredText(text);
        textY = game.tileSize * 9;
        gc.fillText(text, textX, textY);
        gc.fillText("-->", textX-game.tileSize-8, textY);

    }
    public void levelUpScreen() {
        // Create a Frame...
        final int frameX = game.tileSize * 5;
        final int frameY = game.tileSize * 2;
        final int frameWidth = game.tileSize * 20;
        final int frameHeight = game.tileSize * 14;

        Color c = Color.rgb(255, 209, 184);
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        gc.setFont(titleFont);
        String text = "Monster MunchKins";
        int textX = getXForCenteredText(text);
        int textY = frameY + game.tileSize *2;

        gc.setFill(Color.rgb(255, 255, 255));
        gc.fillText(text, textX, textY);

        // Menu...
        gc.setFill(Color.rgb(255, 255, 255));
        gc.setFont(largeFontBold);

        if (game.player.level == 3) {
            text = " Congratulations! You've reached Level " + game.player.level + ".\nKeep pushing forward, brave hunter.\n\nItem unlocked: Special Sword.\nHint: Find Stuff Parts shop";
        }
        if (game.player.level == 5) {
            text = " Congratulations! You've reached Level " + game.player.level + ".\nKeep pushing forward, brave hunter.\n\nItem unlocked: Fireball, Axe, Shield.\nHint: Find Stuff Parts shop";
        }
        if (game.player.level == 10) {
            text = " Congratulations! You've reached Level " + game.player.level + ".\nKeep pushing forward, brave hunter.\n\nItem unlocked: Ice Sword, Tomahawk.\nHint: Find Stuff Parts shop";
        }
        if (game.player.level == 12) {
            text = " Congratulations! You've reached Level " + game.player.level + ".\nKeep pushing forward, brave hunter.\n\nItem unlocked: Fire Sword.\nHint: Find Stuff Parts shop";
        }
        if (game.player.level != 3 || game.player.level != 5 || game.player.level !=10 || game.player.level != 12) {
            text = " Congratulations! You've reached Level " + game.player.level + ".\nKeep pushing forward, brave hunter.";
        }

        textX = getXForCenteredText(text);
        textY = game.tileSize * 7;
        gc.fillText(text, textX, textY);
        gc.fillText("", textX-game.tileSize-8, textY);
    }
    public void gameOverScreen() {
        // Create a Frame...
        final int frameX = game.tileSize * 5;
        final int frameY = game.tileSize * 2;
        final int frameWidth = game.tileSize * 20;
        final int frameHeight = game.tileSize * 14;

        Color c = Color.rgb(255, 209, 184);
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        gc.setFont(titleFont);
        String text = "Monster MunchKins";
        int textX = getXForCenteredText(text);
        int textY = frameY + game.tileSize *2;

        gc.setFill(Color.rgb(255, 255, 255));
        gc.fillText(text, textX, textY);

        // Menu...
        gc.setFill(Color.rgb(255, 255, 255));
        gc.setFont(largeFontBold);

        text = "Congratulations, brave hunter! You have successfully\neliminated all the monsters on the island. The villagers\n are safe once more, thanks to your efforts.\n\n Press 'ENTER' to play NEW GAME.\nPress 'ESCAPE' to EXIT the game.";
        textX = game.tileSize * 6;
        textY = game.tileSize * 6;
        gc.fillText(text, textX, textY);
        gc.fillText("", textX-game.tileSize-8, textY);
    }

    public void drawCharacterScreen() {
        // Create a Frame...
        final int frameX = game.tileSize * 2;
        final int frameY = game.tileSize * 3;
        final int frameWidth = game.tileSize * 6;
        final int frameHeight = (game.tileSize * 12) + (game.tileSize/2);

        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // Text....
        gc.setFill(Color.rgb(255, 255, 255));
        gc.setFont(mediumFontBold);

        int textX = frameX + game.tileSize/2;
        int textY = frameY + game.tileSize+8;
        final int lineGap = 32;

        // NAMES....
        gc.fillText("Level: ", textX, textY);
        textY += lineGap;
        gc.fillText("Life: ", textX, textY);
        textY += lineGap;
        gc.fillText("Strength: ", textX, textY);
        textY += lineGap;
        gc.fillText("Dexterity: ", textX, textY);
        textY += lineGap;
        gc.fillText("Attack: ", textX, textY);
        textY += lineGap;
        gc.fillText("Defence: ", textX, textY);
        textY += lineGap;
        gc.fillText("EXP: ", textX, textY);
        textY += lineGap;
        gc.fillText("Energy: ", textX, textY);
        textY += lineGap;
        gc.fillText("Coin: ", textX, textY);
        textY += lineGap + 8;
        gc.fillText("Weapon: ", textX, textY);
        textY += lineGap + 8;
        gc.fillText("Shield: ", textX, textY);

        // VALUES....
        int tailX = (frameX + frameWidth) - 18;
        // RESET textY...
        textY = frameY + game.tileSize+8;
        String value;

        value = String.valueOf(game.player.level);
        textX = getXForAlignToRightText(value, tailX);
        gc.fillText(value, textX, textY);
        textY += lineGap;

        value = String.valueOf(game.player.life);
        textX = getXForAlignToRightText(value, tailX);
        gc.fillText(value, textX, textY);
        textY += lineGap;

        value = String.valueOf(game.player.strength);
        textX = getXForAlignToRightText(value, tailX);
        gc.fillText(value, textX, textY);
        textY += lineGap;

        value = String.valueOf(game.player.dexterity);
        textX = getXForAlignToRightText(value, tailX);
        gc.fillText(value, textX, textY);
        textY += lineGap;

        value = String.valueOf(game.player.attack);
        textX = getXForAlignToRightText(value, tailX);
        gc.fillText(value, textX, textY);
        textY += lineGap;

        value = String.valueOf(game.player.defense);
        textX = getXForAlignToRightText(value, tailX);
        gc.fillText(value, textX, textY);
        textY += lineGap;

        value = String.valueOf(game.player.exp);
        textX = getXForAlignToRightText(value, tailX);
        gc.fillText(value, textX, textY);
        textY += lineGap;

        value = String.valueOf(game.player.energy);
        textX = getXForAlignToRightText(value, tailX);
        gc.fillText(value, textX, textY);
        textY += lineGap;

        value = String.valueOf(game.player.coin);
        textX = getXForAlignToRightText(value, tailX);
        gc.fillText(value, textX, textY);
        textY += lineGap - 16;
        //In the initaial stage player will have no weapon so
        //when he do not have weapon and sheild then there will draw nothing
        if(game.player.currentWeapon!=null){
        gc.drawImage(game.player.currentWeapon.down1, tailX-game.tileSize, textY);
        } //we can draw something instead of nothing [for weapon]
        textY += lineGap + 8;
        if(game.player.currentShield!=null) {
            gc.drawImage(game.player.currentShield.down1, tailX - game.tileSize, textY);
        }//we can draw something instead of nothing [for weapon]
    }

    public void drawInventory(Entity entity, boolean cursor,int tileSizeNum){
        //Initial value
        int frameX=0;
        int frameY=0;
        int frameWidth=0;
        int frameHeight=0;
        int slotCol=0;
        int slotRow=0;

        if(entity==game.player){
            //FRAME
            frameX=game.tileSize*tileSizeNum;
            frameY=game.tileSize*4;
            frameWidth=game.tileSize*7;
            frameHeight=game.tileSize*6;
            slotCol=playerSlotCol;
            slotRow=playerSlotRow;
        }else{
            //FRAME
            frameX=game.tileSize*16;
            frameY=game.tileSize*4;
            frameWidth=game.tileSize*7;
            frameHeight=game.tileSize*6;
            slotCol=shopSlotCol;
            slotRow=shopSlotRow;
        }
        //set variable for making dynamic two inventory and different column ;
//        int xPositon=21;
//        int r1=4,r2=9,r3=14;

        //Draw the frame
        drawSubWindow(frameX, frameY, frameWidth, frameHeight,cream,darkCream);
        if(entity==game.player){
            drawPlayerPropertiesBox(frameX-(game.tileSize*4),frameY,115,frameHeight);
        }else{
            //Title
            gc.setFont(largeFontBold);
            String titleText=shop.name;
            double titlewidth= getWidthOfText(titleText);
            gc.setFill(darkDarkCream);
            gc.fillText(titleText,frameX+((frameWidth-titlewidth)/2),frameY+24);
        }

        //SLOT
        final int slotXStart=frameX+21;
        final int slotYStart=frameY+36;
        int slotX=slotXStart;
        int slotY=slotYStart;
        int slotSize=game.tileSize+3;


        //Draw Players new Items
        for(int i=0; i<entity.inventory.size(); i++){

            // Text....

            //EQUIP CURSOR
            if(entity.inventory.get(i)==entity.currentWeapon||entity.inventory.get(i)==entity.currentShield){
                gc.setFill(Color.rgb(112, 224, 0));
//                gc.setFill(Color.rgb(171, 255, 79));
                gc.fillRoundRect(slotX,slotY,game.tileSize,game.tileSize,10,10);
            }
            gc.drawImage(entity.inventory.get(i).down1, slotX, slotY);
            slotX+=slotSize+1;
            if(i==4 ||i==9||i==14){
                slotY+=slotSize+2;
                //reset SlotX
                slotX=slotXStart;
            }
        }
        //CURSOR
        if(cursor==true){
            int cursorX=slotXStart+(slotSize*slotCol);
            int cursorY=slotYStart+(slotSize*slotRow);
            int cursorWidth=game.tileSize;
            int cursorHeight=game.tileSize;
            //Draw cursor
            gc.setStroke(darkCream);
            //gc.setFont(Font.font("Arial", 16));
           // gc.setStroke(new BasicStroke(3));
            //gc.setLineWidth(3);
            //gc.setStroke(Color.rgb(26, 3, 5)); // Dark yellow color
            gc.setLineWidth(3); // Width of the outline
            gc.strokeRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10); // Stroke the round rectangle

            //Another subwindow to show the description
            //DESCRIPTION FRAME
            int dFrameX= frameX;
            int dFrameY= frameY+ frameHeight+8;
            int dFrameWidth= frameWidth;
            int dFrameHeight= game.tileSize*5;
            //DRAW DESCRIPTION TEXT
            int textX=dFrameX+20;
            int textY=dFrameY+(2*game.tileSize);

           // gc.setFill(Color.rgb(255, 255, 255));
            //gc.setFont(Font.font("Arial", 16));
            //this is for setting description TEXT
            int itemIndex=getItemIndexOnSlot(slotCol,slotRow);
            if(itemIndex<entity.inventory.size()) {
                drawSubWindow(dFrameX,dFrameY,dFrameWidth,dFrameHeight,cream,darkCream);
                int price= entity.inventory.get(itemIndex).price;
//                System.out.println("Price: " + price);
                int x=dFrameX+16;
                int y=dFrameY+game.tileSize/2;
                gc.setFont(mediumFont);
                gc.setFill(darkDarkCream);
                gc.fillText("->Description<-",x,y+14);
                x=dFrameX+game.tileSize*5;
                if(isBuyState){
                    drawCoinBox(coinImage,x-10,y,price);
                }
                else{
                    drawCoinBox(coinImage,x-10,y,(int)(price*0.5));
                }
                gc.setFont(mediumFontBold);
                for(String line : entity.inventory.get(itemIndex).description.split("\n")) {
                    gc.setFill(darkDarkCream);
                    gc.fillText(line, textX,textY);
                    textY+=32;
                }
            }
        }

    }
    public void drawPlayerPropertiesBox(int frameX,int frameY,int frameWidth,int frameHeight) {
        drawSubWindow(frameX, frameY, frameWidth, frameHeight,cream,darkCream);
        gc.setFill(darkDarkCream);
        gc.setFont(mediumFont);
        String userName=game.user.getUsername();
        int userNameX=getXForCenteredTextInFrame(userName,frameX,frameWidth);
        gc.fillText(userName,userNameX,frameY+75);
        gc.drawImage(playerImage,frameX+20,frameY+100);
        //in here we are drawing player coin box, player coin
        drawCoinBox(coinImage,frameX+25,frameY+20,game.player.coin);

    }
    //THis method is used for showing coin box and value
    public void drawCoinBox(Image image,int x, int y,int price){
        gc.setFill(darkCream);
        int width=58;
        int height=20;
        int arc=20;
        gc.fillRoundRect(x,y,width,height,arc,arc);
        gc.setFont(mediumFontBold);
        gc.setFill(Color.WHITE);
        String text=""+price;
        int priceX=getXForAlignToRightText(text,x+width-8);
        int priceY=y+height-4;
        gc.fillText(text,priceX,priceY);

        gc.drawImage(image,x-16,y-4);
    }




    public void drawTradeScreen(){
        isBuyState = false;
        switch (subState){
            case 0: select();break;
            case 1: buy();break;
            case 2: sell();break;
        }
        game.keyHandler.setEnterPressed(false);
    }

    public void select(){

        //Draw Window
        int x=game.tileSize*8;
        int y=game.tileSize*4;
        int width=game.tileSize*14;
        int height=(game.tileSize*8);
        drawSubWindow(x, y, width, height,cream,darkCream);
        // Text....
        gc.setFill(darkDarkCream);
       // pixelSport=Font.font(pixelSport.getFamily(),FontWeight.LIGHT,30);
        gc.setFont(largeFontBold);

        //DRAW TEXT

        y+= game.tileSize;
        String text=shop.name;
        int tempX= getXForCenteredTextInFrame(text,x,width);
        gc.fillText(shop.name,tempX,y);
        x+= game.tileSize*2;
        y+=game.tileSize*2;
        gc.fillText("Buy",x,y);
        if(commandNum==0){
            gc.fillText(">",x-24,y);
            if(game.keyHandler.isEnterPressed()==true){
                game.playSoundEffect(game.soundEffect.popUp);
                subState=1;
            }
        }
        y+= game.tileSize;
        gc.fillText("Sell",x,y);
        if(commandNum==1){
            gc.fillText(">",x-24,y);
            if(game.keyHandler.isEnterPressed()==true){
                game.playSoundEffect(game.soundEffect.popUp);
                subState=2;
            }
        }

        y+= game.tileSize;
        gc.fillText("Leave",x,y);
        if(commandNum==2){
            gc.fillText(">",x-24,y);
            if(game.keyHandler.isEnterPressed()==true){
                commandNum=0;
                //game.playSoundEffect(game.soundEffect.inventoryClose);
                game.gameState=game.playState;

            }
        }
    }
    public void buy(){
        isBuyState = true;
//        //Draw player Inventory
        drawInventory(game.player,false,6);

//        //Draw ShopC inventory
        drawInventory(shop,true,6);

//        //DRAW HINT WINDOWS
        int x= game.tileSize*11;
        int y=game.tileSize*2;
        int width=game.tileSize*8;
        int height=game.tileSize*2;
        drawBackHint2("Press [ESC] key to back",x,y,width,height);
//        drawSubWindow(x, y-5, width, height,cream,darkCream);
//        //drawing text
//        gc.setFill(darkDarkCream);
//        gc.setFont(mediumFontBold);
//        y+=game.tileSize;
//        gc.fillText("Press [ESC] key to back",x+15,y);
        //DRAW PRICE WINDOWS
        int itemIndex = getItemIndexOnSlot(shopSlotCol,shopSlotRow);
        if(itemIndex<shop.inventory.size()){



            //IF BUY an ITEM





            if(game.keyHandler.isEnterPressed()==true){
                boolean check=checkBuy(shop.inventory.get(itemIndex));
                if(check){
                    if(shop.inventory.get(itemIndex).price>game.player.coin){
                        subState=0;
                        game.playSoundEffect(game.soundEffect.popUp);
                        game.gameState=game.messageState;
                        currentDialogue="You need more coin to buy that";
                        drawDialogueScreen();
                    }
                    else {
                        int exp=shop.inventory.get(itemIndex).exp;
                        game.player.coin-=shop.inventory.get(itemIndex).price;
                        game.player.exp+=exp;
                        game.player.checkLevelUp();
                        addMessage("Exp gained: "+exp);
                        game.playSoundEffect(game.soundEffect.buy);
                        Entity entity= UtilityTool.getInventoryItem(shop.inventory.get(itemIndex).itemCode);
                        game.player.inventory.add(entity);
                        MyJDBC.addItemToInventory(game.player.playerId,shop.inventory.get(itemIndex).itemCode);
                    }
                    if (game.player.inventory.size()==game.player.maxInventorySize) {
                        subState=0;
                        game.playSoundEffect(game.soundEffect.popUp);
                        game.gameState=game.messageState;
                        currentDialogue="You can not carry any more items";
                        drawDialogueScreen();
                    }
                }
                else{
                    subState=0;
                    commandNum=0;
                    game.playSoundEffect(game.soundEffect.popUp);
                    game.gameState=game.messageState;
                    Entity item =shop.inventory.get(itemIndex);
                    if(item.type==game.specialSword.type){
                        currentDialogue="You need to level 3";
                    }
                    else if(item.type==game.fireBall.type || item.type==game.axe.type || item.type==game.shieldWood.type){
                        currentDialogue="You need to level 5";
                    }
                    else if(item.type==game.iceSword.type || item.type==game.tomahawkAxe.type) {
                        currentDialogue="You need to level 10";
                    }
                    else if(item.type==game.fireSword.type){
                        currentDialogue="You need to level 12";
                    }
                    else{
//                            return true;
                        currentDialogue="You need to more level";
                    }

                    drawDialogueScreen();
                }
            }






//            if(game.keyHandler.isEnterPressed()==true){
//                if(shop.inventory.get(itemIndex).price>game.player.coin){
//                    subState=0;
//                    game.playSoundEffect(game.soundEffect.popUp);
//                    game.gameState=game.messageState;
//                    currentDialogue="You need more coin to buy that";
//                    drawDialogueScreen();
//                }
//                else if (game.player.inventory.size()==game.player.maxInventorySize) {
//                    subState=0;
//                    game.playSoundEffect(game.soundEffect.popUp);
//                    game.gameState=game.messageState;
//                    currentDialogue="You can not carry any more items";
//                    drawDialogueScreen();
//                }
//                else{
//                    boolean check=checkBuy(shop.inventory.get(itemIndex));
//                    if(check){
//                        int exp=shop.inventory.get(itemIndex).exp;
//                        game.player.coin-=shop.inventory.get(itemIndex).price;
//                        game.player.exp+=exp;
//                        game.player.checkLevelUp();
//                        addMessage("Exp gained: "+exp);
//                        Entity entity= UtilityTool.getInventoryItem(shop.inventory.get(itemIndex).itemCode);
//                        game.player.inventory.add(entity);
//                        MyJDBC.addItemToInventory(game.player.playerId,shop.inventory.get(itemIndex).itemCode);
//
//                    }
//                    else{
//                        subState=0;
//                        commandNum=0;
//                        game.playSoundEffect(game.soundEffect.popUp);
//                        game.gameState=game.messageState;
//                        Entity item =shop.inventory.get(itemIndex);
//                        if(item.type==game.specialSword.type){
//                            currentDialogue="You need to level 3";
//                        }
//                        else if(item.type==game.fireBall.type || item.type==game.axe.type || item.type==game.shieldWood.type){
//                            currentDialogue="You need to level 5";
//                        }
//                        else if(item.type==game.iceSword.type || item.type==game.tomahawkAxe.type) {
//                            currentDialogue="You need to level 10";
//                        }
//                        else if(item.type==game.fireSword.type){
//                            currentDialogue="You need to level 12";
//                        }
//                        else{
////                            return true;
//                            currentDialogue="You need to more level";
//                        }
//
//                        drawDialogueScreen();
//                    }
//
//
//
//
//                }
//            }
        }
    }

    public void drawBackHint(String text,int x,int y,int width, int height){
        drawSubWindow(x, y-5, width, height,cream,darkCream);
        //drawing text
        gc.setFill(darkDarkCream);
        gc.setFont(mediumFontBold);
        int tempX= getXForCenteredTextInFrame(text,x,width);
        y+=game.tileSize;
        gc.fillText(text,tempX,y);
    }
    public void drawBackHint2(String text,int x,int y,int width, int height){
        drawSubWindow(x, y-5, width, height,darkCream,cream);
        //drawing text
        gc.setFill(Color.WHITE);
        gc.setFont(mediumFontBold);
        int tempX= getXForCenteredTextInFrame(text,x,width);
        y+=game.tileSize;
        gc.fillText(text,tempX,y);
    }


    public boolean checkBuy(Entity item){
        if(item.type==game.specialSword.type){
            return game.player.level >= 3;
        }
        else if(item.type==game.fireBall.type || item.type==game.axe.type || item.type==game.shieldWood.type){
            return game.player.level >= 5;
        }
        else if(item.type==game.iceSword.type || item.type==game.tomahawkAxe.type) {
            return game.player.level >= 10;
        }
        else if(item.type==game.fireSword.type){
            return game.player.level >= 12;
        }
        else{
            return true;
        }

    }
    public void sell(){
//        //Draw player Inventory
        drawInventory(game.player,true,13);
//        //Draw NPC inventory
      //  drawInventory(npc,true);

        //DRAW HINT WINDOWS
//        int x= game.tileSize*2;
//        int y=game.tileSize*9;
//        int width=game.tileSize*6;
//        int height=game.tileSize*2;
        int x= game.tileSize*10;
        int y=game.tileSize*2;
        int width=game.tileSize*9;
        int height=game.tileSize*2;
        drawBackHint2("Press [ESC] key to back",x,y,width,height);


//        DRAW PLAYER COIN WINDOWs
//        x= game.tileSize*12;
//        y=game.tileSize*9;
//        width=game.tileSize*6;
//        height=game.tileSize*2;
//        drawSubWindow(x, y, width, height);
//        gc.setFont(Font.font("Arial", 16));
//        gc.fillText("Your Coin: "+game.player.coin,x+24,y+60);


        //DRAW PRICE WINDOWS
        int itemIndex = getItemIndexOnSlot(playerSlotCol,playerSlotRow);
        if(itemIndex<game.player.inventory.size()){
//            x=(int)(game.tileSize*15.5);
//            y=(int)(game.tileSize*5.5);
//            width=(int)(game.tileSize*2.5);
//            height=game.tileSize;
//            drawSubWindow(x,y,width,height,cream,darkCream);
            //draw
           // gc.drawImage(coinImage,x+10,y+10,25,25);

            int price= game.player.inventory.get(itemIndex).price;
//            drawCoinBox(coinImage,x+10,y+10,price);

//            String text=""+price;
//            x=getXForAlignToRightText(text,game.tileSize*8-20);
//            gc.fillText(text,x,y+20);

            //IF Sell an ITEM
            if(game.keyHandler.isEnterPressed()==true){
                if(game.player.inventory.get(itemIndex)==game.player.currentWeapon||game.player.inventory.get(itemIndex)==game.player.currentShield){
                    commandNum=0;
                    subState=0;
                    game.playSoundEffect(game.soundEffect.popUp);
                    game.gameState=game.messageState;
                    currentDialogue="you can not sell an equip items";
                }else{
                    game.playSoundEffect(game.soundEffect.sell);
                    int exp=(int) ((game.player.inventory.get(itemIndex).exp)*0.5);
                    game.player.exp += exp;
                    game.ui.uiMainGame.addMessage("Exp gained: "+exp);
                    game.player.checkLevelUp();
                    MyJDBC.removeItemFromInventory(game.player.playerId,game.player.inventory.get(itemIndex).itemCode);
                    game.player.inventory.remove(itemIndex);
                    game.player.coin += (int) (price * 0.5);
                }


            }
        }
    }
    public void globalChatScreen(){
        int x = 64;
        int y = 48;
        int width = game.tileSize*26;
        int height = game.tileSize * 15;
        drawSubWindow(x, y, width, height,cream,darkCream);
        gc.setFill(darkDarkCream);
        x += game.tileSize;
        y += game.tileSize;
        gc.setFont(largeFont);
        String text="Global Chat";
        int tempX=getXForCenteredText(text);
        gc.fillText(text,tempX,y);
        gc.setFont(mediumFontBold);

        int messageY = y + game.tileSize; // Initial Y position for the messages
        int i=0;
        for (String msg : game.chatMessages) {
            int loopX=x+16;
            // Split the message into words
            String[] words = msg.split(" ", 2);
            String name = words[0];
            String messageContent ;
            if(words.length>1){
                messageContent=words[1];
                int textWidth= (int)getWidthOfText(msg)+22;
                // Alternate background colors for messages
                if (name.equals(game.user.getUsername())) {
                    gc.setFill(darkCream);
                    gc.fillRoundRect(loopX - 12, messageY - 20, textWidth, game.tileSize, 20, 20);
                    gc.setFill(lightCream);

                } else {
                    gc.setFill(lightCream);
                    gc.fillRoundRect(loopX - 16, messageY - 20, textWidth, game.tileSize, 20, 20);
                    gc.setFill(darkDarkCream);
                }

                // Display the name and message content
                gc.fillText(name + ": " + messageContent, loopX, messageY);
                messageY += game.tileSize; // Move to the next line for the next message
                i++;
            }
//            = words.length > 1 ? words[1] : "";

            // Set the text width for the message content
//            int textWidth = width - (game.tileSize * 2);

        }


//        for (String msg : game.chatMessages) {
////            int textWidth= (int)getWidthOfText(msg)+16;
//            int textWidth= width-(game.tileSize*2);
//            if(i%2==0) {
//                gc.setFill(lightCream);
//                gc.fillRoundRect(x-16, messageY-20,textWidth , game.tileSize, 20, 20);
//                gc.setFill(darkDarkCream);
//            }else {
//                gc.setFill(darkCream);
//                gc.fillRoundRect(x-16, messageY-20,textWidth , game.tileSize, 20, 20);
//                gc.setFill(lightCream);
//            }
//
//            gc.fillText(msg, x, messageY);
//            messageY += game.tileSize; // Move to the next line for the next message
//            i++;
//        }
        y+=(11*game.tileSize)+16;

        text="send";
        int textWidth=(int)getWidthOfText(text);
        tempX=game.tileSize*26;
        y+=game.tileSize;
        int buttonX = tempX-5;
        int buttonY = y-20;
        int buttonWidth = textWidth+10;
        int buttonHeight = game.tileSize;
        gc.setFill(darkCream);
        gc.fillRoundRect(buttonX, buttonY, buttonWidth, buttonHeight, 10, 10);
        gc.setFill(lightCream);
        gc.fillText(text,tempX,y);

        buttonX=x;
        buttonWidth*=17;
//        buttonHeight=;
        gc.setFill(Color.WHITE);
        gc.fillRoundRect(buttonX, buttonY, buttonWidth-15, buttonHeight, 10, 10);
        gc.setFill(darkDarkCream);
        gc.strokeRoundRect(buttonX, buttonY, buttonWidth -15, buttonHeight, 10, 10);

        gc.fillText(msgText,buttonX+10,y);
        int limit=88;
        String limitMsg=+ msgText.length()+"/"+limit+"";
//        int now= msgText.length();
        gc.setFont(smallFont);
        gc.fillText(limitMsg,buttonX+buttonWidth-50,y-8);





    }
    public void appendChat(String message) {
        if (game.chatMessages.size() >= 11) {
            game.chatMessages.removeFirst(); // Remove the oldest message if we already have 11 messages
        }
        game.chatMessages.add(message);
        // Logic to append message to the chat display
        System.out.println(message); // For simplicity, just print it. Adapt as needed.
    }
    public void fisheriesScreen(){
        int x = game.tileSize * 3;
        int y = game.tileSize / 2;
        int width = game.screenWidth-(game.tileSize*6);
        int height = game.tileSize * 4;
        drawSubWindow(x, y, width, height,cream,darkCream);
        gc.setFill(darkDarkCream);
        x += game.tileSize;
        y += game.tileSize;
        gc.setFont(mediumFontBold);
        for(String line: currentDialogue.split("\n")){
            gc.fillText(line,x,y);
            y+=game.tileSize;
        }
        x = game.tileSize * 20;
        y = (game.tileSize / 2)+(game.tileSize*4);
        width = game.tileSize*6;
        height = game.tileSize * 4;
        drawSubWindow(x, y, width, height,cream,darkCream);
        gc.setFill(darkDarkCream);
        x += game.tileSize;
        y += game.tileSize;
        gc.fillText("Do you want to buy?",x,y);
        y += game.tileSize;
        gc.fillText("Yes",x,y);
        if(commandNum==0){
            gc.fillText(">",x-24,y);
            if(game.player.keyHandler.isEnterPressed()==true){
                game.playSoundEffect(game.soundEffect.popUp);
                game.gameState=game.messageState;
                if(game.player.coin>=200){
                    game.player.coin-=200;
                    addMessage("cost 200 coins!");
                    currentDialogue="You got a fish! You can find it in your inventory!";
                    //addign fish in inventory
                    game.player.inventory.add(new OBJ_Fish(game));
                    //update the inventory in database
                    MyJDBC.addItemToInventory(game.player.playerId,new OBJ_Fish(game).itemCode);
                }else{
                    currentDialogue="You do not have enough coin";
                }
            }
        }
        y+= game.tileSize;
        gc.fillText("No",x,y);
        if(commandNum==1){
            gc.fillText(">",x-24,y);
            if(game.player.keyHandler.isEnterPressed()==true) {
                game.gameState = game.playState;
            }
        }
    }

    public  void shipTeleportScreen(){
        //we will write code later
    int x = game.tileSize * 3;
    int y = game.tileSize / 2;
    int width = game.screenWidth-(game.tileSize*6);
    int height = game.tileSize * 4;
    drawSubWindow(x, y, width, height,cream,darkCream);
    gc.setFill(darkDarkCream);
    x += game.tileSize;
    y += game.tileSize;
    //System.out.println(currentDialogue);
    //to create new line
    gc.setFont(mediumFontBold);
    for(String line: currentDialogue.split("\n")){
        gc.fillText(line,x,y);
        y+=game.tileSize;

    }
     x = game.tileSize * 20;
     y = (game.tileSize / 2)+(game.tileSize*4);
     width = game.tileSize*6;
     height = game.tileSize * 4;
    drawSubWindow(x, y, width, height,cream,darkCream);
    gc.setFill(darkDarkCream);
    x += game.tileSize;
    y += game.tileSize;
    if(game.isShipStarted==false){
    gc.fillText("Do you got the Blue Key?",x,y);
    }else {
    gc.fillText("Do you want to go?",x,y);
    }
    y += game.tileSize;
    gc.fillText("Yes",x,y);
    if(commandNum==0){
        gc.fillText(">",x-24,y);
        if(game.player.keyHandler.isEnterPressed()==true){

            if(npc.name.equals("thisSide")){
                Boolean ok = false;
                if(game.isShipStarted==false){
                    for(int i =0;i<game.player.inventory.size();i++){
                        if(game.player.inventory.get(i) instanceof OBJ_BlueKey){
                            game.eventHandler.teleport(game.currentMap,27,11);
                            MyJDBC.removeItemFromInventory(game.player.playerId,game.player.inventory.get(i).itemCode);
                            game.player.inventory.remove(i);
                            ok=true;
                            break;
                        }
                    }

                }else if(game.isShipStarted==true){
                    if(game.player.coin>=2000){
                        ok=true;
                    }
                }
                if(ok==false){
                    game.playSoundEffect(game.soundEffect.popUp);
                    game.gameState=game.messageState;
                    if(game.isShipStarted==false){
                        currentDialogue="You do not have the Blue Key. The Key is hidden somewhere in the island.";
                    }else if(game.isShipStarted==true){
                        currentDialogue="You do not have enough coin";
                    }
                }else if(ok==true){
                    game.eventHandler.teleport(game.currentMap,27,11);
                    if(game.isShipStarted==false) {
                        game.isShipStarted = true;
                    }else if(game.isShipStarted==true){
                        game.player.coin-=2000;
                    }
                }

            }else if(npc.name.equals("otherSide")){
                game.eventHandler.teleport(game.currentMap,21,85);
            }

        }
    }
    y+= game.tileSize;
    gc.fillText("No",x,y);
    if(commandNum==1){
        gc.fillText(">",x-24,y);
        if(game.keyHandler.isEnterPressed()==true){
            if(game.isShipStarted==false){
            game.gameState=game.messageState;
            currentDialogue="Your mission is to find the blue key. It is said to be hidden deep within the island, guarded by fearsome \ncreatures." +
                    "Once you find the blue key, bring it back to me. With it, we can set sail to the mysterious island \nand uncover its secrets together." +
                    " Good luck, adventurer! The fate of our journey depends on you.";
            }else {
                game.gameState=game.playState;
            }
//            game.gameState=game.playState;
        }
    }


    }

    public void drawTransition() {
        System.out.println("transition method called");
        transitionCounter++;

        // Calculate opacity and ensure it stays within the valid range
        double opacity = transitionCounter * 0.02; // This ensures the value goes from 0.0 to 1.0 over 50 steps

        // Set the fill color with the corrected opacity value
        gc.setFill(new Color(0, 0, 0, opacity));
        gc.fillRect(0, 0, game.screenWidth, game.screenHeight);

        if (transitionCounter == 50) {
            transitionCounter = 0;
            game.currentMap = game.eventHandler.tempMap;
            game.player.worldX = game.tileSize * game.eventHandler.tempCol;
            game.player.worldY = game.tileSize * game.eventHandler.tempRow;

            if(npc.name.equals("thisSide")){
                    game.player.direction="down";
                game.playSoundEffect(game.soundEffect.popUp);
                    game.gameState=game.messageState;
                game.playSoundEffect(game.soundEffect.popUp);

                currentDialogue="Welcome to the mysterious Island";

            }else if(npc.name.equals("otherSide")){
                game.player.direction="down";
                game.playSoundEffect(game.soundEffect.popUp);
                game.gameState=game.messageState;
                game.playSoundEffect(game.soundEffect.popUp);
                currentDialogue="Welcome back to the Monster Island!";
            }else{
                game.gameState = game.playState;
            }
        }
    }


    public void addMessage(String text){
        game.playSoundEffect(game.soundEffect.addMessage);
//        message = text;
//        messageOn = true;
        message.add(text);
        messageCounter.add(0);
    }

    public void drawMessage(){
        int messageX = game.tileSize;
        int messageY = game.tileSize * 5;

        gc.setFont(largeFontBold);
        gc.setFill(Color.WHITE);
        for(int i = 0; i < message.size(); i++){

            if(message.get(i) != null){

                gc.fillText(message.get(i), messageX, messageY);

                int counter = messageCounter.get(i) + 1;
                messageCounter.set(i, counter);     //set the counter to the array;
                messageY += 30;

                if(messageCounter.get(i) > 240){
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
        }
    }

    public void drawMessageRight(int size){
        int messageX = game.tileSize*size;
        int messageY = game.tileSize * 5;

        gc.setFont(largeFontBold);
        gc.setFill(Color.WHITE);
        for(int i = 0; i < message.size(); i++){

            if(message.get(i) != null){

                gc.fillText(message.get(i), messageX, messageY);

                int counter = messageCounter.get(i) + 1;
                messageCounter.set(i, counter);     //set the counter to the array;
                messageY += 30;

                if(messageCounter.get(i) > 240){
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
        }
    }

    public void drawDialogueScreen(){
        //  CREATING A DIALOGUE WINDOW
        //set parameter for window
//        System.out.println("UI: drawDialogueScreen() method called");

        int x = game.tileSize * 3;
        int y = game.tileSize / 2;
        int width = game.screenWidth-(game.tileSize*6);
        int height = game.tileSize * 4;
        drawSubWindow(x, y, width, height,cream,darkCream);
        gc.setFill(Color.rgb(255,255,255));
        //gc.setFont(gc.getFont().deriveFont(Font.PLAIN,28F));
        x += game.tileSize;
        y += game.tileSize;
        //System.out.println(currentDialogue);
        //to create new line
        gc.setFont(mediumFontBold);
        gc.setFill(darkDarkCream);
        for(String line: currentDialogue.split("\n")){
            gc.fillText(line,x,y);
            y+=32;

        }

    }

    public void drawSubWindow(int x, int y, int width, int height){
        Color c = Color.rgb(0, 0, 0, .80);
        gc.setFill(c);
        gc.fillRoundRect(x,y,width,height,35,35);

        gc.setStroke(Color.rgb(170, 60, 60));


        gc.setLineWidth(5); // Setting stroke width
        gc.setLineDashes(0); // Setting line dashes to 0 (solid line)
        gc.setLineCap(StrokeLineCap.ROUND); // Setting line cap to round
        gc.strokeRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    public void drawSubWindow(int x, int y, int width, int height, Color color,Color strokeColor){
        gc.setFill(color);
        gc.fillRoundRect(x,y,width,height,35,35);
        gc.setStroke(strokeColor);
        gc.setLineWidth(4); // Setting stroke width
        gc.strokeRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    public void drawWizConversationScreen(){
        System.out.println("Command num: "+commandNum);
        //System.out.println("drawWizConversationScreen draw working");

        drawDialogueScreen();
        int x=game.tileSize*15;
        int y=game.tileSize*4;
        int width=game.tileSize*3;
        int height=game.tileSize*4;
        drawSubWindow(x, y, width, height);
        //Draw Text
        gc.setFill(Color.rgb(255,255,255));
        x+=game.tileSize;
        y+=game.tileSize;
        gc.fillText("School",x,y);
        if(commandNum==0){
            System.out.println("command num 2 ");
            gc.fillText(">",x-24,y);
            if(game.keyHandler.isEnterPressed()){
                showPath(84,115);
            }
        }

        y+=game.tileSize;
        gc.fillText("Game Center",x,y);
        if(commandNum==1){
            System.out.println("command num 1 ");
            gc.fillText(">",x-24,y);
            if(game.keyHandler.isEnterPressed()){
                showPath(176,166);
            }
        }

        y+=game.tileSize;
        gc.fillText("Shop",x,y);
        if(commandNum==2){
            System.out.println("command num 2 ");
            gc.fillText(">",x-24,y);
            if(game.keyHandler.isEnterPressed()){
                showPath(118,153);
            }
        }

        y+=game.tileSize;
        gc.fillText("leave",x,y);
        if(commandNum==3){
            gc.fillText(">",x-24,y);
            if(game.keyHandler.isEnterPressed()){
                System.out.println("enter press for leave working");
                commandNum=0;
                game.gameState=game.playState;
            }

        }
    }

    public int getItemIndexOnSlot(int slotCol,int slotRow){
        int itemIndex=slotCol+(slotRow*5);
        return itemIndex;
    }
    public double getWidthOfText(String text){
        // Create a temporary Text node to measure the width
        Text textNode = new Text(text);
        textNode.setFont(gc.getFont());
        double textWidth = textNode.getBoundsInLocal().getWidth();
        return textWidth;
    }
    public int getXForAlignToRightText(String text, int tailX) {
        Text textNode = new Text(text);
        textNode.setFont(gc.getFont());
        int length = (int)textNode.getBoundsInLocal().getWidth();
        int x = tailX - length;
        return x;
    }

    public int getXForCenteredText(String text) {
        Text textNode = new Text(text);
        textNode.setFont(gc.getFont());
        int length = (int)textNode.getBoundsInLocal().getWidth();
        int x = game.screenWidth/2 - length/2;
        return x;
    }

    public int getXForCenteredTextInFrame(String text,int frameX,int frameWidth) {
        Text textNode = new Text(text);
        textNode.setFont(gc.getFont());
        double length = textNode.getLayoutBounds().getWidth();

        int x = frameX + (frameWidth / 2 - (int) length / 2);
        return x;
    }

    public double getXforAlignRightText(String text, int tailX){
        double width=getWidthOfText(text);
        double x=tailX-width;
        return x;
    }

    public void showPath(int goalCol,int goalRow) {
        npc.onPath = true;
        npc.goalCol = goalCol;
        npc.goalRow = goalRow;
        game.gameState = game.playState;
    }
}

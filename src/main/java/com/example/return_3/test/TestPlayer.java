package com.example.return_3.test;


import com.example.return_3.entity.Entity;
import com.example.return_3.gameCenter.spaceInvaders.GameSpaceInvaders;
import com.example.return_3.main.Game;
import com.example.return_3.main.KeyHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.Objects;

public class TestPlayer extends TestEntity{
    public TestKeyHandler keyHandler;
//    public int gameState;
//    public final int titleState=0;
//    public final int playState=1;
//    public final int pauseState=2;
//    public final int dialogueState=3;
//    public final int characterState=4;

    TestGame game;
    public final int tileSize = 32; //SO every tile will be 32 pixels
    public final int maxScreenCol = 30; //here will be 20 column of titles  =>1024 pixel width
    public final int maxScreenRow = 18; //here will be 25 row of titles => 800 pixel height
    public final int screenWidth= tileSize*maxScreenCol; //1024 pixel width
    public final int screenHeight= tileSize*maxScreenRow; //800 pixel height

    public Image up1,up2,down1, down2,left1,left2,right1,right2;

    int speed;
    boolean collisionOn=false;
    public int spriteCounter=0;
    public  int spriteNum=1;


    public  int life;
    public int maxLife=4;


    public Rectangle solidArea;

    public int solidAreaDefaultX,solidAreaDefaultY;

    public TestPlayer(TestGame game,TestKeyHandler keyHandler) {
        super(game);

        this.game=game;
        this.keyHandler=keyHandler;
        double screenWidth = game.scene.getWidth();
        double screenHeight = game.scene.getHeight();
//part 6 collision part starts
        solidArea= new Rectangle(); //we can skip this session. (as we already make it in Entity class)
        solidArea.setX(5);
        solidArea.setY(10);

        //part 8 Object Interaction part starts
        solidAreaDefaultX=(int)(solidArea.getX());
        solidAreaDefaultY=(int)(solidArea.getY());
        //part 8 Object Interaction part  ends

        solidArea.setWidth(22);
        solidArea.setHeight(22);
        //part 6 collision part ends
        loadPlayerImages();



        setDefaultValues();




    }



    public void loadPlayerImages() {
        up1 = loadImage("/gameCenter/spaceInvaders/player/playerShip1_blue.png", game.tileSize, game.tileSize);
        up2 = loadImage("/gameCenter/spaceInvaders/player/playerShip1_blue.png", game.tileSize, game.tileSize);
        down1 = loadImage("/gameCenter/spaceInvaders/player/playerShip1_blue.png", game.tileSize, game.tileSize);
        down2 = loadImage("/gameCenter/spaceInvaders/player/playerShip1_blue.png", game.tileSize, game.tileSize);
        left1 = loadImage("/gameCenter/spaceInvaders/player/playerShip1_blue.png", game.tileSize, game.tileSize);
        left2 = loadImage("/gameCenter/spaceInvaders/player/playerShip1_blue.png", game.tileSize, game.tileSize);
        right1 = loadImage("/gameCenter/spaceInvaders/player/playerShip1_blue.png", game.tileSize, game.tileSize);
        right2 = loadImage("/gameCenter/spaceInvaders/player/playerShip1_blue.png", game.tileSize, game.tileSize);
    }
    public void setDefaultValues(){
        setDefaultPositions();
        speed=(int) (250*game.targetFrameTime); //pixel per second
        life=maxLife;



    }

    public void setDefaultPositions(){
        posX = (game.screenWidth - game.tileSize) / 2; // Center X position
        posY = game.screenHeight - game.tileSize; // Bottom Y position
        direction="down";
    }


    public void update(){



        if(keyHandler.isMoveUp() || keyHandler.isMoveDown() || keyHandler.isMoveRight() || keyHandler.isMoveLeft() || keyHandler.isEnterPressed()) {
            // Move player based on key inputs
            if (keyHandler.isMoveUp()) {
                direction = "up";
            }
            if (keyHandler.isMoveDown()) {
                direction = "down";
            }
            if (keyHandler.isMoveRight()) {
                direction = "right";
            }
            if (keyHandler.isMoveLeft()) {
                direction = "left";
            }

            collisionOn = false;

            //Now check for the colliosion here.

            //game.cChecker.checkTile(this);
//            System.out.println("Collision: " + collisionOn);

            //CHeck NPC collision
            int npcIndex=game.collisionChecker.checkEntity(this,game.enemies);
            if(npcIndex!=999){
                System.out.println(npcIndex);
            }
            interactNPC(npcIndex);


            //new code




            if (collisionOn == false) {
                switch (direction) {
                    case "up":
                        posY -= speed;
                        break;
                    case "down":
                        posY += speed;
                        break;
                    case "left":
                        posX -= speed;
                        break;
                    case "right":
                        posX += speed;
                        break;
                }
            }
            if (posX < 0) {
                posX = 0; // Prevent player from moving beyond the left boundary
            } else if (posX + game.tileSize > (screenWidth-(2*game.tileSize))) {
                posX = (screenWidth-(2*game.tileSize)) - game.tileSize; // Prevent player from moving beyond the right boundary
            }

            if (posY < 0) {
                posY = 0; // Prevent player from moving beyond the top boundary
            } else if (posY + game.tileSize > screenHeight) {
                posY = screenHeight - game.tileSize; // Prevent player from moving beyond the bottom boundary
            }


//        playerImageView.setX(playerImageView.getX() - (playerSpeed*deltaTime));

            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }

        }


        if(destroyed){
            System.out.println("TestPlayer 188: destroy method: player died");
        }
        destroyed=false;

//        try{
//            //game.eventHandler.checkEvent();
//        }catch(IOException e){
//            e.printStackTrace();
//        }

//        System.out.println("player posX: "+posX + " posY: "+posY);
//        System.out.println("player solidX: "+solidAreaDefaultX + " solidY: "+solidAreaDefaultY);

    }


    public void draw(GraphicsContext gc){
        Image image= null;

        //temp variable
//        int tempScreenX=screenX;
//        int tempScreenY=screenY;

        switch (direction) {
            case "up":
                if (spriteNum == 1){

                    //playerImageView.setImage(up1);
                    image=up1;
                }
                else if(spriteNum == 2){
                    image=(up2);
                }
                break;
            case "down":
                if (spriteNum == 1){

                    image=(down1);
                }
                else if(spriteNum == 2){
                    image=(down2);
                }
                break;
            case "right":
                if (spriteNum == 1){

                    image=(right1);
                }
                else if(spriteNum == 2){
                    image=(right2);
                }
                break;

            case "left":
                if (spriteNum == 1){

                    image=(left1);
                }
                else if(spriteNum == 2){
                    image=(left2);
                }
                break;
            // Handle other directions similarly
        }

        gc.drawImage(image,posX,posY);
    }



 public void interactNPC(int i){
        if(i!=999){
            game.enemies[i]=null;
            life--;
            System.out.println("Life:"+life);
        }

 }

    public Image loadImage(String imagePath, int width, int height) {
        return new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)), width, height, true, true);
    }

}



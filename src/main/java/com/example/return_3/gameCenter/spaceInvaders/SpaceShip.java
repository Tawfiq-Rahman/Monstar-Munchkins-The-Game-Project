package com.example.return_3.gameCenter.spaceInvaders;

import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;
import com.example.return_3.main.KeyHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class SpaceShip extends Entity {
    public SpaceInvadersKeyHandler keyHandler;
    GameSpaceInvaders gameSpaceInvaders;
    Image bcPic;
    Game game;
    public int posX,posY;


    //    public final int screenX;
//    public final int screenY;
    public SpaceShip(Game game,SpaceInvadersKeyHandler keyHandler) {
        super(game);
        this.game=game;
        gameSpaceInvaders= Game.gameSpaceInvaders;
        this.keyHandler=keyHandler;
        double screenWidth = Game.gameScene.getWidth();
        double screenHeight = Game.gameScene.getHeight();
        maxLife=6;
        life=maxLife;
       // bcPic=  loadImage("/gameCenter/spaceInvaders/background_1.jpg", game.screenWidth, game.screenHeight);



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

        // Load player images and initialize ImageView
        loadPlayerImages();



        setDefaultValues();




    }



    private void loadPlayerImages() {
        up1 = loadImage("/gameCenter/spaceInvaders/player/playerShip1_blue.png", game.tileSize*5, game.tileSize);
        up2 = loadImage("/gameCenter/spaceInvaders/player/playerShip1_blue.png", game.tileSize*5, game.tileSize);
        down1 = loadImage("/gameCenter/spaceInvaders/player/playerShip1_blue.png", game.tileSize*5, game.tileSize);
        down2 = loadImage("/gameCenter/spaceInvaders/player/playerShip1_blue.png", game.tileSize*5, game.tileSize);
        left1 = loadImage("/gameCenter/spaceInvaders/player/playerShip1_blue.png", game.tileSize*5, game.tileSize);
        left2 = loadImage("/gameCenter/spaceInvaders/player/playerShip1_blue.png", game.tileSize*5, game.tileSize);
        right1 = loadImage("/gameCenter/spaceInvaders/player/playerShip1_blue.png", game.tileSize*5, game.tileSize);
        right2 = loadImage("/gameCenter/spaceInvaders/player/playerShip1_blue.png", game.tileSize*5, game.tileSize);
    }
    public void setDefaultValues(){
        setDefaultPositions();
        speed=(int) (150*game.targetFrameTime); //pixel per second

    }

    public void setDefaultPositions(){
        posX = (game.screenWidth - game.tileSize) / 2; // Center X position
        posY = game.screenHeight - game.tileSize; // Bottom Y position
        direction="down";
    }


    public void update(){
        if(life<=0){
            destroyed=true;
        }

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

            //CHeck NPC collision
            int npcIndex=game.cChecker.checkEnemy(this,Game.gameSpaceInvaders.enemies);
            if(npcIndex!=999){
                System.out.println(npcIndex);
            }
            interactNPC(npcIndex);



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
            } else if (posX + game.tileSize > game.screenWidth) {
                posX = game.screenWidth - game.tileSize; // Prevent player from moving beyond the right boundary
            }

            if (posY < 0) {
                posY = 0; // Prevent player from moving beyond the top boundary
            } else if (posY + game.tileSize > game.screenHeight) {
                posY = game.screenHeight - game.tileSize; // Prevent player from moving beyond the bottom boundary
            }

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




    }
    public void interactNPC(int i){
        if(i!=999){
            Game.gameSpaceInvaders.enemies[i]=null;
            life--;
            System.out.println("Life:"+life);
        }

    }


    public void draw(GraphicsContext gc){
        Image image= null;

        Image backgroundImage =bcPic;

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
       // gc.drawImage(bcPic,0,0);

        gc.drawImage(image,posX,posY);
    }


}

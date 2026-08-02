package com.example.return_3.test;

import java.util.ArrayList;
import java.util.Random;

public class TestEnemyBlue extends TestEntity {
    public TestEnemyBlue(TestGame game) {
        super(game);
        loadPlayerImages();
        setDefaultValues();
        shots = new ArrayList<>();
        shotSpeed=(int) (130*game.targetFrameTime);
    }


    public void loadPlayerImages() {
        up1 = loadImage("/gameCenter/spaceInvaders/enemy/enemyBlue1.png", game.tileSize, game.tileSize);
        up2 = loadImage("/gameCenter/spaceInvaders/enemy/enemyBlue1.png", game.tileSize, game.tileSize);
        down1 = loadImage("/gameCenter/spaceInvaders/enemy/enemyBlue1.png", game.tileSize, game.tileSize);
        down2 = loadImage("/gameCenter/spaceInvaders/enemy/enemyBlue1.png", game.tileSize, game.tileSize);
        left1 = loadImage("/gameCenter/spaceInvaders/enemy/enemyBlue1.png", game.tileSize, game.tileSize);
        left2 = loadImage("/gameCenter/spaceInvaders/enemy/enemyBlue1.png", game.tileSize, game.tileSize);
        right1 = loadImage("/gameCenter/spaceInvaders/enemy/enemyBlue1.png", game.tileSize, game.tileSize);
        right2 = loadImage("/gameCenter/spaceInvaders/enemy/enemyBlue1.png", game.tileSize, game.tileSize);
        shotImage=loadImage("/gameCenter/spaceInvaders/fire/fire19.png", game.tileSize, game.tileSize);
    }
    public void setAction(){
        actionLookCounter++;
        if(actionLookCounter==120){//for two seconds it means
            Random random= new Random();
            int i=random.nextInt(100)+1; //we add 1 because otherwise it will catch 0 to 99.. we want to avoid 0 here
            if(i<=50){
                direction="down";
            }
            if(i>50&&i<=75){
                direction="left";
            }
            if(i>75&&i<=100){
                direction="right";
            }
            actionLookCounter=0;
        }
    }
    public void update(){
        setAction();
        collisionOn=false;
        //            //collision related code
            if(game.collisionChecker.checkPlayer(this)){
                game.testPlayer.destroyed=true;
            }

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
            bottomTouched=true;
            // posY = screenHeight - game.tileSize; // Prevent player from moving beyond the bottom boundary
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
        for (TestShot shot : shots) {
            shot.update();
        }
        // Remove inactive shots
        shots.removeIf(shot -> !shot.isActive());
        // Shoot at player
        shootAtPlayer();
    }

}

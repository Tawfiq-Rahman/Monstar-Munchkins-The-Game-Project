package com.example.return_3.gameCenter.spaceInvaders.enemy;

import com.example.return_3.gameCenter.spaceInvaders.Shot;
import com.example.return_3.main.Game;
import com.example.return_3.test.TestShot;

import java.util.ArrayList;

public class Enemy_Red extends Enemy{
    public Enemy_Red(Game game) {
        super(game);
        loadPlayerImages();
        setDefaultValues();
        shots = new ArrayList<>();
        shotSpeed=(int) (130*game.targetFrameTime);
    }

    public void loadPlayerImages() {
        up1 = loadImage("/gameCenter/spaceInvaders/enemy/enemyRed1.png", game.tileSize, game.tileSize);
        up2 = loadImage("/gameCenter/spaceInvaders/enemy/enemyRed1.png", game.tileSize, game.tileSize);
        down1 = loadImage("/gameCenter/spaceInvaders/enemy/enemyRed1.png", game.tileSize, game.tileSize);
        down2 = loadImage("/gameCenter/spaceInvaders/enemy/enemyRed1.png", game.tileSize, game.tileSize);
        left1 = loadImage("/gameCenter/spaceInvaders/enemy/enemyRed1.png", game.tileSize, game.tileSize);
        left2 = loadImage("/gameCenter/spaceInvaders/enemy/enemyRed1.png", game.tileSize, game.tileSize);
        right1 = loadImage("/gameCenter/spaceInvaders/enemy/enemyRed1.png", game.tileSize, game.tileSize);
        right2 = loadImage("/gameCenter/spaceInvaders/enemy/enemyRed1.png", game.tileSize, game.tileSize);
        shotImage=loadImage("/gameCenter/spaceInvaders/fire/fire19.png", game.tileSize, game.tileSize);
    }
    public void setAction(){
        actionLookCounter++;
        if(actionLookCounter == 120){ // Every two seconds
            // Get player position
            int playerX = Game.gameSpaceInvaders.spaceShip.posX;
            int playerY = Game.gameSpaceInvaders.spaceShip.posY;

            // Calculate the direction towards the player
            int dx = playerX - posX;
            int dy = playerY - posY;

            // Determine the primary direction based on the largest difference
            if(Math.abs(dx) > Math.abs(dy)){ // Horizontal movement
                direction = dx > 0 ? "right" : "left";
            } else { // Vertical movement
                direction = dy > 0 ? "down" : "up";
            }

            actionLookCounter = 0;
        }
    }

    public void update(){
        setAction();
        collisionOn=false;
        //            //collision related code
        if(game.cChecker.checkPlayer(this)){
            Game.gameSpaceInvaders.spaceShip.destroyed=true;
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
        } else if (posX + game.tileSize > (game.screenWidth-(2*game.tileSize))) {
            posX = (game.screenWidth-(2*game.tileSize)) - game.tileSize; // Prevent player from moving beyond the right boundary
        }

        if (posY < 0) {
            posY = 0; // Prevent player from moving beyond the top boundary
        } else if (posY + game.tileSize > game.screenHeight) {
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

        for (Shot shot : shots) {
            shot.update();
        }
        // Remove inactive shots
        shots.removeIf(shot -> !shot.isActive());
        // Shoot at player
        shootAtPlayer();
    }
}

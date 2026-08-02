package com.example.return_3.gameCenter.spaceInvaders.enemy;

import com.example.return_3.entity.Entity;
import com.example.return_3.gameCenter.spaceInvaders.Shot;
import com.example.return_3.main.Game;
import com.example.return_3.test.TestEntity;
import com.example.return_3.test.TestGame;
import com.example.return_3.test.TestShot;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Random;




public class Enemy_Black extends Enemy {

Game game;

    public Enemy_Black(Game game) {
        super(game);
        this.game = game;
        loadPlayerImages();
        setDefaultValues();
        speed=(int) (80*game.targetFrameTime);
        shots = new ArrayList<>();
        shotSpeed=(int) (130*game.targetFrameTime);

    }




    public void loadPlayerImages() {
        up1 = loadImage("/gameCenter/spaceInvaders/enemy/enemyBlack1.png", game.tileSize, game.tileSize);
        up2 = loadImage("/gameCenter/spaceInvaders/enemy/enemyBlack1.png", game.tileSize, game.tileSize);
        down1 = loadImage("/gameCenter/spaceInvaders/enemy/enemyBlack1.png", game.tileSize, game.tileSize);
        down2 = loadImage("/gameCenter/spaceInvaders/enemy/enemyBlack1.png", game.tileSize, game.tileSize);
        left1 = loadImage("/gameCenter/spaceInvaders/enemy/enemyBlack1.png", game.tileSize, game.tileSize);
        left2 = loadImage("/gameCenter/spaceInvaders/enemy/enemyBlack1.png", game.tileSize, game.tileSize);
        right1 = loadImage("/gameCenter/spaceInvaders/enemy/enemyBlack1.png", game.tileSize, game.tileSize);
        right2 = loadImage("/gameCenter/spaceInvaders/enemy/enemyBlack1.png", game.tileSize, game.tileSize);
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

            if(Math.max(Math.abs(dx), Math.abs(dy))<300){
                // Determine the primary direction based on the largest difference
                if(Math.abs(dx) > Math.abs(dy)){ // Horizontal movement
                    direction = dx > 0 ? "right" : "left";
                } else { // Vertical movement
                    direction = dy > 0 ? "down" : "up";
                }
            }else{
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
            }

            actionLookCounter = 0;
        }
    }




    public void update(){
        super.update();
        for (Shot shot : shots) {
            shot.update();
        }
        // Remove inactive shots
        shots.removeIf(shot -> !shot.isActive());
        // Shoot at player
        shootAtPlayer();
//        System.out.println("enemyBlack posX: "+posX + " posY: "+posY);
//        System.out.println("enemyBlack solidX: "+solidAreaDefaultX + " solidY: "+solidAreaDefaultY);

    }




    public void draw(GraphicsContext gc) {
        super.draw(gc);
        // Draw shots
        for (Shot shot : shots) {
            shot.draw(gc);
        }
    }


}


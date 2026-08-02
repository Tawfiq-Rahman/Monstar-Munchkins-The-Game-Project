package com.example.return_3.test;

import java.util.ArrayList;
import java.util.Random;

public class TestEnemyGreen extends TestEntity {
    public TestEnemyGreen(TestGame game) {
        super(game);
        shots = new ArrayList<>();
        shotSpeed=(int) (130*game.targetFrameTime);
        loadPlayerImages();
        setDefaultValues();
    }
    public void loadPlayerImages() {
        up1 = loadImage("/gameCenter/spaceInvaders/enemy/enemyGreen1.png", game.tileSize, game.tileSize);
        up2 = loadImage("/gameCenter/spaceInvaders/enemy/enemyGreen1.png", game.tileSize, game.tileSize);
        down1 = loadImage("/gameCenter/spaceInvaders/enemy/enemyGreen1.png", game.tileSize, game.tileSize);
        down2 = loadImage("/gameCenter/spaceInvaders/enemy/enemyGreen1.png", game.tileSize, game.tileSize);
        left1 = loadImage("/gameCenter/spaceInvaders/enemy/enemyGreen1.png", game.tileSize, game.tileSize);
        left2 = loadImage("/gameCenter/spaceInvaders/enemy/enemyGreen1.png", game.tileSize, game.tileSize);
        right1 = loadImage("/gameCenter/spaceInvaders/enemy/enemyGreen1.png", game.tileSize, game.tileSize);
        right2 = loadImage("/gameCenter/spaceInvaders/enemy/enemyGreen1.png", game.tileSize, game.tileSize);
        shotImage=loadImage("/gameCenter/spaceInvaders/fire/fire19.png", game.tileSize, game.tileSize);
    }
    public void setAction(){
        actionLookCounter++;
        if(actionLookCounter==100){//for two seconds it means
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

}

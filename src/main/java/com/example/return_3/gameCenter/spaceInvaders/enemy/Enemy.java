package com.example.return_3.gameCenter.spaceInvaders.enemy;

import com.example.return_3.entity.Entity;
import com.example.return_3.gameCenter.spaceInvaders.Shot;
import com.example.return_3.main.Game;
import com.example.return_3.test.TestShot;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.List;
import java.util.Random;

public class Enemy extends Entity {
    Game game;
    public List<Shot> shots;
    public  int shotAvailableCounter=0;
    public boolean destroyed=false;
    Image shotImage;
    int shotSpeed;

    public Enemy(Game game) {
        super(game);
        this.game = game;
    }
    public void setDefaultValues(){
        direction="down";
        speed=(int) (100*game.targetFrameTime); //pixel per second

    }
    public void update(){
        setAction();
        collisionOn = false;

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
    public void setAction(){
        actionLookCounter++;
        if(actionLookCounter==100){//for two seconds it means
            Random random= new Random();
            int i=random.nextInt(100)+1; //we add 1 because otherwise it will catch 0 to 99.. we want to avoid 0 here
            if(i<=25){
                direction="up";
            }
            if(i>25&&i<=50){
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
        for (Shot shot : shots) {
            shot.update();
        }
        // Remove inactive shots
        shots.removeIf(shot -> !shot.isActive());
        // Shoot at player
        shootAtPlayer();
    }
    public void shootAtPlayer() {
        shotAvailableCounter++;

        // Logic to determine when to shoot at the player
        // Create shot instances and add them to the shots list
        // Example:
        int i=new Random().nextInt(100)+1;
        if (i>99&&shotAvailableCounter>50) {
            Shot shot = new Shot(game,posX, posY, shotSpeed, direction, shotImage, game.screenWidth, game.screenHeight);
            shots.add(shot);
            shotAvailableCounter=0;
        }
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

        for (Shot shot : shots) {
            shot.draw(gc);
        }

        gc.drawImage(image,posX,posY);
    }
}

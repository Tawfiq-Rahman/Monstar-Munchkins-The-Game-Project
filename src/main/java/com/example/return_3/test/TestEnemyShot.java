package com.example.return_3.test;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Objects;

public class TestEnemyShot {
    TestGame game;
    public final int tileSize = 32; //SO every tile will be 32 pixels
    public final int maxScreenCol = 30; //here will be 20 column of titles  =>1024 pixel width
    public final int maxScreenRow = 18; //here will be 25 row of titles => 800 pixel height
    public final int screenWidth= tileSize*maxScreenCol; //1024 pixel width
    public final int screenHeight= tileSize*maxScreenRow; //800 pixel height

    public Image up1,up2,down1, down2,left1,left2,right1,right2;

    int speed;
    String direction;
    boolean collisionOn=false;
    int posX,posY;
    public int spriteCounter=0;
    public  int spriteNum=1;
    public int actionLookCounter=0;






    public TestEnemyShot(TestGame game) {
        this.game=game;
        double screenWidth = game.scene.getWidth();
        double screenHeight = game.scene.getHeight();
        loadPlayerImages();
        setDefaultValues();
    }


    public void loadPlayerImages() {
        up1 = loadImage("/gameCenter/spaceInvaders/fire/fire09.png", game.tileSize, game.tileSize);
        up2 = loadImage("/gameCenter/spaceInvaders/fire/fire09.png", game.tileSize, game.tileSize);
        down1 = loadImage("/gameCenter/spaceInvaders/fire/fire09.png", game.tileSize, game.tileSize);
        down2 = loadImage("/gameCenter/spaceInvaders/fire/fire09.png", game.tileSize, game.tileSize);
        left1 = loadImage("/gameCenter/spaceInvaders/fire/fire09.png", game.tileSize, game.tileSize);
        left2 = loadImage("/gameCenter/spaceInvaders/fire/fire09.png", game.tileSize, game.tileSize);
        right1 = loadImage("/gameCenter/spaceInvaders/fire/fire09.png", game.tileSize, game.tileSize);
        right2 = loadImage("/gameCenter/spaceInvaders/fire/fire09.png", game.tileSize, game.tileSize);
    }
    public void setDefaultValues(){
        direction="down";
        speed=(int) (100*game.targetFrameTime); //pixel per second

    }

    public void update(){



    }
    public void draw(GraphicsContext gc){
        Image image= null;

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




    public Image loadImage(String imagePath, int width, int height) {
        return new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)), width, height, true, true);
    }

}

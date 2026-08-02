package com.example.return_3.test;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class TestEntity {
    public List<TestShot> shots;
    //public Rectangle solidArea = new Rectangle(0,0,32,32);
    public Rectangle solidArea;
    public int solidAreaDefaultX,solidAreaDefaultY;




    public  int shotAvailableCounter=0;


    boolean bottomTouched=false;

    TestGame game;
    public final int tileSize = 32; //SO every tile will be 32 pixels
    public final int maxScreenCol = 30; //here will be 20 column of titles  =>1024 pixel width
    public final int maxScreenRow = 18; //here will be 25 row of titles => 800 pixel height
    public final int screenWidth= tileSize*maxScreenCol; //1024 pixel width
    public final int screenHeight= tileSize*maxScreenRow; //800 pixel height

    public Image up1,up2,down1, down2,left1,left2,right1,right2;

    int speed;
    String direction="down";
    boolean collisionOn=false;
    int posX,posY;
    public int spriteCounter=0;
    public  int spriteNum=1;
    public int actionLookCounter=0;
    public boolean destroyed=false;
    Image shotImage;
    int shotSpeed;

    public TestEntity(TestGame game) {
        this.game=game;
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
//        loadPlayerImages();

    }



    public void loadPlayerImages() {
//        up1 = loadImage("/gameCenter/spaceInvaders/enemy/enemyBlue1.png", game.tileSize, game.tileSize);
//        up2 = loadImage("/gameCenter/spaceInvaders/enemy/enemyBlue1.png", game.tileSize, game.tileSize);
//        down1 = loadImage("/gameCenter/spaceInvaders/enemy/enemyBlue1.png", game.tileSize, game.tileSize);
//        down2 = loadImage("/gameCenter/spaceInvaders/enemy/enemyBlue1.png", game.tileSize, game.tileSize);
//        left1 = loadImage("/gameCenter/spaceInvaders/enemy/enemyBlue1.png", game.tileSize, game.tileSize);
//        left2 = loadImage("/gameCenter/spaceInvaders/enemy/enemyBlue1.png", game.tileSize, game.tileSize);
//        right1 = loadImage("/gameCenter/spaceInvaders/enemy/enemyBlue1.png", game.tileSize, game.tileSize);
//        right2 = loadImage("/gameCenter/spaceInvaders/enemy/enemyBlue1.png", game.tileSize, game.tileSize);
    }
    public void setDefaultValues(){
        direction="down";
        speed=(int) (100*game.targetFrameTime); //pixel per second

    }

    public void update(){

        setAction();
            collisionOn = false;

//            //collision related code
//            if(game.collisionChecker.checkPlayer(this)){
//                game.testPlayer.destroyed=true;
//            }

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





//        try{
//            //game.eventHandler.checkEvent();
//        }catch(IOException e){
//            e.printStackTrace();
//        }



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
        for (TestShot shot : shots) {
            shot.update();
        }
        // Remove inactive shots
        shots.removeIf(shot -> !shot.isActive());
        // Shoot at player
        shootAtPlayer();
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

        for (TestShot shot : shots) {
            shot.draw(gc);
        }

        gc.drawImage(image,posX,posY);
    }


    public void shootAtPlayer() {
        shotAvailableCounter++;

        // Logic to determine when to shoot at the player
        // Create shot instances and add them to the shots list
        // Example:
        int i=new Random().nextInt(100)+1;
        if (i>99&&shotAvailableCounter>50) {
            TestShot shot = new TestShot(game,posX, posY, shotSpeed, direction, shotImage, screenWidth, screenHeight);
            shots.add(shot);
            shotAvailableCounter=0;
        }
    }


    public Image loadImage(String imagePath, int width, int height) {
        return new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)), width, height, true, true);
    }

}




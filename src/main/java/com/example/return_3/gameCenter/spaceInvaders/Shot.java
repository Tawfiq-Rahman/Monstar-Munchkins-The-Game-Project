package com.example.return_3.gameCenter.spaceInvaders;

import com.example.return_3.main.Game;
import com.example.return_3.test.TestGame;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;


public class Shot {

    public Rectangle solidArea;
    public int solidAreaDefaultX,solidAreaDefaultY;

    public double posX;
    public double posY;
    public double speed;
    public String direction;
    private Image image;
    private final int screenWidth;
    private final int screenHeight;
    private boolean active;
    Game game;

    public Shot(Game game,double posX, double posY, double speed, String direction, Image image, int screenWidth, int screenHeight) {
        this.game=game;
        this.posX = posX;
        this.posY = posY;
        this.speed = speed;
        this.direction = direction;
        this.image = image;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.active = true;
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
    }

    public void update() {

        if(game.cChecker.checkPlayerShot(this)){
//            Game.gameSpaceInvaders.spaceShip.destroyed=true;
            Game.gameSpaceInvaders.spaceShip.life--;

            active=false;
        }

        switch (direction) {
            case "up":
                posY += speed;
                break;
            case "down":
                posY += speed;
                break;
            case "left":
                posY += speed;
                break;
            case "right":
                posY += speed;
                break;
        }



        // Check if shot is out of bounds
        if (posX < 0 || posX > screenWidth || posY < 0 || posY > screenHeight) {
            active = false;
        }
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(image, posX, posY);
    }

    public boolean isActive() {
        return active;
    }
    public void setActive(boolean setActive) {
        active=setActive;
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }
}

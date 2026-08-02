package com.example.return_3.npc;

import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class NPC_Universal extends Entity {
    Game game;
    String name;
    public NPC_Universal(Game game, String name) {
        super(game);
        this.game=game;
        this.name=name;
//        collisionOn=false;

        //type= type_npc;
        speed=(int) (65*game.targetFrameTime);
        getNPCImage();
    }
    public void getNPCImage(){
        int size=game.tileSize-8;
        up1=loadImage( "/npc/npc_"+name+"_up_1.png",size,size);
        up2= loadImage("/npc/npc_"+name+"_up_2.png",size,size);
        down1= loadImage("/npc/npc_"+name+"_down_1.png",size,size);
        down2= loadImage("/npc/npc_"+name+"_down_2.png",size,size);
        left1=loadImage ("/npc/npc_"+name+"_left_1.png",size,size);
        left2= loadImage("/npc/npc_"+name+"_left_2.png",size,size);
        right1= loadImage("/npc/npc_"+name+"_right_1.png",size,size);
        right2= loadImage("/npc/npc_"+name+"_right_2.png",size,size);
    }
    //set dialogue
    public void speak(){
        //It has no use
    }
    public void checkCollision(){
        collisionOn=false;
        game.cChecker.checkTile(this);
        game.cChecker.checkEntity(this,game.npc);
        game.cChecker.checkEntity(this,game.iTile);
    }
    public void update(){
        getRandomDirection();
        checkCollision();

        //if collisionOn is false then player can be able to move
        if(collisionOn == false){

            switch (direction){
                case "up":worldY -= speed; break;
                case "down":worldY+= speed; break;
                case "left":worldX -= speed; break;
                case "right":worldX += speed; break;
            }
        }

        spriteCounter++;
        if (spriteCounter > 24) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public void draw(GraphicsContext gc){
        Image image= null;
        int screenX= worldX-game.player.worldX + game.player.screenX;
        int screenY= worldY-game.player.worldY + game.player.screenY;


        //this process is for not drawing the whole world map but the map tiles we needed only
        if(worldX+ game.tileSize>game.player.worldX-game.player.screenX&&
                worldX-game.tileSize<game.player.worldX+game.player.screenX&&
                worldY+ game.tileSize>game.player.worldY-game.player.screenY&&
                worldY- game.tileSize<game.player.worldY+game.player.screenY
        ){
            //temp variable
            int tempScreenX = screenX;
            int tempScreenY = screenY;

            switch (direction) {
                case "up":
                        if (spriteNum == 1){image = up1;}
                        else if(spriteNum == 2){image = up2;}break;
                case "down":
                        if (spriteNum == 1){image = down1;}
                        else if(spriteNum == 2){image = down2;}break;
                case "left":
                        if (spriteNum == 1){image = left1;}
                        else if(spriteNum == 2){image = left2;}break;
                case "right":
                        if (spriteNum == 1){image = right1;}
                        else if(spriteNum == 2){image = right2;}break;
            }
            gc.drawImage(image,tempScreenX,tempScreenY);
        }
    }

}


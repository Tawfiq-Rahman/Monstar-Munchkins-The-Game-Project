package com.example.return_3.monster;

import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;
import com.example.return_3.object.OBJ_Coin;
import com.example.return_3.object.OBJ_Heart;
import com.example.return_3.object.OBJ_Potion_Red;
import com.example.return_3.object.OBJ_Rock;

import java.util.Random;

public class Mon_Green extends Entity {
    Game game;
    String imgName;
    public Mon_Green(Game game,int area) {
        super(game);
        this.game=game;
        monster_area=area;
        monster_type=type_slime;
        name = "Green Slime";
        defaultSpeed = 1;
        speed = defaultSpeed;
        coin = 5;
        type = type_monster;
        maxLife = 3;
        life = maxLife;
        attack = 10;
        defense = 0;
        exp = 1;

        //set the SOLID AREA for  collision part
        solidArea.setX(1);
        solidArea.setY(10);
        solidArea.setWidth(30);
        solidArea.setHeight(23);
        solidAreaDefaultX = (int)(solidArea.getX());
        solidAreaDefaultY = (int)(solidArea.getY());
        imgName="greenMon";
        getImage();
    }
    public void getImage(){
        up1 = loadImage( "/monster/" +

                imgName +
                "_up_1.png",game.tileSize,game.tileSize);
        up2 = loadImage("/monster/" +
                imgName +
                "_up_2.png",game.tileSize,game.tileSize);
        down1 = loadImage("/monster/" +
                imgName +
                "_down_1.png",game.tileSize,game.tileSize);
        down2 = loadImage("/monster/" +
                imgName +
                "_down_2.png",game.tileSize,game.tileSize);
        left1 =loadImage ("/monster/" +
                imgName+
                "_left_1.png",game.tileSize,game.tileSize);
        left2 = loadImage("/monster/" +
                imgName+
                "_left_2.png",game.tileSize,game.tileSize);
        right1 = loadImage("/monster/" +
                imgName+
                "_right_1.png",game.tileSize,game.tileSize);
        right2 = loadImage("/monster/" +
                imgName+
                "_right_2.png",game.tileSize,game.tileSize);
    }


    public void setAction(){
            // Get a random direction...
            getRandomDirection();
            if(speedChange==true){
                speed=defaultSpeed+2;
                if(actionLookCounter>40){
                    speedChange=false;
                    speed=defaultSpeed;
                }
            }

    }

    public void damageReaction(){
        actionLookCounter = 0;
        speedChange=true;
        switch (game.player.direction){
            case "up": direction = "up"; break;
            case "left": direction = "left"; break;
            case "right": direction = "right"; break;
            case "down": direction = "down"; break;
        }

    }
}

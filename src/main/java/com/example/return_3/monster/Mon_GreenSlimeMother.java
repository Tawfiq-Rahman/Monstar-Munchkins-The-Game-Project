package com.example.return_3.monster;

import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;
import com.example.return_3.object.*;

import java.util.Random;

public class Mon_GreenSlimeMother extends Entity {
    Game game;
    String imgName;
    int speedCounter=0;
    public Mon_GreenSlimeMother(Game game,int area) {
        super(game);
        this.game=game;
        monster_area=area;
        monster_type=type_slimeMother;
        name = "Green Slime Mother";
        defaultSpeed = 1;
        speed = defaultSpeed;
        type = type_monster;
        maxLife = 100;
        coin = 500;
        life = maxLife;
        attack = 10;
        defense = 0;
        exp = 50;
        //projectile=new OBJ_Rock(game);

        //set the SOLID AREA for  collision part
        solidArea.setX(4);
        solidArea.setY(32);
        solidArea.setWidth(92);
        solidArea.setHeight(64);
        solidAreaDefaultX = (int)(solidArea.getX());
        solidAreaDefaultY = (int)(solidArea.getY());
        imgName="greenMon";
        getImage();
    }
    public void getImage(){
        int size=game.tileSize*3;
        up1 = loadImage( "/monster/" +

                imgName +
                "_up_1.png",size,size);
        up2 = loadImage("/monster/" +
                imgName +
                "_up_2.png",size,size);
        down1 = loadImage("/monster/" +
                imgName +
                "_down_1.png",size,size);
        down2 = loadImage("/monster/" +
                imgName +
                "_down_2.png",size,size);
        left1 =loadImage ("/monster/" +
                imgName+
                "_left_1.png",size,size);
        left2 = loadImage("/monster/" +
                imgName+
                "_left_2.png",size,size);
        right1 = loadImage("/monster/" +
                imgName+
                "_right_1.png",size,size);
        right2 = loadImage("/monster/" +
                imgName+
                "_right_2.png",size,size);
    }


    public void setAction(){
        // Get a random direction...
        getRandomDirection();
        //checkShootOrNot(100, 30);
        speedCounter++;
        if(speedCounter>185&&speedCounter<200){
            speed=2;
        }else if(speedCounter>200&&speedCounter<220){
            speed=3;
        }else if(speedCounter>220&&speedCounter<235){
            speed=2;
        }
        else if (speedCounter>235) {
            speed=defaultSpeed;
            speedCounter=0;
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

    public void checkDrop() {
        dropItem(new OBJ_Fireball(game));
    }
}

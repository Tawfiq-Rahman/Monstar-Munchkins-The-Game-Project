package com.example.return_3.monster;

import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;
import com.example.return_3.object.*;

import java.util.Random;

public class Mon_Worm extends Entity {
    Game game;
    private String imgName;
    private int size;
    public Mon_Worm(Game game,int area) {
        super(game);
        this.game=game;
        monster_area=area;
        monster_type=type_worm;
        name = "Worm";
        defaultSpeed = 1;
        speed = defaultSpeed;
        type = type_monster;
        maxLife = 3;
        coin = 10;
        life = maxLife;
        attack = 3;
        defense = 0;
        exp = 2;
        projectile=new OBJ_Rock(game);

        //set the SOLID AREA for  collision part
        solidArea.setX(1);
        solidArea.setY(10);
        solidArea.setWidth(30);
        solidArea.setHeight(23);
        solidAreaDefaultX = (int)(solidArea.getX());
        solidAreaDefaultY = (int)(solidArea.getY());
        imgName="worm";
        size=game.tileSize;
        getImage();
    }
    public void getImage(){
//        up1 = loadImage( "/monster/" +
//
//                imgName +
//                "_up_1.png",game.tileSize,game.tileSize);
//        up2 = loadImage("/monster/" +
//                imgName +
//                "_up_2.png",game.tileSize,game.tileSize);
//        down1 = loadImage("/monster/" +
//                imgName +
//                "_down_1.png",game.tileSize,game.tileSize);
//        down2 = loadImage("/monster/" +
//                imgName +
//                "_down_2.png",game.tileSize,game.tileSize);
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

            actionLookCounter++;
            Random random= new Random();
            int actionLookCounterLimit = random.nextInt(50)+90;

            if(actionLookCounter > actionLookCounterLimit){   // for two seconds it means

                int i=random.nextInt(100)+1; //we add 1 because otherwise it will catch 0 to 99.. we want to avoid 0 here
                if(i<=50){
                    direction="left";
                }
                if(i>50&&i<=100){
                    direction="right";
                }
                actionLookCounter=0;
            }
    }


    public void damageReaction(){
        actionLookCounter = 0;
        switch (game.player.direction){
            case "left": direction = "right"; break;
            case "right": direction = "left"; break;
        }
    }

}

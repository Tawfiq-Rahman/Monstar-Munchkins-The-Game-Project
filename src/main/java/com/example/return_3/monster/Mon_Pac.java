package com.example.return_3.monster;

import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;
import com.example.return_3.object.*;

import java.util.Random;

public class Mon_Pac extends Entity {
    Game game;
    String imgName;
    int speedCounter=0;
    public Mon_Pac(Game game,int area) {
        super(game);
        this.game=game;
        monster_area=area;
        monster_type=type_pacman;
        name = "Pac Red";
        defaultSpeed = 1;
        coin = 50;
        speed = defaultSpeed;
        type = type_monster;
        maxLife = 10;
        life = maxLife;
        attack = 10;
        defense = 0;
        exp = 4;
        projectile=new OBJ_Rock(game);

        //set the SOLID AREA for  collision part
        solidArea.setX(1);
        solidArea.setY(10);
        solidArea.setWidth(30);
        solidArea.setHeight(23);
        solidAreaDefaultX = (int)(solidArea.getX());
        solidAreaDefaultY = (int)(solidArea.getY());
        imgName="pacMon";
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

        if (onPath == true) {

            // Check if id stop chasing...
            checkStopChasingOrNot(game.player, 12, 100);

            // Search the direction to go...
            searchPath(getGoalCol(game.player), getGoalRow(game.player));

            // Check if shoot a projectile...
            //checkShootOrNot(200, 30);
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
        else {
            // Check if it starts chasing...
            checkStartChasingOrNot(game.player, 8, 100);

            // Get a random direction...
            getRandomDirection();
        }

    }


    public void damageReaction(){
        actionLookCounter = 0;
        switch (game.player.direction){
            case "up": direction = "down"; break;
            case "left": direction = "right"; break;
            case "right": direction = "left"; break;
            case "down": direction = "up"; break;
        }
    }

    public void checkDrop(){
        //CAST A DIE
        int i = new Random().nextInt(100)+1;
        //SET THE MONSTER DROP
        if (i < 25){
            dropItem(new OBJ_Potion_Red(game));
        }else if (i < 50){
            dropItem(new OBJ_PowerPotion(game));
        }else if (i < 75){
            dropItem(new OBJ_SpeedPotion(game));
        }else if (i < 100){
            dropItem(new OBJ_DefensePotion(game));
        }
    }
}

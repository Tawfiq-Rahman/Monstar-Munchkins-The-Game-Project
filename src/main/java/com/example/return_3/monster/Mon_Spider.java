package com.example.return_3.monster;

import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;
import com.example.return_3.object.*;

import java.util.Random;

public class Mon_Spider extends Entity {
    Game game;
    String imgName;
    public Mon_Spider(Game game,int area) {
        super(game);
        this.game=game;
        monster_area=area;
        monster_type=type_spider;
        name = "Spider Monster";
        defaultSpeed = 2;
        speed = defaultSpeed;
        type = type_monster;
        maxLife = 15;
        coin = 80;
        life = maxLife;
        attack = 10;
        defense = 0;
        exp = 3;
        projectile=new OBJ_Spider_Shots(game);

        //set the SOLID AREA for  collision part
        solidArea.setX(1);
        solidArea.setY(10);
        solidArea.setWidth(30);
        solidArea.setHeight(23);
        solidAreaDefaultX = (int)(solidArea.getX());
        solidAreaDefaultY = (int)(solidArea.getY());
        imgName="spider";
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

            // Check if stop chasing...
            checkStopChasingOrNot(game.player, 10, 100);

            // Search the direction to go...
            searchPath(getGoalCol(game.player), getGoalRow(game.player));

            // Check if shoot a projectile...
            //checkShootOrNot(100, 30);

        }
        else {
            // Check if it starts chasing...
            checkStartChasingOrNot(game.player, 5, 100);

            // Get a random direction...
            getRandomDirection();
        }
    }

    public void getRandomDirection() {
        actionLookCounter++;
        Random random= new Random();
        int actionLookCounterLimit = random.nextInt(50)+90;

        if(actionLookCounter > actionLookCounterLimit){   // for two seconds it means

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

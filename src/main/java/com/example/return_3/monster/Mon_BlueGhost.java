package com.example.return_3.monster;

import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;
import com.example.return_3.object.*;

import java.util.Random;

public class Mon_BlueGhost extends Entity {
    Game game;
    String imgName;
    public Mon_BlueGhost(Game game,int area) {
        super(game);
        this.game=game;
        monster_area=area;
        monster_type=type_blueGhost;
        name = "Blue Ghost";
        defaultSpeed = 1;
        speed = defaultSpeed;
        type = type_monster;
        maxLife = 40;
        coin = 100;
        life = maxLife;
        attack = 15;
        defense = 0;
        exp = 5;
        projectile=new OBJ_Ghost_Dirt(game);

        //set the SOLID AREA for  collision part
        solidArea.setX(1);
        solidArea.setY(10);
        solidArea.setWidth(30);
        solidArea.setHeight(23);
        solidAreaDefaultX = (int)(solidArea.getX());
        solidAreaDefaultY = (int)(solidArea.getY());
        imgName="blueGhost";
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
                "_down_1.png",game.tileSize,game.tileSize);
        left2 = loadImage("/monster/" +
                imgName+
                "_down_2.png",game.tileSize,game.tileSize);
        right1 = loadImage("/monster/" +
                imgName+
                "_down_1.png",game.tileSize,game.tileSize);
        right2 = loadImage("/monster/" +
                imgName+
                "_down_2.png",game.tileSize,game.tileSize);
    }


    public void setAction(){
        checkShootOrNot(100, 30);
        if (onPath == true) {

            // Check if stop chasing...
            checkStopChasingOrNot(game.player, 10, 100);

            // Search the direction to go...
            searchPath(getGoalCol(game.player), getGoalRow(game.player));

            // Check if shoot a projectile...


        }
        else {
            // Check if it starts chasing...
            checkStartChasingOrNot(game.player, 5, 100);

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

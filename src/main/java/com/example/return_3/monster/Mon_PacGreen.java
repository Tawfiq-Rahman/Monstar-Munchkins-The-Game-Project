package com.example.return_3.monster;

import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;
import com.example.return_3.object.OBJ_Coin;
import com.example.return_3.object.OBJ_Heart;
import com.example.return_3.object.OBJ_Potion_Red;
import com.example.return_3.object.OBJ_Rock;

import java.util.Random;

public class Mon_PacGreen extends Entity {
    Game game;
    String imgName;
    public Mon_PacGreen(Game game,int area) {
        super(game);
        this.game=game;
        monster_area=area;
        monster_type=type_pacmanGreen;
        name = "Pac Green";
        defaultSpeed = 1;
        coin = 30;
        speed = defaultSpeed;
        type = type_monster;
        maxLife = 10;
        life = maxLife;
        attack = 5;
        defense = 0;
        exp = 3;
        projectile=new OBJ_Rock(game);

        //set the SOLID AREA for  collision part
        solidArea.setX(1);
        solidArea.setY(10);
        solidArea.setWidth(30);
        solidArea.setHeight(23);
        solidAreaDefaultX = (int)(solidArea.getX());
        solidAreaDefaultY = (int)(solidArea.getY());
        imgName="pacMonGreen";
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
           // checkStartChasingOrNot(game.player, 5, 100);

            // Get a random direction...
            getRandomDirection();
        }

    }


    public void damageReaction(){
        actionLookCounter = 0;
        onPath=true;
        switch (game.player.direction){
            case "up": direction = "down"; break;
            case "left": direction = "right"; break;
            case "right": direction = "left"; break;
            case "down": direction = "up"; break;
        }
    }

}

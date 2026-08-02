package com.example.return_3.monster;

import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;
import com.example.return_3.object.*;

import java.util.Random;

public class Mon_ORC extends Entity {
    Game game;
    public Mon_ORC(Game game,int area) {
        super(game);
        this.game=game;
        monster_area=area;
        name = "ORC";
        defaultSpeed = 1;
        speed = defaultSpeed;
        type = type_OrcMonster;
        monster_type=type_arc;
        maxLife = 50;
        coin = 100;
        life = maxLife;
        attack = 20;    // Sword power will be 5 for player...
        defense = 0;
        exp = 8;
        knockBackPower = 5;
        projectile=new OBJ_Rock(game);

        //set the SOLID AREA for  collision part
        solidArea.setX(4);
        solidArea.setY(4);
        solidArea.setWidth(28);
        solidArea.setHeight(30);
        solidAreaDefaultX = (int)(solidArea.getX());
        solidAreaDefaultY = (int)(solidArea.getY());
        attackArea.setWidth(32);
        attackArea.setHeight(32);

        motion1_duration = 40;
        motion2_duration = 85;

        loadMonsterImages();
        loadMonsterAttackImages();

    }


    public void loadMonsterImages(){
        up1 = loadImage( "/monster/orc_up_1.png",game.tileSize,game.tileSize);
        up2 = loadImage("/monster/orc_up_2.png",game.tileSize,game.tileSize);
        down1 = loadImage("/monster/orc_down_1.png",game.tileSize,game.tileSize);
        down2 = loadImage("/monster/orc_down_2.png",game.tileSize,game.tileSize);
        left1 =loadImage ("/monster/orc_left_1.png",game.tileSize,game.tileSize);
        left2 = loadImage("/monster/orc_left_2.png",game.tileSize,game.tileSize);
        right1 = loadImage("/monster/orc_right_1.png",game.tileSize,game.tileSize);
        right2 = loadImage("/monster/orc_right_2.png",game.tileSize,game.tileSize);
    }

    private void loadMonsterAttackImages() {
        attackUp1 = loadImage("/monster/orc_attack_up_1.png", game.tileSize, game.tileSize * 2);
        attackUp2 = loadImage("/monster/orc_attack_up_2.png", game.tileSize, game.tileSize * 2);
        attackDown1 = loadImage("/monster/orc_attack_down_1.png", game.tileSize, game.tileSize * 2);
        attackDown2 = loadImage("/monster/orc_attack_down_2.png", game.tileSize, game.tileSize * 2);
        attackLeft1 = loadImage("/monster/orc_attack_left_1.png", game.tileSize * 2, game.tileSize);
        attackLeft2 = loadImage("/monster/orc_attack_left_2.png", game.tileSize * 2, game.tileSize);
        attackRight1 = loadImage("/monster/orc_attack_right_1.png", game.tileSize * 2, game.tileSize);
        attackRight2 = loadImage("/monster/orc_attack_right_2.png", game.tileSize * 2, game.tileSize);

    }


    public void setAction(){

        if (onPath == true) {

            // Check if id stop chasing...
            checkStopChasingOrNot(game.player, 10, 100);

            // Search the direction to go...
            searchPath(getGoalCol(game.player), getGoalRow(game.player));

        }
        else {

            // Check if it starts chasing...
            checkStartChasingOrNot(game.player, 5, 100);

            // Get a random direction...
            getRandomDirection();

            // Check if it attacks...
            if (attacking == false) {   // make monster aggressive by deceasing the rate...
                checkAttackOrNot(30, game.tileSize * 4, game.tileSize);
            }
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

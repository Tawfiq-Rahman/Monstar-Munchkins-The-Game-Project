package com.example.return_3.npc;

import com.example.return_3.entity.InteractNPC;
import com.example.return_3.main.Game;

public class InteractNPC_Welcome extends InteractNPC {
    Game game;
    int size;
    public InteractNPC_Welcome(Game game) {
        super(game);
        this.game=game;
        type= type_npc;
        speed=1;
        getNPCImage();
        setDialogue();
        goalCol=84;
        goalRow=113;
        size=game.tileSize;

    }
    public void checkCollision(){
        collisionOn=false;
        //CHeching part of collision so that entity got collision and can not move
        game.cChecker.checkTile(this);
        game.cChecker.checkPlayer(this);
//        gp.cChecker.checkObject(this,false); //its not player so its remain false
        game.cChecker.checkEntity(this,game.npc);
        game.cChecker.checkEntity(this,game.monster);
        game.cChecker.checkEntity(this,game.iTile);



    }
    public void getNPCImage(){
        up1=loadImage( "/npc/oldman_up_1.png",game.tileSize,game.tileSize);
        up2= loadImage("/npc/oldman_up_2.png",game.tileSize,game.tileSize);
        down1= loadImage("/npc/oldman_down_1.png",game.tileSize,game.tileSize);
        down2= loadImage("/npc/oldman_down_2.png",game.tileSize,game.tileSize);
        left1=loadImage ("/npc/oldman_left_1.png",game.tileSize,game.tileSize);
        left2= loadImage("/npc/oldman_left_2.png",game.tileSize,game.tileSize);
        right1= loadImage("/npc/oldman_right_1.png",game.tileSize,game.tileSize);
        right2= loadImage("/npc/oldman_right_2.png",game.tileSize,game.tileSize);

    }
    //set dialogue
    public void setDialogue(){
        dialogue[0] = "Greetings, brave hunter!\nWelcome to the island. Our island is in grave danger. Monsters have overrun our home.";
        dialogue[1] = "Your mission is to kill all the monsters and set us free.\nHere are the guidelines to help you:";
        dialogue[2] = "1. Use W/A/S/D or Arrow Keys to move.\n2. Press Enter to interact with game properties.";
        dialogue[3] = "3. Press Space to attack, V to defend.\n4. Press C to see your inventory.";
        dialogue[4] = "I will give you a sword. This sword will help you to continue your mission.\nPlease, we are counting on you to save us.\nGood luck, brave hunter!";
    }

    public void getRandomDirection(){
        if(npcGone==false) {
            super.getRandomDirection();
            uTool.areaSetup(this, 80 * size, 116 * size, 8 * size, 5 * size);//88,121
        }
    }
    public void setAction(){
//        System.out.println(onPath);
        if (onPath == true) {
            // Check if stop chasing...
            checkStopChasingOrNot(game.player, 10, 100);
            // Search the direction to go...
            if(npcGoneCommand==false) {

                searchPath(getGoalCol(game.player), getGoalRow(game.player));
                uTool.areaSetup(this, 80 * size, 116 * size, 8 * size, 5 * size);//88,121
            }
            else{
                searchPath(goalCol, goalRow);
                if(onPath==false){
                    npcGone=true;
                    game.npcWelcome=true;
                    //                System.out.println("NPC WORK DONE");
                }
            }
            // Check if shoot a projectile...
        }
        else {
            // Check if it starts chasing...
            checkStartChasingOrNot(game.player, 5, 100);
            // Get a random direction...
            getRandomDirection();
        }
    }

}

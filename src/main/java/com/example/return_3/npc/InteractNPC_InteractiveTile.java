package com.example.return_3.npc;

import com.example.return_3.entity.InteractNPC;
import com.example.return_3.main.Game;

public class InteractNPC_InteractiveTile extends InteractNPC {
    Game game;
    int size;
    public InteractNPC_InteractiveTile(Game game) {
        super(game);
        this.game=game;
        type= type_npc;
        speed=1;
        getNPCImage();
        setDialogue();
        goalCol=167;
        goalRow=150;
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
        up1=loadImage( "/npc/npc_oldman_up_1.png",game.tileSize,game.tileSize);
        up2= loadImage("/npc/npc_oldman_up_2.png",game.tileSize,game.tileSize);
        down1= loadImage("/npc/npc_oldman_down_1.png",game.tileSize,game.tileSize);
        down2= loadImage("/npc/npc_oldman_down_2.png",game.tileSize,game.tileSize);
        left1=loadImage ("/npc/npc_oldman_left_1.png",game.tileSize,game.tileSize);
        left2= loadImage("/npc/npc_oldman_left_2.png",game.tileSize,game.tileSize);
        right1= loadImage("/npc/npc_oldman_right_1.png",game.tileSize,game.tileSize);
        right2= loadImage("/npc/npc_oldman_right_2.png",game.tileSize,game.tileSize);

    }
    //set dialogue
    public void setDialogue(){
        dialogue[0] = "Hello, brave hunter! I see you’re venturing deep into our island."+
                    "\nIn this game, you will come across many dry trees and breakable rocks."+
                    "\nYou can break rocks with a Tomahawk and cut trees with an axe.";
        dialogue[1] = "If you don't have an axe yet, you can find one in the downward colony."+
                    "\nBut be careful—the monsters around there are fierce. Stay alert and stay safe!";
    }
    public void getRandomDirection(){
        if(npcGone==false) {
            super.getRandomDirection();//106,80
//            uTool.areaSetup(this, 97 * size, 71 * size, 9 * size, 9 * size);
            uTool.areaSetup(this, 168 * size, 135 * size, 15 * size, 10 * size); //183,145

        }
    }
//    public void setAction(){
//        if(onPath==true){
//            searchPath(goalCol, goalRow);
//            if(onPath==false){
//                npcGone=true;
//                System.out.println("NPC WORK DONE");
//            }
//        } else  {
//            getRandomDirection();
//        }
//    }
    public void setAction(){
//        System.out.println(onPath);
        if (onPath == true) {
            // Check if stop chasing...
            checkStopChasingOrNot(game.player, 10, 100);
            // Search the direction to go...
            if(npcGoneCommand==false) {

            searchPath(getGoalCol(game.player), getGoalRow(game.player));
                uTool.areaSetup(this, 168 * size, 135 * size, 15 * size, 10 * size); //183,145
            }
            else{
                searchPath(goalCol, goalRow);
                if(onPath==false){
                    npcGone=true;
                    game.npcAxe=true;
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

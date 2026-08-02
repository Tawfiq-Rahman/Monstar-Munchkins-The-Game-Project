package com.example.return_3.npc;

import com.example.return_3.entity.InteractNPC;
import com.example.return_3.main.Game;

public class InteractNPC_FireBallGiver extends InteractNPC {
    Game game;
    int size;
    public InteractNPC_FireBallGiver(Game game) {
        super(game);
        this.game=game;
        type= type_npc;
        speed=1;
        getNPCImage();
        setDialogue();
        goalCol=128;
        goalRow=153;
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
        dialogue[0] = "Thank you for saving our island from those dreadful monsters, brave hunter. We are forever in your debt.\n"+
                "However, a new threat has emerged. A fearsome RedFly monster that hurls fireballs is terrorizing our land.\n"+
                "Ordinary weapons won't suffice against this beast. You will need something powerful to counter its flames.";
        dialogue[1] = "Here,I am giving you a Fireball take this fireball. Use it wisely and with courage. \n" +
                "It’s our only hope against the RedFly monster.\n"+
                "May fortune favor you in this battle. Good luck, brave hunter!";
    }
    public void getRandomDirection(){
        if(npcGone==false) {
            super.getRandomDirection();//106,80
//            uTool.areaSetup(this, 97 * size, 71 * size, 9 * size, 9 * size);
            uTool.areaSetup(this, 97 * size, 148 * size, 24 * size, 9 * size); //121,155

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
                uTool.areaSetup(this, 97 * size, 148 * size, 24 * size, 9 * size); //121,155
            }
            else{
                searchPath(goalCol, goalRow);
                if(onPath==false){
                    npcGone=true;
                    game.npcFireball=true;
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

package com.example.return_3.npc;

import com.example.return_3.entity.InteractNPC;
import com.example.return_3.main.Game;

public class InteractNPC_GlobalChat extends InteractNPC {
    Game game;
    int size;
    public InteractNPC_GlobalChat(Game game) {
        super(game);
        this.game=game;
        type= type_npc;
        speed=1;
        getNPCImage();
        setDialogue();
        goalCol=24; //21
        goalRow=171;  //179
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
        dialogue[0] = "Greetings, brave hunter! This door behind me leads to a special area where you can some loot\n"+
        "Unfortunately, the door is locked and requires a Yellow Key to open."+
         "\nThe key was lost somewhere on this island. Many have searched for it, but none have succeeded so far.";
        dialogue[1] = "If you manage to find the Yellow Key, you will be able to unlock the door and chat with others inside."+
                "\nYou can communicate with other hunter by pressing 'G'"+
         "\nKeep your eyes peeled and explore every corner of the island. The key could be hidden anywhere.";

                dialogue[2]="\nGood luck on your search! And be cautious—the island is filled with dangerous creatures. Stay safe, hunter!";
    }


    public void getRandomDirection(){
        if(npcGone==false) {
            super.getRandomDirection();//106,80
//            uTool.areaSetup(this, 97 * size, 71 * size, 9 * size, 9 * size);
            uTool.areaSetup(this, 22 * size, 161 * size, 24 * size, 2 * size); //41,163

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
            uTool.areaSetup(this, 22 * size, 161 * size, 24 * size, 2 * size); //41,163
        }
        else{
            searchPath(goalCol, goalRow);
            if(onPath==false){
                npcGone=true;
                game.npcGlobalChat=true;
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

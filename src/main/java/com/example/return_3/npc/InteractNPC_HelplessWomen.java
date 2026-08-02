package com.example.return_3.npc;

import com.example.return_3.entity.InteractNPC;
import com.example.return_3.main.Game;

public class InteractNPC_HelplessWomen extends InteractNPC {
    Game game;
    int size;
    public InteractNPC_HelplessWomen(Game game) {
        super(game);
        this.game=game;
        type= type_npc;
        speed=1;
        getNPCImage();
        setDialogue();
        goalCol=93;
        goalRow=68;
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
        up1=loadImage( "/npc/npc_girl2_up_1.png",game.tileSize,game.tileSize);
        up2= loadImage("/npc/npc_girl2_up_2.png",game.tileSize,game.tileSize);
        down1= loadImage("/npc/npc_girl2_down_1.png",game.tileSize,game.tileSize);
        down2= loadImage("/npc/npc_girl2_down_2.png",game.tileSize,game.tileSize);
        left1=loadImage ("/npc/npc_girl2_left_1.png",game.tileSize,game.tileSize);
        left2= loadImage("/npc/npc_girl2_left_2.png",game.tileSize,game.tileSize);
        right1= loadImage("/npc/npc_girl2_right_1.png",game.tileSize,game.tileSize);
        right2= loadImage("/npc/npc_girl2_right_2.png",game.tileSize,game.tileSize);

    }
    //set dialogue
    public void setDialogue(){
        dialogue[0]="Oh, thank goodness you're here! You saved us from those terrifying monsters. I don't know how to thank \nyou enough," +
                "brave hunter. Please, stay alert—those creatures can appear when you least expect them.\n" +
                " And take care of yourself." ;
        dialogue[1]=" I've heard whispers of a mysterious island, shrouded in fog. They say a giant slime monster dwells there. \nIf you can defeat it,all the slimes on this island might perish too." ;
//        dialogue[2]="1. Use W/A/S/D or Arrow Keys to move.\n2. Press Enter to interact with game properties.\n" ;
//        dialogue[3]="3. Press Space to attack, V to defend.\n4. Press C to see your inventory.\n" ;
//        dialogue[4]="Please, we are counting on you to save us.\n Good luck, brave hunter!";
    }
    public void getRandomDirection(){
        if(npcGone==false) {
            super.getRandomDirection();//106,80
//            uTool.areaSetup(this, 97 * size, 71 * size, 9 * size, 9 * size);
            uTool.areaSetup(this, 93 * size, 69 * size, 20 * size, 15 * size); //113,84

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
            uTool.areaSetup(this, 93 * size, 69 * size, 20 * size, 15 * size); //113,84
            }
            else{
                searchPath(goalCol, goalRow);
                if(onPath==false){
                    npcGone=true;
                    game.npcMotherSlime=true;
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

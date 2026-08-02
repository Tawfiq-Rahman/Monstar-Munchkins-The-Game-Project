package com.example.return_3.npc;

import com.example.return_3.entity.InteractNPC;
import com.example.return_3.main.Game;

public class InteractNPC_ShipOtherSide extends InteractNPC {
    Game game;
    public InteractNPC_ShipOtherSide(Game game) {
        super(game);
        name="otherSide";
        this.game=game;
        type= type_npc;
        speed=1;
        solidArea.setX(0);
        solidArea.setY(0);
        solidArea.setWidth(32);
        solidArea.setHeight(32);
        solidAreaDefaultX=(int)(solidArea.getX());
        solidAreaDefaultY=(int)(solidArea.getY());
        getNPCImage();
        setDialogue();
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
        up1=loadImage( "/npc/npc_townHall.png",game.tileSize,game.tileSize);
//        up2= loadImage("/npc/oldman_up_2.png",game.tileSize,game.tileSize);
//        down1= loadImage("/npc/oldman_down_1.png",game.tileSize,game.tileSize);
//        down2= loadImage("/npc/oldman_down_2.png",game.tileSize,game.tileSize);
//        left1=loadImage ("/npc/oldman_left_1.png",game.tileSize,game.tileSize);
//        left2= loadImage("/npc/oldman_left_2.png",game.tileSize,game.tileSize);
//        right1= loadImage("/npc/oldman_right_1.png",game.tileSize,game.tileSize);
//        right2= loadImage("/npc/oldman_right_2.png",game.tileSize,game.tileSize);
        up2=up1;
        down1=up1;
        down2= up1;
        left1=up1;
        left2= up1;
        right1= up1;
        right2= up1;
    }
    //set dialogue
    public void setDialogue(){
        dialogue[0]="Welcome hunter. Nice to see you again.";
    }
    public void update(){
        if(type==type_npc){
            int xDistance=Math.abs(worldX-game.player.worldX);
            int yDistance=Math.abs(worldY-game.player.worldY);
            int tileDistance=(xDistance+yDistance)/game.tileSize;

            if(tileDistance<3){
                chatOnStatus=true;
            }else{
                chatOnStatus=false;
            }
            if( chatOnStatus==true){
                chatCounter++;
                if (chatCounter <15 ) {
                    chatNum=1;
                } else if (chatCounter >=15&& chatCounter<30 ) {
                    chatNum=2;
                } else if (chatCounter >=30&& chatCounter<45 ) {
                    chatNum=3;
                } else if (chatCounter >=45&& chatCounter<60 ) {
                    chatNum=4;
                }else {
                    chatCounter = 0;
                }
            }
        }
    }
    public void speak(){
        game.playSoundEffect(game.soundEffect.popUp);
        game.gameState = game.shipTeleportState;
        game.ui.uiMainGame.npc=this;
        game.ui.uiMainGame.currentDialogue=dialogue[dialogueIndex];
    }
}
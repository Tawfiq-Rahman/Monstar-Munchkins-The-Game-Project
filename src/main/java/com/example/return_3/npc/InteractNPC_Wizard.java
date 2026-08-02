package com.example.return_3.npc;

import com.example.return_3.entity.InteractNPC;
import com.example.return_3.main.Game;

public class InteractNPC_Wizard extends InteractNPC {
    Game game;
    public InteractNPC_Wizard(Game game) {
        super(game);
        this.game=game;
        type= type_wizard;
        name="wizard";
        getNPCImage();
        setDialogue();

    }
    public void getNPCImage(){
        up1=loadImage( "/npc/npc1_up_1.png",game.tileSize,game.tileSize);
        up2= loadImage("/npc/npc1_up_2.png",game.tileSize,game.tileSize);
        down1= loadImage("/npc/npc1_down_1.png",game.tileSize,game.tileSize);
        down2= loadImage("/npc/npc1_down_2.png",game.tileSize,game.tileSize);
        left1=loadImage ("/npc/npc1_left_1.png",game.tileSize,game.tileSize);
        left2= loadImage("/npc/npc1_left_2.png",game.tileSize,game.tileSize);
        right1= loadImage("/npc/npc1_right_1.png",game.tileSize,game.tileSize);
        right2= loadImage("/npc/npc1_right_2.png",game.tileSize,game.tileSize);
    }
    public void setDialogue(){
        dialogue[0]="Hello little fellow!\nHow could i help you?";
    }
    public void speak(){
        super.speak();
        game.gameState=game.wizConversationState;
        game.ui.uiMainGame.npc=this;
        //onPath=true;
    }

}
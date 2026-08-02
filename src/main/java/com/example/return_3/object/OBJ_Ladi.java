package com.example.return_3.object;

import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;

public class OBJ_Ladi extends Entity {
    Game game;
    public OBJ_Ladi(Game game) {
        super(game);
        this.game = game;
        //Type will erite here later
        name="Red Potion";
        price =120;
        down1=loadImage("/objects/test_ladi.png",game.tileSize,game.tileSize);
        description="["+name+"]\nHeals your life by "+value+".";
    }
//    public void use(Entity entity){
//        game.gameState=game.dialogueState;
//        game.ui.uiMainGame.currentDialogue="You drink the "+name+"!\nYour life has been recovered by "+value+",";
//        entity.life+=value;
//
//        //gp.playSE(2);
//    }
}


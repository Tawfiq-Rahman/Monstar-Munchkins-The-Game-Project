package com.example.return_3.object;

import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;

public class OBJ_Potion_Red extends Entity {
    Game game;

    public OBJ_Potion_Red(Game game) {
        super(game);
        this.game=game;
        type= type_consumable;
        name="Red Potion";
        itemCode=303;
        value =20;
        price=50;
        exp = 5;
        down1=loadImage("/objects/potion_red.png",game.tileSize,game.tileSize);
        description="["+name+"]\nHeals your life by "+value+".";
    }

    public boolean use(Entity entity) {
        game.gameState = game.playState;
//        game.ui.uiMainGame.currentDialogue="You drink the "+name+"!\nYour life has been recovered by "+value+",";
        game.ui.uiMainGame.addMessage("You drink the "+name+"!\nYour life has been recovered by "+value+",");
        entity.life+=value;
        return true;
    }
}

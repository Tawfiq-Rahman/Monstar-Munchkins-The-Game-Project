package com.example.return_3.object.food;

import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;

public class OBJ_Meat extends Entity {
    Game game;
    public OBJ_Meat(Game game) {
        super(game);
        this.game = game;
        //Type will write here later
        type = type_consumable;
        name ="Meat";
        value = 100;
        exp = 3;
        itemCode = 208;
        price = value;
        down1 = uTool.loadImage("/objects/food/legPiece.png",game.tileSize-7,game.tileSize-7);
        description = "This is " + name + ". It can heal\nyou a little bit.";
    }
    public boolean use(Entity entity){
        game.ui.uiMainGame.addMessage("Life gained: "+(int) (value * 0.30));
        game.player.life += (int) (value * 0.30);
        return true;
    }
}

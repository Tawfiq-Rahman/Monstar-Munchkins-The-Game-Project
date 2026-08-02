package com.example.return_3.object;

import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;

public class OBJ_Shield_Blue extends Entity {
    public OBJ_Shield_Blue(Game game) {
        super(game);
        type=type_shield;
        name="Blue Sheild";
        down1=loadImage("/objects/shield_blue.png",game.tileSize,game.tileSize);
        defenseValue=2;
        price=2;
        itemCode=104;
        description="["+name+"]\nA Shiny blue shield";
        knockBackPower = 25;
    }
}

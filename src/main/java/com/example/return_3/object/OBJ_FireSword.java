package com.example.return_3.object;

import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;

public class OBJ_FireSword extends Entity {
    public OBJ_FireSword(Game game) {
        super(game);
        type = type_fireSword;
        name = "Fire Sword";
        down1 = loadImage("/objects/sword_fire.png", game.tileSize, game.tileSize);
        attackValue = 20;
        price = 2000;
        exp = 10;
        itemCode = 106;
        attackArea.setWidth(32);
        attackArea.setHeight(28);
        description="["+name+"]\nA Fire sword.";
        knockBackPower = 20;

        motion1_duration = 5;
        motion2_duration = 25;
    }
}

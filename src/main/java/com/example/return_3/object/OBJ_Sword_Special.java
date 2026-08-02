package com.example.return_3.object;

import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;

public class OBJ_Sword_Special extends Entity {
    public OBJ_Sword_Special(Game game) {
        super(game);
        type = type_specialSword;
        name = "Sword_Special";
        down1 = loadImage("/objects/sword_special.png", game.tileSize, game.tileSize);
        attackValue = 5;
        price = 500;
        exp = 10;
        itemCode = 105;
        attackArea.setWidth(32);
        attackArea.setHeight(28);
        description="["+name+"]\nA special sword.";
        knockBackPower = 15;

        motion1_duration = 5;
        motion2_duration = 35;
    }
}

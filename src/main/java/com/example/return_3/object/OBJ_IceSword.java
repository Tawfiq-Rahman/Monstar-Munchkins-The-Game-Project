package com.example.return_3.object;

import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;

public class OBJ_IceSword extends Entity {
    public OBJ_IceSword(Game game) {
        super(game);
        type = type_iceSword;
        name = "Ice Sword";
        down1 = loadImage("/objects/sword_ice.png", game.tileSize, game.tileSize);
        attackValue = 15;
        exp = 10;
        price = 1500;
        itemCode = 108;
        attackArea.setWidth(32);
        attackArea.setHeight(28);
        description="["+name+"]\nAn Ice sword.";
        knockBackPower = 20;

        motion1_duration = 5;
        motion2_duration = 25;
    }
}

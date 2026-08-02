package com.example.return_3.object;

import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;

public class OBJ_Sword_Normal extends Entity {

    public OBJ_Sword_Normal(Game game) {
        super(game);
        type=type_sword;
        name = "Sword_Normal";
        down1 = loadImage("/objects/sword_normal.png", game.tileSize, game.tileSize);
        attackValue = 1;
        price = 100;
        exp = 5;
        itemCode =101;
        attackArea.setWidth(32);
        attackArea.setHeight(28);
        description="["+name+"]\nAn old sword";
        knockBackPower = 10;

        motion1_duration = 5;
        motion2_duration = 25;
    }
}

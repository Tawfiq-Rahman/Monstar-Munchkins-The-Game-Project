package com.example.return_3.object;

import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;

public class OBJ_Tomahawk extends Entity {
    public OBJ_Tomahawk(Game game) {
        super(game);
        type = type_tomahawkAxe;
        name = "Tomahawk Axe";
        down1 = loadImage("/objects/axe1.png",game.tileSize,game.tileSize);
        price = 150;
        exp = 3;
        itemCode = 109;
        attackValue = 2;
        life = 15;
        attackArea.setWidth(15);
        attackArea.setHeight(15);
        description="["+name+"]\nA bit rusty but still can \nbreak some stones";
        knockBackPower = 15;

        motion1_duration = 20;
        motion2_duration = 40;
    }
}

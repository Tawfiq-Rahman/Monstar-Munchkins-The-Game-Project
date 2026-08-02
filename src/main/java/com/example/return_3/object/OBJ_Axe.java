package com.example.return_3.object;

import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;

public class OBJ_Axe extends Entity {

    public OBJ_Axe(Game game) {
        super(game);
        type =type_axe;
        name="Woodcutter's Axe";
        down1=loadImage("/objects/axe.png",game.tileSize,game.tileSize);
        price=100;
        itemCode=102;
        attackValue=2;
        life=15;
        exp = 3;
        attackArea.setWidth(15);
        attackArea.setHeight(15);
        description="["+name+"]\nA bit rusty but still can \ncut some trees";
        knockBackPower = 15;

        motion1_duration = 20;
        motion2_duration = 40;
    }
}

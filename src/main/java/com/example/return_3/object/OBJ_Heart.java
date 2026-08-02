package com.example.return_3.object;

import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;

public class OBJ_Heart extends Entity {

    Game game;
    public OBJ_Heart(Game game){

        super(game);
        this.game = game;
        type = type_pickupOnly;
        name="Heart";
        value=30;
        itemCode=302;
        down1 = loadImage("/objects/heart_full.png",game.tileSize,game.tileSize);
        image1 = loadImage("/objects/heart_full.png",game.tileSize,game.tileSize);
        image2 = loadImage("/objects/heart_half.png",game.tileSize,game.tileSize);
        image3 = loadImage("/objects/heart_blank.png",game.tileSize,game.tileSize);

    }
    public boolean use(Entity entity){
        //gp.playSE(2);
        game.ui.uiMainGame.addMessage("Life + "+value);
        entity.life += value;
        if(game.player.life>game.player.maxLife){
            game.player.life=game.player.maxLife;
        }
        return true;
    }
}

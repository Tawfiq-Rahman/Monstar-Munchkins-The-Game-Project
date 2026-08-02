package com.example.return_3.object;

import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;
import com.example.return_3.main.UtilityTool;

public class OBJ_HugeCoin extends Entity {
    Game game;
    UtilityTool uTool =new UtilityTool();
    public OBJ_HugeCoin(Game game) {
        super(game);
        this.game = game;
        //Type will erite here later
        type= type_pickupOnly;
        name="Coin";
        value =500;
        itemCode=309;
        down1=uTool.loadImage("/objects/coin.png",game.tileSize-7,game.tileSize-7);
        description="this is "+name;
    }
    public boolean use(Entity entity){
        game.ui.uiMainGame.addMessage("Coin: "+value);
        game.player.coin+=value;
        return true;
    }
}

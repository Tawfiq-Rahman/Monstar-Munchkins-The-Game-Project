package com.example.return_3.object;

import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;

public class OBJ_SpeedPotion extends Entity{
    Game game;

    public OBJ_SpeedPotion(Game game) {
        super(game);
        this.game=game;
        type= type_consumable;
        name="Speed Potion";
        itemCode=307;
        value =1;
        exp = 5;
        price=50;
        down1=loadImage("/objects/speedPotion.png",game.tileSize,game.tileSize);
        description="["+name+"]\nincreses your speed by "+value+".";
    }

    public boolean use(Entity entity) {
        game.gameState = game.playState;
//        game.ui.uiMainGame.currentDialogue="You drink the "+name+"!\nYour life has been recovered by "+value+",";
        game.ui.uiMainGame.addMessage("You drink the "+name+"!\nYour speed has been increased by "+value+",\n for 10 second");
        new Thread(new Runnable() {
            @Override
            public void run() {

               entity.speed+=value;

                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }finally {
                   entity.speed-=value;
                }
            }
        }).start();
        return true;
    }
}

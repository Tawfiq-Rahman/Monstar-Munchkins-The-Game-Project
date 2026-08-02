package com.example.return_3.object;

import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;

public class OBJ_PowerPotion extends Entity {
    Game game;

    public OBJ_PowerPotion(Game game) {
        super(game);
        this.game=game;
        type= type_consumable;
        name="Power Potion";
        itemCode=305;
        value = 2;
        price = 50;
        exp = 5;
        down1=loadImage("/objects/powerPotion.png",game.tileSize,game.tileSize);
        description="["+name+"]\nIncrease you Attack by "+value+".";
    }

    public boolean use(Entity entity) {
        game.gameState = game.playState;
//        game.ui.uiMainGame.currentDialogue="You drink the "+name+"!\nYour life has been recovered by "+value+",";
        game.ui.uiMainGame.addMessage("You drink the "+name+"!\nNow your attack increased by "+value+",\n for 30 seconds");
        new Thread(new Runnable() {
            @Override
            public void run() {
                entity.strength+=value;
                if(entity.currentWeapon!=null){
                entity.getAttack();
                }
                try {
                    Thread.sleep(30000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }finally {
                   entity.strength-=value;
                    if(entity.currentWeapon!=null){
                        entity.getAttack();
                    }
                }
            }
        }).start();
        return true;
    }
}

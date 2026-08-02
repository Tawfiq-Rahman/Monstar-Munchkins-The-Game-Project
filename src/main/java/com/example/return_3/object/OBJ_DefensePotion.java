package com.example.return_3.object;

import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;

public class OBJ_DefensePotion extends Entity {
    Game game;

    public OBJ_DefensePotion(Game game) {
        super(game);
        this.game=game;
        type= type_consumable;
        name="Defense Potion";
        itemCode=306;
        value =2;
        exp = 5;
        price=50;
        down1=loadImage("/objects/defensePotion.png",game.tileSize,game.tileSize);
        description="["+name+"]\nadd defense by "+value+".\n for 30 second";
    }

    public boolean use(Entity entity) {
        game.gameState = game.playState;
//        game.ui.uiMainGame.currentDialogue="You drink the "+name+"!\nYour life has been recovered by "+value+",";
        game.ui.uiMainGame.addMessage("You drink the "+name+"!\nYou got extra "+value+" defense for 30 second,");
//        entity.life+=value;
            new Thread(new Runnable() {
                @Override
                public void run() {

                    entity.dexterity+=value;
                    if(entity.currentShield!=null){
                        entity.getDefense();
                    }else{
                        entity.defense=5*entity.dexterity;
                    }

                    try {
                        Thread.sleep(30000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }finally {
                        entity.dexterity-=value;
                        if(entity.currentShield!=null){
                            entity.getDefense();
                        }else{
                            entity.defense=0;
                        }
                    }
                }
            }).start();
        return true;
    }
}

package com.example.return_3.shop;

import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;
import com.example.return_3.object.*;

public class StuffShop extends Shop {
    Game game;
    public StuffShop(Game game) {
        super(game);
        name="Stuff shop";
        this.game = game;
        setItems();
       // setDialogue();

    }

    public void setItems(){
        inventory.add(new OBJ_Sword_Normal(game));
        inventory.add(new OBJ_Sword_Special(game));
        inventory.add(new OBJ_FireSword(game));
        inventory.add(new OBJ_IceSword(game));
        inventory.add(new OBJ_Shield_Wood(game));
        inventory.add(new OBJ_Axe(game));
        inventory.add(new OBJ_Tomahawk(game));
//        inventory.add(new OBJ_Shield_Blue(game));
        inventory.add(new OBJ_Fireball(game));
    }
    //public void setDialogue(){
//        dialogue[0]="He he, so you found me.\nI have some good stuff. Do you want to trade?";
//    }
    public void use(){
        //game.playSoundEffect(game.soundEffect.inventoryOpen);
        game.gameState= game.tradeState;
        game.ui.uiMainGame.shop=this;
    }
}

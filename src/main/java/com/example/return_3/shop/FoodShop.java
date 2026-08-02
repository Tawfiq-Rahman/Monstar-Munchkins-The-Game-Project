package com.example.return_3.shop;

import com.example.return_3.main.Game;
import com.example.return_3.object.OBJ_Axe;
import com.example.return_3.object.OBJ_Shield_Blue;
import com.example.return_3.object.OBJ_Shield_Wood;
import com.example.return_3.object.OBJ_Sword_Normal;
import com.example.return_3.object.food.*;

public class FoodShop extends Shop{
    Game game;
    public FoodShop(Game game) {
        super(game);
        name="Food shop";
        this.game = game;
        setItems();
      //  setDialogue();
    }
    public void setItems(){
        inventory.add(new OBJ_Apple(game));
        inventory.add(new OBJ_Banana(game));
        inventory.add(new OBJ_Berries(game));
        inventory.add(new OBJ_Orange(game));
        inventory.add(new OBJ_Egg(game));
        inventory.add(new OBJ_Fish(game));
        inventory.add(new OBJ_Cheese(game));
        inventory.add(new OBJ_Meat(game));
    }
//    public void setDialogue(){
//        dialogue[0]="He he, so you found me.\nI have some good stuff. Do you want to trade?";
//    }
    public void use(){
        //game.playSoundEffect(game.soundEffect.inventoryOpen);
        game.gameState= game.tradeState;
        game.ui.uiMainGame.shop=this;
    }
}

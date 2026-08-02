package com.example.return_3.shop;

import com.example.return_3.main.Game;

public class ClothShop extends Shop{
    Game game;
    public ClothShop(Game game) {
        super(game);
        this.game=game;
    }
    public void use(){
        game.gameState= game.tradeState;
        game.ui.uiMainGame.shop=this;
    }
}

package com.example.return_3.interactiveTile;

import com.example.return_3.main.Game;

public class CuttableTree_Parts extends InteractiveTile{
    Game game;
    public CuttableTree_Parts(Game game, int col, int row) {
        super(game,col,row);
        this.game=game;
        this.worldX=game.tileSize*col;
        this.worldY=game.tileSize*row;
        down1=loadImage("/tiles_interactive/trunk.png",game.tileSize,game.tileSize);
        //set no solid area for set no collision
        solidArea.setX(0);
        solidArea.setY(0);
        solidArea.setWidth(0);
        solidArea.setHeight(0);
        solidAreaDefaultX=(int)(solidArea.getX());
        solidAreaDefaultY=(int)(solidArea.getY());
    }


}

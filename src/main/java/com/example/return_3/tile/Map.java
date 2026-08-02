package com.example.return_3.tile;

import com.example.return_3.main.Game;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Map extends TileManager {
    Game game;
    Image[] worldMap;
    public boolean mainMapOn = false;

    public Map(Game game) {
        super(game);
        this.game = game;
        createWorldMap();
    }

    public void createWorldMap() {
        worldMap = new Image[game.maxMap];
        int worldMapWidth = game.tileSize * game.maxWorldCol;
        int worldMapHeight = game.tileSize * game.maxWorldRow;

        for (int i = 0; i < game.maxMap; i++) {



            Canvas canvas = new Canvas(worldMapWidth, worldMapHeight);
            GraphicsContext gc = canvas.getGraphicsContext2D();
            // You can draw on the canvas using the GraphicsContext object 'gc'
            // For example:
            // gc.setFill(Color.RED);
            // gc.fillRect(0, 0, worldMapWidth, worldMapHeight);

            // Convert canvas to Image
            worldMap[i] = canvas.snapshot(null, null);

            int col=0;
            int row=0;
            while(col<game.maxWorldCol&&row<game.maxWorldRow){
                int tileNum= mapTileNum[i][col][row];
                int x=game.tileSize*col;
                int y=game.tileSize*row;
                gc.drawImage(tile[tileNum].image,x,y);
                col++;
                if(col==game.maxWorldCol){
                    col=0;
                    row++;
                }
            }
        }
    }


    public void drawFullMapScreen(GraphicsContext gc){
        //Background COlor
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0,game.screenWidth,game.screenHeight);

        //DRAW MAP
        int width= 500;
        int height=500;
        int x=game.screenWidth/2-width/2;
        int y=game.screenHeight/2-height/2;
        gc.drawImage(worldMap[game.currentMap],x,y,height,width);

    }


}

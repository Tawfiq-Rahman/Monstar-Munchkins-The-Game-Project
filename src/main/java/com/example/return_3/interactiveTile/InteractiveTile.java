package com.example.return_3.interactiveTile;


import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;
import javafx.scene.canvas.GraphicsContext;

public class InteractiveTile extends Entity {
    public int objectType;
    Game game;
    public boolean destructible=false;
    public InteractiveTile(Game game, int col, int row) {
        super(game);
        this.game = game;
    }
    public boolean isCorrectItem(Entity entity){
        boolean isCorrectItem=false;
        return isCorrectItem;

    }
    public void playSE(){

    }
    public InteractiveTile getDestryoedForm(){
        InteractiveTile tile=null;

        return tile;
    }
    public void update(){
        if(invincible){
            invincibleCounter++;
            if(invincibleCounter>20){
                invincible=false;
                invincibleCounter=0;
            }
        }
    }
    public void draw(GraphicsContext gc){
        int screenX= worldX-game.player.worldX + game.player.screenX;
        int screenY= worldY-game.player.worldY + game.player.screenY;


        //this process is for not drawing the whole world map but the map tiles we needed only
        if(worldX+ game.tileSize>game.player.worldX-game.player.screenX&&
                worldX-game.tileSize<game.player.worldX+game.player.screenX&&
                worldY+ game.tileSize>game.player.worldY-game.player.screenY&&
                worldY- game.tileSize<game.player.worldY+game.player.screenY
        ){
            gc.drawImage(down1,screenX,screenY);
        }

    }
}


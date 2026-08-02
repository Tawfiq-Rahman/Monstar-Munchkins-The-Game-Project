package com.example.return_3.interactiveTile;

import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;
import javafx.scene.paint.Color;


public class BreakableSmallRock extends InteractiveTile{
    Game game;
    public BreakableSmallRock(Game game, int col, int row) {
        super(game,col,row);
        this.game=game;
        life=3;
        objectType=game.type_interactiverockSmall;
        this.worldX=game.tileSize*col;
        this.worldY=game.tileSize*row;
        down1=loadImage("/tiles_interactive/small_rock.png",game.tileSize,game.tileSize);
        destructible=true;
        life=6;
        type=type_rock;
        //set solid area for set  collision
        solidArea.setX(2);
        solidArea.setY(9);
        solidArea.setWidth(28);
        solidArea.setHeight(21);
        solidAreaDefaultX=(int)(solidArea.getX());
        solidAreaDefaultY=(int)(solidArea.getY());
    }


    public boolean isCorrectItem(Entity entity){
        boolean isCorrectItem=false;
        if(entity.currentWeapon != null && entity.currentWeapon.type==type_tomahawkAxe){
            isCorrectItem=true;
        }
        return isCorrectItem;

    }
//    public void playSE(){
//        gp.playSE(11);
//    }
//    public InteractiveTile getDestryoedForm(){
//        InteractiveTile tile= new CuttableTree_Parts(game, worldX/game.tileSize, worldY/game.tileSize);
//        return tile;
//    }
    public Color getParticleColor(){ //this indicates the color of the particle
        Color color= Color.rgb(51,56,76);
        return color;
    }
    public int getParticleSize(){// this indicates the size of the particle
        int size=4;
        return size;
    }
    public int getParticleSpeed(){ //this indicates the speed of the particle
        int speed=1;
        return speed;
    }

    public int getParticleMaxLife(){ //this indicates how long the particle will last
        int maxLife=20;
        return maxLife;
    }


}



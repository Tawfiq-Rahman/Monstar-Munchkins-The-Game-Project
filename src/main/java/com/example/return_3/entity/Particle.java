package com.example.return_3.entity;


import com.example.return_3.main.Game;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Particle extends Entity{
    Entity generator;
    Color color;
    int size;
    int xd;
    int yd;
    public Particle(Game game, Entity generator, Color color, int size, int speed, int maxLife, int xd, int yd) {
        super(game);
        this.generator=generator;
        this.color=color;
        this.size=size;
        this.speed=speed;
        this.maxLife=maxLife;
        this.xd=xd;
        this.yd=yd;

        life=maxLife;
        int offset=(game.tileSize/2)-(size/2);
        worldX=generator.worldX+offset;
        worldY=generator.worldY+offset;

    }
    public void update(){
        life--;
        if(life<maxLife/3){
            yd++;
        }
        worldX+=xd*speed;
        worldY+=yd*speed;
        if(life==0){
            alive=false;
        }
    }
    public void draw(GraphicsContext gc){
        int screenX=worldX-game.player.worldX+game.player.screenX;
        int screenY=worldY-game.player.worldY+game.player.screenY;

        gc.setFill(color);
        gc.fillRect(screenX,screenY,size,size);
    }
}


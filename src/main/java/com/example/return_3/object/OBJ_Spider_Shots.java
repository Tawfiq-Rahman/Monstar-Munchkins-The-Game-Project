package com.example.return_3.object;

import com.example.return_3.entity.Entity;
import com.example.return_3.entity.Projectile;
import com.example.return_3.main.Game;
import javafx.scene.paint.Color;

public class OBJ_Spider_Shots extends Projectile {
    Game game;
    public OBJ_Spider_Shots(Game game) {
        super(game);
        this.game = game;
        name ="Spider shot";
        speed =3;
        maxLife=80;
        life= maxLife;
        attack=10;
        useCost=1;
        alive=false;
        getImage();
    }
    public void getImage() {

        up1=loadImage( "/objects/projectile/spider_drop_up_1.png",game.tileSize-7,game.tileSize-7);
        up2= loadImage("/objects/projectile/spider_drop_up_2.png",game.tileSize-7,game.tileSize-7);
        down1= loadImage("/objects/projectile/spider_drop_down_1.png",game.tileSize-7,game.tileSize-7);
        down2= loadImage("/objects/projectile/spider_drop_down_2.png",game.tileSize-7,game.tileSize-7);
        left1=loadImage ("/objects/projectile/spider_drop_left_1.png",game.tileSize-7,game.tileSize-7);
        left2= loadImage("/objects/projectile/spider_drop_left_2.png",game.tileSize-7,game.tileSize-7);
        right1= loadImage("/objects/projectile/spider_drop_right_1.png",game.tileSize-7,game.tileSize-7);
        right2= loadImage("/objects/projectile/spider_drop_right_2.png",game.tileSize-7,game.tileSize-7);
    }
    public boolean haveResource(Entity user){
        boolean haveResource=false;
        if(user.ammo>= useCost){
            haveResource=true;
        }
        return haveResource;
    }

    public void subtractResource(Entity user){
        user.ammo -= useCost;
    }
    public Color getParticleColor(){ //this indicates the color of the particle
        Color color= Color.rgb(59,65,84);
        return color;
    }
    public int getParticleSize(){// this indicates the size of the particle
        int size=10;
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

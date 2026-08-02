package com.example.return_3.object;


import com.example.return_3.entity.Entity;
import com.example.return_3.entity.Projectile;
import com.example.return_3.main.Game;
import javafx.scene.paint.Color;

public class OBJ_Rock extends Projectile {
    Game game;
    public OBJ_Rock(Game game) {
        super(game);
        this.game = game;
        name ="Rock";
        speed =8;
        maxLife=80;
        life= maxLife;
        attack=2;
        useCost=1;
        alive=false;
        getImage();
    }
    public void getImage() {

        up1=loadImage( "/objects/projectile/rock_down_1.png",game.tileSize,game.tileSize);
        up2= loadImage("/objects/projectile/rock_down_1.png",game.tileSize,game.tileSize);
        down1= loadImage("/objects/projectile/rock_down_1.png",game.tileSize,game.tileSize);
        down2= loadImage("/objects/projectile/rock_down_1.png",game.tileSize,game.tileSize);
        left1=loadImage ("/objects/projectile/rock_down_1.png",game.tileSize,game.tileSize);
        left2= loadImage("/objects/projectile/rock_down_1.png",game.tileSize,game.tileSize);
        right1= loadImage("/objects/projectile/rock_down_1.png",game.tileSize,game.tileSize);
        right2= loadImage("/objects/projectile/rock_down_1.png",game.tileSize,game.tileSize);
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
        Color color= Color.rgb(40,50,0);
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


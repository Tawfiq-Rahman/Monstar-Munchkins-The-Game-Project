package com.example.return_3.object;

import com.example.return_3.entity.Entity;
import com.example.return_3.entity.Projectile;
import com.example.return_3.main.Game;
import javafx.scene.paint.Color;

public class OBJ_Ghost_Dirt extends Projectile {
    Game game;
    public OBJ_Ghost_Dirt(Game game) {
        super(game);
        this.game = game;
        name ="ghost dirt";
        speed =0;
        maxLife=600;
        life= maxLife;
        attack=5;
        useCost=1;
        alive=false;
        getImage();
    }
    public void getImage() {

        up1=loadImage( "/objects/projectile/blueGhost_Projectile.png",game.tileSize+15,game.tileSize+15);
        up2= loadImage("/objects/projectile/blueGhost_Projectile.png",game.tileSize+15,game.tileSize+15);
        down1= loadImage("/objects/projectile/blueGhost_Projectile.png",game.tileSize+15,game.tileSize+15);
        down2= loadImage("/objects/projectile/blueGhost_Projectile.png",game.tileSize+15,game.tileSize+15);
        left1=loadImage ("/objects/projectile/blueGhost_Projectile.png",game.tileSize+15,game.tileSize+15);
        left2= loadImage("/objects/projectile/blueGhost_Projectile.png",game.tileSize+15,game.tileSize+15);
        right1= loadImage("/objects/projectile/blueGhost_Projectile.png",game.tileSize+15,game.tileSize+15);
        right2= loadImage("/objects/projectile/blueGhost_Projectile.png",game.tileSize+15,game.tileSize+15);
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
        Color color= Color.rgb(30,21,46);
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

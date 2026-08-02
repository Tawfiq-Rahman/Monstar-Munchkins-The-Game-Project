package com.example.return_3.object;

import com.example.return_3.db.MyJDBC;
import com.example.return_3.entity.Entity;
import com.example.return_3.entity.Projectile;
import com.example.return_3.main.Game;
import javafx.scene.paint.Color;


public class OBJ_Fireball extends Projectile {
    Game game;

    public OBJ_Fireball(Game game) {
        super(game);
        this.game = game;
        name="Fireball";
        type = type_fireball;
        itemCode=107;
        speed = 5;
        maxLife=80;
        exp = 5;
        life = maxLife;
        mana=10;
        attack=10;
        price = 500;
        knockBackPower = 15;
        useCost=1;
        alive=false;
        description="["+name+"]\nIt can destroy Red Fly";
        getImage();
        solidArea.setX(11);
        solidArea.setY(13);
        solidArea.setWidth(12);
        solidArea.setHeight(8);
        solidAreaDefaultX = (int)(solidArea.getX());
        solidAreaDefaultY = (int)(solidArea.getY());
    }

    public void getImage(){
        up1 = loadImage("/objects/projectile/fireball_up_1.png", game.tileSize, game.tileSize);
        up2 = loadImage("/objects/projectile/fireball_up_2.png", game.tileSize, game.tileSize);
        down1 = loadImage("/objects/projectile/fireball_down_1.png", game.tileSize, game.tileSize);
        down2 = loadImage("/objects/projectile/fireball_down_2.png", game.tileSize, game.tileSize);
        left1 = loadImage("/objects/projectile/fireball_left_1.png", game.tileSize, game.tileSize);
        left2 = loadImage("/objects/projectile/fireball_left_2.png", game.tileSize, game.tileSize);
        right1 = loadImage("/objects/projectile/fireball_right_1.png", game.tileSize, game.tileSize);
        right2 = loadImage("/objects/projectile/fireball_right_2.png", game.tileSize, game.tileSize);

    }
    public boolean haveResource(Entity user){
        boolean haveResource=false;
        if(user.mana>= useCost){
            haveResource=true;
        }
        return haveResource;
    }

    public void subtractResource(Entity user){
        game.player.currentWeapon.mana -= useCost;
        System.out.println("Mana ="+game.player.currentWeapon.mana);
        if (game.player.currentWeapon.mana <= 0) {
            removeCurrentWeapon();
            System.out.println("ENd of mana");
        }
    }
    public void removeCurrentWeapon() {
        for(int i=0; i < game.player.inventory.size(); i++) {
            if(game.player.inventory.get(i).itemCode == game.player.currentWeapon.itemCode) {
                game.player.inventory.remove(i);
                MyJDBC.removeItemFromInventory(game.player.playerId, game.player.currentWeapon.itemCode);
                String text = game.player.currentWeapon.name +" disposed!";
                game.ui.uiMainGame.addMessage(text);
                game.player.currentWeapon = null;
                break;
            }
        }
    }

    public Color getParticleColor(){ //this indicates the color of the particle
        return Color.rgb(240,50,0);
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

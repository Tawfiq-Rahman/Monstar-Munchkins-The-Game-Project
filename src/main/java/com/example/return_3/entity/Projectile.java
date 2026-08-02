package com.example.return_3.entity;

import com.example.return_3.main.Game;

public class Projectile extends Entity{

    Game game;
    Entity user;
    public Projectile(Game game) {
        super(game);
        this.game=game;
    }
    public void set(int worldX,int worldY,String direction,boolean alive,Entity user){
        this.worldX=worldX;
        this.worldY=worldY;
        this.direction=direction;
        this.alive=alive;
        this.user=user;
        this.life=maxLife;
    }
    public void update(){
        //NOW WE ARE GOING TO CHECCK COLLISION FOR OUR PROJECTILE
        //IF IT IS MONSTER THEN WE WILL  CHECK PLAYER COLLISION
        //IF IT IS PLAYER THEN WE WILL CHECK MONSTER COLLISION
        if(user==game.player){
            int monsterIndex=game.cChecker.checkEntity(this,game.monster);
            if(monsterIndex!=999){
                game.player.damagedMonster(monsterIndex, this, attack, knockBackPower);
                 generateParticle(user.projectile,game.monster[game.currentMap][monsterIndex]);
                alive=false; //projectile disapear.
            }
        }
        if(user!=game.player){
            boolean contactPlayer=game.cChecker.checkPlayer(this);
            if(game.player.invincible==false&& contactPlayer==true){
                damagePlayer(attack);
                generateParticle(user.projectile ,user.projectile);
                alive=false;
            }
        }
        //CHECK COLLISION ON SOLID TILES
        collisionOn=false;
        game.cChecker.checkTile(this);
        if(collisionOn){
            alive=false;
        }


        switch (direction){
            case "up":worldY-=speed;break;
            case "down":worldY+=speed;break;
            case "left":worldX-=speed;break;
            case "right":worldX+=speed;break;
        }
        life--;
        if(life<=0){
            alive=false;
        }
        spriteCounter++;
        if(spriteCounter>12){
            if(spriteNum==1){
                spriteNum=2;
            }
            else if (spriteNum==2){
                spriteNum=1;
            }
            spriteCounter=0;
        }
    }

    public boolean haveResource(Entity user){
        boolean haveResource=false;
        //use the condition

        return haveResource;
    }
    public void subtractResource(Entity user){}
}
package com.example.return_3.thread;

import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;
import com.example.return_3.monster.Mon_Green;

public class GreenMonsterRebornThread extends Thread{
    public Game game;
    public int index;
    public int monsterArea;
    int x;
    int y;
    boolean unsuccessfull=true;

    public GreenMonsterRebornThread(Game game, int index,int monsterArea,int x, int y){
        this.game = game;
        this.index = index;
        this.monsterArea = monsterArea;
        this.x=x;
        this.y=y;
    }
    public void run(){
        Entity slimeMonster= new Mon_Green(game,monsterArea);
        slimeMonster.worldX=x;
        slimeMonster.worldY=y;
        while(unsuccessfull){
            System.out.println("trying to set reborn monster" );
            try {
                Thread.sleep(5000);
                slimeMonster.checkCollision();
                if(slimeMonster.collisionOn==false){
                    //now we can proceed our works
                    unsuccessfull=false;
                    game.monster[game.currentMap][index] = slimeMonster;
                    System.out.println("successfully set the reborned monster");

                }else{
                System.out.println("unsuccessfull to set the rebodrned monster");
                }

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

    }
}

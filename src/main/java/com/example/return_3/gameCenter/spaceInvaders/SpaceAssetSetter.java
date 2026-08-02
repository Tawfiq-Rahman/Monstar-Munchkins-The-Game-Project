package com.example.return_3.gameCenter.spaceInvaders;

import com.example.return_3.gameCenter.spaceInvaders.enemy.Enemy_Black;
import com.example.return_3.gameCenter.spaceInvaders.enemy.Enemy_Blue;
import com.example.return_3.gameCenter.spaceInvaders.enemy.Enemy_Red;
import com.example.return_3.main.Game;

import java.util.Random;

public class SpaceAssetSetter extends Thread{
    Game game;
    public int sleepTime;
    private static final Random RAND = new Random();
    public int posX,posY;
    public SpaceAssetSetter(Game game ){
        this.game = game;
        sleepTime=900;
    }

    public void run() {
        setEnemy();
    }

    public void setEnemy(){



        for (int i =0 ; i < Game.gameSpaceInvaders.enemies.length ; i++){


            posX = RAND.nextInt(game.screenWidth-game.tileSize)+game.tileSize;
            posY = RAND.nextInt(40);
            if(sleepTime<1500){
                Game.gameSpaceInvaders.enemies[i]=new Enemy_Black(game);
            } else if (sleepTime>=1500 && sleepTime<2200) {
                Game.gameSpaceInvaders.enemies[i]=new Enemy_Blue(game);
            }else if (sleepTime>=2200) {
                Game.gameSpaceInvaders.enemies[i]=new Enemy_Red(game);
            }
            Game.gameSpaceInvaders.enemies[i].posX=posX;
            Game.gameSpaceInvaders.enemies[i].posY=posY;

            // Pause execution for one second
            try {
                Thread.sleep(sleepTime); // 1000 milliseconds = 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sleepTime+=100;

        }




    }
}

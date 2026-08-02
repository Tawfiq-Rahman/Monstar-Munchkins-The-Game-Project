package com.example.return_3.test;

import com.example.return_3.main.Game;

import java.util.Random;

public class TestAssetSetter extends Thread{
    TestGame game;
    public int sleepTime;
    private static final Random RAND = new Random();
    public int posX,posY;
    public TestAssetSetter(TestGame game ){
        this.game = game;
        sleepTime=900;
    }

    public void run() {
        setEnemy();
    }
    
    public void setEnemy(){



        for (int i =0 ; i < game.enemies.length ; i++){


            posX = RAND.nextInt(game.screenWidth-game.tileSize)+game.tileSize;
            posY = RAND.nextInt(40);
            if(sleepTime<1500){
            game.enemies[i]=new TestEnemyBlack(game);
            } else if (sleepTime>=1500 && sleepTime<2200) {
                game.enemies[i]=new TestEnemyBlue(game);
            }else if (sleepTime>=2200) {
                game.enemies[i]=new TestEnemyRed(game);
            }
            game.enemies[i].posX=posX;
            game.enemies[i].posY=posY;

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

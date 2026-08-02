package com.example.return_3.thread;

import com.example.return_3.db.MyJDBC;
import com.example.return_3.main.Game;
import com.example.return_3.monster.Mon_Green;

public class SlimeDeadThread extends Thread{
    Game game;

    public SlimeDeadThread(Game game){
    this.game=game;

    }
    public void run(){
        for (int j = 0; j < game.monster[game.currentMap].length; j++) {
            if (game.monster[game.currentMap][j] instanceof Mon_Green) {
                game.monster[game.currentMap][j].slimeDeathOn=true;
                game.monster[game.currentMap][j].dying=true;
            }
        }
        MyJDBC.setDestroyedForSlimes();
    }
}

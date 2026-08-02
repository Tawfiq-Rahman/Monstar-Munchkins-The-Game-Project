package com.example.return_3.test;

import javafx.animation.AnimationTimer;

public class TestAnimationTimer extends AnimationTimer {
    TestGame game;

    TestAnimationTimer(TestGame game) {
        this.game = game;
    }

    @Override
    public void handle(long currentNanoTime) {
        double elapsedTime = (currentNanoTime-game.lastNanoTime)/1e9; // convert nanoseconds to seconds
        game.lastNanoTime=currentNanoTime;

        game.accumulatedTime+=elapsedTime;

        //Update the game loop until it catches up with the target frame time
        while(game.accumulatedTime>=game.targetFrameTime){
            game.update(); //update the game state
            //game.update(game.targetFrameTime); //update the game state
            game.accumulatedTime-=game.targetFrameTime;
        }
        game.render(); //Render the game;
    }
}

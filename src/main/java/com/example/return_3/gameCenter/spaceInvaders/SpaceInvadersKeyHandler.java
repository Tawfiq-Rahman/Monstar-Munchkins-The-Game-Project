package com.example.return_3.gameCenter.spaceInvaders;


import com.example.return_3.main.Game;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
public class SpaceInvadersKeyHandler {
    Game game;
    GameSpaceInvaders gameSpaceInvaders;
    Stage stage;
    private boolean moveUp, moveDown, moveLeft, moveRight,enterPressed, spacePressed, escapePressed,FKeyPressed;

    public SpaceInvadersKeyHandler(Game game,GameSpaceInvaders gameSpace) {
        this.game = game;
        gameSpaceInvaders=gameSpace;
        gameSpaceInvaders.spaceInvadersScene.setOnKeyPressed(event -> handleKeyPress(event.getCode()));
        gameSpaceInvaders.spaceInvadersScene.setOnKeyReleased(event -> handleKeyRelease(event.getCode()));
    }

    private void handleKeyPress(KeyCode code) {




        //This is for PlayState
        if(game.gameState == game.playState){
            playState(code);
            if(code==KeyCode.K){
                System.out.println("Pressing K in SpaceKeyHandler");
            }
        }

        //MENU BAR state
        else if (game.gameState == game.menuBarState) {
                menuBarState(code);
        } //TITLE state
        else if (game.gameState == game.titleState) {
                titleState(code);
        }
    }


    private void titleState(KeyCode code){
        if (code == KeyCode.ESCAPE) {
            escapePressed = true;
            game.gameState=game.playState;
        }
        if(code== KeyCode.W || code== KeyCode.UP){

            Game.gameSpaceInvaders.uiGameSpaceInvaders.commandNum--;
            if(Game.gameSpaceInvaders.uiGameSpaceInvaders.commandNum < 0){
                Game.gameSpaceInvaders.uiGameSpaceInvaders.commandNum = 3;
            }
        }
        if(code== KeyCode.S || code== KeyCode.DOWN){
            Game.gameSpaceInvaders.uiGameSpaceInvaders.commandNum++;
            if(Game.gameSpaceInvaders.uiGameSpaceInvaders.commandNum > 3){
                Game.gameSpaceInvaders.uiGameSpaceInvaders.commandNum=0;
            }
        }
        if (code == KeyCode.ENTER) {
            if (Game.gameSpaceInvaders.uiGameSpaceInvaders.commandNum == 0) {
                game.gameState=game.playState;
                System.out.println("starting the resume game");
            }
            if ( Game.gameSpaceInvaders.uiGameSpaceInvaders.commandNum == 1) {
                try {
                    Game.gameSpaceInvaders.startGameSpaceInvaders();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                System.out.println("starting the start game");
            }
            if ( Game.gameSpaceInvaders.uiGameSpaceInvaders.commandNum == 2) {
                System.out.println("Option clicked ");

            }
            if ( Game.gameSpaceInvaders.uiGameSpaceInvaders.commandNum == 3) {
                try {
                    game.showGameCenter();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    private void menuBarState(KeyCode code) {
//        if (code == KeyCode.ESCAPE) {
//            escapePressed = true;
//            game.gameState=game.playState;
//        }
        if(code== KeyCode.W || code== KeyCode.UP){

            Game.gameSpaceInvaders.uiGameSpaceInvaders.commandNum--;
            if(Game.gameSpaceInvaders.uiGameSpaceInvaders.commandNum < 0){
                Game.gameSpaceInvaders.uiGameSpaceInvaders.commandNum = 1;
            }
        }
        if(code== KeyCode.S || code== KeyCode.DOWN){
            Game.gameSpaceInvaders.uiGameSpaceInvaders.commandNum++;
            if(Game.gameSpaceInvaders.uiGameSpaceInvaders.commandNum > 1){
                Game.gameSpaceInvaders.uiGameSpaceInvaders.commandNum=0;
            }
        }
        if (code == KeyCode.ENTER) {
            if (Game.gameSpaceInvaders.uiGameSpaceInvaders.commandNum == 0) {
                //game.gameState = game.playState;
               // game.player.setDefaultPositions();
                System.out.println("command num 0 set");
            }
            if ( Game.gameSpaceInvaders.uiGameSpaceInvaders.commandNum == 1) {
                System.out.println("command num 1 set");
                try {
                    game.gameState=game.titleState;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void playState(KeyCode code){

        switch (code) {
            case UP: moveUp = true; break;
            case DOWN: moveDown = true; break;
            case LEFT: moveLeft = true; break;
            case RIGHT: moveRight = true; break;
            case W: moveUp = true; break;
            case S: moveDown = true; break;
            case A: moveLeft = true; break;
            case D: moveRight = true; break;
            case F: FKeyPressed=true;break;
            case ENTER: enterPressed = true; break;
            case SPACE: spacePressed = true; break;
            case C: game.gameState = game.characterState; break;
            case ESCAPE: game.gameState = game.titleState; break;

        }
    }




    private void handleKeyRelease(KeyCode code) {
        switch (code) {
            case UP: moveUp = false; break;
            case DOWN: moveDown = false; break;
            case LEFT: moveLeft = false; break;
            case RIGHT: moveRight = false; break;
            case W: moveUp = false; break;
            case S: moveDown = false; break;
            case A: moveLeft = false; break;
            case D: moveRight = false; break;
            case F: FKeyPressed=false;break;
            case ENTER: enterPressed = false; break;
            case SPACE:spacePressed = false; break;
            case ESCAPE:escapePressed = false; break;
        }

    }

    // Getters for the movement flags
    public boolean isMoveUp() {
        return moveUp;
    }

    public boolean isMoveDown() {

        return moveDown;
    }

    public boolean isMoveLeft() {

        return moveLeft;
    }

    public boolean isMoveRight() {
        return moveRight;
    }
    public boolean isEnterPressed(){
        return enterPressed;
    }
    public boolean isSpacePressed() {
        return spacePressed;
    }
    public boolean isEscapePressed() {
        return escapePressed;
    }
    public  boolean isFKeyPressed(){
        return FKeyPressed;
    }

    public void setEnterPressed(boolean enterPressed) {
        this.enterPressed = enterPressed;
    }
    public void setSpacePressed(boolean spacePressed) {
        this.spacePressed = spacePressed;
    }
    public void setEscapePressed(boolean escapePressed) {
        this.escapePressed = escapePressed;
    }


    public void setBooleanAll(boolean value){
        this.moveUp = value;
        this.moveDown = value;
        this.moveLeft = value;
        this.moveRight = value;
        this.enterPressed = value;
        this.spacePressed = value;
        this.escapePressed = value;
    }

}
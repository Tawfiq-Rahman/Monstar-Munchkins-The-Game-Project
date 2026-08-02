package com.example.return_3.gameCenter.spaceInvaders;

import com.example.return_3.entity.Entity;
import com.example.return_3.gameCenter.spaceInvaders.enemy.Enemy;
import com.example.return_3.main.Game;
import com.example.return_3.main.GameAnimationTimer;
import com.example.return_3.main.KeyHandler;
import com.example.return_3.main.UtilityTool;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameSpaceInvaders {

    Game game;
    //SCREEN SETTINGS
//    public final int tileSize = 32; //SO every tile will be 32 pixels
//    public final int maxScreenCol = 28; //here will be 20 column of titles  =>1024 pixel width
//    public final int maxScreenRow = 18; //here will be 25 row of titles => 800 pixel height
//    public final int screenWidth= tileSize*maxScreenCol; //1024 pixel width
//    public final int screenHeight= tileSize*maxScreenRow; //800 pixel height


    // $$$$$$$$$ System $$$$$$$$$
    //instantiates new instances
//    Canvas canvas = new Canvas(screenWidth, screenHeight);
//    GraphicsContext gc = canvas.getGraphicsContext2D();
    Canvas canvas;
    GraphicsContext gc;
    Scene spaceInvadersScene;

    public SpaceShip spaceShip ;
    Image bcPic;
    UtilityTool uTool;


    public Entity[] enemies = new Entity[20];
    public List<Enemy> enemiesList = new ArrayList<>();
    SpaceAssetSetter assetSetter;
    public UI_GameSpaceInvaders uiGameSpaceInvaders;


    public GameSpaceInvaders( Game game){
        this.game = game;
        this.canvas=new Canvas(game.screenWidth,game.screenHeight);
        this.gc = canvas.getGraphicsContext2D();
         uTool= new UtilityTool();
         uiGameSpaceInvaders=new UI_GameSpaceInvaders(game,gc);
    }



    public void startGameSpaceInvaders() throws Exception {
        game.gameStatus=game.gameSpaceInvadersStatus; //change the GameStatus  as our new gameStatus so that update and render method will work in that such crieteria
        game.gameState=game.playState; //change the GameState as play state
        Pane gameSpaceInvadersRoot = new Pane();
        spaceInvadersScene = new Scene(gameSpaceInvadersRoot, game.screenWidth, game.screenHeight); // Set the scene before creating KeyHandler
        //game.keyHandler= new KeyHandler(game);
        spaceShip=new SpaceShip(game,new SpaceInvadersKeyHandler(game,this));
        bcPic=  uTool.loadImage("/gameCenter/spaceInvaders/level1.png", game.screenWidth, game.screenHeight);

        gameSpaceInvadersRoot.getChildren().add(canvas); //Now we added the root created in gameSpaceInvaders
        //game.lastNanoTime = System.nanoTime();
       // Game.gameTimer = new GameAnimationTimer(game);
        assetSetter= new SpaceAssetSetter(game);

        assetSetter.start();
        Game.gameTimer.start();
        Game.primaryStage.setScene(spaceInvadersScene);
    }



    public void update(){
        //System.out.println("update working");
        if(spaceShip!=null){
            spaceShip.update();
            if(spaceShip.destroyed){
                System.out.println("gameOver");
            }
        }
        for(int i=0; i<enemies.length;i++){
            if(enemies[i]!=null){
                if(enemies[i].bottomTouched==false){


                    enemies[i].update();
                }else if(enemies[i].bottomTouched==true){
                    enemies[i]=null;
                }
            }
        }
    }

    public void draw( ){
        //System.out.println("GameSpaceInvaders Class: `drawMethod`  draw working");
        //Now drawing the background

        Image backgroundImage =null;
         backgroundImage =bcPic;
        gc.drawImage(backgroundImage,0,0);
        //new CheckedThread(bcPic,gc).start();

        for(int i=0; i<enemies.length;i++){
            if(enemies[i]!=null){
                enemies[i].draw(gc);
            }
        }
        //Drawing spaceShip
        spaceShip.draw(this.gc);

    }

}

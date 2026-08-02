package com.example.return_3.test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.List;

public class TestGame extends Application {
    TestAnimationTimer testGameTimer;
public TestCollisionChecker collisionChecker;
    public int gameState;

    public final int titleState=0;
    public final int playState=1;
    public final int pauseState=2;
    public final int dialogueState=3;
    public final int characterState=4;


    public final int tileSize = 32; //SO every tile will be 32 pixels
    public final int maxScreenCol = 28; //here will be 20 column of titles  =>1024 pixel width
    public final int maxScreenRow = 18; //here will be 25 row of titles => 800 pixel height
    public final int screenWidth= tileSize*maxScreenCol; //1024 pixel width
    public final int screenHeight= tileSize*maxScreenRow; //800 pixel height


    // $$$$$$$$$  World Setting $$$$$$$$$



    public static final double targetFrameRate=60.0;
    public static final double targetFrameTime=1/targetFrameRate; //0.01666666666 it means per second e frame 60. so 0.01666666666 second e ekbar frame change
    public double accumulatedTime=0.0; // accumulated time
    public long lastNanoTime;
    protected Scene scene;
    Canvas canvas = new Canvas(screenWidth, screenHeight);
    GraphicsContext gc = canvas.getGraphicsContext2D();

    TestKeyHandler testKeyHandler;
    TestEntity testPlayer;
    Image backgroundImage;

    TestEntity[] enemies = new TestEntity[20];
    List<TestEntity> enemiesList = new ArrayList<TestEntity>();
    TestAssetSetter assetSetter;


    @Override
    public void start(Stage stage) throws Exception {

        Pane root = new Pane();
         scene = new Scene(root, screenWidth, screenHeight); // Set the scene before creating KeyHandler
        testKeyHandler=new TestKeyHandler(this);
        collisionChecker= new TestCollisionChecker(this);

         testPlayer=new TestPlayer(this,testKeyHandler);
         backgroundImage = testPlayer.loadImage("/gameCenter/spaceInvaders/background_1.jpg", screenWidth, screenHeight);
         assetSetter= new TestAssetSetter(this);
        assetSetter.start();
        root.getChildren().add(canvas);
        lastNanoTime = System.nanoTime();
        testGameTimer= new TestAnimationTimer(this);
        testGameTimer.start();
        gameState=playState;
        stage.setScene(scene);

        stage.setTitle("Powered By return_3;");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    public void update(){

        testPlayer.update();

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
    public void render(){

        gc.drawImage(backgroundImage, 0, 0);
        for(int i=0; i<enemies.length;i++){
            if(enemies[i]!=null){
                enemies[i].draw(gc);
            }
        }
//        if(testPlayer.destroyed==false){
//        }
        testPlayer.draw(gc);

    }

}


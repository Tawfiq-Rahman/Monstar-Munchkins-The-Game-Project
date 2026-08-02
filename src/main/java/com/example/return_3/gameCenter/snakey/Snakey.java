package com.example.return_3.gameCenter.snakey;

import com.example.return_3.gameCenter.spaceInvaders.SpaceAssetSetter;
import com.example.return_3.gameCenter.spaceInvaders.SpaceShip;
import com.example.return_3.main.Game;
import com.example.return_3.main.KeyHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Snakey {
    //set up for every gc canvas game. START
    Game game;
    Canvas canvas;
    GraphicsContext gc;
    //set up for every gc canvas game. END
    KeyHandler keyHandler;
    int k=0;

    private static final int WIDTH = 960;
    private static final int HEIGHT = 576;
    private static final int ROWS = Game.gameInstance.maxScreenRow;
    private static final int COLUMNS = Game.gameInstance.maxScreenCol;
    private static final int SQUARE_SIZE = WIDTH / ROWS;
    private static final String[] FOODS_IMAGE = new String[]{"/gameCenter/snakey/ic_orange.png", "/gameCenter/snakey/ic_apple.png", "/gameCenter/snakey/ic_cherry.png",
            "/gameCenter/snakey/ic_berry.png", "/gameCenter/snakey/ic_coconut_.png", "/gameCenter/snakey/ic_peach.png", "/gameCenter/snakey/ic_watermelon.png", "/gameCenter/snakey/ic_orange.png",
            "/gameCenter/snakey/ic_pomegranate.png"};

    private static final int RIGHT = 0;
    private static final int LEFT = 1;
    private static final int UP = 2;
    private static final int DOWN = 3;

    private List<Point> snakeBody = new ArrayList();
    private Point snakeHead;
    private Image foodImage;
    private int foodX;
    private int foodY;
    private boolean gameOver;
    private int currentDirection;
    private int score = 0;

    public Snakey(Game game,GraphicsContext gc){
        this.game = game;
        this.gc = gc;
        this.canvas=game.mainGameCanvas;
        // Initialize snakeHead here
        snakeHead = new Point(5, ROWS / 2);
        keyHandler= new KeyHandler(game);
    }
    public void startGameSnakey(){
        game.gameStatus=game.gameSnakeyStatus; //change the GameStatus  as our new gameStatus so that update and render method will work in that such crieteria
        game.gameState=game.playState; //change the GameState as play state
        Pane ganeSnakeyRoot = new Pane();
        Game.gameScene = new Scene(ganeSnakeyRoot, game.screenWidth, game.screenHeight); // Set the scene before creating KeyHandler
        //game.keyHandler= new KeyHandler(game);
//        spaceShip=new SpaceShip(game,new KeyHandler(game));
//        bcPic=  uTool.loadImage("/gameCenter/spaceInvaders/level1.png", game.screenWidth, game.screenHeight);
//
        ganeSnakeyRoot.getChildren().add(canvas); //Now we added the root created in gameSpaceInvaders
//        //game.lastNanoTime = System.nanoTime();
//        // Game.gameTimer = new GameAnimationTimer(game);
//        assetSetter= new SpaceAssetSetter(game);
//
//        assetSetter.start();

        Game.gameTimer.start();
        Game.primaryStage.setScene(Game.gameScene);
    }



















    public void update(){
//
//        if (gameOver) {
//            gc.setFill(javafx.scene.paint.Color.RED);
//            gc.setFont(new javafx.scene.text.Font("Digital-7", 70));
//            gc.fillText("Game Over", WIDTH / 3.5, HEIGHT / 2);
//            return;
//
//        }
        System.out.println(k++);

        for (int i = snakeBody.size() - 1; i >= 1; i--) {
            snakeBody.get(i).x = snakeBody.get(i - 1).x;
            snakeBody.get(i).y = snakeBody.get(i - 1).y;
        }

             // Move player based on key inputs
             if (keyHandler.isMoveUp() && currentDirection != DOWN) {
                 currentDirection = UP;
             } else if (keyHandler.isMoveDown() && currentDirection != UP) {
                 currentDirection = DOWN;
             } else if (keyHandler.isMoveLeft() && currentDirection != RIGHT) {
                 currentDirection = LEFT;
             } else if (keyHandler.isMoveRight() && currentDirection != LEFT) {
                 currentDirection = RIGHT;
             }



        switch (currentDirection) {
            case RIGHT:
                moveRight();
                break;
            case LEFT:
                moveLeft();
                break;
            case UP:
                moveUp();
                break;
            case DOWN:
                moveDown();
                break;
        }

        for (int i = 0; i < 3; i++) {
            snakeBody.add(new Point(5, ROWS / 2));
        }
        snakeHead = snakeBody.get(0);
        generateFood();


        gameOver();
        eatFood();
    }

    public void draw(GraphicsContext gc) {
        drawBackground(gc);
        drawFood(gc);
        drawSnake(gc);
        drawScore();
    }



    private void drawBackground(GraphicsContext gc) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if ((i + j) % 2 == 0) {
                    gc.setFill(javafx.scene.paint.Color.web("AAD751"));
                } else {
                    gc.setFill(javafx.scene.paint.Color.web("A2D149"));
                }
                gc.fillRect(i * SQUARE_SIZE, j * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
    }

    private void generateFood() {
        start:
        while (true) {
            foodX = (int)(Math.random() * ROWS);
            foodY = (int)(Math.random() * COLUMNS);

            for (Point snake : snakeBody) {
                if (snake.getX() == foodX && snake.getY() == foodY) {
                    continue start;
                }
            }
            foodImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(FOODS_IMAGE[(int) (Math.random() * FOODS_IMAGE.length)])));
//            foodImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(FOODS_IMAGE[0])));
            break;
        }
    }

    private void drawFood(GraphicsContext gc) {
        gc.drawImage(foodImage, foodX * SQUARE_SIZE, foodY * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
    }

    private void drawSnake(GraphicsContext gc) {
        gc.setFill(javafx.scene.paint.Color.web("FF4141"));
        gc.fillRoundRect(snakeHead.getX() * SQUARE_SIZE, snakeHead.getY() * SQUARE_SIZE, SQUARE_SIZE - 1, SQUARE_SIZE - 1, 35, 35);

        for (int i = 1; i < snakeBody.size(); i++) {
            gc.fillRoundRect(snakeBody.get(i).getX() * SQUARE_SIZE, snakeBody.get(i).getY() * SQUARE_SIZE, SQUARE_SIZE - 1,
                    SQUARE_SIZE - 1, 20, 20);
        }
    }

    private void moveRight() {
        snakeHead.x++;
    }

    private void moveLeft() {
        snakeHead.x--;
    }

    private void moveUp() {
        snakeHead.y--;
    }

    private void moveDown() {
        snakeHead.y++;
    }

    public void gameOver() {
        if (snakeHead.x < 0 || snakeHead.y < 0 || snakeHead.x * SQUARE_SIZE >= WIDTH || snakeHead.y * SQUARE_SIZE >= HEIGHT) {
            gameOver = true;
        }

        //destroy itself
        for (int i = 1; i < snakeBody.size(); i++) {
            if (snakeHead.x == snakeBody.get(i).getX() && snakeHead.getY() == snakeBody.get(i).getY()) {
                gameOver = true;
                break;
            }
        }

    }

    private void eatFood() {
        if (snakeHead.getX() == foodX && snakeHead.getY() == foodY) {
            snakeBody.add(new Point(-1, -1));
            generateFood();
            score += 5;
        }
    }

    private void drawScore() {
        gc.setFill(Color.BLACK);
        gc.setFont(new Font("Digital-7", 35));
        gc.fillText("Score: " + score, 10, 35);
    }
}

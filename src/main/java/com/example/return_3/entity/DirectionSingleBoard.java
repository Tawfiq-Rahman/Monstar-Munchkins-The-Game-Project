package com.example.return_3.entity;

import com.example.return_3.main.Game;

public class DirectionSingleBoard extends InteractNPC {
    Game game;
    String imgPath;

    public DirectionSingleBoard(Game game, int x) {
        super(game);
        this.game=game;
        this.name="directionBoard"+x;
        getNPCImage();
        //set the collision part
        solidArea.setX(13);
        solidArea.setY(25);
        solidArea.setWidth(23);
        solidArea.setHeight(24);
        solidAreaDefaultX=(int)(solidArea.getX());
        solidAreaDefaultY=(int)(solidArea.getY());
    }
    public void checkCollision(){
        collisionOn=false;
        //CHeching part of collision so that entity got collision and can not move
        game.cChecker.checkTile(this);
        game.cChecker.checkPlayer(this);
//        gp.cChecker.checkObject(this,false); //its not player so its remain false
        game.cChecker.checkEntity(this,game.npc);
        game.cChecker.checkEntity(this,game.monster);
        game.cChecker.checkEntity(this,game.iTile);



    }
    public void getNPCImage(){
//        up1=loadImage( "/tiles_interactive/directionBoard1.png",75,75);
//        System.out.println(name);
                up1=loadImage( "/tiles_interactive/" +name+
                ".png",50,50);

//        src/main/resources/tiles_interactive/directionBoard1.png
        up2=up1;
        down1=up1;
        down2= up1;
        left1=up1;
        left2= up1;
        right1= up1;
        right2= up1;
    }

    public void update(){}


}

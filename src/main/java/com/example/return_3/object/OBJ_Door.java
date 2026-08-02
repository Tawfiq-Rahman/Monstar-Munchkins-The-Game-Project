package com.example.return_3.object;

import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;


public class OBJ_Door extends Entity {
    Game game;

    public OBJ_Door(Game game) {
        super(game);
        this.game = game;
        type= type_obstacle;
        name= "door";
        itemCode=320;
        image1=uTool.loadImage("/objects/door.png",game.tileSize,game.tileSize);
        image2=uTool.loadImage("/objects/door_opened.png",game.tileSize,game.tileSize);
        down1=image1;

        description="this is "+name;
        collision=true;
        solidArea.setX(0);
        solidArea.setY(10);
        solidArea.setWidth(32);
        solidArea.setHeight(22);
        solidAreaDefaultX= (int) solidArea.getX();
        solidAreaDefaultY= (int) solidArea.getY();
    }
    public void interact(){
        if(!destroyed){
            game.playSoundEffect(game.soundEffect.popUp);
            game.gameState=game.messageState;
            game.ui.uiMainGame.currentDialogue="You need a key to open the door";
            game.playSoundEffect(game.soundEffect.doorLocked);

        }
    }

}

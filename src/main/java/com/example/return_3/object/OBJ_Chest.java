package com.example.return_3.object;

import com.example.return_3.db.MyJDBC;
import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;

import java.util.Random;


public class OBJ_Chest extends Entity {
    Game game;
    public OBJ_Chest(Game game) {
        super(game);
        this.game = game;
        type = type_obstacle;
        name = "Chest";
        itemCode = 321;
        image1 = uTool.loadImage("/objects/chest.png",game.tileSize,game.tileSize);
        image2 = uTool.loadImage("/objects/chest_opened.png",game.tileSize,game.tileSize);
        down1 = image1;

        description = "this is " + name;
        collision = true;
        solidArea.setX(0);
        solidArea.setY(10);
        solidArea.setWidth(32);
        solidArea.setHeight(22);
        solidAreaDefaultX = (int) solidArea.getX();
        solidAreaDefaultY = (int) solidArea.getY();
    }
    public void interact(){
        game.playSoundEffect(game.soundEffect.popUp);
        game.gameState = game.messageState;

        if(!destroyed){
            game.playSoundEffect(3);
            Random random = new Random();
            int coin = 300 + random.nextInt(200);
            StringBuilder sb = new StringBuilder();
            sb.append("You opened the chest and find a ").append(coin).append(" coin!");
            sb.append("\nYou obtained the ").append(coin).append(" coin!");
            game.player.coin += coin;
            down1 = image2;
            destroyed = true;
            int row = worldY/game.tileSize;
            int col = worldX/game.tileSize;
            MyJDBC.updateObjectDestroyedStatus(game.player.playerId,game.currentMap,row,col,game.type_object,true);
            game.ui.uiMainGame.currentDialogue = sb.toString();
        }
        else {
          game.ui.uiMainGame.currentDialogue = "Already opened and there is no loot!";
        }
    }

}

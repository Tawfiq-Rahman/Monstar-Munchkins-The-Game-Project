package com.example.return_3.object;

import com.example.return_3.db.MyJDBC;
import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;

public class OBJ_BlueKey extends Entity {
    Game game;
    public OBJ_BlueKey(Game game) {
        super(game);
        this.game = game;
        type= type_consumable;
        itemCode=308;
        exp = 20;
        name= "Blue Key";
        down1=uTool.loadImage("/objects/blueKey.png",game.tileSize,game.tileSize);
        description="this is "+name;

    }
    public boolean use(Entity entity) {
        game.playSoundEffect(game.soundEffect.popUp);
        game.gameState=game.messageState;

        int objectIndex=getDetected(entity,game.obj,"door");
        if(objectIndex!=999 &&game.obj[game.currentMap][objectIndex].destroyed!=true){
            game.ui.uiMainGame.currentDialogue="You use the "+name+"and opened the door";
            game.playSoundEffect(game.soundEffect.doorOpened);
            Entity entity2=game.obj[game.currentMap][objectIndex];
            int row=entity2.worldY/game.tileSize;
            int col=entity2.worldX/game.tileSize;
            MyJDBC.updateObjectDestroyedStatus(game.player.playerId,game.currentMap,row,col,game.type_object,true);
            System.out.println("player id"+playerId);
            System.out.println(game.currentMap);
            System.out.println(row);
            System.out.println(col);
            System.out.println(game.type_object);
            System.out.println(true);
            entity2.down1=entity2.image2;
            entity2.collision=false;
            entity2.destroyed=true;
            return true;
        }
        else {
            game.ui.uiMainGame.currentDialogue="r e vai dorjar samne use kor!";
            return false;
        }
    }
}

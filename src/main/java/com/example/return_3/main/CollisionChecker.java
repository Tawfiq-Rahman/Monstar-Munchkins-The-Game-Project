package com.example.return_3.main;

import com.example.return_3.entity.Entity;
import com.example.return_3.gameCenter.spaceInvaders.Shot;
import com.example.return_3.gameCenter.spaceInvaders.SpaceShip;
import com.example.return_3.gameCenter.spaceInvaders.enemy.Enemy;
import com.example.return_3.test.TestEntity;
import com.example.return_3.test.TestShot;

public class CollisionChecker {
    Game game;

    public CollisionChecker(Game game) {
        this.game = game;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.worldX + (int) (entity.solidArea.getX());
        int entityRightWorldX = entity.worldX + (int) (entity.solidArea.getX() + entity.solidArea.getWidth());
        int entityTopWorldY = entity.worldY + (int) (entity.solidArea.getY());
        int entityBottomWorldY = entity.worldY + (int) (entity.solidArea.getY() + entity.solidArea.getHeight());

        int entityLeftCol = entityLeftWorldX / game.tileSize;
        int entityRightCol = entityRightWorldX / game.tileSize;
        int entityTopRow = entityTopWorldY / game.tileSize;
        int entityBottomRow = entityBottomWorldY / game.tileSize;

        int tileNum1, tileNum2;

        // USe temporal direction when it's being knock backed...
        String direction = entity.direction;
        if (entity.knockBack == true) {
            direction = entity.knockBackDirection;
        }


        //checking for the direction
        switch (direction) {
            case "up":
//                System.out.println(" speed"+ entity.speed);
                entityTopRow = (entityTopWorldY - entity.speed) / game.tileSize;
                // in above case what will happen is that we kind of predict where the player will be after he movied
                // in there subtract the speed because in up direction y value will be less as much we go up.
                tileNum1 = game.tileM.mapTileNum[game.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = game.tileM.mapTileNum[game.currentMap][entityRightCol][entityTopRow];

                if (game.tileM.tile[tileNum1].collision == true || game.tileM.tile[tileNum2].collision == true) { // here we are checking the collision of that two tile if they are solid tiles then we will do any action or not
                    //that means it will hit any solid tile so the player can not move in this direction .
                    entity.collisionOn = true;

                }
                break;
            case "down":
//                System.out.println("speed"+ entity.speed);

                entityBottomRow = (entityBottomWorldY + entity.speed) / game.tileSize;
                tileNum1 = game.tileM.mapTileNum[game.currentMap][entityLeftCol][entityBottomRow];
                tileNum2 = game.tileM.mapTileNum[game.currentMap][entityRightCol][entityBottomRow];
                if (game.tileM.tile[tileNum1].collision == true || game.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
//                System.out.println("speed"+ entity.speed);

                entityLeftCol = (entityLeftWorldX - entity.speed) / game.tileSize;
                tileNum1 = game.tileM.mapTileNum[game.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = game.tileM.mapTileNum[game.currentMap][entityLeftCol][entityBottomRow];
                if (game.tileM.tile[tileNum1].collision == true || game.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
//                System.out.println("speed"+ entity.speed);

                entityRightCol = (entityRightWorldX + entity.speed) / game.tileSize;
                tileNum1 = game.tileM.mapTileNum[game.currentMap][entityRightCol][entityTopRow];
                tileNum2 = game.tileM.mapTileNum[game.currentMap][entityRightCol][entityBottomRow];
                if (game.tileM.tile[tileNum1].collision == true || game.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
        }
    }


    //part 8 Object Interaction part starts

    public int checkObject(Entity entity, boolean player){
        int index =999;

        for(int i=0; i<game.obj[game.currentMap].length;i++){
            if(game.obj[game.currentMap][i]!= null){
                //Get entity solid Area Position
                entity.solidArea.setX(entity.worldX + entity.solidArea.getX());
                entity.solidArea.setY(entity.worldY + entity.solidArea.getY());
                //Get the object's solid Area position
                game.obj[game.currentMap][i].solidArea.setX(game.obj[game.currentMap][i].worldX + game.obj[game.currentMap][i].solidArea.getX());
                game.obj[game.currentMap][i].solidArea.setY(game.obj[game.currentMap][i].worldY + game.obj[game.currentMap][i].solidArea.getY());

//                switch (entity.direction){
//                    case "up":
//                        entity.solidArea.y-=entity.speed;
//                        break;
//                    case "down":
//                        entity.solidArea.y+=entity.speed;
//                        break;
//                    case "left":
//                        entity.solidArea.x-=entity.speed;
//                        break;
//                    case "right":
//                        entity.solidArea.x+=entity.speed;
//                        break;
//                }

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.setY(entity.solidArea.getY() - entity.speed);
                        break;
                    case "down":
                        entity.solidArea.setY(entity.solidArea.getY() + entity.speed);
                        break;
                    case "left":
                        entity.solidArea.setX(entity.solidArea.getX() - entity.speed);
                        break;
                    case "right":
                        entity.solidArea.setX(entity.solidArea.getX() + entity.speed);
                        break;
                }
                //rectangle class has a method named intersects to check two rectangeles are overlapperd or not

                if (entity.solidArea.intersects(game.obj[game.currentMap][i].solidArea.getBoundsInLocal())) {
                    if (game.obj[game.currentMap][i].collision==true) {
                        //check the object is solid or not
                        entity.collisionOn = true;

                    }
                    //check this is a player or not (it could be a monster or other npc)
                    if(player==true){
                        index=i;
                    }
                }
                // reset the value
                entity.solidArea.setX(entity.solidAreaDefaultX);
                entity.solidArea.setY(entity.solidAreaDefaultY);

                game.obj[game.currentMap][i].solidArea.setX(game.obj[game.currentMap][i].solidAreaDefaultX);
                game.obj[game.currentMap][i].solidArea.setY(game.obj[game.currentMap][i].solidAreaDefaultY);

            }
        }

        return index;
    }
//    //part 8 Object Interaction part ends
    //

    // This method is for check NPC or Monster Collision
    //this is identical of checkObject method
    public int checkEntity(Entity entity, Entity[][] target) {
        int index = 999;

        // USe temporal direction when it's being knock backed...
        String direction = entity.direction;
        if (entity.knockBack == true) {
            direction = entity.knockBackDirection;
        }

        for (int i = 0; i < target[game.currentMap].length; i++) {
            if (target[game.currentMap][i] != null) {
                //Get entity solid Area Position
                entity.solidArea.setX(entity.worldX + entity.solidArea.getX());
                entity.solidArea.setY(entity.worldY + entity.solidArea.getY());

                //Get the object's solid Area position
                target[game.currentMap][i].solidArea.setX(target[game.currentMap][i].worldX + target[game.currentMap][i].solidArea.getX());
                target[game.currentMap][i].solidArea.setY(target[game.currentMap][i].worldY + target[game.currentMap][i].solidArea.getY());


                switch (direction) {
                    case "up":
                        entity.solidArea.setY(entity.solidArea.getY() - entity.speed);
                        break;
                    case "down":
                        entity.solidArea.setY(entity.solidArea.getY() + entity.speed);
                        break;
                    case "left":
                        entity.solidArea.setX(entity.solidArea.getX() - entity.speed);
                        break;
                    case "right":
                        entity.solidArea.setX(entity.solidArea.getX() + entity.speed);
                        break;
                }

                if (entity.solidArea.intersects(target[game.currentMap][i].solidArea.getBoundsInLocal())) {
                    if (target[game.currentMap][i] != entity) {
                        //check the object is solid or not
                        entity.collisionOn = true;
                        index = i;
                    }
                }


                // reset the value
                entity.solidArea.setX(entity.solidAreaDefaultX);
                entity.solidArea.setY(entity.solidAreaDefaultY);

                target[game.currentMap][i].solidArea.setX(target[game.currentMap][i].solidAreaDefaultX);
                target[game.currentMap][i].solidArea.setY(target[game.currentMap][i].solidAreaDefaultY);
            }
        }

        return index;
    }


    public boolean checkPlayer(Entity entity) {
        boolean contactPlayer = false;
        //Get entity solid Area Position
        entity.solidArea.setX(entity.worldX + entity.solidArea.getX());
        entity.solidArea.setY(entity.worldY + entity.solidArea.getY());
        //Get the object's solid Area position
        game.player.solidArea.setX(game.player.worldX + game.player.solidArea.getX());
        game.player.solidArea.setY(game.player.worldY + game.player.solidArea.getY());

        switch (entity.direction) {
            case "up":
                entity.solidArea.setY(entity.solidArea.getY() - entity.speed);
                break;
            case "down":
                entity.solidArea.setY(entity.solidArea.getY() + entity.speed);
                break;
            case "left":
                entity.solidArea.setX(entity.solidArea.getX() - entity.speed);
                break;
            case "right":
                entity.solidArea.setX(entity.solidArea.getX() + entity.speed);
                break;
        }
        if (entity.solidArea.intersects(game.player.solidArea.getBoundsInLocal())) {
            entity.collisionOn = true;
            contactPlayer = true;
        }


        // reset the value
        entity.solidArea.setX(entity.solidAreaDefaultX);
        entity.solidArea.setY(entity.solidAreaDefaultY);

        game.player.solidArea.setX(game.player.solidAreaDefaultX);
        game.player.solidArea.setY(game.player.solidAreaDefaultY);

        return contactPlayer;
    }

    public boolean checkPlayerShot(Shot entity) {
        boolean contactPlayer = false;
        //Get entity solid Area Position
        entity.solidArea.setX(entity.posX + entity.solidArea.getX());
        entity.solidArea.setY(entity.posY + entity.solidArea.getY());
        //Get the object's solid Area position
        Game.gameSpaceInvaders.spaceShip.solidArea.setX(Game.gameSpaceInvaders.spaceShip.posX + Game.gameSpaceInvaders.spaceShip.solidArea.getX());
        Game.gameSpaceInvaders.spaceShip.solidArea.setY(Game.gameSpaceInvaders.spaceShip.posY + Game.gameSpaceInvaders.spaceShip.solidArea.getY());

        switch (entity.direction) {
            case "up":
                entity.solidArea.setY(entity.solidArea.getY() - entity.speed);

                break;
            case "down":
                entity.solidArea.setY(entity.solidArea.getY() + entity.speed);
                break;
            case "left":
                entity.solidArea.setX(entity.solidArea.getX() - entity.speed);

                break;
            case "right":
                entity.solidArea.setX(entity.solidArea.getX() + entity.speed);

                break;
        }
        if (entity.solidArea.intersects(Game.gameSpaceInvaders.spaceShip.solidArea.getBoundsInLocal())) {
            //entity.collisionOn=true;
            contactPlayer = true;
        }


        // reset the value
        entity.solidArea.setX(entity.solidAreaDefaultX);
        entity.solidArea.setY(entity.solidAreaDefaultY);

        Game.gameSpaceInvaders.spaceShip.solidArea.setX(Game.gameSpaceInvaders.spaceShip.solidAreaDefaultX);
        Game.gameSpaceInvaders.spaceShip.solidArea.setY(Game.gameSpaceInvaders.spaceShip.solidAreaDefaultY);

        return contactPlayer;
    }


    //For GameSpaceInvaders
    public int checkEnemy(SpaceShip entity, Entity[] target) {
        int index = 999;

        for (int i = 0; i < target.length; i++) {
            if (target[i] != null) {
                //Get entity solid Area Position
                entity.solidArea.setX(entity.posX + entity.solidArea.getX());
                entity.solidArea.setY(entity.posY + entity.solidArea.getY());

                //Get the object's solid Area position
                target[i].solidArea.setX(target[i].posX + target[i].solidArea.getX());
                target[i].solidArea.setY(target[i].posY + target[i].solidArea.getY());


                switch (entity.direction) {
                    case "up":
                        entity.solidArea.setY(entity.solidArea.getY() - entity.speed);

                        break;
                    case "down":
                        entity.solidArea.setY(entity.solidArea.getY() + entity.speed);
                        break;
                    case "left":
                        entity.solidArea.setX(entity.solidArea.getX() - entity.speed);

                        break;
                    case "right":
                        entity.solidArea.setX(entity.solidArea.getX() + entity.speed);

                        break;
                }

                if (entity.solidArea.intersects(target[i].solidArea.getBoundsInLocal())) {

                    //check the object is solid or not
                    entity.collisionOn = true;
                    index = i;


                    entity.solidArea.setX(entity.solidAreaDefaultX);
                    entity.solidArea.setY(entity.solidAreaDefaultY);

                    target[i].solidArea.setX(target[i].solidAreaDefaultX);
                    target[i].solidArea.setY(target[i].solidAreaDefaultY);
                }
            }

        }
            return index;

    }
}


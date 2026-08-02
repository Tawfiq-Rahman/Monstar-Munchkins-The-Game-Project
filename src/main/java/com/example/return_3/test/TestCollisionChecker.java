package com.example.return_3.test;

import com.example.return_3.entity.Entity;

public class TestCollisionChecker {
    TestGame game;
    public TestCollisionChecker(TestGame game) {
        this.game = game;
    }

    public int checkEntity(TestEntity entity, TestEntity[] target){
        int index =999;

        for(int i=0; i<target.length;i++){
            if(target[i]!= null ){
                //Get entity solid Area Position
                entity.solidArea.setX(entity.posX + entity.solidArea.getX());
                entity.solidArea.setY(entity.posY + entity.solidArea.getY());

                //Get the object's solid Area position
                target[i].solidArea.setX(target[i].posX + target[i].solidArea.getX());
                target[i].solidArea.setY(target[i].posY + target[i].solidArea.getY());



                switch (entity.direction){
                    case "up":
                        entity.solidArea.setY(entity.solidArea.getY()-entity.speed);

                        break;
                    case "down":
                        entity.solidArea.setY(entity.solidArea.getY()+entity.speed);
                        break;
                    case "left":
                        entity.solidArea.setX(entity.solidArea.getX()-entity.speed);

                        break;
                    case "right":
                        entity.solidArea.setX(entity.solidArea.getX()+entity.speed);

                        break;
                }

                if(entity.solidArea.intersects(target[i].solidArea.getBoundsInLocal())){

                        //check the object is solid or not
                        entity.collisionOn=true;
                        index=i;
//                    System.out.println("Collision Happened");
//                    System.out.println("Player posX: "+entity.posX + " posY: "+entity.posY);
//                    System.out.println("Player solidX: "+entity.solidAreaDefaultX + " solidY: "+entity.solidAreaDefaultY);
//                    System.out.println("enemyBlack posX: "+target[i].posX + " posY: "+target[i].posY);
//                    System.out.println("enemyBlack solidX: "+target[i].solidAreaDefaultX + " solidY: "+target[i].solidAreaDefaultY);


                }
//                System.out.println("Player posX: "+entity.posX + " posY: "+entity.posY);
//                System.out.println("Player solidX: "+entity.solidArea.getX() + " solidY: "+entity.solidArea.getY());
//                System.out.println("enemyBlack posX: "+target[i].posX + " posY: "+target[i].posY);
//                System.out.println("enemyBlack solidX: "+target[i].solidArea.getX() + " solidY: "+target[i].solidArea.getY());
                // reset the value

                entity.solidArea.setX(entity.solidAreaDefaultX);
                entity.solidArea.setY(entity.solidAreaDefaultY);

                target[i].solidArea.setX(target[i].solidAreaDefaultX);
                target[i].solidArea.setY(target[i].solidAreaDefaultY) ;
            }
        }

        return index;
    }


    public boolean checkPlayer(TestEntity entity){
        boolean contactPlayer=false;
        //Get entity solid Area Position
        entity.solidArea.setX(entity.posX + entity.solidArea.getX());
        entity.solidArea.setY(entity.posY + entity.solidArea.getY());
        //Get the object's solid Area position
        game.testPlayer.solidArea.setX(game.testPlayer.posX + game.testPlayer.solidArea.getX());
        game.testPlayer.solidArea.setY(game.testPlayer.posY + game.testPlayer.solidArea.getY());

        switch (entity.direction){
            case "up":
                entity.solidArea.setY(entity.solidArea.getY()-entity.speed);

                break;
            case "down":
                entity.solidArea.setY(entity.solidArea.getY()+entity.speed);
                break;
            case "left":
                entity.solidArea.setX(entity.solidArea.getX()-entity.speed);

                break;
            case "right":
                entity.solidArea.setX(entity.solidArea.getX()+entity.speed);

                break;
        }
        if(entity.solidArea.intersects(game.testPlayer.solidArea.getBoundsInLocal())){
            entity.collisionOn=true;
            contactPlayer=true;
        }


        // reset the value
        entity.solidArea.setX(entity.solidAreaDefaultX);
        entity.solidArea.setY(entity.solidAreaDefaultY);

        game.testPlayer.solidArea.setX(game.testPlayer.solidAreaDefaultX);
        game.testPlayer.solidArea.setY(game.testPlayer.solidAreaDefaultY) ;

        return contactPlayer;
    }


    public boolean checkPlayerShot(TestShot entity){
        boolean contactPlayer=false;
        //Get entity solid Area Position
        entity.solidArea.setX(entity.posX + entity.solidArea.getX());
        entity.solidArea.setY(entity.posY + entity.solidArea.getY());
        //Get the object's solid Area position
        game.testPlayer.solidArea.setX(game.testPlayer.posX + game.testPlayer.solidArea.getX());
        game.testPlayer.solidArea.setY(game.testPlayer.posY + game.testPlayer.solidArea.getY());

        switch (entity.direction){
            case "up":
                entity.solidArea.setY(entity.solidArea.getY()-entity.speed);

                break;
            case "down":
                entity.solidArea.setY(entity.solidArea.getY()+entity.speed);
                break;
            case "left":
                entity.solidArea.setX(entity.solidArea.getX()-entity.speed);

                break;
            case "right":
                entity.solidArea.setX(entity.solidArea.getX()+entity.speed);

                break;
        }
        if(entity.solidArea.intersects(game.testPlayer.solidArea.getBoundsInLocal())){
            //entity.collisionOn=true;
            contactPlayer=true;
        }


        // reset the value
        entity.solidArea.setX(entity.solidAreaDefaultX);
        entity.solidArea.setY(entity.solidAreaDefaultY);

        game.testPlayer.solidArea.setX(game.testPlayer.solidAreaDefaultX);
        game.testPlayer.solidArea.setY(game.testPlayer.solidAreaDefaultY) ;

        return contactPlayer;
    }


}

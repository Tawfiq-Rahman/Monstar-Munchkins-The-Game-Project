package com.example.return_3.entity;

import com.example.return_3.main.Game;
import com.example.return_3.main.UtilityTool;
import com.example.return_3.monster.*;
import com.example.return_3.object.OBJ_ChatBox;
import com.example.return_3.thread.SlimeDeadThread;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;


public class Entity {

    Game game;

    // <----------VARIABLES---------->
    public String name;
    public int speed;
    public int worldX, worldY;
    public String direction = "down";
    public  int spriteNum = 1;
    public int chatNum = 1;
    public int itemCode;
    public  int itemCount;
    public int goalCol;
    public int goalRow;
    public int dialogueIndex = 0;
    public int dialogueArraySize = 0;
    public String[] dialogue =new String[20];
    public Entity attacker;
    public String knockBackDirection;


    //for space invaders
    public int posX,posY;



    // <----------IMAGE--------->
    public Image image1, image2, image3;
    public Image up1, up2, down1, down2, left1, left2, right1, right2;
    public Image attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2, guardUp, guardDown, guardLeft, guardRight;



    // <--------Boolean-------->

    public boolean collision = false;
    public boolean collisionOn = false;
    public boolean invincible = false;
    public boolean transparent = false;
    public boolean attacking = false;
    public boolean guarding  = false;
    public boolean alive = true;
    public boolean dying = false;
    public boolean npcGone = false;
    public boolean npcGoneCommand = false;
    public boolean slimeDeathOn = false;
    public boolean slimeDeath = false;
    public boolean hpBarOn = false;
    public boolean onPath = false;
    public boolean chatOnStatus = false;
    public boolean knockBack = false;
    public boolean offBalance;

    public boolean speedChange=false;




    //For SpaceInvadors
    public boolean destroyed=false;
    public boolean bottomTouched=false;



    // <---------COUNTER--------->
    public int spriteCounter = 0;
    public int chatCounter = 0;
    public int actionLookCounter=0;
    public int invincibleCounter = 0;
    public int dyingCounter = 0;
    public int hpBarCounter = 0;
    public int shotAvailableCounter = 0;
    public int knockBackCounter = 0;
    public int guardCounter = 0;
    public  int offBalanceCounter = 0;



    // <---------CHARACTER STATUS---------->
    public int maxLife;
    public int life;

    public int maxMana;
    public int mana;
    public  int ammo;



    // <------------CHARACTER ATTRIBUTES----------->
    public int playerId;
    public int level;
    public int strength;
    public int dexterity;
    public int attack;
    public int defense;
    public int energy;
    public int maxEnergy;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public Entity currentWeapon;
    public Entity currentShield;
    public Projectile projectile;
    public int defaultSpeed;
    public int motion1_duration;
    public int motion2_duration;



    // <----------ITEM ATTRIBUTES---------->
    public int attackValue;
    public int defenseValue;
    public ArrayList<Entity> inventory = new ArrayList<>();
    public int maxInventorySize = 20;
    public String description = "";
    public int useCost;
    public int price;
    public int value;
    public int knockBackPower = 0;



    // <---------Type of All Element--------->
    public int type; // 0 for player, 1 for npc, 2 for monster
    public final int type_player = 0;
    public static final int type_npc = 1;
    public final int type_wizard = 2;
    public final int type_monster = 3;
    public final int type_axe = 4;
    public final int type_sword = 5;
    public final int type_shield = 6;
    public final int type_consumable = 7;
    public final int type_pickupOnly = 8;
    public final int type_obstacle = 9;
    public final int type_OrcMonster = 10;
    public final int type_specialSword = 11;
    public final int type_fireSword = 12;
    public final int type_iceSword = 13;
    public final int type_fireball = 14;
    public final int type_tomahawkAxe = 15;
    public final int type_tree = 16;
    public final int type_rock = 17;


    // <---------Type of NPC--------->
    public int npc_area;
    public final int area_village=1;
    // <---------Type of Monster--------->
    public int monster_type;
    public static final int type_slime = 1;
    public final int type_pacman = 2;
    public final int type_spider = 3;
    public final int type_worm = 4;
    public final int type_arc = 5;
    public final int type_pacmanGreen = 6;
    public final int type_blueGhost = 7;
    public final int type_redOrc = 8;
    public final int type_redFly = 9;
    public final int type_skeleton = 10;
    public final int type_spiderBrown = 11;
    public final int type_slimeMother = 12;
    public final int type_sixEyes = 13;


    // <---------Area of Monster--------->
    public int monster_area;
    public final int area_th_1 = 1;
    public final int area_th_2 = 2;
    public final int area_th_3 = 3;
    public final int area_th_4 = 4;
    public final int area_th_5 = 5;
    public final int area_hill_1 = 6;
    public final int area_hill_2 = 7;
    public final int area_hill_3 = 8;
    public final int area_hill_4 = 9;
    public final int area_mi_1 = 10;
    public final int area_mi_2 = 11;
    public final int area_mi_3 = 12;


  // <-------------COLLiSION------------->
    public Rectangle solidArea = new Rectangle(0,0,32,32);
    public Rectangle attackArea = new Rectangle(0,0,0,0);
    public int solidAreaDefaultX,solidAreaDefaultY;

 public UtilityTool uTool;

    public Entity(Game game){
        this.game=game;
         uTool=new UtilityTool();
    }
    //get entity properties
    public double getLeftX(){
        return  (worldX+solidArea.getX());
    }
    public double getRightX(){
        return (worldX+solidArea.getX()+solidArea.getWidth());
    }
    public double getTopY(){
        return worldY+solidArea.getY();
    }
    public double getBottomY(){
        return worldY+solidArea.getY()+solidArea.getHeight();
    }
    public int getCol(){
        return (int) ((worldX+solidArea.getX())/game.tileSize);
    }
    public int getRow(){
        return (int) ((worldY+solidArea.getY())/game.tileSize);
    }

// Return `TRUE` if you used the item and `FALSE` if you failed to use it
    public boolean use(Entity entity){
        return false;
    }
    public void interact(){}
    public void checkDrop(){}
    public void dropItem(Entity droppedItem){
        for (int i=0; i<game.obj[0].length;i++){
            if(game.obj[game.currentMap][i]==null){
                game.obj[game.currentMap][i]=droppedItem;
                game.obj[game.currentMap][i].worldX=worldX; //DEAD MONSTERS X POSITION
                game.obj[game.currentMap][i].worldY=worldY; //DEAD MONSTERS Y POSITION
                break;
            }
        }
    }


    public int getXDistance(Entity target) {
        int xDistance = Math.abs(worldX - target.worldX);
        return xDistance;
    }
    public int getYDistance(Entity target) {
        int yDistance = Math.abs(worldY - target.worldY);
        return yDistance;
    }
    public int getTileDistance(Entity target) {
        int tileDistance = (getXDistance(target) + getYDistance(target)) / game.tileSize;
        return tileDistance;
    }
    public int getGoalCol(Entity target) {
        int goalCol = (int)(target.worldX + target.solidArea.getX()) / game.tileSize;
        return goalCol;
    }
    public int getGoalRow(Entity target) {
        int goalRow = (int)(target.worldY + target.solidArea.getY()) / game.tileSize;
        return goalRow;
    }


    public void setAction(){}
    public void damageReaction(){}
    public void checkCollision(){
        collisionOn=false;
        //CHeching part of collision so that entity got collision and can not move
        game.cChecker.checkTile(this);
        game.cChecker.checkPlayer(this);
        game.cChecker.checkObject(this,false); //its not player so its remain false
//        game.cChecker.checkEntity(this,game.npc);
        game.cChecker.checkEntity(this,game.interactNpc);
        game.cChecker.checkEntity(this,game.monster);
        game.cChecker.checkEntity(this,game.iTile);


    }
    public void update(){
        if (knockBack == true) {

            checkCollision();

            if (collisionOn == true) {
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            }
            else if (collisionOn == false) {

                switch (knockBackDirection) {
                    case "up":worldY -= speed; break;
                    case "down":worldY+= speed; break;
                    case "left":worldX -= speed; break;
                    case "right":worldX += speed; break;
                }
            }

            knockBackCounter++;
            if (knockBackCounter == 2) {  // KnockBack distance....
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            }

        } else if (attacking == true) {
            attacking();
        } else {
            setAction();
            checkCollision();

            //if collisionOn is false then player can be able to move
            if(collisionOn == false){

                switch (direction){
                    case "up":worldY -= speed; break;
                    case "down":worldY+= speed; break;
                    case "left":worldX -= speed; break;
                    case "right":worldX += speed; break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 24) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }


        //MONSTER ATTACK ON PLAYER.
        boolean contactPlayer= game.cChecker.checkPlayer(this);
        if(contactPlayer){
            if(type == type_OrcMonster){
                damagePlayerOrc(attack);
            }else{
                damagePlayer(attack);
            }
        }



        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 40) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if(shotAvailableCounter<30){
            shotAvailableCounter++;
        }

        if (offBalance == true) {
            offBalanceCounter++;
            if (offBalanceCounter > 60) {
                offBalance = false;
                offBalanceCounter = 0;
            }
        }

        set_Monster_Area();

    }


    public void checkShootOrNot(int rate, int shootInterval) {
        int i = new Random().nextInt(rate);
        if (i == 0 && projectile.alive == false && shotAvailableCounter == shootInterval) {
            projectile.set(worldX, worldY, direction, true, this);

            // Check vacancy...
            for (int ii = 0; ii < game.projectile[game.currentMap].length; ii++) {
                if (game.projectile[game.currentMap][ii] == null) {
                    game.projectile[game.currentMap][ii] = projectile;
                    break;
                }
            }
            shotAvailableCounter = 0;
        }
    }

    public void checkStartChasingOrNot(Entity target, int distance, int rate) {
        if (getTileDistance(target) < distance) {
            int i = new Random().nextInt(rate);
            if (i > 50) {
                onPath = true;
            }
        }

    }

    public void checkStopChasingOrNot(Entity target, int distance, int rate) {
        if (getTileDistance(target) > distance) {
            int i = new Random().nextInt(rate);
            if (i == 0) {
                onPath = false;
            }
        }

    }

    public void getRandomDirection() {
        actionLookCounter++;
        Random random= new Random();
        int actionLookCounterLimit = random.nextInt(50)+90;

        if(actionLookCounter > actionLookCounterLimit){   // for two seconds it means

            int i=random.nextInt(100)+1; //we add 1 because otherwise it will catch 0 to 99.. we want to avoid 0 here
            if(i<=25){
                direction="up";
            }else
            if(i<=50){
                direction="down";
            }else
            if(i<=75){
                direction="left";
            }else
            if(i>75){
                direction="right";
            }
            actionLookCounter=0;
        }
    }


    public String getOppositeDirection(String direction) {
        String oppositeDirection = "";
        switch (direction) {
            case "up" : oppositeDirection = "down"; break;
            case "down" : oppositeDirection = "up"; break;
            case "left" : oppositeDirection = "right"; break;
            case "right" : oppositeDirection = "left"; break;
        }
        return oppositeDirection;
    }


    public void attacking() {
        spriteCounter++;
        if (spriteCounter <= motion1_duration) {
            spriteNum = 1;
        }
        if (spriteCounter > motion1_duration && spriteCounter <= motion2_duration) {
            spriteNum = 2;

            // SAVE THE CURRENT DATA......
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = (int) solidArea.getWidth();
            int solidAreaHeight = (int) solidArea.getHeight();

            // ADJUST PLAYRER S WORLD X/Y FOR THE ATTACK AREA
            switch (direction) {
                case "up" : worldY -= (int) attackArea.getHeight(); break;
                case "down" : worldY += (int) attackArea.getHeight(); break;
                case "left" : worldX -= (int) attackArea.getWidth(); break;
                case "right" : worldX += (int) attackArea.getWidth(); break;
            }
            // ATTACK BECOME SOLID AREA
            solidArea.setWidth(attackArea.getWidth());
            solidArea.setHeight(attackArea.getHeight());


            if (type == type_OrcMonster) {
                if (game.cChecker.checkPlayer(this) == true) {
                    damagePlayerOrc(attack);
                }
            }
            else { // Player
                //CHECK monster collision with the updated worldX, worldY and solidArea....
                int monsterIndex = game.cChecker.checkEntity(this, game.monster);
                if(game.player.currentWeapon!=null && monsterIndex!=999){
                    if(game.monster[game.currentMap][monsterIndex] instanceof Mon_RedFly){
                        if(game.player.currentWeapon.type==type_fireball){
                            game.player.damagedMonster(monsterIndex, this, attack, currentWeapon.knockBackPower);
                        }
                    }else{
                        game.player.damagedMonster(monsterIndex, this, attack, currentWeapon.knockBackPower);
                    }

                }

                //CHECK INTERACTIVE TILES COLLSION AND GET ATTACK
                int iTileIndex= game.cChecker.checkEntity(this,game.iTile);
                game.player.damageInteractiveTiles(iTileIndex);

                int projectileIndex = game.cChecker.checkEntity(this, game.projectile);
                game.player.damageProjectile(projectileIndex);
            }

//            System.out.println("solidAreaX: "+solidArea.getX());
//            System.out.println("solidAreaY: "+solidArea.getY());
//
//            System.out.println("solidArea Width"+solidArea.getWidth());
//            System.out.println("solidArea height"+solidArea.getHeight());
            // After checking collision restore the original data...
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.setWidth(solidAreaWidth);
            solidArea.setHeight(solidAreaHeight);
        }
        if (spriteCounter > motion2_duration) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void checkAttackOrNot(int rate, int straight, int horizontal) {

        boolean targetInRange = false;
        int xDis = getXDistance(game.player);
        int yDis = getYDistance(game.player);

        switch (direction) {
            case "up":
                if (game.player.worldY < worldY && yDis < straight && xDis < horizontal) {
                    targetInRange = true;
                }
                break;
            case "down":
                if (game.player.worldY > worldY && yDis < straight && xDis < horizontal) {
                    targetInRange = true;
                }
                break;
            case "left":
                if (game.player.worldY < worldX && xDis < straight && yDis < horizontal) {
                    targetInRange = true;
                }
                break;
            case "right":
                if (game.player.worldY > worldX && xDis < straight && yDis < horizontal) {
                    targetInRange = true;
                }
                break;
        }
        if (targetInRange == true) {
            // Check if it initiates an attack...
            int i = new Random().nextInt(rate);
            if (i == 0) {
                attacking = true;
                spriteNum = 1;
                spriteCounter = 0;
                shotAvailableCounter = 0;
            }
        }
    }

    public void damagePlayer(int attack){
        if (game.player.invincible == false) {
            game.playSoundEffect(6);
            int damage = attack - game.player.defense;

            // Get and opposite direction of an attacker...
            String canGuardDirection = getOppositeDirection(direction);
            if (game.player.guarding == true && game.player.direction.equals(canGuardDirection)) {
                // Parry : // if you parry 10 frame before monsters attack you can parry monster...if you increase the value the parry became easier...
                if (game.player.guardCounter < 10) {
                    damage = 0;
                    setKnockBack(this, game.player, game.player.currentShield.knockBackPower);
                    offBalance = true;
                    spriteCounter -= 60;
                }

                // Normal guard...
                damage /= 3;
            }
            else {
                // Not Guarding...
                if (damage < 0) {
                    damage = 0;
                }
            }

            if (damage != 0) {
                game.player.transparent = true;
                setKnockBack(game.player, this, knockBackPower);
            }

            game.player.life -= damage;
            game.player.invincible = true;

            if (game.player.life >= 0 && game.player.life <= 20) {
                game.ui.uiMainGame.addMessage( "Careful, " + game.player.life + " % Life left!");
            }
            if (game.player.life <= 0) {
                game.playSoundEffect(game.soundEffect.playerDeath);
                game.player.dying = true;
                game.player.setHospitalPosition();
                game.keyHandler.setBooleanAll(false);
                game.player.life = game.player.maxLife;
                if (game.player.coin >= 200) {
                    game.player.coin -= 200;
                }
                else {
                    game.player.coin = 0;
                }
            }
        }
    }

    public void damagePlayerOrc(int attack){

        if (game.player.invincible == false && attacking == true) {
            game.playSoundEffect(6);
            int damage = attack - game.player.defense;

            // Get and opposite direction of an attacker...
            String canGuardDirection = getOppositeDirection(direction);
            if (game.player.guarding == true && game.player.direction.equals(canGuardDirection)) {
                // Parry : // if you parry 10 frame before monsters attack you can parry monster...if you increase the value the parry became easier...
                if (game.player.guardCounter < 30) {
                    damage = 0;
                    setKnockBack(this, game.player, game.player.currentShield.knockBackPower);
                    offBalance = true;
                    spriteCounter -= 60;
                }

                // Normal guard...
                damage /= 3;
            }
            else {
                // Not Guarding...
                if (damage < 0) {
                    damage = 0;
                }
            }

            if (damage != 0) {
                game.player.transparent = true;
                setKnockBack(game.player, this, knockBackPower);
            }

            game.player.life -= damage;
            game.player.invincible = true;

            if (game.player.life >= 0 && game.player.life <= 20) {
                game.ui.uiMainGame.addMessage( "Careful, " + game.player.life + " % Life left!");
            }
            if (game.player.life <= 0) {
                game.player.dying = true;
                game.playSoundEffect(game.soundEffect.playerDeath);


                game.player.setHospitalPosition();
                game.keyHandler.setBooleanAll(false);
                game.player.life = game.player.maxLife;
                if (game.player.coin >= 300) {
                    game.player.coin -= 300;
                }
                else {
                    game.player.coin = 0;
                }
            }
        }
    }
    public void setKnockBack(Entity target, Entity attacker, int knockBackPower) {
        this.attacker = attacker;
        target.knockBackDirection = attacker.direction;
        target.speed += knockBackPower;
        target.knockBack = true;
    }

    public void draw(GraphicsContext gc){
        Image image= null;
        int screenX= worldX-game.player.worldX + game.player.screenX;
        int screenY= worldY-game.player.worldY + game.player.screenY;


        //this process is for not drawing the whole world map but the map tiles we needed only
        if(worldX+ game.tileSize>game.player.worldX-game.player.screenX&&
                worldX-game.tileSize<game.player.worldX+game.player.screenX&&
                worldY+ game.tileSize>game.player.worldY-game.player.screenY&&
                worldY- game.tileSize<game.player.worldY+game.player.screenY
        ){
            //temp variable
            int tempScreenX = screenX;
            int tempScreenY = screenY;

            switch (direction) {
                case "up":
                    if (attacking == false) {
                        if (spriteNum == 1){
                            //playerImageView.setImage(up1);
                            image = up1;
                        }
                        if(spriteNum == 2){
                            image = up2;
                        }
                    }
                    if (attacking == true) {
                        tempScreenY = screenY - game.tileSize;
                        if (spriteNum == 1){
                            image = attackUp1;
                        }
                        if(spriteNum == 2){
                            image = attackUp2;
                        }
                    }
                    break;
                case "down":
                    if (attacking == false) {
                        if (spriteNum == 1){

                            image = down1;
                        }
                        if(spriteNum == 2){
                            image = down2;
                        }
                    }
                    if (attacking == true) {
                        if (spriteNum == 1){

                            image = attackDown1;
                        }
                        if(spriteNum == 2){
                            image = attackDown2;
                        }
                    }
                    break;
                case "left":
                    if (attacking == false) {
                        if (spriteNum == 1){

                            image = left1;
                        }
                        if(spriteNum == 2){
                            image = left2;
                        }
                    }
                    if (attacking == true) {
                        tempScreenX = screenX - game.tileSize;
                        if (spriteNum == 1){
                            image = attackLeft1;
                        }
                        if(spriteNum == 2){
                            image = attackLeft2;
                        }
                    }
                    break;
                case "right":
                    if (attacking == false) {
                        if (spriteNum == 1){

                            image = right1;
                        }
                        if(spriteNum == 2){
                            image = right2;
                        }
                    }
                    if (attacking == true) {
                        if (spriteNum == 1){

                            image = attackRight1;
                        }
                        if(spriteNum == 2){
                            image = attackRight2;
                        }
                    }
                    break;

                // Handle other directions similarly
            }


            //Monster HP Bar......
            if ((type == type_monster || type == type_OrcMonster) && hpBarOn) {

                if (monster_type == type_slimeMother) {
                    double oneScale = (double)game.tileSize *3 /maxLife;
                    double hpBarValue = oneScale * life;
                    gc.setFill(Color.RED);
                    gc.fillRect(screenX, screenY - 5, (int)hpBarValue, 10);
                    gc.setStroke(Color.BLACK);
                    gc.setLineWidth(1); // Width of the outline
                    gc.strokeRect(screenX, screenY - 5, game.tileSize * 3, 10);

                    hpBarCounter++;
                    if (hpBarCounter > 600) {
                        hpBarCounter = 0;
                        hpBarOn = false;
                    }
                } else {
                    double oneScale = (double)game.tileSize/maxLife;
                    double hpBarValue = oneScale*life;
                    gc.setFill(Color.RED);
                    gc.fillRect(screenX, screenY - 5, (int)hpBarValue, 10);
                    gc.setStroke(Color.BLACK);
                    gc.setLineWidth(1); // Width of the outline
                    gc.strokeRect(screenX, screenY - 5, game.tileSize, 10);

                    hpBarCounter++;
                    if (hpBarCounter > 600) {
                        hpBarCounter = 0;
                        hpBarOn = false;
                    }
                }

            }
//            if ((type == type_slimeMother) && hpBarOn == true) {
//                double oneScale = (double)game.tileSize/maxLife;
//                double hpBarValue = oneScale*life;
//
//
//                gc.setFill(Color.RED);
//                gc.fillRect(screenX, screenY - 5, (int)hpBarValue, 10);
//                gc.setStroke(Color.BLACK);
//                gc.setLineWidth(1); // Width of the outline
//                gc.strokeRect(screenX, screenY - 5, game.tileSize * 3, 10);
//
//                hpBarCounter++;
//                if (hpBarCounter > 600) {
//                    hpBarCounter = 0;
//                    hpBarOn = false;
//                }
//            }


            if (invincible == true) {
                hpBarOn = true;
                hpBarCounter = 0;
                gc.setGlobalAlpha(0.4);
            }
            if (dying == true) {
                dyingAnimation(gc);
            }
            if(npcGone==true) {
                goneAnimation(gc);
            }


            gc.drawImage(image,tempScreenX,tempScreenY);

            //<<<<<RESET ALPHA>>>>>
            gc.setGlobalAlpha(1);




        }
    }

    public void dyingAnimation(GraphicsContext gc) {

        dyingCounter++;
        int i = 5;
        if (dyingCounter <= i) {gc.setGlobalAlpha(0);}
        if (dyingCounter > i && dyingCounter <= i*2) {gc.setGlobalAlpha(1);}
        if (dyingCounter > i*2 && dyingCounter <= i*3) {gc.setGlobalAlpha(0);}
        if (dyingCounter > i*3 && dyingCounter <= i*4) {gc.setGlobalAlpha(1);}
        if (dyingCounter > i*4 && dyingCounter <= i*5) {gc.setGlobalAlpha(0);}
        if (dyingCounter > i*5 && dyingCounter <= i*6) {gc.setGlobalAlpha(1);}
        if (dyingCounter > i*6 && dyingCounter <= i*7) {gc.setGlobalAlpha(0);}
        if (dyingCounter > i*7 && dyingCounter <= i*8) {gc.setGlobalAlpha(1);}
        if (dyingCounter > i*8) {
            alive = false;
        }
    }

    public void goneAnimation(GraphicsContext gc){
        System.out.println("transition method called");
        if(alive){
        game.ui.uiMainGame.transitionCounter++;
        }
        // Calculate opacity and ensure it stays within the valid range
        double alpha =  1.0 - ((double) game.ui.uiMainGame.transitionCounter / 40);; // This ensures the value goes from 0.0 to 1.0 over 50 steps
        if (alpha >= 0) {
            gc.setGlobalAlpha(alpha);
        }else{
            alive=false;
            game.ui.uiMainGame.transitionCounter=0;
        }
    }


    public void speak(){

    }


    public void set_NPC_Area(){ // we use different area here  for different position
        switch (npc_area){ //x is for map starting position and y is for map ending position
            //width and height give us the expected recatngular area for us
            case area_village:uTool.areaSetup(this,36*game.tileSize,133*game.tileSize,12*game.tileSize,4*game.tileSize);   ;break;
//            case area_village:        areaSetup(36*game.tileSize,133*game.tileSize,12*game.tileSize,4*game.tileSize);   ;break;
        }
    }

    public void set_Monster_Area(){    // we use different area here  for different position
        switch (monster_area){    //x is for map starting position and y is for map ending position
            // width and height give us the expected rectangular area for us
            case area_th_1 : uTool.areaSetup(this,36*game.tileSize,113*game.tileSize,37*game.tileSize,48*game.tileSize);   ;break;
            case area_th_2 : uTool.areaSetup(this,93*game.tileSize,119*game.tileSize,43*game.tileSize,19*game.tileSize) ;break;
            case area_th_3 : uTool.areaSetup(this,139*game.tileSize,144*game.tileSize,47*game.tileSize,41*game.tileSize) ;break;
            case area_th_4 : uTool.areaSetup(this,75*game.tileSize,156*game.tileSize,61*game.tileSize,29*game.tileSize) ;break;
            case area_th_5 : uTool.areaSetup(this,16*game.tileSize,163*game.tileSize,57*game.tileSize,22*game.tileSize) ;break;
            case area_hill_1 : uTool.areaSetup(this,142*game.tileSize,100*game.tileSize,44*game.tileSize,28*game.tileSize) ;break;
            case area_hill_2 : uTool.areaSetup(this,95*game.tileSize,78*game.tileSize,45*game.tileSize,26*game.tileSize) ;break;
            case area_hill_3 : uTool.areaSetup(this,18*game.tileSize,65*game.tileSize,76*game.tileSize,18*game.tileSize) ;break;
            case area_hill_4 : uTool.areaSetup(this,16*game.tileSize,85*game.tileSize,58*game.tileSize,27*game.tileSize) ;break;
            case area_mi_1 : uTool.areaSetup(this,107*game.tileSize,14*game.tileSize,36*game.tileSize,25*game.tileSize) ;break;
            case area_mi_2 : uTool.areaSetup(this,149*game.tileSize,14*game.tileSize,38*game.tileSize,25*game.tileSize) ;break;
            case area_mi_3 : uTool.areaSetup(this,153*game.tileSize,40*game.tileSize,34*game.tileSize,24*game.tileSize) ;break;



        }
    }

    public Image loadImage(String imagePath, int width, int height) {
        return new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)), width, height, true, true);
    }

    public Color getParticleColor(){ //this indicates the color of the particle
         Color color=  null;
        return color;
    }
    public int getParticleSize(){// this indicates the size of the particle
        int size=0;
        return size;
    }
    public int getParticleSpeed(){ //this indicates the speed of the particle
        int speed=0;
        return speed;
    }

    public int getParticleMaxLife(){ //this indicates how long the particle will last
        int maxLife=0;
        return maxLife;
    }

    //this method is for giving a little animation on falling particles

    public void generateParticle(Entity generator,Entity target){
        //We will do it later
        Color color= generator.getParticleColor();
        int size= generator.getParticleSize();
        int speed= generator.getParticleSpeed();
        int maxLife= generator.getParticleMaxLife();



        //creating particle
        Particle p1= new Particle(game,target,color,size,speed,maxLife,-2,-1 );
        Particle p2= new Particle(game,target,color,size,speed,maxLife,2,-1 );
        Particle p3= new Particle(game,target,color,size,speed,maxLife,-2,1 );
        Particle p4= new Particle(game,target,color,size,speed,maxLife,2,1 );
        game.particleList.add(p1);
        game.particleList.add(p2);
        game.particleList.add(p3);
        game.particleList.add(p4);
    }

    public void searchPath(int goalCol, int goalRow){

        int startCol=(int)(worldX+solidArea.getX())/game.tileSize;
        int startRow=(int)(worldY+solidArea.getY())/game.tileSize;

        game.pFinder.setNodes(startCol, startRow, goalCol,goalRow);

        if(game.pFinder.search()==true){
            //Next worldX & worldY
            int nextX= game.pFinder.pathList.get(0).col *game.tileSize;
            int nextY= game.pFinder.pathList.get(0).row *game.tileSize;

            //Entity's solidArea position
            int enleftX=(int)(worldX + solidArea.getX());
            int enRightX=(int)(worldX + solidArea.getX()+solidArea.getWidth());
            int enTopY=(int)(worldY+solidArea.getY());
            int enBottomY=(int)(worldY+solidArea.getY()+solidArea.getHeight());

            if(enTopY>nextY && enleftX>=nextX && enRightX< nextX+game.tileSize){
                direction= "up";
            }else if(enTopY<nextY && enleftX>=nextX && enRightX< nextX+game.tileSize){
                direction= "down";
            }else if(enTopY>= nextY && enBottomY<nextY+game.tileSize){
                //left or right
                if(enleftX>nextX){
                    direction="left";
                }if(enleftX<nextX){
                    direction="right";
                }
            } else if (enTopY>nextY && enleftX> nextX) {
                //up or left
                direction="up";
                checkCollision();
                if(collisionOn==true){
                    direction="left";
                }
            } else if (enTopY > nextY && enleftX < nextX) {
                //up or right
                direction="up";
                checkCollision();
                if(collisionOn==true){
                    direction="right";
                }
            }else if(enTopY< nextY && enleftX>nextX) {
                //down or left
                direction="down";
                checkCollision();
                if(collisionOn==true){
                    direction="left";
                }
            }else if(enTopY< nextY && enleftX<nextX) {
                //down or right
                direction="down";
                checkCollision();
                if(collisionOn==true){
                    direction="right";
                }
            }
            int nextCol=game.pFinder.pathList.get(0).col;
            int nextRow=game.pFinder.pathList.get(0).row;

            if(nextCol==goalCol&& nextRow==goalRow){
                onPath=false;
            }

        }
    }

    public int getDetected(Entity user, Entity target[][], String targetName){
        int index= 999;
        //check the sourrending object
        double nextWorldX=user.getLeftX();
        double nextWorldY=user.getTopY();
        switch(user.direction){
            case "up":nextWorldY=user.getTopY()-10;break;
            case "down":nextWorldY=user.getBottomY()+1;break;
            case "left":nextWorldX=user.getLeftX()-1;break;
            case "right":nextWorldX=user.getRightX()+1;break;
        }
        int col= (int) (nextWorldX/game.tileSize);
        int row= (int)(nextWorldY/game.tileSize);

        for(int i=0;i<target[1].length;i++){
            if(target[game.currentMap][i]!=null){
                if(target[game.currentMap][i].getCol()==col &&
                target[game.currentMap][i].getRow()==row &&
                target[game.currentMap][i].name.equals(targetName)){
                    index=i;
                    break;
                }
            }
        }

        return index;
    }


    public int getAttack() {
        return 0;
    }

    public int getDefense() {
        return 0;
    }
}

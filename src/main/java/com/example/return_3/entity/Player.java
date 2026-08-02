package com.example.return_3.entity;

import com.example.return_3.db.MyJDBC;
import com.example.return_3.main.Game;
import com.example.return_3.main.KeyHandler;
import com.example.return_3.monster.*;
import com.example.return_3.object.*;
import com.example.return_3.thread.SlimeDeadThread;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;


import java.io.IOException;
import java.util.Objects;


public class Player extends Entity{
    //VARIABLES
    public KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;

    public final int backPackSize=10;


    //part 8 Object Interaction part starts
//    public  int hasKey=0;
    //part 8 Object Interaction part ends

    public boolean attackCanceled=false;
    public Player(Game game) {
        super(game);
        this.keyHandler = game.keyHandler;
//        maxInventorySize=12;
        // Get the width and height of the screen
        double screenWidth = Game.gameScene.getWidth();
        double screenHeight = Game.gameScene.getHeight();
        // Calculate the middle point
        screenX = (int) (screenWidth / 2);
        screenY = (int) (screenHeight / 2);

        //part 6 collision part starts
        solidArea= new Rectangle(); //we can skip this session. (as we already make it in Entity class)
        solidArea.setX(9);
        solidArea.setY(14);

        //part 8 Object Interaction part starts
        solidAreaDefaultX=(int)(solidArea.getX());
        solidAreaDefaultY=(int)(solidArea.getY());
        //part 8 Object Interaction part  ends

        solidArea.setWidth(14);
        solidArea.setHeight(10);
        //part 6 collision part ends

        //We are handling the attack area in the weapon class
//        attackArea.setWidth(32);
//        attackArea.setHeight(28);



        setDefaultValues();
        //setItems(); //as in the initial stage we will not set any weapon for our game so we are not using this right now
        loadPlayerImages();    // Load player images and initialize ImageView
        //loadPlayerAttackImages(); // Load player attack images only work when player will choose different weapon
        //that is why we handle it in select items method



    }


    private void loadPlayerImages() {
        up1 = loadImage("/player/boy_up_1.png", game.tileSize, game.tileSize);
        up2 = loadImage("/player/boy_up_2.png", game.tileSize, game.tileSize);
        down1 = loadImage("/player/boy_down_1.png", game.tileSize, game.tileSize);
        down2 = loadImage("/player/boy_down_2.png", game.tileSize, game.tileSize);
        left1 = loadImage("/player/boy_left_1.png", game.tileSize, game.tileSize);
        left2 = loadImage("/player/boy_left_2.png", game.tileSize, game.tileSize);
        right1 = loadImage("/player/boy_right_1.png", game.tileSize, game.tileSize);
        right2 = loadImage("/player/boy_right_2.png", game.tileSize, game.tileSize);

        //System.out.println("Player loaded");
    }
    private void loadPlayerAttackImages() {

        if(currentWeapon.type==type_sword) {
            attackUp1 = loadImage("/player/attacking_up_1.png", game.tileSize, game.tileSize * 2);
            attackUp2 = loadImage("/player/attacking_up_2.png", game.tileSize, game.tileSize * 2);
            attackDown1 = loadImage("/player/attacking_down_1.png", game.tileSize, game.tileSize * 2);
            attackDown2 = loadImage("/player/attacking_down_2.png", game.tileSize, game.tileSize * 2);
            attackLeft1 = loadImage("/player/attacking_left_1.png", game.tileSize * 2, game.tileSize);
            attackLeft2 = loadImage("/player/attacking_left_2.png", game.tileSize * 2, game.tileSize);
            attackRight1 = loadImage("/player/attacking_right_1.png", game.tileSize * 2, game.tileSize);
            attackRight2 = loadImage("/player/attacking_right_2.png", game.tileSize * 2, game.tileSize);
        }
        else if(currentWeapon.type==type_axe){
            attackUp1=loadImage( "/player/boy_axe_up_1.png",game.tileSize,game.tileSize*2);
            attackUp2= loadImage("/player/boy_axe_up_2.png",game.tileSize,game.tileSize*2);
            attackDown1= loadImage("/player/boy_axe_down_1.png",game.tileSize,game.tileSize*2);
            attackDown2= loadImage("/player/boy_axe_down_2.png",game.tileSize,game.tileSize*2);
            attackLeft1=loadImage ("/player/boy_axe_left_1.png",game.tileSize*2,game.tileSize);
            attackLeft2= loadImage("/player/boy_axe_left_2.png",game.tileSize*2,game.tileSize);
            attackRight1= loadImage("/player/boy_axe_right_1.png",game.tileSize*2,game.tileSize);
            attackRight2= loadImage("/player/boy_axe_right_2.png",game.tileSize*2,game.tileSize);
        }else if(currentWeapon.type==type_tomahawkAxe) {
            attackUp1 = loadImage("/player/boy_axe1_up_1.png", game.tileSize, game.tileSize * 2);
            attackUp2 = loadImage("/player/boy_axe1_up_2.png", game.tileSize, game.tileSize * 2);
            attackDown1 = loadImage("/player/boy_axe1_down_1.png", game.tileSize, game.tileSize * 2);
            attackDown2 = loadImage("/player/boy_axe1_down_2.png", game.tileSize, game.tileSize * 2);
            attackLeft1 = loadImage("/player/boy_axe1_left_1.png", game.tileSize * 2, game.tileSize);
            attackLeft2 = loadImage("/player/boy_axe1_left_2.png", game.tileSize * 2, game.tileSize);
            attackRight1 = loadImage("/player/boy_axe1_right_1.png", game.tileSize * 2, game.tileSize);
            attackRight2 = loadImage("/player/boy_axe1_right_2.png", game.tileSize * 2, game.tileSize);
        }
        else if(currentWeapon.type==type_specialSword) {
            attackUp1 = loadImage("/player/attackingSpecial_up_1.png", game.tileSize, game.tileSize * 2);
            attackUp2 = loadImage("/player/attackingSpecial_up_2.png", game.tileSize, game.tileSize * 2);
            attackDown1 = loadImage("/player/attackingSpecial_down_1.png", game.tileSize, game.tileSize * 2);
            attackDown2 = loadImage("/player/attackingSpecial_down_2.png", game.tileSize, game.tileSize * 2);
            attackLeft1 = loadImage("/player/attackingSpecial_left_1.png", game.tileSize * 2, game.tileSize);
            attackLeft2 = loadImage("/player/attackingSpecial_left_2.png", game.tileSize * 2, game.tileSize);
            attackRight1 = loadImage("/player/attackingSpecial_right_1.png", game.tileSize * 2, game.tileSize);
            attackRight2 = loadImage("/player/attackingSpecial_right_2.png", game.tileSize * 2, game.tileSize);
        }
        else if(currentWeapon.type==type_fireSword) {
            attackUp1 = loadImage("/player/attackingFire_up_1.png", game.tileSize, game.tileSize * 2);
            attackUp2 = loadImage("/player/attackingFire_up_2.png", game.tileSize, game.tileSize * 2);
            attackDown1 = loadImage("/player/attackingFire_down_1.png", game.tileSize, game.tileSize * 2);
            attackDown2 = loadImage("/player/attackingFire_down_2.png", game.tileSize, game.tileSize * 2);
            attackLeft1 = loadImage("/player/attackingFire_left_1.png", game.tileSize * 2, game.tileSize);
            attackLeft2 = loadImage("/player/attackingFire_left_2.png", game.tileSize * 2, game.tileSize);
            attackRight1 = loadImage("/player/attackingFire_right_1.png", game.tileSize * 2, game.tileSize);
            attackRight2 = loadImage("/player/attackingFire_right_2.png", game.tileSize * 2, game.tileSize);
        }
        else if(currentWeapon.type==type_iceSword) {
            attackUp1 = loadImage("/player/attackingIce_up_1.png", game.tileSize, game.tileSize * 2);
            attackUp2 = loadImage("/player/attackingIce_up_2.png", game.tileSize, game.tileSize * 2);
            attackDown1 = loadImage("/player/attackingIce_down_1.png", game.tileSize, game.tileSize * 2);
            attackDown2 = loadImage("/player/attackingIce_down_2.png", game.tileSize, game.tileSize * 2);
            attackLeft1 = loadImage("/player/attackingIce_left_1.png", game.tileSize * 2, game.tileSize);
            attackLeft2 = loadImage("/player/attackingIce_left_2.png", game.tileSize * 2, game.tileSize);
            attackRight1 = loadImage("/player/attackingIce_right_1.png", game.tileSize * 2, game.tileSize);
            attackRight2 = loadImage("/player/attackingIce_right_2.png", game.tileSize * 2, game.tileSize);
        }
    }

    public void loadPlayerGuardImages() {
        if (currentShield.type == type_shield) {
            guardUp = loadImage("/player/boy_guard_up.png", game.tileSize, game.tileSize);
            guardDown = loadImage("/player/boy_guard_down.png", game.tileSize, game.tileSize);
            guardLeft = loadImage("/player/boy_guard_left.png", game.tileSize, game.tileSize);
            guardRight = loadImage("/player/boy_guard_right.png", game.tileSize, game.tileSize);
        }

    }

//    public void setItems(){
//        inventory.clear();
//        inventory.add(currentWeapon);
//        inventory.add(currentShield);
//    }

    public void setDefaultValues(){
        setDefaultPositions();
        //defaultSpeed = (int) (250*game.targetFrameTime);  //pixel per second
        defaultSpeed = 3;
        speed = defaultSpeed;


        // <---------Player Status--------->
        playerId=game.user.getUserId();
        level = game.user.getLevel();
        maxLife = game.user.getMaxLife();
        life = game.user.getLife();
        strength = game.user.getStrength();    //The more strength he has, the more damage he gives...
        dexterity = game.user.getDexterity();   // The more dexterity he has, the less damage he receives...
        exp = game.user.getExp();
        nextLevelExp = 50;
        coin = game.user.getCoin();
        //as we are not setting current weapon and sheild at the beginning so that is why we commented it
        //currentWeapon = new OBJ_Sword_Normal(game);
        //currentShield = new OBJ_Shield_Wood(game);
        projectile = new OBJ_Fireball(game);
        //as there is not current weapon and shield at the beginning so we do not need to calculate attack or sheild defense
        //attack = getAttack();    // The total attack value is decided by strength and weapon...
        //defense = getDefense();   // The total defence value is decided by dexterity and shield...

        energy = game.user.getEnergy();
        maxEnergy = game.user.getMaxEnergy();
//        maxMana = game.user.getBullet();
        maxMana = 10;
        //mana = game.user.getBullet();
        mana = game.fireBall.mana;
        //ammo=10;
        inventory= MyJDBC.getUserInventory(playerId);


    }

    public int getDefense() {
        return defense = dexterity * currentShield.defenseValue;
    }
    public int getAttack() {
        attackArea=currentWeapon.attackArea;
        motion1_duration = currentWeapon.motion1_duration;
        motion2_duration = currentWeapon.motion2_duration;
        return attack = strength * currentWeapon.attackValue;
    }

    public void setDefaultPositions(){
//        worldX= game.tileSize*  21;
        worldX=  game.user.getWorldX();
//        worldY= game.tileSize*  85;
        worldY=  game.user.getWorldY();
        direction = "down";
    }
    public void setHospitalPosition() {
        worldX = game.tileSize * 151;
        worldY = game.tileSize * 136;
        direction = "down";
    }

//remove current weapon and also cr
public void removeCurrentWeapon() {
    for(int i=0;i<inventory.size();i++) {
        if(inventory.get(i).itemCode==currentWeapon.itemCode) {
            MyJDBC.removeItemFromInventory(playerId,currentWeapon.itemCode);
            inventory.remove(i);
            String text="";
            if(currentWeapon instanceof OBJ_Axe){
            text="Axe Brocked!";

            }else if(currentWeapon instanceof OBJ_Tomahawk){
             text="Tomahawk Brocked!";

            }
            currentWeapon=null;

            game.ui.uiMainGame.addMessage(text);
            break;
        }
    }
}
    public void update(){
        if(currentWeapon!=null && (currentWeapon instanceof OBJ_Axe||currentWeapon instanceof OBJ_Tomahawk) && currentWeapon.life<=0){
            removeCurrentWeapon();
        }

        if (knockBack == true) {

            //Now check for the colliosion here.
            collisionOn = false;
            game.cChecker.checkTile(this);
            game.cChecker.checkObject(this,true);
            game.cChecker.checkEntity(this,game.npc);
            game.cChecker.checkEntity(this,game.monster);
            game.cChecker.checkEntity(this,game.iTile);

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

        }
        else if (attacking == true) {
            attacking();
        }
        else if (currentShield != null && keyHandler.isVKeyPressed()) {
            if(guarding == false){
             game.playSoundEffect(game.soundEffect.shield);
            }
            guarding = true;
            guardCounter++;
        }
        else if (keyHandler.isMoveUp() || keyHandler.isMoveDown() || keyHandler.isMoveRight() || keyHandler.isMoveLeft() || keyHandler.isEnterPressed() || keyHandler.isSpacePressed()) {
            // Move player based on key inputs
            if (keyHandler.isMoveUp()) {
                direction = "up";
            }
            else if (keyHandler.isMoveDown()) {
                direction = "down";
            }
            else if (keyHandler.isMoveLeft()) {
                direction = "left";
            }
            else if (keyHandler.isMoveRight()) {
                direction = "right";
            }

            guarding = false;
            guardCounter = 0;


            //Now check for the colliosion here.
            collisionOn = false;
            game.cChecker.checkTile(this);

            //Check Object collision
            int objIndex=game.cChecker.checkObject(this,true);
            pickUpObject(objIndex);

            //CHeck NPC collision
            int npcIndex=game.cChecker.checkEntity(this,game.interactNpc);
            interactNPC(npcIndex);

            //CHECK MONSTER COLLISION
            int monsterIndex = game.cChecker.checkEntity(this,game.monster);
            contactMonster(monsterIndex);

            //CHECK INTERACTIVE TILE COLLISION
            game.cChecker.checkEntity(this,game.iTile);

            //in useWeapon method are making player attack status true or false
            useWeapon();


            //new code


            if (collisionOn == false && keyHandler.isEnterPressed() == false && keyHandler.isSpacePressed() == false) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }
              game.keyHandler.setEnterPressed(false);
              game.keyHandler.setSpacePressed(false);

//        playerImageView.setX(playerImageView.getX() - (playerSpeed*deltaTime));

            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }

        }
        else {
            guarding = false;
            guardCounter = 0;

        }


            //END  of IF statement
//        if(game.keyHandler.isFKeyPressed()==true && projectile.alive==false
//                && shotAvailableCounter==30 && projectile.haveResource(this)==true){
//            //Set default COORDINATES, DIRECTION AND USER
//            projectile.set(worldX,worldY,direction, true, this);
//
//            //SUBTRACT THE COST MANA/ AMMO ETC
//            projectile.subtractResource(this);
//
//            // ADD IT TO THE LIST
//            for (int i = 0; i < game.projectile[game.currentMap].length; i++) {
//                if (game.projectile[game.currentMap][i] == null) {
//                    game.projectile[game.currentMap][i] = projectile;
//                    break;
//                }
//            }
//
//            shotAvailableCounter=0;
//        }






        try{
            game.eventHandler.checkEvent();
        }catch(IOException e){
            e.printStackTrace();
        }


        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                transparent = false;
                invincibleCounter = 0;
            }
        }
        if(shotAvailableCounter<30){
            shotAvailableCounter++;
        }
        //Check if the life is greater than max life then life will set max life not more than that
        if(life>maxLife){
            life=maxLife;
        }
        //Check if the mana is greater than max mana then mana will set max life not more than that
        if(mana>maxMana){
            mana=maxMana;
        }



    }




    public void interactNPC(int i){
        if(keyHandler.isEnterPressed() == true){
            if(i != 999){
                //attackCanceled=true;
                if(game.interactNpc[game.currentMap][i].npcGoneCommand==false){
                game.interactNpc[game.currentMap][i].speak();
                }
            }
            //gp.playSE(7);
        }
    }


    public void damagedMonster(int i, Entity attacker, int attack, int knockBackPower) {
        if (i != 999) {
            if (game.monster[game.currentMap][i].invincible == false) {

                if (knockBackPower > 0) {
                    setKnockBack(game.monster[game.currentMap][i], attacker, knockBackPower);
                }

                if (game.monster[game.currentMap][i].offBalance == true) {
                    attack *= 5;
                }

                int damage = attack - game.monster[game.currentMap][i].defense;
                if (damage < 0) {
                    damage = 0;
                }

                game.monster[game.currentMap][i].life -= damage;
                game.monster[game.currentMap][i].invincible = true;
                game.monster[game.currentMap][i].damageReaction();

                if (game.monster[game.currentMap][i].life <= 0) {
                    int col=game.monster[game.currentMap][i].worldX/game.tileSize;
                    int row= game.monster[game.currentMap][i].worldY/game.tileSize;
                    int monsterType=game.monster[game.currentMap][i].monster_type;
                    String name= game.monster[game.currentMap][i].name;
                    game.monster[game.currentMap][i].dying = true;



                    if(game.monster[game.currentMap][i] instanceof Mon_Green){
                        game.playSoundEffect(game.soundEffect.slimeDeathSound);
                        System.out.println("green slime died");
                    } else if(game.monster[game.currentMap][i] instanceof Mon_Pac){
                        game.playSoundEffect(game.soundEffect.slimeDeathSound);
                        System.out.println("Mon_Pac died");
                    }else if(game.monster[game.currentMap][i] instanceof Mon_Spider){
                        game.playSoundEffect(game.soundEffect.slimeDeathSound);
                        System.out.println("Mon_Spider died");
                    }else if(game.monster[game.currentMap][i] instanceof Mon_SpiderBrown){
                        game.playSoundEffect(game.soundEffect.slimeDeathSound);
                        System.out.println("Mon_SpiderBrown died");
                    }else if(game.monster[game.currentMap][i] instanceof Mon_Worm){
                        game.playSoundEffect(game.soundEffect.slimeDeathSound);
                        System.out.println("Mon_Worm died");
                    }else if(game.monster[game.currentMap][i] instanceof Mon_PacGreen){
                        game.playSoundEffect(game.soundEffect.slimeDeathSound);
                        System.out.println("Mon_PacGreen died");
                    }else if(game.monster[game.currentMap][i] instanceof Mon_BlueGhost){
                        game.playSoundEffect(game.soundEffect.slimeDeathSound);
                        System.out.println("Mon_BlueGhost died");
                    }else if(game.monster[game.currentMap][i] instanceof Mon_RedFly){
                        game.playSoundEffect(game.soundEffect.slimeDeathSound);
                        System.out.println("Mon_RedFly died");
                    }else if(game.monster[game.currentMap][i] instanceof Mon_ORC){
                        game.playSoundEffect(game.soundEffect.slimeDeathSound);
                        System.out.println("Mon_ORC died");
                    }else if(game.monster[game.currentMap][i] instanceof Mon_RedORC){
                        game.playSoundEffect(game.soundEffect.slimeDeathSound);
                        System.out.println("Mon_RedORC died");
                    }else if(game.monster[game.currentMap][i] instanceof Mon_Skeleton){
                        game.playSoundEffect(game.soundEffect.spiderSound);
                        System.out.println("Mon_Skeleton died");
                    }else if(game.monster[game.currentMap][i] instanceof Mon_GreenSlimeMother){
                        game.playSoundEffect(game.soundEffect.slimeDeathSound);

                        System.out.println("Mon_GreenSlimeMother died");
                        SlimeDeadThread slimeDeadThread= new SlimeDeadThread(game);
                        slimeDeadThread.start();
//                        for (int j = 0; j < game.monster[game.currentMap].length; j++) {
//                            if (game.monster[game.currentMap][j] instanceof Mon_Green) {
//                                game.monster[game.currentMap][j].slimeDeathOn=true;
//                                game.monster[game.currentMap][j].dying=true;
//                            }
//                        }

                    }


                    game.ui.uiMainGame.addMessage("Killed the " + game.monster[game.currentMap][i].name + "!");
                    coin += game.monster[game.currentMap][i].coin;
                    game.ui.uiMainGame.addMessage(" Coin + " + game.monster[game.currentMap][i].coin);
                    exp += game.monster[game.currentMap][i].exp;
                    game.ui.uiMainGame.addMessage(" EXP + " + game.monster[game.currentMap][i].exp);
                    checkLevelUp();
                    if(game.monster[game.currentMap][i] instanceof Mon_Green ){
                        //then do nothing
                    }
                    else{
                        //set the destroy mode true
                    MyJDBC.updateMonsterDestroyedStatus(playerId,i,monsterType,game.currentMap,true);
                    }

                }
            }

        }
    }
    private void contactMonster(int i) {
        if (i != 999) {
            if (invincible == false && game.monster[game.currentMap][i].dying==false && game.monster[game.currentMap][i].type == type_monster) {
                game.playSoundEffect(6);

                int damage = game.monster[game.currentMap][i].attack - defense;
                if (damage < 0) {
                    damage = 0;
                }

                life -= damage;
                invincible = true;
                transparent = true;

            }
        }
    }
    public void useWeapon() {
        //we set the condition when player equip a weapon only then time he or she can attack
        if (currentWeapon instanceof OBJ_Fireball){
            if(game.keyHandler.isSpacePressed() && projectile.alive==false
                    && shotAvailableCounter==30 && projectile.haveResource(this)==true){

                game.playSoundEffect(game.soundEffect.burning);
                //Set default COORDINATES, DIRECTION AND USER
                projectile.set(worldX,worldY,direction, true, this);

                //SUBTRACT THE COST MANA/ AMMO ETC
                projectile.subtractResource(this);

                // ADD IT TO THE LIST
                for (int i = 0; i < game.projectile[game.currentMap].length; i++) {
                    if (game.projectile[game.currentMap][i] == null) {
                        game.projectile[game.currentMap][i] = projectile;
                        break;
                    }
                }

                shotAvailableCounter=0;
            }
        }
        else {
            if (currentWeapon !=null && game.keyHandler.isSpacePressed()) {
                attacking = true;
                if (currentWeapon.type == type_specialSword) {
                    game.playSoundEffect(7);
                }
                if (currentWeapon.type == type_fireSword) {
                    game.playSoundEffect(game.soundEffect.fireSword);
                }
                if (currentWeapon.type == type_axe || currentWeapon.type == type_tomahawkAxe) {
                    game.playSoundEffect(game.soundEffect.swingWhoosh2);
                }
                if (currentWeapon.type == type_sword || currentWeapon.type == type_iceSword) {
                    game.playSoundEffect(game.soundEffect.swordSound2);
                }

            }
        }
    }


    public void damageProjectile(int i) {
        if (i != 999) {
            Entity projectile = game.projectile[game.currentMap][i];
            projectile.alive = false;
            generateParticle(projectile, projectile);
            //Here i need ot add projectile sounds
        }
    }

    public void damageInteractiveTiles(int i){
        if(i!=999 && game.iTile[game.currentMap][i].destructible==true
                && game.iTile[game.currentMap][i].isCorrectItem(this)==true &&game.iTile[game.currentMap][i].invincible==false){

            if(game.iTile[game.currentMap][i].type==type_tree){

            game.playSoundEffect(game.soundEffect.treeCut);
            }else if(game.iTile[game.currentMap][i].type==type_rock){
            game.playSoundEffect(game.soundEffect.rockBreak);
            }
            game.iTile[game.currentMap][i].life--;
            game.iTile[game.currentMap][i].invincible=true;
            currentWeapon.life--;
            generateParticle(game.iTile[game.currentMap][i],game.iTile[game.currentMap][i]);

            if(game.iTile[game.currentMap][i].life < 1){
                game.player.exp += (game.axe.exp - 1);
                game.player.checkLevelUp();
                int row=game.iTile[game.currentMap][i].worldY/game.tileSize;
                int col=game.iTile[game.currentMap][i].worldX/game.tileSize;
                MyJDBC.updateObjectDestroyedStatus(playerId,game.currentMap,row,col,game.iTile[game.currentMap][i].objectType,true);
                //thogh we can simply use `null` but we did that code because if we need any destroyed form
                game.iTile[game.currentMap][i]=game.iTile[game.currentMap][i].getDestryoedForm();
            }
        }
    }
    public void pickUpObject(int i){
        if(i!=999){
            //PICKUP ONLY ITEMS
            if(game.obj[game.currentMap][i].type==type_pickupOnly){
                if (Objects.equals(game.obj[game.currentMap][i].name, "Coin")) {
                    game.playSoundEffect(1);

                }

                game.obj[game.currentMap][i].use(this);
                int row=game.obj[game.currentMap][i].worldY/game.tileSize;
                int col=game.obj[game.currentMap][i].worldX/game.tileSize;
                MyJDBC.updateObjectDestroyedStatus(playerId,game.currentMap,row,col,game.type_object,true);

                game.obj[game.currentMap][i]=null;

            }
            //obstacles
            else if (game.obj[game.currentMap][i].type==type_obstacle){

                if(keyHandler.isEnterPressed()){
                    game.obj[game.currentMap][i].interact();
                }
//                System.out.println("obstacle detected");
            }
            //INVENTORY ITEMS
            else{
                String text;
                if(inventory.size()!=maxInventorySize){
                    //inventory.add(game.obj[game.currentMap][i]);
                    addToInventory(game.obj[game.currentMap][i]);
                    MyJDBC.addItemToInventory(playerId,game.obj[game.currentMap][i].itemCode);
                    game.playSoundEffect(game.soundEffect.pickUpItem);
                    if (game.obj[game.currentMap][i] instanceof OBJ_BlueKey) {
                        game.player.exp += game.blueKey.exp;
                        game.player.checkLevelUp();
                    }
                    text="Got a "+ game.obj[game.currentMap][i].name+" !";
                   // game.obj[game.currentMap][i].itemCount++;
                }else {
                    text="You can not carry any more!";
                }
                game.ui.uiMainGame.addMessage(text);
                int row=game.obj[game.currentMap][i].worldY/game.tileSize;
                int col=game.obj[game.currentMap][i].worldX/game.tileSize;

                MyJDBC.updateObjectDestroyedStatus(playerId,game.currentMap,row,col,game.type_object,true);
                game.obj[game.currentMap][i]=null;

            }
        }
    }
    public void addToInventory(Entity entity){
        Entity item=game.inventoryMapgetItem(entity.itemCode);
        inventory.add(entity);
        item.itemCount++;
    }

    public void checkLevelUp() {
        if (exp >= nextLevelExp) {

            level++;
//            nextLevelExp = nextLevelExp + 10;   // We will use fibonacci series
            exp = 0;
//            strength++;
//            dexterity++;
//            attack = getAttack();
//            defense = getDefense();
//            game.gameState = game.messageState;
//            game.ui.uiMainGame.currentDialogue = " Congratulations! \nYou are level in " + level + " now.";
            game.playSoundEffect(game.soundEffect.levelUp);
            game.gameState = game.levelUpState;
        }
    }
    public void selectItem(){
        int itemIndex=game.ui.uiMainGame.getItemIndexOnSlot(game.ui.uiMainGame.playerSlotCol,game.ui.uiMainGame.playerSlotRow);
        if(itemIndex<inventory.size()){
            Entity selectedItem=inventory.get(itemIndex);
            //We need to fix this type_sword or something else
            if(selectedItem.type==type_sword|| selectedItem.type==type_axe || selectedItem.type==type_specialSword || selectedItem.type==type_fireSword || selectedItem.type == type_iceSword || selectedItem.type == type_tomahawkAxe){
                game.playSoundEffect(game.soundEffect.equip);
                currentWeapon=selectedItem;
                //update the attack method with proper power
                attack=getAttack();
                loadPlayerAttackImages();
            }
            if (selectedItem.type == type_fireball) {
                game.playSoundEffect(game.soundEffect.equip);
                currentWeapon=selectedItem;
                System.out.println(currentWeapon.name +" ->"+currentWeapon.mana);
            }
            if(selectedItem.type==type_shield){
                game.playSoundEffect(game.soundEffect.equip);
                currentShield=selectedItem;
                //update the defense method with proper defense power
                defense=getDefense();
                loadPlayerGuardImages();
            }
            if(selectedItem.type==type_consumable){
                game.playSoundEffect(game.soundEffect.consume);
                //WE are gonna use this later
                if(selectedItem.use(this)){
                MyJDBC.removeItemFromInventory(playerId,selectedItem.itemCode);
                inventory.remove(itemIndex);
                }
            }
        }

    }


    public void draw(GraphicsContext gc){
        Image image = null;

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
                if (guarding == true) {
                    image = guardUp;
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
                if (guarding == true) {
                    image = guardDown;
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
                if (guarding == true) {
                    image = guardLeft;
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
                if (guarding == true) {
                    image = guardRight;
                }
                break;



            // Handle other directions similarly
        }

        if (transparent == true) {
            gc.setGlobalAlpha(0.3);
        }
        if (dying == true) {
            //here we will called sound effects for our player died
            dyingAnimation(game.gc);
        }
        gc.drawImage(image, tempScreenX, tempScreenY);

        //RESET ALPHA
        gc.setGlobalAlpha(1);


        //DEBUG...
//        gc.setFont(new Font("Arial", 26.0));
//        gc.setFill(Color.WHITE);
//        String text = "Invincible: " + invincibleCounter;
//        gc.fillText(text, 10, 400);
    }
}



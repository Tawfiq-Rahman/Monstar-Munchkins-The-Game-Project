package com.example.return_3.db;


/*
User entity which is used to store user information ()i.e. id, username, password and current balance
* */
public class User {
    private final int userId;
    private final String  username, password;

    private int worldX,worldY, coin, energy,maxEnergy, life,maxLife, exp,nextLevelExp, level,strength,dexterity, bullet,maxBullet;
    private boolean isShipStarted,npcAxe,npcFireBall,npcMotherSlime,npcWelcome,npcGlobalChat,isGameOver;

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    public User(int userId, String  username, String password, int playerCol, int playerRow, int coin, int energy, int maxEnergy, int life, int maxLife, int exp, int nextLevelExp, int level, int strength, int dexterity, int bullet, int maxBullet , boolean isShipStarted, boolean npcFireBall, boolean npcGlobalChat, boolean npcMotherSlime, boolean npcWelcome, boolean npcAxe , boolean isGameOver){
        this.userId=userId;
        this.username=username;
        this.password=password;
        this.worldX=playerCol;
        this.worldY=playerRow;

        this.coin=coin;
        this.energy=energy;
        this.maxEnergy=maxEnergy;
        this.life=life;
        this.maxLife=maxLife;
        this.exp=exp;
        this.nextLevelExp=nextLevelExp;
        this.level=level;
        this.strength=strength;
        this.dexterity=dexterity;
        this.bullet=bullet;
        this.maxBullet=maxBullet;
        this.isShipStarted=isShipStarted;
        this.npcAxe=npcAxe;
        this.npcWelcome=npcWelcome;
        this.npcFireBall=npcFireBall;
        this.npcMotherSlime=npcMotherSlime;
        this.npcGlobalChat=npcGlobalChat;
        this.isGameOver=isGameOver;

    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getCoin() {
        return coin;
    }

    public int getEnergy() {
        return energy;
    }

    public int getLife() {
        return life;
    }

    public int getExp() {
        return exp;
    }

    public int getLevel() {
        return level;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public boolean isShipStarted() {
        return isShipStarted;
    }

    public void setShipStarted(boolean shipStarted) {
        isShipStarted = shipStarted;
    }

    public boolean isNpcAxe() {
        return npcAxe;
    }

    public void setNpcAxe(boolean npcAxe) {
        this.npcAxe = npcAxe;
    }

    public boolean isNpcFireBall() {
        return npcFireBall;
    }

    public void setNpcFireBall(boolean npcFireBall) {
        this.npcFireBall = npcFireBall;
    }

    public boolean isNpcMotherSlime() {
        return npcMotherSlime;
    }

    public void setNpcMotherSlime(boolean npcMotherSlime) {
        this.npcMotherSlime = npcMotherSlime;
    }

    public boolean isNpcWelcome() {
        return npcWelcome;
    }

    public void setNpcWelcome(boolean npcWelcome) {
        this.npcWelcome = npcWelcome;
    }

    public boolean isNpcGlobalChat() {
        return npcGlobalChat;
    }

    public void setNpcGlobalChat(boolean npcGlobalChat) {
        this.npcGlobalChat = npcGlobalChat;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void setMaxLife(int maxLife) {
        this.maxLife = maxLife;
    }

    public int getMaxLife() {
        return maxLife;
    }

    public int getNextLevelExp() {
        return nextLevelExp;
    }

    public void setNextLevelExp(int nextLevelExp) {
        this.nextLevelExp = nextLevelExp;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public void setBullet(int bullet) {
        this.bullet = bullet;
    }

    public void setMaxBullet(int maxBullet) {
        this.maxBullet = maxBullet;
    }

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getBullet() {
        return bullet;
    }

    public int getMaxBullet() {
        return maxBullet;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public void setMaxEnergy(int maxEnergy) {
        this.maxEnergy = maxEnergy;
    }

    public int getWorldX() {
        return worldX;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}

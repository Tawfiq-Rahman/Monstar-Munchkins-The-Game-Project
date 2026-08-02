package com.example.return_3.main;

import javax.sound.sampled.*;
import java.net.URL;

public class Sound {
    private Clip clip;
    private URL[] soundUrls = new URL[50];
    FloatControl fc;
    public float volume = 0.6f; // Default volume
    public int  volumeScale = 3;
    public int  burning = 10;
    public final int pickUpItem=16;
    public final int doorOpened=17;
    public final int doorLocked=18;
    public final int fireSword=19;
    public final int swordSound2=20;
    public final int swingWhoosh1=21;
    public final int swingWhoosh2=22;
    public final int swingWhoosh3=23;

    public  final int blueghostDeathSound=24;
    public final int fireflyDeathSound=25;
    public final int greenPacDeathSound=26;
    public final int orcDeathSound=27;
    public final int redPacDeathSound=28;
    public final int slimeDeathSound=29;
    public final int spiderSound=30;
    public final int shipWave=31;
    public final int shield = 32;
    public final int click=33;
    public final int equip=34;
    public final int error=35;
    public final int inventoryClose=36;
    public final int inventoryOpen=37;
    public final int messageSend=38;
    public final int consume=39;
    public final int levelUp=40;
    public final int gameOver=41;
    public final int popUp=42;
    public final int chatType=43;
    public final int buy=44;
    public final int sell=45;
    public final int rockBreak=46;
    public final int playerDeath=47;
    public final int treeCut=48;
    public final int addMessage=49;

    public Sound() {
        // Initialize sound URLs
//        soundUrls[0] = getClass().getResource("/sound/BlueBoyAdventure.wav");
        soundUrls[0] = getClass().getResource("/sound/bgMusic.wav");
        soundUrls[1] = getClass().getResource("/sound/coin.wav");
        soundUrls[2] = getClass().getResource("/sound/powerup.wav");
        soundUrls[3] = getClass().getResource("/sound/unlock.wav");
        soundUrls[4] = getClass().getResource("/sound/fanfare.wav");
        soundUrls[5] = getClass().getResource("/sound/hitmonster.wav");
        soundUrls[6] = getClass().getResource("/sound/receivedamage.wav");
        soundUrls[7] = getClass().getResource("/sound/swordswing.wav");
        soundUrls[8] = getClass().getResource("/sound/levelup.wav");
        soundUrls[9] = getClass().getResource("/sound/cursor.wav");
        soundUrls[burning] = getClass().getResource("/sound/burning.wav");
        soundUrls[11] = getClass().getResource("/sound/cuttree.wav");
        soundUrls[12] = getClass().getResource("/sound/gameover.wav");
        soundUrls[13] = getClass().getResource("/sound/stairs.wav");
        soundUrls[14] = getClass().getResource("/sound/newSword.wav");
        soundUrls[15] = getClass().getResource("/sound/monsterIsland.wav");
        soundUrls[pickUpItem]= getClass().getResource("/sound/pickUpItem.wav");
        soundUrls[doorOpened]= getClass().getResource("/sound/doorOpened.wav");
        soundUrls[doorLocked]= getClass().getResource("/sound/doorLocked.wav");
        soundUrls[fireSword]=getClass().getResource("/sound/fireSwordSwing.wav");
        soundUrls[swordSound2]=getClass().getResource("/sound/swordSound2.wav");
        soundUrls[swingWhoosh1]=getClass().getResource("/sound/swingWhoosh1.wav");
        soundUrls[swingWhoosh2]=getClass().getResource("/sound/swingWhoosh2.wav");
        soundUrls[swingWhoosh3]=getClass().getResource("/sound/swingWhoosh3.wav");
        soundUrls[shipWave]=getClass().getResource("/sound/shipWave.wav");
//        soundUrls[blueghostDeathSound]=getClass().getResource("/sound/blueghostDeathSound.wav");
//        soundUrls[fireflyDeathSound]=getClass().getResource("/sound/fireflyDeathSound.wav");
//        soundUrls[greenPacDeathSound]=getClass().getResource("/sound/greenPacDeathSound.wav");
//        soundUrls[orcDeathSound]=getClass().getResource("/sound/orcDeathSound.wav");
//        soundUrls[redPacDeathSound]=getClass().getResource("/sound/redPacDeathSound.wav");
        soundUrls[slimeDeathSound]=getClass().getResource("/sound/slimeDeathSound.wav");
        soundUrls[spiderSound]=getClass().getResource("/sound/spiderSound.wav");
        soundUrls[shield] = getClass().getResource("/sound/blocked.wav");

        soundUrls[click] = getClass().getResource("/sound/click.wav");
        soundUrls[equip] = getClass().getResource("/sound/equip.wav");
        soundUrls[error] = getClass().getResource("/sound/error.wav");
        soundUrls[inventoryClose] = getClass().getResource("/sound/inventoryClose.wav");
        soundUrls[inventoryOpen] = getClass().getResource("/sound/inventoryOpen.wav");
        soundUrls[messageSend] = getClass().getResource("/sound/messageSend.wav");
        soundUrls[consume] = getClass().getResource("/sound/consume.wav");
        soundUrls[levelUp] = getClass().getResource("/sound/levelUpNew.wav");
        soundUrls[gameOver]= getClass().getResource("/sound/gameOver.wav");
        soundUrls[popUp]=getClass().getResource("/sound/messageState.wav");
        soundUrls[chatType]=getClass().getResource("/sound/chatType.wav");
        soundUrls[buy]=getClass().getResource("/sound/buy.wav");
        soundUrls[sell]=getClass().getResource("/sound/sell.wav");
        soundUrls[playerDeath]=getClass().getResource("/sound/playerDeath.wav");
        soundUrls[rockBreak]=getClass().getResource("/sound/rockBreak.wav");
        soundUrls[treeCut]=getClass().getResource("/sound/treeCut.wav");
        soundUrls[addMessage]=getClass().getResource("/sound/addMessage.wav");

    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundUrls[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }

    public void checkVolume() {
        switch (volumeScale){
            case 0: volume = -80f; break;
            case 1: volume = -20f; break;
            case 2: volume = -12f; break;
            case 3: volume = -5f; break;
            case 4: volume = 1f; break;
            case 5: volume = 6f; break;
        }
        fc.setValue(volume);
    }


}

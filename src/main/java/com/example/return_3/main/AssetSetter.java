package com.example.return_3.main;


import com.example.return_3.db.MyJDBC;
import com.example.return_3.db.ObjectData;
import com.example.return_3.entity.*;
import com.example.return_3.npc.*;
import com.example.return_3.object.*;

import java.util.ArrayList;
import java.util.List;

//This class is made for setting Asset to the Game map.
public class AssetSetter {
    Game game;
    public AssetSetter(Game game ){
        this.game = game;
    }

    public void setObject() {

    }

    public void setNPC() {
        int mapNum = 0;
        int i = 0;

        // <-------------Interact NPC------------>
        if(!game.npcWelcome){
            game.interactNpc[mapNum][i]= new InteractNPC_Welcome(game);
            game.interactNpc[mapNum][i].worldX=game.tileSize*81;
            game.interactNpc[mapNum][i].worldY=game.tileSize*117;
            i++;
        }
        if (!game.npcMotherSlime) {
            game.interactNpc[mapNum][i]= new InteractNPC_HelplessWomen(game);
            game.interactNpc[mapNum][i].worldX=game.tileSize*99;
            game.interactNpc[mapNum][i].worldY=game.tileSize*73;
            i++;
        }
        if (!game.npcGlobalChat) {
            game.interactNpc[mapNum][i]= new InteractNPC_GlobalChat(game);
            game.interactNpc[mapNum][i].worldX=game.tileSize*30;
            game.interactNpc[mapNum][i].worldY=game.tileSize*162;
            i++;
        }
        if (!game.npcAxe) {
            game.interactNpc[mapNum][i]= new InteractNPC_InteractiveTile(game);
            game.interactNpc[mapNum][i].worldX=game.tileSize*169;
            game.interactNpc[mapNum][i].worldY=game.tileSize*142;
            i++;
        }

        if (!game.npcFireball) {
            game.interactNpc[mapNum][i]= new InteractNPC_FireBallGiver(game);
            game.interactNpc[mapNum][i].worldX=game.tileSize*106;
            game.interactNpc[mapNum][i].worldY=game.tileSize*152;
            i++;
        }

        game.interactNpc[mapNum][i]= new InteractNPC_FisherMan(game);
        game.interactNpc[mapNum][i].worldX=game.tileSize*188;
        game.interactNpc[mapNum][i].worldY=game.tileSize*113;
        i++;
        game.interactNpc[mapNum][i]= new InteractNPC_ShipThisSide(game);
        game.interactNpc[mapNum][i].worldX=game.tileSize*23;
        game.interactNpc[mapNum][i].worldY=game.tileSize*85;
        i++;
        game.interactNpc[mapNum][i]= new InteractNPC_ShipOtherSide(game);
        game.interactNpc[mapNum][i].worldX=game.tileSize*29;
        game.interactNpc[mapNum][i].worldY=game.tileSize*11;
        i++;

        // <----------Direction-------->
        game.interactNpc[mapNum][i]= new DirectionBoard(game,1);
        game.interactNpc[mapNum][i].worldX=game.tileSize*90;
        game.interactNpc[mapNum][i].worldY=game.tileSize*120;
        i++;
        game.interactNpc[mapNum][i]= new DirectionBoard(game,2);
        game.interactNpc[mapNum][i].worldX=game.tileSize*64;
        game.interactNpc[mapNum][i].worldY=game.tileSize*156;
        i++;
        game.interactNpc[mapNum][i]= new DirectionSingleBoard(game,3);
        game.interactNpc[mapNum][i].worldX=game.tileSize*93;
        game.interactNpc[mapNum][i].worldY=game.tileSize*153;
        i++;
        game.interactNpc[mapNum][i]= new DirectionSingleBoard(game,4);
        game.interactNpc[mapNum][i].worldX=game.tileSize*165;
        game.interactNpc[mapNum][i].worldY=game.tileSize*111;
        i++;
        game.interactNpc[mapNum][i]= new DirectionSingleBoard(game,4);
        game.interactNpc[mapNum][i].worldX=game.tileSize*142;
        game.interactNpc[mapNum][i].worldY=game.tileSize*141;
        i++;
        game.interactNpc[mapNum][i]= new DirectionSingleBoard(game,5);
        game.interactNpc[mapNum][i].worldX=game.tileSize*177;
        game.interactNpc[mapNum][i].worldY=game.tileSize*141;
        i++;
        game.interactNpc[mapNum][i]= new DirectionSingleBoard(game,6);
        game.interactNpc[mapNum][i].worldX=game.tileSize*148;
        game.interactNpc[mapNum][i].worldY=game.tileSize*72;
        i++;
        game.interactNpc[mapNum][i]= new DirectionSingleBoard(game,7);
        game.interactNpc[mapNum][i].worldX=game.tileSize*136;
        game.interactNpc[mapNum][i].worldY=game.tileSize*153;
        i++;


        // <---------Set Npc--------->
        i=0;
        game.npc[mapNum][i] = new NPC_Universal(game,"girl");
        game.npc[mapNum][i].worldX = game.tileSize * 40;
        game.npc[mapNum][i].worldY = game.tileSize * 134;
        i++;
        game.npc[mapNum][i]= new NPC_Universal(game,"girl3");
        game.npc[mapNum][i].worldX=game.tileSize*45;
        game.npc[mapNum][i].worldY=game.tileSize*133;
        i++;
        game.npc[mapNum][i]= new NPC_Universal(game,"pig");
        game.npc[mapNum][i].worldX=game.tileSize*46;
        game.npc[mapNum][i].worldY=game.tileSize*135;
        i++;
        game.npc[mapNum][i]= new NPC_Universal(game,"girl");
        game.npc[mapNum][i].worldX=game.tileSize*43;
        game.npc[mapNum][i].worldY=game.tileSize*133;
        i++;
        game.npc[mapNum][i]= new NPC_Universal(game,"girl2");
        game.npc[mapNum][i].worldX=game.tileSize*42;
        game.npc[mapNum][i].worldY=game.tileSize*133;
        i++;
        game.npc[mapNum][i]= new NPC_Universal(game,"boy");
        game.npc[mapNum][i].worldX=game.tileSize*50;
        game.npc[mapNum][i].worldY=game.tileSize*132;
        i++;
        game.npc[mapNum][i]= new NPC_Universal(game,"boy2");
        game.npc[mapNum][i].worldX=game.tileSize*52;
        game.npc[mapNum][i].worldY=game.tileSize*132;
        i++;
        game.npc[mapNum][i]= new NPC_Universal(game,"oldman");
        game.npc[mapNum][i].worldX=game.tileSize*54;
        game.npc[mapNum][i].worldY=game.tileSize*132;
        i++;
        game.npc[mapNum][i]= new NPC_Universal(game,"girl");
        game.npc[mapNum][i].worldX=game.tileSize*78;
        game.npc[mapNum][i].worldY=game.tileSize*116;
        i++;
        game.npc[mapNum][i]= new NPC_Universal(game,"girl2");
        game.npc[mapNum][i].worldX=game.tileSize*88;
        game.npc[mapNum][i].worldY=game.tileSize*121;
        i++;
        game.npc[mapNum][i]= new NPC_Universal(game,"boy");
        game.npc[mapNum][i].worldX=game.tileSize*110;
        game.npc[mapNum][i].worldY=game.tileSize*150;
        i++;
        game.npc[mapNum][i]= new NPC_Universal(game,"boy2");
        game.npc[mapNum][i].worldX=game.tileSize*125;
        game.npc[mapNum][i].worldY=game.tileSize*159;
        i++;
        game.npc[mapNum][i]= new NPC_Universal(game,"oldman");
        game.npc[mapNum][i].worldX=game.tileSize*112;
        game.npc[mapNum][i].worldY=game.tileSize*176;
        i++;
        game.npc[mapNum][i]= new NPC_Universal(game,"boy2");
        game.npc[mapNum][i].worldX=game.tileSize*165;
        game.npc[mapNum][i].worldY=game.tileSize*172;
        i++;
        game.npc[mapNum][i]= new NPC_Universal(game,"oldman");
        game.npc[mapNum][i].worldX=game.tileSize*171;
        game.npc[mapNum][i].worldY=game.tileSize*151;
        i++;
        game.npc[mapNum][i]= new NPC_Universal(game,"girl");
        game.npc[mapNum][i].worldX=game.tileSize*153;
        game.npc[mapNum][i].worldY=game.tileSize*139;
        i++;
        game.npc[mapNum][i]= new NPC_Universal(game,"girl2");
        game.npc[mapNum][i].worldX=game.tileSize*148;
        game.npc[mapNum][i].worldY=game.tileSize*125;
        i++;
        game.npc[mapNum][i]= new NPC_Universal(game,"boy");
        game.npc[mapNum][i].worldX=game.tileSize*127;
        game.npc[mapNum][i].worldY=game.tileSize*127;
        i++;
        game.npc[mapNum][i]= new NPC_Universal(game,"boy2");
        game.npc[mapNum][i].worldX=game.tileSize*131;
        game.npc[mapNum][i].worldY=game.tileSize*113;
        i++;
        game.npc[mapNum][i]= new NPC_Universal(game,"oldman");
        game.npc[mapNum][i].worldX=game.tileSize*142;
        game.npc[mapNum][i].worldY=game.tileSize*176;
        i++;

        // <--------Hill Area------>
        game.npc[mapNum][i]= new NPC_Universal(game,"oldman");
        game.npc[mapNum][i].worldX=game.tileSize*81;
        game.npc[mapNum][i].worldY=game.tileSize*84;
        i++;
        game.npc[mapNum][i]= new NPC_Universal(game,"oldman");
        game.npc[mapNum][i].worldX=game.tileSize*153;
        game.npc[mapNum][i].worldY=game.tileSize*106;
        i++;
        game.npc[mapNum][i]= new NPC_Universal(game,"boy2");
        game.npc[mapNum][i].worldX=game.tileSize*137;
        game.npc[mapNum][i].worldY=game.tileSize*99;
        i++;
        game.npc[mapNum][i]= new NPC_Universal(game,"girl2");
        game.npc[mapNum][i].worldX=game.tileSize*83;
        game.npc[mapNum][i].worldY=game.tileSize*64;
        i++;
        game.npc[mapNum][i]= new NPC_Universal(game,"boy");
        game.npc[mapNum][i].worldX=game.tileSize*104;
        game.npc[mapNum][i].worldY=game.tileSize*71;
        i++;
        game.npc[mapNum][i]= new NPC_Universal(game,"boy");
        game.npc[mapNum][i].worldX=game.tileSize*66;
        game.npc[mapNum][i].worldY=game.tileSize*42;
        i++;
        game.npc[mapNum][i]= new NPC_Universal(game,"girl");
        game.npc[mapNum][i].worldX=game.tileSize*41;
        game.npc[mapNum][i].worldY=game.tileSize*95;
        i++;
        game.npc[mapNum][i]= new NPC_Universal(game,"girl");
        game.npc[mapNum][i].worldX=game.tileSize*53;
        game.npc[mapNum][i].worldY=game.tileSize*97;
        i++;
        game.npc[mapNum][i]= new NPC_Universal(game,"oldman");
        game.npc[mapNum][i].worldX=game.tileSize*78;
        game.npc[mapNum][i].worldY=game.tileSize*57;
        i++;
        game.npc[mapNum][i]= new NPC_Universal(game,"oldman");
        game.npc[mapNum][i].worldX=game.tileSize*133;
        game.npc[mapNum][i].worldY=game.tileSize*71;
        i++;
    }


    public void setMonster(){
        int mapNum = 0;
        MyJDBC.setMonsters(game.player.playerId,mapNum);
    }


    public void setInteractiveTile(){
        int mapNum = 0;
        MyJDBC.setObjects(game.player.playerId,mapNum);
    }


    public static void addObjectToDB(int userId) {
        int mapNum = 0;
        int objecttype = Game.gameInstance.type_object;

        List<ObjectData> objects = new ArrayList<>();

        addObjectToList(objects, userId, objecttype, Game.gameInstance.axe.itemCode, 172, 162, mapNum);
        addObjectToList(objects, userId, objecttype, Game.gameInstance.door.itemCode, 28, 161, mapNum);
        addObjectToList(objects, userId, objecttype, Game.gameInstance.blueKey.itemCode, 67, 36, mapNum);
        addObjectToList(objects, userId, objecttype, Game.gameInstance.key.itemCode, 185, 137, mapNum);
        addObjectToList(objects, userId, objecttype, Game.gameInstance.chest.itemCode, 93, 110, mapNum); //townhall hill
        addObjectToList(objects, userId, objecttype, Game.gameInstance.chest.itemCode, 17, 159, mapNum); //gamecenter
        addObjectToList(objects, userId, objecttype, Game.gameInstance.chest.itemCode, 34, 40, mapNum); //Mother slime island
        addObjectToList(objects, userId, objecttype, Game.gameInstance.chest.itemCode, 179, 80, mapNum); //hill -> near monster island
        addObjectToList(objects, userId, objecttype, Game.gameInstance.chest.itemCode, 66, 25, mapNum); //hill

        // <----------FOR INTERACTIVE TILE [ Dry Tree ]----------->
        objecttype = Game.gameInstance.type_interactiveTreeBig;
        addCuttableTreeBigToList(objects, userId, objecttype, mapNum);
        objecttype = Game.gameInstance.type_interactiveTreeSmall;
        addCuttableTreeSmallToList(objects, userId, objecttype, mapNum);

        // <-----------FOR INTERACTIVE TILE [Breakable RockBig]----------->
        objecttype = Game.gameInstance.type_interactiverockBig;
        addbreakableRockBigToList(objects, userId, objecttype, mapNum);

        // <-----------FOR INTERACTIVE TILE [Breakable RockSmall]----------->
        objecttype = Game.gameInstance.type_interactiverockSmall;
        addbreakableRockSmallToList(objects, userId, objecttype, mapNum);

        // <-----Insert all objects in a single batch------>
        MyJDBC.addObjectBatch(objects);

        // <---------- Insert All MONSTERS ---------->
        addMonsters(userId, mapNum);
    }

    private static void addObjectToList(List<ObjectData> objects, int userId, int objectType, int itemCode, int col, int row, int mapNum) {
        objects.add(new ObjectData(userId, objectType, itemCode, col, row, mapNum));
    }

    private static void addCuttableTreeBigToList(List<ObjectData> objects, int userId, int objectType, int mapNum) {
        int itemCode = 0; // Assuming itemCode is 0 for interactive tiles
        int[][] tilePositions = {
                {16, 102}, {17, 102}, {19, 102}, {21, 102},
                {17, 103}, {20, 103}, {23, 103},
                {16, 104},{17, 104},{18, 104},{20, 104},{21, 104},{24, 104},{27, 104},{28, 104},{29, 104},
                {16, 105},{18, 105},{19, 105},{20, 105},{22, 105},{25, 105},{26, 105},{30, 105},{32, 105},{34, 105},
                {16, 106},{17, 106},{18, 106},{19, 106},{20, 106},{22, 106},{24, 106},{26, 106},{28, 106},{33, 106},{36, 106},
                {19, 107},{21, 107},{23, 107},{26, 107},{30, 107},{31, 107},{34, 107},{35, 107},{37, 107},{39, 107},{40, 107},{42, 107},{43, 107},
                {17, 108},{18, 108},{20, 108},{22, 108},{24, 108},{25, 108},{27, 108},{29, 108},{30, 108},{31, 108},{32, 108},{34, 108},{36, 108},{37, 108},{42, 108},{44, 108},
                {16, 109},{18, 109},{19, 109},{21, 109},{22, 109},{23, 109},{24, 109},{25, 109},{27, 109},{28, 109},{30, 109},{32, 109},{33, 109},{34, 109},{35, 109},{38, 109},{40, 109},{41, 109},{43, 109},{45, 109},{46, 109},
                {17, 110},{19, 110},{20, 110},{21, 110},{22, 110},{23, 110},{24, 110},{25, 110},{26, 110},{27, 110},{29, 110},{31, 110},{33, 110},{35, 110},{36, 110},{37, 110},{38, 110},{41, 110},{42, 110},{44, 110},{45, 110},{46, 110},{47, 110},{48, 110},{49, 110},
                {16, 111},{17, 111},{18, 111},{19, 111},{20, 111},{21, 111},{22, 111},{23, 111},{24, 111},{25, 111},{27, 111},{28, 111},{29, 111},{30, 111},{32, 111},{33, 111},{34, 111},{35, 111},{36, 111},{37, 111},{38, 111},{39, 111},{41, 111},{42, 111},{43, 111},
                {16, 112},{17, 112},{18, 112},{21, 112},{23, 112},{24, 112},{25, 112},{26, 112},{27, 112},{29, 112},{30, 112},{31, 112},{32, 112},{34, 112},{36, 112},{38, 112},{40, 112},
                {16, 113},{31, 113},{32, 113},{33, 113},{34, 113},{35, 113},{36, 113},{37, 113},{38, 113},
                {33, 114}, {35, 114}, {33, 115}, {34, 115}, {35, 115}, {36, 115}, {33, 116}, {34, 116}, {34, 117},

                // <--------Hill------->
                {171, 98}, {167, 98}, {166, 98}, {165, 97}, {168, 97}, {170, 96}, {166, 96}, {164, 95}, {167, 95},

                // <---------Another Hill---------->
                {108, 62}, {109, 62}, {109, 63}, {109, 60}, {110, 61}, {110, 62}, {110, 63}, {110, 64}, {111, 64}, {111, 63}, {111, 61}, {111, 60}, {111, 59}, {112, 63}, {112, 62}, {112, 61}, {113, 64}, {113, 61}, {113, 60},

                // <-----------Another hill----------->
                {88, 32}, {89, 33}, {89, 32}, {89, 31}, {89, 30}, {89, 29}, {90, 32}, {90, 30}, {90, 29}, {91, 32}, {91, 31}, {91, 30}, {91, 29}, {92, 29}, {92, 30}, {93, 28}, {94, 31}, {95, 29},
        };

        for (int[] pos : tilePositions) {
            objects.add(new ObjectData(userId, objectType, itemCode, pos[0], pos[1], mapNum));
        }
    }


    private static void addCuttableTreeSmallToList(List<ObjectData> objects, int userId, int objectType, int mapNum) {
        int itemCode = 0; // Assuming itemCode is 0 for interactive tiles
        int[][] tilePositions = {

                // <-------Key Hill Area positions--------->
                {95, 113}, {97, 113}, {99, 113}, {98, 114}, {101, 114},
                {97, 115}, {98, 115}, {99, 115}, {100, 115}, {102, 115}, {105, 115},
                {99, 116}, {100, 116}, {105, 117}, {108, 117}, {108, 118}, {112, 118},
                {102, 119}, {103, 119}, {107, 119}, {108, 119}, {101, 120}, {102, 120},
                {107, 120}, {112, 120}, {114, 120}, {116, 120}, {111, 121}, {112, 121},
                {113, 121}, {115, 121}, {117, 121}, {111, 122}, {113, 122}, {114, 122},
                {117, 122}, {111, 123}, {115, 123}, {116, 123}, {112, 124}, {113, 124},
                {114, 124}, {115, 124},

                // <-------key guards-------->
                {182, 142}, {183, 142}, {184, 142}, {185, 142}, {186, 142},
                {180, 141}, {181, 141}, {182, 141}, {183, 141}, {184, 141}, {186, 141},
                {177, 140}, {180, 140}, {179, 140}, {182, 140}, {184, 140}, {186, 140},
                {186, 139}, {185, 139}, {184, 139}, {183, 139}, {182, 139}, {181, 139}, {180, 139}, {179, 139}, {178, 139}, {175, 139},
                {177, 138}, {178, 138}, {179, 138}, {181, 138}, {182, 138}, {183, 138},
                {183, 137}, {173, 137}, {174, 137}, {175, 137}, {176, 137}, {177, 137}, {180, 137}, {181, 137}, {182, 137},
                {172, 136}, {173, 136}, {174, 136}, {175, 136}, {177, 136}, {180, 136}, {182, 136}, {183, 136},
                {173, 135}, {174, 135}, {175, 135}, {176, 135}, {177, 135}, {179, 135}, {180, 135}, {181, 135}, {182, 135},
                {171, 134}, {172, 134}, {173, 134}, {175, 134}, {176, 134}, {177, 134}, {178, 134}, {179, 134}, {180, 134},
                {171, 133}, {172, 133}, {173, 133}, {174, 133}, {175, 133}, {176, 133}, {177, 133},
                {172, 132}, {171, 131}, {172, 131}, {170, 130}, {174, 129}, {171, 129},

                // <----------Big Slime entry area---------->
                {65, 87}, {67, 87}, {68, 86}, {64, 86}, {65, 86}, {66, 86}, {63, 85}, {66, 85}, {69, 85}, {70, 85}, {62, 84}, {69, 84}, {71, 84}, {64, 83}, {67, 83}, {70, 83}, {60, 82}, {62, 82}, {69, 82},{72, 82},
        };

        for (int[] pos : tilePositions) {
            objects.add(new ObjectData(userId, objectType, itemCode, pos[0], pos[1], mapNum));
        }
    }


    private static void addbreakableRockBigToList(List<ObjectData> objects, int userId, int objectType, int mapNum) {
        int itemCode = 0; // Assuming itemCode is 0 for interactive tiles
        int[][] tilePositions = {
                // <---------HILL--------->
                {53, 55}, {53, 57},
                {54, 54}, {54, 55},
                {89, 26}, {91, 26},
                {90, 25}, {91, 25},
                {145, 67}, {143, 62},
                {143, 63}
        };

        for (int[] pos : tilePositions) {
            objects.add(new ObjectData(userId, objectType, itemCode, pos[0], pos[1], mapNum));
        }
    }

    private static void addbreakableRockSmallToList(List<ObjectData> objects, int userId, int objectType, int mapNum) {
        int itemCode = 0; // Assuming itemCode is 0 for interactive tiles
        int[][] tilePositions = {
                // <------------Hill---------->
                {52, 58}, {53, 58}, {54, 58}, // Add more positions as needed
                {51, 51},{52, 51},{54, 51},{55, 51},
                {51, 55},{52, 53},{55, 54},

                {89, 25},{90, 26},{92, 26},

                {143, 68},{143, 65},{143, 60},
                {142, 66}, {142, 62},
                {144, 61}, {144, 62}, {144, 63}, {144, 66},
                {145, 61}, {145, 63}
        };

        for (int[] pos : tilePositions) {
            objects.add(new ObjectData(userId, objectType, itemCode, pos[0], pos[1], mapNum));
        }
    }

    private static void addMonsters(int userId, int mapNum) {
        Entity entity = new Entity(Game.gameInstance);
        int monIndex = 0;

        int[][] townHall1Positions = {
                {entity.type_slime, entity.area_th_1, 42, 123},
                {entity.type_slime, entity.area_th_1, 39, 134},
                {entity.type_slime, entity.area_th_1, 55, 139},
                {entity.type_slime, entity.area_th_1, 64, 134},
                {entity.type_slime, entity.area_th_1, 66, 142},
                {entity.type_slime, entity.area_th_1, 42, 143},
                {entity.type_slime, entity.area_th_1, 43, 151},
                {entity.type_slime, entity.area_th_1, 54, 156},
                {entity.type_slime, entity.area_th_1, 38, 159},

                {entity.type_worm, entity.area_th_1, 49, 125},
                {entity.type_worm, entity.area_th_1, 48, 134},
                {entity.type_worm, entity.area_th_1, 48, 143},
                {entity.type_worm, entity.area_th_1, 61, 150},
                {entity.type_worm, entity.area_th_1, 50, 158},
                {entity.type_worm, entity.area_th_1, 68, 158},

                {entity.type_pacmanGreen, entity.area_th_1, 57, 145}
        };


        int[][] townHall2Positions = {
                {entity.type_worm, entity.area_th_2, 106, 135},
                {entity.type_worm, entity.area_th_2, 129, 132},

                {entity.type_slime, entity.area_th_2, 99, 136},
                {entity.type_slime, entity.area_th_2, 112, 135},
                {entity.type_slime, entity.area_th_2, 130, 136},
                {entity.type_slime, entity.area_th_2, 130, 124},

                {entity.type_pacman, entity.area_th_2, 122, 135},

                {entity.type_pacmanGreen, entity.area_th_2, 121, 125},

                {entity.type_spider, entity.area_th_2, 132, 130},

                {entity.type_redFly, entity.area_th_2, 118, 130}
        };

        int[][] townHall3Positions = {
                {entity.type_worm, entity.area_th_3, 146, 155},
                {entity.type_worm, entity.area_th_3, 173, 155},
                {entity.type_worm, entity.area_th_3, 163, 169},
                {entity.type_worm, entity.area_th_3, 145, 177},
                {entity.type_worm, entity.area_th_3, 175, 177},

                {entity.type_slime, entity.area_th_3, 166, 163},
                {entity.type_slime, entity.area_th_3, 159, 167},
                {entity.type_slime, entity.area_th_3, 183, 159},
                {entity.type_slime, entity.area_th_3, 171, 173},
                {entity.type_slime, entity.area_th_3, 176, 182},
                {entity.type_slime, entity.area_th_3, 143, 182},

                {entity.type_spider, entity.area_th_3, 149, 170},
                {entity.type_spider, entity.area_th_3, 183, 168},
                {entity.type_spider, entity.area_th_3, 176, 150},

                {entity.type_spiderBrown, entity.area_th_3, 144, 149},
                {entity.type_spiderBrown, entity.area_th_3, 150, 162},

                {entity.type_pacman, entity.area_th_3, 152, 180},
                {entity.type_pacman, entity.area_th_3, 158, 151},

                {entity.type_pacmanGreen, entity.area_th_3, 166, 182},
                {entity.type_pacmanGreen, entity.area_th_3, 182, 151}
        };

        int[][] townHall4Positions = {
                {entity.type_worm, entity.area_th_4, 83, 179},
                {entity.type_worm, entity.area_th_4, 124, 174},

                {entity.type_slime, entity.area_th_4, 85, 159},
                {entity.type_slime, entity.area_th_4, 93, 164},
                {entity.type_slime, entity.area_th_4, 81, 173},
                {entity.type_slime, entity.area_th_4, 100, 174},
                {entity.type_slime, entity.area_th_4, 89, 182},
                {entity.type_slime, entity.area_th_4, 109, 159},
                {entity.type_slime, entity.area_th_4, 119, 163},
                {entity.type_slime, entity.area_th_4, 119, 177},
                {entity.type_slime, entity.area_th_4, 132, 170},
                {entity.type_slime, entity.area_th_4, 131, 182},

                {entity.type_spiderBrown, entity.area_th_4, 80, 164},
                {entity.type_spiderBrown, entity.area_th_4, 92, 174},

                {entity.type_spider, entity.area_th_4, 115, 182},
                {entity.type_spider, entity.area_th_4, 101, 161},
                {entity.type_spider, entity.area_th_4, 125, 160},

                {entity.type_pacmanGreen, entity.area_th_4, 78, 183},
                {entity.type_pacmanGreen, entity.area_th_4, 101, 168},
                {entity.type_pacmanGreen, entity.area_th_4, 124, 183},

                {entity.type_pacman, entity.area_th_4, 114, 172}

        };


        // <----------Town Hall 5----------->
        int[][] townHall5Positions = {
                {entity.type_worm, entity.area_th_5, 38, 170},
                {entity.type_worm, entity.area_th_5, 58, 170},
                {entity.type_worm, entity.area_th_5, 56, 177},
                {entity.type_worm, entity.area_th_5, 19, 174},

                {entity.type_slime, entity.area_th_5, 28, 169},
                {entity.type_slime, entity.area_th_5, 48, 172},
                {entity.type_slime, entity.area_th_5, 54, 165},
                {entity.type_slime, entity.area_th_5, 56, 182},
                {entity.type_slime, entity.area_th_5, 70, 176},

                {entity.type_pacmanGreen, entity.area_th_5, 34, 165},
                {entity.type_pacmanGreen, entity.area_th_5, 47, 168},
                {entity.type_pacmanGreen, entity.area_th_5, 45, 176},
                {entity.type_pacmanGreen, entity.area_th_5, 64, 178},

                {entity.type_pacman, entity.area_th_5, 70, 183},
                {entity.type_pacman, entity.area_th_5, 67, 166},
                {entity.type_pacman, entity.area_th_5, 21, 166},
                {entity.type_pacman, entity.area_th_5, 18, 179}
        };

        // <---------Hill 1----------->
        int[][] hill1Positions = {
                {entity.type_worm, entity.area_hill_1, 148, 113},
                {entity.type_worm, entity.area_hill_1, 184, 113},
                {entity.type_worm, entity.area_hill_1, 161, 104},

                {entity.type_slime, entity.area_hill_1, 152, 105},
                {entity.type_slime, entity.area_hill_1, 172, 108},
                {entity.type_slime, entity.area_hill_1, 154, 116},
                {entity.type_slime, entity.area_hill_1, 161, 122},
                {entity.type_slime, entity.area_hill_1, 174, 119},

                {entity.type_redFly, entity.area_hill_1, 167, 113},
                {entity.type_blueGhost, entity.area_hill_1, 177, 104},

                {entity.type_spiderBrown, entity.area_hill_1, 150, 123}
        };


        // <----------Hill 2----------->
        int[][] hill2Positions = {
                {entity.type_worm, entity.area_hill_2, 102, 89},
                {entity.type_worm, entity.area_hill_2, 123, 89},
                {entity.type_worm, entity.area_hill_2, 123, 97},

                {entity.type_slime, entity.area_hill_2, 113, 87},
                {entity.type_slime, entity.area_hill_2, 102, 81},
                {entity.type_slime, entity.area_hill_2, 134, 97},
                {entity.type_slime, entity.area_hill_2, 130, 85},

                {entity.type_redFly, entity.area_hill_2, 116, 82},
                {entity.type_redFly, entity.area_hill_2, 127, 101},

                {entity.type_blueGhost, entity.area_hill_2, 97, 80},
                {entity.type_blueGhost, entity.area_hill_2, 115, 91},
                {entity.type_blueGhost, entity.area_hill_2, 137, 103},
                {entity.type_blueGhost, entity.area_hill_2, 136, 81}
        };

        // <----------Hill 3---------->
        int[][] hill3Positions = {
                {entity.type_slime, entity.area_hill_3, 44, 73},
                {entity.type_slime, entity.area_hill_3, 53, 78},
                {entity.type_slime, entity.area_hill_3, 65, 71},
                {entity.type_slime, entity.area_hill_3, 71, 80},
                {entity.type_slime, entity.area_hill_3, 81, 77},

                {entity.type_worm, entity.area_hill_3, 87, 74},
                {entity.type_worm, entity.area_hill_3, 49, 74},

                {entity.type_pacman, entity.area_hill_3, 73, 70},
                {entity.type_pacman, entity.area_hill_3, 22, 76},

                {entity.type_blueGhost, entity.area_hill_3, 20, 67},
                {entity.type_blueGhost, entity.area_hill_3, 37, 78},
                {entity.type_blueGhost, entity.area_hill_3, 58, 67},
                {entity.type_blueGhost, entity.area_hill_3, 89, 69},

                {entity.type_redFly, entity.area_hill_3, 65, 74},
                {entity.type_redFly, entity.area_hill_3, 31, 71},

                {entity.type_spider, entity.area_hill_3, 86, 81}

        };

        // <--------------Hill 4---------------->
        int[][] hill4Positions = {
                {entity.type_redFly, entity.area_hill_4, 71, 105},
                {entity.type_redFly, entity.area_hill_4, 29, 100},

                {entity.type_spider, entity.area_hill_4, 46, 104},

                {entity.type_blueGhost, entity.area_hill_4, 34, 92},
                {entity.type_blueGhost, entity.area_hill_4, 60, 93},
                {entity.type_blueGhost, entity.area_hill_4, 59, 106},

                {entity.type_slime, entity.area_hill_4, 46, 194},
                {entity.type_slime, entity.area_hill_4, 71, 97}
        };


        // <------------Monster Island 1------------>
        int[][] mi1positions = {
                {entity.type_spider, entity.area_mi_1, 110, 19},
                {entity.type_spider, entity.area_mi_1, 118, 32},
                {entity.type_spider, entity.area_mi_1, 140, 33},

                {entity.type_sixEyes, entity.area_mi_1, 128, 23},
                {entity.type_sixEyes, entity.area_mi_1, 114, 24},

                {entity.type_redFly, entity.area_mi_1, 130, 36},
                {entity.type_redFly, entity.area_mi_1, 138, 24},

                {entity.type_redOrc, entity.area_mi_1, 123, 16},

                {entity.type_arc, entity.area_mi_1, 121, 27},

                {entity.type_worm, entity.area_mi_1, 134, 27}
        };

        // <-----------Monster Island 2------------->
        int[][] mi2positions = {
                {entity.type_redOrc, entity.area_mi_2, 151, 17},
                {entity.type_redOrc, entity.area_mi_2, 166, 21},
                {entity.type_redOrc, entity.area_mi_2, 182, 18},
                {entity.type_redOrc, entity.area_mi_2, 183, 36},

                {entity.type_arc, entity.area_mi_2, 160, 34},

                {entity.type_blueGhost, entity.area_mi_2, 157, 20},
                {entity.type_blueGhost, entity.area_mi_2, 175, 27},

                {entity.type_redFly, entity.area_mi_2, 155, 26},
                {entity.type_redFly, entity.area_mi_2, 182, 26},

                {entity.type_sixEyes, entity.area_mi_2, 167, 27},

                {entity.type_skeleton, entity.area_mi_2, 175, 37},

                {entity.type_worm, entity.area_mi_2, 161, 27}

        };


        // <----------Monster Island 3---------->
        int[][] mi3positions = {
                {entity.type_blueGhost, entity.area_mi_3, 182, 49},

                {entity.type_redOrc, entity.area_mi_3, 167, 42},
                {entity.type_redOrc, entity.area_mi_3, 167, 53},
                {entity.type_redOrc, entity.area_mi_3, 184, 61},

                {entity.type_arc, entity.area_mi_3, 179, 43},
                {entity.type_arc, entity.area_mi_3, 155, 42},

                {entity.type_redFly, entity.area_mi_3, 177, 53},

                {entity.type_skeleton, entity.area_mi_3, 160, 46},
                {entity.type_skeleton, entity.area_mi_3, 171, 57},
                {entity.type_skeleton, entity.area_mi_3, 180, 57},
                {entity.type_skeleton, entity.area_mi_3, 172, 47}
        };



        // Monster setup without area
        int[][] noAreaPositions = {
                {entity.type_slimeMother,0,31,32},
                {entity.type_slime, 0, 39, 34},
                {entity.type_slime, 0, 21, 32},
                {entity.type_slime, 0, 29, 39},
                {entity.type_slime, 0, 18, 22},
                {entity.type_slime, 0, 44, 22},


                {entity.type_arc, 0, 71, 24},
                {entity.type_redFly, 0, 67, 27},
                {entity.type_blueGhost, 0, 86, 24},
                {entity.type_arc, 0, 66, 33},
                {entity.type_slime, 0, 70, 36},
                {entity.type_worm, 0, 78, 33},
                {entity.type_slime, 0, 94, 34},
                {entity.type_pacmanGreen, 0, 99, 27},
                {entity.type_arc, 0, 102, 48},
                {entity.type_blueGhost, 0, 96, 57},
                {entity.type_slime, 0, 105, 54},
                {entity.type_spider, 0, 115, 62},
                {entity.type_spider, 0,111, 49},
                {entity.type_spider, 0, 129, 57},
                {entity.type_spiderBrown, 0, 128, 50},
                {entity.type_spiderBrown, 0, 114, 57},
                {entity.type_redFly, 0, 121, 53},
                {entity.type_slime, 0, 122, 62},
                {entity.type_slime, 0, 135, 72},
                {entity.type_slime, 0, 163, 94},
                {entity.type_arc, 0, 156, 85},
                {entity.type_pacman, 0, 169, 81},
                {entity.type_slime, 0, 148, 79},
                {entity.type_redFly, 0, 54, 45},
                {entity.type_blueGhost, 0, 64, 47},
                {entity.type_redFly, 0, 64, 40},


                // <-----Soil area----->
                {entity.type_sixEyes, 0, 161, 71},
                {entity.type_sixEyes, 0, 156, 63},
                {entity.type_sixEyes, 0, 169, 71},
                {entity.type_sixEyes, 0, 156, 77},


                // <-----Port area------>
                {entity.type_sixEyes, 0, 17, 116},
                {entity.type_blueGhost, 0 , 24, 119},
                {entity.type_sixEyes, 0, 31, 119},

        };

        int[][][] allPositions = {townHall1Positions, townHall2Positions, townHall3Positions, townHall4Positions, townHall5Positions, hill1Positions, hill2Positions, hill3Positions, hill4Positions, mi1positions, mi2positions, mi3positions, noAreaPositions};
//        int[][][] allPositions = {mi3positions};

        List<int[]> allMonsters = new ArrayList<>();
        for (int i = 0; i < allPositions.length; i++) {
            for (int[] pos : allPositions[i]) {
                allMonsters.add(new int[]{userId, monIndex++, pos[0], pos[1], pos[2], pos[3], mapNum});
            }
        }

        MyJDBC.addMonsters(allMonsters);
    }


    public static void addInventoryToDB(int userId) {
        int[] itemCodes = new int[]{101, 102, 103, 104, 105, 106, 107,108,109, 201, 202, 203, 204, 205, 206, 207, 208, 303, 304, 305, 306, 307,308};
        MyJDBC.addInventory(userId, itemCodes);
    }
}
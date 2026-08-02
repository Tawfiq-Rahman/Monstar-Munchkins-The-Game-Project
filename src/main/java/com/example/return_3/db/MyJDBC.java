
package com.example.return_3.db;

import com.example.return_3.entity.Entity;
import com.example.return_3.interactiveTile.*;
import com.example.return_3.main.Game;
import com.example.return_3.main.UtilityTool;
import com.example.return_3.monster.*;
import com.example.return_3.object.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//JDBC class is used to interact with our MYSQL database to perform activites such as retrieving and updatin our db
public class MyJDBC {

    //database configuration
    //%%%%%%%%%%------->For OFFLINE DB<----------%%%%%%%%%%
    // private static final String DB_URL ="jdbc:mysql://127.0.0.1:3306/game";
    // private static final String DB_USERNAME= "root";
    // private static  final String DB_PASSWORD="password";
    //%%%%%%%%%%------->For Online DB<----------%%%%%%%%%%
    private static final String DB_URL = "jdbc:mysql://SG-Monster-Munchkins-8715-mysql-master.servers.mongodirector.com:3306/game";
    private static final String DB_USERNAME = "sgroot";
    private static final String DB_PASSWORD = "8cDE6XrDHO5Wv+6m";

    //if valid return an object with the user's information
    public static User validateLogin(String username, String password){
        try {
            //establish a connection to the database using configuration
            Connection connection= DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
            //create sql query
            PreparedStatement preparedStatement= connection.prepareStatement(
                    "SELECT * FROM game.Users WHERE username = ? AND password= ?"
            );
            //replace the ? with values
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);

            //execute query and store into a result set
            ResultSet resultSet = preparedStatement.executeQuery();

            //next() return true or false
            //true - query returned data and result set now points to the first row
            // false - query returned no dat and reset set equals to null
            if(resultSet.next()){
                //success
                //get id
                int userId=resultSet.getInt("user_id");
                return getUserProperties(userId,username,password,resultSet);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        //not valid user
        return  null;
    }

    public static boolean checkUsername(String username){
        try {
            //establish a connection to the database using configuration
            Connection connection= DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
            //create sql query
            PreparedStatement preparedStatement= connection.prepareStatement(
                    "SELECT * FROM game.Users WHERE username = ?"
            );
            //replace the ? with values
            preparedStatement.setString(1,username);


            //execute query and store into a result set
            ResultSet resultSet = preparedStatement.executeQuery();

            //next() return true or false
            //true - query returned data and result set now points to the first row
            // false - query returned no dat and reset set equals to null
            if(resultSet.next()){
                //success
                return true;
                //get id
//                int userId=resultSet.getInt("user_id");
//                return getUserProperties(userId,username,password,resultSet);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        //not valid user
        return  false;
    }


    private static User getUserProperties(int userId, String username, String password, ResultSet resultSet) throws SQLException {
        int coin= resultSet.getInt("coin");
        int energy= resultSet.getInt("energy");
        int maxEnergy= resultSet.getInt("maxEnergy");
        int life= resultSet.getInt("life");
        int maxLife= resultSet.getInt("maxLife");
        int exp= resultSet.getInt("exp");
        int nextLevelExp= resultSet.getInt("nextLevelExp");
        int level= resultSet.getInt("level");
        int strength= resultSet.getInt("strength");
        int dexterity= resultSet.getInt("dexterity");
        int bullet= resultSet.getInt("bullet");
        int maxBullet= resultSet.getInt("maxBullet");
        int worldX= resultSet.getInt("world_x");
        int worldY= resultSet.getInt("world_y");
        boolean isShipStarted = resultSet.getBoolean("is_ship_started");
        boolean npcFireball = resultSet.getBoolean("npc_fireball");
        boolean npcGlobalChat = resultSet.getBoolean("npc_global_chat");
        boolean npcMotherSlime = resultSet.getBoolean("npc_mother_slime");
        boolean npcWelcome = resultSet.getBoolean("npc_welcome");
        boolean npcAxe = resultSet.getBoolean("npc_axe");
        boolean gameOver = resultSet.getBoolean("game_over");

        // Return user object
        return new User(userId, username, password, worldX, worldY, coin, energy, maxEnergy, life, maxLife, exp, nextLevelExp, level, strength, dexterity, bullet, maxBullet, isShipStarted, npcFireball, npcGlobalChat, npcMotherSlime, npcWelcome,npcAxe,gameOver);
        //return user object
    }

    public static User getUserData(int userId) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM game.Users WHERE user_id = ?"
            );
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Retrieve user data from the result set
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                return getUserProperties(userId, username, password, resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // User not found or error occurred
    }

    //true - register success
    //false - register failed
    public static int register(String username, String password) {
        try {
            if (!checkUser(username)) {
                Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO game.Users(username,password)" + "VALUES(? , ?)", Statement.RETURN_GENERATED_KEYS
                );
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                preparedStatement.executeUpdate();

                // Retrieve the auto-generated user ID
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // Return the user ID
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Registration failed
    }


    //check if username already exists in the db
    //true - user exists
    //false - user doesn't exist
    private  static boolean checkUser(String username){
        try{
            Connection connection= DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
            PreparedStatement preparedStatement= connection.prepareStatement(
                    "SELECT * FROM game.Users WHERE username = ?"
            );
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();

            //this means that the query returned no data meaning that the username is available
            if(!resultSet.next()){
                return false;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    //Update users table before exit the game.
    public static void updateUser(Entity user) {
        try {
            // Establish a connection to the database using configuration
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            // Create SQL query
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE game.Users SET coin = ?, energy = ?, maxEnergy = ?, life = ?, maxLife = ?, " +
                            "exp = ?, nextLevelExp = ?, level = ?, strength = ?, dexterity = ?, " +
                            "bullet = ?, maxBullet = ?, world_x = ?, world_y = ?, is_ship_started = ?," +
                            "npc_fireball = ?, npc_global_chat = ?, npc_mother_slime = ?, npc_axe = ?," +
                            "npc_welcome = ?, game_over = ? WHERE user_id = ?"
            );
            //, can_touch_event = ?

            // Replace the placeholders with values
            preparedStatement.setInt(1, user.coin);
            preparedStatement.setInt(2, user.energy);
            preparedStatement.setInt(3, user.maxEnergy);
            preparedStatement.setInt(4, user.life);
            preparedStatement.setInt(5, user.maxLife);
            preparedStatement.setInt(6, user.exp);
            preparedStatement.setInt(7, user.nextLevelExp);
            preparedStatement.setInt(8, user.level);
            preparedStatement.setInt(9, user.strength);
            preparedStatement.setInt(10, user.dexterity);
            preparedStatement.setInt(11, user.mana);
            preparedStatement.setInt(12, user.maxMana);
            preparedStatement.setInt(13, user.worldX);
            preparedStatement.setInt(14, user.worldY);
            preparedStatement.setBoolean(15, Game.gameInstance.isShipStarted);
            preparedStatement.setBoolean(16, Game.gameInstance.npcFireball);
            preparedStatement.setBoolean(17, Game.gameInstance.npcGlobalChat);
            preparedStatement.setBoolean(18, Game.gameInstance.npcMotherSlime);
            preparedStatement.setBoolean(19, Game.gameInstance.npcAxe);
            preparedStatement.setBoolean(20, Game.gameInstance.npcWelcome);
            preparedStatement.setBoolean(21, Game.gameInstance.gameOver);
            //preparedStatement.setBoolean(15, user.can_touch_event);
            preparedStatement.setInt(22, user.playerId); // user_id for WHERE clause

            // Execute the update query
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User data updated successfully.");
            } else {
                System.out.println("No user found with the given ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean deleteUserById(int userId) {
        Connection connection = null;
        PreparedStatement deleteMonstersStmt = null;
        PreparedStatement deleteObjectsStmt = null;
        PreparedStatement deleteInventoryStmt = null;
        PreparedStatement deleteUserStmt = null;

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            connection.setAutoCommit(false); // Begin transaction

            // Delete related entries in the Monsters table
            deleteMonstersStmt = connection.prepareStatement("DELETE FROM game.Monsters WHERE user_id = ?");
            deleteMonstersStmt.setInt(1, userId);
            deleteMonstersStmt.executeUpdate();

            // Delete related entries in the Objects table
            deleteObjectsStmt = connection.prepareStatement("DELETE FROM game.Objects WHERE user_id = ?");
            deleteObjectsStmt.setInt(1, userId);
            deleteObjectsStmt.executeUpdate();

            // Delete related entries in the Inventory table
            deleteInventoryStmt = connection.prepareStatement("DELETE FROM game.Inventory WHERE user_id = ?");
            deleteInventoryStmt.setInt(1, userId);
            deleteInventoryStmt.executeUpdate();

            // Finally, delete the user from the Users table
            deleteUserStmt = connection.prepareStatement("DELETE FROM game.Users WHERE user_id = ?");
            deleteUserStmt.setInt(1, userId);
            deleteUserStmt.executeUpdate();

            connection.commit(); // Commit transaction
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback(); // Rollback transaction on error
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            return false;
        } finally {
            // Close resources
            try {
                if (deleteMonstersStmt != null) deleteMonstersStmt.close();
                if (deleteObjectsStmt != null) deleteObjectsStmt.close();
                if (deleteInventoryStmt != null) deleteInventoryStmt.close();
                if (deleteUserStmt != null) deleteUserStmt.close();
                if (connection != null) connection.close();
            } catch (SQLException closeEx) {
                closeEx.printStackTrace();
            }
        }
    }

//    public static void updateUser2(Entity user) {
//        try {
//            // Establish a connection to the database using configuration
//            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
//
//            // Create SQL query
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "UPDATE users SET coin = ?, energy = ?, maxEnergy = ?, life = ?, maxLife = ?, " +
//                            "exp = ?, nextLevelExp = ?, level = ?, strength = ?, dexterity = ?, " +
//                            "bullet = ?, maxBullet = ?, world_x = ?, world_y = ?, is_ship_started = ?, " +
//                            "npc_fireball = ?, npc_global_chat = ?, npc_mother_slime = ?, npc_axe = ?, " +
//                            "npc_welcome = ? WHERE user_id = ?"
//            );
//
//            // Replace the placeholders with values
//            preparedStatement.setInt(1, user.coin);
//            preparedStatement.setInt(2, user.energy);
//            preparedStatement.setInt(3, user.maxEnergy);
//            preparedStatement.setInt(4, user.life);
//            preparedStatement.setInt(5, user.maxLife);
//            preparedStatement.setInt(6, user.exp);
//            preparedStatement.setInt(7, user.nextLevelExp);
//            preparedStatement.setInt(8, user.level);
//            preparedStatement.setInt(9, user.strength);
//            preparedStatement.setInt(10, user.dexterity);
//            preparedStatement.setInt(11, user.worldX);
//            preparedStatement.setInt(12, user.worldY);
//            preparedStatement.setBoolean(15, user.isShipStarted);
//            preparedStatement.setBoolean(16, user.npcFireball);
//            preparedStatement.setBoolean(17, user.npcGlobalChat);
//            preparedStatement.setBoolean(18, user.npcMotherSlime);
//            preparedStatement.setBoolean(19, user.npcAxe);
//            preparedStatement.setBoolean(20, user.npcWelcome);
//            preparedStatement.setInt(21, user.userId); // user_id for WHERE clause
//
//            // Execute the update query
//            int rowsAffected = preparedStatement.executeUpdate();
//            if (rowsAffected > 0) {
//                System.out.println("User data updated successfully.");
//            } else {
//                System.out.println("No user found with the given ID.");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }


    //////For INVENTORY
//we i need to check this method once again because there might be one operation and i did multiple for not knowing the


//    public static void addInventory(int userId, int itemCode) {
//        try {
//            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "INSERT INTO Inventory (user_id, item_code, item_count) " +
//                            "VALUES (?, ?, 0)"
//            );
//            preparedStatement.setInt(1, userId);
//            preparedStatement.setInt(2, itemCode);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public static void addInventory(int userId, int[] itemCodes) {
        String query = "INSERT INTO game.Inventory (user_id, item_code, item_count) VALUES ";
        StringBuilder values = new StringBuilder();

        for (int i = 0; i < itemCodes.length; i++) {
            values.append("(?, ?, 0)");
            if (i < itemCodes.length - 1) {
                values.append(", ");
            }
        }

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query + values.toString())) {

            int parameterIndex = 1;
            for (int itemCode : itemCodes) {
                preparedStatement.setInt(parameterIndex++, userId);
                preparedStatement.setInt(parameterIndex++, itemCode);
            }

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }







    public static ArrayList<Entity> getUserInventory(int userId) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM game.Inventory WHERE user_id = ?"
            );
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<Entity> inventory = new ArrayList<>();
            while (resultSet.next()) {
                int itemCode = resultSet.getInt("item_code");
                int count = resultSet.getInt("item_count");
                for(int i=0;i<count;i++){
                    inventory.add(UtilityTool.getInventoryItem(itemCode));
                }
            }
            return inventory;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
//    public static void addItemToInventory(int userId, int itemCode) {
//        try {
//            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "INSERT INTO Inventory (user_id, item_code, item_count) VALUES (?, ?, 1) " +
//                            "ON DUPLICATE KEY UPDATE item_count = item_count + 1"
//            );
//            preparedStatement.setInt(1, userId);
//            preparedStatement.setInt(2, itemCode);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public static void addItemToInventory(int userId, int itemCode) {
        String sql = "UPDATE game.Inventory SET item_count = item_count + 1 WHERE user_id = ? AND item_code = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, itemCode);
            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated == 0) {
                System.out.println("No record found for user_id " + userId + " and item_code " + itemCode);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //    public static void removeItemFromInventory(int userId, int itemCode) {
//        try {
//            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "UPDATE Inventory SET item_count = item_count - 1 WHERE user_id = ? AND item_code = ? AND item_count > 0"
//            );
//            preparedStatement.setInt(1, userId);
//            preparedStatement.setInt(2, itemCode);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
    public static void removeItemFromInventory(int userId, int itemCode) {
        String sql = "UPDATE game.Inventory SET item_count = item_count - 1 WHERE user_id = ? AND item_code = ? AND item_count > 0";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, itemCode);
            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated == 0) {
                System.out.println("No item found or item count is already 0 for user_id " + userId + " and item_code " + itemCode);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // <----------------OBJECT LINE------------------->
    //in this addInteractivetile  we will add tile by this method at the time of sign up .
    //when user will create then the interactive tile will also be created


//    public static void addObject(int userId, int objectType, int itemCode, int col, int row, int mapNum) {
//        try {
//            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "INSERT INTO Objects (user_id, object_type, item_code, tile_col, tile_row, map_num) " +
//                            "VALUES (?, ?, ?, ?, ?, ?)"
//            );
//            preparedStatement.setInt(1, userId);
//            preparedStatement.setInt(2, objectType);
//            preparedStatement.setInt(3, itemCode);
//            preparedStatement.setInt(4, col);
//            preparedStatement.setInt(5, row);
//            preparedStatement.setInt(6, mapNum);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//

    public static void addObjectBatch(List<ObjectData> objects) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO game.Objects (user_id, object_type, item_code, tile_col, tile_row, map_num) " +
                            "VALUES (?, ?, ?, ?, ?, ?)"
            );
            for (ObjectData object : objects) {
                preparedStatement.setInt(1, object.getUserId());
                preparedStatement.setInt(2, object.getObjectType());
                preparedStatement.setInt(3, object.getItemCode());
                preparedStatement.setInt(4, object.getCol());
                preparedStatement.setInt(5, object.getRow());
                preparedStatement.setInt(6, object.getMapNum());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }















    public static void setObjects(int userId, int mapNum) {
        int oIndex=0;
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT tile_col, tile_row, item_code,object_type FROM  game.Objects " +
                            "WHERE user_id = ? AND map_num = ? AND destroyed = FALSE"
            );
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, mapNum);
            // preparedStatement.setInt(3, objectType);
            ResultSet resultSet = preparedStatement.executeQuery();
            int i = 0;

            int count =0;
            // Iterate over the result set and create instances of objects based on type
            while (resultSet.next()) {
                System.out.println("count: "+count);

                int itemCode= resultSet.getInt("item_code");
                int col = resultSet.getInt("tile_col");
                int row = resultSet.getInt("tile_row");
                int objectType = resultSet.getInt("object_type");

                // Create instance of object based on the type retrieved from the database
                if (objectType == Game.gameInstance.type_interactiveTreeBig) {
                    // Interactive tile
                    InteractiveTile cuttableTree = new CuttableTreeBig(Game.gameInstance, col, row);
                    Game.gameInstance.iTile[mapNum][i] = cuttableTree;
                    i++;
                    System.out.println("tile number " + i  );
                } else if (objectType == Game.gameInstance.type_interactiveTreeSmall) {
                    // Interactive tile
                    InteractiveTile breakableBigRock = new CuttableTreeSmall(Game.gameInstance, col, row);
                    Game.gameInstance.iTile[mapNum][i] = breakableBigRock;
                    i++;
                    System.out.println("tile number " + i  );
                }else if (objectType == Game.gameInstance.type_interactiverockBig) {
                    // Interactive tile
                    InteractiveTile breakableBigRock = new BreakableBigRock(Game.gameInstance, col, row);
                    Game.gameInstance.iTile[mapNum][i] = breakableBigRock;
                    i++;
                    System.out.println("tile number " + i  );
                } else if (objectType == Game.gameInstance.type_interactiverockSmall) {
                    // Interactive tile
                    InteractiveTile breakableSmallRock = new BreakableSmallRock(Game.gameInstance, col, row);
                    Game.gameInstance.iTile[mapNum][i] = breakableSmallRock;
                    i++;
                    System.out.println("tile number " + i  );
                } else if (objectType == Game.gameInstance.type_object) {
                    Entity entity;
                    switch (itemCode){
                        case 101:   entity =new OBJ_Sword_Normal(Game.gameInstance);break;
                        case 105:   entity =new OBJ_Sword_Special(Game.gameInstance);break;
                        case 106:   entity =new OBJ_FireSword(Game.gameInstance);break;
                        case 108:   entity =new OBJ_IceSword(Game.gameInstance);break;
                        case 102:   entity =new OBJ_Axe(Game.gameInstance);break;
                        case 109:   entity =new OBJ_Tomahawk(Game.gameInstance);break;
                        case 103:   entity =new OBJ_Shield_Wood(Game.gameInstance);break;
                        case 301:   entity =new OBJ_Coin(Game.gameInstance);break;
                        case 303:   entity =new OBJ_Potion_Red(Game.gameInstance);break;
                        case 304:   entity =new OBJ_Key(Game.gameInstance);break;
                        case 305:   entity =new OBJ_PowerPotion(Game.gameInstance);break;
                        case 306:   entity =new OBJ_DefensePotion(Game.gameInstance);break;
                        case 307:   entity =new OBJ_SpeedPotion(Game.gameInstance);break;
                        case 308:   entity =new OBJ_BlueKey(Game.gameInstance);break;
                        case 309:   entity =new OBJ_HugeCoin(Game.gameInstance);break;
                        case 320:   entity =new OBJ_Door(Game.gameInstance);break;
                        case 321:   entity =new OBJ_Chest(Game.gameInstance);break;
//                        case 321:   entity =new OBJ_Chest(Game.gameInstance);break;
                        default:entity = null;
                    }
                    if(entity!=null){
                        Game.gameInstance.obj[mapNum][oIndex]=entity;
                        Game.gameInstance.obj[mapNum][oIndex].worldX=Game.gameInstance.tileSize*col;
                        Game.gameInstance.obj[mapNum][oIndex].worldY=Game.gameInstance.tileSize*row;
                    }
                    oIndex++;
                    System.out.println("object number " + oIndex  );
                    // Object
                    // Create instance of Object using itemCode
                }
                count++;

            }

            // Close resources
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


//        try {
//            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "SELECT tile_col, tile_row FROM Objects " +
//                            "WHERE user_id = ? AND map_num = ? AND destroyed = TRUE AND item_code= 320"
//            );
//            preparedStatement.setInt(1, userId);
//            preparedStatement.setInt(2, mapNum);
//            // preparedStatement.setInt(3, objectType);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            int i = 0;
//
//            int count =0;
//            // Iterate over the result set and create instances of objects based on type
//            while (resultSet.next()) {
//                System.out.println("count: "+count);
//
////                int itemCode= resultSet.getInt("item_code");
//                int col = resultSet.getInt("tile_col");
//                int row = resultSet.getInt("tile_row");
////                int objectType = resultSet.getInt("object_type");
//
//
//                        Game.gameInstance.obj[mapNum][oIndex]=new OBJ_Door(Game.gameInstance);
//                        Game.gameInstance.obj[mapNum][oIndex].down1=Game.gameInstance.obj[mapNum][oIndex].image2;
//                        Game.gameInstance.obj[mapNum][oIndex].collision=false;
//                        Game.gameInstance.obj[mapNum][oIndex].destroyed=true;
//                        Game.gameInstance.obj[mapNum][oIndex].worldX=Game.gameInstance.tileSize*col;
//                        Game.gameInstance.obj[mapNum][oIndex].worldY=Game.gameInstance.tileSize*row;
//
//                    oIndex++;
//
//
//            }
//            // Close resources
//            resultSet.close();
//            preparedStatement.close();
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }


        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT tile_col, tile_row, item_code FROM  game.Objects " +
                            "WHERE user_id = ? AND map_num = ? AND destroyed = TRUE AND item_code IN (320, 321)"
            );
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, mapNum);
            ResultSet resultSet = preparedStatement.executeQuery();
            int i = 0;
            int count = 0;

            // Iterate over the result set and create instances of objects based on type
            while (resultSet.next()) {
                System.out.println("count: " + count);

                int col = resultSet.getInt("tile_col");
                int row = resultSet.getInt("tile_row");
                int itemCode = resultSet.getInt("item_code");

                if (itemCode == 320) {
                    Game.gameInstance.obj[mapNum][oIndex] = new OBJ_Door(Game.gameInstance);
                    Game.gameInstance.obj[mapNum][oIndex].collision = false;
                } else if (itemCode == 321) {
                    Game.gameInstance.obj[mapNum][oIndex] = new OBJ_Chest(Game.gameInstance);
                }

                Game.gameInstance.obj[mapNum][oIndex].down1 = Game.gameInstance.obj[mapNum][oIndex].image2;

                Game.gameInstance.obj[mapNum][oIndex].destroyed = true;
                Game.gameInstance.obj[mapNum][oIndex].worldX = Game.gameInstance.tileSize * col;
                Game.gameInstance.obj[mapNum][oIndex].worldY = Game.gameInstance.tileSize * row;

                oIndex++;
                count++;
            }

            // Close resources
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }
    public static void updateObjectDestroyedStatus(int userId, int mapNum, int row, int col, int objectType, boolean destroyed) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE game.Objects SET destroyed = ? " +
                            "WHERE user_id = ? AND map_num = ? AND tile_row = ? AND tile_col = ? AND object_type = ?"
            );
            preparedStatement.setBoolean(1, destroyed);
            preparedStatement.setInt(2, userId);
            preparedStatement.setInt(3, mapNum);
            preparedStatement.setInt(4, row);
            preparedStatement.setInt(5, col);
            preparedStatement.setInt(6, objectType);
            // Execute the update query
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Object data updated successfully.");
            } else {
                System.out.println("No object found with fgggggggggggthe given data.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    ////  <------------  MONSTER
    public static void addMonsters(List<int[]> monsters) {
        String sql = "INSERT INTO game.Monsters (user_id, indexNum, monster_type, area_type, tile_col, tile_row, map_num) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            for (int[] monster : monsters) {
                pstmt.setInt(1, monster[0]);
                pstmt.setInt(2, monster[1]);
                pstmt.setInt(3, monster[2]);
                pstmt.setInt(4, monster[3]);
                pstmt.setInt(5, monster[4]);
                pstmt.setInt(6, monster[5]);
                pstmt.setInt(7, monster[6]);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


//public static void addMonster(int userId, int monsterType, int areaType, int col, int row, int mapNum, int indexNum) {
//
//    try {
//        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
//        PreparedStatement preparedStatement = connection.prepareStatement(
//                "INSERT INTO Monsters (user_id, monster_type, area_type, tile_col, tile_row, indexNum, map_num) " +
//                        "VALUES (?, ?, ?, ?, ?, ?, ?)"
//        );
//        preparedStatement.setInt(1, userId);
//        preparedStatement.setInt(2, monsterType);
//        preparedStatement.setInt(3, areaType);
//        preparedStatement.setInt(4, col);
//        preparedStatement.setInt(5, row);
//        preparedStatement.setInt(6, indexNum);
//        preparedStatement.setInt(7, mapNum);
//        preparedStatement.executeUpdate();
//    } catch (SQLException e) {
//        e.printStackTrace();
//    }
//}
//


    public static void setMonsters(int userId, int mapNum) {
        Game game = Game.gameInstance;
        Entity entity00 = new Entity(game);
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT tile_col, tile_row, area_type, monster_type, indexNum FROM game.Monsters " +
                            "WHERE user_id = ? AND map_num = ? AND destroyed = FALSE"
            );
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, mapNum);
            ResultSet resultSet = preparedStatement.executeQuery();
            //int i = 0; // Index for MONSTER
            // Iterate over the result set and create instances of monsters
            while (resultSet.next()) {
                int areaType = resultSet.getInt("area_type");
                int col = resultSet.getInt("tile_col");
                int row = resultSet.getInt("tile_row");
                int monsterType = resultSet.getInt("monster_type");
                int indexNum = resultSet.getInt("indexNum");

                Entity monster = null;
                // Determine the type of monster and create an instance accordingly
                if (monsterType == entity00.type_pacman) {
                    monster = new Mon_Pac(game,areaType);
                } else if (monsterType == entity00.type_spider) {
                    monster = new Mon_Spider(game,areaType);
                } else if (monsterType == entity00.type_spiderBrown) {
                    monster = new Mon_SpiderBrown(game, areaType);
                }else if (monsterType == entity00.type_arc) {
                    monster = new Mon_ORC(game,areaType);
                }else if (monsterType == entity00.type_skeleton) {
                    monster = new Mon_Skeleton(game, areaType);
                }else if (monsterType == entity00.type_redFly) {
                    monster = new Mon_RedFly(game, areaType);
                }else if (monsterType == entity00.type_slimeMother) {
                    monster = new Mon_GreenSlimeMother(game, areaType);
                } else if (monsterType == entity00.type_redOrc) {
                    monster = new Mon_RedORC(game,areaType);
                } else if (monsterType == entity00.type_slime) {
                    monster = new Mon_Green(game,areaType);
                } else if (monsterType == entity00.type_worm) {
                    monster = new Mon_Worm(game,areaType);
                }else if (monsterType == entity00.type_pacmanGreen) {
                    monster = new Mon_PacGreen(game,areaType);
                }else if (monsterType == entity00.type_blueGhost) {
                    monster = new Mon_BlueGhost(game,areaType);
                }else if (monsterType == entity00.type_sixEyes) {
                    monster = new Mon_SixEyes(game,areaType);
                }

                // Place the monster in the game world
                if (monster != null) {
                    Game.gameInstance.monster[mapNum][indexNum] = monster;
                    Game.gameInstance.monster[mapNum][indexNum].worldX = Game.gameInstance.tileSize * col;
                    Game.gameInstance.monster[mapNum][indexNum].worldY = Game.gameInstance.tileSize * row;
                    System.out.println("Monster is not null");
                }
            }

            // Close resources
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateMonsterDestroyedStatus(int userId, int indexNum, int monsterType, int mapNum, boolean destroyed) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE game.Monsters SET destroyed = ? " +
                            "WHERE user_id = ? AND indexNum = ? AND monster_type = ? AND map_num = ?"
            );
            preparedStatement.setBoolean(1, destroyed);
            preparedStatement.setInt(2, userId);
            preparedStatement.setInt(3, indexNum);
            preparedStatement.setInt(4, monsterType);
            preparedStatement.setInt(5, mapNum);
            // Execute the update query
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Monster data updated successfully.");
            } else {
                System.out.println("No monster found with the given data.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void saveMonsterPositions(int userId, int mapNum, Entity[][] monsters) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE game.Monsters SET tile_col = ?, tile_row = ? WHERE user_id = ? AND map_num = ? AND indexNum = ?"
            );

            // Iterate over the monsters array and update their positions in the database
            for (int i = 0; i < monsters[mapNum].length; i++) {
                if (monsters[mapNum][i] != null) {
                    preparedStatement.setInt(1, monsters[mapNum][i].worldX / Game.gameInstance.tileSize);
                    preparedStatement.setInt(2, monsters[mapNum][i].worldY / Game.gameInstance.tileSize);
                    preparedStatement.setInt(3, userId);
                    preparedStatement.setInt(4, mapNum);
                    preparedStatement.setInt(5, i + 1); // Assuming indexNum starts from 1
                    preparedStatement.addBatch();
                }
            }
            // Execute batch update
            preparedStatement.executeBatch();
            System.out.println("Monster positions saved successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setDestroyedForSlimes() {
        String sql = "UPDATE game.Monsters SET destroyed = TRUE WHERE monster_type = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, Entity.type_slime);  // Assuming Entity.type_slime is a static variable

            int affectedRows = pstmt.executeUpdate();
            System.out.println("Updated " + affectedRows + " rows.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static boolean areAllMonstersDestroyed(int userId) {
        boolean allDestroyed = false;
        try {
            // Establish a connection to the database using configuration
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            // Create SQL query
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT COUNT(*) FROM game.Monsters WHERE user_id = ? AND destroyed = FALSE"
            );

            // Replace the placeholder with the user ID
            preparedStatement.setInt(1, userId);

            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                // If count is 0, all monsters are destroyed
                allDestroyed = (count == 0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allDestroyed;
    }


}

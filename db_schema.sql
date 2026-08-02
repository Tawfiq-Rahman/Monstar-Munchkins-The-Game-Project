-- Monster Munchkins Database Schema

-- 1. Create the Database
CREATE DATABASE game;
USE game;

-- 2. Create the Tables

-- USERS TABLE
CREATE TABLE `Users` (
    `user_id` INT AUTO_INCREMENT PRIMARY KEY,
    `username` VARCHAR(50) NOT NULL UNIQUE,
    `password` VARCHAR(100) NOT NULL,
    `coin` INT DEFAULT 0,
    `energy` INT DEFAULT 100,
    `maxEnergy` INT DEFAULT 100,
    `life` INT DEFAULT 3,
    `maxLife` INT DEFAULT 3,
    `exp` INT DEFAULT 0,
    `nextLevelExp` INT DEFAULT 100,
    `level` INT DEFAULT 1,
    `strength` INT DEFAULT 1,
    `dexterity` INT DEFAULT 1,
    `bullet` INT DEFAULT 0,
    `maxBullet` INT DEFAULT 0,
    `world_x` INT DEFAULT 0,
    `world_y` INT DEFAULT 0,
    `is_ship_started` BOOLEAN DEFAULT FALSE,
    `npc_fireball` BOOLEAN DEFAULT FALSE,
    `npc_global_chat` BOOLEAN DEFAULT FALSE,
    `npc_mother_slime` BOOLEAN DEFAULT FALSE,
    `npc_welcome` BOOLEAN DEFAULT FALSE,
    `npc_axe` BOOLEAN DEFAULT FALSE,
    `game_over` BOOLEAN DEFAULT FALSE
);

-- INVENTORY TABLE
CREATE TABLE `Inventory` (
    `user_id` INT,
    `item_code` INT,
    `item_count` INT DEFAULT 0,
    PRIMARY KEY (`user_id`, `item_code`),
    FOREIGN KEY (`user_id`) REFERENCES `Users`(`user_id`) ON DELETE CASCADE
);

-- MONSTERS TABLE
CREATE TABLE `Monsters` (
    `user_id` INT,
    `indexNum` INT,
    `monster_type` INT,
    `area_type` INT,
    `tile_col` INT,
    `tile_row` INT,
    `map_num` INT,
    `destroyed` BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (`user_id`, `indexNum`, `map_num`),
    FOREIGN KEY (`user_id`) REFERENCES `Users`(`user_id`) ON DELETE CASCADE
);

-- OBJECTS TABLE
CREATE TABLE `Objects` (
    `user_id` INT,
    `object_type` INT,
    `item_code` INT,
    `tile_col` INT,
    `tile_row` INT,
    `map_num` INT,
    `destroyed` BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (`user_id`, `object_type`, `item_code`, `tile_col`, `tile_row`, `map_num`),
    FOREIGN KEY (`user_id`) REFERENCES `Users`(`user_id`) ON DELETE CASCADE
); 
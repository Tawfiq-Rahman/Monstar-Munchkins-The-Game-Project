package com.example.return_3.object;

import com.example.return_3.entity.Entity;
import com.example.return_3.main.Game;

public class OBJ_ChatBox extends Entity {
    public OBJ_ChatBox(Game game) {
        super(game);
        name="chatBox";
        down1 = loadImage("/objects/chat1.png",game.tileSize,game.tileSize);
        up1 = loadImage("/objects/chat2.png",game.tileSize,game.tileSize);
        left1 = loadImage("/objects/chat3.png",game.tileSize,game.tileSize);
        right1 = loadImage("/objects/chat4.png",game.tileSize,game.tileSize);
    }
}

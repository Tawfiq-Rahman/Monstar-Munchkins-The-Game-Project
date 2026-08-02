package com.example.return_3.main;


import com.example.return_3.db.MyJDBC;
import com.example.return_3.db.User;
import com.example.return_3.globalChat.Client;
import com.example.return_3.npc.InteractNPC_FireBallGiver;
import com.example.return_3.npc.InteractNPC_HelplessWomen;
import com.example.return_3.npc.InteractNPC_Welcome;
import com.example.return_3.object.OBJ_Fireball;
import com.example.return_3.object.OBJ_Sword_Normal;
import com.example.return_3.shop.StuffShop;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class KeyHandler {
    Game game;
    Stage stage;
    private boolean moveUp, moveDown, moveLeft, moveRight,enterPressed, spacePressed, vKeyPressed, escapePressed,FKeyPressed,shiftPressed;

    public KeyHandler(Game game) {
        this.game = game;
        Game.gameScene.setOnKeyPressed(event -> {
            try {
                handleKeyPress(event.getCode());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        Game.gameScene.setOnKeyReleased(event -> handleKeyRelease(event.getCode()));
    }

    private void handleKeyPress(KeyCode code) throws Exception {

//        if (code == KeyCode.Q) {
//            try {
//                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                alert.setTitle("Exit");
//                alert.setHeaderText("You are about to exit");
//                alert.setContentText("Do you want to exit?");
//                if (alert.showAndWait().get() == ButtonType.OK) {
//                    Game.exitGame();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }



        //This is for PlayState
        if(game.gameState == game.playState){
            game.ui.uiMainGame.commandNum=0;
            playState(code);
            if(code==KeyCode.L){
                game.playSoundEffect(game.soundEffect.popUp);
                new StuffShop(game).use();
            }if(code==KeyCode.G){
                game.playSoundEffect(game.soundEffect.popUp);
                game.gameState=game.globalChatState;
                if(game.isStartClient==false){
                    game.startGlobalChat();
                    game.isStartClient=true;
                }
            }
            if(code==KeyCode.M){
                game.gameState=game.gameOverState;
            }if(code==KeyCode.K){
                System.out.println("Pressing K in KeyHandler");

            }
        }
        
        //DIALOGUE state
        else if(game.gameState == game.dialogueState) {
            dialogueState(code);
        }//MESSAGE state
        else if(game.gameState == game.messageState) {
            messageState(code);
        }
        //WizConversation state
        else if(game.gameState == game.wizConversationState){
            wizConversationState(code);
        }
        //characterState state
        else if (game.gameState == game.characterState) {
//            game.ui.uiMainGame.playerSlotCol=0;
//            game.ui.uiMainGame.playerSlotRow=0;
            characterState(code);
        }
        //TRADE state
        else if (game.gameState == game.tradeState) {
            tradeState(code);
        }
        //Teleport state
        else if (game.gameState == game.shipTeleportState) {
            shipTeleportState(code);
        }//Fisheris state
        else if (game.gameState == game.fisheriesState) {
            fisheriesState(code);
        }
        //GLOBAL Chat State
        else if (game.gameState == game.globalChatState) {
            globalChatState(code);
        }
        //MENU BAR state
        else if (game.gameState == game.menuBarState) {
                menuBarState(code);
            }

        // <------------Pause State---------->
        else if (game.gameState == game.pauseState) {
                pauseState(code);
        }

        // <------------Pause State---------->
        else if (game.gameState == game.gameOverState) {
                gameOverState(code);
        }

        // <------------Level up State---------->
        else if (game.gameState == game.levelUpState) {
                levelUpState(code);
        }


        else if (game.gameState == game.hospitalState) {
            hospitalState(code);
        }


        else if (game.gameState == game.mapState) {
            mapState(code);
        }

// Handle settingState
        else if (game.gameState == game.settingsState) {
            settingsState(code);
        }
        //Handle
        else if (game.gameState == game.guidelineState) {
            guidelineState(code);
        }
    }

    private void guidelineState(KeyCode code) {
        if(code== KeyCode.ESCAPE){
            if(game.ui.uiMainGame.commandNum > 0){
                game.ui.uiMainGame.commandNum--;
            }
        }if(code== KeyCode.ENTER){
            game.ui.uiMainGame.commandNum++;
            if(game.ui.uiMainGame.commandNum >= game.guidelineScene){
                game.gameState = game.playState;
                game.ui.uiMainGame.commandNum=0;
            }
        }
    }

    private void settingsState(KeyCode code) {
        if(code== KeyCode.W || code== KeyCode.UP){
            game.ui.uiMainGame.commandNum--;
            game.playSoundEffect(9);
            if(game.ui.uiMainGame.commandNum < 0){
                game.ui.uiMainGame.commandNum = 2;
            }
        }
        if(code== KeyCode.S || code== KeyCode.DOWN){
            game.ui.uiMainGame.commandNum++;
            game.playSoundEffect(9);
            if(game.ui.uiMainGame.commandNum > 2){
                game.ui.uiMainGame.commandNum=0;
            }
        }
        if (code == KeyCode.ENTER) {
            if (game.ui.uiMainGame.commandNum == 0) {

            }
            if (game.ui.uiMainGame.commandNum == 1) {

            }
            if (game.ui.uiMainGame.commandNum == 2) {
                game.gameState = game.menuBarState;
            }
        }
        if (code == KeyCode.A || code == KeyCode.LEFT) {
            if (game.ui.uiMainGame.commandNum == 0 && game.music.volumeScale > 0) {
                game.music.volumeScale--;
                game.music.checkVolume();
                game.playSoundEffect(9);
            }
            if (game.ui.uiMainGame.commandNum == 1 && game.soundEffect.volumeScale > 0) {
                game.soundEffect.volumeScale--;
                game.playSoundEffect(9);
            }
        }
        if (code == KeyCode.D || code == KeyCode.RIGHT) {
            if (game.ui.uiMainGame.commandNum == 0 && game.music.volumeScale < 5) {
                game.music.volumeScale++;
                game.music.checkVolume();
                game.playSoundEffect(9);
            }
            if (game.ui.uiMainGame.commandNum == 1 && game.soundEffect.volumeScale < 5) {
                game.soundEffect.volumeScale++;
                game.playSoundEffect(9);
            }
        }
    }

    private void hospitalState(KeyCode code) {
        if(code== KeyCode.W || code== KeyCode.UP){
            game.ui.uiMainGame.commandNum--;
            game.playSoundEffect(9);
            if(game.ui.uiMainGame.commandNum < 0){
                game.ui.uiMainGame.commandNum = 2;
            }
        }
        if(code== KeyCode.S || code== KeyCode.DOWN){
            game.ui.uiMainGame.commandNum++;
            game.playSoundEffect(9);
            if(game.ui.uiMainGame.commandNum > 2){
                game.ui.uiMainGame.commandNum=0;
            }
        }
        if (code == KeyCode.ENTER) {
            if (game.ui.uiMainGame.commandNum == 0) {
                int calCoin = (game.player.maxLife - game.player.life) * 2;
                if (game.player.coin >= calCoin && game.player.life < game.player.maxLife) {
                    game.player.life = game.player.maxLife;
                    game.player.coin -= calCoin;
                    game.gameState = game.playState;
                    game.ui.uiMainGame.addMessage("Healing SuccessFull!");
                }
                else {
                    System.out.println("Don't have coin ");

                    if (game.player.coin < calCoin) {
                        game.gameState = game.playState;
                        game.ui.uiMainGame.addMessage("You don't have much coin!");
                    }
                    else if (game.player.life >= game.player.maxLife) {
                        game.gameState = game.playState;
                        game.ui.uiMainGame.addMessage("You already have max life!");
                    }

                }

            }
            if (game.ui.uiMainGame.commandNum == 1) {
                if (game.player.level >= 10) {
                    if (game.player.coin >= 300) {
                        game.player.maxLife += 10;
                        game.player.coin -= 300;
                        game.gameState = game.playState;
                        game.ui.uiMainGame.addMessage("Increasing Max life successful!");
                    }
                    else {
                        //System.out.println("Don't have coin ");
                        game.gameState = game.playState;
                        game.ui.uiMainGame.addMessage("You don't have much coin!");
                    }
                }
                else {
                    game.ui.uiMainGame.currentDialogue = "You need level 10 to Increase Max life...";
                    game.playSoundEffect(game.soundEffect.popUp);
                    game.gameState = game.messageState;
                }

            }


            if (game.ui.uiMainGame.commandNum == 2) {
                game.gameState = game.playState;
            }
        }
    }

    private void characterState(KeyCode code) {
        if (code == KeyCode.C) {
            //game.playSoundEffect(game.soundEffect.inventoryClose);
            game.gameState = game.playState;
        }
        if(code==KeyCode.ENTER){
            game.player.selectItem();
        }
        playerInventory(code);
    }
    public void playerInventory(KeyCode code){
        if(code== KeyCode.W || code== KeyCode.UP){ //VK_W means if user press W then
            if(game.ui.uiMainGame.playerSlotRow!=0){
                game.ui.uiMainGame.playerSlotRow--;
                game.playSoundEffect(9);
            }
        }
        if(code== KeyCode.S || code== KeyCode.DOWN){ //VK_S means if user press S then
            if(game.ui.uiMainGame.playerSlotRow!=3) {
                game.ui.uiMainGame.playerSlotRow++;
                game.playSoundEffect(9);
            }
        }
        if(code==KeyCode.A || code== KeyCode.LEFT){ //VK_A means if user press A then
            if(game.ui.uiMainGame.playerSlotCol!=0) {
                game.ui.uiMainGame.playerSlotCol--;
                game.playSoundEffect(9);
            }
        }
        if(code==KeyCode.D || code== KeyCode.RIGHT){ //VK_D means if user press D then
            if(game.ui.uiMainGame.playerSlotCol!=4) {
                game.ui.uiMainGame.playerSlotCol++;
                game.playSoundEffect(9);
            }
        }
    }
    public void npcInventory(KeyCode code){
        if(code== KeyCode.W || code== KeyCode.UP){ //VK_W means if user press W then
            if(game.ui.uiMainGame.shopSlotRow!=0){
                game.ui.uiMainGame.shopSlotRow--;
                game.playSoundEffect(9);
            }
        }
        if(code== KeyCode.S || code== KeyCode.DOWN){ //VK_S means if user press S then
            if(game.ui.uiMainGame.shopSlotRow!=3) {
                game.ui.uiMainGame.shopSlotRow++;
                game.playSoundEffect(9);
            }
        }
        if(code==KeyCode.A || code== KeyCode.LEFT){ //VK_A means if user press A then
            if(game.ui.uiMainGame.shopSlotCol!=0) {
                game.ui.uiMainGame.shopSlotCol--;
                game.playSoundEffect(9);
            }
        }
        if(code==KeyCode.D || code== KeyCode.RIGHT){ //VK_D means if user press D then
            if(game.ui.uiMainGame.shopSlotCol!=4) {
                game.ui.uiMainGame.shopSlotCol++;
                game.playSoundEffect(9);
            }
        }
    };

    private void menuBarState(KeyCode code) {
        if (code == KeyCode.ESCAPE) {
            escapePressed = true;
            game.gameState=game.playState;
        }
        if(code== KeyCode.W || code== KeyCode.UP){
            game.ui.uiMainGame.commandNum--;
            game.playSoundEffect(9);
            if(game.ui.uiMainGame.commandNum < 0){
                game.ui.uiMainGame.commandNum = 2;
            }
        }
        if(code== KeyCode.S || code== KeyCode.DOWN){
            game.ui.uiMainGame.commandNum++;
            game.playSoundEffect(9);
            if(game.ui.uiMainGame.commandNum > 2){
                game.ui.uiMainGame.commandNum = 0;
            }
        }
        if (code == KeyCode.ENTER) {
            if (game.ui.uiMainGame.commandNum == 0) {
                game.playSoundEffect(game.soundEffect.popUp);
                game.gameState = game.settingsState;
            }
            if (game.ui.uiMainGame.commandNum == 1) {
                MyJDBC.updateUser(game.player);
//                for ( Entity item: game.player.inventory) {
//                    int itemCode=item.itemCode;
//                    int count=item.itemCount;
//                    System.out.println("after updated:");
//                    System.out.println("itemName: "+item.name+"|| itemCount: "+count);
//                }
                game.stopMusic();
                Game.exitGame();
            }
            if (game.ui.uiMainGame.commandNum == 2) {
                MyJDBC.updateUser(game.player);
//                for ( Entity item: game.player.inventory) {
//                    int itemCode = item.itemCode;
//                    int count = item.itemCount;
//                    System.out.println("after updated:");
//                    System.out.println("itemName: " + item.name + "|| itemCount: " + count);
//                }
                game.stopMusic();
                game.logout();
            }
        }
    }

    private void pauseState(KeyCode code) {
        if (code == KeyCode.ENTER) {
            game.gameState = game.playState;
        }
    }
    private void gameOverState(KeyCode code) throws Exception {
        if (code == KeyCode.ENTER) {
//            Game.exitGame();
            String username=game.user.getUsername();
            String password=game.user.getPassword();
            MyJDBC.deleteUserById(game.player.playerId);
            int id=MyJDBC.register(username, password);
            game.user=null;
            game.user =MyJDBC.getUserData(id);
            game.gameState=game.playState;
            game.gameOver=false;
            try {
                // Write user ID to file to indicate login
                BufferedWriter writer = new BufferedWriter(new FileWriter("loginStatus.txt"));
                writer.write(String.valueOf(Game.gameInstance.user.getUserId()));
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            game.startGame();
        }
        if (code == KeyCode.ESCAPE) {
            Game.exitGame();
        }
    }

    private void levelUpState(KeyCode code) {
        if (code == KeyCode.ENTER) {
                game.gameState = game.playState;
        }
    }


    public void playState(KeyCode code){

        switch (code) {
            case UP: moveUp = true; break;
            case DOWN: moveDown = true; break;
            case LEFT: moveLeft = true; break;
            case RIGHT: moveRight = true; break;
            case W: moveUp = true; break;
            case S: moveDown = true; break;
            case A: moveLeft = true; break;
            case D: moveRight = true; break;
            case F: FKeyPressed=true;break;
            case ENTER: enterPressed = true; break;
            case SPACE: spacePressed = true; break;
            case V: vKeyPressed = false; break;
            case C: game.playSoundEffect(game.soundEffect.popUp);
                    game.gameState = game.characterState; break;
            case ESCAPE: game.playSoundEffect(game.soundEffect.popUp);
                         game.gameState = game.menuBarState; break;

        }
    }


    public void dialogueState(KeyCode code){
        if(code== KeyCode.ENTER ){ //VK_ENTER means if user press ENTER then
            game.ui.uiMainGame.npc.dialogueIndex++;
            if(game.ui.uiMainGame.npc.dialogue[game.ui.uiMainGame.npc.dialogueIndex]==null){
//                game.isDialogueToGameState=true;
                game.gameState=game.playState;
                game.ui.uiMainGame.npc.dialogueIndex=0;
//                if(game.ui.uiMainGame.npc instanceof InteractNPC_Welcome ||game.ui.uiMainGame.npc instanceof InteractNPC_HelplessWomen ||game.ui.uiMainGame.npc instanceof InteractNPC_FireBallGiver){
                    game.ui.uiMainGame.npc.onPath=true;
                    game.ui.uiMainGame.npc.npcGoneCommand=true;
                    if(game.ui.uiMainGame.npc instanceof InteractNPC_Welcome){
                        game.ui.uiMainGame.npc.dropItem(new OBJ_Sword_Normal(game));
                    }else if(game.ui.uiMainGame.npc instanceof InteractNPC_FireBallGiver){
                        game.ui.uiMainGame.npc.dropItem(new OBJ_Fireball(game));
                    }
//                }
            }else{
                game.ui.uiMainGame.npc.speak();
            }
        }else if(code==KeyCode.ESCAPE){
            if(game.ui.uiMainGame.npc.dialogueIndex>0){
                game.ui.uiMainGame.npc.dialogueIndex--;
                game.ui.uiMainGame.npc.speak();
            }
        }
    }

    public void messageState(KeyCode code){
        if(code== KeyCode.ENTER ){ //VK_ENTER means if user press ENTER then
//            game.ui.uiMainGame.currentDialogue=dialogue[dialogueIndex];
            game.gameState=game.playState;
        }
    }

    public void wizConversationState(KeyCode code){
        if(code== KeyCode.ENTER ){
            enterPressed=true;
        }
        if(code== KeyCode.W || code== KeyCode.UP){
            game.playSoundEffect(9);
            game.ui.uiMainGame.commandNum--;
            if(game.ui.uiMainGame.commandNum<0){
                game.ui.uiMainGame.commandNum=3;
            }
        }
        if(code== KeyCode.S || code== KeyCode.DOWN){
            game.playSoundEffect(9);
            game.ui.uiMainGame.commandNum++;
            if(game.ui.uiMainGame.commandNum>3){
                game.ui.uiMainGame.commandNum=0;
            }
        }
    }

    public void tradeState(KeyCode code){
        if(code== KeyCode.ENTER ){
            enterPressed=true;
        }
        if(game.ui.uiMainGame.subState==0){

            if(code== KeyCode.W || code== KeyCode.UP){
                game.playSoundEffect(9);
                game.ui.uiMainGame.commandNum--;
                if(game.ui.uiMainGame.commandNum<0){
                    game.ui.uiMainGame.commandNum=2;
                }
            }
            if(code== KeyCode.S || code== KeyCode.DOWN){
                game.playSoundEffect(9);
                game.ui.uiMainGame.commandNum++;
                if(game.ui.uiMainGame.commandNum>2){
                    game.ui.uiMainGame.commandNum=0;
                }
            }
        }
        if(game.ui.uiMainGame.subState==1){
            npcInventory(code);
            if(code==KeyCode.ESCAPE){
                //game.playSoundEffect(game.soundEffect.inventoryClose);
                game.ui.uiMainGame.subState=0;
            }
        }if(game.ui.uiMainGame.subState==2){
            playerInventory(code);
            if(code==KeyCode.ESCAPE){
                //game.playSoundEffect(game.soundEffect.inventoryClose);
                game.ui.uiMainGame.subState=0;
            }
        }

    }

    public void shipTeleportState(KeyCode code) {
        if(code== KeyCode.ENTER ){
            enterPressed=true;
        }
        //we will write code lated
        if(code== KeyCode.W || code== KeyCode.UP){
            game.playSoundEffect(9);
            game.ui.uiMainGame.commandNum--;
            if(game.ui.uiMainGame.commandNum<0){
                game.ui.uiMainGame.commandNum=1;

            }
        }
        if(code== KeyCode.S || code== KeyCode.DOWN){
            game.playSoundEffect(9);
            game.ui.uiMainGame.commandNum++;
            if(game.ui.uiMainGame.commandNum>1){
                game.ui.uiMainGame.commandNum=0;
            }
        }
    }

    //    public void globalChatState(KeyCode code){
//        game.ui.uiMainGame.msgText="";
//        if(code== KeyCode.ENTER ){
//            enterPressed=true;
//        }
//        if(code == KeyCode.ESCAPE){
//            game.gameState=game.playState;
//        }
//
//        //now we are taking text from keyboard
//
//    }


    public void globalChatState(KeyCode code) {
        // Handle the Escape key to exit the global chat state
        if (code == KeyCode.ESCAPE) {
            game.gameState = game.playState;
            return;
        }
        if (code == KeyCode.SHIFT) {
            shiftPressed=true;
            return;
        }

        // Handle the Enter key to send the message
        if (code == KeyCode.ENTER) {
            // Process the message in msgText
            String message = game.ui.uiMainGame.msgText.trim();
            if (!message.isEmpty()) {
                game.playSoundEffect(game.soundEffect.messageSend);
                // Add logic to send the message to the chat system
                sendMessageToGlobalChat(message);

                // Clear the input field after sending the message
                game.ui.uiMainGame.msgText = "";
            }
            return;
        }

        // Handle Backspace key to delete the last character
        if (code == KeyCode.BACK_SPACE) {
            if (!game.ui.uiMainGame.msgText.isEmpty()) {
                game.playSoundEffect(game.soundEffect.chatType);
                game.ui.uiMainGame.msgText = game.ui.uiMainGame.msgText.substring(0, game.ui.uiMainGame.msgText.length() - 1);
            }
            return;
        }
        // Handle space key to append a space character
        if (code == KeyCode.SPACE) {
            game.playSoundEffect(game.soundEffect.chatType);
            game.ui.uiMainGame.msgText += " ";
            return;
        }

        // Append typed character to msgText
        String keyText = code.getName();
        System.out.println("keyText: " + keyText);
        if(game.ui.uiMainGame.msgText.length()<88){

            if (keyText.length() == 1) { // Ensure it's a single character
                game.playSoundEffect(game.soundEffect.chatType);
                char c = keyText.charAt(0);

                if (Character.isLetter(c) ) {
                    if(shiftPressed){
                        game.ui.uiMainGame.msgText += c;
                    }else{
                        c= Character.toLowerCase(c);
                        game.ui.uiMainGame.msgText += c;
                    }
                    System.out.println("letter");
                }else if(Character.isDigit(c)){
                    System.out.println("you ate pressed " + c);
                    System.out.println("digit");
                    if(shiftPressed){
                        //i want to check here
                        switch (c) {
                            case '1': c = '!';break;
                            case '2': c = '@';break;
                            case '3': c = '#';break;
                            case '4': c = '$';break;
                            case '5': c = '%';break;
                            case '6': c = '^';break;
                            case '7': c = '&';break;
                            case '8': c = '*';break;
                            case '9': c = '(';break;
                            case '0': c = ')';break;
                            default: break;
                        }
                    }
                    game.ui.uiMainGame.msgText += c;
                }
            }else {

                char c = 0;
                switch (keyText) {
                    case "Period": c = shiftPressed ? '<' : '.';break;
                    case "Comma": c = shiftPressed ? '>' : ',';break;
                    case "Slash": c = shiftPressed ? '?' : '/';break;
                    case "Semicolon": c = shiftPressed ? ':' : ';';break;
                    case "Quote": c = shiftPressed ? '"' : '\'';break;
                    case "Back Slash": c = shiftPressed ? '|' : '\\';break;
                    case "Open Bracket": c = shiftPressed ? '{' : '[';break;
                    case "Close Bracket": c = shiftPressed ? '}' : ']';break;
                    case "Minus": c = shiftPressed ? '_' : '-';break;
                    case "Equals":c = shiftPressed ? '+' : '=';break;
                    case "Grave": c = shiftPressed ? '~' : '`';break;
                    default:
                        break;
                }
                if (c != 0) {
                    game.playSoundEffect(game.soundEffect.chatType);
                    game.ui.uiMainGame.msgText += c;
                }
            }
        }else{
            System.out.println("Limit exceeded!");
            System.out.println(game.ui.uiMainGame.msgText.length());
        }

    }
    // Helper method to send a message to the global chat
    private void sendMessageToGlobalChat(String message) {
        Client.clientWriterThread.sendMessage(message);
    }

    // Helper method to check for special characters
    // Helper method to check for allowed punctuation characters
    private boolean isAllowedPunctuation(char c) {
        // Add more special characters as needed
        return ".,!?;:()[]{}<>-_+=/\\\"'@#$%^&*".indexOf(c) >= 0;
    }

    public void fisheriesState(KeyCode code) {
        if(code== KeyCode.ENTER ){
            enterPressed=true;
        }
        //we will write code lated
        if(code== KeyCode.W || code== KeyCode.UP){
            game.playSoundEffect(9);
            game.ui.uiMainGame.commandNum--;
            if(game.ui.uiMainGame.commandNum<0){
                game.ui.uiMainGame.commandNum=1;

            }
        }
        if(code== KeyCode.S || code== KeyCode.DOWN){
            game.playSoundEffect(9);
            game.ui.uiMainGame.commandNum++;
            if(game.ui.uiMainGame.commandNum>1){
                game.ui.uiMainGame.commandNum=0;
            }
        }
    }

    public void mapState(KeyCode code) {
        if(code==KeyCode.M){
            game.gameState=game.playState;
        }
    }

    private void handleKeyRelease(KeyCode code) {
        switch (code) {
            case UP: moveUp = false; break;
            case DOWN: moveDown = false; break;
            case LEFT: moveLeft = false; break;
            case RIGHT: moveRight = false; break;
            case W: moveUp = false; break;
            case S: moveDown = false; break;
            case A: moveLeft = false; break;
            case D: moveRight = false; break;
            case F: FKeyPressed=false;break;
            case ENTER: enterPressed = false; break;
            case SPACE:spacePressed = false; break;
            case V: vKeyPressed = false; break;
            case ESCAPE:escapePressed = false; break;
            case SHIFT:shiftPressed = false; break;

        }

    }

    // Getters for the movement flags
    public boolean isMoveUp() {
        return moveUp;
    }

    public boolean isMoveDown() {

        return moveDown;
    }

    public boolean isMoveLeft() {

        return moveLeft;
    }

    public boolean isMoveRight() {
        return moveRight;
    }
    public boolean isEnterPressed(){
        return enterPressed;
    }
    public boolean isSpacePressed() {
        return spacePressed;
    }
    public boolean isVKeyPressed() {
        return vKeyPressed;
    }
    public boolean isEscapePressed() {
        return escapePressed;
    }
    public  boolean isFKeyPressed(){
        return FKeyPressed;
    }

    public void setEnterPressed(boolean enterPressed) {
        this.enterPressed = enterPressed;
    }
    public void setSpacePressed(boolean spacePressed) {
        this.spacePressed = spacePressed;
    }
    public void setVKeyPressed(boolean vKeyPressed) {
        this.vKeyPressed = vKeyPressed;
    }
    public void setEscapePressed(boolean escapePressed) {
        this.escapePressed = escapePressed;
    }


    public void setBooleanAll(boolean value){
        this.moveUp = value;
        this.moveDown = value;
        this.moveLeft = value;
        this.moveRight = value;
        this.enterPressed = value;
        this.spacePressed = value;
        this.escapePressed = value;
        this.vKeyPressed = value;
    }

}

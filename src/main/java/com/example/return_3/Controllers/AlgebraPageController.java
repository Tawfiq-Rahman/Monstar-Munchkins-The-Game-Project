package com.example.return_3.Controllers;

import com.example.return_3.main.Game;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;

public class AlgebraPageController {
    public Button exitBtn2;
    public Button nextBtn2;
    public Button backBtn1;
    public Button exitBtn3;
    public Button nextBtn3;
    public Button backBtn2;
    public Button exitBtn4;
    public Button nextBtn4;
    public Button backBtn3;
    public Button exitBtn5;
    public Button nextBtn5;
    public Button backBtn4;
    public Button exitBtn6;
    public Button nextBtn6;
    public Button backBtn5;
    public Button exitBtn7;
    public Button backBtn6;
    public Button testBtn;
    Game game;

    public Button exitBtn1;
    public Button nextBtn1;

    public void exitToMath1(ActionEvent event) throws Exception {
        Game.gameInstance.showMathScene();
    }

    public void next1(ActionEvent event) throws Exception {
        Game.gameInstance.showAlgebraPage2();

    }

    public void exitToMath2(ActionEvent event) throws Exception {
        Game.gameInstance.showMathScene();
    }

    public void next2(ActionEvent event) throws Exception {
        Game.gameInstance.showAlgebraPage3();
    }

    public void back1(ActionEvent event) throws Exception {
        Game.gameInstance.showAlgebraPage1();
    }

    public void exitToMath3(ActionEvent event) throws Exception {
        Game.gameInstance.showMathScene();
    }

    public void next3(ActionEvent event) throws Exception {
        Game.gameInstance.showAlgebraPage4();
    }

    public void back2(ActionEvent event) throws Exception {
        Game.gameInstance.showAlgebraPage2();
    }

    public void exitToMath4(ActionEvent event) throws Exception {
        Game.gameInstance.showMathScene();
    }

    public void next4(ActionEvent event) throws IOException {
        Game.gameInstance.showAlgebraPage5();
    }

    public void back3(ActionEvent event) throws IOException {
        Game.gameInstance.showAlgebraPage3();
    }

    public void exitToMath5(ActionEvent event) throws IOException {
        Game.gameInstance.showMathScene();
    }

    public void next5(ActionEvent event) throws IOException {
        Game.gameInstance.showAlgebraPage6();
    }

    public void back4(ActionEvent event) throws IOException {
        Game.gameInstance.showAlgebraPage4();
    }

    public void exitToMath6(ActionEvent event) throws IOException {
        Game.gameInstance.showMathScene();
    }

    public void next6(ActionEvent event) throws IOException {
        Game.gameInstance.showAlgebraPage7();
    }

    public void back5(ActionEvent event) throws IOException {
        Game.gameInstance.showAlgebraPage5();
    }

    public void exitToMath7(ActionEvent event) throws IOException {
        Game.gameInstance.showMathScene();
    }

    public void back6(ActionEvent event) throws IOException {
        Game.gameInstance.showAlgebraPage6();
    }

    public void goToTest(ActionEvent event) throws IOException {
        Game.gameInstance.showAlgebraTestPage1();
    }
}

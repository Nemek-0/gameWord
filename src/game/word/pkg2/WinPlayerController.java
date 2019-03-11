/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.word.pkg2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ПК-Тащера
 */
public class WinPlayerController implements Initializable {
    private Stage stage;
    private boolean action = false;
    @FXML
    private void button_yes() {
        action = true;
        stage.close();
    }
    
    @FXML
    private void button_no() {
        action = false;
        stage.close();
    }
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    public boolean setActoin() {
        return this.action;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

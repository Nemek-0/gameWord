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
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ПК-Тащера
 */
public class NewGameController implements Initializable {
    @FXML
    private ChoiceBox<String> choiceBox;
    private String text;
    private Stage stage;
    
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    
    @FXML
    public void action() {
       text =  this.choiceBox.getValue();
       stage.close();
        
    }
    public String getValue() {
        
        return this.choiceBox.getValue();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        choiceBox.getItems().add("Города России");
        choiceBox.getItems().add("Животные");
        choiceBox.getItems().add("Растения");
        choiceBox.getItems().add("Имена");
        
    }    
    
}

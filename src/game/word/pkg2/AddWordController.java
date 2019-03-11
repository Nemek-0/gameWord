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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ПК-Тащера
 */
public class AddWordController implements Initializable {

    
    
    @FXML
    private Button button_add_word_ok;
    @FXML
    private Button button_add_word_cancel;
    @FXML
    public TextArea textarea_add_word;
    
    
    private String text_word;
    private String text_tema;
    private Stage stage;
    private boolean response = false;
    
    
    @FXML
    private void button_ok() {
        response = true;
        stage.close();
    }
    @FXML
    private void button_cancel() {
        response = false;
        stage.close();
    }
    
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    public void setTextWord(String str) {
        this.text_word = str;
    }
    
    public void setTextTema(String str) {
        this.text_tema = str;
        setTaxtArea();
    }
    
    private void setTaxtArea() {
        textarea_add_word.setText("Я не знаю такого слова '" + this.text_word + "' в теме'" + this.text_tema + "'\n"
        + "Если вы уверены что есть такое слово то нажмите 'Ок', если нет то нажмите 'Отмена' ");
    }
    
    public boolean getResponse() {
        return this.response;
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}

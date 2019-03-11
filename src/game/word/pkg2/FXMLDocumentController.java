/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.word.pkg2;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author ПК-Тащера
 */
public class FXMLDocumentController implements Initializable {
    //-----------------------------------------------------------
    @FXML private TextArea textarea;
    @FXML private TextField textfield;
    @FXML private Label label;
    ArrayList<String> listGame = new ArrayList();
    ArrayList<Word> listGameWord = new ArrayList();
    String nameFile;
    ListWord listWord = new ListWord();
    private String nameTheme;
    private Stage stageMain;
    
    
    @FXML
    private void buttonaction() throws IOException {
        String player = player_move();
        if(player.equals(""))
            return;
        listGame.add("Вы: " + player);
        listGameWord.add(new Word(player));
        String computer = computer_move();
        if (computer.equals(""))
            playerWin();
        else
        {
            listGame.add("Компьютер: " + computer);
            listGameWord.add(new Word(computer));
        }
        textarea.setText(printListGame());
        label.setText("");
        textfield.setText("");
        
    }
    
    private String player_move() throws IOException { 
        if(listGameWord.isEmpty())
        {
            if(listWord.lookForWord(textfield.getText().trim()))
                return textfield.getText().trim();
            else
                return add_word();
        }
            else
            {
                if(textfield.getText().trim().substring(0, 1).toLowerCase().equals
                    ((listGameWord.get(listGameWord.size() -1).getLastLetter())))
                    if (there_is_listGame(textfield.getText().trim()))
                    {
                        label.setText("Слово '" + textfield.getText().trim() + "' уже было использавано");
                        return ""; 
                    }
                    else
                       if(!listWord.lookForWord(textfield.getText().trim()))
                           return add_word();
                       else
                           return textfield.getText().trim();
                else
                {
                    label.setText("Слово должно начинаться на букву '" + 
                        listGameWord.get(listGameWord.size() -1).getLastLetter() + "'");
                    return ""; 
                }
            }       
    }
    
    private String computer_move() {
        String lastLetter = listGameWord.get(listGameWord.size() - 1).getLastLetter();
        return look_for_word(look_for_list_word(lastLetter));
        
    }
    
    private String lastSymbol(String str) {
         String letter = str.substring(str.length() - 1);
         if (letter.equals("ь") || letter.equals("ъ") || letter.equals("ы") || letter.equals("й") || letter.equals("ё"))
             return lastSymbol(str.substring(0, str.length() - 1));
         return letter.toLowerCase();
    } 
    
    private ArrayList<Word> look_for_list_word(String letter) {
        
       ArrayList<Word> list_word = new ArrayList();
       listWord.setListWord().stream().filter((u) -> (letter.equals(u.getFirstLetter()) && !there_is_listGame(u.getWord()))).forEach((u) -> {
           list_word.add(u);
        });
       return list_word;
   }
    
    private String printListGame() {
        String str = "";
        str = listGame.stream().map((u) -> u + "\n").reduce(str, String::concat);
        return str;
    }
    
   private String look_for_word(ArrayList<Word> list_word) {
       Map <Integer, String> map = new Hashtable<Integer, String>();
       list_word.forEach((u) -> {
           map.put(listWord.amount_word(lastSymbol(u.getWord())),u.getWord());
        });
       if (map.isEmpty())
           return "";
       return map.get(map.keySet().stream().sorted().findFirst().get());
       
       
      
   }
   
   private boolean there_is_listGame(String str) {
    return listGameWord.stream().anyMatch((u) -> (u.getWord().equals(str.toLowerCase())));
}
   //-----------------------------------------------------------
   //-----------------------------------------------------------
   //-----------------------------------------------------------
    
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (listWord.setListWord().isEmpty())
            try {
                new_game();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    @FXML
    private void new_game() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("NewGame.fxml"));
        Parent root = loader.load();
        
        NewGameController controller = loader.getController(); // аааа горит из-за этой строчки
        
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        controller.setStage(stage);
        stage.showAndWait();
        System.out.println(controller.getValue());
        listGame.clear();
        listGameWord.clear();
        name_thema(controller.getValue());
 }
    
    @FXML
    private String add_word() throws IOException {
       
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AddWord.fxml"));
        Parent root = loader.load();
        
        AddWordController controller = loader.getController(); // аааа горит из-за этой строчки
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        
        controller.setStage(stage);
        controller.setTextWord(textfield.getText().trim());
        controller.setTextTema(nameTheme);
        
        stage.showAndWait();
        
        if(controller.getResponse())
            return textfield.getText().trim();
        return "";
    }
    
    private void name_thema(String thema) {
        nameTheme = thema;
        switch(thema){
            case "Города России":
                nameFile = "public\\City.txt";
                break;
            case "Животные":
                nameFile = "public\\Animals.txt";
                break;
            case "Растения":
                nameFile = "public\\Plants.txt";
                break;
            case "Имена":
                nameFile = "public\\Name.txt";
                break;
        }
        listWord = new ListWord(nameFile);
            
        }

    private void playerWin() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("WinPlayer.fxml"));
        Parent root = loader.load();
        
        WinPlayerController controller = loader.getController(); // аааа горит из-за этой строчки
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        
        controller.setStage(stage);
        
        stage.showAndWait();
        
        if (controller.setActoin())
            new_game();
        else
            this.stageMain.close();
    
            
    }
    @FXML
    private void rule() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("rule.fxml"));
        Parent root = loader.load();
        
        RuleController controller = loader.getController(); // аааа горит из-за этой строчки
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        
        controller.setStage(stage);
        
        stage.showAndWait();
    }
    
    public void SetStageMain (Stage stage){
        this.stageMain = stage;
    }
    
   

    
}

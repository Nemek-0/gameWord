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
    @FXML
    private TextArea textarea;
    @FXML
    private TextField textfield;
    @FXML
    private Label label;
    ArrayList<String> listGame = new ArrayList();
    ArrayList<String> listGame_word = new ArrayList();
    String name_file;
    ListWord listWord = new ListWord();
    private String name_tema;
    private Stage stageMain;
    
    
    @FXML
    private void buttonaction() throws IOException {
            String player = player_move();
            if(player.equals(""))
                return;
            listGame.add("Вы: " + player);
            listGame_word.add(player);
            String computer = computer_move();
            if (computer.equals(""))
                player_win();
            else
            {
                listGame.add("Компьютер: " + computer);
                listGame_word.add(computer);
            }
        textarea.setText(print_listGame());
        label.setText("");
        textfield.setText("");
        
    }
    
    private String player_move() throws IOException { 
            if(listGame_word.isEmpty())
            {
               if(listWord.look_for_word(textfield.getText().trim()))
                    return textfield.getText().trim();
                else
                    return add_word();
            }
            else
            {
                if(textfield.getText().trim().substring(0, 1).toLowerCase().equals
                    (last_letter(listGame_word.get(listGame_word.size() -1))))
                    if (there_is_listGame(textfield.getText().trim()))
                    {
                        label.setText("Слово '" + textfield.getText().trim() + "' уже было использавано");
                        return ""; 
                    }
                    else
                       if(!listWord.look_for_word(textfield.getText().trim()))
                           return add_word();
                       else
                           return textfield.getText().trim();
                else
                {
                    label.setText("Слово должно начинаться на букву '" + 
                        last_letter(listGame_word.get(listGame_word.size() -1)) + "'");
                    return ""; 
                }
            }       
    }
    
    private String computer_move() {
        String prev_word = listGame_word.get(listGame_word.size() - 1);
        String letter = last_letter(prev_word);
        return look_for_word(look_for_list_word(letter));
        
    }
    
    private String last_letter(String str) {
         String letter = str.substring(str.length() - 1);
         if (letter.equals("ь") || letter.equals("ъ") || letter.equals("ы") || letter.equals("й") || letter.equals("ё"))
             return last_letter(str.substring(0, str.length() - 1));
         return letter.toLowerCase();
    } 
    
    private ArrayList<String> look_for_list_word(String letter) {
        
       ArrayList list_word = new ArrayList();
       listWord.setListWord().stream().filter((u) -> (letter.equals(u.substring(0, 1).toLowerCase()) && !there_is_listGame(u))).forEach((u) -> {
           list_word.add(u);
        });
       return list_word;
   }
    
    private String print_listGame() {
        String str = "";
        str = listGame.stream().map((u) -> u + "\n").reduce(str, String::concat);
        return str;
    }
    
   private String look_for_word(ArrayList<String> list_word) {
       Map <Integer, String> map = new Hashtable<Integer, String>();
       list_word.forEach((u) -> {
           map.put(listWord.amount_word(last_letter(u)),u);
        });
       if (map.isEmpty())
           return "";
       return map.get(map.keySet().stream().sorted().findFirst().get());
       
       
      
   }
   
   private boolean there_is_listGame(String str) {
    return listGame_word.stream().anyMatch((u) -> (u.toLowerCase().equals(str.toLowerCase())));
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
        listGame_word.clear();
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
        controller.setTextTema(name_tema);
        
        stage.showAndWait();
        
        if(controller.getResponse())
            return textfield.getText().trim();
        return "";
    }
    
    private void name_thema(String thema) {
        name_tema = thema;
        switch(thema){
            case "Города России":
                name_file = "public\\City.txt";
                break;
            case "Животные":
                name_file = "public\\Animals.txt";
                break;
            case "Растения":
                name_file = "public\\Plants.txt";
                break;
            case "Имена":
                name_file = "public\\Name.txt";
                break;
        }
        listWord = new ListWord(name_file);
            
        }

    private void player_win() throws IOException {
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

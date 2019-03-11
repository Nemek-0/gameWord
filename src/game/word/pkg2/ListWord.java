/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.word.pkg2;

import java.util.ArrayList;

/**
 *
 * @author ПК-Тащера
 */
public class ListWord {
    private ArrayList<String> listWord;
    
    ListWord(String name_file)  {
        listWord = new My_file().read_file(name_file);
    }

    ListWord() {
        listWord = new ArrayList();
    }
    
    public ArrayList<String> setListWord() {
        return listWord;
    }
    
    public Integer amount_word(String letter) {
        Integer i= 0;
        for (String u: listWord)
        {
            if (letter.equals(u.substring(0, 1).toLowerCase()))
                i++;
        }
        return i;
    }
    
    public boolean look_for_word(String word) {
        for (String u: this.listWord)
        {
            if (u.toLowerCase().equals(word.toLowerCase()))
                return true;
        }
        return false;
    }
    
    
    
    
    @Override
    public String toString() {
        String str = "";
        for(String u: listWord) 
            str += u + "\n";
    return str;
        
    }
    
    
}

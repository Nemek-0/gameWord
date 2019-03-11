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
    private ArrayList<Word> listWord;
    
    ListWord(String nameFile)  {
        listWord = new ReadFile().readList(nameFile);
    }

    ListWord() {
        listWord = new ArrayList();
    }
    
    public ArrayList<Word> setListWord() {
        return listWord;
    }
    
    public Integer amount_word(String letter) {
        Integer i= 0;
        for (Word u: listWord)
        {
            if (letter.equals(u.getFirstLetter()))
                i++;
        }
        return i;
    }
    
    public boolean lookForWord(String word) {
        for (Word u: this.listWord)
        {
            if (u.getWord().equals(word.toLowerCase()))
                return true;
        }
        return false;
    }
    
    
    public void add(Word word){
        this.listWord.add(word);
    }
    
    @Override
    public String toString() {
        String str = "";
        for(Word u: listWord) 
            str += u + "\n";
    return str;
        
    }
    
    
}

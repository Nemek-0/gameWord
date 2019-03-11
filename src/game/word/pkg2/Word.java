/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.word.pkg2;

/**
 *
 * @author Serge
 */
public class Word {
    private String word;
    
    Word(String word){
        this.word = word.toLowerCase();
    }
    
    public String getWord(){
        return this.word;
    }
    
    public String getFirstLetter(){
        return word.substring(0, 1);
    }
    public String getLastLetter() {
        String letter = null;
        for(int i = 1; i < this.getWord().length(); i++){
            letter = word.substring(word.length() - i, word.length() - i + 1);
            if (letter.equals("ü") || letter.equals("ú") || letter.equals("û") || letter.equals("é") || letter.equals("¸")){
                
            }
            else break;      
        }
        return letter;
    }
    
    @Override
    public String toString(){
        return this.word;
    }
}

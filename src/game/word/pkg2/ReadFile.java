/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.word.pkg2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author ПК-Тащера
 */
public class ReadFile {

    
public ArrayList<Word> readList(String fileName) {
    
    ArrayList <Word> list = new ArrayList();
    try 
    {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String str;
        while((str = reader.readLine())!= null)
        {
            Word word = new Word(str);
            list.add(word);
        }
    }
    catch (FileNotFoundException e) {
        e.printStackTrace();
    }
    catch (IOException e) {
        e.printStackTrace();
    }
    return list;
    
    
       
}

    
           
}

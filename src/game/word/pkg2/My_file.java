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
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author ПК-Тащера
 */
public class My_file {

    
public ArrayList<String> read_file(String file_name) {
    
    ArrayList <String> list = new ArrayList();
    try 
    {
        BufferedReader reader = new BufferedReader(new FileReader(file_name));
        String str;
        while((str = reader.readLine())!= null)
        {
            list.add(str);
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

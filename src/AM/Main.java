/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AM;
import AM.Constants.constants;
import AM.database.database;

/**
 *
 * @author Chris
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        if(database.connectToDB() != null){
          
        }else{
          database.createDB(constants.databaseName);
          database.initializeDB(database.connectToDB());
        }
        
    }
    
}



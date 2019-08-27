/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AM.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import AM.Constants.constants;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author Chris
 */
public class database {
    
    public static Connection connectToDB(){
        
    Connection con = null;    
 
    try{
      Class.forName("com.mysql.jdbc.Driver");  
    } catch(ClassNotFoundException e){
      System.out.println(e); 
    }
    
    try{
      con = DriverManager.getConnection(constants.URL, constants.userName, constants.password);
    } catch(SQLException e){
      System.out.println(e);
    }   
    return con;  
  }
    
    public static void createDB(String databaseName){
         
    try{
      Class.forName("com.mysql.jdbc.Driver");  
    } catch(ClassNotFoundException e){
      System.out.println(e); 
    }
      
    try{
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost/", constants.userName, constants.password);
      PreparedStatement ps = con.prepareStatement("CREATE DATABASE IF NOT EXISTS "+ databaseName);
      System.out.println("Creating "+constants.databaseName+" database..."); 
      ps.executeUpdate();
      ps.close();
      System.out.println("Successfully created "+ constants.databaseName+" database"); 
    } catch(SQLException e){
      System.out.println(e);
    } 
  }
    
    public static void initializeDB(Connection databaseConnection){
       
       Connection con = databaseConnection;
       final String RTASQL = "CREATE TABLE `RTA` ("
                           + " `id` int AUTO_INCREMENT,"
                           + " `Unit Address` varchar(255),"   
                           + " `LandLord Address` varchar(255),"  
                           + " `landLord Email` varchar(255)," 
                           + " `Tenant Email` varchar(255),"   
                           + " `Contact Info` varchar(255),"
                           + " `Type Of Tenacy` varchar(255),"
                           + "  Installments varchar(255),"
                           + " `payable To` varchar(255),"
                           + " `Method Of Payment` varchar(255),"
                           + " `Discount Reason` varchar(255),"
                           + " `Key Description` varchar(255),"
                           + " `Smoking Rules` varchar(255),"
                           + " `Start of Tenancy` DATE,"
                           + " `When To pay` varchar(255),"
                           + " `Parking Fee` FLOAT,"
                           + " `Rental Unit Fee` FLOAT,"
                           + " `NSF` FLOAT,"
                           + " `Last Month Deposit` FLOAT,"
                           + " `Key Deposit` FLOAT,"
                           + "  PRIMARY KEY (id))";
       
       final String tenantSQL = "CREATE TABLE Tenants ("
                           + " `id` int,"
                           + " `Name` varchar(255))";       
       
       final String landLordSQL = "CREATE TABLE `Land Lords` ("
                           + " `id` int,"
                           + " `Name` varchar(255))"; 
       
       final String carsSQL = "CREATE TABLE `Cars` ("
                           + " `id` int,"
                           + " `Year` int,"
                           + " `Colour` varchar(255),"
                           + " `Model` varchar(255),"
                           + " `License Plate` varchar(255))";
       
       final String parkingSQL = "CREATE TABLE `Parking` ("
                           + " `id` int,"
                           + " `Location` varchar(255),"
                           + " `Assigned Spot` varchar(255),"
                           + " `duration` DATE,"
                           + " `Parking Fee` FLOAT)";
        
       final String paymentSQL = "CREATE TABLE `Payment` ("
                           + " `id` int,"
                           + " `Rent Increased By` FLOAT,"
                           + " `Amount Of Rent Owed` FLOAT,"
                           + " `Amount Of Rent Paid` FLOAT,"
                           + " `Rent Paid On` DATE)";
       
       final String loginSQL = "CREATE TABLE `Login` ("
                           + " `login` varchar(255),"
                           + " `password` varchar(255))";
                          
                           
       try{
         Class.forName("com.mysql.jdbc.Driver");  
       } catch(ClassNotFoundException e){
         System.out.println(e); 
       }
       
      try{
      PreparedStatement ps = con.prepareStatement(RTASQL);                
      ps.executeUpdate();
      
      ps = con.prepareStatement(tenantSQL);                
      ps.executeUpdate();
      
      ps = con.prepareStatement(landLordSQL);                
      ps.executeUpdate();
      
      ps = con.prepareStatement(carsSQL);                
      ps.executeUpdate();
      
      ps = con.prepareStatement(parkingSQL);                
      ps.executeUpdate();
      
      ps = con.prepareStatement(paymentSQL);                
      ps.executeUpdate();
      
      ps = con.prepareStatement(loginSQL);                
      ps.executeUpdate();
     
      ps.close();
    } catch(SQLException e){
      System.out.println(e);
    } 
       
    } 
       
}
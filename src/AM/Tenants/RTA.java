/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AM.Tenants;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author Chris
 */
public class RTA {
  
    //Name of Land Lord
    String landLord;
    
    //Name of tenants living in complex
    ArrayList<String> tenants = new ArrayList<String>();
    
    //Unit Address
    String unitAddress;
    
    //Is it Condominum
    boolean isCondominum;
    
    //Address of Land Lord   
    String landLordAddress;
    
    //Is it Email
    boolean isEmailAllowed;
    
    //Land lord e-mail Address
    String landLordEmail;
    
    //Tenant e-mail Addres
    String tenantEmail;
    
    //Is it land lord emergency contact
    boolean isLandLordContact;
    
    //Email address or phone number of contactee
    String contactInfo;
    
    //The YYYY/MM/DD the tenant started
    Date startTenanacy = new Date();
    
    //1. a fixed Length ending on YYYY/MM/DD
    //2. a montly Tenancy
    //3. Other (such as Daily, weekly, specify)
    String typeOfTenancy;
    
    //What day is rent Paid on
    // First,second (etc)
    int dayToPay;
    
    //When to pay rent, monthly, weekly.
    String installments;
    
    //The Summary of rent
    
    //Base rent of unit
    int rentalUnitFee;
    //Fee for parking
    int parkingFee;
    //Utility or services 
    HashMap<String, Integer> services = new HashMap<>();
    
    //The indiviual allow to recieve the money
    String payAble;
    
    //Method of payment
    String methodOfPayment;
    
    //Partial Period
    boolean isPartialPeriod;
    
    //Charge Back Fee Amount capped at 20
    int NSF;
    
    //Serive that will be included to the tenat
    HashMap<String, String> includeService = new HashMap<>();
    
    //Responsiblity of uilties
     HashMap<String, String> utilitiesResponsibility = new HashMap<>();
     
     //Rent Discount
     boolean isDiscount;
     String discountReason;
    
     boolean isRentDeposit;
     int LastMonthDeposit;
     
     boolean isKeyDeposit;
     int keyDeposit;
     String keyDescription;
     
     boolean isSmokingAllowed;
     String smokingRules;
     
     boolean isTenantInsured;
     
    
    
    
}
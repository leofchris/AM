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

    private String unitAddress;
    private String landLordAddress;
    private String landLordEmail;
    private String tenantEmail;
    private String contactInfo;
    private String typeOfTenancy;
    private String installments;
    private String payableTo;
    private String methodOfPayment;
    private String discountReason;
    private String keyDescription;
    private String smokingRules;
    private String whenToPay;
    
    private Date startTenanacy = new Date();
    
    private int id;

    private double parkingFee;
    private double rentalUnitFee;
    private double NSF;
    private double lastMonthDeposit;
    private double keyDeposit;
 
    private ArrayList<String> tenants = new ArrayList<String>();
    private ArrayList<String> landLords = new ArrayList<String>();
    
    private HashMap<String, Integer> services = new HashMap<>();
    private HashMap<String, String> includeService = new HashMap<>();
    private HashMap<String, String> utilitiesResponsibility = new HashMap<>();
    
    private boolean isCondominum;
    private boolean isEmailAllowed;
    private boolean isLandLordContact;
    private boolean isPartialPeriod;
    private boolean isDiscount;
    private boolean isRentDeposit;
    private boolean isKeyDeposit;
    private boolean isSmokingAllowed;
    private boolean isTenantInsured; 

}
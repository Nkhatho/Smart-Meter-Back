/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.bcx.web.smartmeter.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.primefaces.event.FlowEvent;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.ApplicationScope;

/**
 *
 * @author Leolen
 */
@Component
@ApplicationScope
@Repository
public class SystemDB {
    private Set<Billing> billingData = new HashSet<>();
    private boolean skip;
    
    /**
     * 
     * @return List of all debtors
     */
    public Set<Debtor> getDebtorsDetails(){
        Set<Debtor> debtors = new HashSet<>();
        
        for(Billing bill : getBillingData()){
            debtors.add(bill.getDebtorDetails());
            break;
        }
        
        return debtors;
    }
    
    /**
     * 
     * @param id - Debtor ID number
     * @return The requested debtor
     */
    public Debtor getDebtor(String id){
   
        Debtor result = null;
        if(!billingData.isEmpty()){
            for(Billing bill : getBillingData()){
                result = new Debtor(bill.getDebtorDetails());
                if(result.getIdNumber().equals(id))
                    break;
            }
        }
        return result;
    }
    
    /**
     * 
     * @param debtor - Debtor details
     * @return true if debtor details has been set and is in list
     */
    public boolean setDebtorDetails(Debtor debtor) {
        
        Billing bill = new Billing();
        bill.setUnitsConsumed(BigDecimal.valueOf(0));
        bill.setAmountDue(BigDecimal.valueOf(0));
        bill.setDebtorDetails(debtor);
        bill.setMeterDetails(debtor.getMeter());
        this.getBillingData().add(bill);
        
        return this.getBillingData().contains(bill);
    }

    /**
     * @return the billingData
     */
    public Set<Billing> getBillingData() {
        return billingData;
    }

    /**
     * @param billingData the billingData to set
     */
    public void setBillingData(Set<Billing> billingData) {
        this.billingData = billingData;
    }
//    public void save() {        
//        FacesMessage msg = new FacesMessage("Successful", "Welcome :" + user.getFirstname());
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//    }
     
    public boolean isSkip() {
        return skip;
    }
 
    public void setSkip(boolean skip) {
        this.skip = skip;
    }
     
    public String onFlowProcess(FlowEvent event) {
        if(skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        }
        else {
         
            return event.getNewStep();
        }
    }

}

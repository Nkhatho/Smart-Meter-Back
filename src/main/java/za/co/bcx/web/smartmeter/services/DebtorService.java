/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.bcx.web.smartmeter.services;

import java.util.List;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.bcx.web.smartmeter.models.Debtor;
import za.co.bcx.web.smartmeter.models.Meter;
import za.co.bcx.web.smartmeter.models.SystemDB;

/**
 *
 * @author Leolen
 */
@Service
public class DebtorService {
        
    @Autowired SystemDB systemDB = new SystemDB();

   public Debtor getDebtor(String id){
        return this.systemDB.getDebtor(id);
    }
    
    public boolean setDebtorDetails(Debtor debtor){
        return this.systemDB.setDebtorDetails(debtor);
    }

    public Set<Debtor> getDebtorsDetails() {
        return this.systemDB.getDebtorsDetails();
    }

    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Debtor Selected", ((Debtor) event.getObject()).getIdNumber());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
 
    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Debtor Unselected", ((Debtor) event.getObject()).getIdNumber());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}

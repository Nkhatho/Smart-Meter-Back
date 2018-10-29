/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.bcx.web.smartmeter.controllers;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.SessionScope;

/**
 *
 * @author Leolen
 */
@RestController
@SessionScoped
public class BillingController implements Serializable{
    
    @RequestMapping(value="/unitsconsumed")
    @ResponseBody
    public String unitsConsumed(){
        return "Units Consumed";
    }
    
    public BillingController(){}
    public void createPDF(){
        
    }
    
}

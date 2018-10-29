/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.bcx.web.smartmeter.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import za.co.bcx.web.smartmeter.models.Billing;
import za.co.bcx.web.smartmeter.models.Debtor;
import za.co.bcx.web.smartmeter.models.Meter;
import za.co.bcx.web.smartmeter.models.PDF;
import za.co.bcx.web.smartmeter.services.DebtorService;

/**
 *
 * @author Leolen
 */
@RestController
public class DebtorController {

    @Autowired private DebtorService debtorService;

    @RequestMapping(value="/ping" , method = RequestMethod.GET)
    public String pong(){
        return "pong";
    }
    
    //working!!
    @RequestMapping(value= "/debtor/{id}", method = RequestMethod.GET)
    public ResponseEntity<Debtor> getDebtor(@PathVariable String id){
        HttpHeaders responseHeaders = new HttpHeaders();
        ResponseEntity<Debtor> debtorResponse;
        Debtor debtor = debtorService.getDebtor(id);
        if( debtor == null){
            debtorResponse = new ResponseEntity<>(responseHeaders, HttpStatus.NO_CONTENT);
        }
        else{
            debtorResponse = new ResponseEntity<>(debtor, responseHeaders, HttpStatus.OK);
        }
        //could also add meter and billing info to the Debtor class
        return debtorResponse;
    }   
    
    //working!!
    //could also add another parameter of Meter info and Billing info
    @RequestMapping(value= "/debtordetails/save", method = RequestMethod.POST)
    public ResponseEntity<Debtor> setDebtorDetails(@RequestBody Debtor debtorDetails){
        HttpHeaders responseHeaders = new HttpHeaders();
        ResponseEntity<Debtor> debtorDetailsResponse;
        
        if(debtorService.setDebtorDetails(debtorDetails)){
            debtorDetailsResponse = new ResponseEntity<>(debtorDetails, responseHeaders, HttpStatus.OK);            
        }
        else{
            debtorDetailsResponse = new ResponseEntity<>(responseHeaders, HttpStatus.NO_CONTENT);
        }
        
        return debtorDetailsResponse;
    }

        //GET ALL DEBTORS
    @RequestMapping(value ="/debtors/details", method = RequestMethod.GET)
    public ResponseEntity<Set<Debtor>> getDebtorsDetails(){
        HttpHeaders responseHeaders = new HttpHeaders();
        ResponseEntity<Set<Debtor>> debtorResponse;
        Set<Debtor> debtors = debtorService.getDebtorsDetails();
        if( debtors == null){
            debtorResponse = new ResponseEntity<>(responseHeaders, HttpStatus.NO_CONTENT);
        }
        else{
            debtorResponse = new ResponseEntity<>(debtors, responseHeaders, HttpStatus.OK);
        }
        //could also add meter and billing info to the Debtor class
        return debtorResponse;
    } 
    
    @RequestMapping(value = "/balance/{id}", method = RequestMethod.GET)
    public ResponseEntity<BigDecimal> getBalance(@PathVariable String id){
        HttpHeaders responseHeaders = new HttpHeaders();
        ResponseEntity<BigDecimal> debtorResponse;
        Debtor debtor = debtorService.getDebtor(id);
        if( debtor == null){
            debtorResponse = new ResponseEntity<>(responseHeaders, HttpStatus.NO_CONTENT);
        }
        else{
            debtorResponse = new ResponseEntity<>(debtor.getBalance(), responseHeaders, HttpStatus.OK);
        }
        //could also add meter and billing info to the Debtor class
        return debtorResponse;
    }
    
//    @RequestMapping(value="/itesting", method=RequestMethod.GET)
//   public ResponseEntity<String> testingItext(){
//       HttpHeaders responseHeaders = new HttpHeaders();
//       ResponseEntity<String> itestResponse;
//       
//       try{
//            PDF pdf = new PDF();
//            if(pdf.createPDF() == 1){
//                itestResponse = new ResponseEntity<>("PDF WAS GOOD", responseHeaders, HttpStatus.OK);
//                System.out.println("PDF Created !!!!!");
//            }
//            else{
//                itestResponse = new ResponseEntity<>("DID NOT WORK", responseHeaders, HttpStatus.NO_CONTENT);
//                System.out.println("PDF NOT Created !!!!!");
//            }
//       }catch(IOException ex){
//           itestResponse = new ResponseEntity<>("IOEXCEPTION EXCEPTION ===" + ex.getMessage(), responseHeaders, HttpStatus.NO_CONTENT);
//           System.out.println("PDF NOT Created !!!!!");
//       }
//       catch(Exception ex){
//           itestResponse = new ResponseEntity<>("EXCEPTION EXCEPTION ===" + ex.getMessage(), responseHeaders, HttpStatus.NO_CONTENT);
//           System.out.println("PDF NOT Created !!!!!");
//       }
//        
//        return itestResponse;
//    }
}

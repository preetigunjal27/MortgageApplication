package com.businesslayer.mortgage.MortgageBusinessLayer.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.businesslayer.mortgage.MortgageBusinessLayer.Gateway.MortgageDataLayerCaller;
import com.businesslayer.mortgage.MortgageBusinessLayer.Model.Mortgage;
import com.businesslayer.mortgage.MortgageBusinessLayer.Service.MortgageException;
import com.businesslayer.mortgage.MortgageBusinessLayer.Service.MortgageValidations;

@RestController
@RequestMapping(path = "/mortgage")
public class MortgageController {
	
	/**
	 * REST API to retrieve all Mortgages exposed by Data layer
	 * @return Mortgage application array
	 */
	@GetMapping(path="/getMortgages", produces = "application/json")
    public String getMortgages() 
    { 				
		return MortgageDataLayerCaller.getAllMortgage();
    } 
    
	/**
	 * REST API to retrieve all Mortgages exposed by Data layer
	 * @param sortBy offerDate or CreateDate
	 * @return Sorted Mortgage array
	 * @throws Exception
	 */
	@GetMapping(path= "/getAllMortgagesSortedBy/{columnName}", produces = "application/json")
    public String getAllMortgagesSortedBy(                        
                       @PathVariable("columnName") String sortBy) 
                 throws Exception 
    {       
		return MortgageDataLayerCaller.getAllMortgageSortedBy(sortBy);
    }

	/**
	 * REST API to Insert new Mortgage application exposed by Data layer
	 * @param mortgage -new input object
	 * @return Mortgage array
	 * @throws Exception
	 */
	@PostMapping(path= "/addMortgage", consumes = "application/json", produces = "application/json")
    public Mortgage addMortgage(                        
                        @RequestBody Mortgage mortgage) 
                 throws Exception 
    {       
		try {
			MortgageValidations.validateMortgage(mortgage);
		} catch (MortgageException e) {
			throw new ResponseStatusException(
			           HttpStatus.BAD_REQUEST, e.getMessage().toString(), e);
		}
		return MortgageDataLayerCaller.addMortgage(mortgage);
    }
}

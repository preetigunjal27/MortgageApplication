package com.datalayer.mortgage.MortgageDataLayer.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datalayer.mortgage.MortgageDataLayer.dao.MortgageDAO;
import com.datalayer.mortgage.MortgageDataLayer.dao.SortMortgages;
import com.mortgage.MortgageDataLayer.Model.Mortgage;

@RestController
@RequestMapping(path = "/mortgageData")
public class MortgageDataController {
	
	/**
	 * REST API to retrieve all Mortgages
	 * @return Mortgage array
	 */
	@GetMapping(path="/getMortgagesData", produces = "application/json")
    public Mortgage[] getMortgages() 
    { 				
	 MortgageDAO mortgageDao= MortgageDAO.getMortgageDataStorage();
        return mortgageDao.getAllMortgage();
    } 
    
	/**
	 * REST API to retrieve all Mortgages
	 * @param columnName offerDate or CreateDate
	 * @return sorted Mortgage array
	 * @throws Exception
	 */
	@GetMapping(path= "/getAllMortgageSortedBy/{columnName}", produces = "application/json")
    public Mortgage[] getAllMortgageSortedBy(                        
    		@PathVariable("columnName") String columnName) 
                 throws Exception 
    {       
		MortgageDAO mortgageDao= MortgageDAO.getMortgageDataStorage();
		return SortMortgages.sort(mortgageDao.getAllMortgage(), columnName);
    }	
    
	/**
	 *  REST API to Insert new Mortgage application.
	 * @param mortgage
	 * @return Mortgage array
	 * @throws Exception
	 */
	@PostMapping(path= "/addMortgageData", consumes = "application/json", produces = "application/json")
    public Mortgage addMortgage(                        
                        @RequestBody Mortgage mortgage) 
                 throws Exception 
    {       
		MortgageDAO mortgageDao= MortgageDAO.getMortgageDataStorage();
		return mortgageDao.addMortgage(mortgage);
    }			 
	
	
	/**
	 * REST API to retrieve max version for provided MortgageId 
	 * @param mId
	 * @return Mortgage
	 * @throws Exception
	 */
	@GetMapping(path= "/getMaxVersionById/{mId}", produces = "application/json")
    public Mortgage getMortgageMaxVersionById(                        
    		@PathVariable("mId") String mId) 
                 throws Exception 
    {       
		MortgageDAO mortgageDao= MortgageDAO.getMortgageDataStorage();
		return mortgageDao.getMaxVersionMortgageById(mId);
    }	
}

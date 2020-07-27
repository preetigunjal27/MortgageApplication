package com.businesslayer.mortgage.MortgageBusinessLayer.Gateway;

import org.springframework.web.client.RestTemplate;

import com.businesslayer.mortgage.MortgageBusinessLayer.Model.Mortgage;

public class MortgageDataLayerCaller {

	public static String getAllMortgage()
	{
	    final String uri = "http://localhost:8088/mortgageData/getMortgagesData";
	     
	    RestTemplate restTemplate = new RestTemplate();
	    return restTemplate.getForObject(uri, String.class);
	}
	
	public static String getAllMortgageSortedBy(String columnName)
	{
	    final String uri = "http://localhost:8088/mortgageData/getAllMortgageSortedBy/" + columnName;
	     
	    RestTemplate restTemplate = new RestTemplate();
	    return restTemplate.getForObject(uri, String.class);
	}
	
	public static Mortgage addMortgage(Mortgage mortgage)
	{
	    final String uri = "http://localhost:8088/mortgageData/addMortgageData";
	     
	    RestTemplate restTemplate = new RestTemplate();
	    return restTemplate.postForObject(uri,mortgage, Mortgage.class);
	}
	
	public static Mortgage getMaxVersionByMortgageId(String mId)
	{
	    String uri = "http://localhost:8088/mortgageData/getMaxVersionById/"+mId;
	     
	    RestTemplate restTemplate = new RestTemplate();
	    return restTemplate.getForObject(uri, Mortgage.class);
	}
}
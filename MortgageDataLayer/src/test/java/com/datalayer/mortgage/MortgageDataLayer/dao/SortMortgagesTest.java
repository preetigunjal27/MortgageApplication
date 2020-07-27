package com.datalayer.mortgage.MortgageDataLayer.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;

import com.mortgage.MortgageDataLayer.Model.Mortgage;


public class SortMortgagesTest {

	/*
	 * Test to make sure mortgages are getting sorted 
	 * based on created date.
	 */
	@Test
	public void MortageSortByCreatedDate_Test() throws Exception {
		long uniqueNumber = System.currentTimeMillis();
		String mortgageID = "M" + uniqueNumber;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 1);
		Date date1 = cal.getTime(); //smallest created date.

		cal.add(Calendar.DATE, 2);
		Date date2 = cal.getTime();
		cal.add(Calendar.DATE, 3);
		Date date3 = cal.getTime();
		cal.add(Calendar.DATE, 4);
		Date date4 = cal.getTime();
		cal.add(Calendar.DATE, 5);
		Date date5 = cal.getTime(); //Farthest created date
		
		cal.add(Calendar.MONTH, 8);
		Date offerDate = cal.getTime();
		
		Mortgage[] mArray = new Mortgage[5]; 
		mArray[0] = new Mortgage(mortgageID + "1", 1, "B1", "OI-1", formatter.format(date5), formatter.format(offerDate), "N");
		mArray[1] = new Mortgage(mortgageID + "2", 1, "B1", "OI-1", formatter.format(date2), formatter.format(offerDate), "N");
		mArray[2] = new Mortgage(mortgageID + "3", 1, "B1", "OI-1", formatter.format(date4), formatter.format(offerDate), "N");
		mArray[3] = new Mortgage(mortgageID + "4", 1, "B1", "OI-1", formatter.format(date1), formatter.format(offerDate), "N");
		mArray[4] = new Mortgage(mortgageID + "5", 1, "B1", "OI-1", formatter.format(date3), formatter.format(offerDate), "N");
		
		Mortgage[] result = SortMortgages.sort(mArray,"createdDate");
		assertNotNull(result);
		assertEquals(result[0].getCreatedDate(),formatter.format(date1));
		assertEquals(result[1].getCreatedDate(),formatter.format(date2));
		assertEquals(result[2].getCreatedDate(),formatter.format(date3));
		assertEquals(result[3].getCreatedDate(),formatter.format(date4));;
		assertEquals(result[4].getCreatedDate(),formatter.format(date5));
		
	}
	
	/*
	 * Test to make sure mortgages are getting sorted 
	 * based on offer date.
	 */
	@Test
	public void MortageSortByOfferedDate_Test() throws Exception {
		long uniqueNumber = System.currentTimeMillis();
		String mortgageID = "M" + uniqueNumber;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		Date date1 = cal.getTime(); //smallest created date.

		cal.add(Calendar.DATE, 1);
		Date date2 = cal.getTime();
		cal.add(Calendar.DATE, 1);
		Date date3 = cal.getTime();
		cal.add(Calendar.DATE, 1);
		Date date4 = cal.getTime();
		cal.add(Calendar.DATE, 1);
		Date date5 = cal.getTime(); //Farthest created date

		Mortgage[] mArray = new Mortgage[5]; 
		mArray[0] = new Mortgage(mortgageID + "1", 1, "B1", "OI-1", formatter.format(currentDate) , formatter.format(date2), "N");
		mArray[1] = new Mortgage(mortgageID + "2", 1, "B1", "OI-1",formatter.format(currentDate) , formatter.format(date5), "N");
		mArray[2] = new Mortgage(mortgageID + "3", 1, "B2", "OI-1",formatter.format(currentDate) , formatter.format(date1), "N");
		mArray[3] = new Mortgage(mortgageID + "4", 1, "B3", "OI-1",formatter.format(currentDate) , formatter.format(date3), "N");
		mArray[4] = new Mortgage(mortgageID + "5", 1, "B2", "OI-1",formatter.format(currentDate) , formatter.format(date4), "N");
		
		Mortgage[] result = SortMortgages.sort(mArray,"offerDate");
		assertNotNull(result);
		assertEquals(result[0].getOfferDate(),formatter.format(date1));
		assertEquals(result[1].getOfferDate(),formatter.format(date2));
		assertEquals(result[2].getOfferDate(),formatter.format(date3));
		assertEquals(result[3].getOfferDate(),formatter.format(date4));
		assertEquals(result[4].getOfferDate(),formatter.format(date5));
		
	}
}

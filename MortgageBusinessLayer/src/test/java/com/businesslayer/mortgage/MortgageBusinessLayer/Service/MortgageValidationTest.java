package com.businesslayer.mortgage.MortgageBusinessLayer.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


public class MortgageValidationTest {

	/*
	 * Test to make sure MortgageException occurs if offer date is not farther than
	 * today + 6 months
	 */
	@Test
	public void validateOfferDate_Negative() throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 2); // offer date is only 2 month farther
		Date offerDate = cal.getTime();

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		try {
			MortgageValidations.validateOfferDate(formatter.format(offerDate));
			Assertions.fail("MortgageException was expected.");
		} catch (MortgageException e) {
			assertEquals("Offer date must be farther than next 6 months.", e.getMessage());
		}
	}

	/*
	 * Test to make sure MortgageException does not occurs 
	 * if offer date is farther than today + 6 months
	 */
	@Test
	public void validateOfferDate_Positive() throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 7); // offer date is 7 month farther
		Date offerDate = cal.getTime();

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		try {
			MortgageValidations.validateOfferDate(formatter.format(offerDate));

		} catch (Exception e) {
			Assertions.fail("Unexpected Exception occured." + e.getMessage());
		}
	}
}

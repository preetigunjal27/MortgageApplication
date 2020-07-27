package com.businesslayer.mortgage.MortgageBusinessLayer.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.businesslayer.mortgage.MortgageBusinessLayer.Model.Mortgage;

public class MortgageValidationAttributeTest {

	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	Date currentDate;
	Date offerDate;

	@BeforeEach
	public void setUp() {
		Calendar cal = Calendar.getInstance();
		currentDate = cal.getTime();
		cal.add(Calendar.MONTH, 8);
		offerDate = cal.getTime();
	}

	/*
	 * Test to make sure MortgageException occurs if mortgage id is null.
	 */
	@Test
	public void validateMortgageAttributes_Test() throws Exception {

		Mortgage mortgage = new Mortgage(null, 1, "B1", "OI-1", formatter.format(currentDate),
				formatter.format(offerDate), "N");
		try {
			MortgageValidations.validateMortgageAttributes(mortgage);
			Assertions.fail("MortgageException was expected.");
		} catch (MortgageException e) {
			assertEquals("Provide value for all mortgage attributes", e.getMessage());
		}
	}
	
	/*
	 * Test to make sure MortgageException occurs if expire flag is invalid  id is null.
	 */
	@Test
	public void validateMortgageAttributes_Test_ExpiryFlag() throws Exception {

		Mortgage mortgage = new Mortgage(null + "1", 1, "B1", "OI-1", formatter.format(currentDate),
				formatter.format(offerDate), "F");
		try {
			MortgageValidations.validateMortgageAttributes(mortgage);
			Assertions.fail("MortgageException was expected.");
		} catch (MortgageException e) {
			assertEquals("Provide value for all mortgage attributes", e.getMessage());
		}
	}
}

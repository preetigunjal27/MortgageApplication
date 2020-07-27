package com.businesslayer.mortgage.MortgageBusinessLayer.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.businesslayer.mortgage.MortgageBusinessLayer.Gateway.MortgageDataLayerCaller;
import com.businesslayer.mortgage.MortgageBusinessLayer.Model.Mortgage;

public class MortgageValidations {
	public static void validateMortgage(Mortgage mortgageObj) throws Exception {
		validateMortgageAttributes(mortgageObj);
		validateVersion(mortgageObj.getMortgageId(),mortgageObj.getVersion());
		validateOfferDate(mortgageObj.getOfferDate());
	}
	
	protected static void validateMortgageAttributes(Mortgage mortgageObj) throws MortgageException {
		if(mortgageObj.getMortgageId() == null
				|| mortgageObj.getVersion() <1
				|| mortgageObj.getOfferDate() == null
				|| mortgageObj.getCreatedDate() == null
				|| mortgageObj.getOfferId() == null
				|| mortgageObj.getProductId() == null
				|| !validateExpirationFalg(mortgageObj.getExpired()))
		 {
			throw new MortgageException("Provide value for all mortgage attributes");
		}
	}

	private static boolean validateExpirationFalg(String expireFlag) {
		return expireFlag != null && (expireFlag.equals("Y") || expireFlag.equals("N"));
	}
	protected static void validateVersion(String mortgageId, int mortgageVersion) throws MortgageException {
		Mortgage storedMortage = MortgageDataLayerCaller.getMaxVersionByMortgageId(mortgageId);
		if(storedMortage != null && mortgageVersion < storedMortage.getVersion()) {
			throw new MortgageException("Latest version available. Can not store lower versions.");
		}
	}
	
	protected static void validateOfferDate(String mortgageOfferDate) throws MortgageException, ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date provideOfferDate = formatter.parse(mortgageOfferDate);
		Calendar cal = Calendar.getInstance(); 
		cal.add(Calendar.MONTH, 6);
		Date dt = cal.getTime();
		dt = formatter.parse(formatter.format(dt));
		
		if(provideOfferDate.compareTo(dt) < 0 ) {
			throw new MortgageException("Offer date must be farther than next 6 months.");
		}
	}

}

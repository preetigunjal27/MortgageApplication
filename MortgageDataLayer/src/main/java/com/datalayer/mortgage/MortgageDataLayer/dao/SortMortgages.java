package com.datalayer.mortgage.MortgageDataLayer.dao;

import java.text.SimpleDateFormat;

import com.mortgage.MortgageDataLayer.Model.Mortgage;


public class SortMortgages {

	public static Mortgage[] sort(Mortgage[] mortgages, String columnName) throws Exception {
		if (mortgages == null
				|| !(columnName.equalsIgnoreCase("createdDate") || columnName.equalsIgnoreCase("offerDate"))) {
			return mortgages;
		}
		int count = mortgages.length;
		Mortgage temp;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		for (int i = 0; i < count; i++) {
			for (int j = i + 1; j < count; j++) {
				boolean swap = (columnName.equalsIgnoreCase("createdDate")
						? formatter.parse(mortgages[i].getCreatedDate())
								.compareTo(formatter.parse(mortgages[j].getCreatedDate())) > 0
						: formatter.parse(mortgages[i].getOfferDate())
								.compareTo(formatter.parse(mortgages[j].getOfferDate())) > 0);
				if (swap) {
					temp = mortgages[i];
					mortgages[i] = mortgages[j];
					mortgages[j] = temp;
				}
			}
		}
		return mortgages;
	}

}

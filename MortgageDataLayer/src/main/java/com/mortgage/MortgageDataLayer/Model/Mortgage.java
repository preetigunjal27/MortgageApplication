package com.mortgage.MortgageDataLayer.Model;

public class Mortgage {

	private String mortgageId;

	private int version;

	private String offerId;

	private String productId;

	private String offerDate;

	private String createdDate;

	private String expired;

	public Mortgage() {
	}

	public Mortgage(String mId, int mVersion, String mProductId, String mOfferId, String mCreatedDate, String mOfferDate,
			String mExpired) {
		this.mortgageId = mId;
		this.version = mVersion;
		this.offerId = mOfferId;
		this.productId = mProductId;
		this.offerDate = mOfferDate;
		this.createdDate = mCreatedDate; //Ideally this should not be taken as input. Instead get current date.
		this.expired = mExpired;		
	}

	public String getMortgageId() {
		return mortgageId;
	}

	public void setMortgageId(String mortgageId) {
		this.mortgageId = mortgageId;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getOfferId() {
		return offerId;
	}

	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getOfferDate() {
		return offerDate;
	}

	public void setOfferDate(String offerDate) {
		this.offerDate = offerDate;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getExpired() {
		return expired;
	}

	public void setExpired(String expired) {
		this.expired = expired;
	}
}

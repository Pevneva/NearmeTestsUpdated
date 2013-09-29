package ru.st.selenium.model;

public class Offer {

	private String title;
	private String subTitle;
	private String validityFrom;
	private String validityTo;
	private boolean isUnique;
	private boolean isRedeemable;
	private String offerType;
	private String codeType;
	private String codeFormat;

	
	public String getValidityFrom() {
		return validityFrom;
	}
	
	public Offer setValidityFrom(String validityFrom) {
		this.validityFrom = validityFrom;
		return this;
	}
	
	public String getValidityTo() {
		return validityTo;
	}
	public Offer setValidityTo(String validityTo) {
		this.validityTo = validityTo;
		return this;
	}
	
	public boolean getIsUnique() {
		return isUnique;
	}
	public Offer setIsUnique(boolean isUnique) {
		this.isUnique = isUnique;
		return this;
	}

	public boolean getIsRedeemable() {
		return isRedeemable;
	}
	public Offer setIsRedeemable(boolean isRedeemable) {
		this.isRedeemable = isRedeemable;
		return this;
	}

	public String getOfferType() {
		return offerType;
	}
	public Offer setOfferType(String offerType) {
		this.offerType = offerType;
		return this;
	}

	public String getCodeType() {
		return codeType;
	}
	public Offer setCodeType(String codeType) {
		this.codeType = codeType;
		return this;
	}


	public String getCodeFormat() {
		return codeFormat;
	}
	public Offer setCodeFormat(String codeFormat) {
		this.codeFormat = codeFormat;
		return this;
	}
/*
	public String getCountry() {
		return country;
	}
	
	public User setCountry(String country) {
		this.country = country;
		return this;
	}

	public String getCity() {
		return city;
	}
	public User setCity(String city) {
		this.city = city;
		return this;
	}
*/
	
}

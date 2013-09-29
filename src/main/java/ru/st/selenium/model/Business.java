package ru.st.selenium.model;

public class Business {

	private String tradeName;
	private String businessType;
	private String numberOfVenues;
	private String contractNumber;	
	private String managerEmail;
	private String urlName;
	private String contractType;	
	private String promoCode;	
	
/*
	private String agent;	
	private String paymentMethod;
	private String startOfDate;
	private String managerFirstname;
	private String managerLastname;
	private String managerNationalInsuranceNo;
	private String managerJobTitle;
	private String managerTelephone;
	private String managerMobile;
	private String companyNo;
	private String companyVatNo;
	private String address1;
	private String address2;
	private String address3;
	private String city;
	private String postcode;
	private String country;
	private String region;
	private String county;
	private String contactTelephone;
	private String contactFax;
	private String contactEmail;
	private String contactWebsite;
	private String facebook;
	private String twitter;
	private String foursquare;
	private String pinterest;
	private String category;
	private String keywords;
*/	
	
	public String getTradename() {
		return tradeName;
	}
	
	public Business setTradename(String tradeName) {
		this.tradeName = tradeName;
		return this;
	}
	
	public String getBusinessType() {
		return businessType;
	}
	
	public Business setBusinessType(String businessType) {
		this.businessType = businessType;
		return this;
	}
	
	public String getNumberOfVenues() {
		return numberOfVenues;
	}
	
	public Business setNumberOfVenues(String numberOfVenues) {
		this.numberOfVenues = numberOfVenues;
		return this;
	}	
	
	public String getContractNumber() {
		return contractNumber;
	}
	public Business setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
		return this;
	}
	
	public String getManagerEmail() {
		return managerEmail;
	}
	public Business setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
		return this;
	}
	
	public String getUrlName() {
		return urlName;
	}
	public Business setUrlName(String urlName) {
		this.urlName = urlName;
		return this;
	}
	
	public String getContractType() {
		return contractType;
	}
	public Business setContractType(String contractType) {
		this.contractType = contractType;
		return this;
	}

	public String getPromoCode() {
		return promoCode;
	}
	public Business setPromoCode(String promoCode) {
		this.promoCode = promoCode;
		return this;
	}

/*
	public String getLastname() {
		return lastname;
	}
	public User setLastname(String lastname) {
		this.lastname = lastname;
		return this;
	}



	public String getGender() {
		return gender;
	}
	public User setGender(String gender) {
		this.gender = gender;
		return this;
	}


	public String getBirthday() {
		return birthday;
	}
	public User setBirthday(String birthday) {
		this.birthday = birthday;
		return this;
	}

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

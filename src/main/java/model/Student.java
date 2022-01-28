package model;

public class Student {
	
	private int id;
	private String firstName;
	private String lastName;
	private String address;
	private String postCode;
	private String postOffice;
	
	public Student() {
		this.id = -1;
		this.firstName = "";
		this.lastName = "";
		this.address = "";
		this.postCode = "";
		this.postOffice = "";
	}
	
	public Student(int id, String firstName, String lastName, String address, String postCode, String postOffice) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.postCode = postCode;
		this.postOffice = postOffice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getPostOffice() {
		return postOffice;
	}

	public void setPostOffice(String postOffice) {
		this.postOffice = postOffice;
	}
	
	@Override
	public String toString() {
		return id + ": " + firstName + " " + lastName +  ", " + address + ", " + postCode + ", " + postOffice;
	}
}

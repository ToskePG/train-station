package data;

public class Worker {
	private String jmbg;
	private String firstName;
	private String lastName;
	private int yearsOfExperience;
	private String dateOfEmployment;
	
	public Worker(String jmbg, String firstName, String lastName, int yearsOfExperience, String dateOfEmployment) {
		this.jmbg = jmbg;
		this.firstName = firstName;
		this.lastName = lastName;
		this.yearsOfExperience = yearsOfExperience;
		this.dateOfEmployment = dateOfEmployment;
	}
	
	public String getJmbg() {
		return jmbg;
	}
	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
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
	public int getYearsOfExperience() {
		return yearsOfExperience;
	}
	public void setYearsOfExperience(int yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}
	public String getDateOfEmployment() {
		return dateOfEmployment;
	}
	public void setDateOfEmployment(String dateOfEmployment) {
		this.dateOfEmployment = dateOfEmployment;
	}
}

package data;

import java.util.Scanner;

public class Wagon {
	
	Scanner input = new Scanner(System.in);
	
	private int numberOfSeats;
	private int wagonClass;
	private int yearOfManufacture;
	private int locomotiveId;
	
	public Wagon(int numberOfSeats, int wagonClass, int yearOfManufacture, int locomotiveId) {
		this.numberOfSeats = numberOfSeats;
		this.wagonClass = wagonClass;
		setYearOfManufacture(yearOfManufacture);
		this.locomotiveId = locomotiveId;
	}
	
	public int getNumberOfSeats() {
		return numberOfSeats;
	}
	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}
	public int getWagonClass() {
		return wagonClass;
	}
	public void setWagonClass(int wagonClass) {
		this.wagonClass = wagonClass;
	}
	public int getYearOfManufacture() {
		return yearOfManufacture;
	}
	public void setYearOfManufacture(int yearOfManufacture) {
		while(yearOfManufacture < 1920 || yearOfManufacture > 2023) {
			System.out.println("Nevalidan unos godine proizvodnje. Pokusajte ponovo. ");
			yearOfManufacture = input.nextInt();
		}
		this.yearOfManufacture = yearOfManufacture;
	}
	public int getLocomotiveId() {
		return this.locomotiveId;
	}
	public void setLocomotiveId(int locomotiveId) {
		this.locomotiveId = locomotiveId;
	}
	
}



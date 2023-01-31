package data;

import java.util.Scanner;

public class Locomotive {
	
	Scanner input = new Scanner(System.in);
	
	private int numberOfWagons;
	private int yearOfManufacture;
	
	public Locomotive(int numberOfWagons,int yearOfManufacture) {
		this.numberOfWagons = numberOfWagons;
		setYearOfManufacture(yearOfManufacture);
	}
	
	public int getNumberOfWagons() {
		return numberOfWagons;
	}
	public void setNumberOfWagons(int numberOfWagons) {
		this.numberOfWagons = numberOfWagons;
	}
	public int getYearOfManufacture() {
		return yearOfManufacture;
	}
	public void setYearOfManufacture(int yearOfManufacture) {
		while(yearOfManufacture < 1920 || yearOfManufacture > 2023) {
			System.out.println("Nevalidan unos godine proizvodnje. Unesite ponovo!");
			yearOfManufacture = input.nextInt();
		}
		this.yearOfManufacture = yearOfManufacture;
	}
	
	@Override
	public String toString() {
		return "Locomotive [input=" + input + ", numberOfWagons=" + numberOfWagons + ", yearOfManufacture="
				+ yearOfManufacture + "]";
	}
	
}

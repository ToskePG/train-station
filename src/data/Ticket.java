package data;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Ticket {
	
	String pattern = "yyyy-MM-dd";
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	
	private String fullName;
	private int ticketNumber;
	private double ticketPrice;
	private Date ticketDate;
	
	public Ticket(String fullName, int ticketNumber, double ticketPrice, Date ticketDate) {
		this.fullName = fullName;
		this.ticketNumber = ticketNumber;
		this.ticketPrice = ticketPrice;
		this.ticketDate = ticketDate;
	}
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public int getTicketNumber() {
		return ticketNumber;
	}
	public void setTicketNumber(int ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	public double getTicketPrice() {
		return ticketPrice;
	}
	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	public Date getTicketDate() {
		return ticketDate;
	}
	public void setTicketDate(Date ticketDate) {
		this.ticketDate = ticketDate;
	}

	@Override
	public String toString() {
		return "Ticket buyer: " + fullName + ", ticketNumber=" + ticketNumber + ", ticketPrice=" + ticketPrice
				+ ", ticketDate=" + ticketDate;
	}
	
	
	
}

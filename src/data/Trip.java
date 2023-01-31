package data;

public class Trip {
	private String destination;
	private String time;
	private String tripStart;
	private int tripCardId;
	private int tripCompositionId;
	private int driverId;
	
	public Trip(String destinantion, String time, String tripStart, int tripCard, int tripCompositionId, int driverId) {
		this.destination = destinantion;
		this.time = time;
		this.tripStart = tripStart;
		this.tripCardId = tripCard;
		this.tripCompositionId = tripCompositionId;
		this.driverId = driverId;
	}
	
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTripStart() {
		return tripStart;
	}
	public void setTripStart(String tripStart) {
		this.tripStart = tripStart;
	}
	public int getTripCardId() {
		return tripCardId;
	}
	public void setTripCardId(int tripCardId) {
		this.tripCardId = tripCardId;
	}
	public int getTripCompositionId() {
		return tripCompositionId;
	}
	public void setTripCompositionId(int tripCompositionId) {
		this.tripCompositionId = tripCompositionId;
	}
	public int getDriverId() {
		return driverId;
	}
	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}
	
	@Override
	public String toString() {
		return "Trip [destination=" + destination + ", time=" + time + ", tripStart=" + tripStart + ", tripCardId="
				+ tripCardId + ", tripCompositionId=" + tripCompositionId + ", driverId=" + driverId + "]";
	}
	
}

package data;

public class Driver {
	private int workerId;
	private int workHours;
	
	public Driver(int workerId, int workHours) {
		this.workerId = workerId;
		this.workHours = workHours;
	}
	
	public int getWorkerId() {
		return this.workerId;
	}
	public void setWorkerId(int workerId) {
		this.workerId = workerId;
	}
	public int getWorkHours() {
		return this.workHours;
	}
	public void setWorkHours(int workHours) {
		this.workHours = workHours;
	}

	@Override
	public String toString() {
		return "Driver [workerId=" + workerId + ", workHours=" + workHours + "]";
	}
	
	
}

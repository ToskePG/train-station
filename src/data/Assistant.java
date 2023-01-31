package data;

public class Assistant {
	private int workerId;

	public Assistant(int workerId) {
		this.workerId = workerId;
	}
	

	public int getWorkerId() {
		return workerId;
	}

	public void setWorkerId(int workerId) {
		this.workerId = workerId;
	}
	
	@Override
	public String toString() {
		return "Assistant [workerId=" + workerId + "]";
	}
}

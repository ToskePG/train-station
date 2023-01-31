package data;

public class MedicalReview {
	private String healthReport;
	private String dateOfReview;
	
	public MedicalReview(String healthReport, String dateOfReview) {
		this.healthReport = healthReport;
		this.dateOfReview = dateOfReview;
	}
	
	public String getHealthReport() {
		return healthReport;
	}
	public void setHealthReport(String healthReport) {
		this.healthReport = healthReport;
	}
	public String getDateOfReview() {
		return dateOfReview;
	}
	public void setDateOfReview(String dateOfReview) {
		this.dateOfReview = dateOfReview;
	}
	
	@Override
	public String toString() {
		return "MedicalReview [healthReport=" + healthReport + ", dateOfReview=" + dateOfReview + "]";
	}
}

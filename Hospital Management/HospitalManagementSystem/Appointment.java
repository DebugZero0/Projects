package HospitalManagementSystem;

public class Appointment {
	private Patient patient;
	private Doctor doctor; 
	private String data;
	
	public Appointment(Doctor doctor,Patient patient,String data) {
		this.data=data;
		this.doctor=doctor;
		this.patient=patient;
	}
	
	public String toString() {
		return "Appointment [ patient"+patient+" Doctor "+doctor+" Data: "+data+"]";
	}
}

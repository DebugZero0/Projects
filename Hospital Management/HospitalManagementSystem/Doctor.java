package HospitalManagementSystem;

public class Doctor {
	private static int idCount=1;
	private int id;
	private String name;
	private String specialty;
	
	public Doctor (String name,String specialty) {
		this.id=idCount++;
		this.name=name;
		this.specialty=specialty;
	}
	
	public int getId() {
		return id;
	}
	@Override
	public String toString() {
		return "Doc Id:"+ id +" Name:"+name+" Specialty:"+specialty;
	}
	
}

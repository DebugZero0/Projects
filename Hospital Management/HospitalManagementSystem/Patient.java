package HospitalManagementSystem;

public class Patient { 
	private static int idCount=0;
	private int id;
	private int age;
	private String name;
	private String gender;
	
	public Patient(String name,String gender,int age) {
		this.id=++idCount; 
		this.age=age;
		this.gender=gender;
		this.name=name;
	}
	public int getId() {
        return id;
    }
	@Override
	public String toString() { 
		return "Id: "+ id +" Name: "+ name +" Age :"+age+" Gender: "+gender;
	}

}

package users;

public class Teacher extends Responsible{
	public String room;
	
	public Teacher(String username, String password, String name, String email) {
		super(username, password, name, email);
		this.room = "teste";
	}
	
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "Teacher";
	}
	
}

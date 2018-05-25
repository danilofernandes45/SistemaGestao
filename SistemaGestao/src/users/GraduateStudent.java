package users;

public class GraduateStudent extends UserStandard{
	public GraduateStudent(String username, String password, String name, String email) {
		super(username, password, name, email);
	}
	
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "GraduateStudent";
	}

}

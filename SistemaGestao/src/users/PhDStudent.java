package users;

public class PhDStudent extends UserStandard{

	public PhDStudent(String username, String password, String name, String email) {
		super(username, password, name, email);
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "PhDStudent";
	}
}

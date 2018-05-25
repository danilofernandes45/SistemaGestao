package users;

public class MasterStudent extends UserStandard{

	public MasterStudent(String username, String password, String name, String email) {
		super(username, password, name, email);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "MasterStudent";
	}


}

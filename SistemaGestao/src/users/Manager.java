package users;

public class Manager extends Responsible{

	public Manager(String username, String password, String name, String email) {
		super(username, password, name, email);
	}
	
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "Manager";
	}
	
}

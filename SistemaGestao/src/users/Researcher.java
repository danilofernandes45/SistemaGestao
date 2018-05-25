package users;

public class Researcher extends Responsible{
	public Researcher(String username, String password, String name, String email) {
		super(username, password, name, email);
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "Researcher";
	}
	
}

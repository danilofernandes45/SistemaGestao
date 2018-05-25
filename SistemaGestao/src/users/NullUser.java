package users;

public class NullUser implements User {

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public void setUsername(String username) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public void setPassword(String password) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public void setEmail(String email) {
		// TODO Auto-generated method stub

	}
	
	public String toString() {
		return "No user was given";
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "NullUser";
	}

}

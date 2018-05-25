package activities;

import java.util.ArrayList;

import users.User;
import util.SupportMaterial;

public class TradicionalClass extends ActivityStandard{

	public TradicionalClass(String name, String description, User userResponsible, ArrayList<User> listParticipants,
			SupportMaterial material) {
		super(name, description, userResponsible, listParticipants, material);
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "TradicionalClass";
	}

}

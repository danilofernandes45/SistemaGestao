package activities;

import java.util.ArrayList;

import users.User;
import util.SupportMaterial;

public interface Activity {

	String getName();
	
	void setName(String name);
	
	String getDescription();
	
	void setDescription(String description);
	
	User getUserResponsible();
	
	void setUserResponsible(User userResponsible);
	
	ArrayList<User> getListParticipants();
	
	void setListParticipants(ArrayList<User> listParticipants);
	
	SupportMaterial getMaterial();
	
	void setMaterial(SupportMaterial material);
	
	void addParticipant(User participant);
	
	String getType();
	
	String toString();

	
}

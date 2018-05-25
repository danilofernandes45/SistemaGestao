package activities;

import java.util.ArrayList;

import users.*;
import util.NullSupportMaterial;
import util.SupportMaterial;

public class NullActivity implements Activity {

	public String getName() {
		return "";
	}
	public void setName(String name) {
	}
	public String getDescription() {
		return "";
	}
	public void setDescription(String description) {
	}
	public User getUserResponsible() {
		return new NullUser();
	}
	public void setUserResponsible(User userResponsible) {
	}
	public ArrayList<User> getListParticipants() {
		return new ArrayList<User>();
	}
	public void setListParticipants(ArrayList<User> listParticipants) {
	}
	public SupportMaterial getMaterial() {
		return new NullSupportMaterial();
	}
	public void setMaterial(SupportMaterial material) {
	}
	
	public void addParticipant(User participant) {
		
	}
	
	public String getType() {
		return "NullActivity";
	}
	
	public String toString() {
		
		return "No activity";
		
	}	

	
}

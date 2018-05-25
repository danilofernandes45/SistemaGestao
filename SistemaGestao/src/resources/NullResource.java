package resources;

import java.util.ArrayList;
import java.util.Calendar;

import users.Responsible;

public class NullResource implements Resource {

	@Override
	public String getIdentification() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public void setIdentification(String identification) {
		// TODO Auto-generated method stub

	}

	@Override
	public Calendar getInitialDate() {
		// TODO Auto-generated method stub
		return Calendar.getInstance();
	}

	@Override
	public void setInitialDate(Calendar initialDate) {
		// TODO Auto-generated method stub

	}

	@Override
	public Calendar getFinishDate() {
		// TODO Auto-generated method stub
		return Calendar.getInstance();
	}

	@Override
	public void setFinishDate(Calendar finishDate) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Responsible> getResponsibles() {
		// TODO Auto-generated method stub
		return new ArrayList<Responsible>();
	}

	@Override
	public void setResponsibles(ArrayList<Responsible> responsibles) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public String toString() {
		
		return "No resource";
		
	}

}

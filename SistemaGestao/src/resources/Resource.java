package resources;

import java.util.ArrayList;
import java.util.Calendar;

import users.Responsible;

public interface Resource {

	String getIdentification();
	
	void setIdentification(String identification);
	
	Calendar getInitialDate();
	
	void setInitialDate(Calendar initialDate);
	
	Calendar getFinishDate();
	
	void setFinishDate(Calendar finishDate);
	
	ArrayList<Responsible> getResponsibles();
	
	void setResponsibles(ArrayList<Responsible> responsibles);
	
	String toString();	
	
}

package main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import activities.*;
import allocations.*;
import resources.*;
import users.*;
import util.PresentationFile;
import util.SourceCode;
import util.SupportMaterial;

public class Menu {
	
	private Scanner input;
	private User activeUser;
	private AcademicUnit academicUnit;
	
	public Menu(String name) {
		
		input = new Scanner(System.in);
		activeUser = new NullUser();
		createDefaultAcademicUnit(name);
	}
	
	public void start() {
		
		login();
		
		control();	
	}
		
	private void login() {
		
		while(activeUser instanceof NullUser) {
			
			System.out.println("Informe seu nome de usuario: ");
			String username = input.nextLine();
			System.out.println("Informe sua senha: ");
			String password = input.nextLine();
			
			activeUser = academicUnit.getUserByLogin(username,password);
		}
	}
	
	private void createDefaultAcademicUnit(String name) {
		
		academicUnit = new AcademicUnit(name);		
		Manager defaultManager = new Manager("admin","admin","Administrador","admin@admin.com");
		academicUnit.addUser(defaultManager);
	}
	
	private void control() {
		
		int option = -1;
		
		while(activeUser instanceof UserStandard) {
			
			int result;
			option = mainMenu();
			
			switch(option) {
				case 0:
					result = choose("Voce deseja continuar utilizando o sistema?\n" + 
									"\t1.SIM\n\t2.NAO", 1, 2);
					activeUser = new NullUser();
					
					if(result == 1)
						login();
						
					break;
					
				case 1:
					result = choose("CONSULTAR\n\t1.USUARIO\n\t2.RECURSO", 1, 2);
					switch(result) {
						case 1:
							consultUser();
							break;
						case 2:
							consultResource();
							break;
					}
					break;
				case 2:
					result = choose("CADASTRAR\n\t1.USUARIO\n\t2.ATIVIDADE\n\t3.RECURSO", 1, 3);
					switch(result) {
						case 1:
							registerUser();
							break;
						case 2:
							registerActivity();
							break;
						case 3:
							registerResource();
							break;
					}
					break;
				case 3:
					changeAllocationStatus();
					break;
				case 4:
					addResourceInActivity();
					break;
				case 5:
					report();
					break;	
			}
		}
	
		
	}
	
	public Integer mainMenu(){

		System.out.println("USUARIO: " + activeUser.getName() + "  TIPO_CONTA: " + activeUser.getType() + "\n\n");
		System.out.println("MENU - SISTEMA DE GESTAO DE RECURSOS\n\n");
		
		System.out.println("\t1.CONSULTAR");
		if( activeUser instanceof Responsible ) {
			System.out.println("\t2.CADASTRAR");
			System.out.println("\t3.ALTERAR STATUS DA ALOCACAO");
			System.out.println("\t4.ADICIONAR RECURSO NA ATIVIDADE");
		}
		if( activeUser instanceof Manager )
			System.out.println("\t5.RELATORIO DE ATIVIDADES");
		
		System.out.println("\t0.SAIR");
		
		System.out.println("Informe o numero da opcao desejada:");
		Integer option = Integer.parseInt(input.nextLine());
		
		
		while(option < 0 || option > 5 || (option != 1 && !( activeUser instanceof Responsible )) || (option == 5 && !( activeUser instanceof Manager ))) {
			System.out.println("Opcao invalida, digite novamente:");
			option = Integer.parseInt(input.nextLine());
		}
		
		return option;
	}
	
	private int choose(String statement, int inferiorLimit, int upperLimit) {
		
		int option;
		
		System.out.println();
		
		System.out.println(statement);
		option = Integer.parseInt(input.nextLine());
		
		while(option < inferiorLimit || option > upperLimit) {
			System.out.println("Opcao invalida, informe novamente:");
			option = Integer.parseInt(input.nextLine());
		}
		
		return option;
		
	}
	
	private void consultUser() {
		
		System.out.println("Informe o nome de usuario ou email do usuario desejado:");
		String id = input.nextLine();
		User user = academicUnit.getUserById(id);
		
		if(user instanceof NullUser) {
			System.out.println("Foi encontrado nenhum usuario!");
		}else {
			
			System.out.println(user);
			
			if(user instanceof Responsible) {
				Responsible responsible = (Responsible) user;
				
				String message = "\nHistorico de Atividades Realizadas";
				String message_to_empty_list = "Este usuario possui nenhuma atividade realizada!\n";
				
				iterate(responsible.getActivities(), message, message_to_empty_list);
				
				message = "\nHistorico de Recursos Alocados";
				message_to_empty_list = "Este usuario possui nenhum recurso alocado!\n";
				
				iterate(responsible.getAllocatedResources(), message, message_to_empty_list);
				
			}
		}
	}
	
	private <E> void iterate(ArrayList<E> list, String message, String message_to_empty_list) {
		
		if(list.size() > 0){
			
			System.out.println(message);
			for(E object : list) {
				
				System.out.println(object);
				
			}
		}else {
			System.out.println(message_to_empty_list);
		}
		
	}
	
	public void consultResource() {

		System.out.println("Informe a identificacao do recurso:");
		String identification = input.nextLine();
		
		Resource resource = academicUnit.getResourceById(identification);
		
		System.out.println(resource);
		
		if(resource instanceof ResourceStandard) {
		
			System.out.println("Responsaveis pelo Recurso");
			
			for(User user : resource.getResponsibles())
				System.out.println("Nome: " + user.getName() + "\tE-mail: " + user.getEmail());
			
			System.out.println("Atividades relacionadas ao recurso:");
			
			for(Allocation allocation : academicUnit.getListAllocations()) {
				
				for(Resource currentResource : allocation.getListResources()) {
					
					if(currentResource.equals(resource)) 
						System.out.println(allocation.getActivity());
					
				}
			}
		}
	}
	
	public void registerUser() {
		
		int option;
		
		if(activeUser instanceof Manager) {
			System.out.println("\tCADASTRAR USUARIO\n\n");
			System.out.println("Informe o nome de usuario desejado:");
			String username = input.nextLine();
			System.out.println("Informe a senha desejada:");
			String password = input.nextLine();
			System.out.println("Informe o nome completo do usuario: ");
			String name = input.nextLine();
			System.out.println("Informe o email do usuario");
			String email = input.nextLine();
			
			
			option = choose("Informe o tipo de usuario: \n\t1.ALUNO DE GRADUACAO\n\t2.ALUNO DE MESTRADO"
					+ "\n\t3.ALUNO DE DOUTORADO\n\t4.PROFESSOR\n\t5.PESQUISADOR\n\t6.ADMINISTRADOR", 1, 6);
			
			User user = new NullUser();
			
			switch(option) {
				case 1:
					user = new GraduateStudent(username, password, name, email);
					break;
				case 2:
					user = new MasterStudent(username, password, name, email);
					break;
				case 3:
					user = new PhDStudent(username, password, name, email);
					break;
				case 4:
					user = new Teacher(username, password, name, email);
					break;
				case 5:
					user = new Researcher(username, password, name, email);
					break;
				case 6:
					user = new Manager(username, password, name, email);
					break;
			}
			
			academicUnit.addUser(user);
			
		}else {
			System.out.println("Voce precisa ser administrador para cadastrar novos usuarios.");
		}
		
	}

	public void registerActivity() {
		
		
		int option;
		
		System.out.println("Informe o nome da atividade:");
		String name = input.nextLine();
		System.out.println("Escreva uma breve descricao da atividade");
		String description = input.nextLine();
		
		option = choose("Informe a opcao do material de apoio: (1 - Codigo Fonte, 2 - Arquivo de Apresentacao)", 1, 2);
		
		SupportMaterial material;
		
		if(option == 1) {
			material = new SourceCode();
		}else {
			material = new PresentationFile();
		}
		
		ArrayList<User> listParticipants = new ArrayList<User>();
		boolean flag = false;
		String id;
		while(!flag) {

			System.out.println("Informe o nome de usuario ou email do participante:");
			id = input.nextLine();
			
			User member = academicUnit.getUserById(id);
			
			if(member instanceof UserStandard) {
				if(member.getUsername().equals(activeUser.getUsername())) {
					System.out.println("Voce nao pode se adicionar como participante");
				}else {
					listParticipants.add(member);
				}		
			}else {
				System.out.println("Nao foi possivel encontrar nenhum usuario com a informacao fornecida!");
			}
			
			option = choose("Deseja continuar adicionando novos participantes?\n\t1.SIM\n\t2.NAO", 1, 2);
			
			if(option == 2) {
				flag = true;
			}
		}
		
		option = choose("Informe o tipo da atividade:\n\t1.AULA TRADICIONAL\n\t2.APRESENTACAO\n\t3.LABORATORIO", 1, 3);
		
		Activity activity = new NullActivity();
		
		if(option == 1) {
			activity = new TradicionalClass(name, description, activeUser, listParticipants, material);
		}else if(option == 2){
			activity = new Presentation(name, description, activeUser, listParticipants, material);
		}else {
			activity = new Laboratory(name, description, activeUser, listParticipants, material);
		}
		
		academicUnit.addActivity(activity, activeUser);
		
	}
	
	public void registerResource() {
		
		if(activeUser instanceof Responsible) {
			Responsible responsible = (Responsible) activeUser;
			
			if(!responsible.checkResourceInProgress()) {
				System.out.println("Informe a identificacao do recurso:");
				String identification = input.nextLine();
				
				System.out.println("Informe o data de inicio do recurso no formato (dd/mm/aaaa):");
				String initialDateStr = input.nextLine();
				
				while(isValidDate(initialDateStr) == null) {
					System.out.println("Formato invalido, informe a data no formato (dd/mm/aaaa):");
					initialDateStr = input.nextLine();
				}
				
				Calendar initialDate = isValidDate(initialDateStr);
				
				System.out.println("Informe a hora de inicio do recurso no formato (HH:MM):");
				String initialHourStr = input.nextLine();
				
				while(!isValidHour(initialHourStr,initialDate)) {
					System.out.println("Formato invalido, informe a hora no formato (HH:MM):");
					initialHourStr = input.nextLine();
				}
				
				System.out.println("Informe o data de termino do recurso no formato (dd/mm/aaaa):");
				String finishDateStr = input.nextLine();
				
				while(isValidDate(finishDateStr) == null) {
					System.out.println("Formato invalido, informe a data no formato (dd/mm/aaaa):");
					finishDateStr = input.nextLine();
				}
				
				Calendar finishDate = isValidDate(finishDateStr);
				
				System.out.println("Informe a hora de termino do recurso no formato (HH:MM):");
				String finishHourStr = input.nextLine();
				
				while(!isValidHour(finishHourStr,finishDate)) {
					System.out.println("Formato invalido, informe a hora no formato (HH:MM):");
					finishHourStr = input.nextLine();
				}
				
				int option = choose("Informe o tipo do recurso:\n\t1.LABORATORIO\n\t2.AUDITORIO\n\t3.SALA DE AULA\n\t4.PROJETOR", 1, 4);
				
				ArrayList<Responsible> listResponsible = new ArrayList<Responsible>();
				listResponsible.add(responsible);
				String responsibleId = "";
				
				while(responsibleId.length() <= 0) {
					System.out.println("Informe o nome de usuario ou email do responsavel:");
					responsibleId = input.nextLine();
					
					User user = academicUnit.getUserById(responsibleId);
					
					if(user instanceof UserStandard) {
						if(user instanceof Responsible) {
							boolean flag = true;
							for(Responsible iterator : listResponsible) {
								if(iterator.getUsername().equals(user.getUsername())) {
									System.out.println("Este usuario ja esta como responsavel");
									flag = false;
									break;
								}
							}
							if(flag) {
								listResponsible.add((Responsible) user);
								System.out.println("Usuario adicionado a lista de responsaveis!");
							}
						}else {
							System.out.println("Este usuario nao pode ser responsavel");
						}
						
						option = choose("Deseja continuar adicionando responsaveis?\n\t1.SIM\n\t2.NAO", 1, 2);
						
						if(option == 1)
							responsibleId = "";
					}else {
						System.out.println("Nao foi possivel encontrar um usuario com as informacoes fornecidas");
					}
				}
				
				Resource resource = new NullResource();
				switch(option) {
					case 1:
						resource = new LaboratoryRoom(identification, initialDate, finishDate,listResponsible);
						break;
					case 2:
						resource = new Auditorium(identification, initialDate, finishDate, listResponsible);
						break;
					case 3:
						resource = new Classroom(identification, initialDate, finishDate, listResponsible);
						break;
					case 4:
						resource = new Projector(identification, initialDate, finishDate, listResponsible);
						break;
				}
				academicUnit.addResource(resource,responsible);
				
			}else {
				System.out.println("Ja possui recursos em estado de andamento");
			}
		}else {
			System.out.println("Nao e possivel criar um novo recurso");
		}
		
	}
	
	public void changeAllocationStatus() {
		
		System.out.println("Informe o nome da atividade que voce deseja alterar o status");
		String id = input.nextLine();
		
		for(Allocation allocation : academicUnit.getListAllocations()) {
			if(allocation.getActivity().getName().equals(id)) {
				switch(allocation.getStatus()) {
					case ALLOCATION_PROCESS:
						if(activeUser.getClass() == Manager.class && allocation.getActivity().getName() != null && allocation.getActivity().getListParticipants().size() > 0) {
							allocation.setStatus(AllocationType.ALLOCATED);
							System.out.println("Status da alocacao alterado com sucesso");
						}else {
							System.out.println("Nao foi possivel alterar o status!");
						}
						
						break;
					case ALLOCATED:
						for(Resource resource : allocation.getListResources()) {
							for(User user : resource.getResponsibles()) {
								if(user.getUsername().equals(activeUser.getUsername())) {
									allocation.setStatus(AllocationType.PROGRESS);
									System.out.println("Status da alocacao alterado com sucesso");
									break;
								}
							}
						}
						break;
					case PROGRESS:
						if(activeUser.getClass() == Manager.class && allocation.getActivity().getDescription().length() > 0) {
							allocation.setStatus(AllocationType.ACCOMPLISHED);
							System.out.println("Status da alocacao alterado com sucesso");
						}else {
							System.out.println("Nao foi possivel alterar o status dessa alocacao");
						}
						
						break;
					case ACCOMPLISHED:
						System.out.println("Alocacao ja foi concluida");
						break;
				}
			}
		}

	}
	
	public void addResourceInActivity() {
		Scanner input = new Scanner(System.in);
		boolean flag = false;
		
		if(activeUser.getClass().getSuperclass() == Responsible.class) {
			Responsible responsible = (Responsible) activeUser;
			
			System.out.println("Informe a identificacao do recurso");
			String identification = input.nextLine();
			for(Resource resource : responsible.getAllocatedResources()) {
				if(resource.getIdentification().equals(identification)) {
					System.out.println("Informe o nome da atividade");
					identification = input.nextLine();
					
					for(Allocation allocation : responsible.getAllocations()) {
						if(allocation.getActivity().getName().equals(identification)) {
							allocation.getListResources().add(resource);
							flag = true;
							break;
						}
					}
				}
				
			}
				
			if(flag) {
				System.out.println("Recurso adicionado na atividade com sucesso!");
			}else {
				System.out.println("Nao foi possivel concluir essa opcao");
			}
		}else {
			System.out.println("Acesso negado!");
		}
		
	}
	
	public void report() {
		System.out.println("\tRELATORIO\n");
		
		System.out.println("Numero de usuarios: " + academicUnit.getQuantityUsers());
		academicUnit.printQuantityResources();
		System.out.println("Numero total de alocacoes: " + academicUnit.getQuantityAllocations());
		academicUnit.printQuantityActivities();
	}

	
	private Calendar isValidDate(String date) {
		//Data : 24/10/1996
		
		if(date.length() == 10) {
			if(date.charAt(2) == '/' && date.charAt(5) == '/') {
				Integer day = Integer.parseInt(date.substring(0, 2));
				Integer month = Integer.parseInt(date.substring(3, 5));
				Integer year = Integer.parseInt(date.substring(6));
				if(day <= 31 && month <= 12) {
					Calendar newDate = Calendar.getInstance();
					newDate.set(year, month, day);
					
					return newDate;
				}
			}
		}
			
		return null;
	}
	
	private boolean isValidHour(String hourOfDay, Calendar date) {
		//Hora : 12:00
		
		if(hourOfDay.length() == 5) {
			if(hourOfDay.charAt(2) == ':') {
				Integer hour = Integer.parseInt(hourOfDay.substring(0, 2));
				Integer minute = Integer.parseInt(hourOfDay.substring(3));
				
				if(hour >= 0 && hour <= 23 && minute >= 0 && minute <= 59) {
					date.set(Calendar.HOUR, hour);
					date.set(Calendar.MINUTE, minute);
					return true;
				}
			}
		}
		
		return false;
	}

}

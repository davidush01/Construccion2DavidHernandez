package app.controller;
import java.sql.Date;

import app.controller.validator.PartnerValidator;
import app.controller.validator.PersonValidator;
import app.controller.validator.UserValidator;
import app.dto.PartnerDto;
import app.dto.PersonDto;
import app.dto.UserDto;
import app.service.Service;
import app.service.interfaces.AdminService;

public class AdminController implements ControllerInterfaces{
	private PersonValidator personValidator;
	private UserValidator userValidator;
	private PartnerValidator partnerValidator;
	private Service service;
	private static final String MENU = "ingrese la opcion que desea \n 1.para crear socio \n 2. para ver historial de facturas \n 3. para la promocion de facturas \n 4. para cerrar sesion \n";
	
	public AdminController() {
		super();
		this.personValidator = new PersonValidator();
		this.userValidator = new UserValidator ();
		this.partnerValidator = new PartnerValidator();
		this.service = new Service();
	}
	public void session() throws Exception {
		boolean session = true;
		while (session) {
			session = menu();
		}

	}
	
	private boolean menu() {
		try {
			System.out.println("bienvenido " + Service.user.getUserName());
			System.out.print(MENU);
			String option = Utils.getReader().nextLine();
			return options(option);

		} catch (

		Exception e) {
			System.out.println(e.getMessage());
			return true;
		}
		
		
	}
	
	private boolean options(String option) throws Exception{
		switch (option) {
		case "1": {
			this.createPartner();
			return true;
		}
		case "2": {
			this.invoiceHistory();
			System.out.println("");
			return true;
		}
		case "3": {
			this.vipPromotion();
			System.out.println("");
			return true;
		}
		
		case "4":
			System.out.println("se ha cerrado sesion");
			return false;
		default: {
			System.out.println("ingrese una opcion valida");
			return true;
		}
		}
	}
	
	
	 private void createPartner() throws Exception{
	        System.out.println("ingrese el nombre del socio");
	        String name = Utils.getReader().nextLine();
		personValidator.validName(name);
		System.out.println("ingrese la cedula del socio");
	        long document = personValidator.validDocument(Utils.getReader().nextLine());
		System.out.println("ingrese el numero del socio");
		long cellPhone = personValidator.validCellphone(Utils.getReader().nextLine());
		System.out.println("ingrese el nombre de usuario del socio");
		String userName = Utils.getReader().nextLine();
		userValidator.validUserName(userName);
		System.out.println("ingrese la contraseña del socio");
		String password = Utils.getReader().nextLine();
		userValidator.validPassword(password);
	        
		PersonDto personDto = new PersonDto();
		personDto.setName(name);
		personDto.setDocument(document);
		personDto.setCellphone(cellPhone);
		UserDto userDto = new UserDto();
		userDto.setPersonId(personDto);
		userDto.setUserName(userName);
		userDto.setPassword(password);
		userDto.setRole("partner");          
	        PartnerDto partnerDto = new PartnerDto();
	        partnerDto.setUserTol(userDto);
	        partnerDto.setType(true);
	        partnerDto.setAmount(50000);
	        partnerDto.setCreationDate(new Date(System.currentTimeMillis()));
	        this.service.createPartner(userDto);
		System.out.println("se ha creado el usuario exitosamente");
	    }  
	
	
	public void invoiceHistory() {
		
	}
	
	
	public void vipPromotion() {
		
	}



}

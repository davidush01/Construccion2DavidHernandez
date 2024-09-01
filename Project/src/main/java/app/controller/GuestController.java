package app.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import app.controller.validator.DetailValidator;
import app.controller.validator.GuestValidator;
import app.controller.validator.InvoiceValidator;
import app.controller.validator.PartnerValidator;
import app.controller.validator.PersonValidator;
import app.dao.PersonDaoImplementation;
import app.dao.interfaces.PersonDao;
import app.dto.GuestDto;
import app.dto.InvoiceDetailDto;
import app.dto.InvoiceDto;
import app.dto.PartnerDto;
import app.dto.UserDto;
import app.service.Service;
import app.service.interfaces.GuestService;

public class GuestController implements ControllerInterfaces {
	private PersonValidator personValidator;
	private PartnerValidator partnerValidator;
	private InvoiceValidator invoiceValidator;
	private DetailValidator detailValidator;
	private GuestValidator guestValidator;
	private GuestService  service;
	private PersonDao personDao;
	private static final String MENU = "Ingrese la opcion la accion que desea hacer \n 1. para crear factura. \n 2. para cerrar sesion";
	
	Service partnerDto = new Service();
	
	@Override
	public void session() throws Exception {
		boolean session = true;
		while (session) {
			session = guestSession();
		}

	}
	
	private boolean guestSession() {
		try {
			System.out.println(MENU);
			String option = Utils.getReader().nextLine();
			return menu(option);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return true;
		}
		
		
	}
	
	private boolean menu(String option) throws Exception {
		switch (option) {
		case "1": {
			this.createInvoiceGuest();
			return true;
		}
		case "2": {
			System.out.println("se ha cerrado sesion");
			return false;
		}
		default: {
			System.out.println("opcion invalida");
			return true;
		}
		}
		
		

	}
	
	public GuestController() {
		this.personValidator = personValidator;
		this.partnerValidator = partnerValidator;
		this.invoiceValidator = invoiceValidator;
		this.service = service;
		this.personDao= new PersonDaoImplementation();
		this.service = (GuestService) new Service ();
	}
	
	
	public void createInvoiceGuest() throws Exception {

        System.out.println("Ingrese el monto total de la factura");
        double totalAmount = invoiceValidator.validAmount(Utils.getReader().nextLine());

        
        InvoiceDto invoiceHeader = new InvoiceDto();
        invoiceHeader.setCreationDate(new Date(System.currentTimeMillis()));
        invoiceHeader.setAmount(totalAmount);
        invoiceHeader.setStatus("Sin pagar");

        System.out.println("Ingrese la cantidad de ítems de la factura");
        int itemCount = Integer.parseInt(Utils.getReader().nextLine());
        InvoiceDetailDto[] invoiceDetails = new InvoiceDetailDto[itemCount];

        for (int i = 0; i < itemCount; i++) {
 

            System.out.println("Ingrese la descripción del ítem " + (i + 1));
            String itemDescription = Utils.getReader().nextLine();

            System.out.println("Ingrese el valor del ítem " + (i + 1));
            double itemValue = invoiceValidator.validAmount(Utils.getReader().nextLine());

            InvoiceDetailDto detail = new InvoiceDetailDto();
            detail.setDescription(itemDescription);
            detail.setAmount(itemValue);
            invoiceDetails[i] = detail;
            
            /*Service invoiceService = new Service(databaseConnection); // `databaseConnection` es tu conexión a la DB
            invoiceService.saveInvoice(invoiceHeader, invoiceDetails);

            System.out.println("Factura y detalles guardados exitosamente.");*/
    	
        }
        
        
        
	
	
}
	public void promoteGuestToPartner(String guestDocument) throws Exception {

	    GuestDto guest = service.findGuestByDocument(guestDocument);
	    
	    if (guest == null) {
	        throw new Exception("Invitado no encontrado.");
	    }
	    
	    
	    
	  
	    /*guest.setStatus(false); 
	   
	    GuestDto GuestDto = new GuestDto();
	    PartnerDto partnerDto = new PartnerDto();
	    UserDto UserDto = new UserDto();
	    partnerDto.setName(GuestDto.getName());
	    partnerDto.setDocument(GuestDto.getDocument());
	    partnerDto.setCelphone(GuestDto.getCelphone());
	    UserDto.setRole("partner");*/

	   
	    service.convertGuestToPartner();

	    System.out.println("El invitado ha sido promovido a socio exitosamente.");
	}

	
	
	


}

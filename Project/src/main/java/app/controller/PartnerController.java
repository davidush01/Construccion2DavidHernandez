package app.controller;

import java.util.List;
import java.sql.Date;

import app.controller.validator.DetailValidator;
import app.controller.validator.InvoiceValidator;
import app.controller.validator.PartnerValidator;
import app.controller.validator.PersonValidator;
import app.controller.validator.GuestValidator;
import app.dto.GuestDto;
import app.dto.InvoiceDetailDto;
import app.dto.InvoiceDto;
import app.dto.PartnerDto;
import app.dto.PersonDto;
import app.dto.UserDto;
import app.service.Service;
import app.service.interfaces.AdminService;
import app.service.interfaces.GuestService;

public class PartnerController implements ControllerInterfaces{
	private PersonValidator personValidator;
	private PartnerValidator partnerValidator;
	private InvoiceValidator invoiceValidator;
	private DetailValidator detailValidator;
	private GuestValidator guestValidator;
	private Service Service;
	
	private static final String MENU = "Ingrese la opcion la accion que desea hacer \n 1. para crear invitado. \n 2. para activar invitado \n 3. para desactivar invitado \n 4. para solicitar baja \n 5. para hacer consumo \n 6. para cerrar sesion \n";

	Service service = new Service();
	@Override
	public void session() throws Exception {
		boolean session = true;
		while (session) {
			session = PartnerSession();
		}

	    }
	
	private boolean PartnerSession() {
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
			this.createGuest();
			return true;
		}
		case "2": {
			this.activateGuest();
			return true;
		}
		case "3": {
			this.disableGuest();
			return true;
		}
		case "4": {
			this.demotePartnerToGuest(option);;
			return true;
		}
		case "5": {
			this.createInvoice();
			return true;
		}
		default: {
			System.out.println("opcion invalida");
			return true;
		}
		}

	    }
	
	public PartnerController() {
		this.personValidator = personValidator;
		this.partnerValidator = partnerValidator;
		this.invoiceValidator = invoiceValidator;
		this.guestValidator = guestValidator;
		this.service = service;
		this.service =  new Service ();
	    }
	
	
	public void createInvoice() throws Exception {

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
        }
	
	
	
        }
	
	public void createGuest ()throws Exception  {
		System.out.println("ingrese el nombre del invitado");
		String name = Utils.getReader().nextLine();
		personValidator.validName(name);
		System.out.println("ingrese el documento del invitado");
		String document = Utils.getReader().nextLine();
		personValidator.validDocument(document);
		System.out.println("ingrese el numero celular del invitado");
		String celphone = Utils.getReader().nextLine();
		personValidator.validDocument(celphone);
		System.out.println("ingrese el estado de la invitacion (activa o inactiva)");
		boolean status = Utils.getReader().hasNextBoolean();
		guestValidator.validStatus(status);
		GuestDto guestDto = new GuestDto();
		PersonDto personDto = new PersonDto();
		UserDto userDto = new UserDto();
		personDto.setName(name);
		personDto.setCellphone(0);
		personDto.setDocument(0);
		userDto.setRole("Guest");
		this.service.createbecomepartner(guestDto);
		System.out.println("se ha creado el usuario exitosamente");
		
	}
	
	public void demotePartnerToGuest(String document) throws Exception {
        List<InvoiceDto> partnerInvoices = service.getInvoicesByGuest(document);
        for (InvoiceDto invoice : partnerInvoices) {
            if ("Sin pagar".equals(invoice.getStatus())) {
                throw new Exception("El socio tiene facturas pendientes y no puede ser dado de baja.");
            }
        }

        PartnerDto partner = service.findPartnerByDocument(document);
        if (partner == null) {
            throw new Exception("Socio no encontrado.");
        }

       
        service.deleteInvoicesByGuest(document);

        
        service.deleteGuestByPartner(partner);

     
        service.deletePartnerData(partner);

        System.out.println("El socio ha sido dado de baja y convertido en invitado.");
    }
	private void incrementAmount() throws Exception{
        System.out.println("Ingrese el monto que desea aumentar");
            double amount = Utils.getReader().nextDouble();
     
            PartnerDto partnerDto = new PartnerDto();
            partnerDto.setAmount(amount);
    }
	private void disableGuest()throws Exception{
        System.out.println("desactivar invitado");
        System.out.println("numero de cedula del invitado");
        long document = personValidator.validDocument(Utils.getReader().nextLine());
        this.service.disableGuest(document);
    }
	private void activateGuest()throws Exception{
        System.out.println("desactivar invitado"); 
    }
}
	

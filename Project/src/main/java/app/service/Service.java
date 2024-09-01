package app.service;
import java.sql.PreparedStatement;
import app.dao.GuestDaoImplementation;
import java.util.Iterator;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import app.dao.PersonDaoImplementation;
import app.dao.UserDaoImplementation;
import app.dao.PartnerDaoImplementation;
import app.dao.interfaces.GuestDao;
import app.dao.interfaces.PersonDao;
import app.dao.interfaces.UserDao;
import app.dao.interfaces.PartnerDao;
import app.dto.GuestDto;
import app.dto.InvoiceDetailDto;
import app.dto.InvoiceDto;
import app.dto.PartnerDto;
import app.dto.PersonDto;
import app.dto.UserDto;
import app.service.interfaces.AdminService;
import app.service.interfaces.LoginService;
import app.service.interfaces.PartnerService;
import app.service.interfaces.GuestService;

public class Service implements LoginService, AdminService, GuestService {
	private UserDao userDao;
	private PersonDao personDao;
	private Connection connection;
	private GuestDao guestDao;
	private PartnerDao partnerDao;
	public static UserDto user;
	
    private List<GuestDto> guests = new ArrayList<>();
    private List<InvoiceDto> invoices = new ArrayList<>();
    private List<PartnerDto> partners = new ArrayList<>(); 
    //private List<PersonDto> Person = new ArrayList<>(); 
	public Service()  {
		this.userDao = new UserDaoImplementation();
		this.personDao= new PersonDaoImplementation();
		this.connection = connection;
		this.guestDao = new GuestDaoImplementation();
		this.partnerDao = new PartnerDaoImplementation();
		
	}
	
	

   /* public PersonDto findByDocument(String document) {
        for (PersonDto Person : Person) {
            if (Person.getId() != null && document.equals(Person.getId())) {
                return Person;
            }
        }
        return null; 
    }*/
    

    public List<InvoiceDto> getInvoicesByGuest(String document) {
        List<InvoiceDto> result = new ArrayList<>();
        for (InvoiceDto invoice : invoices) {
            if (invoice.getPersonId() != null && document.equals(invoice.getPersonId().getDocument())) {
                result.add(invoice);
            }
        }
        return result;
    }
    
 
    public void convertGuestToPartner()throws Exception {
    	PersonDto personDto = personDao.findById(user.getPersonId());
    	List<InvoiceDto> invoices = getInvoicesByGuest(String.valueOf(personDto.getDocument()));
	    boolean hasPendingInvoices = invoices.stream() //es una librería que facilita mucho el trabajo con collections, como List y Sets, y consiste en solo llamar un método. Luego de esto se llama al resto de las funciones.
	                                         .anyMatch(invoice -> "Sin pagar".equals(invoice.getStatus()));
	                                           //devuelve true si al menos un elemento (cualquiera) en el flujo coincide con el predicado proporcionado, de lo contrario, false . El false también se devuelve si el flujo está vacío
	    if (hasPendingInvoices) {
	        throw new Exception("El invitado tiene facturas pendientes y no puede ser promovido a socio.");
	    }
	    GuestDto guestDto = new GuestDto();
	    guestDto.setUserId(user);
	    guestDto = guestDao.byUserId(guestDto);
	    guestDto.setStatus(false);
	    PartnerDto partnerDto = new PartnerDto();
        partnerDto.setUserTol(user);
        partnerDto.setCreationDate(new Date(System.currentTimeMillis()));
        partnerDto.setType(false);
        partnerDto.setAmount(50000);
        //crear partner Dao e insertar el nuevo socio

  
    }
    
    public PartnerDto findPartnerByDocument(String document) {
        for (PartnerDto partner : partners ) {
            if (partner.getUserTol() != null && document.equals(partner.getUserTol())) {
                return partner;
            }
        }
        return null;
    }
    
    /*public List<InvoiceDto> getInvoicesByGuest(String document) {
        List<InvoiceDto> result = new ArrayList<>();
        for (InvoiceDto invoice : invoices) {
            if (invoice.getPersonId() != null && document.equals(invoice.getPersonId().getDocument())) {
                result.add(invoice);
            }
        }
        return result;
    }*/
    
    public void deleteInvoicesByGuest(String document) {
        Iterator<InvoiceDto> iterator = invoices.iterator();
        while (iterator.hasNext()) {
            InvoiceDto invoice = iterator.next();
            if (invoice.getPersonId() != null && document.equals(invoice.getPersonId().getDocument())) {
                iterator.remove(); 
            }
        }
    }
    
    public void deleteGuestByPartner(PartnerDto partner) {
        Iterator<GuestDto> iterator = guests.iterator();
        while (iterator.hasNext()) {
            GuestDto guest = iterator.next();
            if (guest.getPartnerId() != null && guest.getPartnerId().getId() == partner.getId()) {
                iterator.remove(); 
            }
        }
    }
    
    public void deletePartnerData(PartnerDto partner) {
        partners.remove(partner);
        
    }



	
	@Override
	public void createbecomepartner(GuestDto guestDto) throws Exception {
		
	}
	@Override
	public void createPartnerRegistration(PartnerDto partnerdto) throws Exception {
		
		
	}
	@Override
	public void createViewInvoiceHistory(UserDto userDto) throws Exception {
		
		
	}
	@Override
	public void createVIPPromotion(UserDto userDto) throws Exception {
		
		
	}
	@Override
	public void login(UserDto userDto) throws Exception {
		UserDto validateDto = userDao.findByUserName(userDto);
		if (validateDto == null) {
			throw new Exception("no existe usuario registrado");
		}
		if (!userDto.getPassword().equals(validateDto.getPassword())) {
			throw new Exception("usuario o contraseña incorrecto");
		}
		userDto.setRole(validateDto.getRole());
		user = validateDto;
	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub
		
	}
	private void createUser(UserDto userDto) throws Exception{
		this.createPerson(userDto.getPersonId());
		userDto.setPersonId(personDao.findByDocument(userDto.getPersonId()));
		if(this.userDao.existsByUserName(userDto)) {
			this.personDao.deletePerson(userDto.getPersonId());
			throw new Exception("ya existe un usuario con ese user name");
		}
		this.userDao.createUser(userDto);
	}
	private void createPerson(PersonDto personDto)throws Exception{
		if(this.personDao.existsByDocument(personDto)) {
			throw new Exception("ya existe una persona con ese documento");
		}
		this.personDao.createPerson(personDto);
		this.partnerDao.createPartner(null);
	}



	@Override
	public void createGuest(PartnerDto partnerdto) throws Exception {
		// TODO Auto-generated method stub
		
	}



	@Override
	public GuestDto findGuestByDocument(String guestDocument) {
		// TODO Auto-generated method stub
		return null;
	}

    public void disableGuest(long document) throws Exception {
        GuestDto guestDto = new GuestDto();
        guestDto.setStatus(false);
    }
    
    /*public void saveInvoice(InvoiceDto invoice, InvoiceDetailDto[] invoiceDetails) throws SQLException {
        String insertInvoiceSQL = "INSERT INTO invoices (creation_date, amount, status) VALUES (?, ?, ?)";
        String insertDetailSQL = "INSERT INTO invoice_details (invoice_id, description, amount) VALUES (?, ?, ?)";

        try (PreparedStatement invoiceStmt = connection.prepareStatement(insertInvoiceSQL, new String[]{"id"});
             PreparedStatement detailStmt = connection.prepareStatement(insertDetailSQL)) {

            // Guardar la factura
            invoiceStmt.setDate(1, new java.sql.Date(invoice.getCreationDate().getTime()));
            invoiceStmt.setDouble(2, invoice.getAmount());
            invoiceStmt.setString(3, invoice.getStatus());
            invoiceStmt.executeUpdate();

            // Obtener el ID de la factura generada
            try (var generatedKeys = invoiceStmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    long invoiceId = generatedKeys.getLong(1);

                    // Guardar los detalles de la factura
                    for (InvoiceDetailDto detail : invoiceDetails) {
                        detailStmt.setLong(1, invoiceId);
                        detailStmt.setString(2, detail.getDescription());
                        detailStmt.setDouble(3, detail.getAmount());
                        detailStmt.executeUpdate();
                    }
                } else {
                    throw new SQLException("No se pudo obtener el ID de la factura generada.");
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al guardar la factura: " + e.getMessage(), e);
        }
    }*/
    
    public void createPartner(UserDto userDto) throws Exception {
        this.createUser(userDto);
       // this.createPartners(partnerDto);
    }
    /*private void createPartners(PartnerDto partnerDto) throws Exception{
		this.createPartner(partnerDto.getUserTol());
		partnerDto.setUserTol(userDao.FindByUserName(partnerDto.getUserTol()));
		if(this.partnerDao.existsByUserId(partnerDto)) {
			this.userDao.DeleteUser(user);
			throw new Exception("ya existe un usuario con ese user name");
		}
		this.partnerDao.CreatePartner(partnerDto);
	}
    
    private void createPartnerss (PersonDto partnerDto)throws Exception{
		if(this.partnerDao.existsByUserId(partnerDto)) {
			throw new Exception("ya existe una persona con ese documento");
		}
		this.partnerDao.createPartner(partnerDto);
	}*/


  
    
}

    


	



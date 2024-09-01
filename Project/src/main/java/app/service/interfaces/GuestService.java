package app.service.interfaces;

import java.util.List;

import app.dto.GuestDto;
import app.dto.InvoiceDto;
import app.dto.PartnerDto;

public interface GuestService {
	public void createbecomepartner(GuestDto guestDto) throws Exception;

	public GuestDto findGuestByDocument(String guestDocument);

	public List<InvoiceDto> getInvoicesByGuest(String guestDocument);

	public void convertGuestToPartner() throws Exception;


}

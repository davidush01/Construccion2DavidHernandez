package app.dao.interfaces;

import app.dto.GuestDto;
import app.dto.PartnerDto;
import app.dto.UserDto;

public interface GuestDao {
	public long byId (GuestDto GuestDto)throws Exception;
	public GuestDto byUserId(GuestDto GuestDto)throws Exception;
	public PartnerDto byPartnerId (GuestDto GuestDto)throws Exception;
	public boolean byStatus (GuestDto GuestDto)throws Exception;
    
	
	public void createGuest(GuestDto GuestDto) throws Exception;
	public void deleteGuest(GuestDto GuestDto) throws Exception;
}

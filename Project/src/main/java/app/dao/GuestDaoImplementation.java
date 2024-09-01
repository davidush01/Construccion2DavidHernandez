package app.dao;

import app.dao.interfaces.GuestDao;
import app.dto.GuestDto;
import app.dto.PartnerDto;

public class GuestDaoImplementation implements GuestDao{

	@Override
	public long byId(GuestDto GuestDto) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public GuestDto byUserId(GuestDto GuestDto) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PartnerDto byPartnerId(GuestDto GuestDto) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean byStatus(GuestDto GuestDto) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void createGuest(GuestDto GuestDto) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteGuest(GuestDto GuestDto) throws Exception {
		// TODO Auto-generated method stub
		
	}

}

package app.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import app.config.MYSQLConnection;
import app.dao.interfaces.PartnerDao;
import app.dto.PartnerDto;
import app.dto.PersonDto;
import app.dto.UserDto;
import app.dao.interfaces.UserDao;
import app.helpers.Helper;
import app.model.Partner;
import app.model.Person;
import app.model.User;

public class PartnerDaoImplementation implements PartnerDao {

	public boolean existsByUserId(PartnerDto partnerDto) throws Exception {
        String query = "SELECT 1 FROM PARTNER WHERE USER_ID = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong(1, partnerDto.getUserTol().getId());
        ResultSet resultSet = preparedStatement.executeQuery();
        boolean exists = resultSet.next();
        resultSet.close();
        preparedStatement.close();
        return exists;
    }

	 @Override
	    public void createPartner(PartnerDto partnerDto) throws Exception {
	        Partner partner = Helper.parse(partnerDto);
	        String query = "INSERT INTO PARTNER(USER_ID, AMOUNT, TYPE, CREATION_DATE) VALUES (?, ?, ?, ?)";
	        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
	        preparedStatement.setLong(1, partner.getUserTol().getId());
	        preparedStatement.setDouble(2, partner.getAmount());
	        preparedStatement.setBoolean(3, partner.isType());
	        preparedStatement.setDate(4, partner.getCreationDate());
	        preparedStatement.execute();
	        preparedStatement.close();
	    }

	 @Override
	    public void deletePartner(PartnerDto partnerDto) throws Exception {
	        Partner partner = Helper.parse(partnerDto);
	        String query = "DELETE FROM PARTNER WHERE USER_ID = ?";
	        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
	        preparedStatement.setLong(1, partner.getUserTol().getId());
	        preparedStatement.execute();
	        preparedStatement.close();
	    }
	 @Override
	    public PartnerDto findById(PartnerDto partnerDto) throws Exception {
	        String query = "SELECT ID, USER_ID, AMOUNT, TYPE, CREATION_DATE FROM PARTNER WHERE ID = ?";
	        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
	        preparedStatement.setLong(1, partnerDto.getId());
	        ResultSet resultSet = preparedStatement.executeQuery();
	        if (resultSet.next()) {
	            Partner partner = new Partner();
	            partner.setId(resultSet.getLong("ID"));
	            //partner.setUserTol(Helper.parseUserDto(resultSet.getLong("USER_ID")));
	            partner.setUserTol(null);
	            partner.setAmount(resultSet.getDouble("AMOUNT"));
	            partner.setType(resultSet.getBoolean("TYPE"));
	            partner.setCreationDate(resultSet.getDate("CREATION_DATE"));
	            resultSet.close();
	            preparedStatement.close();
	            return Helper.parse(partner);
	        }
	        resultSet.close();
	        preparedStatement.close();
	        return null;
	    }
	
	 @Override
	    public PartnerDto findByUserId(PartnerDto partnerDto) throws Exception {
	        String query = "SELECT ID, USER_ID, AMOUNT, TYPE, CREATION_DATE FROM PARTNER WHERE USER_ID = ?";
	        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
	        preparedStatement.setLong(1, partnerDto.getUserTol().getId());
	        ResultSet resultSet = preparedStatement.executeQuery();
	        if (resultSet.next()) {
	            Partner partner = new Partner();
	            partner.setId(resultSet.getLong("ID"));
	            partner.setUserTol(null);
	            partner.setAmount(resultSet.getDouble("AMOUNT"));
	            partner.setType(resultSet.getBoolean("TYPE"));
	            partner.setCreationDate(resultSet.getDate("CREATION_DATE"));
	            resultSet.close();
	            preparedStatement.close();
	            return Helper.parse(partner);
	        }
	        resultSet.close();
	        preparedStatement.close();
	        return null;
	    }

	

	@Override
	public double amount(PartnerDto partnerDto) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	    }

	@Override
	public boolean type(PartnerDto partnerDto) throws Exception {
		// TODO Auto-generated method stub
		return false;
	    }

	@Override
	public Date creationDate(PartnerDto partnerDto) throws Exception {
		// TODO Auto-generated method stub
		return null;
	    }

	@Override
	public UserDto userTol(PartnerDto PartnerDto) throws Exception {
		// TODO Auto-generated method stub
		return null;
	    }

	@Override
	public PersonDto findByDocument(PersonDto personDto) throws Exception {
		// TODO Auto-generated method stub
		return null;
	    }
	


	


	


}

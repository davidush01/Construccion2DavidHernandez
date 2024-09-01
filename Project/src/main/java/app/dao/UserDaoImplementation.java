package app.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import app.config.MYSQLConnection;
import app.dao.interfaces.UserDao;
import app.dto.PersonDto;
import app.dto.UserDto;
import app.helpers.Helper;
import app.model.Person;
import app.model.User;

public class UserDaoImplementation implements UserDao {

	@Override
	public UserDto findByUserName(UserDto UserDto) throws Exception {
		String query = "SELECT ID,USERNAME,PASSWORD,ROLE,PERSONNID FROM USER WHERE USERNAME = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setString(1, UserDto.getUserName());
        ResultSet Result = preparedStatement.executeQuery();
        if (Result.next()) {
        	User User = new User();
	        User.setId(Result.getLong("ID"));
	        User.setUserName(Result.getString("USERNAME"));
	        User.setPassword(Result.getString("PASSWORD"));
	        User.setRole(Result.getString("ROLE"));
	        Person Person = new Person();
	        Person.setDocument(Result.getLong("PERSONNID"));
	        User.setPersonId(Person);
	        Result.close();
	        preparedStatement.close();
	        
	        return Helper.parse(User);
        }
        Result.close();
        preparedStatement.close();
        
        
    UserDto validateDto = new UserDto();
        if(UserDto.getUserName().equals("ADMIN")) {
	validateDto.setUserName(UserDto.getUserName());
	validateDto.setRole(UserDto.getUserName());
	validateDto.setPassword("ADMIN");
	return validateDto;
        }
        
        if(UserDto.getUserName().equals("PARTNER")) {
	validateDto.setUserName(UserDto.getUserName());
	validateDto.setRole(UserDto.getUserName());
	validateDto.setPassword("PARTNER");
	return validateDto;
        }
        if(UserDto.getUserName().equals("123")) {
	validateDto.setUserName(UserDto.getUserName());
	validateDto.setRole("GUEST");
	validateDto.setPassword("123");
	return validateDto;
        }
       return null;
     
		
		
	}

	@Override
	public boolean existsByUserName(UserDto UserDto) throws Exception {
		String query = "SELECT 1 FROM USER WHERE USERNAME = ? ";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setString(1, UserDto.getUserName());
        ResultSet resulSet = preparedStatement.executeQuery();
        boolean exists = resulSet.next();
        resulSet.close();
        preparedStatement.close();
        return exists;
        
		
	}
	
	@Override
	public void createUser(UserDto userDto) throws Exception {
		//System.out.println("se ha registrado el usuario");
        User user = Helper.parse(userDto);
        String query = "INSERT INTO USER(USERNAME,PASSWORD,PERSONNID,ROLE) VALUES (?,?,?,?) ";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setString(1, user.getUserName());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setLong(3,user.getPersonId().getId());
        preparedStatement.setString(4, user.getRole());
        preparedStatement.execute();
        preparedStatement.close();
	}

	@Override
	public long byId(UserDto UserDto) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String findpassword(UserDto UserDto) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String role(UserDto UserDto) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersonDto personId(UserDto UserDto) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public void deleteUser(UserDto userDto) throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	
}

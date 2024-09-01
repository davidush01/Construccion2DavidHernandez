package app.dao.interfaces;

import app.dto.PersonDto;
import app.dto.UserDto;

public interface UserDao {
	public UserDto findByUserName(UserDto UserDto) throws Exception;
	public boolean existsByUserName(UserDto UserDto) throws Exception;
	public long byId (UserDto UserDto)throws Exception;
	public String findpassword (UserDto UserDto)throws Exception;
	public String role (UserDto UserDto)throws Exception;
	public PersonDto personId (UserDto UserDto)throws Exception;
	
	public void createUser(UserDto userDto) throws Exception;
	public void deleteUser(UserDto userDto) throws Exception;
}

package app.dao.interfaces;

import app.dto.PersonDto;

public interface PersonDao {
	public boolean existsByDocument(PersonDto personDto) throws Exception;
	public void createPerson(PersonDto personDto) throws Exception;
	public void deletePerson(PersonDto personDto) throws Exception;
	public PersonDto findById(PersonDto personDto) throws Exception;
	
	public String name (PersonDto personDto)throws Exception;
	public long findCellphone (PersonDto personDto)throws Exception;
	public PersonDto findByDocument(PersonDto personDto) throws Exception;
	
}
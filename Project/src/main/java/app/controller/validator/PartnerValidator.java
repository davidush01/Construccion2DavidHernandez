package app.controller.validator;

public class PartnerValidator extends CommonsValidator {

	public PartnerValidator() {
		super();
		
	}
	public void validName(String name) throws Exception{
		super.isValidString("el nombre de la persona ", name);
	}
	
	public long validDocument(String document) throws Exception{
		return super.isValidLong("la cedula de la persona ", document);
	}
	
	public int validNumber(String number) throws Exception{
		return super.isValidInteger("el numero de telefono es ", number);
	}
	public double validAmount(String amount) throws Exception{
        return super.isValidDouble("La cantidad del socio", amount);
    }
    
	

	

}

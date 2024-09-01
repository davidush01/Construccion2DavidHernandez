package app.controller.validator;

public class DetailValidator extends CommonsValidator{

	public DetailValidator() {
		super();
		
	}
	public long invoiceId(String invoiceId) throws Exception{
		return super.isValidInteger("el id de el detalle es  ", invoiceId);
	}
	
	public int validitem(String item) throws Exception{
		return super.isValidInteger("los items son ", item);
	}
	
	public void validDescription(String description) throws Exception{
		 super.isValidString("el numero de telefono es ", description);
	}
	
	public double amount(String amount) throws Exception{
		return super.isValidDouble("el monto es   ", amount);
	}
	
	

}

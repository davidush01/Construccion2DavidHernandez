package app.controller.validator;

public class InvoiceValidator extends CommonsValidator{

	protected InvoiceValidator() {
		super();
		
	}
	public void validItems(String items)throws Exception {
		super.isValidString("items de la factura", items);
	}
	
	public double validAmount(String amount) throws Exception{
		return super.isValidDouble("el monto de la factura ", amount);
	}
	
	
	public boolean validStatus(double status)throws Exception {
		return super.isValidboolean("la factura es " + status);
	}

	

}

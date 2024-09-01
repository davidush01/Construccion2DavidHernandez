package app;

import app.config.MYSQLConnection;
import app.controller.ControllerInterfaces;
import app.controller.LoginController;

public class App {
	public static void main(String[] args) throws Exception{
		ControllerInterfaces controller = new LoginController();
		try {
			controller.session();
                        MYSQLConnection.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }

}

package app.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import app.service.interfaces.GuestService;
import app.controller.*;
import app.dto.*;

public class MYSQLconection {
	private static final String URL = "jbdc:mysql://localhost:3306/CLUB";
	private static final String USER = "root";
	private static final String PASSWORD = "";
	public static Connection getConnection () {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.driver");
			connection = DriverManager.getConnection(URL,USER,PASSWORD);
			System.out.println("conexion exitosa");
		}catch (ClassNotFoundException | SQLException e ) {
			e.printStackTrace();
		}
		return connection;
	}
	
	


}
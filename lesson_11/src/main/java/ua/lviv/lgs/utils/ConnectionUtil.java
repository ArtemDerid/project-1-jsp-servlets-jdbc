package ua.lviv.lgs.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.xml.DOMConfigurator;

public class ConnectionUtil {
	
	private static String USER_NAME = "artem";
	private static String USER_PASSWORD = "24221513";
	private static String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static String URL =  "jdbc:mysql://localhost/magazine";
	
	

	public static Connection openConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		DOMConfigurator.configure("loggerConfig.xml");
		Class.forName(DRIVER);
		return DriverManager.getConnection (URL, USER_NAME, USER_PASSWORD);
	}

}

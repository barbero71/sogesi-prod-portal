package test.it.sogesispa.prod.web.jdbc;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class OracleJDBC {

	public static void main(String[] argv) {

		System.out.println("-------- Oracle JDBC Connection Testing ------");

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

		} catch (ClassNotFoundException e) {

			System.out.println("Where is your Oracle JDBC Driver?");
			e.printStackTrace();
			return;

		}

		System.out.println("Oracle JDBC Driver Registered!");

		Connection connection = null;

		try {

//			connection = DriverManager.getConnection(
//					"jdbc:oracle:thin:@localhost:1521:xe", "SOGESI",
//					"SOGESI");

			//SOGESI PROD
			connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.1.231:1521:orcl", 
					"sogesi",
					"manager");
			
			
// SISO
//			connection = DriverManager.getConnection(
//					"jdbc:oracle:thin:@172.29.0.73:1521:CAT", "CS_ZS42",
//					"CS_ZS42");

		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;

		}

		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
	}

}
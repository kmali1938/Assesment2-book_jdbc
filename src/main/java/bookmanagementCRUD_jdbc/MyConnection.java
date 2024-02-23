package bookmanagementCRUD_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {

		
		static Connection conn;
		public static Connection getConnection() {
			String connectionUrl="jdbc:mysql://localhost:3306/mydb";
			try {
				conn= DriverManager.getConnection(connectionUrl,"root","abcd1234!");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return conn;
		}

}

package DBsection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB_Link_Driver {

	private static final String URL="jdbc:mysql://localhost:3306/mysql";
	private static final String USER="root";
	private static final String PASSWORD="123";
	
	private static Connection conn=null;
	
	static {
		try {
			//1.������������
			Class.forName("com.mysql.jdbc.Driver");
			//2.������ݿ������
			conn=DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(){
		return conn;
	}
	
}

package projetHopital.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Context {
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	private static DaoPatient daoPatient=new DaoPatientImpl();
	public static DaoPatient getDaoPatient() {
		return daoPatient;
	}

	
	
	
	private static Context singleton=null;
	
	public static Context getContext() {
		if(singleton==null) {
			singleton=new Context();
		}
		return singleton;
	}
	
	
	
	private Connection connection;
	
	
	private Context() {
		try {
			connection=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hopital","root","root123@");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}
	
	
	public static void destroy() {
		if(singleton!=null) {
			singleton.close();
			singleton=null;
		}
	}
	
	
	private void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

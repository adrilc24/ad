package serpis.ad;

import java.sql.Connection;

import javax.persistence.EntityManagerFactory;

public class HApp {
	private static HApp instance = new HApp();
	
	private HApp() {
		
	}
	
	public static HApp getInstance() {
		return instance;
	}
	
	private Connection connection;
	
	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	public EntityManagerFactory getEntityManagerFactory() {
		return null;
	}
}
package serpis.ad;

import java.sql.Connection;

import javax.persistence.EntityManagerFactory;

public class App {
	private static App instance = new App();
	
	private App() {
		
	}
	
	public static App getInstance() {
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
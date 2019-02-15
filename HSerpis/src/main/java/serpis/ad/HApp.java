package serpis.ad;

import java.sql.Connection;

import javax.persistence.EntityManagerFactory;

public class HApp {
	private static HApp instance = new HApp();
	private  EntityManagerFactory entityManagerFactory;
	
	private HApp() {
		
	}
	
	public static HApp getInstance() {
		return instance;
	}
	
	private Connection connection;
	
	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}
	
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}
}
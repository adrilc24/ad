package serpis.ad;

import java.util.function.Consumer;
import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.engine.spi.ExecuteUpdateResultCheckStyle;

public class JpaHelper {
	
	private static void execute(EntityManagerFactory entityManagerFactory, Consumer<EntityManager> consumer) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		consumer.accept(entityManager);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	public static void execute(Consumer<EntityManager> consumer) {
		 execute(HApp.getInstance().getEntityManagerFactory(), consumer);
	}
	
	public static <R> R execute(Function<EntityManager, R> function) {
		return execute(HApp.getInstance().getEntityManagerFactory(), function);
	}
	
	public static <R> R execute(EntityManagerFactory entityManagerFactory, Function<EntityManager, R> function) {
		return null;
	}

}

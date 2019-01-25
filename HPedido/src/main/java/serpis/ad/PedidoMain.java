package serpis.ad;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PedidoMain {
	
	private static EntityManagerFactory entityManagerFactory;

	public static void main(String[] args) {
		entityManagerFactory = Persistence.createEntityManagerFactory("serpis.ad.hpedido");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		List <Categoria> categorias = entityManager.createQuery("select c from Categoria c", Categoria.class).getResultList();
		
		Articulo articulo = newArticulo();
		articulo.setCategoria(categorias.get(new Random().nextInt(categorias.size())));
		
		
		
//		Categoria categoria1 = new Categoria();
//		categoria1.setNombre("cambiado "+ LocalDateTime.now());
		
		//articulo.setCategoria(entityManager.getReference(Categoria.class, 1L));
		
		entityManager.persist(articulo);
		
		//Articulo articulo = entityManager.find(Articulo.class, 1L);
		show(articulo);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
		System.out.println("Añadido articulo. Pulsa enter para seguir...");
		new Scanner(System.in).nextLine();
		
		remove(articulo);
		
		entityManagerFactory.close();
	}
	
	public static Articulo newArticulo() {
		Articulo articulo = new Articulo();
		articulo.setNombre("nuevo "+ LocalDateTime.now());
		articulo.setPrecio(new BigDecimal(1.5));
		return articulo;
	}
	
	private static void show (Articulo articulo) {
		System.out.printf("%4s %-30s %30s %s %n", 
				articulo.getId(), articulo.getNombre(), articulo.getCategoria(), articulo.getPrecio());
	}
	
	private static void remove (Articulo articulo) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		//articulo = entityManager.find(Articulo.class, articulo.getId());
		articulo = entityManager.getReference(Articulo.class, articulo.getId());
		entityManager.remove(articulo);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
	}

}

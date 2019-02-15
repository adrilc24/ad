package serpis.ad;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.cfg.JPAIndexHolder;

public class PedidoMain {
	
	private static EntityManagerFactory entityManagerFactory;

	public static void main(String[] args) {
			
		List <Categoria> categorias = JpaHelper.execute(entityManager -> {
			return entityManager.createQuery("select c from Cartegoria c order by id", Categoria.class).getResultList();
		});
		
		List<Cliente> clientes=JpaHelper.execute(entityManager -> {
			return	entityManager.createQuery("select cl from Cliente cl", Cliente.class).getResultList();
		});
		List<Pedido> pedidos=JpaHelper.execute(entityManager -> {
			return	entityManager.createQuery("select p from Pedido p", Pedido.class).getResultList();
		});
		List<Articulo> articulos=JpaHelper.execute(entityManager -> {
			return	entityManager.createQuery("select a from Articulo a", Articulo.class).getResultList();
		});


			
		JpaHelper.execute(entityManager -> {
			Articulo articulo = new Articulo();
			articulo.setNombre("nuevo "+LocalDateTime.now());
			articulo.setPrecio(new BigDecimal(1.5));
			entityManager.persist(articulo);
		});
		
		JpaHelper.execute(entityManager -> {
			Pedido pedido= new Pedido();
			pedido.setDate(new Date(5));
			pedido.setNum(5);;
		});
		
		JpaHelper.execute(entityManager -> {
			PedidoLinea pedidoLinea=new PedidoLinea();
			pedidoLinea.setPrecio(20);
			pedidoLinea.setUnidades(3);
			pedidoLinea.setImporte(14);
		});
		
//		JpaHelper.execute(entityManager -> {
//			Cliente cliente=new Cliente();
//			cliente.setNombre("Manolo");
//		});
		

	
		System.out.println("Añadido articulo. Pulsa enter para seguir...");
		new Scanner(System.in).nextLine();
		
	
		
		
		Articulo articulo = JpaHelper.execute(entityManager  -> {
			return entityManager.find(Articulo.class, 13L);
		});
		showArticulo(articulo);
		
	
		
		
		
	}
	
	public static Articulo newArticulo() {
		Articulo articulo = new Articulo();
		articulo.setNombre("nuevo "+ LocalDateTime.now());
		articulo.setPrecio(new BigDecimal(1.5));
		return articulo;
	}
	
	public static Cliente newCliente() {
		Cliente cliente = new Cliente();
		cliente.setNombre("nuevo "+ LocalDateTime.now());
		return cliente;
	}
	
	
	private static void showArticulo(Articulo articulo) {
		System.out.printf("%4s %-30s %30s %s %n", 
				articulo.getId(), articulo.getNombre(), articulo.getCategoria(), articulo.getPrecio());
	}
	
	private static void showCliente(Cliente cliente) {
		System.out.printf("%4s %-30s", 
				cliente.getId(), cliente.getNombre());
	}
	
	private static void showPedido(Pedido pedido) {
		System.out.printf("%4s %-30s %30s %s %n", 
				pedido.getDate(), pedido.getNum());
	}
	
	private static void showPedidoLinea(PedidoLinea pedidoLinea) {
		System.out.printf("%4s %-30s %30s %s %n", 
				pedidoLinea.getPrecio(), pedidoLinea.getUnidades(), pedidoLinea.getImporte());
	}
	
	
	private static void remove (Articulo articulo) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		articulo = entityManager.getReference(Articulo.class, articulo.getId());
		entityManager.remove(articulo);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
	}
	
	
	public static void tryCatchFinally() {
		Object item=null;
		EntityManager entityManager=null;
		entityManager = HApp.getInstance().getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		try {
		
		entityManager.persist(item);
		entityManager.getTransaction().commit();
		}catch(Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		}
		finally {
			entityManager.close();
		}
		
	}
	
}

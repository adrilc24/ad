package serpis.ad;

import java.util.*;

public class CategoriaConsole {
	
	public static long getId() {
		Categoria categoria = new Categoria();
		long id;
		id = categoria.getId();
		return id;
	}
	
	public static void newCategoria(Categoria categoria) {
		
	}
	
	public static void editCategoria(Categoria categoria) {
		
	}
	
	public static void idNotExists() {
		Categoria categoria = new Categoria();
		if (categoria == null)
			System.out.println("La categoria no existe");
	}
	
	public static boolean deleteConfirm() {
		String res = "N";
		return res.equalsIgnoreCase("S");
	}
	
	public static void show(Categoria categoria) {
		System.out.printf("%s %s\n", categoria.getId(), categoria.getNombre());
	}
	
	public static void showList(List<Categoria> categorias) {
		for (Categoria categoria : categorias) {
			System.out.printf("%s %s\n", categoria.getId(), categoria.getNombre());
		}
	}
	
	
	
}
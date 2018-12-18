package serpis.ad;

import java.util.*;

public class CategoriaConsole {
	private static Scanner scanner = new Scanner(System.in);
	
	
	public static long getId() {
		return ScannerHelper.getInt("Id: ");
	}
	
	public static void newCategoria(Categoria categoria) {
		System.out.print("Nombre: ");
		String nombre = scanner.nextLine();
		categoria.setNombre(nombre);
	}
	
	public static void editCategoria(Categoria categoria) {
		show(categoria);
		System.out.print("Nombre: ");
		String nombre = scanner.nextLine();
		categoria.setNombre(nombre);
	}
	
	public static void showIdNotExists() {
		System.out.println("La categoria no existe");
	}
	
	public static boolean deleteConfirm() {
		System.out.print("¿Desea eliminar el registro (S/N) ? ");
		String res = scanner.nextLine();
		return res.equalsIgnoreCase("S");
	}
	
	public static void show(Categoria categoria) {

		System.out.printf("   Id: %s || Nombre: %s\n", categoria.getId(), categoria.getNombre());
		System.out.println("");
	}
	
	public static void showList(List<Categoria> categorias) {
		for (Categoria categoria : categorias) {
			System.out.printf("%s %s\n", categoria.getId(), categoria.getNombre());
		}
	}
	
	
	
}
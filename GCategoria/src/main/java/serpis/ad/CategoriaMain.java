package serpis.ad;

import java.util.*;
import java.util.ArrayList;

public class CategoriaMain {

	@FunctionalInterface
	public interface Action {
		void execute();
	}
	
	private static Scanner scanner = new Scanner(System.in);
	private static boolean exit = false;
	public static void main(String[] args) {
		
		
//		Menu.create("Menú Categoría")
//		.exitWhen("0 - Salir")
//		.add("1 - Nuevo", CategoriaMain::nuevo)
//		.add("2 - Editar", CategoriaMain::editar)
//		.loop();
		
		
		List <Action> actions = new ArrayList<>();
		actions.add(  () -> exit = true);
		actions.add(CategoriaMain::nuevo);
		actions.add(CategoriaMain::editar);
		
		while (!exit) {
			System.out.println("0 - Salir\n1 - Nuevo\n2 - Editar\nElige opción: ");
			int option = Integer.parseInt(scanner.nextLine());
			actions.get(option).execute();
		}
		
		
		for (Action action : actions)
			action.execute();

	}
	
	public static void nuevo() {
		System.out.println("Método nuevo");
	}
	
	public static void editar() {
		System.out.println("Método editado");
	}

}

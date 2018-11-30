package serpis.ad;

import org.apache.commons.math3.stat.descriptive.StatisticalMultivariateSummary;

public class CategoriaMain {
	@FunctionalInterface
	public interface Action {
		void execute();
	}
	
	private static boolean exit  = false;
	public static void main(String[] args) {
		Menu.create("Menú Categoría")
		.exitWhen("0 - Salir")
		.add("1 - Nuevo", CategoriaMain::nuevo)
		.add("2 - Editar", CategoriaMain::editar)
		.loop();		
		
		
		editar();
}
	
	public static void nuevo() {
		System.out.println("Método nuevo");
	}
	
	public static void editar() {
		System.out.println("Método editado");
		int id = ScannerHelper.getInt("Id: ");
	}
}


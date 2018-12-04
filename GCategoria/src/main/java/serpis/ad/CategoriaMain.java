package serpis.ad;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.math3.stat.descriptive.StatisticalMultivariateSummary;

public class CategoriaMain {
	
	private static boolean exit  = false;
	public static void main(String[] args) throws SQLException {
		App.getInstance().setConnection(DriverManager.getConnection("jdbc:mysql://localhost/dbprueba", "root", "sistemas"));
		
		Menu.create("Menú Categoría")
		.exitWhen("0 - Salir")
		.add("1 - Nuevo", CategoriaMain::nuevo)
		.add("2 - Editar", CategoriaMain::editar)
		.loop();		
		App.getInstance().getConnection().close();
}
	
	public static void nuevo() {
		System.out.println("Método nuevo");
	}
	
	public static void editar() {
		System.out.println("Método editado");
		int id = ScannerHelper.getInt("Id: ");
	}
	
//	public static void salir() {
//		System.out.println("Hasta Luego");
//		System.exit(0);
//	}
}


package serpis.ad;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.math3.stat.descriptive.StatisticalMultivariateSummary;

public class CategoriaMain {
	
	private static boolean exit  = false;
	public static void main(String[] args) throws SQLException {
		App.getInstance().setConnection(DriverManager.getConnection("jdbc:mysql://localhost/dbprueba", "root", "sistemas"));
		
		Menu.create("Menú Categoría")
		.exitWhen("0 - Salir")
		.add("1 - Nuevo", CategoriaMain::nuevo)
		.add("2 - Editar", CategoriaMain::editar)
		.add("3 - Eliminar", CategoriaMain::eliminar)
		.add("4 - Consultar", CategoriaMain::consultar)
		.add("5 - Listar", CategoriaMain::listar)
		.loop();		
		App.getInstance().getConnection().close();
}	
	
	public static void nuevo() {
		Categoria categoria = new Categoria();
		CategoriaConsole.newCategoria(categoria);
		try {
			CategoriaDao.save(categoria);
		} catch (SQLException e) {
			System.out.println("No se ha podido crear");
		}
	}
	
	public static void editar() {
		long id = CategoriaConsole.getId();
		Categoria categoria;
		try {
			categoria = CategoriaDao.load(id);
			if (categoria == null) {
				CategoriaConsole.idNotExists();
				return;
			}
			CategoriaConsole.editCategoria(categoria);
			CategoriaDao.save(categoria);
		} catch (SQLException e) {
			System.out.println("Error al editar");
			e.printStackTrace();
		}

	}
	
	public static void eliminar() {
		long id = CategoriaConsole.getId();
			try {
				if (CategoriaConsole.deleteConfirm())
					CategoriaDao.delete(id);
			} catch (SQLException e) {
				System.out.println("Error al eliminar");
			}
	}
	
	public static void consultar() {
		long id = CategoriaConsole.getId();
		Categoria categoria;
		try {
			categoria = CategoriaDao.load(id);
			if (categoria == null) {
				CategoriaConsole.idNotExists();
				return;
			}
			CategoriaConsole.show(categoria);
		} catch (SQLException e) {
			System.out.println("Error al realizar la consulta");
		}

	}
	
	public static void listar() {
		List<Categoria> categorias;
		try {
			categorias = CategoriaDao.getAll();
			CategoriaConsole.showList(categorias);
		} catch (SQLException e) {
			System.out.println("Error al obtener lista");
		}

	}
}
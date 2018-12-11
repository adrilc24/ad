package serpis.ad;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.result.UpdatableResultSet;

public class CategoriaDao {
	private static String insertSql = "insert into categoria (nombre) values ()?";
	private static String updateSql = "update categoria set (nombre) where (id) = ()?";
	private static String deleteSql = "delete from categoria where (id) = ()?";
	private static String selectSql = "select * from categoria where (id) = ()?";

	private static int insert(Categoria categoria) throws SQLException {
		PreparedStatement preparedStatement = App.getInstance().getConnection().prepareStatement(insertSql);
		preparedStatement.setObject(1, categoria.getNombre());
		return preparedStatement.executeUpdate();
	}

	private static int update(Categoria categoria) throws SQLException {
		PreparedStatement preparedStatement = App.getInstance().getConnection().prepareStatement(updateSql);
		preparedStatement.setObject(1, categoria.getNombre());
		return preparedStatement.executeUpdate();
	}

	public static int save(Categoria categoria) throws SQLException {
		if (categoria.getId() == 0)
			return insert(categoria);
		else
			return update(categoria);
	}

	public static Categoria load(long id) throws SQLException {
		return null;
	}

	public static int delete(long id) throws SQLException {
		PreparedStatement preparedStatement = App.getInstance().getConnection().prepareStatement(deleteSql);
		return preparedStatement.executeUpdate();
	}

	public static List<Categoria> getAll() throws SQLException {
		List<Categoria> categorias = new ArrayList<>();
		Categoria categoria = new Categoria();
		categoria.setId(1);
		categoria.setNombre("Categoria 1");
		categorias.add(categoria);
		return categorias;
	}
}
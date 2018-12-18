package serpis.ad;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import org.apache.commons.math3.distribution.GeometricDistribution;

import java.util.*;
import java.time.*;
import com.mysql.cj.jdbc.result.UpdatableResultSet;
import com.mysql.*;

public class CategoriaDao {
	private static String insertSql = "insert into categoria (nombre) values (?)";
	private static String updateSql = "update categoria set nombre=? where id = ?";
	private static String deleteSql = "delete from categoria where id = ?";
	private static String selectAll = "select id, nombre from categoria";
	private static String selectWhereId = "select id, nombre from categoria where id = ?";

	private static int insert(Categoria categoria) throws SQLException {
		PreparedStatement preparedStatement = App.getInstance().getConnection().prepareStatement(insertSql);
		preparedStatement.setObject(1, categoria.getNombre());
		int rowCount = preparedStatement.executeUpdate();
		preparedStatement.close();
		return rowCount;
	}

	private static int update(Categoria categoria) throws SQLException {
		PreparedStatement preparedStatement = App.getInstance().getConnection().prepareStatement(updateSql);
		preparedStatement.setObject(1, categoria.getNombre());
		preparedStatement.setObject(2, categoria.getId());
		int rowCount = preparedStatement.executeUpdate();
		preparedStatement.close();
		return rowCount;
	}

	public static int save(Categoria categoria) throws SQLException {
		if (categoria.getId() == 0)
			return insert(categoria);
		else
			return update(categoria);
	}

	public static Categoria load(long id) throws SQLException {
		try (PreparedStatement preparedStatement = App.getInstance().getConnection().prepareStatement(selectWhereId)) {
			preparedStatement.setObject(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (!resultSet.next())
				return null;
			Categoria categoria = new Categoria();
			categoria.setId(resultSet.getLong("id"));
			categoria.setNombre((String) resultSet.getObject("nombre"));
			return categoria;
		}
	}

	public static int delete(long id) throws SQLException {
		try (PreparedStatement preparedStatement = App.getInstance().getConnection().prepareStatement(deleteSql)) {
			preparedStatement.setObject(1, id);
			return preparedStatement.executeUpdate();
		}
	}

	public static List<Categoria> getAll() throws SQLException {
		List<Categoria> categorias = new ArrayList<>();
		Statement statement = App.getInstance().getConnection().createStatement();
		ResultSet resultSet = statement.executeQuery(selectAll);
		while (resultSet.next()) {
			Categoria categoria = new Categoria();
			// categoria.setId( ((BigInteger) resultSet.getObject("id")).longValue() );
			categoria.setId(resultSet.getLong("id"));
			categoria.setNombre((String) resultSet.getObject("nombre"));
			categorias.add(categoria);
		}
		statement.close();
		return categorias;
	}
}
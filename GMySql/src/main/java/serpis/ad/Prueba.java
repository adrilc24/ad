package serpis.ad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

//import com.mysql.cj.x.protobuf.MysqlxCrud.Insert;

public class Prueba {
	
	private static Connection connection;
	public static void main(String[] args) throws SQLException {
		//Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/dbprueba?user=root&password=sistemas");
		connection = DriverManager.getConnection("jdbc:mysql://localhost/dbprueba", "root", "sistemas");
		
		insert();
		
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from categoria");
		while (resultSet.next())
			System.out.printf("%s %s\n", resultSet.getObject(1), resultSet.getObject(2));
		statement.close(); //implicit resultSet.close()
		connection.close();
	}
	private  static void insert() throws SQLException {
		String insertSql = "insert into categoria (nombre) values (?)";
		PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
		preparedStatement.setObject(1, "categoria "+ LocalDateTime.now());
		preparedStatement.executeUpdate();
		preparedStatement.close();
	}
}
using System;
using MySql.Data.MySqlClient;
using System.Data;
namespace CMysql
{
	class MainClass
	{
		private static string[] getFielNames(){
			throw new NotImplementedException();
		}
        public static void Main(string[] args)
        {
			IDbConnection dbConnection = new MySqlConnection(
				"server = localhost;database=dbprueba;user=root;password=sistemas;sslmode=none"
			);
			dbConnection.Open();

			IDbCommand dbCommand = dbConnection.CreateCommand();
			dbCommand.CommandText = "select * from categoria order by id";
			IDataReader dataReader = dbCommand.ExecuteReader();
			//dataReader.GetName;
			while (dataReader.Read())
				Console.WriteLine("id={0} nombre{1}", dataReader["id"], dataReader["nombre"]);
			    //Console.WriteLine("id={0} nombre{1}", dataReader[0], dataReader[1]);
			dataReader.Close();
			dbConnection.Close();
        }
    }
}


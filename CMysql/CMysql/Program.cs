using System;
using MySql.Data.MySqlClient;

namespace CMysql
{
    class MainClass
    {
        public static void Main(string[] args)
        {
			MySqlConnection mySqlConnection = new MySqlConnection(
				"server = localhost;database=dbprueba;user=root;password=sistemas;sslmode=none"
			);
			mySqlConnection.Open();
			mySqlConnection.Close();
        }
    }
}


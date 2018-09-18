using System;
using MySql.Data.MySqlClient;
using System.Data;
using System.Collections.Generic;
namespace CMysql
{
	class MainClass
	{
		private static string[] getFieldNames(IDataReader dataReader){
			int fieldCount = dataReader.FieldCount;
			string[] fieldNames = new string[fieldCount];
			for (int i = 0; i < fieldCount; i++)
			{
				fieldNames[i] = dataReader.GetName(i);
				Console.WriteLine("Columna = " + fieldNames[i]);
			}
			return fieldNames;

			//List<string> fieldNameList = new List<string>();
			//for (int i = 0; i < fieldCount; i++)
			//	fieldNameList.Add(dataReader.GetName(i));
			//return fieldNameList.ToArray;
		}
        public static void Main(string[] args)
        {
			IDbConnection dbConnection = new MySqlConnection(
				"server = localhost;database=dbprueba;user=root;password=sistemas;sslmode=none"
			);
			dbConnection.Open();

			IDbCommand dbCommand = dbConnection.CreateCommand();
			dbCommand.CommandText = "select id as 'Código', nombre as 'Apellido' from categoria order by id";
			IDataReader dataReader = dbCommand.ExecuteReader();
			//string[] fieldnames = getFieldNames(dataReader);
			getFieldNames(dataReader);





			//foreach (string fieldname in fieldnames)
			//{
			//	Console.WriteLine("Columna = " + fieldname);
			//}
			
			
			//Console.WriteLine("Número de columnas = " + dataReader.FieldCount);
			//for (int i = 0; i < dataReader.FieldCount; i++)
			//	Console.WriteLine("Columna {0} = {1}", i, dataReader.GetName(i));
			//dataReader.GetName;
			//while (dataReader.Read())
				//Console.WriteLine("id={0} nombre{1}", dataReader["id"], dataReader["nombre"]);
			    //Console.WriteLine("id={0} nombre{1}", dataReader[0], dataReader[1]);
			dataReader.Close();
			dbConnection.Close();
        }
    }
}


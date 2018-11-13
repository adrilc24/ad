using System;
using System.Collections.Generic;

namespace CList
{
    class MainClass
    {
        public static void Main(string[] args)
        {
			List<string> propertyNames = new List<string>(new string[] { "Id", "Nombre", "Precio", "Categoria" });
			//List<string> fieldsWithoutId = propertyNames.GetRange(1, propertyNames.Count - 1);

			//List<string> fieldsWithoutId = new List<string>(propertyNames);
			//fieldsWithoutId.RemoveAt(0);

			//List<string> fieldsWithoutId = new List<string>();
			//for (int i = 1; i < propertyNames.Count; i++)
			//fieldsWithoutId.Add(propertyNames[i]);

			//List<string> fieldsWithoutId = new List<string>();
			//foreach (string i in propertyNames) {
			//      if (i != "Id")
			//	        fieldsWithoutId.Add("@"+i);
			//}

			List<string> fieldsWithoutId = new List<string>();
			List<string> parameters = new List<string>();
			for (int index = 1; index < propertyNames.Count; index++){
				fieldsWithoutId.Add(propertyNames[index]);
				parameters.Add("@" + propertyNames[index]);
			}

			//fieldsWithoutId.ForEach(item => parameters.Add("@" + item));

			string fieldNamesCsv = string.Join(", ", fieldsWithoutId).ToLower();
			Console.WriteLine("fieldNamesCsv = " + fieldNamesCsv);
			string parametersCsv = string.Join(", ", parameters).ToLower();
			Console.WriteLine("parametersCsv = " + parametersCsv);


			//update articulo set nombre=@nombre, precio=@precio, categoria = @categoria where id=@id
			//update {0} set {1} where {2} = @id
			List<string> fieldParameterPairs = new List<string>();
			for (int index = 1; index < propertyNames.Count; index++){
				string item = propertyNames[index];
				fieldParameterPairs.Add(item + "=@" + item);
			}

			string fieldParameterPairsCsv = string.Join(", ", fieldParameterPairs).ToLower();
			Console.WriteLine("fielfParameterPairsCsv = " + fieldParameterPairsCsv);
         
        }
    }
}

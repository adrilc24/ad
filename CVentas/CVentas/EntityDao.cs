using System;
using System.Collections;
using System.Data;
using System.Collections.Generic;
using System.Reflection;

namespace Serpis.Ad
{
	public class EntityDao<TEntity>
	{
		protected string idPropertyName = "Id";
		protected Type entityType = typeof(TEntity);
		protected List<string> entityPropertyNames = new List<string>();

		public EntityDao() {
			foreach (PropertyInfo PropertyInfo in entityType.GetProperties())
				if (PropertyInfo.Name == idPropertyName)
					entityPropertyNames.Insert(0, idPropertyName);
				else
					entityPropertyNames.Add(PropertyInfo.Name);
		}
			

		public IEnumerable Enumerable{
			get {
				ArrayList list = new ArrayList();
				IDbCommand dbCommand = App.Instance.DbConnection.CreateCommand();
				entityType.GetProperties();
				string tableName = entityType.Name.ToLower();
				string fieldNamesCsv = string.Join(", ", entityPropertyNames).ToLower();
				string selectSql = string.Format(
					"select {0} from {1} order by {2}",
				     fieldNamesCsv, tableName, idPropertyName.ToLower()
				);
                dbCommand.CommandText = selectSql ;
				IDataReader dataReader = dbCommand.ExecuteReader();
				while(dataReader.Read()) {
					object model = Activator.CreateInstance<TEntity>();
					foreach (string propertyName in entityPropertyNames)
					{
						object value = dataReader[propertyName.ToLower()];
						entityType.GetProperty(propertyName).SetValue(model, value);
					}
					list.Add(model);
				}
				dataReader.Close();
				return list;
			}
		}

		public TEntity Load(object id) {
			//TODO Implementar
			return null;
		}

		public void Save(TEntity entity) {
			//TODO Implementar
			object id = entityType.GetProperty(idPropertyName).GetValue(entity);
			object defaultId = Activator.CreateInstance(entityType.GetProperty(idPropertyName).PropertyType);
			if (id.Equals(defaultId)) // Id == 0
				insert(entity);
			else
				update(entity);
		}

		public void Delete(object id) {
			//TODO Implementar
		}

		public void insert(TEntity entity)
        {
            //TODO Implementar
        }

		public void update(TEntity entity)
        {
            //TODO Implementar
        }

    }
}

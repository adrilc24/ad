﻿using System;
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
					object entity = Activator.CreateInstance<TEntity>();
					foreach (string propertyName in entityPropertyNames)
					{
						object value = dataReader[propertyName.ToLower()];
						entityType.GetProperty(propertyName).SetValue(entity, value);
					}
					list.Add(entity);
				}
				dataReader.Close();
				return list;
			}
		}
		protected string selectSql = "select * from categoria where id = @id";
		public TEntity Load(object id) {
			IDbCommand dbCommand = App.Instance.DbConnection.CreateCommand();
			string tableName = entityType.Name.ToLower();
			dbCommand.CommandText = string.Format(selectSql, tableName, idPropertyName.ToLower());
			DbCommandHelper.AddParameter(dbCommand, "id", id);
            IDataReader dataReader = dbCommand.ExecuteReader();
            dataReader.Read();
			TEntity entity = Activator.CreateInstance<TEntity>();
			foreach (string propertyName in entityPropertyNames) {
                object value = dataReader[propertyName.ToLower()];
				if (value == DBNull.Value)
					value = null;
				entityType.GetProperty(propertyName).SetValue(entity, value);
            }
			dataReader.Close();
			return entity;
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

		protected static string deleteSql = "delete from {0} where {1}=@id ";
        public void Delete(object id)
        {
            string tableName = entityType.Name.ToLower();
            IDbCommand dbCommand = App.Instance.DbConnection.CreateCommand();
            dbCommand.CommandText = string.Format(deleteSql, tableName, idPropertyName.ToLower());
            DbCommandHelper.AddParameter(dbCommand, "id", id);
            dbCommand.ExecuteNonQuery();
        }


		protected string insertSql = "insert into {0} ({1}) values ({2})";
		protected void insert(TEntity entity){
			IDbCommand dbCommand = App.Instance.DbConnection.CreateCommand();

			List<string> insertFieldNames = entityPropertyNames.FindAll(item => item != idPropertyName);
			List<string> parameterNames = new List<string>();
			insertFieldNames.ForEach(item => parameterNames.Add("@" + item));
			string tableName = entityType.Name.ToLower();
			string insertFieldNamesCsv = string.Join(", ", insertFieldNames);
			string parameterNamesCsv = string.Join(", ", parameterNames);
			dbCommand.CommandText = string.Format(insertSql, tableName, insertFieldNames, parameterNamesCsv);
            //DbCommandHelper.AddParameter(dbCommand, "nombre", categoria.Nombre);
			foreach(string fieldName in insertFieldNames) {
				object value = entityType.GetProperty(fieldName).GetValue(entity);
				DBCommandHelper.AddParameter(dbCommand, fieldName, value);
			}

			dbCommand.ExecuteNonQuery();
		}

		protected static string updateSql = "update {0} set {1} where {2}=@id";
        protected void update(object entity)
        {
            List<string> fieldParametersPairs = new List<string>();
            List<string> propertyNames = new List<string>(new string[] { "id", "Nombre", "Precio", "Categoria" });


            for (int index = 1; index < propertyNames.Count; index++)
            {
                string item = propertyNames[index];
                fieldParametersPairs.Add(item + "=@" + item);

            }
            string tableName = entityType.Name.ToLower();
            string FieldNamesCsv = string.Join(", ", fieldParametersPairs).ToLower();
            string updateSql = string.Format("update {0} set {1} where {2}=@id", FieldNamesCsv, tableName, propertyNames);
            IDbCommand dbCommand = App.Instance.DbConnection.CreateCommand();
            dbCommand.CommandText = updateSql;
            IDataReader dataReader = dbCommand.ExecuteReader();
        }

    }
}

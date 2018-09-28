using System;
using Gtk;
using MySql.Data.MySqlClient;
using System.Collections.Generic;
using System.Data;
using System.Reflection;
using CCategoria;
using Serpis.Ad;



public partial class MainWindow : Gtk.Window
{
    public MainWindow() : base(Gtk.WindowType.Toplevel)
    {
        Build();




		App.Instance.DbConnection = new MySqlConnection (
                "server = localhost;database=dbprueba;user=root;password=sistemas;sslmode=none"
            );
		new CategoriaWindow();
        App.Instance.DbConnection.Open();
		//insert();
		//update();
		update(new Categoria(3, "categoria 3 " + DateTime.Now));
		//delete();

		//TreeViewHelper.Fill(treeview, CategoriaDao.List);

		CellRendererText cellRendererText = new CellRendererText();


		string[] properties = new string[] { "Id", "Nombre" };

		foreach (string property in properties)
		{
			string propertyName = property;
			treeview.AppendColumn(
				property,
				cellRendererText,
				delegate (TreeViewColumn tree_column, CellRenderer cell, TreeModel tree_model, TreeIter iter)
				{
					object model = tree_model.GetValue(iter, 0);
				object value = model.GetType().GetProperty(property).GetValue(model);
					cellRendererText.Text = value + "";
				}
			);
		}

		ListStore listStore = new ListStore(typeof(Categoria));
		treeview.Model = listStore;


		//IDbCommand dbCommand = App.Instance.DbConnection.CreateCommand();
		//dbCommand.CommandText = "select id, nombre from categoria order by id";
		//IDataReader dataReader = dbCommand.ExecuteReader();
		//while (dataReader.Read())
		//	listStore.AppendValues(new Categoria((ulong)dataReader["id"], (string)dataReader["nombre"]));
		//dataReader.Close();

		foreach (Categoria categoria in CategoriaDao.Categorias)
			listStore.AppendValues(categoria);
    }

	private void insert() {
		IDbCommand dbCommand = App.Instance.DbConnection.CreateCommand();
		dbCommand.CommandText = "insert into categoria (nombre) values ('categoria 4')";
		int filas = dbCommand.ExecuteNonQuery();
	}

	private void update()
    {
		IDbCommand dbCommand = App.Instance.DbConnection.CreateCommand();
        dbCommand.CommandText = "update categoria set nombre = 'categoria 4 cambiada modificada' where id = 4";
        dbCommand.ExecuteNonQuery();
    }

	private void update(Categoria categoria)
    {
		IDbCommand dbCommand = App.Instance.DbConnection.CreateCommand();
		dbCommand.CommandText = "update categoria set nombre =@nombre where id=@id";

		//IDbDataParameter dbDataParameterNombre= dbCommand.CreateParameter();
		//dbDataParameterNombre.ParameterName = "nombre";
		//dbDataParameterNombre.Value = categoria.Nombre;
		//dbCommand.Parameters.Add(dbDataParameterNombre);

		DbCommandHelper.AddParameter(dbCommand, "nombre", categoria.Nombre);
		DbCommandHelper.AddParameter(dbCommand, "id", categoria.Id);

		//IDbDataParameter dbDataParameterID = dbCommand.CreateParameter();
		//dbDataParameterID.ParameterName = "id";
		//dbDataParameterID.Value = categoria.Id;
        //dbCommand.Parameters.Add(dbDataParameterID);

        dbCommand.ExecuteNonQuery();
    }

	private void delete()
    {
		IDbCommand dbCommand = App.Instance.DbConnection.CreateCommand();
        dbCommand.CommandText = "delete from categoria where id = 4";
        dbCommand.ExecuteNonQuery();
    }

    protected void OnDeleteEvent(object sender, DeleteEventArgs a)
    {
		App.Instance.DbConnection.Close();
        Application.Quit();
        a.RetVal = true;
    }
}
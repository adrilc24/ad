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




		App.Instance.DbConnection = new MySqlConnection(
				"server = localhost;database=dbprueba;user=root;password=sistemas;sslmode=none"
			);

		App.Instance.DbConnection.Open();


		TreeViewHelper.Fill(treeview, new string[] { "Id", "Nombre" }, CategoriaDao.Categorias);

		newAction.Activated += delegate {
			new CategoriaWindow(new Categoria());
		};


		editAction.Activated += delegate {
			object id = GetId(treeview);
			Console.WriteLine("Id = " + id);
			Categoria categoria = CategoriaDao.Load(id);
			new CategoriaWindow(categoria);
		};


		treeview.Selection.Changed += delegate {
			refreshUI();
		};

		refreshUI();
      
	}

	public static object GetId(TreeView treeView){
		return Get(treeView, "Id");
	}

	public static object Get(TreeView treeView, string propertyName){
		if (!treeView.Selection.GetSelected(out TreeIter treeIter))
			return null;
		object model = treeView.Model.GetValue(treeIter, 0);
		return model.GetType().GetProperty(propertyName).GetValue(model);
	}


	    private void refreshUI() {
            bool treeViewIsSelected = treeview.Selection.CountSelectedRows() > 0;
		    editAction.Sensitive = treeViewIsSelected;
		    deleteAction.Sensitive = treeViewIsSelected;
        }

	    

		private void insert()
		{
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
        

			DbCommandHelper.AddParameter(dbCommand, "nombre", categoria.Nombre);
			DbCommandHelper.AddParameter(dbCommand, "id", categoria.Id);


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

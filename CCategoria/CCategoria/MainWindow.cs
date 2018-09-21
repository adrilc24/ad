using System;
using Gtk;
using MySql.Data.MySqlClient;
using System.Data;

public partial class MainWindow : Gtk.Window
{
    public MainWindow() : base(Gtk.WindowType.Toplevel)
    {
        Build();

		IDbConnection dbConnection = new MySqlConnection(
                "server = localhost;database=dbprueba;user=root;password=sistemas;sslmode=none"
            );
        dbConnection.Open();

		treeview.AppendColumn("ID", new CellRendererText(), "text", 0);
		treeview.AppendColumn("Nombre", new CellRendererText(), "text", 1);

		ListStore listStore = new ListStore(typeof(string), typeof(string));
		treeview.Model = listStore;

		//listStore.AppendValues("1", "cat 1");
		//listStore.AppendValues("2", "cat 2");

		IDbCommand dbCommand = dbConnection.CreateCommand();
		dbCommand.CommandText = "select id, nombre from categoria order by id";
		IDataReader dataReader = dbCommand.ExecuteReader();
		while (dataReader.Read())
			listStore.AppendValues(dataReader["id"].ToString(), dataReader["nombre"].ToString());
		dataReader.Close();

		dbConnection.Close();


    }

    protected void OnDeleteEvent(object sender, DeleteEventArgs a)
    {
        Application.Quit();
        a.RetVal = true;
    }
}

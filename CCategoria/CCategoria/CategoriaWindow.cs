using System;
namespace CCategoria
{
    public partial class CategoriaWindow : Gtk.Window
    {
        public CategoriaWindow() :
                base(Gtk.WindowType.Toplevel)
        {
            this.Build();
        }

		protected void OnButtonSaveClicked(object sender, EventArgs e)
		{
			App.Instance.DbConnection;
			Console.WriteLine("Nombre = " + entryNombre.Text);
		}
	}
}

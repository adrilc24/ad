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

		Title = "Categoría";

		TreeViewHelper.Fill(treeview, new string[] { "Id", "Nombre" }, CategoriaDao.Categorias);

		newAction.Activated += delegate {
			new CategoriaWindow(new Categoria());
		};

        
		editAction.Activated += delegate {
			object id = TreeViewHelper.GetId(treeview);
			Categoria categoria = CategoriaDao.Load(id);
			new CategoriaWindow(categoria);
		};

		deleteAction.Activated += delegate
		{
			object id = TreeViewHelper.GetId(treeview);
			if (WindowHelper.Confirm(this, "¿Deseas eliminar el registro?")) {
				object Id = TreeViewHelper.GetId(treeview);
				CategoriaDao.Delete(id);
			}
		};

		refreshAction.Activated += delegate {
			TreeViewHelper.Fill(treeview, new string[] { "Id", "Nombre" }, CategoriaDao.Categorias);
		};


		treeview.Selection.Changed += delegate {
			refreshUI();
		};

		refreshUI();
      
	}



	    private void refreshUI() {
            bool treeViewIsSelected = treeview.Selection.CountSelectedRows() > 0;
		    editAction.Sensitive = treeViewIsSelected;
		    deleteAction.Sensitive = treeViewIsSelected;
        }
       
		protected void OnDeleteEvent(object sender, DeleteEventArgs a)
		{
			Application.Quit();
			a.RetVal = true;
		}
	}

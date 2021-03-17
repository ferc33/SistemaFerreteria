package singleton;

import javax.swing.JDialog;

import vistaCategoria.VistaCategoria;

public class  VistaCategoriaSigleton {

	private static JDialog VC;
	
	private VistaCategoriaSigleton() {
		
	}
	public static JDialog getInstance() {
		if(VC==null) 
			VC= new VistaCategoria();
			return VC;
		}
	}
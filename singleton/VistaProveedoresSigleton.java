package singleton;

import javax.swing.JDialog;

import vistaProveedores.VistaProveedores;

public class  VistaProveedoresSigleton {

	private static JDialog VP;
	
	private VistaProveedoresSigleton() {
		
	}
	public static JDialog getInstance() {
		if(VP==null) 
			VP= new VistaProveedores();
			return VP;
		}
	}
	


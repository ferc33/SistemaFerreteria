package singleton;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import vistaCategoria.VistaCategoria;

public class  VistaCategoriaSigleton {

	private static JFrame VC;
	
	private VistaCategoriaSigleton() {
		
	}
	public static JFrame getInstance(){
  
		if(VC==null) { 
		  
		   VC= new VistaCategoria();
		   
		  }
		return VC;
	}
	}
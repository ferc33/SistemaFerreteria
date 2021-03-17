package singleton;

import javax.swing.JFrame;

import vistaInventario.MarcoArticulo;

public class VistaArticulosSingleton {

	private static JFrame VA;

	private VistaArticulosSingleton() {
	}

	public static JFrame getInstance() {
		if (VA == null)
			VA = new MarcoArticulo();
		return VA;
	}

}

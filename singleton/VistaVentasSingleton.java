package singleton;

import javax.swing.JFrame;

import vistaVentas.VistaVentas;

public class VistaVentasSingleton {

		private static JFrame VV;

		private VistaVentasSingleton() {
		}

		public static JFrame getInstance() {
			if (VV == null)
				VV = new VistaVentas();
			return VV;
		}

	}
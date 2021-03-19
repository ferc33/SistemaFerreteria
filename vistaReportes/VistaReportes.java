package vistaReportes;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.sql.Connection;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import controlador.Controlador;
import modelo.Categoria;
import modelo.Conexion;
import modelo.Proveedor;
import modelo.innerjoin.Productos;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class VistaReportes extends JFrame {

	private JPanel contentPane;
	private Connection conn = null;
	private Conexion con;
	private JTable tablaProveedores;
	private Controlador base;
	private JComboBox comboBoxCategorias;
	private JComboBox comboBoxProveedores;
	private DefaultComboBoxModel modeloProveedor;
	private DefaultComboBoxModel modeloCategoria;
	private DefaultTableModel modeloTablaProveedores = new DefaultTableModel();
	private DefaultTableModel modeloTabla = new DefaultTableModel() {
		@Override
		public final boolean isCellEditable(int row, int column) {
			return false;

		}
	};
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaReportes frame = new VistaReportes();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VistaReportes() {
	initComponent();
	ColumnasDeTablaProveedores();
	modeloComboBoxProveedores();
	modeloComboBoxCategoria();
	}
	
	public void initComponent() {
		 
		 con= new Conexion();
		base = new Controlador();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 117, 590, 245);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 12, 578, 174);
		panel_1.add(scrollPane);
		
		tablaProveedores = new JTable();
		scrollPane.setViewportView(tablaProveedores);
		tablaProveedores.setModel(modeloTabla);
		
		comboBoxProveedores = new JComboBox();
		comboBoxProveedores.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				comboBoxSelectItemEvent(e);
			}
		});
		comboBoxProveedores.setBounds(12, 18, 206, 26);
		panel.add(comboBoxProveedores);
		
		comboBoxCategorias = new JComboBox();
		comboBoxCategorias.setBounds(230, 18, 206, 26);
		panel.add(comboBoxCategorias);
		
		JButton btnImprimir = new JButton("Listar");
		btnImprimir.setHorizontalAlignment(SwingConstants.LEFT);
		btnImprimir.setIcon(new ImageIcon("/home/ferc/git/SistemaFerreteria21/Iconos_Imagenes/merge_to_pdf.png"));
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dameProductoPorCategoria(e);
			}
		});
		btnImprimir.setBounds(22, 63, 104, 42);
		panel.add(btnImprimir);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listarProveedoresActionPerformed(e);
			}
		});
		btnNewButton_2.setIcon(new ImageIcon("/home/ferc/git/SistemaFerreteria21/Iconos_Imagenes/attendance_list.png"));
		btnNewButton_2.setBounds(461, 18, 94, 87);
		panel.add(btnNewButton_2);
	}
	
	private void dameProductoPorCategoria(ActionEvent e) {
		try {
			String path = "/home/ferc/JaspersoftWorkspace/SistemaFereteria/Producto.jasper";		
			conn = con.getConexion();
			Map parametros = new HashMap();
			Categoria categoria = (Categoria) comboBoxCategorias.getSelectedItem();
			int c = categoria.getIdCategoria();
			
			parametros.put("idCategoria", c);
			JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
			JasperPrint jprint = JasperFillManager.fillReport(reporte, parametros, conn);
			JasperViewer view = new JasperViewer(jprint, false);
			view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			view.setVisible(true);
		} catch (Exception es) {
			es.printStackTrace();
		}

	}
	
	private void modeloComboBoxProveedores() {

		modeloProveedor = new DefaultComboBoxModel(new Controlador().dameProveedores());
		comboBoxProveedores.setModel(modeloProveedor);

	}
	
	private void modeloComboBoxCategoria() {

		modeloCategoria = new DefaultComboBoxModel(new Controlador().dameCategorias());
		comboBoxCategorias.setModel(modeloCategoria);

	}
	
	private void ColumnasDeTablaProveedores() {

		modeloTabla.addColumn("Codigo");
		modeloTabla.addColumn("Descripcion");
		modeloTabla.addColumn("Rubro");
		modeloTabla.addColumn("Stock");

		TableColumn ColCodigo = tablaProveedores.getColumn("Codigo");
		TableColumn ColNombre = tablaProveedores.getColumn("Descripcion");
		TableColumn ColProve = tablaProveedores.getColumn("Rubro");
		TableColumn ColVenta = tablaProveedores.getColumn("Stock");

		ColCodigo.setMaxWidth(90);
		ColCodigo.setMinWidth(10);

		ColNombre.setMinWidth(300);
		ColNombre.setMaxWidth(200);

		ColProve.setMaxWidth(150);
		ColProve.setMinWidth(100);

		ColVenta.setMaxWidth(120);
		ColVenta.setMinWidth(10);

	}
	
	private void comboBoxSelectItemEvent(ItemEvent e) {

		if (e.getStateChange() == ItemEvent.SELECTED) {

			Proveedor prove = (Proveedor) comboBoxProveedores.getSelectedItem();
			
			// GUARDAMOS LO QUE OBTENEMOS DE LA BASE DE DATOS EN UN MODELO
			DefaultComboBoxModel modCategoria = new DefaultComboBoxModel(base.dameCategorias(prove.getIdProveedor()));
			// MOSTRAMOS EL MODELO EN EL COMBOBOX
		
		}
	}
	private void listarProveedoresActionPerformed(ActionEvent e) {
		// OBTENGO EL ID DEL PROVEEDOR DESDE EL COMBOBOX
		Proveedor prov = (Proveedor) comboBoxProveedores.getSelectedItem();
		// ME DEVUELVE LOS PROVEEDORES Y LOS GUARDA EL VECTOR PRODUCTOS
		ArrayList<Productos> productos = base.dameProductos(prov.getIdProveedor());
		int columnas = productos.size();
		modeloTabla.setNumRows(columnas);

		for (int i = 0; i < columnas; i++) {

			Productos producto = productos.get(i);
			String idD = producto.getId_prod();
			String desc = producto.getDescripcion();
			String nom = producto.getNom_proveedor();
			double stock = producto.getStock();

			modeloTabla.setValueAt(idD, i, 0);
			modeloTabla.setValueAt(desc, i, 1);
			modeloTabla.setValueAt(nom, i, 2);
			modeloTabla.setValueAt(stock, i, 3);

		}
	}
}
	


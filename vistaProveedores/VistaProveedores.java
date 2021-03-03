package vistaProveedores;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.ItemSelectable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.apache.poi.util.SystemOutLogger;

import controlador.Controlador;
import modelo.Categoria;
import modelo.Conexion;
import modelo.Proveedor;
import modelo.innerjoin.Productos;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.awt.event.ItemEvent;
import java.awt.GridLayout;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.JToolBar;

public class VistaProveedores extends JDialog {
	private Connection conn = null;
	private JTextField txtNombre;
	private JTextField txtTel;
	private JTextField txtEmail;
	private JTextField txtDir;
	private DefaultComboBoxModel modeloProveedor;
	private JComboBox comboBoxProveedores;
	private Controlador base = new Controlador();
	private ArrayList<Proveedor> listaProveedores;
	private Proveedor proveedor;
	private JComboBox comboBoxCategorias;
	private JScrollPane scrollPane;
	private JTable tablaProveedores;
	private Conexion con;
	private DefaultTableModel modeloTablaProveedores = new DefaultTableModel();
	private DefaultTableModel modeloTabla = new DefaultTableModel() {
		@Override
		public final boolean isCellEditable(int row, int column) {
			return false;

		}
	};

	public static void main(String[] args) {
		try {
			VistaProveedores dialog = new VistaProveedores();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void initComponent() {

		con = new Conexion();
		JPanel panel = new JPanel();
		panel.setBounds(12, 0, 676, 555);
		getContentPane().add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 677, 133);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(12, 12, 60, 17);
		panel_1.add(lblNombre);

		JLabel lblTel = new JLabel("Tel:");
		lblTel.setBounds(12, 41, 60, 17);
		panel_1.add(lblTel);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(12, 70, 60, 17);
		panel_1.add(lblEmail);

		JLabel lblDireccin = new JLabel("Dirección");
		lblDireccin.setBounds(12, 99, 60, 17);
		panel_1.add(lblDireccin);

		txtNombre = new JTextField();
		txtNombre.setBounds(90, 3, 214, 35);
		panel_1.add(txtNombre);
		txtNombre.setColumns(10);

		txtTel = new JTextField();
		txtTel.setBounds(90, 39, 214, 29);
		panel_1.add(txtTel);
		txtTel.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setBounds(90, 70, 214, 29);
		panel_1.add(txtEmail);
		txtEmail.setColumns(10);

		txtDir = new JTextField();
		txtDir.setBounds(90, 99, 214, 34);
		panel_1.add(txtDir);
		txtDir.setColumns(10);

		comboBoxProveedores = new JComboBox();
		comboBoxProveedores.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				comboBoxSelectItemEvent(e);

			}
		});

		comboBoxProveedores.setBounds(316, 10, 169, 21);
		panel_1.add(comboBoxProveedores);

		JLabel lblProveedor = new JLabel("Proveedores");
		lblProveedor.setBounds(499, 12, 82, 17);
		panel_1.add(lblProveedor);

		JLabel lblCategorias = new JLabel("Rubro");
		lblCategorias.setBounds(499, 41, 46, 17);
		panel_1.add(lblCategorias);

		comboBoxCategorias = new JComboBox();
		comboBoxCategorias.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				comboBoxCategoriasItemEvent(e);
			}
		});
		comboBoxCategorias.setBounds(316, 39, 169, 21);
		panel_1.add(comboBoxCategorias);

		JButton btnListar = new JButton("Listar");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				listarProveedoresActionPerformed(e);

			}
		});
		btnListar.setBounds(597, 7, 68, 27);
		panel_1.add(btnListar);

		JButton btnProductosPdf = new JButton("Imprimir");
		btnProductosPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dameProductoPorCategoria(e);
			}
		});
		btnProductosPdf.setBounds(544, 36, 121, 27);
		panel_1.add(btnProductosPdf);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 145, 677, 318);
		panel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		panel_2.add(scrollPane_1, BorderLayout.CENTER);

		tablaProveedores = new JTable();
		scrollPane_1.setViewportView(tablaProveedores);

		tablaProveedores.setModel(modeloTabla);
		tablaProveedores.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {
				// dameDatosListSelection(e);
			}

		});

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(12, 475, 665, 68);
		panel.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 4, 0, 0));

		JButton btnGuardar = new JButton();
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertarProveedorActionPerformed(e);
			}
		});
		btnGuardar.setText("Guardar");
		panel_3.add(btnGuardar);

		JButton btnActualizarProveedor = new JButton();
		btnActualizarProveedor.setText("Actualizar");
		panel_3.add(btnActualizarProveedor);

		JButton jButton1 = new JButton();
		jButton1.setText("Eliminar");
		panel_3.add(jButton1);

		JButton btnCancelar = new JButton();
		btnCancelar.setText("Salir");
		panel_3.add(btnCancelar);
	}
//INSERTAR UN PROVEEDOR

	private void insertarProveedorActionPerformed(ActionEvent e) {

		String nombre = txtNombre.getText();
		String dir = txtDir.getText();
		String mail = txtEmail.getText();
		String tel = txtTel.getText();

		Proveedor proveedor = new Proveedor(nombre, dir, mail, tel);
		base.insertarProveedor(proveedor);

	}

	private void ColumnasDeTablaProveedores() {

		modeloTabla.addColumn("Id Proveedor");
		modeloTabla.addColumn("Descripcion");
		modeloTabla.addColumn("Rubro");
		modeloTabla.addColumn("Stock");

		TableColumn ColCodigo = tablaProveedores.getColumn("Id Proveedor");
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

	private void cargarColumnasTablaProveedores() {

		modeloTabla.addColumn("Nombre");
		modeloTabla.addColumn("Direccion");
		modeloTabla.addColumn("Correo");
		modeloTabla.addColumn("Tel");

		TableColumn ColCodigo = tablaProveedores.getColumn("Nombre");
		TableColumn ColNombre = tablaProveedores.getColumn("Direccion");
		TableColumn ColProve = tablaProveedores.getColumn("Correo");
		TableColumn ColVenta = tablaProveedores.getColumn("Tel");

		ColCodigo.setMaxWidth(200);
		ColCodigo.setMinWidth(10);

		ColNombre.setMinWidth(200);
		ColNombre.setMaxWidth(200);

		ColProve.setMaxWidth(150);
		ColProve.setMinWidth(100);

		ColVenta.setMaxWidth(120);
		ColVenta.setMinWidth(10);

	}

	public VistaProveedores() {
		initComponent();
		ColumnasDeTablaProveedores();
		modeloComboBoxProveedores();
		setBounds(100, 100, 700, 600);
		getContentPane().setLayout(null);

	}

	// CARGAR EL MODELO EN EL COMBOBOX
	private void modeloComboBoxProveedores() {

		modeloProveedor = new DefaultComboBoxModel(base.dameProveedores());
		comboBoxProveedores.setModel(modeloProveedor);

	}

	private void modeloTablaProveedores() {

		Vector<Proveedor> listaProveedores = base.dameProveedoresTabla();
		int numeroProducto = listaProveedores.size();
		modeloTabla.setNumRows(numeroProducto);

		for (int i = 0; i < numeroProducto; i++) {

			Proveedor proveedor = listaProveedores.get(i);
			String nombre = proveedor.getNomProveedor();
			String dir = proveedor.getDirProveedor();
			String tel = proveedor.getTelProveedor();
			String correo = proveedor.getMailProveedor();

			modeloTabla.setValueAt(tel, i, 0);
			modeloTabla.setValueAt(proveedor, i, 1);
			modeloTabla.setValueAt(dir, i, 2);
			modeloTabla.setValueAt(correo, i, 3);

		}

	}

	// CLICK EN TABLA
	private void dameDatosListSelection(ListSelectionEvent e) {

		if (!e.getValueIsAdjusting() && (tablaProveedores.getSelectedRow() >= 0)) {

			Proveedor proveedor = (Proveedor) modeloTabla.getValueAt(tablaProveedores.getSelectedRow(), 1);
			txtNombre.setText(String.valueOf(proveedor.getNomProveedor()));
			txtDir.setText(String.valueOf(proveedor.getDirProveedor()));
			txtEmail.setText(String.valueOf(proveedor.getMailProveedor()));
			txtTel.setText(String.valueOf(proveedor.getTelProveedor()));

		}

	}

	// LISTA LOS PRODUCTOS EN LA TABLA PROVEEDORES
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

	private void dameProductoPorCategoria(ActionEvent e) {
		try {
			String path = "/home/ferc/JaspersoftWorkspace/SistemaFereteria/Producto.jasper";
			conn = con.getConexion();
			Map parametros = new HashMap();
			Categoria categoria = (Categoria) comboBoxCategorias.getSelectedItem();
			int c = categoria.getIdCategoria();
			System.out.println(c);
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

	private void comboBoxSelectItemEvent(ItemEvent e) {

		if (e.getStateChange() == ItemEvent.SELECTED) {

			Proveedor prove = (Proveedor) comboBoxProveedores.getSelectedItem();
			// GUARDAMOS LO QUE OBTENEMOS DE LA BASE DE DATOS EN UN MODELO
			DefaultComboBoxModel modCategoria = new DefaultComboBoxModel(base.dameCategorias(prove.getIdProveedor()));
			// MOSTRAMOS EL MODELO EN EL COMBOBOX
			comboBoxCategorias.setModel(modCategoria);
		}
	}

	private void comboBoxCategoriasItemEvent(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {

			Categoria cat = (Categoria) comboBoxCategorias.getSelectedItem();

		}

	}
}

/* NO USAR MAS DE UN MODELO EN UNA TABLA */

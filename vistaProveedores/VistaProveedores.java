package vistaProveedores;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.ItemSelectable;
import java.util.ArrayList;
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
import modelo.Producto;
import modelo.Proveedor;
import vistaInventario.MarcoArticulo;

import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.awt.event.ItemEvent;
import java.awt.GridLayout;

public class VistaProveedores extends JDialog {
	private JTextField txtNombre;
	private JTextField txtTel;
	private JTextField txtEmail;
	private JTextField txtDir;
	private DefaultComboBoxModel modeloProveedor;
	private JComboBox comboBoxProveedores;
	private DefaultTableModel modeloTabla = null;
	private Controlador base = new Controlador();
	private ArrayList<Proveedor> listaProveedores;
	private Proveedor proveedor;
	private Producto producto = null;
	private Proveedor ProveedorSeleccionado = null;
	private JComboBox comboBoxCategorias;
	private JScrollPane scrollPane = null;
	private JTable tablaProveedores=null;

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

		JPanel panel = new JPanel();
		panel.setBounds(12, 0, 700, 555);
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
		txtNombre.setBounds(90, 10, 114, 21);
		panel_1.add(txtNombre);
		txtNombre.setColumns(10);

		txtTel = new JTextField();
		txtTel.setBounds(90, 39, 114, 21);
		panel_1.add(txtTel);
		txtTel.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setBounds(90, 68, 114, 21);
		panel_1.add(txtEmail);
		txtEmail.setColumns(10);

		txtDir = new JTextField();
		txtDir.setBounds(90, 97, 114, 21);
		panel_1.add(txtDir);
		txtDir.setColumns(10);

		comboBoxProveedores = new JComboBox();
		comboBoxProveedores.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				comboBoxSelectItemEvent(e);

			}
		});
		comboBoxProveedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		comboBoxProveedores.setBounds(336, 10, 193, 21);
		panel_1.add(comboBoxProveedores);

		JLabel lblProveedor = new JLabel("Proveedores");
		lblProveedor.setBounds(236, 12, 82, 17);
		panel_1.add(lblProveedor);

		JLabel lblCategorias = new JLabel("Categorias");
		lblCategorias.setBounds(236, 41, 82, 17);
		panel_1.add(lblCategorias);

		comboBoxCategorias = new JComboBox();
		comboBoxCategorias.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				comboBoxCategoriasItemEvent(e);
			}
		});
		comboBoxCategorias.setBounds(336, 36, 193, 21);
		panel_1.add(comboBoxCategorias);

		JButton btnAadirCategoria = new JButton("Añadir Categoria");
		btnAadirCategoria.setBounds(525, 94, 140, 27);
		panel_1.add(btnAadirCategoria);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 145, 677, 318);
		panel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		panel_2.add(scrollPane_1, BorderLayout.CENTER);

		tablaProveedores = new JTable();
		scrollPane_1.setViewportView(tablaProveedores);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(12, 475, 665, 68);
		panel.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 4, 0, 0));

		JButton btnGuardar = new JButton();
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

	/*
	 * private void cargarColumnasTablaProveedores() {
	 * 
	 * modeloTabla = new DefaultTableModel(); modeloTabla.addColumn("CODIGO");
	 * modeloTabla.addColumn("DESCRIPCION"); modeloTabla.addColumn("C.PROVEEDOR");
	 * modeloTabla.addColumn("PRECIO VENTA");
	 * 
	 * TableColumn ColCodigo = tablaProveedores.getColumn("CODIGO"); TableColumn
	 * ColNombre = tablaProveedores.getColumn("DESCRIPCION"); TableColumn ColProve =
	 * tablaProveedores.getColumn("C.PROVEEDOR"); TableColumn ColVenta =
	 * tablaProveedores.getColumn("PRECIO VENTA");
	 * 
	 * ColCodigo.setMaxWidth(80); ColCodigo.setMinWidth(10);
	 * 
	 * ColNombre.setMinWidth(300); ColNombre.setMaxWidth(500);
	 * 
	 * ColProve.setMaxWidth(150); ColProve.setMinWidth(50);
	 * 
	 * ColVenta.setMaxWidth(80); ColVenta.setMinWidth(10);
	 * 
	 * }
	 */

	public VistaProveedores() {
		initComponent();
		cargarModeloProv();
		setBounds(100, 100, 700, 600);

		getContentPane().setLayout(null);

	}

	// CARGAR EL MODELO EN EL COMBOBOX
	public void cargarModeloProv() {
		proveedor = new Proveedor();
		modeloProveedor = new DefaultComboBoxModel(base.dameProveedores());
		comboBoxProveedores.setModel(modeloProveedor);

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

/**/

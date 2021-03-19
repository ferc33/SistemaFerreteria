package vistaCategoria;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import controlador.Controlador;
import modelo.Categoria;
import modelo.Proveedor;
import singleton.VistaArticulosSingleton;
import vistaInventario.MarcoArticulo;
import vistaProveedores.VMproveedores;
import vistaProveedores.VistaProveedores;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VistaCategoria extends JFrame {

	private DefaultTableModel modeloTabla = new DefaultTableModel();
	private Controlador base = new Controlador();
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCategoria;
	private JTable tablaCategoria;
	private JTextField txtDesc;
	public static String nombre;
	public static int id;
	public static MarcoArticulo marco = new MarcoArticulo();
	// PERTENECE A ESTA VENTANA

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { try { VistaCategoria dialog = new
	 * VistaCategoria( id, nombre);
	 * dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	 * dialog.setVisible(true); } catch (Exception e) { e.printStackTrace(); } }
	 */

	public void initComponent() {
		contentPanel.setLayout(null);

		{
			JPanel panel = new JPanel();
			panel.setBounds(5, 5, 511, 115);
			contentPanel.add(panel);
			panel.setLayout(null);

			txtCategoria = new JTextField();
			txtCategoria.setBounds(84, 12, 199, 33);
			panel.add(txtCategoria);
			txtCategoria.setColumns(10);

			JLabel lblCategoria = new JLabel("Categoria");
			lblCategoria.setBounds(12, 20, 73, 17);
			panel.add(lblCategoria);

			txtDesc = new JTextField();
			txtDesc.setBounds(84, 73, 381, 33);
			txtDesc.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {

					txtDescKeyReleased(e);
				}
			});
			panel.add(txtDesc);
			txtDesc.setColumns(10);

			JLabel lblBuscar = new JLabel("Buscar");
			lblBuscar.setBounds(12, 83, 73, 17);
			panel.add(lblBuscar);

			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setBounds(466, 73, 33, 28);
			lblNewLabel.setIcon(new ImageIcon("/home/ferc/git/SistemaFerreteria21/Iconos_Imagenes/zoom_iwwn.png"));
			panel.add(lblNewLabel);

			JPanel panel_1 = new JPanel();
			panel_1.setBounds(295, 0, 204, 49);
			panel_1.setBackground(Color.DARK_GRAY);
			panel.add(panel_1);
			panel_1.setLayout(new GridLayout(0, 4, 0, 0));
			JButton btnGuardar = new JButton("");
			panel_1.add(btnGuardar);
			btnGuardar.setBackground(Color.DARK_GRAY);
			btnGuardar.setIcon(new ImageIcon("/home/ferc/git/SistemaFerreteria21/Iconos_Imagenes/Agregar.png"));
			btnGuardar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					insertarCategoriaActionPerformed(e);
				}
			});
			btnGuardar.setActionCommand("OK");
			getRootPane().setDefaultButton(btnGuardar);

			JButton btnModificar = new JButton("");
			panel_1.add(btnModificar);
			btnModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actualizarCategoriaActionPerformed(e);

				}
			});
			btnModificar.setBackground(Color.DARK_GRAY);
			btnModificar.setIcon(new ImageIcon("/home/ferc/git/SistemaFerreteria21/Iconos_Imagenes/Editar.png"));

			JButton btnEliminar = new JButton("");
			panel_1.add(btnEliminar);

			btnEliminar.setBackground(Color.DARK_GRAY);
			btnEliminar.setIcon(new ImageIcon("/home/ferc/git/SistemaFerreteria21/Iconos_Imagenes/Eliminar.png"));

			JButton button = new JButton("");
			button.setBackground(Color.DARK_GRAY);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					LimpiarLista();
					modeloTablaCategorias();
				}
			});
			panel_1.add(button);
			button.setIcon(new ImageIcon("/home/ferc/git/SistemaFerreteria21/Iconos_Imagenes/Visualizar.png"));
		}

		JPanel panel = new JPanel();
		panel.setBounds(5, 121, 511, 214);
		contentPanel.add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 12, 499, 154);
		panel.add(scrollPane);

		tablaCategoria = new JTable();
		scrollPane.setViewportView(tablaCategoria);
		tablaCategoria.setModel(modeloTabla);

		JButton btnEnviarCategoria = new JButton("");
		btnEnviarCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Categoria categoriaEnvio = (Categoria) modeloTabla.getValueAt(tablaCategoria.getSelectedRow(), 1);
				
				//JFrame Articulo = VistaArticulosSingleton.getInstance();
				
				
				/*marco.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				marco.setVisible(true);
				marco.setAlwaysOnTop(false);
				marco.setLocationRelativeTo(new VistaCategoria( ));*/
				marco.lblIdCategoria.setText(String.valueOf(categoriaEnvio.getIdCategoria()));
				marco.txtCategoria.setText(categoriaEnvio.getNomCategoria());
				dispose();
				
			}
		});

		btnEnviarCategoria.setIcon(new ImageIcon("/home/ferc/git/SistemaFerreteria21/Iconos_Imagenes/Aceptar.png"));
		btnEnviarCategoria.setBounds(377, 168, 61, 46);
		panel.add(btnEnviarCategoria);

		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon("/home/ferc/git/SistemaFerreteria21/Iconos_Imagenes/ReHacer.png"));
		btnNewButton.setBounds(444, 168, 55, 46);
		panel.add(btnNewButton);

	}

	public VistaCategoria() {

	

		setBounds(100, 100, 521, 368);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		initComponent();
		cargarColumnasCategorias();
		modeloTablaCategorias();

	}

	private void cargarColumnasCategorias() {
		modeloTabla.addColumn("ID");
		modeloTabla.addColumn("Descripcion");

		TableColumn id = tablaCategoria.getColumn("ID");
		TableColumn des = tablaCategoria.getColumn("Descripcion");

		id.setMaxWidth(30);
		id.setMinWidth(30);

		des.setMinWidth(600);
		des.setMaxWidth(200);

	}

	private void actualizarCategoriaActionPerformed(ActionEvent e) {
		Categoria categoria = (Categoria) modeloTabla.getValueAt(tablaCategoria.getSelectedRow(), 1);
		EditarCategoria vistaD = new EditarCategoria();
		vistaD.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		vistaD.setVisible(true);
		vistaD.setAlwaysOnTop(false);
		vistaD.setLocationRelativeTo(new VistaCategoria( ));
		vistaD.lblId.setText(String.valueOf(categoria.getIdCategoria()));
		vistaD.txtDesc.setText(categoria.getNomCategoria());

	}

	private void insertarCategoriaActionPerformed(ActionEvent e) {

		Categoria categoria = (Categoria) modeloTabla.getValueAt(tablaCategoria.getSelectedRow(), 1);
		int index = categoria.getIdCategoria();
		String nomCategoria = txtCategoria.getText();

		if (nomCategoria.isEmpty() || index == 0) {
			JOptionPane.showMessageDialog(null, "El campo no debe estar vacio y se debe seleccionar un proveedor");
		} else {
			Categoria cat = new Categoria(index, nomCategoria);

			base.insertarCategoriaProducto(cat);

			dispose();
		}

	}

	private Categoria selectCategoria() {

		Categoria categoria = (Categoria) modeloTabla.getValueAt(tablaCategoria.getSelectedRow(), 1);

		int id = categoria.getIdCategoria();
		String nom = categoria.getNomCategoria();

		Categoria cat = new Categoria(id, nom);

		return cat;

	}

	private void txtDescKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtDescKeyReleased

		LimpiarLista();

		String cadena = txtDesc.getText();

		ArrayList<Categoria> listaCategoria = base.obtenerCategoria(cadena);

		int numeroProducto = listaCategoria.size();
		modeloTabla.setNumRows(numeroProducto);
		for (int i = 0; i < numeroProducto; i++) {

			Categoria categoria = listaCategoria.get(i);
			int clave = categoria.getIdCategoria();
			String nomBre = categoria.getNomCategoria();

			modeloTabla.setValueAt(clave, i, 0);
			modeloTabla.setValueAt(categoria, i, 1);

		}
	}

	private void modeloTablaCategorias() {

		ArrayList<Categoria> listaCategoria = base.dameCategoriasTabla();
		int numeroProducto = listaCategoria.size();
		modeloTabla.setNumRows(numeroProducto);

		for (int i = 0; i < numeroProducto; i++) {

			Categoria categoria = listaCategoria.get(i);
			int id = categoria.getIdCategoria();
			String nombre = categoria.getNomCategoria();

			modeloTabla.setValueAt(id, i, 0);
			modeloTabla.setValueAt(categoria, i, 1);

		}

	}

	private void LimpiarLista() {
		int numFilas = modeloTabla.getRowCount();
		if (numFilas > 0) {
			for (int i = numFilas - 1; i < 0; i--) {
				modeloTabla.removeRow(i);
			}
		}
	}
}

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
import modelo.Producto;
import modelo.Proveedor;
import modelo.innerjoin.Productos;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import vistaInventario.MarcoArticulo;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.awt.event.ItemEvent;
import java.awt.GridLayout;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;

public class VistaProveedores extends JDialog {
	private DefaultComboBoxModel modeloProveedor;
	private Controlador base = new Controlador();
	private ArrayList<Proveedor> listaProveedores;
	private Proveedor proveedor;
	private JScrollPane scrollPane;
	private JTable tablaProveedores;
	private Connection conn = null;
	private Conexion con;
	private DefaultTableModel modeloTablaProveedores = new DefaultTableModel();
	private DefaultTableModel modeloTabla = new DefaultTableModel() {
		@Override
		public final boolean isCellEditable(int row, int column) {
			return false;

		}
	};
	private JTextField txtDesc;

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
		panel.setBounds(12, 0, 676, 358);
		getContentPane().add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 677, 73);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		txtDesc = new JTextField();
		txtDesc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				txtDescKeyReleased(e);
			}
		});
		txtDesc.setBounds(90, 21, 214, 32);
		panel_1.add(txtDesc);
		txtDesc.setColumns(10);
		
		JLabel lblBuscar = new JLabel("Buscar:");
		lblBuscar.setBounds(12, 28, 60, 17);
		panel_1.add(lblBuscar);
		
		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setIcon(new ImageIcon("/home/ferc/git/SistemaFerreteria21/Iconos_Imagenes/zoom_iwwn.png"));
		label.setBounds(314, 12, 46, 41);
		panel_1.add(label);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(422, 12, 243, 49);
		panel_1.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 4, 0, 0));
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirVentanaActionPerformed(e);
			}
		});
		
		panel_4.add(btnNewButton);
		btnNewButton.setIcon(new ImageIcon("/home/ferc/git/SistemaFerreteria21/Iconos_Imagenes/Agregar.png"));
		
		JButton btnModificar = new JButton("");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarVentanaActionPerformed(e);
			}
		});
		panel_4.add(btnModificar);
		btnModificar.setIcon(new ImageIcon("/home/ferc/git/SistemaFerreteria21/Iconos_Imagenes/Editar.png"));
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTablaActionPerformed(e);
			}
		});
		
		JButton btnEliminar = new JButton("");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarProveedorActionPerformed(e);
			}
		});
		panel_4.add(btnEliminar);
		btnEliminar.setIcon(new ImageIcon("/home/ferc/git/SistemaFerreteria21/Iconos_Imagenes/Eliminar.png"));
		btnNewButton_1.setIcon(new ImageIcon("/home/ferc/git/SistemaFerreteria21/Iconos_Imagenes/refresh1.png"));
		panel_4.add(btnNewButton_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 85, 677, 268);
		panel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		
		panel_2.add(scrollPane_1, BorderLayout.CENTER);

		tablaProveedores = new JTable();
		scrollPane_1.setViewportView(tablaProveedores);
		tablaProveedores.setModel(modeloTabla);
		
	}
//INSERTAR UN PROVEEDOR



	

	private void cargarColumnasTablaProveedores() {
		modeloTabla.addColumn(" ");
		modeloTabla.addColumn("Descripcion");
		modeloTabla.addColumn("Direccion");		
		modeloTabla.addColumn("Correo");
		modeloTabla.addColumn("Tel");
		TableColumn id = tablaProveedores.getColumn(" ");
		TableColumn ColNombre = tablaProveedores.getColumn("Direccion");
		TableColumn ColCodigo = tablaProveedores.getColumn("Descripcion");
		
		TableColumn ColProve = tablaProveedores.getColumn("Correo");
		TableColumn ColVenta = tablaProveedores.getColumn("Tel");

		id.setMaxWidth(30);
		id.setMinWidth(20);
		
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
		cargarColumnasTablaProveedores();
		modeloTablaProveedores();		
		modeloComboBoxProveedores();
		
		setBounds(100, 100, 700, 400);
		getContentPane().setLayout(null);

	}

	// CARGAR EL MODELO EN EL COMBOBOX
	private void modeloComboBoxProveedores() {

		modeloProveedor = new DefaultComboBoxModel(base.dameProveedores());

	}

	private void modeloTablaProveedores() {

		Vector<Proveedor> listaProveedores = base.dameProveedoresTabla();
		int numeroProducto = listaProveedores.size();
		modeloTabla.setNumRows(numeroProducto);

		for (int i = 0; i < numeroProducto; i++) {

			Proveedor proveedor = listaProveedores.get(i);
			int id = proveedor.getIdProveedor();
			String nombre = proveedor.getNomProveedor();
			String dir = proveedor.getDirProveedor();
			String tel = proveedor.getTelProveedor();
			String correo = proveedor.getMailProveedor();

			modeloTabla.setValueAt(id, i, 0);
			modeloTabla.setValueAt(proveedor, i, 1);
			modeloTabla.setValueAt(dir, i, 2);
			modeloTabla.setValueAt(correo, i, 3);
			modeloTabla.setValueAt(tel, i, 4);

		}

	}
	
	private void updateTablaActionPerformed(ActionEvent e) {
		LimpiarLista();
		modeloTablaProveedores();
	}
	
	private void abrirVentanaActionPerformed(ActionEvent e) {
		
		VMproveedores  vistaD = new VMproveedores();
	      vistaD.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	      vistaD.setVisible(true);
	      vistaD.setAlwaysOnTop(false);
	      vistaD.setLocationRelativeTo(new VistaProveedores());

	}
	
	private void eliminarProveedorActionPerformed(ActionEvent e) {
		
		Proveedor proveedor = (Proveedor) modeloTabla.getValueAt(tablaProveedores.getSelectedRow(), 1);
		
		int d = JOptionPane.showConfirmDialog(null, "¿Desea eliminar un proveedor?");
		
		if(d==0) {
			base.borrarProveedor(proveedor);			
			LimpiarLista();
			modeloTablaProveedores();
		}
	
	}
	private void editarVentanaActionPerformed(ActionEvent e) {
		
		Proveedor proveedor = (Proveedor) modeloTabla.getValueAt(tablaProveedores.getSelectedRow(), 1);
			
		VMproveedorUpdate  vistaD = new VMproveedorUpdate();
	      vistaD.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	      vistaD.setVisible(true);
	      vistaD.setAlwaysOnTop(false);
	      
	      vistaD.setLocationRelativeTo(new VMproveedorUpdate());
	      vistaD.lblId.setText(String.valueOf(proveedor.getIdProveedor()));
	      vistaD.txtTel.setText(proveedor.getTelProveedor());
	      vistaD.txtEmail.setText(proveedor.getMailProveedor());
	      vistaD.txtDesc.setText(proveedor.getNomProveedor());
	      vistaD.txtDir.setText(proveedor.getDirProveedor());
	      
	     
	}


	
	private void txtDescKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescKeyReleased

		 			
		        LimpiarLista();
		
                String cadena = txtDesc.getText();
                
                ArrayList<Proveedor> listaProveedor = base.obtenerProveedoresPorCriterio(cadena);

                int numeroProducto = listaProveedor.size();
                modeloTabla.setNumRows(numeroProducto);
                for (int i = 0; i < numeroProducto; i++) {

                    Proveedor proveedor = listaProveedor.get(i);
                    int clave = proveedor.getIdProveedor();
                    String nomBre = proveedor.getNomProveedor();
                    String dir = proveedor.getDirProveedor();
                    String tel = proveedor.getTelProveedor();
                    String mail = proveedor.getMailProveedor();

                    modeloTabla.setValueAt(clave, i, 0);
                    modeloTabla.setValueAt(proveedor, i, 1);
                    modeloTabla.setValueAt(dir, i, 2);
                    modeloTabla.setValueAt(tel, i, 3);
                    modeloTabla.setValueAt(mail, i, 4);

       
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

/* NO USAR MAS DE UN MODELO EN UNA TABLA */

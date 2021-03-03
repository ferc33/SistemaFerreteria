package vistaCategoria;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;
import modelo.Categoria;
import modelo.Proveedor;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class VistaCategoria extends JDialog {

	private DefaultComboBoxModel modeloCategoria=null;
	private Controlador base = new Controlador();
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCategoria;
	private JComboBox comboBoxProveedores;
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VistaCategoria dialog = new VistaCategoria();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

public void initComponent() {
	
	
	{
		JPanel panel = new JPanel();
		contentPanel.add(panel);
		panel.setLayout(null);
		
		txtCategoria = new JTextField();
		txtCategoria.setBounds(84, 12, 159, 33);
		panel.add(txtCategoria);
		txtCategoria.setColumns(10);
		
		 comboBoxProveedores = new JComboBox();
		comboBoxProveedores.setBounds(263, 12, 198, 33);
		panel.add(comboBoxProveedores);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(12, 20, 73, 17);
		panel.add(lblCategoria);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 86, 511, 39);
		panel.add(panel_1);
		{
			JButton btnGuardar = new JButton("Guardar");
			btnGuardar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
			
							insertarCategoriaActionPerformed(e);
				}
			});
			panel_1.add(btnGuardar);
			btnGuardar.setActionCommand("OK");
			getRootPane().setDefaultButton(btnGuardar);
		}
		
		JButton btnListarProductos = new JButton("Listar Productos");
		btnListarProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_1.add(btnListarProductos);
		{
			JButton cancelButton = new JButton("Salir");
			panel_1.add(cancelButton);
			cancelButton.setActionCommand("Cancel");
		}
	}
	
	JPanel panel = new JPanel();
	contentPanel.add(panel);
	panel.setLayout(null);
	
	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setBounds(0, 12, 499, 101);
	panel.add(scrollPane);
	
	table = new JTable();
	scrollPane.setViewportView(table);
	{
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
	}
	

}
	public VistaCategoria() {
		setBounds(100, 100, 521, 298);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		initComponent();
	cargarModeloProv();
	}
	public void cargarModeloProv() {
 			
		
		 modeloCategoria = new DefaultComboBoxModel(base.dameProveedores());
		comboBoxProveedores.setModel(modeloCategoria);

	}
	
	private void insertarCategoriaActionPerformed(ActionEvent e){
		
		Proveedor idProveedor =(Proveedor)comboBoxProveedores.getSelectedItem();
		
		String nomCategoria = txtCategoria.getText();
		
		Categoria categoria = new Categoria (nomCategoria,idProveedor.getIdProveedor());
		
		base.insertarCategoriaProducto(categoria);
		
		dispose();
		
		
	}
	
}

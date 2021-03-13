package vistaCategoria;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import controlador.Controlador;
import modelo.Categoria;
import modelo.Proveedor;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VistaCategoria extends JDialog {

	private DefaultComboBoxModel modeloCategoria = null;
	private DefaultTableModel modeloTabla = new DefaultTableModel();
	private Controlador base = new Controlador();
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCategoria;
	private JComboBox comboBoxProveedores;
	private JTable tablaCategorias;
	private JTextField txtDesc;

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
			comboBoxProveedores.setBounds(295, 12, 204, 33);
			panel.add(comboBoxProveedores);

			JLabel lblCategoria = new JLabel("Categoria");
			lblCategoria.setBounds(12, 20, 73, 17);
			panel.add(lblCategoria);
			
			txtDesc = new JTextField();
			txtDesc.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					
					txtDescKeyReleased(e);
				}
			});
			txtDesc.setBounds(84, 57, 159, 28);
			panel.add(txtDesc);
			txtDesc.setColumns(10);
			
			JLabel lblBuscar = new JLabel("Buscar");
			lblBuscar.setBounds(12, 62, 73, 17);
			panel.add(lblBuscar);
			
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setBounds(261, 57, 33, 28);
			lblNewLabel.setIcon(new ImageIcon("/home/ferc/git/SistemaFerreteria21/Iconos_Imagenes/zoom_iwwn.png"));
			panel.add(lblNewLabel);
			JButton btnGuardar = new JButton("");
			btnGuardar.setBackground(Color.WHITE);
			btnGuardar.setBounds(309, 79, 58, 34);
			panel.add(btnGuardar);
			btnGuardar.setIcon(new ImageIcon("/home/ferc/git/SistemaFerreteria21/Iconos_Imagenes/add.png"));
			btnGuardar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					insertarCategoriaActionPerformed(e);
				}
			});
			btnGuardar.setActionCommand("OK");
			getRootPane().setDefaultButton(btnGuardar);
			
			JButton btnModificar = new JButton("");
			btnModificar.setBackground(Color.WHITE);
			btnModificar.setBounds(379, 79, 54, 34);
			panel.add(btnModificar);
			btnModificar.setIcon(new ImageIcon("/home/ferc/git/SistemaFerreteria21/Iconos_Imagenes/geasr.png"));
			
			JButton btnEliminar = new JButton("");
			btnEliminar.setBackground(Color.WHITE);
			btnEliminar.setBounds(445, 79, 54, 34);
			panel.add(btnEliminar);
			btnEliminar.setIcon(new ImageIcon("/home/ferc/git/SistemaFerreteria21/Iconos_Imagenes/close.png"));
		}

		JPanel panel = new JPanel();
		contentPanel.add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 499, 113);
		panel.add(scrollPane);

		tablaCategorias = new JTable();
		scrollPane.setViewportView(tablaCategorias);
		tablaCategorias.setModel(modeloTabla);
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
		cargarColumnasCategorias();
		modeloTablaCategorias();
		cargarModeloProv();
	}

	public void cargarModeloProv() {

		modeloCategoria = new DefaultComboBoxModel(base.dameProveedores());
		comboBoxProveedores.setModel(modeloCategoria);

	}

	private void cargarColumnasCategorias() {
		modeloTabla.addColumn("ID");
		modeloTabla.addColumn("Descripcion");

		TableColumn id = tablaCategorias.getColumn("ID");
		TableColumn des = tablaCategorias.getColumn("Descripcion");

		id.setMaxWidth(30);
		id.setMinWidth(30);

		des.setMinWidth(500);
		des.setMaxWidth(200);

	}

	private void insertarCategoriaActionPerformed(ActionEvent e) {

		Proveedor idProveedor = (Proveedor) comboBoxProveedores.getSelectedItem();
		int index = comboBoxProveedores.getSelectedIndex();
		

		String nomCategoria = txtCategoria.getText();
		
		if(nomCategoria.isEmpty() || index==0) {
			JOptionPane.showMessageDialog(null, "El campo no debe estar vacio y se debe seleccionar un proveedor");
		}else {
			Categoria categoria = new Categoria(nomCategoria, idProveedor.getIdProveedor());

			base.insertarCategoriaProducto(categoria);

			dispose();
		}

		

	}
	
	private void txtDescKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescKeyReleased

			
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
			int idP = categoria.getIdProveedor();
		

			modeloTabla.setValueAt(id, i, 0);
			modeloTabla.setValueAt(categoria, i, 1);
			//modeloTabla.setValueAt(idP, i, 2);
		

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

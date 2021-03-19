package vistaCategoria;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;
import modelo.Categoria;
import modelo.Proveedor;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class EditarCategoria extends JDialog {

	private final JPanel contentPanel = new JPanel();
	protected JTextField txtDesc;
	protected JLabel lblId;
	protected Controlador base;
	private DefaultComboBoxModel modeloCategoria = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EditarCategoria dialog = new EditarCategoria();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EditarCategoria() {
		base = new Controlador();
		
		setBounds(100, 100, 295, 156);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			// EDITAR LA CATEGORIA
			public void actionPerformed(ActionEvent e) {
				editarCategoriaActionPerformed(e);
			}
		});
		btnModificar.setIcon(new ImageIcon("/home/ferc/git/SistemaFerreteria21/Iconos_Imagenes/Aceptar1.png"));
		btnModificar.setBounds(80, 62, 135, 54);
		contentPanel.add(btnModificar);

		txtDesc = new JTextField();
		txtDesc.setBounds(80, 12, 144, 27);
		contentPanel.add(txtDesc);
		txtDesc.setColumns(10);

		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(12, 17, 60, 17);
		contentPanel.add(lblCategoria);

		lblId = new JLabel("");
		lblId.setBounds(22, 51, 60, 17);
		contentPanel.add(lblId);
		
		cargarModeloProv();
	}

	private void editarCategoriaActionPerformed(ActionEvent e) {

		int id = Integer.parseInt(lblId.getText());
		String nombre = txtDesc.getText();
		

		Categoria categoria = new Categoria(id, nombre);

		base.actualizarCategoria(categoria);

	
		
		dispose();

	}
	
	public void cargarModeloProv() {

		modeloCategoria = new DefaultComboBoxModel(base.dameProveedores());

	}
}

package vistaProveedores;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;
import modelo.Proveedor;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VMproveedorUpdate extends JDialog {
	protected JTextField txtDesc;
	protected JTextField txtTel;
	protected JTextField txtEmail;
	protected JTextField txtDir;
	protected Controlador base;
	protected	JLabel lblId;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VMproveedorUpdate dialog = new VMproveedorUpdate();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VMproveedorUpdate() {
		base = new Controlador();
		setBounds(100, 100, 370, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(34, 31, 60, 17);
		getContentPane().add(lblNombre);
		
		txtDesc = new JTextField();
		txtDesc.setColumns(10);
		txtDesc.setBounds(112, 22, 214, 35);
		getContentPane().add(txtDesc);
		
		txtTel = new JTextField();
		txtTel.setColumns(10);
		txtTel.setBounds(112, 58, 214, 29);
		getContentPane().add(txtTel);
		
		JLabel lblTel = new JLabel("Tel:");
		lblTel.setBounds(34, 60, 60, 17);
		getContentPane().add(lblTel);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(34, 93, 60, 17);
		getContentPane().add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(112, 87, 214, 29);
		getContentPane().add(txtEmail);
		
		JLabel lblDireccin = new JLabel("Dirección");
		lblDireccin.setBounds(34, 122, 60, 17);
		getContentPane().add(lblDireccin);
		
		txtDir = new JTextField();
		txtDir.setColumns(10);
		txtDir.setBounds(112, 116, 214, 34);
		getContentPane().add(txtDir);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarProveedorActionPerformed(e);
				
			}
		});
		btnGuardar.setIcon(new ImageIcon("/home/ferc/git/SistemaFerreteria21/Iconos_Imagenes/check_mark.png"));
		btnGuardar.setBounds(143, 175, 125, 27);
		getContentPane().add(btnGuardar);
		
		lblId = new JLabel("");
		lblId.setBounds(337, 31, 21, 17);
		getContentPane().add(lblId);
	}

	private void actualizarProveedorActionPerformed(ActionEvent e) {
		
		int id = Integer.parseInt(lblId.getText());
		String nombre = txtDesc.getText();
		String dir = txtDir.getText();
		String mail = txtEmail.getText();
		String tel = txtTel.getText();

		Proveedor proveedor = new Proveedor(id,nombre, dir, mail, tel);
		
		base.actualizarProveedor(proveedor);
		
		dispose();
		
	
		
	}
}

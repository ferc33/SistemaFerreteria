package vistaInventario;

import singleton.VistaCategoriaSigleton;
import java.awt.BorderLayout;
import singleton.VistaProveedoresSigleton;
import vistaCategoria.VistaCategoria;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import controlador.Controlador;
import modelo.Categoria;
import modelo.Conexion;
import modelo.Producto;
import modelo.Proveedor;

import vistaProveedores.VistaProveedores;
import javax.swing.SwingConstants;

import java.awt.GridLayout;

public class MarcoArticulo extends JFrame {
	private JPanel laminaP;
	private JTextField txtCosto;
	private JTextField txtbon1;
	private JTextField txtbon2;
	private JTextField txtbon3;
	private JTextField txtbon4;
	private JTextField txtFlete;
	private JLabel lblCosto;
	private JLabel lblCosto_2;
	private JLabel lblCosto_3;
	private JLabel lblCosto_4;
	private JLabel lblCosto_5;
	private JLabel lblCosto_6;
	private JTextField txtDolar;
	private JLabel lblCosto_7;
	private JTextField txtIva;
	private JLabel lblCosto_8;
	private JTextField txtGanancia;
	private JLabel lblCosto_9;
	private JTextField campoStock;
	private JLabel lblCosto_10;
	private JTextField txtPrecioVenta;
	private JTextField txtIdProducto;
	private JLabel label;
	private JButton btnEliminar;
	private JButton btnUpdate;
	private JButton btnAñadir;
	private File imgArticleFile;
	private JTextField txtNombre;
	private JTextField txtClaveProveedor;
	private JTextField campoBuscarTodo;
	private JScrollPane jScrollPane4;
	private ButtonGroup grupo_botones;
	private Controlador base = new Controlador();
	private Categoria categoria = null;
	private Proveedor proveedor = null;
	private JPanel panel_1;
	private JTable tablaProductos;
	private JButton btnImportar;
	private JLabel imgLabel;
	private JTextField txtIdProduct;
	public JLabel lblIdProveedor;
	public  JLabel lblIdCategoria;
	public JTextField txtCategoria;
	public JTextField txtProveedor;
	
	public MarcoArticulo() {

	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		laminaP = new JPanel();
		laminaP.setBorder(new EmptyBorder(5, 5, 5, 5));
		laminaP.setLayout(new BorderLayout(0, 0));
		getContentPane().add(laminaP);

		initComponents();
		cargarColumnas();
		cargarModeloTabla();

		

		setSize(1000, 670);

		JLabel lblCosto_6_1 = new JLabel("Codigo Art");
		lblCosto_6_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCosto_6_1.setBounds(250, 36, 125, 35);
		getContentPane().add(lblCosto_6_1);

		// Titled borders
		TitledBorder title;
		title = BorderFactory.createTitledBorder("Rubro");

		TitledBorder title2;
		title2 = BorderFactory.createTitledBorder("Proveedores");
		txtIdProduct = new JTextField();
		txtIdProduct.setBounds(526, 421, 114, 21);
		getContentPane().add(txtIdProduct);
		txtIdProduct.setColumns(10);

		JPanel panel = new JPanel();
		panel.setBounds(595, 359, 393, 53);
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(1, 3, 0, 0));

		btnAñadir = new JButton();
		btnAñadir.setIcon(new ImageIcon("/home/ferc/git/SistemaFerreteria21/Iconos_Imagenes/Agregar.png"));
		panel.add(btnAñadir);

		btnAñadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				btnAñadirActionPerformed(arg0);

			}

		});
		btnAñadir.setFont(new Font("Comfortaa", Font.PLAIN, 12));

		btnUpdate = new JButton();
		panel.add(btnUpdate);
		btnUpdate.setIcon(new ImageIcon("/home/ferc/git/SistemaFerreteria21/Iconos_Imagenes/Editar.png"));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnUpdateActionPerformed(arg0);

			}
		});
		btnUpdate.setFont(new Font("Comfortaa", Font.PLAIN, 12));

		btnEliminar = new JButton();
		panel.add(btnEliminar);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnEliminar.setIcon(new ImageIcon("/home/ferc/git/SistemaFerreteria21/Iconos_Imagenes/Eliminar.png"));
		btnEliminar.setFont(new Font("Comfortaa", Font.PLAIN, 12));

		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				abrirVentanaProveedoresActionPerformed(e);

			}
		});
		button.setIcon(new ImageIcon(MarcoArticulo.class.getResource("/Iconos_Imagenes/zoom_iwwn.png")));
		button.setBounds(469, 324, 41, 26);
		getContentPane().add(button);

		JButton btnCategoria = new JButton("");
		btnCategoria.setBounds(468, 279, 41, 26);
		getContentPane().add(btnCategoria);
		btnCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				abrirVentanaCategoriaActionPerformed(e);
				
			}
		});
		btnCategoria.setIcon(new ImageIcon(MarcoArticulo.class.getResource("/Iconos_Imagenes/zoom_iwwn.png")));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(305, 279, 156, 23);
		getContentPane().add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		lblIdCategoria = new JLabel("0");
		panel_2.add(lblIdCategoria, BorderLayout.WEST);
		
		txtCategoria = new JTextField();
		panel_2.add(txtCategoria, BorderLayout.CENTER);
		txtCategoria.setColumns(10);
				 
				 JPanel panel_3 = new JPanel();
				 panel_3.setBounds(305, 324, 154, 26);
				 getContentPane().add(panel_3);
				 		panel_3.setLayout(new BorderLayout(0, 0));
				 		
				 		lblIdProveedor = new JLabel("0");
				 		panel_3.add(lblIdProveedor, BorderLayout.WEST);
				 		
				 		txtProveedor = new JTextField();
				 		panel_3.add(txtProveedor, BorderLayout.CENTER);
				 		txtProveedor.setColumns(10);
		txtIdProduct.setVisible(false);
	}

	// INICIAR LOS COMPONENTESs
	private void initComponents() {
		
		getContentPane().setLayout(null);
		
		lblCosto = new JLabel("Costo");
		lblCosto.setHorizontalAlignment(SwingConstants.CENTER);
		lblCosto.setBounds(0, 68, 125, 35);
		getContentPane().add(lblCosto);

		txtCosto = new JTextField();
		txtCosto.setHorizontalAlignment(SwingConstants.CENTER);
		txtCosto.setText("0.0");
		txtCosto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtCostoKeyPressed(e);
			}
		});
		txtCosto.setBounds(125, 68, 125, 35);
		getContentPane().add(txtCosto);
		txtCosto.setColumns(10);

		lblCosto_6 = new JLabel("Costo Dolar");
		lblCosto_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblCosto_6.setBounds(250, 68, 125, 35);
		getContentPane().add(lblCosto_6);

		txtDolar = new JTextField();
		txtDolar.setHorizontalAlignment(SwingConstants.CENTER);
		txtDolar.setText("0.0");
		txtDolar.setBounds(375, 68, 125, 35);
		txtDolar.setColumns(10);
		getContentPane().add(txtDolar);

		JLabel lblCosto_1 = new JLabel("Bon1%");
		lblCosto_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCosto_1.setBounds(0, 103, 125, 35);
		getContentPane().add(lblCosto_1);

		txtbon1 = new JTextField();
		txtbon1.setHorizontalAlignment(SwingConstants.CENTER);
		txtbon1.setText("0.0");
		txtbon1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtbon1KeyPressed(e);
			}
		});
		txtbon1.setBounds(125, 103, 125, 35);
		txtbon1.setColumns(10);
		getContentPane().add(txtbon1);

		lblCosto_7 = new JLabel("Iva");
		lblCosto_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblCosto_7.setBounds(250, 103, 125, 35);
		getContentPane().add(lblCosto_7);

		txtIva = new JTextField();
		txtIva.setHorizontalAlignment(SwingConstants.CENTER);
		txtIva.setText("0.0");
		txtIva.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtIvaKeyPressed(e);
			}
		});
		txtIva.setBounds(375, 103, 125, 35);
		txtIva.setColumns(10);
		getContentPane().add(txtIva);

		lblCosto_2 = new JLabel("Bon2%");
		lblCosto_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblCosto_2.setBounds(0, 138, 125, 35);
		getContentPane().add(lblCosto_2);

		txtbon2 = new JTextField();
		txtbon2.setHorizontalAlignment(SwingConstants.CENTER);
		txtbon2.setText("0.0");
		txtbon2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtbon2KeyPressed(e);
			}
		});
		txtbon2.setBounds(125, 138, 125, 35);
		txtbon2.setColumns(10);
		getContentPane().add(txtbon2);

		lblCosto_8 = new JLabel("Ganancia");
		lblCosto_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblCosto_8.setBounds(250, 138, 125, 35);
		getContentPane().add(lblCosto_8);

		txtGanancia = new JTextField();
		txtGanancia.setHorizontalAlignment(SwingConstants.CENTER);
		txtGanancia.setText("0.0");
		txtGanancia.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtGananciaKeyPressed(e);
			}
		});
		txtGanancia.setBounds(375, 138, 125, 35);
		txtGanancia.setColumns(10);
		getContentPane().add(txtGanancia);

		lblCosto_3 = new JLabel("Bon3%");
		lblCosto_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblCosto_3.setBounds(0, 173, 125, 35);
		getContentPane().add(lblCosto_3);

		txtbon3 = new JTextField();
		txtbon3.setHorizontalAlignment(SwingConstants.CENTER);
		txtbon3.setText("0.0");
		txtbon3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtbon3KeyPressed(e);
			}
		});
		txtbon3.setBounds(125, 173, 125, 35);
		txtbon3.setColumns(10);
		getContentPane().add(txtbon3);

		lblCosto_9 = new JLabel("Stock");
		lblCosto_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblCosto_9.setBounds(250, 173, 125, 35);
		getContentPane().add(lblCosto_9);

		campoStock = new JTextField();
		campoStock.setHorizontalAlignment(SwingConstants.CENTER);
		campoStock.setText("0");
		campoStock.setBounds(375, 173, 125, 35);
		campoStock.setColumns(10);
		getContentPane().add(campoStock);

		lblCosto_4 = new JLabel("Bon4%");
		lblCosto_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblCosto_4.setBounds(0, 208, 125, 35);
		getContentPane().add(lblCosto_4);

		txtbon4 = new JTextField();
		txtbon4.setHorizontalAlignment(SwingConstants.CENTER);
		txtbon4.setText("0.0");
		txtbon4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtbon4KeyPressed(e);
			}
		});
		txtbon4.setBounds(125, 208, 125, 35);
		txtbon4.setColumns(10);
		getContentPane().add(txtbon4);

		lblCosto_10 = new JLabel("Precio Venta");
		lblCosto_10.setHorizontalAlignment(SwingConstants.CENTER);
		lblCosto_10.setBounds(250, 208, 125, 35);
		getContentPane().add(lblCosto_10);

		txtPrecioVenta = new JTextField();
		txtPrecioVenta.setHorizontalAlignment(SwingConstants.CENTER);
		txtPrecioVenta.setBounds(375, 208, 125, 35);
		txtPrecioVenta.setColumns(10);
		getContentPane().add(txtPrecioVenta);

		lblCosto_5 = new JLabel("Flete%");
		lblCosto_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblCosto_5.setBounds(0, 243, 125, 35);
		getContentPane().add(lblCosto_5);

		txtFlete = new JTextField();
		txtFlete.setHorizontalAlignment(SwingConstants.CENTER);
		txtFlete.setText("0.0");
		txtFlete.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtFleteKeyPressed(e);
			}
		});
		txtFlete.setBounds(125, 243, 125, 35);
		txtFlete.setColumns(10);
		getContentPane().add(txtFlete);

		label = new JLabel("");
		label.setBounds(250, 176, 125, 35);
		getContentPane().add(label);

		txtIdProducto = new JTextField();
		txtIdProducto.setHorizontalAlignment(SwingConstants.CENTER);
		txtIdProducto.setText("0.0");
		txtIdProducto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtidProductoKeyPressed(e);
			}
		});
		txtIdProducto.setBounds(375, 36, 125, 35);
		txtIdProducto.setColumns(10);
		getContentPane().add(txtIdProducto);

		txtNombre = new JTextField();
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtNombreKeyPressed(e);
			}
		});
		txtNombre.setColumns(10);
		txtNombre.setBounds(125, 1, 375, 35);
		getContentPane().add(txtNombre);

		JLabel lblCodigoProveedor = new JLabel("Cod.Proveedor");
		lblCodigoProveedor.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodigoProveedor.setBounds(0, 36, 125, 35);
		getContentPane().add(lblCodigoProveedor);

		JLabel lblProducto = new JLabel("Producto");
		lblProducto.setHorizontalAlignment(SwingConstants.CENTER);
		lblProducto.setBounds(0, 1, 125, 35);
		getContentPane().add(lblProducto);

		txtClaveProveedor = new JTextField();
		txtClaveProveedor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtClaveProveedorKeyPressed(e);
			}
		});
		txtClaveProveedor.setHorizontalAlignment(SwingConstants.CENTER);
		txtClaveProveedor.setText("0.0");

		txtClaveProveedor.setFont(new Font("Comfortaa", Font.PLAIN, 14));
		txtClaveProveedor.setBounds(125, 36, 125, 35);
		getContentPane().add(txtClaveProveedor);

		campoBuscarTodo = new JTextField();
		campoBuscarTodo.setColumns(10);
		campoBuscarTodo.setBounds(33, 386, 366, 26);
		getContentPane().add(campoBuscarTodo);

		grupo_botones = new ButtonGroup();

		JRadioButton buttonDesc = new JRadioButton();
		buttonDesc.setText("Descripcion (F1)");
		buttonDesc.setBounds(20, 420, 137, 23);
		getContentPane().add(buttonDesc);

		JRadioButton buttonCodigo = new JRadioButton();
		buttonCodigo.setText("Codigo (F2)");
		buttonCodigo.setBounds(172, 420, 104, 23);
		getContentPane().add(buttonCodigo);

		JRadioButton buttonProve = new JRadioButton();
		buttonProve.setText("Codigo Prov (F3)");
		buttonProve.setBounds(288, 420, 139, 23);
		getContentPane().add(buttonProve);

		JButton btnLimpiarCampos = new JButton();
		btnLimpiarCampos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				limpiarCampo();
			}
		});
		btnLimpiarCampos.setText("Limpiar");
		btnLimpiarCampos.setBounds(410, 385, 113, 27);
		getContentPane().add(btnLimpiarCampos);

		JPanel panel = new JPanel();
		panel.setBounds(10, 451, 978, 179);
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(100, 0));

		jScrollPane4 = new JScrollPane();
		panel.add(jScrollPane4);

		tablaProductos = new JTable();
		jScrollPane4.setViewportView(tablaProductos);

		panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(575, 12, 413, 300);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		imgLabel = new JLabel("");
		imgLabel.setBounds(1, 1, 411, 287);
		panel_1.add(imgLabel);

		getContentPane().setLayout(null);
		tablaProductos.setModel(modeloTabla);

		btnImportar = new JButton();
		btnImportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				añadirImagen();
			}
		});
		btnImportar.setText("Importar");
		btnImportar.setFont(new Font("Comfortaa", Font.PLAIN, 12));
		btnImportar.setBounds(742, 324, 105, 26);
		getContentPane().add(btnImportar);

		// Obtiene los valores de la tabla con el click
		tablaProductos.setIntercellSpacing(new java.awt.Dimension(4, 4));
		tablaProductos.setRowHeight(25);
		tablaProductos.setModel(modeloTabla);
		tablaProductos.getSelectionModel().addListSelectionListener(

				new ListSelectionListener() {

					public void valueChanged(ListSelectionEvent event) {

						if (!event.getValueIsAdjusting() && (tablaProductos.getSelectedRow() >= 0)) {

							Producto producto = (Producto) modeloTabla.getValueAt(tablaProductos.getSelectedRow(), 2);

							txtIdProduct.setText(String.valueOf(producto.getIdProducto1()));
							txtIdProducto.setText(String.valueOf(producto.getIdProducto()));
							txtNombre.setText(String.valueOf(producto.getNomProducto().toUpperCase()));
							campoStock.setText(String.valueOf(producto.getStockProducto()));
							txtPrecioVenta.setText(String.valueOf(producto.getPrecioVentaProducto()));
							txtCosto.setText(String.valueOf(producto.getPrecioCompraProducto()));
							txtClaveProveedor.setText(producto.getIdProveedorProducto());
							txtIva.setText(String.valueOf(producto.getIva()));
							txtbon1.setText(String.valueOf(producto.getBon1()));
							txtbon2.setText(String.valueOf(producto.getBon2()));
							txtbon3.setText(String.valueOf(producto.getBon3()));
							txtbon4.setText(String.valueOf(producto.getBon4()));
							txtFlete.setText(String.valueOf(producto.getFlete()));
							txtGanancia.setText(String.valueOf(producto.getGanancia()));
							lblIdProveedor.setText(String.valueOf(producto.getIdCategoria()));
							lblIdCategoria.setText(String.valueOf(producto.getIdProveedor()));
					
							int categoria = Integer.parseInt(lblIdProveedor.getText());
							int proveedor = Integer.parseInt(lblIdCategoria.getText());

							txtCategoria.setText(base.dameCategoria(categoria));
							txtProveedor.setText(base.dameProveedor(proveedor));
							txtCategoria.setEnabled(false);
							txtProveedor.setEnabled(false);
							desplegarFoto(producto);
							

						}
					}

				});

	
	}
	

	
	

	public void desplegarFoto(Producto prod) {
		
		ImageIcon ImagenProducto = null;

		try {
			InputStream is = new Controlador().buscarFoto(prod);
			BufferedImage bi = ImageIO.read(is);
			ImagenProducto = new ImageIcon(bi);

			Image imgProd = ImagenProducto.getImage();
			int ancho = imgLabel.getWidth();
			int alto = imgLabel.getHeight();

			ImageIcon icono2 = new ImageIcon(
					ImagenProducto.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));

			imgLabel.setIcon(icono2);

		} catch (Exception e) {
			System.out.println("no hay imagen en la base de datos");
		}

	}

	// METODO QUE AÑADE LA IMAGEN
	public void añadirImagen() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Extension", "jpg", "png", "gif");
		chooser.setFileFilter(filter);

		int returnVal = chooser.showOpenDialog(this);

		if (returnVal == JFileChooser.APPROVE_OPTION) {

			imgArticleFile = chooser.getSelectedFile();

			ImageIcon icono = new ImageIcon(imgArticleFile.getAbsolutePath());

			ImageIcon icono2 = new ImageIcon(
					icono.getImage().getScaledInstance(imgLabel.getWidth(), imgLabel.getHeight(), Image.SCALE_DEFAULT));

			imgLabel.setIcon(icono2);
		}

	}

	private void cargarColumnas() {
		modeloTabla.addColumn("id");
		modeloTabla.addColumn("codigo");
		modeloTabla.addColumn("nombre");
		modeloTabla.addColumn("c.prov");
		modeloTabla.addColumn("venta");
		modeloTabla.addColumn("stock");

		TableColumn Coldi = tablaProductos.getColumn("id");
		TableColumn ColCodigo = tablaProductos.getColumn("codigo");
		TableColumn ColNombre = tablaProductos.getColumn("nombre");
		TableColumn ColProve = tablaProductos.getColumn("c.prov");
		TableColumn ColVent = tablaProductos.getColumn("venta");
		TableColumn ColStock = tablaProductos.getColumn("stock");

		Coldi.setMaxWidth(80);
		Coldi.setMinWidth(10);

		ColCodigo.setMaxWidth(80);
		ColCodigo.setMinWidth(10);

		ColNombre.setMinWidth(200);
		ColNombre.setMaxWidth(450);

		ColProve.setMaxWidth(165);
		ColProve.setMinWidth(50);

		ColVent.setMaxWidth(160);
		ColVent.setMinWidth(90);

		ColStock.setMaxWidth(120);
		ColStock.setMinWidth(10);

	}

	private DefaultTableModel modeloTabla = new DefaultTableModel() {
		@Override
		public final boolean isCellEditable(int row, int column) {
			return false;

		}
	};



	public void cargarModeloTabla() {

		ArrayList<Producto> listaProducto = base.dameProductosBD();
		int numeroProducto = listaProducto.size();

		modeloTabla.setNumRows(numeroProducto);
		Producto producto = new Producto();
		for (int i = 0; i < numeroProducto; i++) {

			producto = listaProducto.get(i);
			int id = producto.getIdProducto1();
			String idClave = producto.getIdProducto();
			String idFabricaProd = producto.getIdProveedorProducto();
			Double pventa = producto.getPrecioVentaProducto();
			double exis = producto.getStockProducto();

			modeloTabla.setValueAt(id, i, 0);
			modeloTabla.setValueAt(idClave, i, 1);
			modeloTabla.setValueAt(producto, i, 2);
			modeloTabla.setValueAt(idFabricaProd, i, 3);
			modeloTabla.setValueAt(pventa, i, 4);
			modeloTabla.setValueAt(exis, i, 5);

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

	// SINGLETON CATEGORIA
	private void abrirVentanaCategoriaActionPerformed(ActionEvent e) {

	    try {

	      JFrame categoria = VistaCategoriaSigleton.getInstance();
	      categoria.setLocationRelativeTo(null);
	      categoria.setVisible(true);

	    } catch (Exception f) {
	      f.printStackTrace();
	    }

	  }

	// SINGLETON CATEGORIA
	private void abrirVentanaProveedoresActionPerformed(ActionEvent e) {

		try {

			JDialog Proveedor = VistaProveedoresSigleton.getInstance();
			Proveedor.setLocationRelativeTo(null);
			Proveedor.setVisible(true);

		} catch (Exception f) {
			f.printStackTrace();
		}

	}

	private void txtClavesKeyPressed(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtClavesKeyPressed

	}// GEN-LAST:event_txtClavesKeyPressed

	// CAMBIAR DE COMPONENTE
	public void hacerFoco(KeyEvent e, Component antecesor, Component sucesor) {

		if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_DOWN) {

			sucesor.requestFocus();
		} else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_ALT) {
			antecesor.requestFocus();

		}
	}

	public String dameSeleccion() {

		return grupo_botones.getSelection().getActionCommand();
	}

	public void buscarPorDescripcion() {

		campoBuscarTodo.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent ke) {

				LimpiarLista();

				String cadena = campoBuscarTodo.getText();

				ArrayList<Producto> listaProductos = base.obtenerProductosPorCadenaTexto(cadena);

				int numeroProducto = listaProductos.size();
				modeloTabla.setNumRows(numeroProducto);
				for (int i = 0; i < numeroProducto; i++) {

					Producto producto = listaProductos.get(i);
					String clave = producto.getIdProducto();
					String nomBre = producto.getNomProducto();
					String idFabricaProd = producto.getIdProveedorProducto();
					Double pventa = producto.getPrecioVentaProducto();
					double exis = producto.getStockProducto();

					modeloTabla.setValueAt(clave, i, 0);
					modeloTabla.setValueAt(producto, i, 1);
					modeloTabla.setValueAt(idFabricaProd, i, 2);
					modeloTabla.setValueAt(pventa, i, 3);
					modeloTabla.setValueAt(exis, i, 4);

				}
			}
		});

	}

	public void buscarPorCodigo() {
		campoBuscarTodo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
					LimpiarLista();

					String cadena = campoBuscarTodo.getText();

					ArrayList<Producto> listaProductos = base.obtenerProductosPorCodigo(cadena);

					int numeroProducto = listaProductos.size();
					modeloTabla.setNumRows(numeroProducto);
					for (int i = 0; i < numeroProducto; i++) {

						Producto producto = listaProductos.get(i);
						String clave = producto.getIdProducto();
						String nomBre = producto.getNomProducto();
						String idFabricaProd = producto.getIdProveedorProducto();
						Double pventa = producto.getPrecioVentaProducto();
						double exis = producto.getStockProducto();

						modeloTabla.setValueAt(clave, i, 0);
						modeloTabla.setValueAt(producto, i, 1);
						modeloTabla.setValueAt(idFabricaProd, i, 2);
						modeloTabla.setValueAt(pventa, i, 3);
						modeloTabla.setValueAt(exis, i, 4);

					}
				}
			}
		});

	}

	public void buscarPorProveedor() {
		campoBuscarTodo.addKeyListener(new KeyAdapter() {

			public void keyReleased(KeyEvent ke) {

				String cadena = campoBuscarTodo.getText();

				LimpiarLista();

				ArrayList<Producto> listaProductos = base.obtenerProductosPorCodigoProveedor(cadena);

				int numeroProducto = listaProductos.size();
				modeloTabla.setNumRows(numeroProducto);
				for (int i = 0; i < numeroProducto; i++) {

					Producto producto = listaProductos.get(i);
					String clave = producto.getIdProducto();
					String nomBre = producto.getNomProducto();
					String idFabricaProd = producto.getIdProveedorProducto();
					Double pventa = producto.getPrecioVentaProducto();
					double exis = producto.getStockProducto();

					modeloTabla.setValueAt(clave, i, 0);
					modeloTabla.setValueAt(producto, i, 1);
					modeloTabla.setValueAt(idFabricaProd, i, 2);
					modeloTabla.setValueAt(pventa, i, 3);
					modeloTabla.setValueAt(exis, i, 4);

				}
			}
		});
	}

	private void limpiarCampo() {
		txtNombre.setText("");
		txtCosto.setText("0.00");
		txtPrecioVenta.setText("0.00");
		txtClaveProveedor.setText("0.00");
		txtbon1.setText("0.00");
		txtbon2.setText("0.00");
		txtbon3.setText("0.00");
		txtbon4.setText("0.00");
		txtFlete.setText("0.00");
		txtIva.setText("0.00");
		txtIdProducto.setText("0.00");
		txtIdProducto.setEnabled(true);
		campoStock.setText("0");
		txtGanancia.setText("0.00");
	}

	private void btnAñadirActionPerformed(ActionEvent evt) {

		añadirArticulo();

	}

	private void añadirArticulo() {
		try {

			String idprod = txtIdProducto.getText();
			String nombre = txtNombre.getText().toUpperCase();
			int stock = Integer.parseInt(campoStock.getText());
			String codigoProveedor = txtClaveProveedor.getText().toUpperCase();
			double pCosto = Double.parseDouble(txtCosto.getText());
			double pVenta = Double.parseDouble(txtPrecioVenta.getText());
			double pDolar = Double.parseDouble(txtDolar.getText());
			double iva = Double.parseDouble(txtIva.getText());
			double dolar = Double.parseDouble(txtDolar.getText());
			double bon1 = Double.parseDouble(txtbon1.getText());
			double bon2 = Double.parseDouble(txtbon2.getText());
			double bon3 = Double.parseDouble(txtbon3.getText());
			double bon4 = Double.parseDouble(txtbon4.getText());
			double flete = Double.parseDouble(txtFlete.getText());
			double ganancia = Double.parseDouble(txtGanancia.getText());
			int cat = Integer.parseInt(lblIdProveedor.getText());
			int pro = Integer.parseInt(lblIdCategoria.getText());

			if (imgArticleFile == null) {

				Producto producto = new Producto(idprod, nombre, stock, codigoProveedor, null, pCosto, pVenta, pDolar,
						0, cat, pro, iva, bon1, bon2, bon3, bon4, flete, ganancia);

				base.insertarProducto(producto);

				cargarModeloTabla();

				JOptionPane.showMessageDialog(this, "No se ha elegido una imagen");

			} else {

				Producto producto = new Producto(idprod, nombre, stock, codigoProveedor, imgArticleFile, pCosto, pVenta,
						pDolar, 0, cat, pro, iva, bon1, bon2, bon3, bon4, flete, ganancia);
				base.insertarProducto(producto);

				JOptionPane.showMessageDialog(this,
						"Se ha guardado con exito el articulo " + producto.getNomProducto());

				cargarModeloTabla();

			}

		} catch (Exception ex) {

			ex.printStackTrace();

		}
	}

	// LIMPIAR CAMPOS
	public void DeleteProducto() {
		try {
			int idProducto = Integer.parseInt(txtIdProduct.getText());
			String id = txtIdProducto.getText();
			String nombre = "";
			int stock = 0;
			String codigoProveedor = "";
			double pCosto = 0;
			double precioVenta = 0;
			double iva = 0;
			double dolar = 0;
			double bon1 = 0;
			double bon2 = 0;
			double bon3 = 0;
			double bon4 = 0;
			double flete = 0;
			double ganancia = 0;
			int existencia = 0;

			precioVenta = pCosto * (1 - (bon1 / 100)) * (1 - (bon2 / 100)) * ((1 - (bon3 / 100)) * (1 - (bon4 / 100))
					* (1 + (flete / 100)) * (1 + (iva / 100)) * (1 + (ganancia / 100)));
			txtPrecioVenta.setText(String.valueOf(precioVenta));

			Producto producto = new Producto(idProducto, id, nombre, stock, codigoProveedor, pCosto, precioVenta,
					existencia, categoria.getIdCategoria(), proveedor.getIdProveedor(), iva, bon1, bon2, bon3, bon4,
					flete, ganancia);

			base.actualizarProducto(producto, false);

			cargarModeloTabla();

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "NO ACTUALIZO IMAGEN");
		}
	}

	public void actualizarProducto() {
		try {

			int idProducto = Integer.parseInt(txtIdProduct.getText());
			String id = txtIdProducto.getText();
			String nombre = txtNombre.getText().toUpperCase();
			int stock = Integer.parseInt(campoStock.getText());
			String codigoProveedor = txtClaveProveedor.getText().toUpperCase();
			double pCosto = Double.parseDouble(txtCosto.getText());
			double pVenta = Double.parseDouble(txtPrecioVenta.getText());
			double pDolar = Double.parseDouble(txtDolar.getText());
			double iva = Double.parseDouble(txtIva.getText());
			double dolar = Double.parseDouble(txtDolar.getText());
			double bon1 = Double.parseDouble(txtbon1.getText());
			double bon2 = Double.parseDouble(txtbon2.getText());
			double bon3 = Double.parseDouble(txtbon3.getText());
			double bon4 = Double.parseDouble(txtbon4.getText());
			double flete = Double.parseDouble(txtFlete.getText());
			double ganancia = Double.parseDouble(txtGanancia.getText());

			int cat = Integer.parseInt(lblIdProveedor.getText());
			int pro = Integer.parseInt(lblIdCategoria.getText());

			if (imgArticleFile == null) {

				Producto producto = new Producto(idProducto, id, nombre, stock, codigoProveedor, null, pCosto, pVenta,
						pDolar, 0, cat, pro, iva, bon1, bon2, bon3, bon4, flete, ganancia);

				base.actualizarProducto(producto, false);

				cargarModeloTabla();

				JOptionPane.showMessageDialog(this, "No se ha elegido una imagen");

			} else {

				Producto producto = new Producto(idProducto, id, nombre, stock, codigoProveedor, imgArticleFile, pCosto,
						pVenta, pDolar, 0, categoria.getIdCategoria(), proveedor.getIdProveedor(), iva, bon1, bon2,
						bon3, bon4, flete, ganancia);
				base.actualizarProducto(producto, true);

				JOptionPane.showMessageDialog(this, "Se actualizó correctamente " + producto.getNomProducto());

				cargarModeloTabla();

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	// MODIFICA EL PRODUCTO SELECCIONADO.
	private void actualizarArticulo() {

		try {
			int idProducto = Integer.parseInt(txtIdProduct.getText());
			String id = txtIdProducto.getText();
			String nombre = txtNombre.getText().toUpperCase();
			double stock = Double.parseDouble(campoStock.getText());
			String codigoProveedor = txtClaveProveedor.getText().toUpperCase();
			double pCosto = Double.parseDouble(txtCosto.getText());
			double precioVenta = Double.parseDouble(txtPrecioVenta.getText());
			double iva = Double.parseDouble(txtIva.getText());
			double dolar = Double.parseDouble(txtDolar.getText());
			double bon1 = Double.parseDouble(txtbon1.getText());
			double bon2 = Double.parseDouble(txtbon2.getText());
			double bon3 = Double.parseDouble(txtbon3.getText());
			double bon4 = Double.parseDouble(txtbon4.getText());
			double flete = Double.parseDouble(txtFlete.getText());
			double ganancia = Double.parseDouble(txtGanancia.getText());
			double existencia = Double.parseDouble(campoStock.getText());
			int cat = Integer.parseInt(lblIdProveedor.getText());
			int pro = Integer.parseInt(lblIdCategoria.getText());

			precioVenta = pCosto * (1 - (bon1 / 100)) * (1 - (bon2 / 100)) * ((1 - (bon3 / 100)) * (1 - (bon4 / 100))
					* (1 + (flete / 100)) * (1 + (iva / 100)) * (1 + (ganancia / 100)));
			txtPrecioVenta.setText(String.valueOf(precioVenta));

			if (imgArticleFile == null) {

				Producto producto = new Producto(idProducto,id,nombre,stock,codigoProveedor,null,pCosto,precioVenta,dolar,existencia,cat,pro,iva,bon1,bon2,bon3,bon4,flete,ganancia);

				base.actualizarProducto(producto, false);

				cargarModeloTabla();

				JOptionPane.showMessageDialog(this, "No se ha elegido una imagen");

			} else {

				Producto producto = new Producto(idProducto, id, nombre, stock, codigoProveedor, imgArticleFile, pCosto,
						precioVenta, dolar, 0, cat, pro, iva, bon1, bon2,	bon3, bon4, flete, ganancia);
				base.actualizarProducto(producto, true);

				cargarModeloTabla();

			}

			cargarModeloTabla();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {
		actualizarArticulo();

	}

	private void txtCostoKeyPressed(java.awt.event.KeyEvent evt) {

		String dameCosto = txtCosto.getText();

		hacerFoco(evt, txtClaveProveedor, txtbon1);

	}

	private void txtIvaKeyPressed(java.awt.event.KeyEvent evt) {
		hacerFoco(evt, txtFlete, txtGanancia);
	}

	private void txtidProductoKeyPressed(java.awt.event.KeyEvent evt) {
		hacerFoco(evt, txtIdProducto, txtClaveProveedor);
	}

	private void txtbon1KeyPressed(java.awt.event.KeyEvent evt) {
		hacerFoco(evt, txtDolar, txtbon2);
	}

	private void txtDolarKeyPressed(java.awt.event.KeyEvent evt) {
		hacerFoco(evt, txtIva, txtbon1);
	}

	private void txtbon2KeyPressed(java.awt.event.KeyEvent evt) {
		hacerFoco(evt, txtbon1, txtbon3);
	}

	private void txtbon3KeyPressed(java.awt.event.KeyEvent evt) {
		hacerFoco(evt, txtbon2, txtbon4);
	}

	private void txtbon4KeyPressed(java.awt.event.KeyEvent evt) {
		hacerFoco(evt, txtbon3, txtFlete);
	}

	private void txtFleteKeyPressed(java.awt.event.KeyEvent evt) {
		hacerFoco(evt, txtbon4, txtIva);
	}

	private void txtNombreKeyPressed(java.awt.event.KeyEvent evt) {
		hacerFoco(evt, txtNombre, txtIdProducto);
	}

	private void txtGananciaKeyPressed(java.awt.event.KeyEvent evt) {
		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
			double pCosto = Double.parseDouble(txtCosto.getText());
			// double dolar = Double.parseDouble(txtDolar.getText());
			double iva = Double.parseDouble(txtIva.getText());
			double bon1 = Double.parseDouble(txtbon1.getText());
			double bon2 = Double.parseDouble(txtbon2.getText());
			double bon3 = Double.parseDouble(txtbon3.getText());
			double bon4 = Double.parseDouble(txtbon4.getText());
			double flete = Double.parseDouble(txtFlete.getText());
			double gan = Double.parseDouble(txtGanancia.getText());

			double pVenta = pCosto * (1 - (bon1 / 100)) * (1 - (bon2 / 100)) * ((1 - (bon3 / 100)) * (1 - (bon4 / 100))
					* (1 + (flete / 100)) * (1 + (iva / 100)) * (1 + (gan / 100)));

			txtPrecioVenta.setText(String.valueOf(pVenta));

		}
		hacerFoco(evt, txtPrecioVenta, campoStock);
	}

	private void txtPrecioVentaKeyPressed(java.awt.event.KeyEvent evt) {
		hacerFoco(evt, campoStock, btnAñadir);
	}

	private void campoStockKeyPressed(java.awt.event.KeyEvent evt) {
		hacerFoco(evt, txtGanancia, txtPrecioVenta);
	}

	private void txtClaveProveedorKeyPressed(KeyEvent e) {
		hacerFoco(e, txtIdProducto, txtCosto);
	}
}

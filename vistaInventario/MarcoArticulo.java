package vistaInventario;

import java.awt.BorderLayout;
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
import java.util.Vector;

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
import vistaCategoria.VistaCategoria;
import vistaDolar.VistaDolar;
import vistaProveedores.VistaProveedores;
import javax.swing.SwingConstants;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class MarcoArticulo extends JFrame {
	private JPanel laminaP;
	private JTextField textField;
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
	private JComboBox comboBoxProveedores;
	private JComboBox comboBoxCategoria;
	private JTextField campoBuscarTodo;
	private JScrollPane jScrollPane4;
	private ButtonGroup grupo_botones;
	private DefaultComboBoxModel modeloProveedores = null;
	private DefaultComboBoxModel modeloCategorias = null;
	private Controlador base = new Controlador();
	private ArrayList<Categoria> listaCategorias;
	private ArrayList<Proveedor> listaProveedores;
	private Producto productoSeleccionado = null;
	private Categoria categoriaSeleccionada = null;
	private Proveedor proveedorSeleccionado = null;
	private Connection conn = null;
	private Conexion conexion = null;
	private Categoria categoria = null;
	private Proveedor proveedor = null;
	private boolean Seleccion = false;
	private DecimalFormat df = null;
	private boolean estaActualizando;
	private JPanel panel_1;
	private JTable tablaProductos;
	private VistaProveedores  vistaProve;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					MarcoArticulo frame = new MarcoArticulo();
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

		JButton btnProveedor = new JButton();
		btnProveedor.setIcon(new ImageIcon("/home/ferc/Descargas/agent.png"));
		btnProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 
		        VistaProveedores vistaD = new VistaProveedores();
		        vistaD.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		        vistaD.setVisible(true);
		        vistaD.setAlwaysOnTop(false);
		        vistaD.setLocationRelativeTo(new MarcoArticulo());
			
			}
		});
		btnProveedor.setFont(new Font("Comfortaa", Font.PLAIN, 12));
		btnProveedor.setBounds(875, 53, 55, 50);
		getContentPane().add(btnProveedor);

		JButton btnCategoria = new JButton();
		btnCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				   VistaCategoria vistaD = new VistaCategoria();
			        vistaD.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			        vistaD.setVisible(true);
			        vistaD.setAlwaysOnTop(false);
			        vistaD.setLocationRelativeTo(new MarcoArticulo());

			}
		});
		btnCategoria.setText("Categoria");
		btnCategoria.setFont(new Font("Comfortaa", Font.PLAIN, 12));
		btnCategoria.setBounds(875, 112, 113, 99);
		getContentPane().add(btnCategoria);

		JButton jButton1 = new JButton();
		jButton1.setText("Actualizar Listas");
		jButton1.setFont(new Font("Decker", Font.PLAIN, 14));
		jButton1.setBounds(875, 370, 113, 99);
		getContentPane().add(jButton1);

		JButton btnListarPorProveedor = new JButton();
		btnListarPorProveedor.setText("Stock");
		btnListarPorProveedor.setBounds(875, 231, 113, 99);
		getContentPane().add(btnListarPorProveedor);

		JButton Exit = new JButton();
		Exit.setText("Eliminar");
		Exit.setFont(new Font("Decker", Font.PLAIN, 14));
		Exit.setBounds(875, 513, 113, 99);
		getContentPane().add(Exit);

		JLabel lblCosto_6_1 = new JLabel("Codigo Art");
		lblCosto_6_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCosto_6_1.setBounds(250, 36, 125, 35);
		getContentPane().add(lblCosto_6_1);
		
		comboBoxCategoria = new JComboBox();
		comboBoxCategoria.setBounds(33, 324, 240, 50);
	
		getContentPane().add(comboBoxCategoria);

		//Titled borders
		TitledBorder title;
		title = BorderFactory.createTitledBorder("Rubro");
		comboBoxCategoria.setBorder(title);
		
		comboBoxProveedores = new JComboBox();
		
		
		comboBoxProveedores.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
			
				if (e.getStateChange() == ItemEvent.SELECTED) {

					Proveedor prove = (Proveedor) comboBoxProveedores.getSelectedItem();
					// GUARDAMOS LO QUE OBTENEMOS DE LA BASE DE DATOS EN UN MODELO
					DefaultComboBoxModel modCategoria = new DefaultComboBoxModel(base.dameCategorias(prove.getIdProveedor()));
					// MOSTRAMOS EL MODELO EN EL COMBOBOX
					comboBoxCategoria.setModel(modCategoria);
				}
			
			}
		});
		
		comboBoxProveedores.setBounds(279, 324, 244, 50);
		cargarModeloProv();
		getContentPane().add(comboBoxProveedores);
		
		TitledBorder title2;
		title2 = BorderFactory.createTitledBorder("Proveedores");
		comboBoxProveedores.setBorder(title2);
		txtIdProduct = new JTextField();
		txtIdProduct.setBounds(681, 422, 114, 21);
		getContentPane().add(txtIdProduct);
		txtIdProduct.setColumns(10);
		
		txtProveedor = new JTextField();
		txtProveedor.setBounds(288, 289, 234, 26);
		getContentPane().add(txtProveedor);
		txtProveedor.setColumns(10);
		
		txtCategoria = new JTextField();
		txtCategoria.setColumns(10);
		txtCategoria.setBounds(32, 290, 234, 26);
		getContentPane().add(txtCategoria);
		txtIdProduct.setVisible(false);
	}
	


	// INICIAR LOS COMPONENTESs
		private void initComponents() {

			conexion = new Conexion();
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

			btnEliminar = new JButton();
			btnEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});
			btnEliminar.setIcon(new ImageIcon(LaminaPrincipal.class.getResource("/Iconos_Imagenes/SALIR_ROJO.png")));
			btnEliminar.setFont(new Font("Comfortaa", Font.PLAIN, 12));
			btnEliminar.setBounds(758, -5, 66, 50);
			getContentPane().add(btnEliminar);

			btnUpdate = new JButton();
			btnUpdate.setIcon(new ImageIcon(LaminaPrincipal.class.getResource("/Iconos_Imagenes/update.png")));
			btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					btnUpdateActionPerformed(arg0);

				}
			});
			btnUpdate.setFont(new Font("Comfortaa", Font.PLAIN, 12));
			btnUpdate.setBounds(670, -5, 66, 50);
			getContentPane().add(btnUpdate);

			btnAñadir = new JButton();
			btnAñadir.setIcon(new ImageIcon(LaminaPrincipal.class.getResource("/Iconos_Imagenes/add.png")));
			btnAñadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					btnAñadirActionPerformed(arg0);

				}

			});
			btnAñadir.setFont(new Font("Comfortaa", Font.PLAIN, 12));
			btnAñadir.setBounds(582, -5, 66, 50);
			getContentPane().add(btnAñadir);

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
			campoBuscarTodo.setBounds(32, 386, 378, 26);
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
			panel.setBounds(10, 451, 853, 179);
			getContentPane().add(panel);
			panel.setLayout(new BorderLayout(100, 0));

			jScrollPane4 = new JScrollPane();
			panel.add(jScrollPane4);

			tablaProductos = new JTable();
			jScrollPane4.setViewportView(tablaProductos);

			panel_1 = new JPanel();
			panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_1.setBounds(534, 57, 329, 290);
			getContentPane().add(panel_1);
			panel_1.setLayout(new BorderLayout(0, 0));

			imgLabel = new JLabel("");
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
			btnImportar.setBounds(553, 359, 105, 50);
			getContentPane().add(btnImportar);

			btnAñadir_2 = new JButton();
			btnAñadir_2.setText("Guardar");
			btnAñadir_2.setFont(new Font("Comfortaa", Font.PLAIN, 12));
			btnAñadir_2.setBounds(691, 360, 104, 50);
			getContentPane().add(btnAñadir_2);

			// Obtiene los valores de la tabla con el click
			tablaProductos.setIntercellSpacing(new java.awt.Dimension(4, 4));
			tablaProductos.setRowHeight(25);
			tablaProductos.setModel(modeloTabla);
			tablaProductos.getSelectionModel().addListSelectionListener(

					new ListSelectionListener() {

						public void valueChanged(ListSelectionEvent event) {

							if (!event.getValueIsAdjusting() && (tablaProductos.getSelectedRow() >= 0)) {

								Producto producto = (Producto) modeloTabla.getValueAt(tablaProductos.getSelectedRow(), 1);
														
								txtIdProduct.setText(String.valueOf(producto.getIdProducto1()));
								txtIdProducto.setText(String.valueOf(producto.getIdProducto()));
								txtNombre.setText(producto.getNomProducto().toUpperCase());
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
								txtProveedor.setText(String.valueOf(producto.getNomProveedor()));
								txtCategoria.setText(String.valueOf(producto.getNomCategoria()));	
								txtProveedor.setEnabled(false);
								txtCategoria.setEnabled(false);
							
								
								comboBoxProveedores.setSelectedItem(producto.getNomProveedor());
								comboBoxCategoria.setSelectedItem(producto.getNomCategoria());

								desplegarFoto(producto);
								productoSeleccionado = producto;
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
				
				ImageIcon icono2 = new ImageIcon(ImagenProducto.getImage().getScaledInstance(ancho,alto, Image.SCALE_DEFAULT));

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

		

		private void addProveedores() {

			modeloProveedores.addElement(proveedor);
			comboBoxProveedores.setModel(modeloProveedores);
			modeloProveedores.setSelectedItem(proveedor);

		}

		private void cargarColumnas() {

			modeloTabla.addColumn("codigo");
			modeloTabla.addColumn("nombre");
			modeloTabla.addColumn("c.prov");
			modeloTabla.addColumn("venta");
			modeloTabla.addColumn("stock");
			modeloTabla.addColumn("Categoria");
			modeloTabla.addColumn("Proveedor");

			TableColumn ColCodigo = tablaProductos.getColumn("codigo");
			TableColumn ColNombre = tablaProductos.getColumn("nombre");
			TableColumn ColProve = tablaProductos.getColumn("c.prov");
			TableColumn Colcant = tablaProductos.getColumn("venta");
			TableColumn ColVen = tablaProductos.getColumn("stock");
			TableColumn ColCat = tablaProductos.getColumn("Categoria");
			TableColumn ColPro = tablaProductos.getColumn("Proveedor");

			ColCodigo.setMaxWidth(80);
			ColCodigo.setMinWidth(10);

			ColNombre.setMinWidth(200);
			ColNombre.setMaxWidth(350);

			ColProve.setMaxWidth(150);
			ColProve.setMinWidth(50);

			Colcant.setMaxWidth(90);
			Colcant.setMinWidth(90);

			ColVen.setMaxWidth(80);
			ColVen.setMinWidth(10);
			
			ColCat.setMaxWidth(100);
			ColCat.setMinWidth(10);
			
			ColPro.setMaxWidth(100);
			ColPro.setMinWidth(10);

		}

		private void cargarModeloCat() {

			categoria = new Categoria();
			modeloCategorias = new DefaultComboBoxModel(base.dameCategorias());
			comboBoxCategoria.setModel(modeloCategorias);

		}

		public void cargarModeloProv() {

			proveedor = new Proveedor();
			modeloProveedores = new DefaultComboBoxModel(base.dameProveedores());
			comboBoxProveedores.setModel(modeloProveedores);
			
		}

		private DefaultTableModel modeloTabla = new DefaultTableModel() {
			@Override
			public final boolean isCellEditable(int row, int column) {
				return false;

			}
		};
		
		private JButton btnImportar;
		private JButton btnAñadir_2;
		private JLabel imgLabel;
		private JComboBox comboBoxCategoria_1;
		private JTextField txtIdProduct;
		private JTextField txtProveedor;
		private JTextField txtCategoria;
	

		public void cargarModeloTabla() {

			ArrayList<Producto> listaProducto = base.obtenerProductos();
			int numeroProducto = listaProducto.size();
			modeloTabla.setNumRows(numeroProducto);

			for (int i = 0; i < numeroProducto; i++) {

				Producto producto = listaProducto.get(i);
				String nomBre = producto.getNomProducto();
				String idClave = producto.getIdProducto();
				String idFabricaProd = producto.getIdProveedorProducto();
				Double pventa = producto.getPrecioVentaProducto();
				int exis = producto.getStockProducto();
				String nomProveedor = producto.getNomProveedor();
				String nomCategoria = producto.getNomCategoria();

				modeloTabla.setValueAt(idClave, i, 0);
				modeloTabla.setValueAt(producto, i, 1);
				modeloTabla.setValueAt(idFabricaProd, i, 2);
				modeloTabla.setValueAt(pventa, i, 3);
				modeloTabla.setValueAt(exis, i, 4);
				modeloTabla.setValueAt(nomCategoria, i, 5);
				modeloTabla.setValueAt(nomProveedor, i, 6);

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
						int exis = producto.getStockProducto();

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

						String cadena =campoBuscarTodo.getText();

						ArrayList<Producto> listaProductos = base.obtenerProductosPorCodigo(cadena);

						int numeroProducto = listaProductos.size();
						modeloTabla.setNumRows(numeroProducto);
						for (int i = 0; i < numeroProducto; i++) {

							Producto producto = listaProductos.get(i);
							String clave = producto.getIdProducto();
							String nomBre = producto.getNomProducto();
							String idFabricaProd = producto.getIdProveedorProducto();
							Double pventa = producto.getPrecioVentaProducto();
							int exis = producto.getStockProducto();

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
						int exis = producto.getStockProducto();

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
				
				Categoria cat  = (Categoria)comboBoxCategoria.getSelectedItem();
				Proveedor pro = (Proveedor)comboBoxProveedores.getSelectedItem();
			

				if (imgArticleFile == null) {

					Producto producto = new Producto(idprod, nombre, stock, codigoProveedor, null, pCosto, pVenta, pDolar,
							0, cat.getIdCategoria(), pro.getIdProveedor(), iva, bon1, bon2, bon3, bon4, flete, ganancia);

					base.insertarProducto(producto);

					cargarModeloTabla();

					JOptionPane.showMessageDialog(this, "No se ha elegido una imagen");

				} else {

					Producto producto = new Producto(idprod, nombre, stock, codigoProveedor, imgArticleFile, pCosto, pVenta,
							pDolar, 0, cat.getIdCategoria(),  pro.getIdProveedor(), iva, bon1, bon2, bon3, bon4, flete, ganancia);
					base.insertarProducto(producto);

					JOptionPane.showMessageDialog(this,
							"Se ha guardado con exito el articulo " + producto.getNomProducto());

					cargarModeloTabla();

				}

			} catch (Exception ex) {

				ex.printStackTrace();

			}
		}

		
		//LIMPIAR CAMPOS
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

				Producto producto = new Producto(idProducto,id, nombre, stock, codigoProveedor, pCosto, precioVenta, existencia,
						categoria.getIdCategoria(), proveedor.getIdProveedor(), iva, bon1, bon2, bon3, bon4, flete,
						ganancia);

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

				Proveedor proveedor = (Proveedor) comboBoxProveedores.getSelectedItem();
				Categoria categoria = (Categoria) comboBoxCategoria.getSelectedItem();

				if (imgArticleFile == null) {

					Producto producto = new Producto( idProducto,id,nombre,stock, codigoProveedor, null, pCosto, pVenta, pDolar, 0,
							categoria.getIdCategoria(), proveedor.getIdProveedor(), iva, bon1, bon2, bon3, bon4, flete,
							ganancia);

					base.actualizarProducto(producto, false);

					cargarModeloTabla();

					JOptionPane.showMessageDialog(this, "No se ha elegido una imagen");

				} else {

					Producto producto = new Producto(idProducto,id,nombre, stock, codigoProveedor, imgArticleFile, pCosto, pVenta, pDolar,
							0, categoria.getIdCategoria(), proveedor.getIdProveedor(), iva, bon1, bon2, bon3, bon4, flete,
							ganancia);
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
				int stock = Integer.parseInt(campoStock.getText());
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
				int existencia = Integer.parseInt(campoStock.getText());

				Proveedor proveedor = (Proveedor) comboBoxProveedores.getSelectedItem();
				Categoria categoria = (Categoria) comboBoxCategoria.getSelectedItem();

				precioVenta = pCosto * (1 - (bon1 / 100)) * (1 - (bon2 / 100)) * ((1 - (bon3 / 100)) * (1 - (bon4 / 100))
						* (1 + (flete / 100)) * (1 + (iva / 100)) * (1 + (ganancia / 100)));
				txtPrecioVenta.setText(String.valueOf(precioVenta));
				// double precioActualizado = Double.parseDouble(pVenta);

				if (imgArticleFile == null) {

					Producto producto = new Producto(idProducto,id, nombre, stock, codigoProveedor, null, pCosto, precioVenta, dolar,
							0, categoria.getIdCategoria(), proveedor.getIdProveedor(), iva, bon1, bon2, bon3, bon4, flete,
							ganancia);

					base.actualizarProducto(producto, false);

					cargarModeloTabla();

					JOptionPane.showMessageDialog(this, "No se ha elegido una imagen");

				} else {

					Producto producto = new Producto(idProducto,id, nombre, stock, codigoProveedor, imgArticleFile, pCosto,
							precioVenta, dolar, 0, categoria.getIdCategoria(), proveedor.getIdProveedor(), iva, bon1, bon2,
							bon3, bon4, flete, ganancia);
					base.actualizarProducto(producto, true);

					JOptionPane.showMessageDialog(this,
							"Se ha guardado con exito el articulo " + producto.getNomProducto());

					cargarModeloTabla();

				}

				Producto producto = new Producto(idProducto,id, nombre, stock, codigoProveedor, pCosto, precioVenta, existencia,
						categoria.getIdCategoria(), proveedor.getIdProveedor(), iva, bon1, bon2, bon3, bon4, flete,
						ganancia);

				base.actualizarProducto(producto, false);

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
					hacerFoco(e,txtIdProducto,txtCosto);
		}
	}
	


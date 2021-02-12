package vistaInventario;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import controlador.BaseDatos;
import modelo.Categoria;
import modelo.Conexion;
import modelo.Producto;
import modelo.Proveedor;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class LaminaPrincipal extends JPanel {
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
	private BaseDatos base = new BaseDatos();
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
	

	/**
	 * Create the panel.
	 */
	public LaminaPrincipal() {
		initComponents();
		cargarColumnas();
		cargarModeloTabla();

		setSize(900, 558);

		JButton btnProveedor = new JButton();
		btnProveedor.setText("Proveedor");
		btnProveedor.setFont(new Font("Comfortaa", Font.PLAIN, 12));
		btnProveedor.setBounds(786, 1, 113, 99);
		add(btnProveedor);

		JButton btnCategoria = new JButton();
		btnCategoria.setText("Categoria");
		btnCategoria.setFont(new Font("Comfortaa", Font.PLAIN, 12));
		btnCategoria.setBounds(786, 112, 113, 99);
		add(btnCategoria);

		JButton jButton1 = new JButton();
		jButton1.setText("Actualizar Listas");
		jButton1.setFont(new Font("Decker", Font.PLAIN, 14));
		jButton1.setBounds(786, 334, 113, 99);
		add(jButton1);

		JButton btnListarPorProveedor = new JButton();
		btnListarPorProveedor.setText("Stock");
		btnListarPorProveedor.setBounds(786, 223, 113, 99);
		add(btnListarPorProveedor);

		JButton Exit = new JButton();
		Exit.setText("Eliminar");
		Exit.setFont(new Font("Decker", Font.PLAIN, 14));
		Exit.setBounds(786, 445, 113, 99);
		add(Exit);

		JButton jButton3 = new JButton();
		jButton3.setText("Salir");
		jButton3.setBounds(1130, 278, 113, 99);
		add(jButton3);

		txtCategoria = new JTextField();
		txtCategoria.setBounds(387, 327, 114, 25);
		add(txtCategoria);
		txtCategoria.setColumns(10);

		txtProveedores = new JTextField();
		txtProveedores.setColumns(10);
		txtProveedores.setBounds(261, 327, 114, 25);
		add(txtProveedores);

		JLabel lblCosto_6_1 = new JLabel("Codigo Art");
		lblCosto_6_1.setBounds(250, 36, 125, 35);
		add(lblCosto_6_1);

		comboBoxCategoria = new JComboBox();
		comboBoxCategoria.setBounds(10, 289, 178, 24);
		cargarModeloCat();
		add(comboBoxCategoria);

		comboBoxProveedores = new JComboBox();
		comboBoxProveedores.setBounds(197, 290, 178, 24);
		cargarModeloProv();
		add(comboBoxProveedores);

	}

	// INICIAR LOS COMPONENTESs
	private void initComponents() {

		conexion = new Conexion();
		setLayout(null);
		lblCosto = new JLabel("Costo");
		lblCosto.setBounds(0, 68, 125, 35);
		add(lblCosto);

		txtCosto = new JTextField();
		txtCosto.setText("0.0");
		txtCosto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtCostoKeyPressed(e);
			}
		});
		txtCosto.setBounds(125, 68, 125, 35);
		add(txtCosto);
		txtCosto.setColumns(10);

		lblCosto_6 = new JLabel("Costo Dolar");
		lblCosto_6.setBounds(250, 68, 125, 35);
		add(lblCosto_6);

		txtDolar = new JTextField();
		txtDolar.setText("0.0");
		txtDolar.setBounds(375, 68, 125, 35);
		txtDolar.setColumns(10);
		add(txtDolar);

		JLabel lblCosto_1 = new JLabel("Bon1%");
		lblCosto_1.setBounds(0, 103, 125, 35);
		add(lblCosto_1);

		txtbon1 = new JTextField();
		txtbon1.setText("0.0");
		txtbon1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtbon1KeyPressed(e);
			}
		});
		txtbon1.setBounds(125, 103, 125, 35);
		txtbon1.setColumns(10);
		add(txtbon1);

		lblCosto_7 = new JLabel("Iva");
		lblCosto_7.setBounds(250, 103, 125, 35);
		add(lblCosto_7);

		txtIva = new JTextField();
		txtIva.setText("0.0");
		txtIva.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtIvaKeyPressed(e);
			}
		});
		txtIva.setBounds(375, 103, 125, 35);
		txtIva.setColumns(10);
		add(txtIva);

		lblCosto_2 = new JLabel("Bon2%");
		lblCosto_2.setBounds(0, 138, 125, 35);
		add(lblCosto_2);

		txtbon2 = new JTextField();
		txtbon2.setText("0.0");
		txtbon2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtbon2KeyPressed(e);
			}
		});
		txtbon2.setBounds(125, 138, 125, 35);
		txtbon2.setColumns(10);
		add(txtbon2);

		lblCosto_8 = new JLabel("Ganancia");
		lblCosto_8.setBounds(250, 138, 125, 35);
		add(lblCosto_8);

		txtGanancia = new JTextField();
		txtGanancia.setText("0.0");
		txtGanancia.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtGananciaKeyPressed(e);
			}
		});
		txtGanancia.setBounds(375, 138, 125, 35);
		txtGanancia.setColumns(10);
		add(txtGanancia);

		lblCosto_3 = new JLabel("Bon3%");
		lblCosto_3.setBounds(0, 173, 125, 35);
		add(lblCosto_3);

		txtbon3 = new JTextField();
		txtbon3.setText("0.0");
		txtbon3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtbon3KeyPressed(e);
			}
		});
		txtbon3.setBounds(125, 173, 125, 35);
		txtbon3.setColumns(10);
		add(txtbon3);

		lblCosto_9 = new JLabel("Stock");
		lblCosto_9.setBounds(250, 173, 125, 35);
		add(lblCosto_9);

		campoStock = new JTextField();
		campoStock.setText("0");
		campoStock.setBounds(375, 173, 125, 35);
		campoStock.setColumns(10);
		add(campoStock);

		lblCosto_4 = new JLabel("Bon4%");
		lblCosto_4.setBounds(0, 208, 125, 35);
		add(lblCosto_4);

		txtbon4 = new JTextField();
		txtbon4.setText("0.0");
		txtbon4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtbon4KeyPressed(e);
			}
		});
		txtbon4.setBounds(125, 208, 125, 35);
		txtbon4.setColumns(10);
		add(txtbon4);

		lblCosto_10 = new JLabel("Precio Venta");
		lblCosto_10.setBounds(250, 208, 125, 35);
		add(lblCosto_10);

		txtPrecioVenta = new JTextField();
		txtPrecioVenta.setBounds(375, 208, 125, 35);
		txtPrecioVenta.setColumns(10);
		add(txtPrecioVenta);

		lblCosto_5 = new JLabel("Flete%");
		lblCosto_5.setBounds(0, 243, 125, 35);
		add(lblCosto_5);

		txtFlete = new JTextField();
		txtFlete.setText("0.0");
		txtFlete.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtFleteKeyPressed(e);
			}
		});
		txtFlete.setBounds(125, 243, 125, 35);
		txtFlete.setColumns(10);
		add(txtFlete);

		label = new JLabel("");
		label.setBounds(250, 176, 125, 35);
		add(label);

		txtIdProducto = new JTextField();
		txtIdProducto.setText("0.0");
		txtIdProducto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtidProductoKeyPressed(e);
			}
		});
		txtIdProducto.setBounds(375, 36, 125, 35);
		txtIdProducto.setColumns(10);
		add(txtIdProducto);

		btnEliminar = new JButton();
		btnEliminar.setIcon(new ImageIcon(LaminaPrincipal.class.getResource("/Iconos_Imagenes/SALIR_ROJO.png")));
		btnEliminar.setFont(new Font("Comfortaa", Font.PLAIN, 12));
		btnEliminar.setBounds(703, 1, 66, 50);
		add(btnEliminar);

		btnUpdate = new JButton();
		btnUpdate.setIcon(new ImageIcon(LaminaPrincipal.class.getResource("/Iconos_Imagenes/update.png")));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnUpdateActionPerformed(arg0);

			}
		});
		btnUpdate.setFont(new Font("Comfortaa", Font.PLAIN, 12));
		btnUpdate.setBounds(615, 1, 66, 50);
		add(btnUpdate);

		btnAñadir = new JButton();
		btnAñadir.setIcon(new ImageIcon(LaminaPrincipal.class.getResource("/Iconos_Imagenes/add.png")));
		btnAñadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				btnAñadirActionPerformed(arg0);

			}

		});
		btnAñadir.setFont(new Font("Comfortaa", Font.PLAIN, 12));
		btnAñadir.setBounds(527, 1, 66, 50);
		add(btnAñadir);

		txtNombre = new JTextField();
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtNombreKeyPressed(e);
			}
		});
		txtNombre.setColumns(10);
		txtNombre.setBounds(125, 1, 375, 35);
		add(txtNombre);

		JLabel lblCodigoProveedor = new JLabel("Cod.Proveedor");
		lblCodigoProveedor.setBounds(0, 36, 125, 35);
		add(lblCodigoProveedor);

		JLabel lblProducto = new JLabel("Producto");
		lblProducto.setBounds(0, 1, 125, 35);
		add(lblProducto);

		txtClaveProveedor = new JTextField();
		txtClaveProveedor.setText("0.0");
		txtClaveProveedor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtClaveProveedorKeyPressed(e);
			}
		});
		txtClaveProveedor.setFont(new Font("Comfortaa", Font.PLAIN, 14));
		txtClaveProveedor.setBounds(125, 36, 125, 35);
		add(txtClaveProveedor);

		campoBuscarTodo = new JTextField();
		campoBuscarTodo.setColumns(10);
		campoBuscarTodo.setBounds(0, 327, 250, 26);
		add(campoBuscarTodo);

		grupo_botones = new ButtonGroup();

		JRadioButton buttonDesc = new JRadioButton();
		buttonDesc.setText("Descripcion (F1)");
		buttonDesc.setBounds(0, 361, 137, 23);
		add(buttonDesc);

		JRadioButton buttonCodigo = new JRadioButton();
		buttonCodigo.setText("Codigo (F2)");
		buttonCodigo.setBounds(152, 361, 104, 23);
		add(buttonCodigo);

		JRadioButton buttonProve = new JRadioButton();
		buttonProve.setText("Codigo Prov (F3)");
		buttonProve.setBounds(268, 361, 139, 23);
		add(buttonProve);

		JButton btnLimpiarCampos = new JButton();
		btnLimpiarCampos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				limpiarCampo();
			}
		});
		btnLimpiarCampos.setText("Limpiar");
		btnLimpiarCampos.setBounds(388, 289, 113, 27);
		add(btnLimpiarCampos);

		JPanel panel = new JPanel();
		panel.setBounds(10, 392, 764, 155);
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		jScrollPane4 = new JScrollPane();
		panel.add(jScrollPane4, BorderLayout.WEST);

		tablaProductos = new JTable();
		jScrollPane4.setViewportView(tablaProductos);

		panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(526, 68, 243, 198);
		add(panel_1);
		panel_1.setLayout(null);

		imgLabel = new JLabel("");
		imgLabel.setBounds(12, 12, 219, 174);
		panel_1.add(imgLabel);

		setLayout(null);
		tablaProductos.setModel(modeloTabla);

		btnImportar = new JButton();
		btnImportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				añadirImagen();
			}
		});
		btnImportar.setText("Importar");
		btnImportar.setFont(new Font("Comfortaa", Font.PLAIN, 12));
		btnImportar.setBounds(527, 277, 105, 50);
		add(btnImportar);

		btnAñadir_2 = new JButton();
		btnAñadir_2.setText("Guardar");
		btnAñadir_2.setFont(new Font("Comfortaa", Font.PLAIN, 12));
		btnAñadir_2.setBounds(665, 278, 104, 50);
		add(btnAñadir_2);

		// Obtiene los valores de la tabla con el click
		tablaProductos.setIntercellSpacing(new java.awt.Dimension(4, 4));
		tablaProductos.setRowHeight(25);
		tablaProductos.setModel(modeloTabla);
		tablaProductos.getSelectionModel().addListSelectionListener(

				new ListSelectionListener() {

					public void valueChanged(ListSelectionEvent event) {

						if (!event.getValueIsAdjusting() && (tablaProductos.getSelectedRow() >= 0)) {

							Producto producto = (Producto) modeloTabla.getValueAt(tablaProductos.getSelectedRow(), 1);

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
							txtCategoria.setText(String.valueOf(comboBoxCategoria.getSelectedItem()));
							txtProveedores.setText(String.valueOf(comboBoxProveedores.getSelectedItem()));
							
							
							
							desplegarFoto(producto);
							productoSeleccionado = producto;
						}
					}

				});

	}

	public void desplegarFoto(Producto prod) {

		ImageIcon ImagenProducto = null;

		try {
			InputStream is = new BaseDatos().buscarFoto(prod);
			BufferedImage bi = ImageIO.read(is);
			ImagenProducto = new ImageIcon(bi);

			ImageIcon icono2 = new ImageIcon(ImagenProducto.getImage().getScaledInstance(imgLabel.getWidth(),
					imgLabel.getHeight(), Image.SCALE_DEFAULT));

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

	// METODO QUE ACTUALIZA EL JCOMBOX CATEGORIA//
	/*
	 * private void addCategoria(Categoria categoria) {
	 * modeloCategorias.addElement(categoria);
	 * comboBoxCategoria.setModel(modeloCategorias);
	 * modeloCategorias.setSelectedItem(categoria);
	 * 
	 * }
	 */

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

		TableColumn ColCodigo = tablaProductos.getColumn("codigo");
		TableColumn ColNombre = tablaProductos.getColumn("nombre");
		TableColumn ColProve = tablaProductos.getColumn("c.prov");
		TableColumn Colcant = tablaProductos.getColumn("venta");
		TableColumn ColVen = tablaProductos.getColumn("stock");

		ColCodigo.setMaxWidth(80);
		ColCodigo.setMinWidth(10);

		ColNombre.setMinWidth(300);
		ColNombre.setMaxWidth(500);

		ColProve.setMaxWidth(150);
		ColProve.setMinWidth(50);

		Colcant.setMaxWidth(50);
		Colcant.setMinWidth(50);

		ColVen.setMaxWidth(80);
		ColVen.setMinWidth(10);

	}

	private void cargarModeloCat() {

		categoria = new Categoria();
		modeloCategorias = new DefaultComboBoxModel(base.dameCategorias());
		comboBoxCategoria.setModel(modeloCategorias);

	}

	private void cargarModeloProv() {

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
	private JTextField txtCategoria;
	private JTextField txtProveedores;
	private JLabel imgLabel;
	private JComboBox comboBoxCategoria_1;

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

			modeloTabla.setValueAt(idClave, i, 0);
			modeloTabla.setValueAt(producto, i, 1);
			modeloTabla.setValueAt(idFabricaProd, i, 2);
			modeloTabla.setValueAt(pventa, i, 3);
			modeloTabla.setValueAt(exis, i, 4);

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

					int cadena = Integer.parseInt(campoBuscarTodo.getText());

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

				if (cadena.isEmpty()) {
					System.out.println("PEDERNA");
				}

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
			int idc = comboBoxCategoria.getSelectedIndex();
			int idp = comboBoxProveedores.getSelectedIndex();

			if (imgArticleFile == null) {

				Producto producto = new Producto(idprod, nombre, stock, codigoProveedor, null, pCosto, pVenta, pDolar,
						0, idc, idp, iva, bon1, bon2, bon3, bon4, flete, ganancia);

				base.insertarProducto(producto);

				cargarModeloTabla();

				JOptionPane.showMessageDialog(this, "No se ha elegido una imagen");

			} else {

				Producto producto = new Producto(idprod, nombre, stock, codigoProveedor, imgArticleFile, pCosto, pVenta,
						pDolar, 0, idc, idp, iva, bon1, bon2, bon3, bon4, flete, ganancia);
				base.insertarProducto(producto);

				JOptionPane.showMessageDialog(this,
						"Se ha guardado con exito el articulo " + producto.getNomProducto());

				cargarModeloTabla();

			}

		} catch (Exception ex) {

			ex.printStackTrace();

		}
	}

	public void DeleteProducto() {
		try {
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

			Producto producto = new Producto(id, nombre, stock, codigoProveedor, pCosto, precioVenta, existencia,
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

				Producto producto = new Producto(nombre, stock, codigoProveedor, null, pCosto, pVenta, pDolar, 0,
						categoria.getIdCategoria(), proveedor.getIdProveedor(), iva, bon1, bon2, bon3, bon4, flete,
						ganancia);

				base.actualizarProducto(producto, false);

				cargarModeloTabla();

				JOptionPane.showMessageDialog(this, "No se ha elegido una imagen");

			} else {

				Producto producto = new Producto(nombre, stock, codigoProveedor, imgArticleFile, pCosto, pVenta, pDolar,
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

				Producto producto = new Producto(id, nombre, stock, codigoProveedor, null, pCosto, precioVenta, dolar,
						0, categoria.getIdCategoria(), proveedor.getIdProveedor(), iva, bon1, bon2, bon3, bon4, flete,
						ganancia);

				base.actualizarProducto(producto, false);

				cargarModeloTabla();

				JOptionPane.showMessageDialog(this, "No se ha elegido una imagen");

			} else {

				Producto producto = new Producto(id, nombre, stock, codigoProveedor, imgArticleFile, pCosto,
						precioVenta, dolar, 0, categoria.getIdCategoria(), proveedor.getIdProveedor(), iva, bon1, bon2,
						bon3, bon4, flete, ganancia);
				base.actualizarProducto(producto, true);

				JOptionPane.showMessageDialog(this,
						"Se ha guardado con exito el articulo " + producto.getNomProducto());

				cargarModeloTabla();

			}

			Producto producto = new Producto(id, nombre, stock, codigoProveedor, pCosto, precioVenta, existencia,
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
		actualizarProducto();
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

	private void txtClaveProveedorKeyPressed(java.awt.event.KeyEvent evt) {
		// TODO add your handling code here:
		hacerFoco(evt, txtProveedores, txtCosto);
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
}
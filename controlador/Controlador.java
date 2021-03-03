package controlador;

import modelo.Categoria;
import modelo.DetalleVenta;
import modelo.Producto;
import modelo.Proveedor;
import modelo.Ventas;
import modelo.innerjoin.Productos;
import vistaProveedores.VistaProveedores;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.table.DefaultTableModel;

import modelo.Clientes;
import modelo.Conexion;
import modelo.Dolar;
import modelo.Presupuesto;
import modelo.Usuarios;

public class Controlador {

	Connection Conexion = null;
	PreparedStatement prep = null;
	Statement st = null;
	ResultSet rs = null;
	Conexion conn = null;
	private File imagen = new File("/home/ferc/git/sistemaFerreteria/src/Iconos_Imagenes/camera.png");

	public Controlador() {
		conn = new Conexion();
		Conexion = conn.getConexion();

	}

	// AUTENTIFICACION DE USUARIO
	public boolean login(Usuarios usr) {

		String sql = "SELECT ID,USUARIO,PASSWORD,ID_TIPO_USUARIO,ID_EMPLEADO FROM USUARIOS WHERE USUARIOS=?";

		try {
			prep = Conexion.prepareStatement(sql);
			prep.setString(1, usr.getUsuario());
			rs = prep.executeQuery();

			if (rs.next()) {

				// si lo que obtengo de la txtpassword es igual al password de bd
				if (usr.getPassword().equals(rs.getString("PASSWORD"))) {
					usr.setId(rs.getInt(1));
					usr.setUsuario(rs.getString(2));
					usr.setId_tipo(rs.getInt(3));
					usr.setId_empleado(rs.getInt(4));

					return true;

				} else {
					return false;
				}
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return false;

	}

	// SELECT DE DOLAR
	public ArrayList<Dolar> obtenerValoresDolar() {

		ArrayList<Dolar> listaDolar = new ArrayList<Dolar>();

		try {

			prep = Conexion.prepareStatement("SELECT * FROM  `db-sistema`.DOLAR ");

			rs = prep.executeQuery();

			while (rs.next()) {

				int idDolar = rs.getInt("ID_DOLAR");
				double valor = rs.getDouble("VALOR");
				String fecha = rs.getString("FECHA");

				Dolar d = new Dolar(idDolar, valor, fecha);

				listaDolar.add(d);

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return listaDolar;

	}

	// UPDATE DE DOLAR
	public void actualizarDolar(Dolar d) {

		try {

			prep = Conexion.prepareStatement("UPDATE `db-sistema`.DOLAR SET VALOR = ?,FECHA = ? WHERE ID_DOLAR = ?");

			prep.setDouble(1, d.getValor());
			prep.setString(2, d.getFecha());
			prep.setInt(3, d.getIdDolar());

			prep.executeUpdate();

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Precios Modificado");

		}
	}

//DOLAR INSERT
	public void insertValorDolar(Dolar d) {
		try {

			String SentenciaSql = "INSERT INTO `db-sistema`.DOLAR (VALOR,FECHA) VALUES(?,?)";

			prep = Conexion.prepareStatement(SentenciaSql);

			prep.setDouble(1, d.getValor());
			prep.setString(2, d.getFecha());

			prep.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//METODO PARA INSERTAR CATEGORIA
	public void insertarCategoriaProducto(Categoria categoria) {

		try {

			String SentenciaSql = "INSERT INTO `db-sistema`.CAT_CATEGORIA (NOM_CATEGORIA,ID_PROVEEDOR) VALUES(?,?) ";

			prep = Conexion.prepareStatement(SentenciaSql);
			prep.setString(1, categoria.getNomCategoria());
			prep.setInt(2, categoria.getIdProveedor());
			

			prep.execute();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

///////////////////////////////METODO PARA INSERTAR EL DETALLE DE VENTA
	/*
	 * public void insertarCliente(Clientes cliente) {
	 * 
	 * try {
	 * 
	 * String sql =
	 * "INSERT INTO CLIENTES (NOMBRE,DIRECCION,TELEFONO,MAIL) VALUES (?,?,?,?)";
	 * 
	 * prep = Conexion.prepareStatement(sql); prep.setString(1,
	 * cliente.getNombre().toUpperCase()); prep.setString(2,
	 * cliente.getDireccion()); prep.setString(3, cliente.getTel());
	 * prep.setString(4, cliente.getMail()); prep.execute();
	 * 
	 * } catch (SQLException ex) { ex.printStackTrace(); } }
	 */
	// METODO PARA INSERTAR CLIENTES EN BASE DE DATOS

	public void insertarDetalleVenta(DetalleVenta detalle) {

		try {

			String sql = "INSERT INTO `db-sistema`.DETALLE_VENTA (ID_VENTA, ID_PROD, CANTIDAD_VENDIDA) VALUES (?,?,?)";

			prep = Conexion.prepareStatement(sql);
			prep.setLong(1, detalle.getIdVenta());
			prep.setString(2, detalle.getCantidadVendidad());
			prep.execute();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
////////////////////////////////////ACTUALIZAR PROVEEDORES

	public void actualizarProveedor(Proveedor proveedor) {

		try {

			prep = Conexion.prepareStatement(
					"UPDATE `db-sistema`.CAT_PROVEEDORES SET NOM_PROVEEDOR = ?, DIR_PROVEEDOR=?,TEL_PROVEEDOR=?,EMAIL_PROVEEDOR = ? WHERE ID_PROVEEDOR = ?");

			prep.setString(1, proveedor.getNomProveedor());
			prep.setString(2, proveedor.getDirProveedor());
			prep.setString(3, proveedor.getTelProveedor());
			prep.setString(4, proveedor.getMailProveedor());
			prep.setInt(5, proveedor.getIdProveedor());

			prep.executeUpdate();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "SE ACTUALIZO CORRECTAMENTE " + proveedor.getNomProveedor());

		}
	}

/////////////////////////////////// ACTUALIZADOR PRODUCTO
	public void actualizarProducto(Producto producto, boolean cambiarFoto) {

		try {

			if (cambiarFoto == true) {

				File fileFoto = producto.getFotoProducto();

				FileInputStream fis = new FileInputStream(fileFoto);

				prep = Conexion.prepareStatement(
						"UPDATE `db-sistema`.CAT_PRODUCTOS SET  ID_PROD = ?,NOM_PROD = ?, ID_PROVEEDOR_PROD = ?"
								+ ", STOCK_PROD= ?,FOTO_PROD= ?,PRECIO_COMPRA_PROD= ?, PRECIO_VENTA_PROD= ?"
								+ ",ID_PROVEEDOR=?,ID_CATEGORIA=?,IVA = ?"
								+ ",DOLAR=?,BON1 = ?,BON2 = ?,BON3 = ?,BON4 = ?"
								+ ",FLETE = ?,GANANCIA = ? WHERE ID_PRODUCTO= ?");
				prep.setString(1, producto.getIdProducto());
				prep.setString(2, producto.getNomProducto());
				prep.setString(3, producto.getIdProveedorProducto());
				prep.setInt(4, producto.getStockProducto());
				prep.setBlob(5, fis);
				prep.setDouble(6, producto.getPrecioCompraProducto());
				prep.setDouble(7, producto.getPrecioVentaProducto());
				prep.setInt(8, producto.getIdProveedor());
				prep.setInt(9, producto.getIdCategoria());
				prep.setDouble(10, producto.getIva());
				prep.setDouble(11, producto.getDolar());
				prep.setDouble(12, producto.getBon1());
				prep.setDouble(13, producto.getBon2());
				prep.setDouble(14, producto.getBon3());
				prep.setDouble(15, producto.getBon4());
				prep.setDouble(16, producto.getFlete());
				prep.setDouble(17, producto.getGanancia());
				prep.setInt(18, producto.getIdProducto1());

				prep.executeUpdate();
				// CUANDO NO SE MODIFICA LA IMAGEN
			} else {

				// FileInputStream fis = new FileInputStream(imagen);

				prep = Conexion.prepareStatement(
						"UPDATE `db-sistema`.CAT_PRODUCTOS SET  ID_PROD =?,NOM_PROD = ?, ID_PROVEEDOR_PROD = ?"
								+ ", STOCK_PROD= ?,PRECIO_COMPRA_PROD= ?, PRECIO_VENTA_PROD= ?"
								+ ",ID_PROVEEDOR=?,ID_CATEGORIA=?,IVA = ?"
								+ ",DOLAR=?,BON1 = ?,BON2 = ?,BON3 = ?,BON4 = ?"
								+ ",FLETE = ?,GANANCIA = ? WHERE ID_PRODUCTO = ?");

				prep.setString(1, producto.getIdProducto());
				prep.setString(2, producto.getNomProducto());
				prep.setString(3, producto.getIdProveedorProducto());
				prep.setInt(4, producto.getStockProducto());
				// prep.setBlob(5, fis);
				prep.setDouble(5, producto.getPrecioCompraProducto());
				prep.setDouble(6, producto.getPrecioVentaProducto());
				prep.setInt(7, producto.getIdProveedor());
				prep.setInt(8, producto.getIdCategoria());
				prep.setDouble(9, producto.getIva());
				prep.setDouble(10, producto.getDolar());
				prep.setDouble(11, producto.getBon1());
				prep.setDouble(12, producto.getBon2());
				prep.setDouble(13, producto.getBon3());
				prep.setDouble(14, producto.getBon4());
				prep.setDouble(15, producto.getFlete());
				prep.setDouble(16, producto.getGanancia());
				prep.setInt(17, producto.getIdProducto1());
				prep.executeUpdate();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

////////////////////////////////////////METODO PARA INSERTAR PRODUCTOS
	public void insertarProducto(Producto producto) {

		try {

			File fileFoto = producto.getFotoProducto();

			if (fileFoto != null) {

				FileInputStream fis = new FileInputStream(fileFoto);

				String sql = "INSERT INTO `db-sistema`.CAT_PRODUCTOS (ID_PROD, NOM_PROD, ID_PROVEEDOR_PROD, STOCK_PROD, FOTO_PROD,PRECIO_COMPRA_PROD, PRECIO_VENTA_PROD, EXISTENCIA_PROD,ID_PROVEEDOR,ID_CATEGORIA,IVA,BON1,BON2,BON3,BON4,FLETE,GANANCIA) "
						+ "VALUES(?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?)";

				prep = Conexion.prepareStatement(sql);
				prep.setString(1, producto.getIdProducto());
				prep.setString(2, producto.getNomProducto());
				prep.setString(3, producto.getIdProveedorProducto());
				prep.setDouble(4, producto.getStockProducto());
				prep.setBlob(5, fis);
				prep.setDouble(6, producto.getPrecioCompraProducto());
				prep.setDouble(7, producto.getPrecioVentaProducto());
				prep.setDouble(8, producto.getExistenciasProducto());
				prep.setInt(9, producto.getIdProveedor());
				prep.setInt(10, producto.getIdCategoria());
				prep.setDouble(11, producto.getIva());
				prep.setDouble(12, producto.getBon1());
				prep.setDouble(13, producto.getBon2());
				prep.setDouble(14, producto.getBon3());
				prep.setDouble(15, producto.getBon4());
				prep.setDouble(16, producto.getFlete());
				prep.setDouble(17, producto.getGanancia());

				prep.execute();
			} else {

				String sql = "INSERT INTO `db-sistema`.CAT_PRODUCTOS (ID_PROD, NOM_PROD, ID_PROVEEDOR_PROD, STOCK_PROD, FOTO_PROD,PRECIO_COMPRA_PROD, PRECIO_VENTA_PROD, EXISTENCIA_PROD,ID_PROVEEDOR,ID_CATEGORIA,IVA,BON1,BON2,BON3,BON4,FLETE,GANANCIA) "
						+ "VALUES(?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?)";

				prep = Conexion.prepareStatement(sql);
				prep.setString(1, producto.getIdProducto());
				prep.setString(2, producto.getNomProducto());
				prep.setString(3, producto.getIdProveedorProducto());
				prep.setDouble(4, producto.getStockProducto());
				prep.setString(5, null);
				prep.setDouble(6, producto.getPrecioCompraProducto());
				prep.setDouble(7, producto.getPrecioVentaProducto());
				prep.setDouble(8, producto.getExistenciasProducto());
				prep.setInt(9, producto.getIdProveedor());
				prep.setInt(10, producto.getIdCategoria());
				prep.setDouble(11, producto.getIva());
				prep.setDouble(12, producto.getBon1());
				prep.setDouble(13, producto.getBon2());
				prep.setDouble(14, producto.getBon3());
				prep.setDouble(15, producto.getBon4());
				prep.setDouble(16, producto.getFlete());
				prep.setDouble(17, producto.getGanancia());

				prep.execute();
			}

		} catch (SQLException | FileNotFoundException ex) {

			ex.printStackTrace();
		}

	}
	
	public ArrayList<Productos> dameProductoDeLaTabla() {

		ArrayList<Productos> listaProductos = new ArrayList<Productos>();

		try {

			prep = Conexion.prepareStatement("SELECT ID_PROD, NOM_PROD, ID_PROVEEDOR_PROD, STOCK_PROD, PRECIO_VENTA_PROD, CAT_CATEGORIA.NOM_CATEGORIA,CAT_PROVEEDORES.NOM_PROVEEDOR"
					+ "  FROM CAT_PRODUCTOS LEFT JOIN CAT_CATEGORIA ON CAT_CATEGORIA.ID_CATEGORIA=CAT_PRODUCTOS.ID_CATEGORIA"
					+ " LEFT JOIN CAT_PROVEEDORES ON CAT_PROVEEDORES. ID_PROVEEDOR=CAT_PRODUCTOS.ID_PROVEEDOR");
			rs = prep.executeQuery();

			while (rs.next()) {
				// ( clave, nombre, descripcion, stock, codigoProveedor
				String idProducto = rs.getString("ID_PROD");
				String nomProd = rs.getString("NOM_PROD");				
				String idProveedorProd = rs.getString("ID_PROVEEDOR_PROD");
				Double stock = rs.getDouble("STOCK_PROD");
				Double pVenta = rs.getDouble("PRECIO_VENTA_PROD");
				String nomCategoria  = rs.getString("NOM_CATEGORIA");
				String nomProveedor = rs.getString("NOM_PROVEEDOR");
				
				
				Productos productos1= new Productos(idProducto,nomProd,idProveedorProd,nomProveedor,nomCategoria,stock,pVenta);
						listaProductos.add(productos1);

			}

		} catch (SQLException ex) {
			ex.printStackTrace();

		}
		return listaProductos;

	}
	
	
	
	public ArrayList<Producto> obtenerProductos() {

		ArrayList<Producto> listaProductos = new ArrayList<Producto>();

		try {

			prep = Conexion.prepareStatement("SELECT ID_PRODUCTO, ID_PROD, NOM_PROD, ID_PROVEEDOR_PROD, STOCK_PROD, PRECIO_VENTA_PROD,PRECIO_COMPRA_PROD,EXISTENCIA_PROD,IVA,DOLAR, BON1, BON2, BON3, BON4, FLETE, GANANCIA,CAT_CATEGORIA.NOM_CATEGORIA,CAT_PROVEEDORES.NOM_PROVEEDOR\n"
					+ "FROM CAT_PRODUCTOS \n"
					+ "LEFT JOIN CAT_CATEGORIA \n"
					+ "ON CAT_CATEGORIA.ID_CATEGORIA=CAT_PRODUCTOS.ID_CATEGORIA\n"
					+ "LEFT JOIN CAT_PROVEEDORES\n"
					+ "ON CAT_PROVEEDORES.ID_PROVEEDOR=CAT_PRODUCTOS.ID_PROVEEDOR");

			rs = prep.executeQuery();

			while (rs.next()) {
				
				int idProducto = rs.getInt("ID_PRODUCTO");
				String idprod = rs.getString("ID_PROD");
				String nomprod = rs.getString("NOM_PROD");
				String idProveedorProd = rs.getString("ID_PROVEEDOR_PROD");
				int stock = rs.getInt("STOCK_PROD");
				double precioVenta = rs.getDouble("PRECIO_VENTA_PROD");
				double precioCompra = rs.getDouble("PRECIO_COMPRA_PROD");
				int existencia = rs.getInt("EXISTENCIA_PROD");
				double iva = rs.getDouble("IVA");
				double dolar = rs.getDouble("DOLAR");
				double bon1 = rs.getDouble("BON1");
				double bon2 = rs.getDouble("BON2");
				double bon3 = rs.getDouble("BON3");
				double bon4 = rs.getDouble("BON4");
				double flete = rs.getDouble("FLETE");
				double ganancia = rs.getDouble("GANANCIA");
				String nom_proveedor = rs.getString("NOM_PROVEEDOR");
				String nom_Categoria  = rs.getString("NOM_CATEGORIA");

				Producto producto = new Producto(idProducto, idprod, nomprod, stock, idProveedorProd, precioCompra,
						precioVenta, existencia,iva, bon1, bon2, bon3, bon4, flete,	ganancia,nom_proveedor,nom_Categoria);
				listaProductos.add(producto);

			
			}

		} catch (SQLException ex) {
			ex.printStackTrace();

		}
		System.out.println(listaProductos);
		return listaProductos;

	}

	public ArrayList<Presupuesto> getPresupuestoPorID(int numPresupuesto) {

		ArrayList<Presupuesto> listarPresupuesto = new ArrayList<Presupuesto>();

		try {
			prep = Conexion.prepareStatement(
					"SELECT CODIGO_PROD,CANTIDAD_PRODUCTOS,NOM_PROD,PRECIO_VENTA_PROD,PRECIO_VENTA_PROD * CANTIDAD_PRODUCTOS,STOCK_PROD FROM CAT_PRESUPUESTO INNER JOIN CAT_PRODUCTOS ON CAT_PRESUPUESTO.ID_PROD=CAT_PRODUCTOS.ID_PROD WHERE NUM_PRESUPUESTO="
							+ numPresupuesto);

			rs = prep.executeQuery();

			while (rs.next()) {
				// ( clave, nombre, descripcion, stock, codigoProveedor
				int id = rs.getInt("CODIGO_PROD");
				int cant = rs.getInt("CANTIDAD_PRODUCTOS");
				String nomProd = rs.getString(3);
				double precioVenta = rs.getDouble("PRECIO_VENTA_PROD");
				double importe = rs.getDouble(5);
				int stock = rs.getInt(6);

				Presupuesto p = new Presupuesto(id, cant, nomProd, precioVenta, importe, stock);

				listarPresupuesto.add(p);

			}

		} catch (SQLException ex) {
			ex.printStackTrace();

		}
		return listarPresupuesto;

	}

	public ArrayList<Presupuesto> getPresupuesto() {

		ArrayList<Presupuesto> listarPresupuesto = new ArrayList<Presupuesto>();

		try {
			prep = Conexion.prepareStatement("SELECT * FROM CAT_PRESUPUESTO");

			rs = prep.executeQuery();

			while (rs.next()) {
				// ( clave, nombre, descripcion, stock, codigoProveedor
				int id = rs.getInt(1);
				int cod = rs.getInt(2);
				int numPresu = rs.getInt(3);
				String fecha = rs.getString(4);
				int cant_prod = rs.getInt(5);
				double ivaRI = rs.getDouble(6);
				double ivaRNI = rs.getDouble(7);
				int idC = rs.getInt(8);
				int idP = rs.getInt(9);
				double desc = rs.getDouble(10);

				Presupuesto presu = new Presupuesto(numPresu, id, cod, fecha, ivaRNI, ivaRI, cant_prod, idC, idP, desc);

				listarPresupuesto.add(presu);

			}

		} catch (SQLException ex) {
			ex.printStackTrace();

		}
		return listarPresupuesto;

	}

	public void insertarPresupuesto(Presupuesto presu) {

		try {

			prep = Conexion.prepareStatement(
					"INSERT INTO `db-sistema`.CAT_PRESUPUESTO (CODIGO_PROD, NUM_PRESUPUESTO, FECHA, CANTIDAD_PRODUCTOS,IVA_RI,IVA_RNI,ID_CLIENTE,ID_PROD,DESCUENTO) VALUES (?,?,?,?,?,?,?,?,?)");

			prep.setInt(1, presu.getCodigo_prod());
			prep.setInt(2, presu.getNum_presupuesto());
			prep.setString(3, presu.getFecha());
			prep.setInt(4, presu.getCant_productos());
			prep.setDouble(5, presu.getIva());
			prep.setDouble(6, presu.getIva_r());
			prep.setInt(7, presu.getId_cliente());
			prep.setInt(8, presu.getId_prod());
			prep.setDouble(9, presu.getDescuento());

			prep.execute();

		} catch (SQLException ex) {
			ex.printStackTrace();

		}

	}

	public ArrayList<Clientes> getClientes() {

		ArrayList<Clientes> listarCliente = new ArrayList<Clientes>();

		try {
			prep = Conexion.prepareStatement(" SELECT * FROM `db-sistema`.CLIENTES");
			rs = prep.executeQuery();

			while (rs.next()) {
				// ( clave, nombre, descripcion, stock, codigoProveedor
				int idCliente = rs.getInt("ID_CLIENTE");
				String nombre = rs.getString("NOMBRE");
				String direccion = rs.getString("DIRECCION");
				String mail = rs.getString("MAIL");
				String telefono = rs.getString("TELEFONO");

				Clientes cliente = new Clientes(idCliente, nombre, direccion, mail, telefono);

				listarCliente.add(cliente);

			}

		} catch (SQLException ex) {
			ex.printStackTrace();

		} finally {
			try {
				rs.close();

			} catch (SQLException ex) {
				ex.printStackTrace();
			}

		}
		return listarCliente;

	}

	public void insertarCliente(Clientes c) {

		try {

			prep = Conexion.prepareStatement(
					"INSERT INTO `db-sistema`.CLIENTES (NOMBRE,DIRECCION,MAIL,TELEFONO) VALUES (?,?,?,?)");

			prep.setString(1, c.getNombre().toUpperCase());
			prep.setString(2, c.getDireccion());
			prep.setString(3, c.getMail());
			prep.setString(4, c.getDireccion());
			prep.execute();

		} catch (SQLException ex) {
			ex.printStackTrace();

		}

	}

	public void insertarProveedor(Proveedor prov) {

		try {

			prep = Conexion.prepareStatement(
					"INSERT INTO `db-sistema`.CAT_PROVEEDORES (NOM_PROVEEDOR, DIR_PROVEEDOR, EMAIL_PROVEEDOR, TEL_PROVEEDOR) VALUES (?,?,?,?)");
			// prep.setInt(1, prov.getIdProveedor());
			prep.setString(1, prov.getNomProveedor().toUpperCase());
			prep.setString(2, prov.getDirProveedor().toUpperCase());
			prep.setString(3, prov.getMailProveedor().toUpperCase());
			prep.setString(4, prov.getTelProveedor());

			prep.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();

		}

	}

	// INSERTAR PRESUPUESTO
////////////////////////////////METODO PARA INSERTAR LA VENTA
	public Long insertarVenta(Ventas venta) {
		Long LastVal = 0l;
		try {

			prep = Conexion.prepareStatement("INSERT INTO `db-sistema`.VENTA (MONTO_VENTA, FECHA_VENTA)VALUES (?,?)");

			prep.setDouble(1, venta.getMontoVenta());

			prep.setDate(2, (Date) venta.getFechaVenta());

			prep.executeUpdate();

			prep.close();

			prep = this.Conexion.prepareStatement("SELECT last_insert_id()");

			rs = prep.executeQuery();

			while (rs.next()) {

				LastVal = rs.getLong("last_insert_id()");

			}

		} catch (SQLException ex) {
			ex.printStackTrace();

		} finally {
			try {
				rs.close();

			} catch (SQLException ex) {
				ex.printStackTrace();
			}

		}
		return LastVal;

	}
	
	
	

//METODO QUE SELECCIONA LOS PROVEEDORES DE LA BASE DE DATOS.
	public Vector<Proveedor> dameProveedores() {

		PreparedStatement ps = null;
		ResultSet rs = null;
		Conexion conn = new Conexion();
		Connection con = conn.getConexion();

		Vector<Proveedor> proveedor = new Vector<Proveedor>();

		Proveedor prov = null;

		try {

			String sql = "SELECT * FROM `db-sistema`.CAT_PROVEEDORES";

			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			prov = new Proveedor();
			prov.setIdProveedor(0);
			prov.setNomProveedor("Selecciona Proveedor");

			proveedor.add(prov);
			while (rs.next()) {
				prov = new Proveedor();
				prov.setIdProveedor(rs.getInt("ID_PROVEEDOR"));
				prov.setNomProveedor(rs.getString("NOM_PROVEEDOR"));
				prov.setDirProveedor(rs.getString("DIR_PROVEEDOR"));
				prov.setTelProveedor(rs.getString("TEL_PROVEEDOR"));
				prov.setMailProveedor(rs.getString("EMAIL_PROVEEDOR"));
				proveedor.add(prov);
			}

			rs.close();

		} catch (Exception e) {
			System.err.print(e.toString());
		}
		// devuelve el vector
		System.out.println(proveedor);
		return proveedor;

	}

	public Vector<Proveedor> dameProveedoresTabla() {

		PreparedStatement ps = null;
		ResultSet rs = null;
		Conexion conn = new Conexion();
		Connection con = conn.getConexion();

		Vector<Proveedor> proveedor = new Vector<Proveedor>();

		Proveedor prov = null;

		try {

			String sql = "SELECT * FROM `db-sistema`.CAT_PROVEEDORES";

			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				prov = new Proveedor();
				prov.setIdProveedor(rs.getInt("ID_PROVEEDOR"));
				prov.setNomProveedor(rs.getString("NOM_PROVEEDOR"));
				prov.setDirProveedor(rs.getString("DIR_PROVEEDOR"));
				prov.setTelProveedor(rs.getString("TEL_PROVEEDOR"));
				prov.setMailProveedor(rs.getString("EMAIL_PROVEEDOR"));
				proveedor.add(prov);
			}

			rs.close();

		} catch (Exception e) {
			System.err.print(e.toString());
		}
		// devuelve el vector
		System.out.println(proveedor);
		return proveedor;

	}

//LISTA LOS PRODUCTOS POR NOMBRE DE PROVEEDOR
	public ArrayList<Productos> dameProductos(int id) {
		ArrayList<Productos> productos = new ArrayList<Productos>();
		Productos prod = null;
		try {
			PreparedStatement ps = null;
			ResultSet rs = null;
			Conexion conn = new Conexion();
			Connection con = conn.getConexion();
			String sql = "SELECT ID_PROD, NOM_PROD, NOM_CATEGORIA, STOCK_PROD FROM CAT_PRODUCTOS "
					+ "INNER JOIN CAT_CATEGORIA ON CAT_PRODUCTOS.ID_CATEGORIA = CAT_CATEGORIA.ID_CATEGORIA WHERE CAT_CATEGORIA.ID_PROVEEDOR = "+id;
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				prod = new Productos();
				prod.setId_prod(rs.getString("ID_PROD"));
				prod.setDescripcion(rs.getString("NOM_PROD"));
				prod.setNom_proveedor(rs.getString("NOM_CATEGORIA"));
				prod.setStock(rs.getDouble("STOCK_PROD"));
				productos.add(prod);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productos;
	}

//METODO QUE SELECCIONA LOS PROVEEDORES DE LA BASE DE DATOS.
	public Vector<Categoria> dameCategorias(Integer idProveedor) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		Conexion conn = new Conexion();
		Connection con = conn.getConexion();

		Vector<Categoria> categoria = new Vector<Categoria>();

		Categoria cat = null;

		try {

			String sql = "SELECT * FROM CAT_CATEGORIA WHERE ID_PROVEEDOR =" + idProveedor;
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			// Añade primer item al comboboxCategoria
			cat = new Categoria();
			cat.setIdCategoria(0);
			cat.setNomCategoria("Selecciona Categoria");
			categoria.add(cat);
			// llena el combobox de categorias
			while (rs.next()) {
				cat = new Categoria();
				cat.setIdCategoria(rs.getInt("ID_CATEGORIA"));
				cat.setNomCategoria(rs.getString("NOM_CATEGORIA"));
				categoria.add(cat);
			}

			rs.close();

		} catch (Exception e) {
			System.err.print(e.toString());
		}


		return categoria;

	}

	public Vector<Categoria> dameCategorias() {

		PreparedStatement ps = null;
		ResultSet rs = null;
		Conexion conn = new Conexion();
		Connection con = conn.getConexion();

		Vector<Categoria> categoria = new Vector<Categoria>();

		Categoria cat = null;

		try {

			String sql = "SELECT * FROM CAT_CATEGORIA";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			// Añade primer item al comboboxCategoria
			cat = new Categoria();
			cat.setIdCategoria(0);
			cat.setNomCategoria("Selecciona Categoria");
			categoria.add(cat);
			// llena el combobox de categorias
			while (rs.next()) {
				cat = new Categoria();
				cat.setIdCategoria(rs.getInt("ID_CATEGORIA"));
				cat.setNomCategoria(rs.getString("NOM_CATEGORIA"));
				categoria.add(cat);
			}

			rs.close();

		} catch (Exception e) {
			System.err.print(e.toString());
		}

		return categoria;

	}

//DEVUELVE LOS PROVEEDORES QUE SELECCIONO DESDE EL COMBOBOX
	public ArrayList<Proveedor> obtenerProveedorPorCriterio(String criterio) {

		ArrayList<Proveedor> listaProductos = new ArrayList<Proveedor>();
		try {

			String sql = "SELECT ID_PROD,NOM_PROD,ID_PROVEEDOR_PROD , PRECIO_VENTA_PROD , STOCK_PROD FROM `db-sistema`.CAT_PRODUCTOS WHERE ID_PROVEEDOR ="
					+ criterio;
			st = Conexion.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {

				String idprod = rs.getString("ID_PROD");
				String nomprod = rs.getString("NOM_PROD");
				String idCodigoProve = rs.getString("ID_PROVEEDOR_PROD");
				double precioVenta = rs.getDouble("PRECIO_VENTA_PROD");
				double stock = rs.getDouble("STOCK_PROD");

			}

		} catch (SQLException e) {
			e.printStackTrace();

		}
		return listaProductos;

	}

	public ArrayList<Producto> obtenerProductosPorCriterio(String criterio) {

		ArrayList<Producto> listaProductos = new ArrayList<Producto>();
		try {

			String sql = "SELECT * FROM `db-sistema`.CAT_PRODUCTOS WHERE ID_PROD =" + criterio;
			st = Conexion.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {

				int idProducto = rs.getInt("ID_PRODUCTO");
				String idprod = rs.getString("ID_PROD");
				String nomprod = rs.getString("NOM_PROD");
				String idCodigoProve = rs.getString("ID_PROVEEDOR_PROD");
				int stock = rs.getInt("STOCK_PROD");
				double precioCompra = rs.getDouble("PRECIO_COMPRA_PROD");
				double precioVenta = rs.getDouble("PRECIO_VENTA_PROD");
				int existencia = rs.getInt("EXISTENCIA_PROD");
				int categoria = rs.getInt("ID_CATEGORIA");
				int idProvee = rs.getInt("ID_PROVEEDOR");
				double iva = rs.getInt("IVA");
				double dolar = rs.getDouble("DOLAR");
				double bon1 = rs.getDouble("BON1");
				double bon2 = rs.getDouble("BON2");
				double bon3 = rs.getDouble("BON3");
				double bon4 = rs.getDouble("BON4");
				double flete = rs.getDouble("FLETE");
				double ganancia = rs.getDouble("GANANCIA");

				Producto producto = new Producto(idProducto, idprod, nomprod, stock, idCodigoProve, precioCompra,
						precioVenta, existencia, categoria, idProvee, iva, bon1, bon2, bon3, bon4, flete, ganancia);
				listaProductos.add(producto);
				

			}

		} catch (SQLException e) {
			e.printStackTrace();

		}
		return listaProductos;

	}

/// ////////////////////////////// METODO PARA REALIZAR BUSQUEDA POR CRITERIO NOMBRES
	public ArrayList<Producto> obtenerProductosPorCadenaTexto(String criterio) {

		ArrayList<Producto> listaProductos = new ArrayList<Producto>();
		try {

			String sql = "SELECT * FROM `db-sistema`.CAT_PRODUCTOS WHERE NOM_PROD LIKE '%" + criterio
					+ "%' ORDER BY NOM_PROD";
			st = Conexion.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {

				int idProducto = rs.getInt("ID_PRODUCTO");
				String idprod = rs.getString("ID_PROD");
				String nomprod = rs.getString("NOM_PROD");
				String idCodigoProve = rs.getString("ID_PROVEEDOR_PROD");
				int stock = rs.getInt("STOCK_PROD");
				double precioCompra = rs.getDouble("PRECIO_COMPRA_PROD");
				double precioVenta = rs.getDouble("PRECIO_VENTA_PROD");
				int existencia = rs.getInt("EXISTENCIA_PROD");
				int categoria = rs.getInt("ID_CATEGORIA");
				int idProvee = rs.getInt("ID_PROVEEDOR");
				double iva = rs.getInt("IVA");
				double dolar = rs.getDouble("DOLAR");
				double bon1 = rs.getDouble("BON1");
				double bon2 = rs.getDouble("BON2");
				double bon3 = rs.getDouble("BON3");
				double bon4 = rs.getDouble("BON4");
				double flete = rs.getDouble("FLETE");
				double ganancia = rs.getDouble("GANANCIA");

				Producto producto = new Producto(idProducto, idprod, nomprod, stock, idCodigoProve, precioCompra,
						precioVenta, existencia, categoria, idProvee, iva, bon1, bon2, bon3, bon4, flete, ganancia);
				listaProductos.add(producto);

			}

		} catch (SQLException e) {
			e.printStackTrace();

		}
		return listaProductos;

	}

//--------------------------------METODO PARA BUSCAR POR CODIGO-----------------------------------------
	public ArrayList<Producto> obtenerProductosPorCodigo(String criterio) {

		ArrayList<Producto> listaProductos = new ArrayList<Producto>();
		try {

			String sql = "SELECT * FROM `db-sistema`.CAT_PRODUCTOS WHERE ID_PROD  = " + criterio;
			st = Conexion.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {
				int idProducto = rs.getInt("ID_PRODUCTO");
				String idprod = rs.getString("ID_PROD");
				String nomprod = rs.getString("NOM_PROD");
				String idCodigoProve = rs.getString("ID_PROVEEDOR_PROD");
				int stock = rs.getInt("STOCK_PROD");
				double precioCompra = rs.getDouble("PRECIO_COMPRA_PROD");
				double precioVenta = rs.getDouble("PRECIO_VENTA_PROD");
				int existencia = rs.getInt("EXISTENCIA_PROD");
				int categoria = rs.getInt("ID_CATEGORIA");
				int idProvee = rs.getInt("ID_PROVEEDOR");
				double iva = rs.getInt("IVA");
				double dolar = rs.getDouble("DOLAR");
				double bon1 = rs.getInt("BON1");
				double bon2 = rs.getInt("BON2");
				double bon3 = rs.getInt("BON3");
				double bon4 = rs.getInt("BON4");
				double flete = rs.getInt("FLETE");
				double ganancia = rs.getInt("GANANCIA");
				String codigoArticulo = rs.getString("CODIGO_ARTICULO");

				Producto producto = new Producto(idProducto, idprod, nomprod, stock, idCodigoProve, precioCompra,
						precioVenta, existencia, categoria, idProvee, iva, bon1, bon2, bon3, bon4, flete, ganancia);
				listaProductos.add(producto);

			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				rs.close();

			} catch (SQLException ex) {
				ex.printStackTrace();
			}

		}
		return listaProductos;

	}

//--------------------------------METODO PARA BUSCAR POR CODIGO PROVEEDOR-----------------------------------------
	public ArrayList<Producto> obtenerProductosPorCodigoProveedor(String criterio) {

		ArrayList<Producto> listaProductos = new ArrayList<Producto>();
		try {
			// SELECT * FROM CAT_PRODUCTOS WHERE NOM_PROD LIKE '%" + criterio + "%' ORDER BY
			// NOM_PROD";
			String sql = "SELECT * FROM `db-sistema`.CAT_PRODUCTOS WHERE ID_PROVEEDOR_PROD LIKE '%" + criterio
					+ "%' ORDER BY NOM_PROD";
			st = Conexion.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {
				int idProducto = rs.getInt("ID_PRODUCTO");
				String idprod = rs.getString("ID_PROD");
				String nomprod = rs.getString("NOM_PROD");
				String idCodigoProve = rs.getString("ID_PROVEEDOR_PROD");
				int stock = rs.getInt("STOCK_PROD");
				double precioCompra = rs.getDouble("PRECIO_COMPRA_PROD");
				double precioVenta = rs.getDouble("PRECIO_VENTA_PROD");
				int existencia = rs.getInt("EXISTENCIA_PROD");
				int categoria = rs.getInt("ID_CATEGORIA");
				int idProvee = rs.getInt("ID_PROVEEDOR");
				double iva = rs.getDouble("IVA");
				double dolar = rs.getDouble("DOLAR");
				double bon1 = rs.getDouble("BON1");
				double bon2 = rs.getDouble("BON2");
				double bon3 = rs.getDouble("BON3");
				double bon4 = rs.getDouble("BON4");
				double flete = rs.getDouble("FLETE");
				double ganancia = rs.getDouble("GANANCIA");

				Producto producto = new Producto(idProducto, idprod, nomprod, stock, idCodigoProve, precioCompra,
						precioVenta, existencia, categoria, idProvee, iva, bon1, bon2, bon3, bon4, flete, ganancia);
				listaProductos.add(producto);

			}

		} catch (SQLException e) {

			e.printStackTrace();

			e.printStackTrace();
		} finally {
			try {
				rs.close();

			} catch (SQLException ex) {
				ex.printStackTrace();
			}

		}
		return listaProductos;

	}

//////////////////////////////////ACTUALIZANDO EL INVENTARIO
	public void actualizarInventario(Producto producto) {

		try {

			prep = Conexion.prepareStatement("UPDATE `db-sistema`.CAT_PRODUCTOS SET STOCK_PROD = ? WHERE ID_PROD=?");

			prep.setInt(1, producto.getStockProducto());

			prep.setString(2, producto.getIdProducto());

			prep.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				rs.close();

			} catch (SQLException ex) {
				ex.printStackTrace();
			}

		}

	}

	public void borrarArticulo(Producto producto) {

		try {

			prep = Conexion.prepareStatement("DELETE FROM `db-sistema`.CAT_PRODUCTOS WHERE ID_PRODUCTO=?");
			prep.setInt(1, producto.getIdProducto1());
			prep.executeQuery();

		} catch (SQLException ex) {
			ex.printStackTrace();

		}

	}

	public InputStream buscarFoto(Producto producto) {
		InputStream streamFoto = null;
		try {

			String sql = "SELECT FOTO_PROD FROM `db-sistema`.CAT_PRODUCTOS WHERE ID_PRODUCTO=?";

			prep = Conexion.prepareStatement(sql);
			prep.setInt(1, producto.getIdProducto1());

			rs = prep.executeQuery();

			while (rs.next()) {
				streamFoto = rs.getBinaryStream("FOTO_PROD");

			}

		} catch (SQLException ex) {
			ex.printStackTrace();

		} finally {
			try {
				rs.close();

			} catch (SQLException ex) {
				ex.printStackTrace();
			}

		}
		return streamFoto;

	}

	///////////// OBTENEMOS LA CATEGORIA PARA APLICAR AL JTEXTFIELD
	public void obtenerCategoria(Categoria categoria) {

		try {

			String sql = "SELECT NOM_CATEGORIA_PROD FROM `db-sistema`.CAT_CATEGORIA;";

			prep = Conexion.prepareStatement(sql);

			st = Conexion.createStatement();

			prep.setString(1, categoria.getNomCategoria().toUpperCase());

			prep.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

}

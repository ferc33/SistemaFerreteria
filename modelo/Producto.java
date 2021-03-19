package modelo;

import java.io.File;

public class Producto {

	private int id;
	private String idProducto;
	private String nomProducto;
	private double stockProducto;
	private String idProveedorProducto;
	private File fotoProducto;
	private double precioCompraProducto;
	private double precioVentaProducto;
	private double existenciasProducto;
	private int idCategoria;
	private int idProveedor;
	private String nomProveedor;
	private String nomCategoria;
	private double dolar;
	private double iva;
	private double ganancia;
	private double bon1;
	private double bon2;
	private double bon3;
	private double bon4;
	private double flete;

	public String getNomProveedor() {
		return nomProveedor;
	}

	public void setNomProveedor(String nomProveedor) {
		this.nomProveedor = nomProveedor;
	}

	public String getNomCategoria() {
		return nomCategoria;
	}

	public void setNomCategoria(String nomCategoria) {
		this.nomCategoria = nomCategoria;
	}

	public Producto(int id, String idProducto, String nomProducto, int stockProducto, String idProveedorProducto,
			double precioCompraProducto, double precioVentaProducto, int existenciasProducto, double iva, double bon1,
			double bon2, double bon3, double bon4, double flete, double ganancia, String nom_proveedor,
			String nom_Categoria) {
		this.id = id;
		this.idProducto = idProducto;
		this.nomProducto = nomProducto;
		this.stockProducto = stockProducto;
		this.idProveedorProducto = idProveedorProducto;
		this.precioCompraProducto = precioCompraProducto;
		this.precioVentaProducto = precioVentaProducto;
		this.dolar = dolar;
		this.iva = iva;
		this.ganancia = ganancia;
		this.bon1 = bon1;
		this.bon2 = bon2;
		this.bon3 = bon3;
		this.bon4 = bon4;
		this.flete = flete;
		this.nomCategoria = nom_Categoria;
		this.nomProveedor = nom_proveedor;

	}

	public Producto(int id, String idProducto, String nomProducto, int stockProducto, String idProveedorProducto,
			double precioCompraProducto, double precioVentaProducto, int existenciasProducto, int idCategoria,
			int idProveedor, double iva, double bon1, double bon2, double bon3, double bon4, double flete,
			double ganancia) {
		this.id = id;
		this.nomProducto=nomProducto;
		this.idProducto = idProducto;
		this.idProveedorProducto = idProveedorProducto;
		this.precioCompraProducto = precioCompraProducto;
		this.precioVentaProducto = precioVentaProducto;
		this.idCategoria = idCategoria;
		this.idProveedor = idProveedor;
		this.dolar = dolar;
		this.iva = iva;
		this.ganancia = ganancia;
		this.bon1 = bon1;
		this.bon2 = bon2;
		this.bon3 = bon3;
		this.bon4 = bon4;
		this.flete = flete;

	}

	public Producto(String id1, String nombre, double stock, String codigoProveedor, File fotoProducto, double pCosto,
			double pVenta, double dolar, int existenciasProducto, int idCategoria, int idProveedor, double iva,
			double bon1, double bon2, double bon3, double bon4, double flete, double ganancia) {

		this.idProducto = id1;
		this.nomProducto = nombre;
		this.stockProducto = stock;
		this.idProveedorProducto = codigoProveedor;
		this.fotoProducto = fotoProducto;
		this.precioCompraProducto = pCosto;
		this.precioVentaProducto = pVenta;
		this.dolar = dolar;
		this.existenciasProducto = existenciasProducto;
		this.idCategoria = idCategoria;
		this.idProveedor = idProveedor;
		this.iva = iva;
		this.ganancia = ganancia;
		this.bon1 = bon1;
		this.bon2 = bon2;
		this.bon3 = bon3;
		this.bon4 = bon4;
		this.flete = flete;
	}

	// UPDATE
	public Producto(int id, String idProducto, String nombre, double stock, String codigoProveedor, File fotoProducto,
			double pCosto, double pVenta, double dolar, double existenciasProducto, int idCategoria, int idProveedor,
			double iva, double bon1, double bon2, double bon3, double bon4, double flete, double ganancia) {

		this.id = id;
		this.idProducto = idProducto;
		this.nomProducto = nombre;
		this.stockProducto = stock;
		this.idProveedorProducto = codigoProveedor;
		this.fotoProducto = fotoProducto;
		this.precioCompraProducto = pCosto;
		this.precioVentaProducto = pVenta;
		this.dolar = dolar;
		this.existenciasProducto = existenciasProducto;
		this.idCategoria = idCategoria;
		this.idProveedor = idProveedor;
		this.iva = iva;
		this.ganancia = ganancia;
		this.bon1 = bon1;
		this.bon2 = bon2;
		this.bon3 = bon3;
		this.bon4 = bon4;
		this.flete = flete;
	}

	public Producto() {
	}
	
	

	/**
	 * @return the nomProducto
	 */
	public String getNomProducto() {
		return nomProducto;
	}

	/**
	 * @param nomProducto the nomProducto to set
	 */
	public void setNomProducto(String nomProducto) {
		this.nomProducto = nomProducto;
	}

	public File getFotoProducto() {
		return fotoProducto;
	}

	public void setFotoProducto(File fotoProducto) {
		this.fotoProducto = fotoProducto;
	}

	public double getDolar() {
		return dolar;
	}

	public void setDolar(double dolar) {
		this.dolar = dolar;
	}

	public double getIva() {
		return iva;
	}

	public void setIva(int iva) {
		this.iva = iva;
	}

	public double getGanancia() {
		return ganancia;
	}

	public void setGanancia(int ganancia) {
		this.ganancia = ganancia;
	}

	public double getBon1() {
		return bon1;
	}

	public void setBon1(int bon1) {
		this.bon1 = bon1;
	}

	public double getBon2() {
		return bon2;
	}

	public void setBon2(int bon2) {
		this.bon2 = bon2;
	}

	public double getBon3() {
		return bon3;
	}

	public void setBon3(int bon3) {
		this.bon3 = bon3;
	}

	public double getBon4() {
		return bon4;
	}

	public void setBon4(int bon4) {
		this.bon4 = bon4;
	}

	public double getFlete() {
		return flete;
	}

	public void setFlete(int flete) {
		this.flete = flete;
	}

	public String getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(String idProd) {
		this.idProducto = idProd;
	}


	public double getStockProducto() {
		return stockProducto;
	}

	public void setStockProducto(double stockProducto) {
		this.stockProducto = stockProducto;
	}

	public String getIdProveedorProducto() {
		return idProveedorProducto;
	}

	public void setIdProveedorProducto(String idProveedorProducto) {
		this.idProveedorProducto = idProveedorProducto;
	}

	public int getIdProducto1() {
		return id;
	}

	public void setIdProducto1(int idProducto1) {
		this.id = idProducto1;
	}

	public double getPrecioCompraProducto() {
		return precioCompraProducto;
	}

	public void setPrecioCompraProducto(double precioCompraProducto) {
		this.precioCompraProducto = precioCompraProducto;
	}

	public double getPrecioVentaProducto() {
		return precioVentaProducto;
	}

	public void setPrecioVentaProducto(double precioVentaProducto) {
		this.precioVentaProducto = precioVentaProducto;
	}

	public double getExistenciasProducto() {
		return existenciasProducto;
	}

	public void setExistenciasProducto(double existenciasProducto) {
		this.existenciasProducto = existenciasProducto;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public int getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}

	@Override
	public String toString() {
		return nomProducto;
	}




}

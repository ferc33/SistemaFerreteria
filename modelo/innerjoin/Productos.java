package modelo.innerjoin;

public class Productos {

	 private String id_prod;
	 private String descripcion;
	 private String cod_proveedor;
	 private Double pVenta;
	 private Double Stock;
	 private String nom_categoria;
	 private String nom_proveedor;
	
	 
	 
	public Productos(String id_prod, String descripcion, String cod_proveedor, String nom_proveedor,String nom_categoria, Double stock, Double pVenta) {
		super();
		this.id_prod = id_prod;
		this.descripcion = descripcion;
		this.cod_proveedor = cod_proveedor;
		this.pVenta=pVenta;
		this.nom_proveedor = nom_proveedor;
		this.nom_categoria = nom_categoria;
		Stock = stock;
	}
	
	public Productos(String id_prod, String descripcion, String cod_proveedor, Double pVenta, Double stock,
			String nom_categoria, String nom_proveedor) {
		super();
		this.id_prod = id_prod;
		this.descripcion = descripcion;
		this.cod_proveedor = cod_proveedor;
		this.pVenta = pVenta;
		Stock = stock;
		this.nom_categoria = nom_categoria;
		this.nom_proveedor = nom_proveedor;
	}




	public Double getpVenta() {
		return pVenta;
	}


	public void setpVenta(Double pVenta) {
		this.pVenta = pVenta;
	}


	public String getNom_categoria() {
		return nom_categoria;
	}


	public void setNom_categoria(String nom_categoria) {
		this.nom_categoria = nom_categoria;
	}


	public Productos() {
		super();
	}
	public String getId_prod() {
		return id_prod;
	}
	public void setId_prod(String id_prod) {
		this.id_prod = id_prod;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getCod_proveedor() {
		return cod_proveedor;
	}
	public void setCod_proveedor(String cod_proveedor) {
		this.cod_proveedor = cod_proveedor;
	}
	public String getNom_proveedor() {
		return nom_proveedor;
	}
	public void setNom_proveedor(String nom_proveedor) {
		this.nom_proveedor = nom_proveedor;
	}
	public Double getStock() {
		return Stock;
	}
	public void setStock(Double stock) {
		Stock = stock;
	}
	@Override
	public String toString() {
		return descripcion;
	}

	 
	
	
	 //INNER JOIN
	
	 
}

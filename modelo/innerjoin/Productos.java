package modelo.innerjoin;

public class Productos {

	 private String id_prod;
	 private String descripcion;
	 private String cod_proveedor;
	 private String nom_proveedor;
	 private Double Stock;
	public Productos(String id_prod, String descripcion, String cod_proveedor, String nom_proveedor, Double stock) {
		super();
		this.id_prod = id_prod;
		this.descripcion = descripcion;
		this.cod_proveedor = cod_proveedor;
		this.nom_proveedor = nom_proveedor;
		Stock = stock;
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
		return "Productos [descripcion=" + descripcion + "]";
	}

	 
	
	
	 //INNER JOIN
	
	 
}

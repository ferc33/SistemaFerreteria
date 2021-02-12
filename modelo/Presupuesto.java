/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

public class Presupuesto {

    private int num_presupuesto;

    private int id_presupuesto;
    private int codigo_prod;
    private String fecha;
    private double precio_unitario;
    private double pNeto;
    private double iva;
    private double iva_r;
    private int cant_productos;
    private int id_cliente;
    private int id_prod;
    private double descuento;
    private String nomProd;
    private int stock;

    public Presupuesto(int id, int cant, String nomProd,double precioVenta, double importe, int stock) {
       this.codigo_prod=id;
       this.cant_productos=cant;
       this.nomProd=nomProd;
       this.precio_unitario=precioVenta;
       this.pNeto=importe;
       this.stock=stock;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    

    @Override
    public String toString() {
        return this.nomProd;
    }
    

    public String getNomProd() {
        return nomProd;
    }

    public void setNomProd(String nomProd) {
        this.nomProd = nomProd;
    }

   

    public Presupuesto() {
    }

    public Presupuesto(int numPresu, int codigoProd, String fecha1, int cantidadProd, int idCliente, int idProd) {
        this.num_presupuesto = numPresu;
        this.codigo_prod = codigoProd;
        this.id_prod = idProd;
        this.fecha = fecha1;
        this.id_cliente = idCliente;
        this.cant_productos = cantidadProd;
    }

    public Presupuesto(int numPresu, int id, int cod, String fecha, double ivaRNI, double ivaRI, int cant_prod, int idC, int idP, double desc) {
    
        this.num_presupuesto=numPresu;
        this.id_presupuesto=id;
        this.codigo_prod=cod;
        this.fecha = fecha;
        this.iva=ivaRNI;
        this.iva_r=ivaRI;
        this.cant_productos=cant_prod;
        this.id_cliente=idC;
        this.id_prod=idP;
        this.descuento=desc;
    
    }

    public int getNum_presupuesto() {
        return num_presupuesto;
    }

    public void setNum_presupuesto(int num_presupuesto) {
        this.num_presupuesto = num_presupuesto;
    }

    public int getId_presupuesto() {
        return id_presupuesto;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public void setId_presupuesto(int id_presupuesto) {
        this.id_presupuesto = id_presupuesto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getpNeto() {
        return pNeto;
    }

    public void setpNeto(double pNeto) {
        this.pNeto = pNeto;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getIva_r() {
        return iva_r;
    }

    public void setIva_r(double iva_r) {
        this.iva_r = iva_r;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_prod() {
        return id_prod;
    }

    public void setId_prod(int id_prod) {
        this.id_prod = id_prod;
    }

    public int getCodigo_prod() {
        return codigo_prod;
    }

    public void setCodigo_prod(int codigo_prod) {
        this.codigo_prod = codigo_prod;
    }

    public int getCant_productos() {
        return cant_productos;
    }

    public void setCant_productos(int cant_productos) {
        this.cant_productos = cant_productos;
    }

    public double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

}


package modelo;


public class DetalleVenta {
    
    private int idDetalleVenta;
    private Long idVenta;
    private String idProducto;
    private String cantidadVendidad;

    public DetalleVenta(Long idVenta, String idProducto, String cantidadVendidad) {
        //this.idDetalleVenta = idDetalleVenta;
        this.idVenta = idVenta;
        this.idProducto = idProducto;
        this.cantidadVendidad = cantidadVendidad;
    }

    public String getCantidadVendidad() {
        return cantidadVendidad;
    }

    public void setCantidadVendidad(String cantidadVendidad) {
        this.cantidadVendidad = cantidadVendidad;
    }

    public int getIdDetalleVenta() {
        return idDetalleVenta;
    }

    public void setIdDetalleVenta(int idDetalleVenta) {
        this.idDetalleVenta = idDetalleVenta;
    }

    public Long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Long idVenta) {
        this.idVenta = idVenta;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }
    
    
}

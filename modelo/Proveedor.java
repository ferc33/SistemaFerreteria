
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class Proveedor {
    
    private int idProveedor;
    private String nomProveedor;
    private String dirProveedor;
    private String mailProveedor;
    private String telProveedor;

    
    public Proveedor(int idProveedor, String nomProveedor, String dirProveedor, String mailProveedor, String telProveedor) {
      
        this.idProveedor=idProveedor;    
       
        this.nomProveedor = nomProveedor;
        this.dirProveedor = dirProveedor;
        this.mailProveedor = mailProveedor;       
        this.telProveedor = telProveedor;
    }

 


       
         public Proveedor() {
		super();
	}

		public int getIdProveedor() {
                  return idProveedor;
         }

         public void setIdProveedor(int idProveedor) {
                  this.idProveedor = idProveedor;
         }

         public String getNomProveedor() {
                  return nomProveedor;
         }

         public void setNomProveedor(String nomProveedor) {
                  this.nomProveedor = nomProveedor;
         }

         public String getDirProveedor() {
                  return dirProveedor;
         }

         public void setDirProveedor(String dirProveedor) {
                  this.dirProveedor = dirProveedor;
         }

         public String getMailProveedor() {
                  return mailProveedor;
         }

         public void setMailProveedor(String mailProveedor) {
                  this.mailProveedor = mailProveedor;
         }


        
         public String getTelProveedor() {
                  return telProveedor;
         }

         public void setTelProveedor(String telProveedor) {
                  this.telProveedor = telProveedor;
         }

        
	@Override
    public String toString(){
        return this.nomProveedor;
    }
    
   
}

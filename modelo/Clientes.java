/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author ferc
 */
public class Clientes {
    
    private int idCliente;
    private String nombre;
    private String direccion;
    private String tel;
    private String mail;

    public Clientes(int idCliente, String nombre, String direccion, String tel, String mail) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.direccion = direccion;
        this.tel = tel;
        this.mail = mail;
    }


    public Clientes(String nombre, String direccion, String tel, String mail) {
       this.nombre=nombre;
       this.direccion= direccion;
       this.tel=tel;
       this.mail=mail;
    }

    public Clientes(String nombre) {
        this.nombre=nombre;
    }
    


    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
    
}

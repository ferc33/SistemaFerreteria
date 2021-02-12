/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

public class Empleado {
    
    private int idEmpleando;
    private String nombre;
    private String correo;
    private String telefono;
    private String dni;

    public Empleado(int idEmpleando, String nombre, String correo, String telefono, String dni) {
        this.idEmpleando = idEmpleando;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.dni = dni;
    }

    public Empleado() {
    }

    
    public int getIdEmpleando() {
        return idEmpleando;
    }

    public void setIdEmpleando(int idEmpleando) {
        this.idEmpleando = idEmpleando;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

public class Dolar {
    
    private int idDolar;
    private double valor;
    private String fecha;
    
    public Dolar(double valor, String fecha) {
        this.valor = valor;
        this.fecha = fecha;
    }

    public double getValor() {
        return valor;
    }

    public Dolar(int idDolar, double valor, String fecha) {
        this.idDolar = idDolar;
        this.valor = valor;
        this.fecha = fecha;
    }

    public int getIdDolar() {
        return idDolar;
    }

    public void setIdDolar(int idDolar) {
        this.idDolar = idDolar;
    }
    
    

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return  (String.valueOf(valor));
    }

    
}

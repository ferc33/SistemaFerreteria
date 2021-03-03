/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Conexion {

    public Conexion() {

    }
    //REGISTRAR USUARIO NUEVO
  

    public static Connection getConexion() {

        Connection con = null;
        String url = "jdbc:mariadb://localhost:3306/db-sistema";
        String user = "root";
        String password = "4435";

        try {

            Class.forName("org.mariadb.jdbc.Driver");

            con = DriverManager.getConnection(url, user, password);

        } catch (Exception e) {
            System.out.println("CONEXION FALLIDA");
        }

        return con;
    }

}

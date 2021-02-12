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

/**
 *
 * @author maxid
 */
public class Conexion {

    public Conexion() {

    }
    //REGISTRAR USUARIO NUEVO
    public boolean registrar(Usuarios usr) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "INSERT INTO USUARIOS (USUARIO, PASSWORD,ID_TIPO_USUARIO,ID_EMPLEADO)"
                + "VALUES(?,?,?,?,?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, usr.getUsuario());
            ps.setString(2, usr.getPassword());
            ps.setInt(3, usr.getId_tipo());
            ps.setInt(4, usr.getId_empleado());
            ps.execute();

            return true;
            //	Si lo anterior no se cumple, devuelve false;	
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Connection getConexion() {

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

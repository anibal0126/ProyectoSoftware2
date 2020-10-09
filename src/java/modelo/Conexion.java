/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author USUARIO
 */
public class Conexion implements Serializable{
    
    Connection connect = null;
    
    /**
     * M e permite conectar a con la base de datos
     *
     * @throws ClassNotFoundException
     */
    public Connection conectar() throws ClassNotFoundException {

        String url = "jdbc:mysql://localhost:3306/software2";

        String username = "root";
        String password = "0711";

        try {

            Class.forName("com.mysql.jdbc.Driver");

            connect = DriverManager.getConnection(url, username, password);
            System.out.println("Connection established" + connect);

        } catch (SQLException ex) {
            System.out.println("in exec");
            System.out.println(ex.getMessage());
        }
        return connect;
    }
    
}

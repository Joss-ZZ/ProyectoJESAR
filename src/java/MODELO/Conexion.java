/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author JhoxiZZ
 */
public class Conexion {
    static String bd = "JesarWeb";
    static String login = "root";
    static String password = "admin";
    static String url = "jdbc:mysql://localhost/"+bd;
    //Variable que guarda la conexion
    Connection conn = null;
    
    public Conexion(){
        try {
            //obtenemos el driver para mysql
            Class.forName("com.mysql.cj.jdbc.Driver");
            //obtenemos la conexion
            conn = DriverManager.getConnection(url, login, password);
            if(conn != null){
                System.out.println("Conexion a base de datos ["+conn+"] OK");
            }
        } catch (SQLException e) //Excepcion ocurrida por la conexion
        {    
            System.out.println("Excepcion conexion: "+e.getMessage());
        } catch (ClassNotFoundException e) //Excepcion ocurrida por no encontrar el Driver
        {
            System.out.println("Excepcion Driver: "+e.getMessage());
        }
    }
    
    // Permite retornar la conexion
    public Connection getConnection(){
        return conn;
    }
    
    // Quitamos de memoria la conexion
    public void desconectar(){
        System.out.println("Base de datos desconectada: ["+conn+"] OK");
        if(conn!=null){
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    } 
}

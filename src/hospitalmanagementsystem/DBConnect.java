/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author dannylam
 */
public class DBConnect {
    
    private static Connection conn;
    private static final String url = "jdbc:mysql://localhost:3306/hospital?zeroDateTimeBehavior=convertToNull";
    private static final String user = "root";
    private static final String pass = "Password1";
    
    public static Connection getConnection() throws SQLException {
        
        conn = DriverManager.getConnection(url,user,pass);
        return conn;
        
    }
    
    public static Connection conn() throws SQLException, ClassNotFoundException{
        if(conn !=null && !conn.isClosed())
            return conn;
        getConnection();
        return conn;
 
    }
    
    
}

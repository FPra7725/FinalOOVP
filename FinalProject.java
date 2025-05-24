/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package finalproject;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Lenovo
 */
public class FinalProject {
    public static Connection ConnectDB(){
        
        Connection conn = null;
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/oovp_final";
            String username = "root";
            String password = "";
            conn = DriverManager.getConnection(url, username, password);
            return conn;
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}

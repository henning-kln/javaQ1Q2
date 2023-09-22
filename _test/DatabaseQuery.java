package _test;

import java.sql.*;
import java.util.Scanner;

public class DatabaseQuery {
    
    public static void main(String[] args) {
        
        // MySQL database connection details
        String url = "jdbc:mysql://localhost:3306/flugzeug";
        String user = "root";
        String password = "";
        
        // Prompt the user to enter a MySQL query
        System.out.print("Enter a MySQL query: ");
        Scanner scanner = new Scanner(System.in);
        String query = scanner.nextLine();
        
        try {
            // Load the MySQL Connector/J driver
            Class.forName("com.mysql.jdbc.Driver");
            
            // Connect to the MySQL database
            Connection conn = DriverManager.getConnection(url, user, password);
            
            // Create a statement object for executing SQL queries
            Statement stmt = conn.createStatement();
            
            // Execute the query and print the results
            ResultSet rs = stmt.executeQuery(query);
            int columns = rs.getMetaData().getColumnCount();
            while (rs.next()) {
            	for (int i =1;i<columns;i++) {
            		System.out.print(rs.getString(i)+"\t");
            	}
            	System.out.println();
                
            }
            
            // Clean up resources
            rs.close();
            stmt.close();
            conn.close();
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}

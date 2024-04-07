package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class test {


	    public static void addUser(String username, String password, String email, String firstname, String lastname) {
	        String sql = "INSERT INTO Users (Username, Password, Email, FirstName, LastName) VALUES (?, ?, ?,?,?)";

	        try (Connection conn = DatabaseUtil.getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {

	            pstmt.setString(1, username);
	            pstmt.setString(2, password); 
	            pstmt.setString(3, email);
	            pstmt.setString(4, firstname);
	            pstmt.setString(5, lastname);
	            
	            int affectedRows = pstmt.executeUpdate();
	            
	            if (affectedRows > 0) {
	                System.out.println("User added successfully!");
	            } else {
	                System.out.println("User could not be added.");
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	           
	        }
	    }

	    public static void main(String[] args) {
	        
	        addUser("user994", "pass994", "user995@example.com","name1","name2");
	    }
	}




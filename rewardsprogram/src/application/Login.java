package application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
	private Stage stage;
	private Scene scene;
	private Parent root;
	
    public void switchtosignup(ActionEvent event) throws IOException {
    	
        Parent	root = FXMLLoader.load(getClass().getResource("Sign up.fxml"));
        
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }
        
        	
        
    public void login(ActionEvent event) throws IOException {
        String inputUsername = username.getText().trim();
        String inputPassword = password.getText().trim();
        
        try {
            if (authenticateUser(inputUsername, inputPassword)) {
                // Login successful
                // Transition to the next screen or display a success message
            	Parent	root = FXMLLoader.load(getClass().getResource("shopPage.fxml"));
                
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            	
            	
            	
            } else {
                // Login failed
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Failed");
                alert.setHeaderText(null);
                alert.setContentText("Username or password is incorrect.");
                alert.showAndWait();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Show a more user-friendly message or log the error as appropriate
        }
    }

    private boolean authenticateUser(String username, String password) throws ClassNotFoundException, SQLException {
        String sql = "SELECT Password FROM Users WHERE Username = ?";
        // Explicitly load the driver class (might be necessary for some environments)
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("Password");
                return storedPassword.equals(password); // In real applications, compare hashed passwords
            }
        }
        return false;
    }
    
    
    
    
    
}

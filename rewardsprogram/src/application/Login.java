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
            String userId = authenticateUser(inputUsername, inputPassword);
            if (userId != null) {
                // Login successful, prepare to transition
                FXMLLoader loader = new FXMLLoader(getClass().getResource("shopPage.fxml"));
                root = loader.load();

                // Get the ShopController and pass userId
                ShopController shopController = loader.getController();
                shopController.setUserId(userId); 
                
                System.out.printf("Current User Id is: " + userId);
                // Transition to shop page
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
        }
    }


    private String authenticateUser(String username, String password) throws ClassNotFoundException, SQLException {
        String sql = "SELECT UserID, Password FROM Users WHERE Username = ?";
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next() && rs.getString("Password").equals(password)) { // Ideally, hash comparison here
                return rs.getString("UserID"); 
                
             
            }
        }
        return null; // Return null if authentication fails
    }
}
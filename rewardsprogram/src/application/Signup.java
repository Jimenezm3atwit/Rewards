package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Signup {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button ssignup;
    @FXML
    private Button login;
    @FXML
    private Label serror;
    @FXML
    private Label sscucces;
    @FXML
    private TextField susername;
    @FXML
    private PasswordField spassword;
    @FXML
    private TextField semail;
    @FXML
    private TextField sfirstname; // TextField for first name
    @FXML
    private TextField slastname;  // TextField for last name

    public Signup() {
    }

    @FXML
    public void onSignup(ActionEvent event) throws IOException {
        // Collect user input from text fields
        String username = susername.getText();
        String password = spassword.getText(); // Remember to hash passwords in real applications
        String email = semail.getText();
        String firstName = sfirstname.getText();
        String lastName = slastname.getText();

        // Add user to the database
        if (addUser(username, password, email, firstName, lastName)) {
        	sscucces.setText("Signup succeesful! Please return to login.");
            
        } else {
            serror.setText("Signup failed. Please try again."); // Display an error message
        }
    }

    private boolean addUser(String username, String password, String email, String firstName, String lastName) {
        String sql = "INSERT INTO Users (Username, Password, Email, FirstName, LastName) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, email);
            pstmt.setString(4, firstName);
            pstmt.setString(5, lastName);

            int affectedRows = pstmt.executeUpdate();

            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void switchbacklogin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

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
import java.util.regex.Pattern;

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
    private Label ssuccess; // Corrected typo
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
        // Reset success and error messages
        ssuccess.setText("");
        serror.setText("");

        // Collect user input from text fields
        String username = susername.getText().trim();
        String password = spassword.getText().trim(); 
        String email = semail.getText().trim();
        String firstName = sfirstname.getText().trim();
        String lastName = slastname.getText().trim();

        // Validation
        if (!validateNames(firstName) || !validateNames(lastName)) {
            serror.setText("First name and last name must not contain numbers.");
            return;
        }

        if (!email.contains("@")) {
            serror.setText("Email must be valid format '@xxxx.xxx'.");
            return;
        }

        if (username.length() < 4) {
            serror.setText("Username must be at least 4 characters long.");
            return;
        }

        if (password.length() < 6) {
            serror.setText("Password must be at least 6 characters long.");
            return;
        }

        // Add user to the database
        if (addUser(username, password, email, firstName, lastName)) {
            ssuccess.setText("Signup successful! Please return to login.");
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

    private boolean validateNames(String name) {
        return !Pattern.compile("[0-9]").matcher(name).find();
    }

    public void switchbacklogin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
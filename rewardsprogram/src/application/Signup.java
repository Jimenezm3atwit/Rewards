package application;

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

import javafx.event.ActionEvent;




public class Signup {

	private Stage stage;
	private Scene scene;
	private Parent root;
	
	
    public Signup() {

    }

    @FXML
    private Button ssignup;
    @FXML
    private Button login;
    @FXML
    private Label serror;
    @FXML
    private TextField susername;
    @FXML
    private PasswordField spassword;
    
    public void switchbacklogin(ActionEvent event) throws IOException {
    	
    Parent	root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
    
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }
    
    	
    }
    









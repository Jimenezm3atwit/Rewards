package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShopController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
    private List<Item> cartItems = new ArrayList<>();
    private String userId;

    // Assuming you have these buttons in your FXML
    @FXML private Button item1, item2, item3, item4, item5, item6;

    public void setUserId(String userId) {
        this.userId = userId;
        System.out.println("This message is from the shop page, the current user logged in is: " + userId);
    }

  
    @FXML
    private void addItemToCart(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        double price = 0.0;
        switch (clickedButton.getId()) {
            case "item1":
                price = 299.99;
                break;
            case "item2":
                price = 459.69;
                break;
            case "item3":
                price = 650.99;
                break;
            case "item4":
                price = 349.99;
                break;
            case "item5":
                price = 159.99;
                break;
            case "item6":
                price = 99.99;
                break;
        }
        cartItems.add(new Item(clickedButton.getId(), clickedButton.getText(), price));
        System.out.println(clickedButton.getText() + " added to cart for $" + price);
    }

    public void goToCart(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CartPage.fxml"));
        Parent root = loader.load();

        CartController cartController = loader.getController();
        cartController.initializeCart(cartItems);

        // Calculate total cost
        double totalCost = cartItems.stream().mapToDouble(Item::getPrice).sum();
        cartController.setCost(totalCost);

        saveTransaction(userId, totalCost); 

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void saveTransaction(String userId, double totalCost) {
        String sql = "INSERT INTO transactions (AccountID, Amount) VALUES (?, ?)";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            pstmt.setDouble(2, totalCost);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

 
    public void switchbacklogin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
        public void switchtoaccount(ActionEvent event) throws IOException {
            root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    
    
    }
    
        public void switchtorewards(ActionEvent event) throws IOException {
            root = FXMLLoader.load(getClass().getResource("rewardPage.fxml"));

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    
        }
    }

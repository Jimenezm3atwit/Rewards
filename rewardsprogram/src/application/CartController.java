package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class CartController {
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    private Label cost;  // Corrected from LabelSkin to Label

    @FXML
    private ListView<Item> cartListView;

    // Initializes the cart list view with the items added to the cart
    public void initializeCart(List<Item> items) {
        cartListView.getItems().setAll(items);
    }

    // Sets the total cost in the cost label
    public void setCost(double totalCost) {
        cost.setText("$" + String.format("%.2f", totalCost));
    }

    // Switches back to the shop page
    public void switchbackshop(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("shopPage.fxml"));  // Ensure the resource path is correct
        
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

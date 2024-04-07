package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class rewardController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    
    
    @FXML
    private ListView<Transaction> recentTransactions; // ListView to display transactions


    @FXML
    public void initialize() {
        recentTransactions.setCellFactory(lv -> new ListCell<Transaction>() {
            @Override
            protected void updateItem(Transaction item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
              
                    setText(String.format("Date: %s, Items: %s, Amount: $%.2f",
                        item.getDate().toString(), item.getItems(), item.getAmount()));
                }
            }
        });
    }

    // Method to load the transaction history for a specific user
    public void loadTransactionHistory(String userId) {
        System.out.println("Loading transactions for user ID: " + userId);
        ObservableList<Transaction> transactions = FXCollections.observableArrayList();
        String sql = "SELECT Date, Items, Amount FROM transactions WHERE AccountID = ? ORDER BY Date DESC";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                LocalDateTime date = rs.getTimestamp("Date").toLocalDateTime();
                String items = rs.getString("Items");
                double amount = rs.getDouble("Amount");

                transactions.add(new Transaction(date, items, amount));
                System.out.println("Transaction added: " + date + ", " + items + ", " + amount);
            }
            if (transactions.isEmpty()) {
                System.out.println("No transactions found for user ID: " + userId);
            } else {
                recentTransactions.setItems(transactions);
                System.out.println("Transactions loaded: " + transactions.size());
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }
    
    public void switchbackshop(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("shopPage.fxml"));  
        
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
 
}}

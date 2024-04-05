package application;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class rewardController {
    
    @FXML
    private ListView<String> recent; // Assuming you want to display transactions as Strings

    // Method to be called when initializing the transactions page
    public void loadUserTransactions(String userId) {
        List<String> transactionDetails = new ArrayList<>();
        
        String sql = "SELECT Date, ItemsBought, Amount FROM transactions WHERE AccountID = ? ORDER BY Date DESC";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                LocalDateTime date = rs.getTimestamp("Date").toLocalDateTime();
                String itemsBought = rs.getString("ItemsBought");
                double amount = rs.getDouble("Amount");
                
                String transactionDetail = String.format("Date: %s, Items: %s, Amount: $%.2f", 
                                                          date.toString(), itemsBought, amount);
                transactionDetails.add(transactionDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle errors here
        }
        
        recent.getItems().setAll(transactionDetails);
    }
}

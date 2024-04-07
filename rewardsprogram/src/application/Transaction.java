package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Transaction {
    private LocalDateTime date;
    private String items;
    private double amount;

    // Constructor
    public Transaction(LocalDateTime date, String items, double amount) {
        this.date = date;
        this.items = items;
        this.amount = amount;
    }

    // Getters
    public LocalDateTime getDate() {
        return date;
    }

    public String getItems() {
        return items;
    }

    public double getAmount() {
        return amount;
    }

   
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    

}

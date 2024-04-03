package application;

public class ShopController {
    private String userId;

    public void setUserId(String userId) {
        this.userId = userId;
        
        System.out.println("This message is from the shop page, the current user loggin is: " +userId);
        // You can now use userId to load user-specific information in the shop page
    }}

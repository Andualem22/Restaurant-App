package domain;

public class Restaurant implements Comparable<Restaurant>{
    private int restaurantID;
    private String restaurantName;
    private String restaurantAddress;
    private int restaurantRate;
    private int phoneNumber;

    public Restaurant() {
    }

    public Restaurant(int restaurantID, String restaurantName, String restaurantAddress, int restaurantRate, int phoneNumber) {
        this.restaurantID = restaurantID;
        this.restaurantName = restaurantName;
        this.restaurantAddress = restaurantAddress;
        this.restaurantRate = restaurantRate;
        this.phoneNumber = phoneNumber;
    }

    public int getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(int restaurantID) {
        this.restaurantID = restaurantID;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    public int getRestaurantRate() {
        return restaurantRate;
    }

    public void setRestaurantRate(int restaurantRate) {
        this.restaurantRate = restaurantRate;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "restaurantID=" + restaurantID +
                ", restaurantName='" + restaurantName + '\'' +
                ", restaurantAddress='" + restaurantAddress + '\'' +
                ", restaurantRate=" + restaurantRate +
                ", phoneNumber=" + phoneNumber +
                '}';
    }


    @Override
    public int compareTo(Restaurant o) {
        return this.restaurantRate - o.restaurantRate;
    }
}

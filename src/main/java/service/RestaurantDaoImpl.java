package service;

import connection.JDBConnection;
import dao.RestaurantDao;
import domain.Restaurant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RestaurantDaoImpl implements RestaurantDao {

    @Override
    public void addRestaurant(Restaurant restaurant) {
        final String ADD_RESTAURANT_SQL = """
                INSERT INTO restaurant(restaurant_id,restaurant_name,restaurant_address,restaurant_rate,restaurant_phoneNumber)
                VALUES(?,?,?,?,?)
                """;
        try(Connection conn = JDBConnection.getConnection()){
            PreparedStatement preparedStatement = conn.prepareStatement(ADD_RESTAURANT_SQL);
            preparedStatement.setInt(1,restaurant.getRestaurantID());
            preparedStatement.setString(2, restaurant.getRestaurantName());
            preparedStatement.setString(3,restaurant.getRestaurantAddress());
            preparedStatement.setInt(4,restaurant.getRestaurantRate());
            preparedStatement.setInt(5,restaurant.getPhoneNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }



    @Override
    public Restaurant retrieveRestaurantByName(String restaurantName) {
        final String GET_RESTAURANT_BY_NAME_SQL = "SELECT * FROM restaurant WHERE restaurant_name = ?";

        try(Connection connection = JDBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_RESTAURANT_BY_NAME_SQL)){
            preparedStatement.setString(1, restaurantName);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return extractRestaurantFromResultSet(resultSet);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }



    @Override
    public void updateRestaurant(Restaurant restaurant) {
        final String UPDATE_RESTAURANT_SQL = "UPDATE restaurant SET restaurant_name = ?, restaurant_address = ?, restaurant_rate = ?, restaurant_phoneNumber = ? WHERE restaurant_id = ?";

        try(Connection connection = JDBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_RESTAURANT_SQL)){
            preparedStatement.setString(1, restaurant.getRestaurantName());
            preparedStatement.setString(2, restaurant.getRestaurantAddress());
            preparedStatement.setInt(3, restaurant.getRestaurantRate());
            preparedStatement.setInt(4, restaurant.getPhoneNumber());
            preparedStatement.setInt(5,restaurant.getRestaurantID());

            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRestaurant(int restaurantId) {
        final String DELETE_RESTAURANT_SQL = "DELETE FROM restaurant WHERE restaurant_id = ?";
        try(Connection connection = JDBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_RESTAURANT_SQL)){
            preparedStatement.setInt(1, restaurantId);

            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Restaurant> getAllRestaurant() {
        final String GET_ALL_RESTAURANTS_SQL = "SELECT * FROM restaurant";
        List<Restaurant> restaurants = new ArrayList<>();
        try(Connection connection = JDBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_RESTAURANTS_SQL)){

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Restaurant restaurant = extractRestaurantFromResultSet(resultSet);
                restaurants.add(restaurant);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return restaurants;
    }
    @Override
    public Restaurant getRestauranttById(int restaurantId) {
        final String GET_RESTAURANT_BY_ID_SQL = "SELECT * FROM restaurant WHERE restaurant_id = ?";

        try (Connection connection = JDBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_RESTAURANT_BY_ID_SQL)) {

            preparedStatement.setInt(1, restaurantId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return extractRestaurantFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Handle the exception as needed in your application
        }
        return null;
    }

    private Restaurant extractRestaurantFromResultSet(ResultSet resultSet) throws SQLException{
        int restaurantId = resultSet.getInt("restaurant_id");
        String restaurantName = resultSet.getString("restaurant_name");
        String restaurantAddress = resultSet.getString("restaurant_address");
        int restaurantRate = resultSet.getInt("restaurant_rate");
        int restaurantPhoneNumber = resultSet.getInt("restaurant_phoneNumber");

        return new Restaurant(restaurantId, restaurantName, restaurantAddress, restaurantRate, restaurantPhoneNumber);
    }

    public static boolean restaurantExists(int restaurantId) {
        String query = "SELECT COUNT(*) FROM restaurant WHERE restaurant_id = ?";

        try (Connection connection = JDBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, restaurantId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Handle the exception as needed in your application
        }

        return false;
    }

}

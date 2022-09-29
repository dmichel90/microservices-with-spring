package de.dmichel90.restaurant.api;

import de.dmichel90.restaurant.model.Restaurant;
import de.dmichel90.restaurant.services.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RestaurantsApiDelegateImpl implements RestaurantsApiDelegate {

    private final RestaurantService restaurantService;

    public RestaurantsApiDelegateImpl(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Override
    public ResponseEntity<Restaurant> createRestaurant(Restaurant restaurant) {
        return ResponseEntity.ok(restaurantService.create(restaurant));
    }

    @Override
    public ResponseEntity<Void> deleteRestaurant(String id) {
        restaurantService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<Restaurant>> getRestaurants() {
        return ResponseEntity.ok(restaurantService.getRestaurants());
    }

    @Override
    public ResponseEntity<Restaurant> updateRestaurant(Restaurant restaurant) {
        return ResponseEntity.ok(restaurantService.update(restaurant));
    }
}

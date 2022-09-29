package de.dmichel90.restaurant.services;

import de.dmichel90.restaurant.model.Nationality;
import de.dmichel90.restaurant.model.Restaurant;
import de.dmichel90.restaurant.repositories.RestaurantRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.oauth2.jwt.JwtDecoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class RestaurantServiceIT extends BaseIT {

    @MockBean
    JwtDecoder jwtDecoder;

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Test
    public void testCreate() {
        var restaurant = new Restaurant().name("Dolce Vita").nationality(Nationality.ITALIAN);
        restaurant = restaurantService.create(restaurant);
        var id = restaurant.getId();
        var optionalRestaurant = restaurantRepository.findById(id);
        assertTrue(optionalRestaurant.isPresent());
    }
}

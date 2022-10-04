package de.dmichel90.food.services;

import de.dmichel90.food.model.Dish;
import de.dmichel90.food.model.FoodType;
import de.dmichel90.food.repositories.DishRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.oauth2.jwt.JwtDecoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class DishServiceIT extends BaseIT {

    @MockBean
    JwtDecoder jwtDecoder;

    @Autowired
    DishService dishService;

    @Autowired
    DishRepository dishRepository;

    @Test
    public void testCreate() {
        var dish = new Dish().name("Dolce Vita").type(FoodType.WITH_MEAT);
        dish = dishService.create(dish);
        var id = dish.getId();
        var optionalRestaurant = dishRepository.findById(id);
        assertTrue(optionalRestaurant.isPresent());
    }
}

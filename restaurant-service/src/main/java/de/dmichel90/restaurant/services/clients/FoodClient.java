package de.dmichel90.restaurant.services.clients;

import de.dmichel90.restaurant.model.Dish;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("food")
public interface FoodClient {

    @RequestMapping(method = RequestMethod.GET, value = "v1/dishes/restaurants/{restaurant_id}", consumes = "application/json")
    List<Dish> getDishesByRestaurant(@PathVariable("restaurant_id") String restaurantId);
}

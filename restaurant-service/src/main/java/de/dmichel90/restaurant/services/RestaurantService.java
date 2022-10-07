package de.dmichel90.restaurant.services;

import de.dmichel90.restaurant.domain.RestaurantEntity;
import de.dmichel90.restaurant.model.Restaurant;
import de.dmichel90.restaurant.repositories.RestaurantRepository;
import de.dmichel90.restaurant.services.clients.FoodClient;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    private final ModelMapper modelMapper;
    private final RestaurantRepository restaurantRepository;
    private final FoodClient foodClient;

    public RestaurantService(ModelMapper modelMapper, RestaurantRepository restaurantRepository, FoodClient foodClient) {
        this.modelMapper = modelMapper;
        this.restaurantRepository = restaurantRepository;
        this.foodClient = foodClient;
    }

    public Restaurant create(Restaurant restaurant) {
        var entity = modelMapper.map(restaurant, RestaurantEntity.class);
        restaurantRepository.save(entity);
        return modelMapper.map(entity, Restaurant.class);
    }

    public void delete(String id) {
        restaurantRepository.deleteById(id);
    }

    public Restaurant update(Restaurant restaurant) {
        var entity = modelMapper.map(restaurant, RestaurantEntity.class);
        restaurantRepository.save(entity);
        return modelMapper.map(entity, Restaurant.class);
    }

    public List<Restaurant> getRestaurants() {
        return restaurantRepository.findAll().stream().map(restaurantEntity -> modelMapper.map(restaurantEntity, Restaurant.class)).collect(Collectors.toList());
    }

    public Restaurant findById(String id) {
        var optional = restaurantRepository.findById(id);
        return optional.map(restaurantEntity -> {
            var restaurant = modelMapper.map(restaurantEntity, Restaurant.class);
            var dishes = foodClient.getDishesByRestaurant(id);
            restaurant.setDishes(dishes);
            return restaurant;
        }).orElseThrow(
                () -> new NoSuchElementException("No restaurant with ID " + id + " found"));
    }
}

package de.dmichel90.restaurant.services;

import de.dmichel90.restaurant.domain.RestaurantEntity;
import de.dmichel90.restaurant.model.Restaurant;
import de.dmichel90.restaurant.repositories.RestaurantRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    private final ModelMapper modelMapper;
    private final RestaurantRepository restaurantRepository;

    public RestaurantService(ModelMapper modelMapper, RestaurantRepository restaurantRepository) {
        this.modelMapper = modelMapper;
        this.restaurantRepository = restaurantRepository;
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
}

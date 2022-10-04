package de.dmichel90.food.services;

import de.dmichel90.food.model.Dish;
import de.dmichel90.food.domain.DishEntity;
import de.dmichel90.food.repositories.DishRepository;
import de.dmichel90.food.repositories.DishRestaurantRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DishService {

    private final ModelMapper modelMapper;
    private final DishRepository dishRepository;
    private final DishRestaurantRepository dishRestaurantRepository;

    public DishService(ModelMapper modelMapper, DishRepository dishRepository, DishRestaurantRepository dishRestaurantRepository) {
        this.modelMapper = modelMapper;
        this.dishRepository = dishRepository;
        this.dishRestaurantRepository = dishRestaurantRepository;
    }

    public Dish create(Dish dish) {
        var entity = modelMapper.map(dish, DishEntity.class);
        dishRepository.save(entity);
        return modelMapper.map(entity, Dish.class);
    }

    public void delete(String id) {
        dishRepository.deleteById(id);
    }

    public Dish update(Dish dish) {
        var entity = modelMapper.map(dish, DishEntity.class);
        dishRepository.save(entity);
        return modelMapper.map(entity, Dish.class);
    }

    public List<Dish> getDishes() {
        return dishRepository.findAll().stream().map(dishEntity -> modelMapper.map(dishEntity, Dish.class)).collect(Collectors.toList());
    }

    public List<Dish> getDishesByRestaurant(String restaurantId) {
        return dishRestaurantRepository.findAllByRestaurantId(restaurantId).stream().map(dishRestaurantEntity -> modelMapper.map(dishRestaurantEntity.getDish(), Dish.class)).collect(Collectors.toList());
    }
}
